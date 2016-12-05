/*
 * Copyright (c) 2015 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */

package com.ge.predix.solsvc.consumersample.handler;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.ContentTypeDelegatingMessageConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ge.predix.solsvc.consumersample.service.SampleCustomService;

/**
 * 
 * @author 212367843
 */
@SuppressWarnings({ "nls" })
@Component
public class SampleCustomMessageHandler implements MessageListener {

	private static final Logger logger = LoggerFactory
			.getLogger(SampleCustomMessageHandler.class.getName());

	@Autowired
	private ContentTypeDelegatingMessageConverter ctdMessageConverter;

	@Autowired
	private Jackson2JsonMessageConverter jacksonMessageConverter;

	@Autowired
	private SimpleMessageConverter simpleMessageConverter;

	@Autowired
	private SampleCustomService sampleSvc;

	public SampleCustomMessageHandler() {
		Map<String, MessageConverter> listOfConverters = new HashMap<String, MessageConverter>();

		listOfConverters.put(MessageProperties.CONTENT_TYPE_JSON,
				jacksonMessageConverter);
		listOfConverters.put(MessageProperties.CONTENT_TYPE_TEXT_PLAIN,
				simpleMessageConverter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.amqp.core.MessageListener#onMessage(org.springframework
	 * .amqp.core.Message)
	 */
	@Override
	public void onMessage(Message message) {

		// Get the java object from the payload
		Object myMessage = extractMessageUsingCustomOrOutOfBoxMessageConverter(message);

		// Business Logic using the payload received from message
		//
		sampleSvc.businessLogic(myMessage);
	}

	public Object extractMessageUsingCustomOrOutOfBoxMessageConverter(
			Message payload) {

		Object myMessage = null;

		try {

			logger.info("Payload message properties = "
					+ payload.getMessageProperties().toString());
			logger.info("Payload message content type = "
					+ payload.getMessageProperties().getContentType());

			logger.info("Available message converters ="
					+ this.ctdMessageConverter.getDelegates());

			logger.info("Message converter used = "
					+ this.ctdMessageConverter.getDelegates().get(
							payload.getMessageProperties().getContentType()));
			myMessage = this.ctdMessageConverter.fromMessage(payload);
			logger.info("Message received = " + myMessage);

		} catch (MessageConversionException e) {
			logger.error("Message extraction failed. Choose appropriate content type or check your message..");
			throw e;
		}

		return myMessage;
	}
}
