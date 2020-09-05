# Kafka Avro Schema Registry
Example using spring-cloud-stream with Kafka and using AVRO as a serializer and deserializer.

### Frameworks
- spring-cloud-stream-function (reactive)
- Spring / Apache / Confluent AVRO libraries

### Programming Language
- Kotlin

### Prerequisites
- [docker](https://docker)
- [docker-compose](https://docker-compose)
- [postman](https://postman) or [curl](https://curl)
- [java >= 11](https://java)

### Running Application
- Go to the project directory
```
$ cd <project directory>
```
- Boot the Kafka infrastructure
```
$ docker-compose up
```
- Create a new topic
```
$ docker-compose exec broker kafka-topics --create --topic user --partitions 1 --replication-factor 1 --if-not-exists --zookeeper zookeeper:2181
```
- Run the application
```
$ ./gradlew run
```
- Create a user (POST request)
```
$ curl --location --request POST 'http://localhost:8080/user' \
  --header 'Content-Type: application/json' \
  --data-raw '{
      "email": "email@gmail.com",
      "password": "anyPassword"
  }'
```
- Look at the application log to view the message being produced and consumed according to this example.

- Example of the produced message log
```
2020-09-05 15:34:51.175  INFO [,e06f595831bdb8ec,e06f595831bdb8ec,true] 30286 --- [or-http-epoll-3] c.d.s.s.a.o.e.p.UserProducer - Produced  : | onNext(User(email=email@gmail.com, password=anyPassword))
```
- Example of the consumed message log
```
2020-09-05 15:34:51.181  INFO [,fde52e87d9dee95a,c8b121fe17b49cda,false] 30286 --- [container-0-C-1] c.d.s.s.a.s.ShowUserService - Consumed   : | onNext(User(email=email@gmail.com, password=anyPassword))
```