package com.mindtree.areamanagement.modules.user.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mindtree.areamanagement.modules.user.dto.UserDto;
import com.mindtree.areamanagement.modules.user.entity.User;
import com.mindtree.areamanagement.modules.user.service.UserService;

@Controller
@CrossOrigin(origins="http://localhost:4200")
public class UserController {
	@Autowired
	private UserService userService;
	@RequestMapping("/login")
    public String login() {
		return "login successfully";	
	}

	
	@PostMapping("/addUser")
	public ResponseEntity<UserDto> insertUsers(@RequestBody UserDto userDto){
		userDto=userService.addUser(userDto);
		return ResponseEntity.ok(userDto);
	}
	
	@RequestMapping("/getUsers")
	public ResponseEntity<?> getUsers() {
		List<UserDto> UserDtoList = userService.retreiveAllUser();
		return new ResponseEntity<List<UserDto>>(UserDtoList, HttpStatus.CREATED);
	}
	
	@PutMapping("/updateUserProfile/{email}")
	public UserDto updateUsers(@PathVariable String email, @RequestBody UserDto userDto) {
		return userService.updateUserProfile(email, userDto);
	}
		
	@GetMapping("/getUser/{email}")
	public ResponseEntity<?> getUsersByEmail(@PathVariable("email") String email) {
		UserDto foundCafe = userService.getUserDetailsByEmail(email);
		return new ResponseEntity<UserDto>(foundCafe, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deleteUser/{email}")
	public UserDto deleteCafe(@PathVariable("email") String email) {
		return userService.deleteParticularUserByEmail(email);
	}
}
