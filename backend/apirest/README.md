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

# springBoot docs

### configure tests
https://www.baeldung.com/spring-testing-separate-data-source
https://www.baeldung.com/spring-boot-testing

### spring validation
https://docs.spring.io/spring-framework/reference/core/validation/beanvalidation.html

### springboot controller
https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-requestmapping.html

### spring jpa + hibernate
https://www.baeldung.com/learn-jpa-hibernate

### following maven phases
https://www.baeldung.com/maven-goals-phases
https://docs.spring.io/spring-boot/docs/3.2.2/maven-plugin/reference/htmlsingle/#introduction

# testing docs
https://www.toptal.com/java/a-guide-to-everyday-mockito