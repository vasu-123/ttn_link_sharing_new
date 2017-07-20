package com.ttn.exceptions;

public class EmailNotFoundException extends Exception {

	public EmailNotFoundException() {
		// TODO Auto-generated constructor stub
	super("Email entered by you is not registered . Kindly register first");
	}
}
