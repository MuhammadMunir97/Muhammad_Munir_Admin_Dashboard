package com.muhammad.admin_dashboard.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.muhammad.admin_dashboard.entities.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
	
	 List<Role> findAll();    
	 List<Role> findByName(String name);
}
