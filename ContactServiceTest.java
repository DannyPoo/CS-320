package test;

import main.Contact;
import main.ContactService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactServiceTest {
private ContactService contactService;
	
	@BeforeEach
	public void setUp() {
		contactService = new ContactService();
	}
	
	@Test
	public void testAddContact() {
		Contact contact = new Contact("TestName", "TestLast", "1234567890", "address");
		contactService.addContact(contact);
		Contact retrievedContact = contactService.getContact(contact.getContactId());
		assertEquals(contact, retrievedContact);
	}
	
	@Test
	public void testUpdateContact() {
		Contact contact = new Contact("TestName", "TestLast", "1234567890", "address");
		contactService.addContact(contact);
		
		contactService.updateContact(contact.getContactId(), "NewName", "NewLast", "0987654321", "NewAddress");
		
		Contact updatedContact = contactService.getContact(contact.getContactId());
		assertEquals("NewName", updatedContact.getFirstName());
		assertEquals("NewLast", updatedContact.getLastName());
		assertEquals("0987654321", updatedContact.getPhone());
		assertEquals("NewAddress", updatedContact.getAddress());
	}
	
	@Test
	public void testDeleteContact() {
		Contact contact = new Contact("TestName", "TestLast", "1234567890", "address");
		contactService.addContact(contact);
		contactService.deleteContact(contact.getContactId());
		assertNull(contactService.getContact(contact.getContactId()));
	}
	
	@Test
	public void testContactProperties() {
		Contact contact = new Contact("TestName", "TestLast", "1234567890", "address");
		assertEquals("TestName", contact.getFirstName());
		assertEquals("TestLast", contact.getLastName());
		assertEquals("1234567890", contact.getPhone());
		assertEquals("address", contact.getAddress());
	}
	
	@Test
	public void testInvalidContactCreation() {
		assertThrows(IllegalArgumentException.class, () -> {new Contact(null, "LastName", "0123456789", "address"); });
		assertThrows(IllegalArgumentException.class, () -> {new Contact("FirstName", null, "0123456789", "address"); });
		assertThrows(IllegalArgumentException.class, () -> {new Contact("FirstName", "LastName", null, "address"); });
		assertThrows(IllegalArgumentException.class, () -> {new Contact("FirstName", "LastName", "0123456789", null); });
		assertThrows(IllegalArgumentException.class, () -> {new Contact("FirstNameIsWayTooLongForCharacterLimit", "lastName", "0123456789", "address"); });
		assertThrows(IllegalArgumentException.class, () -> {new Contact("FirstName", "LastNameIsWayTooLongForCharacterLimit", "0123456789", "address"); });
		assertThrows(IllegalArgumentException.class, () -> {new Contact("FirstName", "LastName", "10123456789", "address"); });
		assertThrows(IllegalArgumentException.class, () -> {new Contact("FirstName", "LastName", "0123456789", "AddressIsWayTooLongForTheCharacterLimit"); });
	}
	
	@Test
	public void testInvalidContactUpdate() {
		Contact contact = new Contact("TestName", "TestLast", "1234567890", "address");
		contactService.addContact(contact);
		
		assertThrows(IllegalArgumentException.class, () -> {contactService.updateContact(contact.getContactId(), null, "LastName", "0123456789", "address"); });
		assertThrows(IllegalArgumentException.class, () -> {contactService.updateContact(contact.getContactId(), "FirstName", null, "0123456789", "address"); });
		assertThrows(IllegalArgumentException.class, () -> {contactService.updateContact(contact.getContactId(), "FirstName", "LastName", null, "address"); });
		assertThrows(IllegalArgumentException.class, () -> {contactService.updateContact(contact.getContactId(), "FirstName", "LastName", "0123456789", null); });
		assertThrows(IllegalArgumentException.class, () -> {contactService.updateContact(contact.getContactId(), "FirstNameIsWayTooLongForCharacterLimit", "lastName", "0123456789", "address"); });
		assertThrows(IllegalArgumentException.class, () -> {contactService.updateContact(contact.getContactId(), "FirstName", "LastNameIsWayTooLongForCharacterLimit", "0123456789", "address"); });
		assertThrows(IllegalArgumentException.class, () -> {contactService.updateContact(contact.getContactId(), "FirstName", "LastName", "10123456789", "address"); });
		assertThrows(IllegalArgumentException.class, () -> {contactService.updateContact(contact.getContactId(), "FirstName", "LastName", "0123456789", "AddressIsWayTooLongForTheCharacterLimit"); });
	}
}
