package br.com.deveria.devtest.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.deveria.devtest.web.dto.UserDto;

@RestController
public class LoginController {

	@PostMapping("/login")
	public String login(@RequestBody UserDto userDto) {
		return "logado";
	}
	
	@PostMapping("/logout")
	public String logout(@RequestBody UserDto userDto) {
		return "logado";
	}
	
	@GetMapping("/teste/{param}")
	public UserDto teste(@PathVariable String param) {
		UserDto userDto = new UserDto();
		userDto.setLogin("thiago");
		return userDto;
	}
}
