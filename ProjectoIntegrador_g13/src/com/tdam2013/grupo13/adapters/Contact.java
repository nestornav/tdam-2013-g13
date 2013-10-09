package com.tdam2013.grupo13.adapters;

import java.util.ArrayList;

public class Contact {
	
	private String id;
	private String name;
	private String lastName;
	private String image;
	private ArrayList<String> emails;
	private ArrayList<Integer> phones;

	public Contact(String id,String name, String lastName, String image, ArrayList<String> emails,ArrayList<Integer> phones){
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.image = image;
		this.emails = emails;
		this.phones	= phones;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public ArrayList<String> getEmails() {
		return emails;
	}

	public void setEmails(ArrayList<String> emails) {
		this.emails = emails;
	}

	public ArrayList<Integer> getPhones() {
		return phones;
	}

	public void setPhones(ArrayList<Integer> phones) {
		this.phones = phones;
	}
	
	
}
