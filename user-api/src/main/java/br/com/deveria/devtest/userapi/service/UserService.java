package br.com.deveria.devtest.userapi.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.deveria.devtest.userapi.domain.User;
import br.com.deveria.devtest.userapi.dto.UserDto;
import br.com.deveria.devtest.userapi.repository.UserRepository;

@Component
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<UserDto> findAll() {
		Iterable<User> all = userRepository.findAll();
		return StreamSupport.stream(all.spliterator(), false)
			.map(u -> new UserDto(u))
			.collect(Collectors.toList());
	}
	
	public UserDto findById(Long id) {
		Optional<User> userOpt = userRepository.findById(id);
		if(userOpt.isPresent()) {
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(userDto, userOpt.get());
			return userDto;
		} else {
			return null;
		}
	}
	
	public void insert(UserDto userDto) {
		User user = new User();
		BeanUtils.copyProperties(userDto, user);
		userRepository.save(user);
	}
	
	public void update(UserDto userDto) {
		User user = new User();
		BeanUtils.copyProperties(userDto, user);
		userRepository.save(user);
	}

	public void delete(Long id) {
		userRepository.deleteById(id);
	}
}
