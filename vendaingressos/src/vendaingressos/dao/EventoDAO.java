package vendaingressos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.naming.NamingException;

import vendaingressos.helper.ConnectionFactory;
import vendaingressos.model.Evento;

public class EventoDAO {

	public static ArrayList<Evento> getAll() {
		ArrayList<Evento> l = new ArrayList<>();

		try {
			Connection c = ConnectionFactory.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM evento");

			Evento e = null;
			while (rs.next()) {
				e = new Evento();
				e.setId(rs.getInt("id"));
				e.setDescricao(rs.getString("descricao"));
				e.setDataHora(LocalDateTime.from(rs.getObject("datahora", LocalDateTime.class)));
				e.setLocal(rs.getString("local"));
				e.setquantidadelugares(String.valueOf(rs.getInt("quantidadelugares")));
				e.setInicioVenda(LocalDateTime.from(rs.getObject("iniciovenda", LocalDateTime.class)));
				e.setFimVenda(LocalDateTime.from(rs.getObject("fimvenda", LocalDateTime.class)));
				l.add(e);
			}

			rs.close();
			s.close();
			c.close();
		} catch (NamingException | SQLException e) {
			return null;
		}

		return l;
	}

	@PermitAll
	public static Evento addEvento(Evento e) {

		try {
			Connection c = ConnectionFactory.getConnection();
			PreparedStatement ps = c.prepareStatement(
					"insert into evento (descricao, datahora, local, quantidadelugares, iniciovenda, fimvenda) values (?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, e.getDescricao());
			ps.setObject(2, e.getDataHora());
			ps.setString(3, e.getLocal());

			Integer quantidadelugares = Integer.parseInt(e.getquantidadelugares());

			ps.setInt(4, quantidadelugares);
			ps.setObject(5, e.getInicioVenda());
			ps.setObject(6, e.getFimVenda());

			ps.execute();

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				e.setId(rs.getInt("id"));
			}

			LugarDAO.insertAll(e);
			ps.close();
			c.close();
			return e;
		} catch (NamingException | SQLException e1) {
			System.out.println(e1.getMessage());
			return null;
		}
	}

	public static boolean checkById(int id) {
		boolean exist = false;

		try {
			Connection c = ConnectionFactory.getConnection();
			PreparedStatement ps = c.prepareStatement("select * from evento where id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			exist = rs.next();

			ps.close();
			c.close();
		} catch (NamingException | SQLException e1) {
			System.out.println(e1.getMessage());
		}

		return exist;
	}

	public static int getQuantidadeLugares(int id) {
		try {

			Connection c = ConnectionFactory.getConnection();
			PreparedStatement ps = c.prepareStatement("select quantidadelugares from evento where id = ?");
			ResultSet rs = null;
			int qtdade = 0;
			ps.setLong(1, id);

			boolean execute = ps.execute();

			rs = ps.getResultSet();

			if (rs.next()) {
				qtdade = rs.getInt("quantidadelugares");
			}
			return qtdade;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public static List<Evento> getEventWithfilter(String sql) {

		List<Evento> eventList = new ArrayList<>();
		try {
			Connection c = ConnectionFactory.getConnection();
			ResultSet rs = null;
			PreparedStatement ps = c.prepareStatement(sql);
			ps.execute();
			rs = ps.getResultSet();

			while (rs.next()) {
				Evento e = new Evento();
				e.setId(rs.getInt("id"));
				e.setDescricao(rs.getString("descricao"));
				e.setDataHora(LocalDateTime.from(rs.getObject("datahora", LocalDateTime.class)));
				e.setLocal(rs.getString("local"));
				e.setquantidadelugares(String.valueOf(rs.getInt("quantidadelugares")));
				e.setInicioVenda(LocalDateTime.from(rs.getObject("iniciovenda", LocalDateTime.class)));
				e.setFimVenda(LocalDateTime.from(rs.getObject("fimvenda", LocalDateTime.class)));
				eventList.add(e);
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}

		return eventList;
	}

}
