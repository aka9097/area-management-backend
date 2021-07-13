package com.mindtree.areamanagement.modules.genie.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.areamanagement.modules.genie.dto.GenieDto;
import com.mindtree.areamanagement.modules.genie.entity.Genie;
import com.mindtree.areamanagement.modules.genie.repository.GenieRepository;
import com.mindtree.areamanagement.modules.user.entity.User;
import com.mindtree.areamanagement.modules.user.repository.UserRepository;

@Service
public class GenieService {

	@Autowired
	private GenieRepository genieRepository;

	@Autowired
	private UserRepository userRepository;

	ModelMapper modelMapper = new ModelMapper();

	// Converting from Dto to Entity
	public Genie convertDtoToEntity(GenieDto genieDto) {
		return modelMapper.map(genieDto, Genie.class);
	}

	// Converting from Entity to Dto
	public GenieDto convertEntityToDto(Genie genie) {
		return modelMapper.map(genie, GenieDto.class);
	}

	// Add/Raise new Genie
	public GenieDto addGenieDetails(GenieDto genieDto) {
		Genie genie = convertDtoToEntity(genieDto);
		Genie genieConvertedEntity = genieRepository.save(genie);
		GenieDto genieConvertedDto = convertEntityToDto(genieConvertedEntity);
		return genieConvertedDto;
	}

	// Get particular Genie details
	public GenieDto getGenie(int genieId) {
		Genie foundGenie = genieRepository.findById(genieId);
		GenieDto foundDtoGenie = convertEntityToDto(foundGenie);
		return foundDtoGenie;
	}

	// Get all Genie details
	public List<GenieDto> retreiveAllGenie() {
		List<GenieDto> genieList = new ArrayList<GenieDto>();
		for (Genie i : genieRepository.findAll()) {
			genieList.add(convertEntityToDto(i));
		}
		return genieList;
	}

	// Delete particular Genie
	public GenieDto deleteParticularGenieById(int genieId) {
		Genie deletedGenie = genieRepository.deleteById(genieId);
		GenieDto deletedDtoGenie = convertEntityToDto(deletedGenie);
		return deletedDtoGenie;
	}

	// Get All Genies by User email
	public List<GenieDto> getAllGenieByUser(String userEmail) {
		User foundUser = userRepository.findByEmail(userEmail);
		long getId = foundUser.getId();
		List<Genie> geniesFound = genieRepository.findGenieByUserId(getId);
		List<GenieDto> genieConverted = new ArrayList<GenieDto>();
		for (Genie i : geniesFound) {
			genieConverted.add(convertEntityToDto(i));
		}
		return genieConverted;

	}

	// Update Genie Details
	public GenieDto updateGenieDetails(int genieId, GenieDto genieDto) {
		Genie foundGenie = genieRepository.findById(genieId);
		foundGenie.setSeverity(genieDto.getSeverity());
		foundGenie.setDescription(genieDto.getDescription());
		genieRepository.save(foundGenie);
		return convertEntityToDto(foundGenie);
	}

	// Update Genie Status
	public GenieDto updateGenieStatus(int genieId, GenieDto genieDto) {
		Genie foundGenie = genieRepository.findById(genieId);
		foundGenie.setStatus(genieDto.isStatus());
		genieRepository.save(foundGenie);
		return convertEntityToDto(foundGenie);
	}

}