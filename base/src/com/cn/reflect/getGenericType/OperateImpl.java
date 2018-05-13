//package com.tone.little.util.impl;
//
//import com.tone.little.annotation.*;
//import com.tone.little.exception.ConditionException;
//import com.tone.little.exception.ConstructException;
//import com.tone.little.obj.HqlObj;
//import com.tone.little.obj.QueryField;
//import com.tone.little.util.Operate;
//import com.tone.little.util.Operator;
//import javassist.ClassClassPath;
//import javassist.ClassPool;
//import javassist.CtClass;
//import javassist.CtConstructor;
//import javassist.bytecode.CodeAttribute;
//import javassist.bytecode.LocalVariableAttribute;
//import javassist.bytecode.MethodInfo;
//import org.apache.commons.lang.StringEscapeUtils;
//
//import javax.persistence.Id;
//import java.io.Serializable;
//import java.lang.reflect.*;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
///**
// * hql各个操作的实现对象
// * @author zlf
// */
//// TODO 1.需要按照传入的QueryField的顺序构造查询条件（当前先遍历构造方法，然后按字段顺序来。会变得不明确，有问题）
//public class OperateImpl implements Operate {
//	private static final int startIndex = 0;
//	private ThreadLocal<SimpleDateFormat> dateFormat;
//	private ThreadLocal<SimpleDateFormat> timeFormat;
//	private ThreadLocal<SimpleDateFormat> datetimeFormat;
//
//	public OperateImpl() {
//		super();
//		dateFormat = new ThreadLocal<SimpleDateFormat>() {
//			@Override
//			protected SimpleDateFormat initialValue() {
//				return new SimpleDateFormat("yyyy-MM-dd");
//			}
//		};
//		timeFormat = new ThreadLocal<SimpleDateFormat>() {
//			@Override
//			protected SimpleDateFormat initialValue() {
//				return new SimpleDateFormat("HH:mm:ss");
//			}
//		};
//		datetimeFormat = new ThreadLocal<SimpleDateFormat>() {
//			@Override
//			protected SimpleDateFormat initialValue() {
//				return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			}
//		};
//	}
//
//	public HqlObj entity2Hql(Object entity, Object viewObject,
//			final QueryField... queryFields) throws Exception {
//		return this.entity2Hql(entity, viewObject, null, queryFields);
//	}
//
//	public HqlObj entity2Hql(Object entity, Object viewObject, Integer constructorIndex,
//			final QueryField... queryFields) throws Exception {
//		boolean isEntity = false;
//		Class<? extends Object> voClass = viewObject.getClass();
//		Class<? extends Object> enClass = entity.getClass();
//		// hql entity string
//		StringBuilder hqlEntity = new StringBuilder();
//		// query filter string
//		StringBuilder hqlCondition = new StringBuilder();
//		// vo construct string
//		StringBuilder voConstruct = new StringBuilder();
//		// order by string
//		StringBuilder orderBy = new StringBuilder();
//
//		String entityName = null;
//		SortField[] sortFields = null;
//		EntityMap entityMap = voClass.getAnnotation(EntityMap.class);
//		if (entityMap != null){
//			// If the field is set
//			if (!entityMap.name().isEmpty()) {
//				entityName = entityMap.name();
//			}
//			SortEntity[] fieldSorts = entityMap.sortfield();
//			if (fieldSorts != null) {
//				sortFields = new SortField[fieldSorts.length];
//				for (int i = 0; i < fieldSorts.length; i++) {
//					sortFields[i] = new SortField(fieldSorts[i].field(), fieldSorts[i].type());
//				}
//			} else {
//				sortFields = new SortField[1];
//				Method[] methods = enClass.getDeclaredMethods();
//				for (Method method : methods) {
//					// id field
//					Id id = method.getAnnotation(Id.class);
//					if (id != null) {
//						sortFields[0] = new SortField(Operator.methodName2FieldName(method.getName()), true);
//						break;
//					}
//				}
//			}
//		} else {
//			sortFields = new SortField[0];
//		}
//		if (entityName == null) {
//			if (voClass.equals(enClass)) {
//				isEntity = true;
//			}
//			entityName = enClass.getSimpleName();
//		}
//
//		hqlEntity.append(" from ");
//		hqlEntity.append(entityName);
//		hqlEntity.append(" model");
//		hqlEntity.append(startIndex);
//		// the construct field names
//		List<String> fieldNames = new ArrayList<String>();
//		if (isEntity) {
//			Field[] fields = voClass.getDeclaredFields();
//			for (Field field : fields) {
//				if (!Modifier.isFinal(field.getModifiers()) && !Modifier.isStatic(field.getModifiers())) {
//					fieldNames.add(field.getName());
//				}
//			}
//		} else {
//			voConstruct.append("select new ");
//			voConstruct.append(voClass.getName());
//			voConstruct.append("(");
//
//			ClassPool pool = ClassPool.getDefault();
//			pool.insertClassPath(new ClassClassPath(voClass));
//			CtClass cc = pool.get(voClass.getName());
//			CtConstructor[] constructors = cc.getConstructors();
//			// detect ConstructMap annotation, first construct which has construct param will be set when don't detected annotation and constructorIndex is null
//			CtConstructor cm = null;
//			if (constructorIndex != null && 0 < constructorIndex && constructorIndex < constructors.length) {
//				cm = constructors[constructorIndex];
//			} else {
//				for (CtConstructor ctConstructor : constructors) {
//					if (ctConstructor.getAnnotation(ConstructMap.class) != null) {
//						cm = ctConstructor;
//						break;
//					} else if (cm == null && ctConstructor.getParameterTypes().length > 0) {
//						cm = ctConstructor;
//						break;
//					}
//				}
//			}
//			if (cm == null || cm.getParameterTypes().length == 0) {
//				throw new ConstructException();
//			}
//			// use java-assist get construct params
//			MethodInfo methodInfo = cm.getMethodInfo();
//			CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
//			LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute
//					.getAttribute(LocalVariableAttribute.tag);
//			if (attr != null) {
//				int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
//				for (int i = 0; i < cm.getParameterTypes().length; i++) {
//					fieldNames.add(attr.variableName(i + pos));
//				}
//			}
//		}
//
//		// join model's index
//		int joinIndex = startIndex;
//		Field[] fields = voClass.getDeclaredFields();
//		Map<String, String> constructMap = new HashMap<String, String>(fieldNames.size());
//		for (Field field : fields) {
//			if (!Modifier.isFinal(field.getModifiers()) && !Modifier.isStatic(field.getModifiers())) {
//				String fieldName = field.getName();
//				boolean defalutContruct = !isEntity && fieldNames.contains(fieldName);
//				Method method = voClass.getDeclaredMethod(Operator.fieldName2GetMethodName(fieldName));
//				FieldTransient fieldTransient = method.getAnnotation(FieldTransient.class);
//				if (fieldTransient == null) {
//					String entityField = fieldName;
//					int modelIdx = startIndex;
//					Object result = method.invoke(viewObject);
//					if (result != null) {
//						// has value
//						FieldMap fieldMap = method.getAnnotation(FieldMap.class);
//						if (fieldMap != null) {
//							entityField = fieldMap.name();
//							if (!fieldMap.type().isEmpty()) {
//								String[] entFields = entityField.split("[.]");
//								int index = 0;
//								for (index = startIndex;  index < entFields.length - 1; index++) {
//									String entField = entFields[index];
//									hqlEntity.append(" ");
//									hqlEntity.append(fieldMap.type().toString());
//									hqlEntity.append(" model");
//									hqlEntity.append(index);
//									hqlEntity.append(".");
//									hqlEntity.append(entField);
//									hqlEntity.append(" model");
//									hqlEntity.append(++joinIndex);
//								}
//								if (defalutContruct) {
//									StringBuilder fieldConstruct = new StringBuilder();
//									fieldConstruct.append("model");
//									fieldConstruct.append(joinIndex);
//									fieldConstruct.append(".");
//									fieldConstruct.append(entFields[index]);
//									fieldConstruct.append(", ");
//									constructMap.put(fieldName, fieldConstruct.toString());
//									defalutContruct = false;
//								}
//								modelIdx = joinIndex;
//								entityField = entFields[index];
//							}
//						}
//						this.matchCondition(hqlCondition, fieldName, modelIdx, entityField, result, queryFields);
//					} else {
//						FieldMap fieldMap = method.getAnnotation(FieldMap.class);
//						if (fieldMap != null) {
//							entityField = fieldMap.name();
//							// if type not empty, must match join type
//							if (!fieldMap.type().isEmpty()) {
//								String[] entFields = entityField.split("[.]");
//								int index = 0;
//								for (index = startIndex;  index < entFields.length - 1; index++) {
//									String entField = entFields[index];
//									hqlEntity.append(" ");
//									hqlEntity.append(fieldMap.type().toString());
//									hqlEntity.append(" model");
//									hqlEntity.append(index);
//									hqlEntity.append(".");
//									hqlEntity.append(entField);
//									hqlEntity.append(" model");
//									hqlEntity.append(++joinIndex);
//								}
//								if (defalutContruct) {
//									StringBuilder fieldConstruct = new StringBuilder();
//									fieldConstruct.append("model");
//									fieldConstruct.append(joinIndex);
//									fieldConstruct.append(".");
//									fieldConstruct.append(entFields[index]);
//									fieldConstruct.append(", ");
//									constructMap.put(fieldName, fieldConstruct.toString());
//									defalutContruct = false;
//								}
//								modelIdx = joinIndex;
//								entityField = entFields[index];
//							}
//						}
//					}
//					if (defalutContruct) {
//						StringBuilder fieldConstruct = new StringBuilder();
//						fieldConstruct.append("model");
//						fieldConstruct.append(startIndex);
//						fieldConstruct.append(".");
//						fieldConstruct.append(entityField);
//						fieldConstruct.append(", ");
//						constructMap.put(fieldName, fieldConstruct.toString());
//					}
//					// order by
//					for (SortField sortField : sortFields) {
//						if (sortField.entityField == null && sortField.fieldName.equals(fieldName)) {
//							sortField.entityField = entityField;
//							sortField.modelIdx = modelIdx;
//							break;
//						}
//					}
//				} else {
//					if (defalutContruct) {
//						throw new ConstructException("The transient field can not in hql construct!");
//					}
//				}
//			}
//		}
//		// construct
//		for (String fieldName : fieldNames) {
//			voConstruct.append(constructMap.get(fieldName));
//		}
//		if (voConstruct.length() > 0) {
//			voConstruct.deleteCharAt(voConstruct.length() - 2);
//			voConstruct.append(")");
//		}
//		if (hqlCondition.length() > 0) {
//			hqlEntity.append(" where");
//			hqlEntity.append(hqlCondition.substring(4));
//		}
//
//		// order by
//		if (sortFields.length > 0) {
//			orderBy.append(" order by");
//			for (SortField sortField : sortFields) {
//				orderBy.append(" model");
//				orderBy.append(sortField.modelIdx);
//				orderBy.append('.');
//				orderBy.append(sortField.entityField);
//				orderBy.append(" ");
//				orderBy.append(sortField.sort);
//				orderBy.append(',');
//			}
//			orderBy.deleteCharAt(orderBy.length() - 1);
//		}
//		HqlObj hqlObj = new HqlObj(hqlEntity.toString(), voConstruct.toString(), orderBy.toString());
//		return hqlObj;
//	}
//
//	private static class SortField {
//		private int modelIdx;
//		private String fieldName;
//		private String entityField;
//		private String sort;
//
//		public SortField(String fieldName) {
//			this(fieldName, SortEntity.ASC);
//		}
//
//		public SortField(String fieldName, boolean entity) {
//			super();
//			this.modelIdx = OperateImpl.startIndex;
//			if (entity) {
//				this.entityField = fieldName;
//			} else {
//				this.fieldName = fieldName;
//			}
//			this.sort = SortEntity.ASC;
//		}
//
//		public SortField(String fieldName, String sort) {
//			this(fieldName, null, sort);
//		}
//
//		public SortField(String fieldName, String entityField, String sort) {
//			super();
//			this.modelIdx = OperateImpl.startIndex;
//			this.fieldName = fieldName;
//			this.entityField = entityField;
//			this.sort = sort;
//		}
//
//	}
//
//	/**
//	 * 匹配queryFields的条件
//	 * @param hqlCondition
//	 * @param fieldName 字段名称
//	 * @param modelIdx 实体类的下标
//	 * @param entityField 实体字段名称
//	 * @param result 显示对象该字段数据值
//	 * @param queryFields 查询的匹配条件
//	 */
//	private void matchCondition(StringBuilder hqlCondition, String fieldName, int modelIdx,
//			String entityField,	Object result, QueryField... queryFields) {
//		if (queryFields != null && queryFields.length > 0) {
//			boolean find = false;
//			for (QueryField queryField : queryFields) {
//				if (fieldName.equals(queryField.getName())) {
//					hqlCondition.append(queryField.getState().getLogic());
//					hqlCondition.append("model");
//					hqlCondition.append(modelIdx);
//					hqlCondition.append(".");
//					hqlCondition.append(entityField);
//					hqlCondition.append(queryField.getState().getSimilar());
//					if (queryField.getState().getSimilar().matches("\\slike\\s'%")) {
//						hqlCondition.append(convertSqlLikeKey(this.getResultString(result)));
//						hqlCondition.append("%'");
//						hqlCondition.append(" escape '/'");
//						hqlCondition.append(queryField.getState().getEndStr());
//					} else if (queryField.getState().getSimilar().matches("\\s*is\\s*(not)?\\s*null\\s*")) {
//						hqlCondition.append(queryField.getState().getEndStr());
//					} else if (queryField.getState().getSimilar().matches("\\s*(not)?\\s*in\\s*")) {
//						hqlCondition.append("(");
//						List<String> values = queryField.getValues();
//						if (values != null && !values.isEmpty()) {
//							for (String value : values) {
//								hqlCondition.append('\'').append(value).append('\'').append(',');
//							}
//							hqlCondition.deleteCharAt(hqlCondition.length() - 1);
//						} else {
//							throw ConditionException.forException(" in " + values);
//						}
//						hqlCondition.append(")");
//						hqlCondition.append(queryField.getState().getEndStr());
//					} else if (queryField.getState().getSimilar().matches("\\s*(not)?\\s*between\\s*")) {
//						List<String> values = queryField.getValues();
//						if (values != null && values.size() >= 2) {
//							hqlCondition.append('\'').append(values.get(0)).append('\'').append(" and ")
//									.append('\'').append(values.get(1)).append('\'');
//						} else {
//							throw ConditionException.forException(" between " + values);
//						}
//					} else {
//						hqlCondition.append(this.getResultString(result));
//						hqlCondition.append("'");
//						hqlCondition.append(queryField.getState().getEndStr());
//					}
//					find = true;
//					break;
//				}
//			}
//			if (!find) {
//				hqlCondition.append(" and model");
//				hqlCondition.append(modelIdx);
//				hqlCondition.append(".");
//				hqlCondition.append(entityField);
//				hqlCondition.append(" like '%");
//				hqlCondition.append(convertSqlLikeKey(this.getResultString(result)));
//				hqlCondition.append("%'");
//				hqlCondition.append(" escape '/'");
//			}
//		} else {
//			hqlCondition.append(" and model");
//			hqlCondition.append(modelIdx);
//			hqlCondition.append(".");
//			hqlCondition.append(entityField);
//			hqlCondition.append(" like '%");
//			hqlCondition.append(convertSqlLikeKey(this.getResultString(result)));
//			hqlCondition.append("%'");
//			hqlCondition.append(" escape '/'");
//		}
//	}
//
//	/**
//	 * 根据不同的日期时间对象获取对应的字符串表达式
//	 * @param result Timestamp,Date等日期时间对象
//	 * @return 格式化后的时间字符串
//	 */
//	private String getResultString(Object result) {
//		String value = result.toString();
//		if (result instanceof java.sql.Timestamp) {
//			value = datetimeFormat.get().format(result);
//		} else if (result instanceof java.sql.Date) {
//			value = dateFormat.get().format(result);
//		} else if (result instanceof java.util.Date) {
//			value = dateFormat.get().format(result);
//		} else if (result instanceof java.sql.Time) {
//			value = timeFormat.get().format(result);
//		}
//		return value;
//	}
//
//	public void buildEntityFromView(Object entity, Object viewObject, final String... fields) throws Exception {
//		Class<? extends Object> entClass = entity.getClass();
//		Class<? extends Object> voClass = viewObject.getClass();
//		Method[] methods = voClass.getMethods();
//		for (Method method : methods) {
//			if (method.getName().matches(Operator.REGEX_GET)) {
//				FieldTransient fieldTransient = method.getAnnotation(FieldTransient.class);
//				if (fieldTransient == null) {
//					Object result = method.invoke(viewObject);
//					String fieldName = Operator.methodName2FieldName(method.getName());
//					if (result != null || this.containsField(fieldName, false, fields)) {
//						FieldMap fieldMap = method.getAnnotation(FieldMap.class);
//						if (fieldMap != null) {
//							String name = fieldMap.name();
//							Object beforeEnt = entity;
//							while (true) {
//								if (name.matches(Operator.REGEX_1_LEVEL)) {
//									String setName  = Operator.fieldName2SetMethodName(name);
//									Method setMethod = beforeEnt.getClass().getDeclaredMethod(setName, method.getReturnType());
//									setMethod.invoke(beforeEnt, result);
//								} else if (name.matches(Operator.REGEX_2_LEVEL)) {
//									String[] levels = name.split("[.]", 2);
//									Method entMethod = beforeEnt.getClass().getDeclaredMethod(Operator.
//											fieldName2GetMethodName(levels[0]));
//									// may lazy initial, so do not get next level object
//									// no next level class instance, new class
//									Class<? extends Object> emdClass = entMethod.getReturnType();
////									Object embObject = emdClass.newInstance();
//									Object embObject = entMethod.invoke(beforeEnt);
//									if (embObject == null) {
//										embObject = emdClass.newInstance();
//									}
//									// set to parent level class
//									Method setMethod = beforeEnt.getClass().getDeclaredMethod(Operator.
//											fieldName2SetMethodName(levels[0]), emdClass);
//									setMethod.invoke(beforeEnt, embObject);
//									name = levels[1];
//									beforeEnt = embObject;
//									continue;
//								}
//								break;
//							}
//						} else {
//							String setName  = Operator.getMethod2SetMethod(method.getName());
//							Method setMethod = entClass.getDeclaredMethod(setName, method.getReturnType());
//							setMethod.invoke(entity, result);
//						}
//					}
//				}
//			}
//		}
//	}
//
//	public void buildViewFromEntity(Object entity, Object viewObject, final String... fields) throws Exception {
//		Class<? extends Object> entClass = entity.getClass();
//		Class<? extends Object> voClass = viewObject.getClass();
//		Field[] dFields = voClass.getDeclaredFields();
//		for (Field field : dFields) {
//			// This field is not final, go to next.
//			if (!Modifier.isFinal(field.getModifiers())) {
//				String fieldName = field.getName();
//				if (this.containsField(fieldName, true, fields)) {
//					String getName = Operator.fieldName2GetMethodName(fieldName);
//					Method voMethod = voClass.getDeclaredMethod(getName);
//					FieldTransient fieldTransient = voMethod.getAnnotation(FieldTransient.class);
//					if (fieldTransient == null) {
//						FieldMap fieldMap = voMethod.getAnnotation(FieldMap.class);
//						if (fieldMap != null) {
//							String name = fieldMap.name();
//							Object beforeEnt = entity;
//							while (true) {
//								if (name.matches(Operator.REGEX_1_LEVEL)) {
//									Method entMethod = beforeEnt.getClass().getDeclaredMethod(Operator.fieldName2GetMethodName(name));
//									Object result = entMethod.invoke(beforeEnt);
//									if (result != null) {
//										String setName  = Operator.fieldName2SetMethodName(fieldName);
//										Method setMethod = voClass.getDeclaredMethod(setName, entMethod.getReturnType());
//										setMethod.invoke(viewObject, result);
//									}
//								} else if (name.matches(Operator.REGEX_2_LEVEL)) {
//									String[] levels = name.split("[.]", 2);
//									Method entMethod = beforeEnt.getClass().getDeclaredMethod(Operator.fieldName2GetMethodName(levels[0]));
//									beforeEnt = entMethod.invoke(beforeEnt);
//									if (beforeEnt != null) {
//										name = levels[1];
//										continue;
//									}
//								}
//								break;
//							}
//						} else {
//							Method entMethod = entClass.getDeclaredMethod(getName);
//							Object result = entMethod.invoke(entity);
//							if (result != null) {
//								String setName  = Operator.fieldName2SetMethodName(fieldName);
//								Method setMethod = voClass.getDeclaredMethod(setName, field.getType());
//								if (result instanceof Collection) {
//									// 是泛型列表需要进行处理。先获取显示对象内对应的泛型类
//									Class<?> subVoCls = this.getGenerics(field);
//									Collection<Object> voCollection = null;
//									// 获取显示对象内对应的泛型是使用List还是Set存储的
//									if (field.getType().isAssignableFrom(List.class)) {
//										voCollection = new ArrayList<Object>();
//									} else {
//										voCollection = new HashSet<Object>();
//									}
//									// 遍历实体内泛型的数据
//									Collection<?> collection = (Collection<?>)result;
//									for (Object subEnt : collection) {
//										Object subVo = subVoCls.newInstance();
//										// 转换泛型类
//										this.buildViewFromEntity(subEnt, subVo, fields);
//										voCollection.add(subVo);
//									}
//									setMethod.invoke(viewObject, voCollection);
//								} else {
//									setMethod.invoke(viewObject, result);
//								}
//							}
//						}
//					}
//				}
//			}
//		}
//	}
//
//	/**
//	 * 获得泛型类的class
//	 * @param field 由于泛型在运行时会自动替换为E(如List<Element>会替换成List<E>)，所以在获取泛型时需要使用到声明时用的Field
//	 */
//	private Class<?> getGenerics(Field field) {
//		// 关键的地方，如果是List类型，得到其Generic的类型
//		Type genericType = field.getGenericType();
//		Class<?> genericCls = null;
//		if (genericType != null) {
//			// 判断是否泛型类型
//			if (genericType instanceof ParameterizedType) {
//				ParameterizedType pt = (ParameterizedType)genericType;
//				genericCls = (Class<?>) pt.getActualTypeArguments()[0];
//			}
//		}
//		return genericCls;
//	}
//
//	/**
//	 * If the given field is included in the field's list
//	 * @param name The given field name
//	 * @param nullFlag If fields is null, contains or not
//	 * @param fields The field's list
//	 * @return true: if contains; false: not contains
//	 */
//	private boolean containsField(String name, boolean nullFlag, final String... fields) {
//		boolean contains = false;
//		if (fields != null && fields.length > 0) {
//			for (String field : fields) {
//				if (field.equalsIgnoreCase(name)) {
//					contains = true;
//					break;
//				}
//			}
//		} else {
//			contains = nullFlag;
//		}
//		return contains;
//	}
//
//	public Serializable getViewIdValue(Object entity, Object viewObject) throws Exception {
//		Class<? extends Object> entClass = entity.getClass();
//		Class<? extends Object> voClass = viewObject.getClass();
//		Method[] methods = entClass.getDeclaredMethods();
//		String getMetName = null;
//		for (Method method : methods) {
//			// id field
//			Id id = method.getAnnotation(Id.class);
//			if (id != null) {
//				getMetName = method.getName();
//				break;
//			}
//		}
//		Serializable idValue = null;
//		if (getMetName != null) {
//			Method getMethod = null;
//			try {
//				getMethod = voClass.getMethod(getMetName);
//			} catch (NoSuchMethodException e) {
//
//			}
//			if (getMethod == null) {
//				String fieldName = Operator.methodName2FieldName(getMetName);
//				methods = voClass.getDeclaredMethods();
//				for (Method method : methods) {
//					FieldMap fieldMap = method.getAnnotation(FieldMap.class);
//					if (fieldMap != null && fieldMap.name().equals(fieldName)) {
//						getMethod = method;
//						break;
//					}
//				}
//			}
//			if (getMethod != null) {
//				idValue = (Serializable)getMethod.invoke(viewObject);
//			}
//		}
//		return idValue;
//	}
//
//	/**
//	 * 重写SQL语句中特殊字符串
//	 * @param keyword 需要重写的字符串
//	 * @return 重写后的字符串
//	 */
//	public String convertSqlLikeKey(String keyword) {
//		if (keyword == null) {
//			return keyword;
//		}
//		keyword = keyword.trim();
//		keyword = StringEscapeUtils.escapeSql(keyword);
//		return keyword.replace("\\", "/\\/\\")
//				.replace("%", "/%").replace("[", "/[").replace("]", "/]")
//				.replace("_", "/_").replace("\"", "/\"");
//	}
//}
