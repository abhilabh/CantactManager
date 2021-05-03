package net.codejava.spring.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import net.codejava.spring.model.Contact;

class ContactDAOTest {
	private DriverManagerDataSource dataSource;
	private ContactDAO dao;
	
	@BeforeEach
	public void setupSetupBeforeEach(){
		dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		dataSource.setUrl("jdbc:sqlserver://localhost:1433;databaseName=Customer");
		dataSource.setUsername("sa");
		dataSource.setPassword("labhlabh");
		dao = new ContactDAOImpl(dataSource);
	}
	
	@Test
	void testSave() {
		
		Contact contact = new Contact("abhi","a@gmail.com", "banaglore","789897999");
		 int ret = dao.saveOrUpdate(contact);
		 assertTrue(ret>0);
	}
	
	@Test
	void testUpdate() {
		Contact contact = new Contact(1, "Abhi","R@gmail.com", "pune","789897999");
		 int ret = dao.saveOrUpdate(contact);
		 assertTrue(ret>0);
	}
	

	@Test
	void testDelete() {
		int id = 1;
		 int ret = dao.delete(id);
		 assertTrue(ret>0);
	}

	@Test
	void testGet() {
		Integer id =1;
		Contact contact = dao.get(id);
		if(contact!=null) System.out.println(contact);
		assertNotNull(contact);
	}

	@Test
	void testList() {
		List<Contact> list = dao.list();
		list.forEach(System.out :: println);
		assertTrue(!list.isEmpty());
	}

}
