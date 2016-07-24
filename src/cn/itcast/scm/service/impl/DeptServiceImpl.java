package cn.itcast.scm.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.scm.dao.DeptDao;
import cn.itcast.scm.entity.Dept;
import cn.itcast.scm.service.DeptService;

@Service("deptService")
public class DeptServiceImpl implements DeptService {
	
	@Autowired
	private DeptDao deptDao;

	@Override
	public Dept selectDept(Integer deptId) {
		// TODO Auto-generated method stub

		return deptDao.selectDept(deptId);
	}

	@Override
	public int insertDept(Dept dept) throws Exception {
		int i =0 ;
		i = deptDao.insertDept(dept);
		//Integer.parseInt("aa");
		//Class.forName("adb.Dee");
		return i;
	}

}
