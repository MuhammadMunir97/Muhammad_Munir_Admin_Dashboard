package com.muhammad.admin_dashboard.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.muhammad.admin_dashboard.entities.User;
import com.muhammad.admin_dashboard.repositories.RoleRepository;
import com.muhammad.admin_dashboard.repositories.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder)     {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    
    public List<User> findAllUsers(){
    	return (List<User>) userRepository.findAll();
    }
    
    // 1
    public void saveWithUserRole(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roleRepository.findByName("ROLE_USER"));
        userRepository.save(user);
    }
     
     // 2 
    public void saveUserWithAdminRolecopy(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roleRepository.findByName("ROLE_ADMIN"));
        userRepository.save(user);
    }    
    
    public void deleteUserById(Long id) {
    	userRepository.deleteById(id);
    }
    
    public User findByUsername(String email) {
    	return userRepository.findByEmail(email);
    }
    
    public User findByUserId(Long id) {
    	Optional<User> user = userRepository.findById(id);
    	if(user.isPresent()) {
    		return user.get();
    	}else {
    		return null;
    	}
    }
}
    
