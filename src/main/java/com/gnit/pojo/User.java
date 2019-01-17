package com.gnit.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 人员信息表
 * @author JDRY-SJM
 *
 */
@Entity
@Table(name = "t_gnit_user")
public class User {
	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	@Column
	private String id;// 主键

	@Column
	private String userId;// 用户id
	@Column
	private String userName;// 用户名
	@Column
	private String userGender;// 性别
	@Column
	private String userMobile;// 手机
	@Column
	private String userPwd;// 密码
	@Column
	private String userRole;// 角色
	@Column
	private Boolean isManager;//是否是管理员
	@Column
	private Boolean isSuperManager;// 是否是超级管理员
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserGender() {
		return userGender;
	}
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public Boolean getIsManager() {
		return isManager;
	}
	public void setIsManager(Boolean isManager) {
		this.isManager = isManager;
	}
	public Boolean getIsSuperManager() {
		return isSuperManager;
	}
	public void setIsSuperManager(Boolean isSuperManager) {
		this.isSuperManager = isSuperManager;
	}
	
}
