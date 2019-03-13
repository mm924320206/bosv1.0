package com.boxugu.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
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
import com.boxugu.domain.base.TakeTime;
import com.boxugu.service.TakeTimeService;
import com.opensymphony.xwork2.ActionContext;

/*@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("json-default")*/
public class TakeTimeAction extends BaseAction<TakeTime>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6047164588954553627L;

	@Autowired
	private TakeTimeService takeTimeService;
	
	
	@Action(value = "time_save", results = {
			@Result(name = "success", location = "/pages/base/take_time.html", type = "redirect")})
	public String save() 
	{
		takeTimeService.save(model);
		return "success";
		}
	
	
	
	//分页显示
	@Action(value = "take_time", results = {
			@Result(name = "success",  type = "json") })
	public String pageQuery() {
		Pageable pageable=new PageRequest(page-1, rows);
		Page<TakeTime> pageData=takeTimeService.findPageData(pageable);
		pushPageDataToValueStack(pageData);
		return "success";
	}
	
	
	//为定区关联查询的所有时间
	@Action(value = "taketime_findAll", results = {
			@Result(name = "success",  type = "json")})
	public String findAll() {
		List<TakeTime> takeTimes=takeTimeService.findAll();
		ActionContext.getContext().getValueStack().push(takeTimes);
		return "success";
	}
}
