package com.muhammad.admin_dashboard.repositories;

import org.springframework.data.repository.CrudRepository;

import com.muhammad.admin_dashboard.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByEmail(String email);
}
