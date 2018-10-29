package br.com.deveria.devtest.userapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.deveria.devtest.common.dto.UserDto;
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

	@GetMapping("/findByLogin/{login}")
	public UserDto findByLogin(@PathVariable String login) {
		return userService.findByLogin(login);
	}
	
	@PostMapping("/insert")
	public ResponseEntity<String> insert(@RequestBody UserDto userDto) {
		try {
			if(userDto == null) {
				throw new Exception("UserDto required.");
			}
			userService.insert(userService.getUserFromUserDto(userDto));
			return new ResponseEntity<String>("User inserted!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Insert fail! Erro:"+e.getMessage(), HttpStatus.OK);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		try {			
			userService.delete(id);
			return new ResponseEntity<String>("User deleted!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Delete fail! Erro:"+e.getMessage(), HttpStatus.OK);
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> update(@RequestBody UserDto userDto) {
		try {
			if(userDto == null) {
				throw new Exception("UserDto required.");
			}
			userService.update(userService.getUserFromUserDto(userDto));
			return new ResponseEntity<String>("User updated!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Update fail! Erro:"+e.getMessage(), HttpStatus.OK);
		}
	}
}
