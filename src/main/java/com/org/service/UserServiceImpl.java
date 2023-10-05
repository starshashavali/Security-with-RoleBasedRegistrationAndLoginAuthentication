package com.org.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.domain.UserEntity;
import com.org.dto.RegistrationRequest;
import com.org.exception.IdNotFoundException;
import com.org.exception.InvalidUserException;
import com.org.repo.UserRepo;

@Service
public class UserServiceImpl {

	@Autowired
	private UserRepo userRepo;

	public RegistrationRequest getUserById(Integer id) {
		Optional<UserEntity> findById = userRepo.findById(id);
		if (findById.isPresent()) {
			UserEntity userEntity = findById.get();
			RegistrationRequest dto = new RegistrationRequest();
			if (userEntity.isActive() == false) {
				throw new InvalidUserException("InActive User credentials...");
			}
			BeanUtils.copyProperties(userEntity, dto);
			return dto;
		}
		throw new IdNotFoundException("id not found with id: " + id);
	}

	public String updateUserDtls(Integer id, RegistrationRequest request) {
		Optional<UserEntity> userEntity = userRepo.findById(id);
		if (userEntity.isPresent()) {
			UserEntity entity = userEntity.get();
			BeanUtils.copyProperties(request, entity);
			userRepo.save(entity);
			return "updated successfully...";
		}
		throw new IdNotFoundException("id not found with id: " + id);
	}

}
