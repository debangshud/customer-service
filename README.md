# CUSTOMER SERVICE
 This is a sample customer service which allows client to: 
 * Create a customer
 * List customers
 * Find a customer by id
 * Delete a customer by id
 
### BUILD AND PUSH DOCKER IMAGE

````shell script
#Build spring-boot service jar
mvn clean package

#Build docker image
docker build -t debangshud/customer-service .

# Login to dockerhub
docker login -u debangshud -p <password>

# Push the image to dockerhub
docker push debangshud/customer-service
````

### RUN DOCKER IMAGE LOCALLY

````shell script
docker run -p 8080:8080 -t debangshud/customer-service
````

### DEPLOY DOCKER IMAGE IN OPENSHIFT

```shell script
oc new-app debangshud/customer-service
oc expose svc/customer-service
```