package com.mindtree.areamanagement.modules.location.service;
import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.areamanagement.modules.location.dto.LocationDto;
import com.mindtree.areamanagement.modules.location.entity.Location;
import com.mindtree.areamanagement.modules.location.repository.LocationRepository;

@Service
public class LocationService {

	@Autowired
	private LocationRepository locationRepository;

	ModelMapper modelMapper = new ModelMapper();

	public LocationDto addLocation(LocationDto locationDto) {
		Location location = convertDtoToEntity(locationDto);
		locationRepository.save(location);
		LocationDto newLocationDto = convertEntityToDto(location);
		return newLocationDto;
	}

	public Location convertDtoToEntity(LocationDto locationDto) {
		return modelMapper.map(locationDto, Location.class);
	}

	public LocationDto convertEntityToDto(Location location) {
		return modelMapper.map(location, LocationDto.class);
	}

	public ArrayList<Location> getLocations() {
		return (ArrayList<Location>) locationRepository.findAll();

	}

	public LocationDto updateLocation(LocationDto locationDto, long id) {

		LocationDto location = convertEntityToDto(locationRepository.getById(id));
		System.out.println(location.getCity());
		location.setLocationName(locationDto.getLocationName());
		location.setCity(locationDto.getCity());
		location.setPincode(locationDto.getPincode());
		Location newLocation = convertDtoToEntity(location);
		locationRepository.save(newLocation);
		return location;
	}

	public LocationDto deleteLocation(long id) {
		LocationDto deletedDto = convertEntityToDto(locationRepository.getById(id));
		locationRepository.deleteById(id);
		return deletedDto;
	}

}