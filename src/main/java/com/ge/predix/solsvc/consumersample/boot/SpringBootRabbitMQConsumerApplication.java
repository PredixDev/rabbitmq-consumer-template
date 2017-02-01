/*
 * Copyright (c) 2015 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */

package com.ge.predix.solsvc.consumersample.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * 
 * @author 212367843
 */
@EnableAutoConfiguration
@Configuration
@ImportResource(
{
        "classpath:META-INF/spring/rabbitmq-consumer.xml"

})
public class SpringBootRabbitMQConsumerApplication
{

    @SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(SpringBootRabbitMQConsumerApplication.class);

    public static void main(String[] args)
            throws InterruptedException
    {
        SpringApplication.run(SpringBootRabbitMQConsumerApplication.class, args);
    }
}
