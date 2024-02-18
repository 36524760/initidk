# run app with testing properties/dependencies

this will run with h2, an in-memory database that supports sql
`
$ mvn spring-boot:test-run
`

with specific profile:
`
$ mvn spring-boot:run -Dspring-boot.run.profiles=dev,local
`

# for docker setup
`
mvn clean package -DskipTests
`
docker-compose --file docker-compose-back.yml up
docker-compose up

localhost:8088/

# run tests

mvn test

### specific tests

mvn test -Dtest="TestClass"

or

mvn test -Dtest="TestClass#TestMethod"

