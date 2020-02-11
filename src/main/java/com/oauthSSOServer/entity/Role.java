package com.oauthSSOServer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Role {
	@Id
    @GeneratedValue
	private long id;
	
	@Column(nullable = false, unique = true)
    private String name;
	
	@Column(nullable = false, unique = true)
    private String code;
	
	@Column(nullable = true, unique = false)
    private boolean enable;
    
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
    
    
}
