package vendaingressos.helper;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionFactory {

	public static Connection getConnection() throws NamingException, SQLException {
		DataSource ds = (DataSource) new InitialContext().lookup("vendaingressosresource");
		Connection c = ds.getConnection();

		return c;
	}

}
