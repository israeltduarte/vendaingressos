package vendaingressos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import vendaingressos.helper.ConnectionFactory;
import vendaingressos.model.Person;

public class PersonDAO {

	public static List<Person> getAll() {
		try {
			Connection c = ConnectionFactory.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM pessoa");
			ResultSet rs = ps.executeQuery();

			List<Person> p = new ArrayList<Person>();
			while (rs.next()) {
				p.add(new Person(rs.getInt("id"), rs.getString("name")));
			}

			rs.close();
			ps.close();
			c.close();
			return (p);
		} catch (NamingException | SQLException e) {
			return null;
		}
	}

	public static Person addPerson(Person p) {
		try {

			Connection c = ConnectionFactory.getConnection();
			PreparedStatement ps = c.prepareStatement("INSERT INTO pessoa (name) values (?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, p.getName());
			ps.execute();

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				p.setId(rs.getInt("id"));
			}

			c.close();
		} catch (NamingException | SQLException e) {
			return null;
		}

		return p;
	}

}