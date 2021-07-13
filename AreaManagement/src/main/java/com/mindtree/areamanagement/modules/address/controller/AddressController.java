package com.mindtree.areamanagement.modules.address.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.areamanagement.modules.address.dto.AddressDto;
import com.mindtree.areamanagement.modules.address.entity.Address;
import com.mindtree.areamanagement.modules.address.service.AddressService;
import com.mindtree.areamanagement.modules.user.dto.UserDto;
import com.mindtree.areamanagement.modules.user.service.UserService;

@RestController
public class AddressController {

	@Autowired
	private AddressService addressService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/addAddress")
	public ResponseEntity<?> addAddress(@RequestBody AddressDto addressDto) {
		AddressDto addedAddressDto  = this.addressService.addAddress(addressDto);
		return new ResponseEntity<AddressDto>(addedAddressDto, HttpStatus.CREATED);
	}
	
	@GetMapping("/allAddressDetails")
	public ResponseEntity<?> getaddressDetails(){
		List<AddressDto> addressDtoList = this.addressService.getaddressDetails();
		return new ResponseEntity<List<AddressDto>>(addressDtoList, HttpStatus.CREATED);
	}

	@DeleteMapping("/deleteAddress/{id}")
	public AddressDto deleteAddress(@PathVariable("id") int id) {
		return this.addressService.deleteAddress(id);
	}

	@GetMapping("/getAddressById/{id}")
	public AddressDto getAddressById(@PathVariable("id") int id) {
		return this.addressService.getAddressById(id);
	}

	@PutMapping("/updateAddress/{userEmail}")
	public AddressDto updateAddress(@PathVariable("userEmail") String userEmail,@RequestBody AddressDto addressDto) {
		UserDto userDetails=userService.getUserDetailsByEmail(userEmail);
		int addressId=userDetails.getAddress().getId();
		return this.addressService.updateAddressDetails(addressId, addressDto);
	}

	
}
