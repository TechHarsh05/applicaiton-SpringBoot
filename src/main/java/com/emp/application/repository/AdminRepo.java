package com.emp.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emp.application.entity.Admin;

public interface AdminRepo extends JpaRepository<Admin, String>{

}
