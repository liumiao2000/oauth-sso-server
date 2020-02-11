package com.oauthSSOServer.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oauthSSOServer.entity.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {

}
