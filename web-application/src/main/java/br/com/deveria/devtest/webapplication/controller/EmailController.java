package br.com.deveria.devtest.webapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.deveria.devtest.webapplication.service.EmailProducerService;

@RestController
public class EmailController {
	
	@Autowired
	private EmailProducerService emailProducerService;
	
	@PostMapping("/email")
	public String send(@RequestParam String to, @RequestParam String message) {
		boolean success = emailProducerService.produce(to, message);
		return success ? "success" : "fail";
	}
}
