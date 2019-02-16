package com.boxugu.action;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.boxugu.domain.base.Standard;
import com.boxugu.service.StandardService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/*@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")*/
public class standCRUDAction extends ActionSupport implements ModelDriven<Standard>{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	private Standard standard=new Standard();
	@Override
	public Standard getModel() {
		// TODO Auto-generated method stub
		return standard;
		
	}

	@Autowired
	private StandardService standardService;
	
	
	@Action(value = "stand_save", results = {
			@Result(name = "success", location = "/pages/base/standard.html", type = "redirect"), })
	public String save() {
		System.out.println("chenggong");
		standardService.save(standard);
		return "success";
		
	}
}
