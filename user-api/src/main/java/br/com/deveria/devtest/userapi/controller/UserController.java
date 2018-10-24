package br.com.deveria.devtest.userapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.deveria.devtest.userapi.dto.UserDto;
import br.com.deveria.devtest.userapi.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/find/{id}")
	public UserDto find(@PathVariable Long id) {
		return userService.findById(id);
	}

	@GetMapping("/findAll")
	public List<UserDto> find() {
		return userService.findAll();
	}
	
	@PostMapping("/insert")
	public void insert(@RequestBody UserDto userDto) {
		userService.insert(userDto);
	}

	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		userService.delete(id);
	}
	
	@PutMapping("/update")
	public void update(@RequestBody UserDto userDto) {
		userService.update(userDto);
	}
}
