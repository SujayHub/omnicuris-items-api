# Omnicuris-items-api
Demo Items Api

* [Description](#description)
* [Running the Application](#run)
* [Port Configuration](#port)
* [Database Configuration](#database)
* [Exceptions](#exceptions)
* [Log Configuration](#logs)
* [Demo](#demo)


#### Description: <a name="description"></a>
This api can perform the following operations:

* [create](#create) create a resource one by one or create a resource in bulk, can be used for updating resources in bulk also</br>
_**note:**_ [`throws ItemAlreadyExistsException`](#ItemAlreadyExistsException) while creating single resource not applicable for bulk operation 
* [read](#read) read one resource or read all the resource [`throws ItemNotFoundException`](#ItemNotFoundException)
* [update](#update) update one resource [`throws ItemNotFoundException`](#ItemNotFoundException)
* [delete](#delete) delete one resource [`throws ItemNotFoundException`](#ItemNotFoundException)


#### Instruction to Run: <a name="run"></a>
clone the repository into your local by running: `git clone https://github.com/SujayHub/omnicuris-items-api.git` </br>

Then run the following command `cd omnicuris-items-api`

Then run:
* on windows 
```bash
mvnw clean install
```
* on linux
```bash
./mvnw clean install
```

after a successful build run the following command
 
* on windows 
```bash
mvnw spring-boot:run
```
* on linux
```bash
./mvnw spring-boot:run
```

#### Port Configuration: <a name="port"></a>

by default our application will run on `port: 8085`

to change the port we can do the following:

* _**using maven**_
    * on windows 
     ```bash
     mvnw -Dserver.port=[your port goes here] spring-boot:run
     example:
        mvnw -Dserver.port=8090 spring-boot:run
     
     ```

    * on linux 
     ```bash
     ./mvnw -Dserver.port=[your port goes here] spring-boot:run
     example:
        ./mvnw -Dserver.port=8090 spring-boot:run
     
     ```
     
* _**application properties**_
    * you can alter the value of the key `server: port` in the application.yml file in the following manner
     
     ```yaml
     server:
       port: [your port goes here]
     ```
* _**note**_ to assign a random port to the application pass the value `0` in place of the port number

#### Database Configuration: <a name="database"></a>

since this is a small application used for demo purpose we're using an in memory database called H2 and comment everything in the data.sql file in the src/main/resources folder

To connect to MySQL db you need to do the following.

Start your MySQL server in port 3036

Open the MySQL terminal and run the following commands to create the database and the table and insert a few data
```SQL
CREATE DATABASE restapi;
USE restapi;
create table item (
    item_id varchar(255) not null,
    item_name varchar(255),
    primary key (item_id)
);

insert into item (item_name, item_id) values ("1","chocolate");
insert into item (item_name, item_id) values ("2","rapsberry");
insert into item (item_name, item_id) values ("3","pie");
insert into item (item_name, item_id) values ("4","oreo");



```

In the application.yaml file put the following properties
```yaml
spring:
 datasource:
 username: your-username-here
  url: jdbc:mysql://localhost:3306/restapi
  passsword: your-password-here
```

* In the pom.xml file _**remove**_ the following dependency:

```xml
 <dependency>
      <groupId>com.h2database</groupId>
      <artifactId>h2</artifactId>
      <scope>runtime</scope>
 </dependency>
```

* and _**add**_ the following dependency:

```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.16</version>
</dependency>
```

#### Exceptions: <a name="exceptions"></a>
We have the following custom exceptions:
* _`ItemAlreadyExistsException: `_<a name="ItemAlreadyExistsException"></a> occurs when we try to add an item with an existing itemId.
* _`ItemNotFoundException: `_<a name="ItemNotFoundException"></a> occurs when we try to read, update of delete and item which does not exist in the database record

#### Log Configuration: <a name="logs"></a>
We are using `SLf4J`. The logs for the application will be created inside a folder called `logs` in a file called `items-api.log`.
_**note**_`The location of the logs folder would be outside the directory from where the jar is running`.
The configuration of logs can be altered by altering the following properties:

```yaml
logging:
  file: "./logs/items-api.log"
  max-size: 10 MB
  level:
    root: INFO
    org:
      springframework:
        web: DEBUG
      hibernate:
        stat: DEBUG
        type: TRACE
```
 

#### Demo: <a name="demo"></a>
   * _**create**_ <a name="create"></a></br>
       * create one item: </br>
       
       ```curl
       curl --request POST \
         --url http://localhost:8085/items/addItem \
         --header 'content-type: application/json' \
         --data '{
       	"itemId": "1" ,
       	"itemName": "chocolates"
       }'
       ```
       response:
       no body
       
       * create or update  multiple item at once: </br>
       
       ```curl
       curl --request POST \
         --url http://localhost:8085/items/addItem/bulkInsert \
         --header 'content-type: application/json' \
         --data '[
       	{
       		"itemId": 2,
       		"itemName": "rapsberry"
       	},
       	{
       		"itemId": 3,
       		"itemName": "pie"
       	},
       	{
       		"itemId": 4,
       		"itemName": "orio"
       	}
       ]'
       ```
       response:
       no body   
       
   * _**read**_ <a name="read"></a>
       * read one item: </br>
       
       ```curl
          curl --request GET \
            --url http://localhost:8085/items/2
          ```
          response:
          ```json
          {
            "itemId": "2",
            "itemName": "rapsberry"
          }
          ```
        
          
       * read all item: </br>
       
       ```curl
          curl --request GET \
            --url http://localhost:8085/items
          ```
          response:
          ```json
          [
            {
              "itemId": "1",
              "itemName": "chocolates"
            },
            {
              "itemId": "2",
              "itemName": "rapsberry"
            },
            {
              "itemId": "3",
              "itemName": "pie"
            },
            {
              "itemId": "4",
              "itemName": "orio"
            }
          ]
          ```
          
   * _**update**_ <a name="update"></a>
       * update one item: </br>
            
            ```curl
               curl --request PUT \
                 --url http://localhost:8085/items/update \
                 --header 'content-type: application/json' \
                 --data '{
               	"itemId": 1,
               	"itemName": "tofy"
               }'
               ```
               response:
               no body    
          
   * _**delete**_ <a name="delete"></a>
       * delete one item: </br>
       
       ```curl
          curl --request DELETE \
            --url http://localhost:8085/items/1
          ```
          response:
          no body

