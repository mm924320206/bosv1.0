package com.boxugu.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.boxugu.common.BaseAction;
import com.boxugu.domain.base.FixAreaLeader;
import com.boxugu.domain.base.FixedArea;
import com.boxugu.service.LeaderService;
import com.boxugu.service.FixAreaService;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("json-default")
public class FixAreaLeaderAction extends BaseAction<FixAreaLeader>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private LeaderService LeaderService;
	
	
	@Action(value = "findfixarealeader", results = {
			@Result(name = "success",  type = "json"), })
	public String FindAll() {
		List<FixAreaLeader> fixAreaLeaders=LeaderService.findAll();
		ActionContext.getContext().getValueStack().push(fixAreaLeaders);
		return "success";
	}
	/*前台已解决利用json数据，不需要再回传服务器
	 * @Action(value = "findtel", results = {
	 * 
	 * @Result(name = "success", type = "json"), }) public String FindTel() {
	 * List<FixAreaLeader>
	 * fixAreaLeaders=LeaderService.findByName(model.getFixedAreaLeader());
	 * ActionContext.getContext().getValueStack().push(fixAreaLeaders); return
	 * "success"; }
	 */
	/*
	 * @Action(value = "findfixarealeader", results = {
	 * 
	 * @Result(name = "success", type = "json"), }) public String FindCompany() {
	 * List<FixAreaLeader> fixAreaLeaders=LeaderService.findAll();
	 * ActionContext.getContext().getValueStack().push(fixAreaLeaders); return
	 * "success"; }
	 */
	
	/*
	 * @Action(value = "fixArea_save", results = {
	 * 
	 * @Result(name = "success", type = "json"), }) public String save() {
	 * System.out.println(model); LeaderService.save(model);
	 * System.out.println("111"); return "success"; }
	 */
}
