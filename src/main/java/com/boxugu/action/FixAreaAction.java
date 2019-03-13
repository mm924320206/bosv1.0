package com.boxugu.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.print.attribute.standard.Media;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import com.boxugu.common.BaseAction;
import com.boxugu.domain.base.FixedArea;
import com.boxugu.service.FixAreaService;
import com.crm.domain.Customer;
import com.opensymphony.xwork2.ActionContext;

/*@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("json-default")*/
public class FixAreaAction extends BaseAction<FixedArea>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private FixAreaService fixAreaService;
	
	
	@Action(value = "fixArea_save", results = {
			@Result(name = "success",location = "/pages/base/fixed_area.html", type = "redirect") })
	public String save() {
		System.out.println(model.getFixedAreaLeader().getId());
		fixAreaService.save(model);
		
		return "success";
	}
	
	//分页查询等
	@Action(value = "fixed_area", results = {
			@Result(name = "success",  type = "json")})
	public String pageQuery() {
		
		Pageable pageable=new PageRequest(page-1, rows);
		
		//如果是带参查询，参数拼凑的地方
		Specification<FixedArea> specification=new Specification<FixedArea>() {

			@Override
			public Predicate toPredicate(Root<FixedArea> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				List<Predicate> list=new ArrayList<Predicate>();
				//第一个条件：定区编码查询
				if (StringUtils.isNotBlank(model.getId())) {
					Predicate p1=cb.equal(root.get("id").as(String.class), model.getId());
					list.add(p1);
					
				}
				
				//第二条件，跨表查询所属单位
				Join<Object, Object> fixarealeaderroot=root.join("fixedAreaLeader",JoinType.INNER);
				if (model.getFixedAreaLeader()!=null&&StringUtils.isNotBlank(model.getFixedAreaLeader().getCompany())) {
					Predicate p2=cb.like(fixarealeaderroot.get("company").as(String.class),"%"+model.getFixedAreaLeader().getCompany()+"%");
					list.add(p2);
				}
				/*
				 * //第三个条件：快递员类型等值 if (StringUtils.isNotBlank(model.getType())) { Predicate
				 * p3=cb.equal(root.get("type").as(String.class), model.getType());
				 * list.add(p3); } //第四条件，跨表查询收派标准 Join<Object, Object>
				 * standardRoot=root.join("standard",JoinType.INNER); if
				 * (model.getStandard()!=null&&StringUtils.isNotBlank(model.getStandard().
				 * getName())) { Predicate
				 * p4=cb.like(standardRoot.get("name").as(String.class),"%"+model.getStandard().
				 * getName()+"%"); list.add(p4); }
				 */
				return cb.and(list.toArray(new Predicate[0]));
			}
		};
		Page<FixedArea> pageData=fixAreaService.findPageData(specification,pageable);
		pushPageDataToValueStack(pageData);
		System.out.println(pageData);
		return "success";
	}
	
	

	//查询未关联客户的定区
	@Action(value = "fixArea_noAssociated", results = {
			@Result(name = "success", type = "json"), })
	public String noAssociated() {
		Collection<? extends Customer> collection=WebClient.create("http://localhost:9002/crm_management/services/customerService/noassociationcustomers")
		.accept(MediaType.APPLICATION_JSON).getCollection(Customer.class);
		ActionContext.getContext().getValueStack().push(collection);
		return "success";
	}

	
	

		//查询已关联客户的定区
		@Action(value = "fixArea_hasAssociated", results = {
				@Result(name = "success", type = "json"), })
		public String hasAssociated() {
			Collection<? extends Customer> collection=WebClient.create("http://localhost:9002/crm_management/services/customerService/associationfixareacustomers/"+model.getId())
					.accept(MediaType.APPLICATION_JSON).getCollection(Customer.class);
					ActionContext.getContext().getValueStack().push(collection);
					return "success";
		}
		
		
		//保存需要关联的客户
		private String[] customerIds;
		
		public void setCustomerIds(String[] customerIds) {
			this.customerIds = customerIds;
		}

		@Action(value = "decidedzone_assigncustomerstodecidedzone", results = {
				@Result(name = "success",location = "/pages/base/fixed_area.html", type = "redirect") })
		public String decidedzone_assigncustomerstodecidedzone() {
			String custormerIdsString=StringUtils.join(customerIds, ",");
			System.out.println(custormerIdsString+model.getId());
			WebClient.create("http://localhost:9002/crm_management/services/customerService/association?idstr="+custormerIdsString+"&fixareaid="+model.getId()).encoding("UTF8")
					.put(null);
					return "success";
		}
		
		//保存关联快递员
		
		private Integer courierId;
		private Integer takeTimeId;
		
		public void setCourierId(Integer courierId) {
			this.courierId = courierId;
		}

		public void setTakeTimeId(Integer takeTimeId) {
			this.takeTimeId = takeTimeId;
		}

		@Action(value = "fixedArea_associationCourierToFixedArea", results = {
				@Result(name = "success",location = "/pages/base/fixed_area.html", type = "redirect") })
		public String fixedArea_associationCourierToFixedArea() {
			System.out.println("!");
			System.out.println(model.getId()+"/"+courierId+"/"+takeTimeId);
			fixAreaService.associationCourierToFixedArea(model,courierId,takeTimeId);
					return "success";
		}
		
		
		//查询某定区已关联的客户
				@Action(value = "association_customer", results = {
						@Result(name = "success", type = "json"), })
				public String association_customer() {
					Collection<? extends Customer> collection=WebClient.create("http://localhost:9002/crm_management/services/customerService/associationfixareacustomers/"+model.getId())
							.accept(MediaType.APPLICATION_JSON).getCollection(Customer.class);
							ActionContext.getContext().getValueStack().push(collection);
							return "success";
				}

}
