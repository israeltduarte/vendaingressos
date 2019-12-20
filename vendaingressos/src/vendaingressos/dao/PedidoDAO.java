package vendaingressos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import vendaingressos.helper.ConnectionFactory;
import vendaingressos.model.Pedido;

public class PedidoDAO {

	public static List<Pedido> getAll() {
		List<Pedido> list = null;

		try {
			Connection c = ConnectionFactory.getConnection();
			PreparedStatement s = c.prepareStatement("select * from pedido");
			ResultSet rs = s.executeQuery();

			list = new ArrayList<>();

			while (rs.next()) {
				Pedido p = new Pedido();
				p.setId(rs.getInt("id"));
				p.setId_comprador(rs.getInt("id_comprador"));
				p.setId_evento(rs.getInt("id_evento"));
				p.setId_lugar(rs.getInt("id_lugar"));
				list.add(p);
			}

			rs.close();
			s.close();
			c.close();
		} catch (NamingException | SQLException e) {
			System.out.println(e);
		}

		return list;
	}

	public static List<Pedido> getPedidosByCPF(int idEvento, String cpf, String token) {
		List<Pedido> list = null;

		try {
			Connection c = ConnectionFactory.getConnection();
			PreparedStatement s = c.prepareStatement(
					"select * from pedido p inner join usuario u on p.id_comprador = u.id where u.cpf = ? and p.id_evento = ? and p.token = ?");
			s.setString(1, cpf);
			s.setInt(2, idEvento);
			s.setString(3, token);

			ResultSet rs = s.executeQuery();

			list = new ArrayList<>();

			while (rs.next()) {
				Pedido p = new Pedido();
				p.setId(rs.getInt("id"));
				p.setId_comprador(rs.getInt("id_comprador"));
				p.setId_evento(rs.getInt("id_evento"));
				p.setId_lugar(rs.getInt("id_lugar"));
				p.setToken(rs.getString("token"));
				list.add(p);
			}

			rs.close();
			s.close();
			c.close();
		} catch (NamingException | SQLException e) {
			System.out.println(e);
		}

		return list;
	}

	public static List<Pedido> getPedidosByCPF(String cpf) {
		List<Pedido> list = null;

		try {
			Connection c = ConnectionFactory.getConnection();
			PreparedStatement s = c.prepareStatement(
					"select * from pedido p inner join usuario u on p.id_comprador = u.id where u.cpf = ?");
			s.setString(1, cpf);

			ResultSet rs = s.executeQuery();

			list = new ArrayList<>();

			while (rs.next()) {
				Pedido p = new Pedido();
				p.setId(rs.getInt("id"));
				p.setId_comprador(rs.getInt("id_comprador"));
				p.setId_evento(rs.getInt("id_evento"));
				p.setId_lugar(rs.getInt("id_lugar"));
				p.setToken(rs.getString("token"));
				list.add(p);
			}

			rs.close();
			s.close();
			c.close();
		} catch (NamingException | SQLException e) {
			System.out.println(e);
		}

		return list;
	}

}
