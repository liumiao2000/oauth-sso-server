package com.oauthSSOServer.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.JoinColumn;

@Entity
public class User implements UserDetails{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5099813914555354109L;
	@Id
    @GeneratedValue
	private long id;
	
	@Column(nullable = false, unique = true)
    private String name;
	
	@Column(nullable = false, unique = false)
    private String password;
	
	@Column(nullable = true, unique = false)
    private String email;
	
	@Column(nullable = true, unique = false)
    private boolean enable;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_role",
		joinColumns = { @JoinColumn(name = "user_id")},
		inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;
    
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

	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	
	
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		if(this.roles != null) {
			for(Role role:this.roles) {
				authorities.add(new SimpleGrantedAuthority(role.getCode()));
			}
		}
		return authorities;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.name;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.enable;
	}
	
    
    
}
