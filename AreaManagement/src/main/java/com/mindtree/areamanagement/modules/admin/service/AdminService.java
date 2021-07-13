package com.mindtree.areamanagement.modules.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.areamanagement.modules.admin.dto.AdminDto;
import com.mindtree.areamanagement.modules.admin.entity.Admin;
import com.mindtree.areamanagement.modules.admin.repository.AdminRepository;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepository adminRepository;
	
	ModelMapper modelMapper=new ModelMapper();

	public AdminDto addAdminDetails(AdminDto adminDto) {
		Admin admin=convertDtoToEntity(adminDto);
		admin=adminRepository.save(admin);
		return adminDto;
	}

	public List<AdminDto> retreiveAllAdmin() {
			List<AdminDto> userList = new ArrayList<AdminDto>();
			for (Admin i : adminRepository.findAll()) {
				userList.add(convertEntityToDto(i));
			}
			return userList;
	}

	public AdminDto getAdminDetailsByEmail(String email) {
		Admin admin= adminRepository.findByEmail(email);
		AdminDto adminDto=convertEntityToDto(admin);
		return adminDto;
	}

	public Admin convertDtoToEntity(AdminDto adminDto)
	{
		return modelMapper.map(adminDto,Admin.class);
	}
	
	public AdminDto convertEntityToDto(Admin admin)
	{
		return modelMapper.map(admin, AdminDto.class);
	}

	public AdminDto updateAdminProfile(String email, AdminDto adminDto) {
		Admin admin = adminRepository.findByEmail(email);
		admin.setPhone(adminDto.getPhone());
		admin.setUserName(adminDto.getUserName());
		Admin admin1=adminRepository.save(admin);
		AdminDto updatedAdminDetails = convertEntityToDto(admin1);
		return updatedAdminDetails;
	}
	
	public AdminDto updateAdminPassword(String email, AdminDto adminDto) {
		Admin admin = adminRepository.findByEmail(email);
		admin.setPassword(adminDto.getPassword());
		Admin admin1=adminRepository.save(admin);
		AdminDto updatedAdminDetails = convertEntityToDto(admin1);
		return updatedAdminDetails;
	}
	

}
