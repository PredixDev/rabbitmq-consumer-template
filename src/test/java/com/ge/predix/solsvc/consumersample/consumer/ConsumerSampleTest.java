/*
 * Copyright (c) 2015 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */

package com.ge.predix.solsvc.consumersample.consumer;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MarshallingMessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ge.predix.solsvc.consumersample.handler.SampleCustomMessageHandler;
import com.ge.predix.solsvc.consumersample.handler.SampleCustomObject;

/**
 * 
 * @author 212367843
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:Test-rabbitmq-consumer.xml")
public class ConsumerSampleTest {

	@Autowired
	private SimpleMessageConverter simpleMessageConverter;
	
	@Autowired
	private Jackson2JsonMessageConverter jacksonMessageConverter;
	
	@Autowired
	private MarshallingMessageConverter xmlMessageConverter;
	
	@Autowired
	private SampleCustomMessageHandler myHandler;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSendStringMessage() {
		
		MessageProperties mp = new MessageProperties();
		mp.setContentType("text/plain");
		
		Message msg = simpleMessageConverter.toMessage("Test String Message", mp);
		myHandler.onMessage(msg);		
		
		Assert.assertNotNull(myHandler.extractMessageUsingCustomOrOutOfBoxMessageConverter(msg));
	}

	@Test
	public void testSendJsonMessage() {
		
		MessageProperties mp = new MessageProperties();
		mp.setContentType("application/json");
		
		SampleCustomObject testObject = new SampleCustomObject();
		testObject.setAttribute1("attrjson1");
		testObject.setAttribute2("attrjson2");
		
		Message msg = jacksonMessageConverter.toMessage(testObject, mp);
		myHandler.onMessage(msg);		
		
		Assert.assertNotNull(myHandler.extractMessageUsingCustomOrOutOfBoxMessageConverter(msg));
	}
	
	@Test
	public void testSendXmlMessage() {
		
		MessageProperties mp = new MessageProperties();
		mp.setContentType("application/xml");
		
		SampleCustomObject testObject = new SampleCustomObject();
		testObject.setAttribute1("xml1");
		testObject.setAttribute2("xml2");
		
		Message msg = xmlMessageConverter.toMessage(testObject, mp);
		myHandler.onMessage(msg);		
		
		Assert.assertNotNull(myHandler.extractMessageUsingCustomOrOutOfBoxMessageConverter(msg));
	}
}
