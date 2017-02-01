<a href="http://predixdev.github.io/rabbitmq-consumer-template/javadocs/index.html" target="_blank" >
	<img height="50px" width="100px" src="images/javadoc.png" alt="view javadoc"></a>
&nbsp;
<a href="http://predixdev.github.io/rabbitmq-consumer-template" target="_blank">
	<img height="50px" width="100px" src="images/pages.jpg" alt="view github pages">
</a>

What is this Project?

   This project is a RabbitMQ consumer template which has RabbitMQ template, connectionfactory, message converters pre-configured for the end-users to focus on writing their business logic.

   Also, the consumer side code is configured with retry from the queue for 3 attempts. If unsuccessful, the payload will be queued to an error queue for later debugging.


Steps to run this project

1) mvn clean package 
	

2) Start rabbitmqserver		

		rabbitmq-server

3) Rabbitmq server will listen typically on 5672 and rabbitmq administration will listen typically on 15672
	
		Go to the following rabbitmq administration web page: 
			
		http://localhost:15672

		login as guest/guest

4) Run the Dispatcherq consumer code as follows:


	java -jar target/dispatcher-q-1.0-SNAPSHOT.jar --port=<rabbitmq server port> --server=<rabbitmq server host> --mainq=<rabbitmq main queue name> --errorq=<rabbitmq error queue name> --dispatcherhost=<server/hostname only where insight service is running> --dispatcherport=<server port where insight service is listening>

	eg: java -jar target/dispatcher-q-1.0-SNAPSHOT.jar --port=5672 --server=localhost --mainq=fieldchangedeventMainQ --errorq=fieldchangedeventErrorQ --dispatcherhost=localhost --dispatcherport=19090 

5) To run integration tests
	
	mvn clean install -P testharness    

[![Analytics](https://ga-beacon.appspot.com/UA-82773213-1/rabbitmq-consumer-template/readme?pixel)](https://github.com/PredixDev)
