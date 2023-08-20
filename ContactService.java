package main;

import java.util.Map;
import java.util.HashMap;

public class ContactService {
	private Map<String, Contact> contacts;
	
	public ContactService() {
		this.contacts = new HashMap<>();
	}
	
	public void addContact(Contact contact) {
		contacts.put(contact.getContactId(), contact);
	}
	
	public Contact updateContact(String contactId, String firstName, String lastName, String phone, String address) {
		Contact contact = contacts.get(contactId);
		
		if(contact != null) {
			contact.setFirstName(firstName);
			contact.setLastName(lastName);
			contact.setPhone(phone);
			contact.setAddress(address);
		}
		return contact;
	}
	
	public void deleteContact(String contactId) {
		contacts.remove(contactId);
	}
	
	public Contact getContact(String contactId) {
		return contacts.get(contactId);
	}
}
