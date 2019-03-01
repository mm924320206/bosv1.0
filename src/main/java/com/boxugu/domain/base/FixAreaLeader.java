package com.boxugu.domain.base;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.struts2.json.annotations.JSON;
import org.hibernate.annotations.Cascade;

/**
 * @description:定区负责人；片警
 */
@Entity
@Table(name = "T_FIXAREALEADER")
public class FixAreaLeader {
	@Id
	@GeneratedValue
	@Column(name = "C_ID")
	private Integer id; // 主键
	@Column(name = "C_FIXED_AREA_LEADER", unique = true)
	private String fixedAreaLeader;// 定区负责人
	@Column(name = "C_TELEPHONE")
	private String telephone;// 联系电话
	@Column(name = "C_COMPANY")
	private String company; // 所属单位
	@OneToMany(mappedBy = "fixedAreaLeader") //一个片警多个定区
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,org.hibernate.annotations.CascadeType.PERSIST})
	private Set<FixedArea> fixedAreas = new HashSet<FixedArea>(0);
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFixedAreaLeader() {
		return fixedAreaLeader;
	}
	public void setFixedAreaLeader(String fixedAreaLeader) {
		this.fixedAreaLeader = fixedAreaLeader;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	@JSON(serialize=false)
	public Set<FixedArea> getFixedAreas() {
		return fixedAreas;
	}
	public void setFixedAreas(Set<FixedArea> fixedAreas) {
		this.fixedAreas = fixedAreas;
	}
	
}
