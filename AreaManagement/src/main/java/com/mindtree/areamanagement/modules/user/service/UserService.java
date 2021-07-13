package com.mindtree.areamanagement.modules.user.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mindtree.areamanagement.modules.user.dto.UserDto;
import com.mindtree.areamanagement.modules.user.entity.User;
import com.mindtree.areamanagement.modules.user.repository.UserRepository;


@Service
public class UserService implements UserDetailsService
 
{
	
	@Autowired
	private UserRepository userRepository;
	
	ModelMapper modelMapper=new ModelMapper();

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.mindtree.areamanagement.modules.user.entity.User user=userRepository.findByUserName(username);
		if(user==null)
			throw new UsernameNotFoundException("User 404");
		return new UserPrincipal(user);
	}

	public UserDto addUser(UserDto userDto) {
		User user=convertDtoToEntity(userDto);
		user=userRepository.save(user);
		return userDto;
	}

	public List<UserDto> retreiveAllUser() {
			List <UserDto> userList = new ArrayList<UserDto>();
			for (User i : userRepository.findAll()) {
				userList.add(convertEntityToDto(i));
			}
			return userList;
	}

	public UserDto getUserDetailsByEmail(String email) {
		User user= userRepository.findByEmail(email);
		UserDto userDto=convertEntityToDto(user);
		return userDto;
	}

	public UserDto deleteParticularUserByEmail(String email) {
		UserDto userDeleted = convertEntityToDto(userRepository.findByEmail(email));
		userRepository.deleteByEmail(email);
		if (userRepository.findByEmail(email) != null) {
			return userDeleted;
		}
		return null;
	}

	public User convertDtoToEntity(UserDto userDto)
	{
		return modelMapper.map(userDto,User.class);
	}
	
	public UserDto convertEntityToDto(User user)
	{
		return modelMapper.map(user, UserDto.class);
	}

	public UserDto updateUserProfile(String email, UserDto userDto) {
	    User user = userRepository.findByEmail(email);
	    user.setPhone(userDto.getPhone());
	    user.setUserName(userDto.getUserName());
		UserDto updatedUserDetails = convertEntityToDto(userRepository.save(user));
		return updatedUserDetails;
		
	}
}
