mvn clean
mvn test-compile
mvn compile  package install -D maven.test.skip=true -P product
