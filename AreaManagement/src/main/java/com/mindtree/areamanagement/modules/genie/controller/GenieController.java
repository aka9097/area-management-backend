package com.mindtree.areamanagement.modules.genie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mindtree.areamanagement.modules.genie.dto.GenieDto;
import com.mindtree.areamanagement.modules.genie.service.GenieService;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class GenieController {

	@Autowired
	private GenieService genieService;

	// Add/Raise new Genie

	@PostMapping("/raiseGenie")
	public ResponseEntity<?> addGenie(@RequestBody GenieDto genieDto) {
		GenieDto addedGenie = genieService.addGenieDetails(genieDto);
		return new ResponseEntity<GenieDto>(addedGenie, HttpStatus.CREATED);
	}

	// Get particular Genie details

	@RequestMapping("/getParticularGenie/{genieId}")
	public ResponseEntity<?> getParticularGenieById(@PathVariable int genieId) {
		GenieDto foundGenie = genieService.getGenie(genieId);
		return new ResponseEntity<GenieDto>(foundGenie, HttpStatus.CREATED);
	}

	// Get all Genie details

	@RequestMapping("/getAllGenieDetails")
	public ResponseEntity<?> getAllGenieDetails() {
		List<GenieDto> geniesList = genieService.retreiveAllGenie();
		return new ResponseEntity<List<GenieDto>>(geniesList, HttpStatus.CREATED);
	}

	// Delete particular Genie

	@DeleteMapping("/delete/{genieId}")
	public ResponseEntity<?> deleteParticularGenie(@PathVariable int genieId) {
		GenieDto deletedGenie = genieService.deleteParticularGenieById(genieId);
		return new ResponseEntity<GenieDto>(deletedGenie, HttpStatus.CREATED);
	}

	// Get All Genies by User email

	@GetMapping("/getAllGenieByUser/{userEmail}")
	public ResponseEntity<?> getAllGenieByUser(@PathVariable String userEmail) {
		List<GenieDto> foundGenies = genieService.getAllGenieByUser(userEmail);
		return new ResponseEntity<List<GenieDto>>(foundGenies, HttpStatus.CREATED);
	}

	// Update Genie Details

	@PutMapping("/updateGenieDetails/{genieId}")
	public ResponseEntity<?> updateGenieDetails(@PathVariable int genieId, @RequestBody GenieDto genieDto) {
		GenieDto updatedGenie = genieService.updateGenieDetails(genieId, genieDto);
		return new ResponseEntity<GenieDto>(updatedGenie, HttpStatus.CREATED);
	}

	// Update Genie Status

	@PutMapping("/updateGenieStatus/{genieId}")
	public ResponseEntity<?> updateGenieStatus(@PathVariable int genieId, @RequestBody GenieDto genieDto) {
		GenieDto updatedGenieStatus = genieService.updateGenieStatus(genieId, genieDto);
		return new ResponseEntity<GenieDto>(updatedGenieStatus, HttpStatus.CREATED);
	}

}