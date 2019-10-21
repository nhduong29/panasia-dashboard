# Demo: 
- [https://panasia-dashboard.herokuapp.com/](https://panasia-dashboard.herokuapp.com/)
# Project structure and Techniques
- Java 8
- NodeJS
- Maven
- Spring Boot
- Spring Security
- JSON Web Token
- ReactJs
- Ant Design
- PostgreSQL
####  Build using maven 
	
```
mvn clean install
```
	
#### Start the app
- You must install Java 8, Manven 3.3.x, and Node JS 8.11.x	
```
mvn spring-boot:run
```

####  RESTful API and Features
- Sign up
-- API: POST ```/api/auth/signup```
-- Request body: ```{
	"name" : "Duong Nguyen",
	"username" : "nhduong29",
	"email" : "nhduong29@gmail.com",
	"password" : "000000"
}```
- Sign in
-- API: GET ```/api/auth/signin```
-- Request body: ```{
	"username" : "nhduong29",
	"password" : "123456"
}```
- Log out
-- Sytem will delete the token
- Paging
--  ?page=NUMBER_OF_PAGE&size=NUMBER_OF_ITEM_PER_PAGE
- Filter
-- ?brand=NAME_OF_CAR_BRAND&color=COLOR
