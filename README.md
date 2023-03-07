# Instagram_Project
## Frameworks and languages used
* SpringBoot
* Java

## Data flow in the project
1. Controller - It is used to define the path flow of service.
2. Services - It is used to perform the bussiness logics to get desired out put.
3. Repository - it is used to store the data modeling methods.
4. DataBase Design - it is used to store the data.

## Data Structure Used in Project
* ArrayList
## Project Summary
Instagram is project where We used to store the data of Users and posts in Mysql Database.
In this project we had used MVC(Model View Controller) Design.
* When ever we click on the link the action takes place in the Contoller.
* Then it goes to Service layer to perform some business operation and according to the request we had given it will perform.
* Then it goes to the dao layer which is known as Repository layer to store and manipulate the date of user.

## API's
### Users
#### Get : fetch data
* Get All Users
```
http://localhost:8080/getAll
```
* Get User by their UserId :- required Id in String format.
* it is param variable so we just provide the value in the param. 
```
http://localhost:8080/user
```
#### Post : add data
* Add User
```
http://localhost:8080/user
```
#### Put : Update data
* Update User by their Id :- required Id in String format.
* we need to provide the pathvariable of UserId 
```http
http://localhost:8080/user/{userId}
```
#### The above Links are used to perform operation on the Users.

### Posts
#### Get : fetch data
* Get Post using userId and PostId as params
```
http://localhost:8080/post?
```
#### Post : add data
* Add Post
```
 http://localhost:8080/post
```
#### Update : update data
* To update Post
* We need to give the postId as path variable
```
http://localhost:8080/post/{PostId}
```
#### The above Links are used to perform operation on the Post.
