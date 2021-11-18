package com.example.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;

import com.example.config.KeycloakConfig;

import com.example.model.User;

@Configuration
public class KeycloakAdminClientService {
		
	 UsersResource usersResource = KeycloakConfig.getInstance().realm(KeycloakConfig.getRealm()).users();
	   
	     
	 public Object addUser(User user) {
		CredentialRepresentation credentialRepresentation = createPasswordCredentials(user.getPassword());
		UserRepresentation kcUser = new UserRepresentation();
  	    kcUser.setUsername(user.getUserName());
  	    kcUser.setCredentials(Collections.singletonList(credentialRepresentation));
  	    kcUser.setFirstName(user.getFirstName());
  	    kcUser.setLastName(user.getLastName());
  	    kcUser.setEmail(user.getEmail());
  	    kcUser.setEnabled(true);
  	    kcUser.setEmailVerified(false);
  	    usersResource.create(kcUser);
  	    System.out.println("result:"+kcUser.getUsername());
  	    return "User Created Sucessfully";

  	}
  	private static CredentialRepresentation  createPasswordCredentials(String password) {
  	    CredentialRepresentation passwordCredentials = new CredentialRepresentation();
  	    passwordCredentials.setTemporary(false);
  	    passwordCredentials.setType(CredentialRepresentation.PASSWORD);
  	    passwordCredentials.setValue(password);
  	    return passwordCredentials;
  	}
  	
	public List<UserRepresentation> diplayUser() {
		
		List<UserRepresentation> response = KeycloakConfig.getInstance().realm(KeycloakConfig.getRealm()).users().list();
  		return  response;  		

	}
	public List<UserRepresentation> getUserDetailsByName(String userName) {
		
		return usersResource.search(userName);
	}
	public Object updateUser(User user) 
	{
        List<UserRepresentation> userList = KeycloakConfig.getInstance().realm(KeycloakConfig.getRealm()).users().search(user.getUserName());
        for (UserRepresentation user1 : userList) {
        String id = user1.getId();
        UserRepresentation userRep = new UserRepresentation();
     	userRep.setId(id);
		userRep.setFirstName(user.getFirstName());
		userRep.setLastName(user.getLastName());
		userRep.setEmail(user.getEmail());
	  	userRep.setEnabled(true);
		userRep.setEmailVerified(false);
		UserResource userResource = KeycloakConfig.getInstance().realm(KeycloakConfig.getRealm()).users().get(userRep.getId());
		userResource.update(userRep);
        }
        return "Update Sucessfully";

	}    

	public Object deleteUserByName(User user) {
		
		Keycloak keycloak = KeycloakConfig.getInstance();
        List<UserRepresentation> userList = keycloak.realm(KeycloakConfig.getRealm()).users().search(user.getUserName());
        
        for (UserRepresentation user1 : userList) {
            if (user1.getUsername().equals(user.getUserName())) {
            	 keycloak.realm(KeycloakConfig.getRealm()).users().delete(user1.getId());
            }
        }        
        return "Deleted Sucessfully";
        }
	
 }

	
	



