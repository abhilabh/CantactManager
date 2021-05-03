package net.codejava.spring.dao;
 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
 
import javax.sql.DataSource;
 
import net.codejava.spring.model.Contact;
 
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
 
/**
 * An implementation of the ContactDAO interface.
 * @author www.codejava.net
 *
 */
public class ContactDAOImpl implements ContactDAO {
 
    private JdbcTemplate jdbcTemplate;
 
    public ContactDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
 
    public int saveOrUpdate(Contact contact) {
        // implementation details goes here...
    	int res =0;
    	if (contact.getId() > 0) {
            // update
            String sql = "UPDATE contactlist SET name=?, email=?, address=?, "
                        + "telephone=? WHERE contact_id=?";
            res = jdbcTemplate.update(sql, contact.getName(), contact.getEmail(),
                    contact.getAddress(), contact.getTelephone(), contact.getId());
        } else {
            // insert
            String sql = "INSERT INTO contactlist (name, email, address, telephone)"
                        + " VALUES (?, ?, ?, ?)";
            res = jdbcTemplate.update(sql, contact.getName(), contact.getEmail(),
                    contact.getAddress(), contact.getTelephone());
        }
    	return res;
    }
 
    public int  delete(int contactId) {
    	String sql = "DELETE FROM contactList WHERE contact_id=?";
        return jdbcTemplate.update(sql, contactId);
    }
 
    public List<Contact> list() {
    	String sql = "SELECT * FROM contactList";
        List<Contact> listContact = jdbcTemplate.query(sql, new RowMapper<Contact>() {
     
            public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
                Contact aContact = new Contact();
     
                aContact.setId(rs.getInt("contact_id"));
                aContact.setName(rs.getString("name"));
                aContact.setEmail(rs.getString("email"));
                aContact.setAddress(rs.getString("address"));
                aContact.setTelephone(rs.getString("telephone"));
     
                return aContact;
            }
     
        });
     
        return listContact;
    }
 
    public Contact get(int contactId) {
    	String sql = "SELECT * FROM contactList WHERE contact_id=" + contactId;
        return jdbcTemplate.query(sql, new ResultSetExtractor<Contact>() {
     
            public Contact extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {
                    Contact contact = new Contact();
                    contact.setId(rs.getInt("contact_id"));
                    contact.setName(rs.getString("name"));
                    contact.setEmail(rs.getString("email"));
                    contact.setAddress(rs.getString("address"));
                    contact.setTelephone(rs.getString("telephone"));
                    return contact;
                }
     
                return null;
            }
     
        });
    }
 
}
