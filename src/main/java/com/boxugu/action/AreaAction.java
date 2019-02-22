package com.boxugu.action;

import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import com.boxugu.domain.base.Area;
import com.boxugu.service.AreaService;
import com.boxugu.utils.PinYin4jUtils;

@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("json-default")
public class AreaAction extends BaseAction<Area>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private AreaService areaService;
	
	//文件上传部分
	private File file;

	public void setFile(File file) {
		this.file = file;
	}
	
	@Action(value = "area_batchImport")
	public String batchImport() throws InvalidFormatException, IOException {
		List<Area> areas=new ArrayList<Area>();
		XSSFWorkbook xWorkbook=new XSSFWorkbook(file);
		XSSFSheet xSheet=xWorkbook.getSheetAt(xWorkbook.getActiveSheetIndex());
		for (Row row : xSheet) {
			if (row.getRowNum()==0) {
				continue;
			}
			if (row.getCell(0)==null||StringUtils.isBlank(row.getCell(0).getStringCellValue())) {
				continue;
			}
			Area area=new Area();
			area.setId(row.getCell(0).getStringCellValue());
			area.setProvince(row.getCell(1).getStringCellValue());
			area.setCity(row.getCell(2).getStringCellValue());
			area.setDistrict(row.getCell(3).getStringCellValue());
			area.setPostcode(row.getCell(4).getStringCellValue());
			//根据工具类，得到简码和城市编码
			String provinceString=area.getProvince().substring(0, area.getProvince().length()-1);
			String cityString=area.getCity().substring(0, area.getCity().length()-1);
			String districtString=area.getDistrict().substring(0, area.getDistrict().length()-1);
			//简码
			String[] headArray=PinYin4jUtils.getHeadByString(provinceString+cityString+districtString);
			StringBuffer stringBuffer=new StringBuffer();
			for (String headStr : headArray) {
				stringBuffer.append(headStr);
			}
			area.setShortcode(stringBuffer.toString());
			//城市编码
			area.setCitycode(PinYin4jUtils.hanziToPinyin(cityString, ""));
			areas.add(area);
		}
		areaService.saveFromBatchImport(areas);
		return null;		
	}
	
	
	//分页查询+条件查询
	@Action(value = "area_pageQuery", results = {
			@Result(name = "success",  type = "json")})
	public String pageQuery() {
		
		Pageable pageable=new PageRequest(page-1, rows);
		
		//如果是带参查询，参数拼凑的地方
		Specification<Area> specification=new Specification<Area>() {

			@Override
			public Predicate toPredicate(Root<Area> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				List<Predicate> list=new ArrayList<Predicate>();
				//第一个条件：省份查询
				if (StringUtils.isNotBlank(model.getProvince())) {
					Predicate p1=cb.like(root.get("province").as(String.class), "%"+model.getProvince()+"%");
					list.add(p1);
				}
				//第二个条件：城市查询
				if (StringUtils.isNotBlank(model.getCity())) {
					Predicate p2=cb.like(root.get("city").as(String.class), "%"+model.getCity()+"%");
					list.add(p2);
				}
				//第三个条件：区域查询
				if (StringUtils.isNotBlank(model.getDistrict())) {
					Predicate p3=cb.like(root.get("district").as(String.class), "%"+model.getDistrict()+"%");
					list.add(p3);
				}
				
				return cb.and(list.toArray(new Predicate[0]));
			}
		};
		Page<Area> pageData=areaService.findPageData(specification,pageable);
		pushPageDataToValueStack(pageData);
		return "success";
	}

}
