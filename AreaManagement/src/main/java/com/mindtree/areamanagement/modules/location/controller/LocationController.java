package com.mindtree.areamanagement.modules.location.controller;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.areamanagement.modules.location.dto.LocationDto;
import com.mindtree.areamanagement.modules.location.entity.Location;
import com.mindtree.areamanagement.modules.location.service.LocationService;

@RestController
public class LocationController {

	@Autowired
	private LocationService locationService;

	@RequestMapping(value = "/addLocation", method = RequestMethod.POST)
	public ResponseEntity<LocationDto> addLocation(@RequestBody LocationDto locationDto) {
		System.out.println(locationDto.getLocationName());
		LocationDto addedLocationDto = this.locationService.addLocation(locationDto);
		return new ResponseEntity<LocationDto>(addedLocationDto, HttpStatus.CREATED);
	}

	@GetMapping("/getLocations")
	public ArrayList<Location> getLocations() {
		return locationService.getLocations();
	}

	@PutMapping("/updateLocation/{id}")
	public ResponseEntity<LocationDto> updateLocation(@RequestBody LocationDto locationDto, @PathVariable long id) {
		LocationDto updateLocationDto = locationService.updateLocation(locationDto, id);

		return new ResponseEntity<LocationDto>(updateLocationDto, HttpStatus.CREATED);
	}

	@DeleteMapping("/deleteLocation/{id}")
	public LocationDto deleteLocation(@PathVariable long id) {
		return this.locationService.deleteLocation(id);
	}
}