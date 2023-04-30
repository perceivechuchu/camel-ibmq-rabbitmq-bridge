# Camel MQ Rabbit Bridge 
  
This project provides functionality to route messages from IBM MQ to RabbitMQ.

## Build project 

Change directory into: 'camel-mq-rabbit-bridge' project and execute commands below in that order:  

$ docker compose build  
$ docker compose up  

To access the IBM MQ Management console visit the following link: https://localhost:9443  
Username: admin  
Password: passw0rd  

To access the Rabbit MQ explorer console, visit the following link: http://localhost:15672/#/queues  
Username: guest  
Password: quest  

- To drop a message into IBM MQ open link: https://localhost:9443/ibmmq/console/#/manage/qmgr/QM1/queue/local/DEV.QUEUE.1/view and click 'CREATE' button. In 'Application data' field type the test message you want to send to IBM MQ Queue.
- You will then see the log in the console and the message is routed to RabbitMQ
- To then view the message in RabbitMQ navigate to: http://localhost:15672/#/queues to view the message routed from IBM MQ to RabbitMQ