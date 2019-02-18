package com.boxugu.action;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.boxugu.common.BaseAction;
import com.boxugu.domain.base.Standard;
import com.boxugu.service.StandardService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("json-default")
public class StandCRUDAction extends BaseAction<Standard>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	

	@Autowired
	private StandardService standardService;
	
	
	//standard的新增和修改
	@Action(value = "stand_save", results = {
			@Result(name = "success", location = "/pages/base/standard.html", type = "redirect"), })
	public String save() {
		standardService.save(model);
		return "success";
		
	}
	
	//standard的分页
	@Action(value = "stand_pageQuery", results = {
			@Result(name = "success",  type = "json"), })
	public String pageQuery() {
		Pageable pageable=new PageRequest(page-1, rows);
		Page<Standard> pageData=standardService.findPageData(pageable);
		pushPageDataToValueStack(pageData);
		return "success";
		
	}
	
	@Action(value = "standard_findAll", results = {
			@Result(name = "success",  type = "json"), })
	public String standardFindAll() {
		List<Standard> standards=standardService.findAll();
		ActionContext.getContext().getValueStack().push(standards);
		return "success";
		
	}
}
