package br.com.deveria.devtest.userapi.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.deveria.devtest.common.dto.UserDto;
import br.com.deveria.devtest.userapi.domain.User;
import br.com.deveria.devtest.userapi.repository.UserRepository;

@Component
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<UserDto> findAll() {
		Iterable<User> all = userRepository.findAll();
		return StreamSupport.stream(all.spliterator(), false)
			.map(u -> getUserDtoFromUser(u))
			.collect(Collectors.toList());
	}

	public UserDto findById(Long id) {
		Optional<User> userOpt = userRepository.findById(id);
		if(userOpt.isPresent()) {
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(userOpt.get(), userDto);
			return userDto;
		} else {
			return null;
		}
	}

	public UserDto findByLogin(String login) {
		Optional<User> userOpt = userRepository.findByLogin(login);
		if(userOpt.isPresent()) {
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(userOpt.get(), userDto);
			return userDto;
		} else {
			return null;
		}
	}
	
	public void insert(@Valid User user) throws Exception {
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		userRepository.save(user);
	}
	
	public void update(@Valid User user) {
		userRepository.save(user);
	}

	public void delete(@NotNull Long id) {
		userRepository.deleteById(id);
	}
	
	public User getUserFromUserDto(UserDto u) {
		User user = new User();
		BeanUtils.copyProperties(u, user);
		return user;
	}
	
	public UserDto getUserDtoFromUser(User u) {
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(u, userDto);
		return userDto;
	}
}
