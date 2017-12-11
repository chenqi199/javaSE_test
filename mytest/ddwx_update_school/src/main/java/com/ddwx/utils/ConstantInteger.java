package com.ddwx.utils;

public interface ConstantInteger {
    /**
     * 家长端
     */
    int PARENT_ACTIVE = 0;
    /**
     * 家长端重置密码
     */
    int PARENT_RESET_PASSWORD = 2;
    /**
     * 教师端注册
     */
    int TEACHER_ACTIVE = 1;
    /**
     * 教师端重置密码
     */
    int TEACHER_RESET_PASSWORD = 3;
    /**
     * 家长端修改手机号
     */
    int PARENT_UPDATE_MOBILE = 4;
    /**
     * 教师端修改手机号
     */
    int TEACHER_UPDATE_MOBILE = 5;

    /**
     * 设备类型为PC
     */
    int DEVICE_TYPE_PC = 3;

    int AUTHORITY_NOT_LEAF = 0;
    int AUTHORITY_LEAF = 1;

    int MESSAGE_AUTHOR_TYPE_SCHOOL = 0;
    int MESSAGE_AUTHOR_TYPE_PARENT = 1;
    int MESSAGE_AUTHOR_TYPE_TEACHER = 2;
    // 作者为孩子仅为发送通知使用
    int MESSAGE_AUTHOR_TYPE_CHILD = 3;
    // 仅用于发送通知使用
    int MESSAGE_AUTHOR_TYPE_TEACHER_USER = 4;

    // 消息SCOPE
    int MESSAGE_SCOPE_ALL = 0;
    int MESSAGE_SCOPE_SCHOOL = 1;
    int MESSAGE_SCOPE_GRADE = 2;
    int MESSAGE_SCOPE_CLASS = 3;
    int MESSAGE_SCOPE_STUDENT = 4;
    int MESSAGE_SCOPE_TEACHER = 5;
    // 仅用于查timeline的时候，跟老师区分是否显示隐藏
    int MESSAGE_SCOPE_PRINCIPAL = 6;
    // 通知SCOPE
    int NOTICE_SCOPE_ALL = 50;
    int NOTICE_SCOPE_ALL_STUDENT = 51;
    int NOTICE_SCOPE_ALL_TEACHER = 52;
    int NOTICE_SCOPE_SCHOOL = 53;
    int NOTICE_SCOPE_SCHOOL_STUDENT = 54;
    int NOTICE_SCOPE_SCHOOL_TEACHER = 55;
    int NOTICE_SCOPE_GRADE = 56;
    int NOTICE_SCOPE_GRADE_STUDENT = 57;
    int NOTICE_SCOPE_GRADE_TEACHER = 58;
    int NOTICE_SCOPE_CLASS = 59;
    int NOTICE_SCOPE_CLASS_STUDENT = 60;
    int NOTICE_SCOPE_CLASS_TEACHER = 61;
    int NOTICE_SCOPE_STUDENT = 62;
    int NOTICE_SCOPE_TEACHER = 63;
    // 由于增加园长发送的通知所添加的类型
    int NOTICE_SCOPE_SCHOOL_PRINCIPAL = 64;
    int NOTICE_SCOPE_SCHOOL_STUDENT_TEACHER = 65;
    int NOTICE_SCOPE_SCHOOL_TEACHER_PRINCIPAL = 66;
    int NOTICE_SCOPE_SCHOOL_STUDENT_PRINCIPAL = 67;
    int NOTICE_SCOPE_ALL_PRINCIPAL = 68;
    int NOTICE_SCOPE_ALL_STUDENT_TEACHER = 69;
    int NOTICE_SCOPE_ALL_TEACHER_PRINCIPAL = 70;
    int NOTICE_SCOPE_ALL_STUDENT_PRINCIPAL = 71;
    // 仅用于发送通知给家长
    int NOTICE_SCOPE_PARENT = 100;
    // 仅用于发送通知给教师
    int NOTICE_SCOPE_TEACHER_USER = 101;

    /**
     * Timeline消息
     */
    int MESSAGE_NOTICE_MESSAGE = 0;
    /**
     * Timeline通知
     */
    int MESSAGE_NOTICE_NOTICE = 1;

    /**
     * 普通信息
     */
    int MESSAGE_TYPE_NORMAL = 0;
    /**
     * 视频消息
     */
    int MESSAGE_TYPE_VIDEO = 1;
    /**
     * 教师点评-通知
     */
    int MESSAGE_TYPE_COMMENT = 2;
    /**
     * 周计划
     */
    int MESSAGE_TYPE_SCHEDULE = 3;
    /**
     * 园方新闻动态
     */
    int MESSAGE_TYPE_DYNAMIC = 4;
    /**
     * 广告-消息
     */
    int MESSAGE_TYPE_AD = 5;
    /**
     * 食谱
     */
    int MESSAGE_TYPE_RECIPE = 6;
    /**
     * 家庭作业-通知
     */
    int MESSAGE_TYPE_HOMEWORK = 7;
    /**
     * 考勤-通知
     */
    int MESSAGE_TYPE_ATTENDANCE = 8;
    /**
     * 教师发布的通知-通知
     */
    int MESSAGE_TYPE_NOTICE = 9;
    /**
     * 请假 - 通知
     */
    int MESSAGE_TYPE_LEAVE = 10;
    /**
     * 请假回应 - 通知
     */
    int MESSAGE_TYPE_LEAVE_ACT = 11;
    /**
     * 视频开通 - 通知
     */
    int MESSAGE_TYPE_OPEN_VIDEO = 12;
    // ------------点赞类
    /**
     * 资讯讨论被点赞
     */
    int MESSAGE_FEED_DISCUSS_PRAISE = 100;
    /**
     * 完成的作业被点赞
     */
    int MESSAGE_ASSIGNMENT_COMPLETE_PRAISE = 101;
    /**
     * 动态消息被点赞
     */
    int MESSAGE_DYNAMIC_PRAISE = 103;
    // ------------消息类
    /**
     * 资讯评论消息
     */
    int MESSAGE_TYPE_FEED_DISCUSS = 102;
    /**
     * 完成的作业被评论
     */
    int MESSAGE_ASSIGNMENT_COMPLETE_DISCUSS = 104;
    /**
     * 邀请消息
     */
    int MESSAGE_TYPE_INVITE = 105;
    /**
     * 邀请消息回复
     */
    int MESSAGE_TYPE_INVITE_ACT = 106;
    /**
     * 入园申请消息
     */
    int MESSAGE_TYPE_INCOME_APPLY = 107;
    /**
     * 入园申请回复
     */
    int MESSAGE_TYPE_INCOME_APPLY_ACT = 108;
    /**
     * 动态消息被评论
     */
    int MESSAGE_DYNAMIC_DISCUSS = 109;

    /**
     * 该条消息禁用，不显示
     */
    int MESSAGE_STATUS_NOT_ACTIVE = 0;
    /**
     * 该条消息启用，显示
     */
    int MESSAGE_STATUS_ACTIVE = 1;

    int USER_TAB_POOL_TYPE_PARENT = 0;
    int USER_TAB_POOL_TYPE_TEACHER = 1;

    int APP_TAB_TYPE_PARENT = 0;  // APP端 家长端
    int APP_TAB_TYPE_TEACHER = 1; // APP端 教师端(老师)
    int APP_TAB_TYPE_PRINCIPAL = 2; // APP端 教师端(园长)
    int APP_TAB_TYPE_TP_PUBLIC = 3; // APP端 教师端(老师与园长共有)

    int USER_TYPE_PARENT = 0;  // 用户类型 家长
    int USER_TYPE_TEACHER = 1;  // 用户类型 教师
    int USER_TYPE_PRINCIPAL = 2;  // 用户类型 园长
    int USER_TYPE_PARENT_TEACHER = 50; // 用户类型 家长+教师
    int USER_TYPE_TEACHER_PRINCIPAL = 51; // 用户类型 教师+园长
    int USER_TYPE_PARENT_PRINCIPAL = 52; // 用户类型 家长+园长
    int USER_TYPE_PARENT_TEACHER_PRINCIPAL = 53; // 用户类型 家长+教师+园长

    int APP_TAB_HAS_SCHOOL_NO = 0;  //app_tab 是否与学校有关 否
    int APP_TAB_HAS_SCHOOL_YES = 1; //app_tab 是否与学校有关 是

    int STATUS_NO = 0; //状态不可用
    int STATUS_YES = 1; //状态可用 同意

    /**
     * 操作日志，添加对象
     */
    int OPERATE_LOG_ADD = 0;
    /**
     * 操作日志，删除对象
     */
    int OPERATE_LOG_DELETE = 1;
    /**
     * 操作日志，更新对象
     */
    int OPERATE_LOG_UPDATE = 2;

    /**
     * 不加精的资讯
     */
    int FEED_NOT_DIGEST = 0;
    /**
     * 加精的资讯
     */
    int FEED_DIGEST = 1;
    /**
     * 资讯未被收藏
     */
    int FEED_NOT_COLLECTION = 0;
    /**
     * 资讯被收藏
     */
    int FEED_COLLECTION = 1;

    /**
     * 学校食谱按文字方式
     */
    int RECIPE_TYPE_CHARACTER = 0;
    /**
     * 学校食谱按图片方式
     */
    int RECIPE_TYPE_PICTURE = 1;

    /**
     * 默认getList方法需要查询的数据条数
     */
    int DEFAULT_LIST_SIZE = 20;
    /**
     * 默认加载评论的数据条数
     */
    int DEFAULT_DISCUSS_SIZE = 5;
    /**
     * 默认孩子家长关联关系的取值
     */
    int DEFAULT_PARENT_CHILD_SIZE = 5;

    /**
     * 已经提交班级作业
     */
    int ASSIGNMENT_SUBMIT = 1;
    /**
     * 未提交班级作业
     */
    int ASSIGNMENT_NOT_SUBMIT = 0;

    /**
     * 消息/通知 接收者范围 所有人
     */
    long MESSAGE_FOR_ALL = 0L;

    int MESSAGE_READ_YES = 1;
    int MESSAGE_READ_NO = 0;

    int SCHOOL_USER_ROLE_ADMIN = 0;//园方管理员

    /**
     * 孩子家长关系-爸爸
     */
    int RELATION_FATHER = 0;
    /**
     * 孩子家长关系-妈妈
     */
    int RELATION_MOTHER = 1;
    /**
     * 孩子家长关系-爷爷
     */
    int RELATION_GRANDPA = 2;
    /**
     * 孩子家长关系-奶奶
     */
    int RELATION_GRANDMA = 3;
    /**
     * 孩子家长关系-外公
     */
    int RELATION_GRANDFATHER = 4;
    /**
     * 孩子家长关系-外婆
     */
    int RELATION_GRANDMOTHER = 5;
    /**
     * 孩子家长关系-其他
     */
    int RELATION_OTHER = 6;

    /**
     * 家长端 育儿banner
     */
    int EDUCATE_CHILD_BANNER = 1;
    /**
     * 家长端 成长banner
     */
    int GROW_UP_BANNER = 2;

    /**
     * 教师端 学堂banner
     */
    int TEACHER_XUETANG = 3;

    /**
     * 考勤缺席
     */
    int ATTENDANCE_ABSENT = 0;
    /**
     * 考勤有来
     */
    int ATTENDANCE_EXIST = 1;
    /**
     * 考勤请假
     */
    int ATTENDANCE_LEAVE = 2;

    /**
     * 物理考勤机考勤
     */
    int MACHINE_TYPE_PHYSICS = 0;
    /**
     * 电子签到考勤
     */
    int MACHINE_TYPE_ELECTRON = 1;
    /**
     * 校车考勤机考勤
     */
    int MACHINE_TYPE_BUS = 3;
    /**
     * 考勤机和学校解绑
     */
    int MACHINE_SCHOOL_UNBIND = 0;
    /**
     * 考勤机和学校绑定
     */
    int MACHINE_SCHOOL_BIND = 1;
    /**
     * 考勤入园
     */
    int ATTENDANCE_IN = 0;
    /**
     * 考勤出园
     */
    int ATTENDANCE_OUT = 1;

    /**
     * 消息未点赞
     */
    int MESSAGE_NOT_PRAISE = 0;
    /**
     * 消息已经点赞
     */
    int MESSAGE_PRAISE = 1;

    /**
     * 作业不公开
     */
    int ASSIGNMENT_COMPLETE_PRIVATE = 0;
    /**
     * 作业公开
     */
    int ASSIGNMENT_COMPLETE_PUBLIC = 1;
    /**
     * 考勤卡未被使用
     */
    int CARD_NOT_USE = 0;
    /**
     * 考勤卡被使用
     */
    int CARD_USED = 1;

    /**
     * 申请入学 - 拒绝
     */
    int APPLY_STATUS_NO = -1;
    /**
     * 申请入学 - 申请中
     */
    int APPLY_STATUS_ING = 0;
    /**
     * 申请入学 - 同意
     */
    int APPLY_STATUS_YES = 1;
    /**
     * 请假审批中
     */
    int LEAVE_STATUS_WAITING = 0;
    /**
     * 同意请假
     */
    int LEAVE_STATUS_APPLY = 1;
    /**
     * 不同意请假
     */
    int LEAVE_STATUS_NOT_APPLY = 2;
    /**
     * 请假信息未处理
     */
    int LEAVE_PROCESS_NO = 0;
    /**
     * 请假信息已经处理
     */
    int LEAVE_PROCESS_YES = 1;

    int CHAT_GROUP_SCOPE_CHILD = 0;
    int CHAT_GROUP_SCOPE_STUDENT = 1;
    int CHAT_GROUP_SCOPE_SCHOOL = 2;
    /**
     * 邀请家长 - 拒绝
     */
    int INVITE_STATUS_NO = -1;
    /**
     * 邀请家长 - 邀请中
     */
    int INVITE_STATUS_ING = 0;
    /**
     * 邀请家长 - 同意
     */
    int INVITE_STATUS_YES = 1;

    /**
     * banner投放中
     */
    int BANNER_ING = 1;
    /**
     * banner未投放
     */
    int BANNER_NOT_STATUS = 2;
    /**
     * banner已下架
     */
    int BANNER_END = 3;

    /**
     * CRM 角色 超级管理员
     */
    int CRM_ROLE_ADMIN = 1;
    /**
     * CRM 角色产品部
     */
    int CRM_ROLE_PRODUCT = 2;
    /**
     * CRM 角色 技术部
     */
    int CRM_ROLE_TECHNOLOGY = 3;
    /**
     * CRM 角色 运营部
     */
    int CRM_ROLE_OPERATION = 4;
    /**
     * CRM 角色 财务部
     */
    int CRM_ROLE_FINANCE = 5;
    /**
     * CRM 角色 市场部
     */
    int CRM_ROLE_MARKET = 6;
    /**
     * CRM 角色 培训部
     */
    int CRM_ROLE_TRAINING = 7;
    /**
     * CRM 角色 自定义
     */
    int CRM_ROLE_CUSTOM = 8;
    /**
     * CRM 角色 独家代理
     */
    int CRM_ROLE_EXCLUSIVE = 9;
    /**
     * CRM 角色 金牌代理
     */
    int CRM_ROLE_GOLD = 10;
    /**
     * 财务审批-充值
     */
    int FINANCEAPPROVE_TYPE_RECHARGE = 1;
    /**
     * 财务审批-修改保证金
     */
    int FINANCEAPPROVE_TYPE_UPDATE_DEPOSIT = 2;
    /**
     * 财务审批-修改单价
     */
    int FINANCEAPPROVE_TYPE_UPDATE_PRICE = 3;

    /**
     * 财务审批-待审批
     */
    int FINANCEAPPROVE_STATUS_WAITING = 1;
    /**
     * 财务审批-已通过
     */
    int FINANCEAPPROVE_STATUS_OK = 2;
    /**
     * 财务审批-已关闭
     */
    int FINANCEAPPROVE_STATUS_CLOSE = 3;

    /**
     * 代理商充值-线下
     */
    int RECHARGE_TYPE_OFFLINE = 1;
    /**
     * 代理商充值-钱包
     */
    int RECHARGE_TYPE_WALLET = 2;
    /**
     * 代理商充值-支付宝
     */
    int RECHARGE_TYPE_ALIPAY = 3;
    /**
     * 代理商/学校充值-代理商给充值的
     */
    int RECHARGE_TYPE_AGENT = 4;
    /**
     * 代理商/学校充值-公司给充值的
     */
    int RECHARGE_TYPE_DONGSYS = 5;

    /**
     * 代理商/学校充值-幼儿园给学生充值的
     */
    int RECHARGE_TYPE_SCHOOL = 6;

    /**
     * 代理商/学校充值-反充值
     */
    int RECHARGE_TYPE_RETUEN = 7;
    /**
     * 1充值成功
     */
    int RECHARGE_SATUS_OK = 1;
    /**
     * 2未付款
     */
    int RECHARGE_SATUS_NOPAY = 2;
    /**
     * 3交易关闭
     */
    int RECHARGE_SATUS_CLOSE = 3;
    /**
     * 4审批中
     */
    int RECHARGE_SATUS_APPROVEING = 4;
    /**
     * 5未付款，交易关闭
     */
    int RECHARGE_SATUS_NOPAY_CLOSE = 5;

    /**
     * 消费记录-给代理商充值
     */
    int CONSUME_TYPE_AGENT = 1;
    /**
     * 消费记录-给学校值
     */
    int CONSUME_TYPE_SCHOOL = 2;
    /**
     * 消费记录-给用户充值
     */
    int CONSUME_TYPE_USER = 3;
    /**
     * 消费记录-独家反充给微校
     */
    int CONSUME_TYPE_RETURN_MICROSCHOOL = 4;
    /**
     * 消费记录-金牌反充给独家
     */
    int CONSUME_TYPE_RETURN_EXCLUSIVE = 5;
    /**
     * 消费记录-幼儿园反充给代理商
     */
    int CONSUME_TYPE_RETURN_AGENT = 6;
    /**
     * 视频券
     */
    int PRODUCT_VIEW = 1;
    /**
     * 考勤券
     */
    int PRODUCT_ATTENDANCE = 2;

    /**
     * 保证金为0时，不禁止
     */
    int DEPOSIT_NOT_FORBIDDEN = 0;
    /**
     * 保证金为0时，禁止
     */
    int DEPOSIT_FORBIDDEN = 1;

    /**
     * 摄像头在线
     */
    int CAMERA_ACTIVE = 1;
    /**
     * 摄像头不在线
     */
    int CAMERA_NOT_ACTIVE = 0;

    /**
     * CRM  代理商保证金/视频券单价/考勤券单价 未设置
     */
    int NOT_SET = -1;

    /**
     * 学生状态-在读
     */
    int STUDENT_STATUS_IN = 0;
    /**
     * 学生状态-离园
     */
    int STUDENT_STATUS_OUT = 1;
    /**
     * 学生状态-完全删除（家长APP端）
     */
    int STUDENT_STATUS_DEL = 2;

    /**
     * 教师在职状态-在职
     */
    int TEACHER_STATUS_IN = 0;
    /**
     * 教师在职状态-离职
     */
    int TEACHER_STATUS_OUT = 1;
    /**
     * 教师状态-完全删除（教师APP端）
     */
    int TEACHER_STATUS_DEL = 2;

    /**
     * 班级状态 可用
     */
    int CLASS_STATUS_YES = 0;

    /**
     * 班级状态 已删除
     */
    int CLASS_STATUS_NO = 1;

    /**
     * 还有剩余视频
     */
    int HAS_VIDEO_BALANCE = 1;
    /**
     * 还有剩余考勤
     */
    int HAS_ATTENDANCE_BALANCE = 1;

    /**
     * 在园方后台删除学生离园记录
     */
    int STUDENT_LEAVE_SCHOOL_DEL = 1;
    /**
     * 没有上车，也没有下车的状态
     */
    int BUS_NORMAL = 0;
    /**
     * 在校车上
     */
    int BUS_ON = 1;
    /**
     * 刚下校车不久
     */
    int BUS_OFF = 2;
    /**
     * 超过多长时间（分钟）没接收到数据算车辆入库。当前为15分钟
     */
    int BUS_OFF_TIME_MILLIS = 15 * 60 * 1000;
    /**
     * 学生下车状态持续时长。当前为1小时
     */
    int BUS_OFF_STUDENT_DURATION = 3600 * 1000;
    /**
     * 教师下车状态持续时长。当前为15分钟
     */
    int BUS_OFF_TEACHER_DURATION = 15 * 60 * 1000;
    /**
     * 校车轨迹能取的最大点数目
     */
    int BUS_MAX_LOCATION = 2000;

    /**
     * 幼儿园付费模式-以学生为单位
     */
    int PAY_TYPE_STUDENT = 0;
    /**
     * 幼儿园付费模式-以家长为单位
     */
    int PAY_TYPE_PARENT = 1;

    /**
     * 视频/考勤 未禁用
     */
    int FORBIDDEN_NO = 0;
    /**
     * 视频/考勤 禁用
     */
    int FORBIDDEN_YES = 1;

    /**
     * 孩子群
     */
    int ADDRESS_BOOK_TYPE_CHILD_GROUP = 0;
    /**
     * 学生群
     */
    int ADDRESS_BOOK_TYPE_STUDENT_GROUP = 1;
    /**
     * 教师群
     */
    int ADDRESS_BOOK_TYPE_SCHOOL_GROUP = 2;
    /**
     * 教师联系方式
     */
    int ADDRESS_BOOK_TYPE_TEACHER = 3;
    /**
     * 园长联系方式
     */
    int ADDRESS_BOOK_TYPE_PRINCIPAL = 4;

    /**
     * 收费模式切换 是否有用户服务重置 无
     */
    int HAS_PRODUCT_RESET_NO = 0;
    /**
     * 收费模式切换 是否有用户服务重置 有
     */
    int HAS_PRODUCT_RESET_YES = 1;

    /**
     * 无数据修改
     */
    int VERSION_NO = 0;
    /**
     * 部分数据修改
     */
    int VERSION_PART = 1;
    /**
     * 更新所有版本数据，将本地数据覆盖
     */
    int VERSION_ALL = 2;

    /**
     * 周计划上午
     */
    int COURSE_AM = 1;
    /**
     * 周计划下午
     */
    int COURSE_PM = 2;
    /**
     * 学校小红点
     */
    int PROMPT_TYPE_SCHOOL = 1;
    /**
     * 食谱小红点
     */
    int PROMPT_TYPE_RECIPES = 2;
    /**
     * 周计划小红点
     */
    int PROMPT_TYPE_COURSE = 3;
    /**
     * 消息非免打扰
     */
    int CHAT_NOT_IGNORE = 0;
    /**
     * 消息免打扰
     */
    int CHAT_IGNORE = 1;

    /**
     * 家长支付单位模式，学生视频考勤状态
     * 0 未开通 1 已开通 2 禁用中 3 试用中 4 已过期 5 试用已过期
     */
    int VOPARADNSTU_STATUS_UNUSE = 0;
    int VOPARADNSTU_STATUS_USE = 1;
    int VOPARADNSTU_STATUS_FORDIDDENING = 2;
    int VOPARADNSTU_STATUS_TRYUSEING = 3;
    int VOPARADNSTU_STATUS_TIMEOIUT = 4;
    int VOPARADNSTU_STATUS_TRYUSE_TIMEOUT = 5;

    /**
     * 在线支付，等在支付状态
     */
    int PAYMENT_WAITING = 1;
    /**
     * 支付超时（默认设置半小时）
     */
    int PAYMENT_TIMEOUT = 2;
    /**
     * 在线支付，支付完成状态
     */
    int PAYMENT_PAID = 3;
    /**
     * 支付宝支付
     */
    int PAYMENT_TYPE_ALI = 1;
    /**
     * 微信支付
     */
    int PAYMENT_TYPE_WX = 2;
    /**
     * 资讯的真实来源，自添加
     */
    int FEED_REAL_SRC_DDWX = 0;
    /**
     * 资讯的真实来源，决胜网
     */
    int FEED_REAL_SRC_JUESHENG = 1;


    /**
     * 食谱模式，0 文本模式  1图片模式
     */
    int SCHOOL_FUNCTION_SWITCH_RECIPE_TYPE = 0;
    /**
     * 开通试用(0:未开通 1:已开通)
     */
    int SCHOOL_FUNCTION_SWITCH_PROBATION_TYPE = 1;
    /**
     * 付费模式(0 以学生为单位  1以家长为单位)
     */
    int SCHOOL_FUNCTION_SWITCH_PAY_TYPE = 2;
    /**
     * 周计划模式 0 文本模式 1
     */
    int SCHOOL_FUNCTION_SWITCH_COURSE_TYPE = 3;
    /**
     * 在线支付 0 不开通 1 开通
     */
    int SCHOOL_FUNCTION_SWITCH_ONLINE_TYPE = 4;
    /**
     * 视频服务类型 0 东电云视 1 绿安
     */
    int SCHOOL_FUNCTION_SWITCH_VIDEO_TYPE = 5;

    /**
     * 学校是否被禁用 0 禁用 1 不禁用
     */
    int SCHOOL_FUNCTION_SWITCH_STATUS_TYPE = 6;

}
