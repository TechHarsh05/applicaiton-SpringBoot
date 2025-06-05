package com.emp.application.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.emp.application.entity.Product;
import com.emp.application.entity.User;
import com.emp.application.repository.AdminRepo;
import com.emp.application.repository.ProductRepository;
import com.emp.application.repository.UserRepository;

@Repository
public class Dao {
	
	@Autowired private UserRepository urepo;
	@Autowired private ProductRepository prepo;
	@Autowired private AdminRepo rrepo;
	@Autowired private PasswordEncoder passwordEncoder;
	
	public Product insertProductDao(Product product) {
		
		return prepo.save(product);
	}

	public List<Product> getProductsDao() {
		
		return prepo.findAll();
	}

	public User insertUserDao(User user, byte[] imageBytes) {
//		public User insertUserDao(User user) {
			
		User saveUser = new User(user.getName(), imageBytes, user.getEmail(), user.getPassword(), user.getPhone());
		
		saveUser.setPassword(passwordEncoder.encode(user.getPassword()));
		return urepo.save(saveUser);
	}
	
	public List<User> getAllUsersDao(){
		return urepo.findAll();
	}

	public Optional<User> getUserById(long id) {
		return urepo.findById(id);
	}

	public void deleteUserDao(long id) {
		urepo.deleteById(id);
	}
	
	public Optional<User> findByEmailDao(String email){
		return urepo.findByEmail(email);
	}
}
