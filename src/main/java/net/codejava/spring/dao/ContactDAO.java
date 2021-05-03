package net.codejava.spring.dao;
 
import java.util.List;
 
import net.codejava.spring.model.Contact;
 
/**
 * Defines DAO operations for the contact model.
 * @author www.codejava.net
 *
 */
public interface ContactDAO {
     
    public int saveOrUpdate(Contact contact);
     
    public int delete(int contactId);
     
    public Contact get(int contactId);
     
    public List<Contact> list();
}