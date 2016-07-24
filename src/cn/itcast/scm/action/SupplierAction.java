package cn.itcast.scm.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.scm.entity.Supplier;
import cn.itcast.scm.service.SupplierService;

@Controller
@RequestMapping("/supplier")
public class SupplierAction {
	@Resource
	private SupplierService supplierService;
	
	@RequestMapping(value="/insert")
	public String insert(Supplier supplier){
		System.out.println("---action.supplier:"+supplier);
		try {
			supplierService.insertSupplier(supplier);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "forward:/jsp/main.jsp";
	}

}
