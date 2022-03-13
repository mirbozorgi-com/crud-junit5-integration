# Product Management Coding Example


## This Project include:

- Unit test with Junit5 (repository,service,controller)
- integration test
- Docker
- Elk integration
- Monitoring integration
- 2 model for persisting data : 1-JPA 2- HQL : hibernate query language
- Profiling in the Spring boot
- Error handling

Following task is implemented:

- Create a REST API that provides CRUD operations for a product which is stored in the `products`
  table.
- The API needs to be able to return all products or a specific product.
- Implement validation for the fields of a product based on the DDL in `data.sql`.
- Provide a unit test for updating a product.
- API calls that are able to alter data should be secured via Spring Security.
    - GET calls should return data without authorization.

- DockerFile and docker-compose.yml added in order to run application with docker. For building the
  image, please execute this command in the root
  directory : `sudo docker build --no-cache -t shop/mirbozorgi:1.0.0 .`
  after that in the root directory with this command , your back end will be gone
  up : `sudo docker-compose up`
  For deploying with docker swarm (in the root directory):
  1- `sudo docker swarm init 2- sudo docker build --no-cache -t shop/mirbozorgi:1.0.0 .`
  3- `sudo docker stack deploy -c docker-compose.yml shop `
  4- with this command you can find the state of services : `sudo docker service ls`

- I set some interceptors like : ApiUsageInterceptor(in order to log the request's path, this is
  basic, we can set more things if it is needed)
- I added the logstash configuration and with this configuration we can centralize our logs in to
  ELK(Elastic search, Logstash, Kibana) and with Kibana's dashboard see our failure points and so
  on.
- I added an api which name is `/health`, this api will be used for monitoring the application and
  databases.For example by deploying (Prometheus,Caddy,Grafana) and set this api in their
  configuration we are able to find out the states.
- I wrote 2 ways of Repository (Jpa `at.wirecube.examples.products.application.repository.jpa`
  ,Entity Manager with sql`at.wirecube.examples.products.application.repository.entitymanager`), and
  in this Project, I have used Jpa,but with changing the profile, there is possibility to use Entity
  Manager.
- You can find the Api documentation (Swagger) in this address: `http://localhost:8080/docs`
- You can find Export of PostMan Package in this Path: `/postman_export` directory
- About `at.wirecube.examples.products.application.enums` : because the enums were Integer model in
  String type and I want to centralize the enums for validating the requests at the controller level
  so I create this class for validating the enums and in the future if some new value will be added
  to the enums, with adding the values to this class again we are able to validating enums with just
  few changes.
- I wrote one sample of integration test in this path:
  `at.wirecube.examples.products.application.integration.controller`, and whenever our server is
  down, it will be ignored (with the usage of `@DisabledIf("customCondition")`), with these methods,
  we can set all of first level acceptance test and integration test and there is no need to test
  the APIs by postman or something like that.

### Custom exception Description:

- incorrect enum exception : this happens when the user set wrong Vat value (in this version,correct
  vat values are : 10,12,18)
  response sample:
  `{
  "msg": "incorrect enum exception",
  "data": "correct values are one of the string value of: [18, 10, 12]"
  }`
  http status : `400`
- not_found : The entity not found in the db, in this version the only entity is product response
  sample :
  `{
  "msg": "not_found",
  "data": "product_not_found"
  }`
  http status : `404`
- Price must not be null : as the instruction wanted, price must not be null response sample :
  `{
  "msg": "wrong_model",
  "data": [
  "price : must not be null"
  ]
  }`
  http status : `400`
- Name must not be null : as the instruction wanted, name must not be null response sample :
  `{
  "msg": "wrong_model",
  "data": [
  "name : must not be null"
  ]
  }`
  http status : `400`


