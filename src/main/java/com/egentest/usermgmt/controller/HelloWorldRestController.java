package com.egentest.usermgmt.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.egentest.usermgmt.model.User;
import com.egentest.usermgmt.service.UserService;

@RestController
public class HelloWorldRestController {

	@Autowired
	UserService userService;  //Service which will do all data retrieval/manipulation work

	//-------------------Provide info when accessing home page '/'--------------------------------------------------------
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<String> welcomeToApp() {
		return new ResponseEntity<String>("<h1> Welcome </h1> <br>"
				+ "<h4>How to use the app:</h4><br>"
				+ "Create User(POST method): http://localhost:8080/UserMgmtCRUDRestServices/user/ <br>"
				+ "Get the user by id(GET method): http://localhost:8080/UserMgmtCRUDRestServices/user/{id} <br>"
				+ "Get All Users(GET method): http://localhost:8080/UserMgmtCRUDRestServices/user/ <br>"
				+ "updateUser(PUT method): http://localhost:8080/UserMgmtCRUDRestServices/user/", HttpStatus.OK);
	}	
	
	//-------------------Retrieve All Users--------------------------------------------------------
	
	@RequestMapping(value = "/user/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> listAllUsers() throws IOException{
		List<User> users = userService.findAllUsers();
		System.out.println("after DAO: " + users.size());
		if(users.isEmpty()){
			throw new IOException("No User Present, create users first");
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	//-------------------Retrieve Single User--------------------------------------------------------
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUser(@PathVariable("id") long id) throws IOException{
	//public ResponseEntity<User> getUser(@PathVariable("id") long id,@RequestBody @Valid User user, BindingResult bindingResult) throws IOException{
		System.out.println("Fetching User with id " + id);
		//if(bindingResult.hasFieldErrors());
		User user = userService.findById(id);
		if (user == null) {
			throw new IOException("User with id: " + id +" not present");
		}else{
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
	}
	
	//-------------------Create a User--------------------------------------------------------
	
	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	public ResponseEntity<Void> createUser(@RequestBody User user, 	UriComponentsBuilder ucBuilder) throws IOException {
		User userFromDB = userService.findById(user.getUserId());
		if (userFromDB == null) {
			userService.saveUser(user);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getUserId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}else{
			throw new IOException("User id:" + user.getUserId() +" Already present");
		}
	}
	
	//------------------- Update a User --------------------------------------------------------
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User updatedUser)  throws IOException{
		User currentUser = userService.findById(id);
		if (currentUser==null) {
			throw new IOException("User with id " + id + " not found");
		}
		
		if(userService.updateUser(id, updatedUser))
			return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
		else
			throw new IOException("error while updating");
	}

	//------------------- Delete a User --------------------------------------------------------
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable("id") long id) throws IOException {
		System.out.println("Fetching & Deleting User with id " + id);

		User user = userService.findById(id);
		if (user == null) {
			System.out.println("Unable to delete. User with id " + id + " not found");
			throw new IOException("Unable to delete. User with id " + id + " not found");
		}

		userService.deleteUserById(id);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}
	
	//------------------- Delete All User --------------------------------------------------------
	
	@RequestMapping(value = "/user/", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteAllUsers() throws IOException{
		System.out.println("Deleting All Users");
		List<User> users = userService.findAllUsers();
		System.out.println("after DAO: " + users.size());
		if(users.isEmpty()){
			throw new IOException("No Users Present to delete");
		}else{
			//userService.deleteAllUsers();
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		}
	}
}
