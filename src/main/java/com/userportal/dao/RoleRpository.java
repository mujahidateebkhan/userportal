package com.userportal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.userportal.entites.Role;

@Repository
public interface RoleRpository extends JpaRepository<Role,Long>{

}
