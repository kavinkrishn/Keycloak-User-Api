package com.example.controller;

import java.util.List;

import javax.ws.rs.core.Response;

import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.example.model.User;
import com.example.service.KeycloakAdminClientService;



@RestController
public class UserController {

		@Autowired
		KeycloakAdminClientService keycloakAdminClientService;

	   @RequestMapping(value = "/createuser", method = RequestMethod.POST)
	   public Object createUser(@RequestBody User user) {
		   return keycloakAdminClientService.addUser(user);
	   }   
	   
	@RequestMapping(value="/getuser",method = RequestMethod.GET)
	public List<UserRepresentation> displayUser (){
		return keycloakAdminClientService.diplayUser();
	}
	
	@RequestMapping(value="/getUserDetailsByName/{userName}",method=RequestMethod.GET)
	public List<UserRepresentation> getUserDetailsByName(@PathVariable String userName)
	{
		return (keycloakAdminClientService.getUserDetailsByName(userName));
	}
	
	@RequestMapping(value="/updateUserDetails",method=RequestMethod.PUT)
	public Object updateUser(@RequestBody User user)
	{
		return (keycloakAdminClientService.updateUser(user));
	}
	
	@RequestMapping(value="/deleteUserDetailsByName",method=RequestMethod.POST)
	public Object deleteUserByName(@RequestBody User user)
	{
		return (keycloakAdminClientService.deleteUserByName(user));
		
	}

}