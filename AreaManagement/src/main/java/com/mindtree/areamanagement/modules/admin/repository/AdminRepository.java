package com.mindtree.areamanagement.modules.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.areamanagement.modules.admin.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer>{

	
	public Admin findByEmail(String email);

}
