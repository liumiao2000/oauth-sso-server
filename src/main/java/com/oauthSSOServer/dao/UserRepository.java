package com.oauthSSOServer.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oauthSSOServer.entity.User;


public interface UserRepository extends JpaRepository<User,Long> {
	public User findByName(String userName);
}
