package com.boxugu.common;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("json-default")
public abstract class BaseAction<T> extends ActionSupport implements ModelDriven<T>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected T model;
	@Override
	public T getModel() {
		// TODO Auto-generated method stub
		return model;
	}
	public BaseAction()
	{
		//this是子类，得到子类的父类：baseACtion<standard>
		Type genericSuperclass=this.getClass().getGenericSuperclass();
		//得到泛型
		ParameterizedType parameterizedType=(ParameterizedType) genericSuperclass;
		//得到第一个泛型
		Class<T> modelClass=(Class<T>) parameterizedType.getActualTypeArguments()[0];
		try {
			model=modelClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("模型驱动注入错误！！");
		}
	}
	//分页的部分
	protected int page;
	protected int rows;
	public void setPage(int page) {
		this.page = page;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	protected void pushPageDataToValueStack(Page<T> pageData) {
		Map<String, Object> result=new HashMap<String, Object>();
		result.put("total", pageData.getTotalElements());
		result.put("rows", pageData.getContent());
		ActionContext.getContext().getValueStack().push(result);
	}
}
