package com.mindtree.areamanagement.modules.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.areamanagement.modules.user.dto.UserDto;
import com.mindtree.areamanagement.modules.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Byte>{

	public User findByUserName(String username);

	public User findByEmail(String email);

	public void deleteByEmail(String email);

	public UserDto save(UserDto userDto);

}
