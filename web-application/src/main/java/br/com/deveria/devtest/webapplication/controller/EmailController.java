package br.com.deveria.devtest.webapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.deveria.devtest.common.dto.EmailDto;
import br.com.deveria.devtest.webapplication.service.EmailProducerService;

@RestController
public class EmailController {
	
	@Autowired
	private EmailProducerService emailProducerService;
	
	@PostMapping("/email")
	public ResponseEntity<String> send(@RequestBody EmailDto emailDto) {
		boolean success = emailProducerService.produce(emailDto);
		if(success) {
			return new ResponseEntity<String>("Sended successfully.", HttpStatus.OK);				
		} else {
			return new ResponseEntity<String>("Not sended.", HttpStatus.OK);
		}
	}
}
