package com.ttn.exceptions;

public class SessionDoesNotExistException extends Exception {

	public SessionDoesNotExistException(){
		super("You are not logged in ! Kindly login to continue !");
	}
}
