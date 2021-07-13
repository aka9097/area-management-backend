package com.mindtree.areamanagement.modules.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.areamanagement.modules.admin.dto.AdminDto;
import com.mindtree.areamanagement.modules.admin.service.AdminService;

@RestController
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
//	@RequestMapping("/login")
//    public String login() {
//		return "login successfully";	
//	}
//	
	@PostMapping("/addAdmin")
	public ResponseEntity<AdminDto> insertAdminDetails(@RequestBody AdminDto adminDto){
		adminDto=adminService.addAdminDetails(adminDto);
		return ResponseEntity.ok(adminDto);
	}
	
	@RequestMapping("/getAdminDetails")
	public ResponseEntity<?> getAdminDetails() {
		List<AdminDto> AdminDtoList = adminService.retreiveAllAdmin();
		return new ResponseEntity<List<AdminDto>>(AdminDtoList, HttpStatus.CREATED);
	}
	
	@PutMapping("/updateAdminProfile/{email}")
	public ResponseEntity<AdminDto> updateAdminDetails(@PathVariable String email, @RequestBody AdminDto adminDto) {
		AdminDto adminDtoDetails =adminService.updateAdminProfile(email, adminDto);
		return new ResponseEntity<AdminDto>(adminDtoDetails, HttpStatus.CREATED);
	}
	
	@PutMapping("/updateAdminPassword/{email}")
	public ResponseEntity<?> updatePasswordOfAdmin(@PathVariable String email, @RequestBody AdminDto adminDto) {
		AdminDto adminDtoDetails =adminService.updateAdminPassword(email, adminDto);
		return new ResponseEntity<AdminDto>(adminDtoDetails, HttpStatus.CREATED);
	}
		
	@GetMapping("/getAdmin/{email}")
	public ResponseEntity<?> getAdminByEmail(@PathVariable("email") String email) {
		AdminDto foundadmin = adminService.getAdminDetailsByEmail(email);
		return new ResponseEntity<AdminDto>(foundadmin, HttpStatus.CREATED);
	}
	
}