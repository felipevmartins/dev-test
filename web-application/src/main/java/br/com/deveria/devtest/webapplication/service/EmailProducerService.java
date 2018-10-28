package br.com.deveria.devtest.webapplication.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.deveria.devtest.common.dto.EmailDto;

@Component
public class EmailProducerService {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private Queue emailQueue;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailProducerService.class);

	public boolean produce(EmailDto emailDto) {
		try {
			String json = new ObjectMapper().writeValueAsString(emailDto);
			rabbitTemplate.convertAndSend(emailQueue.getName(), json);
			return true;
		} catch (JsonProcessingException e) {
			LOGGER.error("Email produce fail. "+e.getMessage(), e);
			return false;
		}
	}

}
