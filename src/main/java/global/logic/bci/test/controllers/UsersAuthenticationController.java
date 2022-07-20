package global.logic.bci.test.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import global.logic.bci.test.handlers.UsersAuthenticationHandler;
import global.logic.bci.test.models.request.RegisterNewUserRequest;
import global.logic.bci.test.models.response.LoginResponse;
import global.logic.bci.test.models.response.RegisterNewUserResponse;

@RestController
public class UsersAuthenticationController {
	private static final Logger logger = LoggerFactory.getLogger(UsersAuthenticationController.class);
	@Autowired
	private UsersAuthenticationHandler handler;
	
	/*
	 	Creación de un usuario
	 	Params: @body (recibe la información necesaria para generar un alta)
	*/
	@PostMapping(value="/sign-up", produces={"application/json"}, consumes = {"application/json"})
	public ResponseEntity<RegisterNewUserResponse> registerNewUser(@RequestBody RegisterNewUserRequest body) {
		logger.info("[Sign-Up] Request recibida");
		return handler.registerNewUser(body);
	}
	
	/*
	 	Login de un usuario
	 	Params: @token
	*/
	@GetMapping(value="/login")
	public ResponseEntity<LoginResponse> login(@RequestHeader String token) {
		logger.info("[Login] Request recibida");
		return handler.login(token);
	}
}