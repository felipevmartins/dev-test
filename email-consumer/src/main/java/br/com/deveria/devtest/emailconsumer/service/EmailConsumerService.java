package br.com.deveria.devtest.emailconsumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.deveria.devtest.common.dto.EmailDto;

public class EmailConsumerService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailConsumerService.class);

	@RabbitListener(containerFactory="rabbitListenerContainerFactory", queues = "${queue.email.name}")
	public void consumeEmailQueue(String message) {
		try {
			EmailDto emailDto = new ObjectMapper().readValue(message, EmailDto.class);
			LOGGER.info(message);
			LOGGER.info(emailDto.getMessage());
		} catch (Exception e) {
			LOGGER.error("Email consume fail. "+e.getMessage(), e);
		}
	}
}
