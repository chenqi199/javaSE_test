package cn.itcast.scm.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.scm.entity.Dept;
import cn.itcast.scm.service.DeptService;

@Controller
@RequestMapping("/dept")
public class DeptAction {
	@Resource
	private DeptService deptService;
	
	@RequestMapping(value="/insert")
	public String insert(Dept dept){
		System.out.println("---action.dept:"+dept);
		try {
			deptService.insertDept(dept);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "forward:/jsp/main.jsp";
	}

}
