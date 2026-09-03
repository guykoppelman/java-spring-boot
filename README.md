# Kafka Message System

Two Spring Boot microservices using Java 21, Kafka, and PostgreSQL.

## Run

```bash
mvn package
docker compose up --build
```

Swagger UI is available at `http://localhost:8080/swagger-ui/index.html`.

```bash
curl -X POST localhost:8080/api/messages -H 'Content-Type: application/json' -d '{"id":1,"msg":"hello"}'
curl -X PUT localhost:8080/api/messages/1 -H 'Content-Type: application/json' -d '{"id":1,"msg":"updated"}'
curl -X DELETE localhost:8080/api/messages/1
curl localhost:8080/api/messages/1
```

Every request returns `202 Accepted` after an asynchronous send to one of `messages.create`, `messages.update`, `messages.delete`, or `messages.read`. Kafka keys by message ID retain per-message ordering while allowing partitions and worker replicas to scale. The worker records each event UUID in the same transaction as the database action, which makes Kafka redelivery idempotent.


## Future Nots
The default scalability policy for this type of servie is to use Horizontal scaling, meaning use multiple command-api containers behind load balancer. this approche will keep the REST handlers fast: (validate, publish to kafka, return HTTP 202, Accepted)
The command-api is not bounded to IO, it only publish messages to the kafka topic serving as a buffer infront of the DB