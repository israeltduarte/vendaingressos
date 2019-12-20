package vendaingressos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import vendaingressos.helper.ConnectionFactory;
import vendaingressos.model.Evento;
import vendaingressos.model.Lugar;
import vendaingressos.model.SetPriceDto;
import vendaingressos.model.Status;

public class LugarDAO {

	public static void insertAll(Evento e) {

		try {
			Connection c = ConnectionFactory.getConnection();
			int i = 0;

			PreparedStatement ps = c
					.prepareStatement("insert into lugar (id_evento, status, versao, numero) values (?,?,?,?)");
			ps.setLong(1, e.getId());
			ps.setString(2, Status.INDISPONIVEL.toString());
			ps.setInt(3, 1);

			for (; i < Integer.parseInt(e.getquantidadelugares()); i++) {
				ps.setInt(4, i + 1);
				ps.execute();
			}

		} catch (NamingException | SQLException e1) {
			System.out.println(e1.getMessage());
		}
	}

	public static boolean setPrice(int id_evento, SetPriceDto priceDto) {
		boolean result = false;

		try {
			Connection c = ConnectionFactory.getConnection();
			PreparedStatement ps = c.prepareStatement(
					"update lugar set preco = ?, status = ? where id_evento = ? and numero between ? and ?");

			ps.setDouble(1, priceDto.getPrice());
			ps.setString(2, Status.DISPONIVEL.toString());
			ps.setDouble(3, id_evento);
			ps.setDouble(4, priceDto.getBegin());
			ps.setDouble(5, priceDto.getEnd());

			result = ps.execute();
			c.close();
			ps.close();

		} catch (NamingException | SQLException e1) {
			System.out.println(e1.getMessage());
		}

		return !result;
	}

	public static List<Lugar> getAvailableSpotsByEvent(int eventId) {

		List<Lugar> listFree = new ArrayList<>();

		try {

			Connection c = ConnectionFactory.getConnection();
			PreparedStatement ps = c.prepareStatement(
					"select l.id, l.preco, l.versao, l.numero, l.id_evento from lugar l where l.status = 'DISPONIVEL' and l.id_evento = ? order by l.numero;");
			ps.setInt(1, eventId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Lugar l = new Lugar();
				l.setStatus(Status.DISPONIVEL);
				l.setId(rs.getInt("id"));
				l.setEvento(rs.getInt("id_evento"));
				l.setPreco(rs.getDouble("preco"));
				l.setVersao(rs.getInt("versao"));
				l.setNumero(rs.getInt("numero"));
				listFree.add(l);
			}

		} catch (NamingException | SQLException e1) {
			System.out.println(e1.getMessage());
		}

		return listFree;

	}

	public static boolean checkVersion(int versao, int id_evento, int numero) {
		try {
			Connection c = ConnectionFactory.getConnection();
			PreparedStatement ps = c.prepareStatement("select versao from lugar where id_evento = ? and numero = ?");
			ps.setInt(1, id_evento);
			ps.setInt(2, numero);

			ResultSet rs = ps.executeQuery();

			int versaoDB = 0;

			if (rs.next()) {
				versaoDB = rs.getInt(1);
			}

			return versaoDB == versao;

		} catch (NamingException | SQLException e1) {
			System.out.println(e1.getMessage());
		}

		return false;
	}

	public static int getVersion(int eventId, int spot) {
		int version = 0;

		try {
			Connection c = ConnectionFactory.getConnection();
			PreparedStatement ps = c.prepareStatement("select versao from lugar where id_evento = ? and numero = ?");

			ps.setInt(1, eventId);
			ps.setInt(2, spot);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				version = rs.getInt(1);
			}

			rs.close();
			ps.close();
			c.close();
		} catch (NamingException | SQLException e) {
			System.out.println(e.getMessage());
		}

		return version;
	}

	public static Lugar getSpot(int eventId, int spot) {
		Lugar l = null;

		try {
			Connection c = ConnectionFactory.getConnection();
			PreparedStatement ps = c.prepareStatement(
					"select *, e.id as id_evento from lugar l inner join evento e on l.id_evento = e.id where e.id = ? and l.numero = ?");

			ps.setInt(1, eventId);
			ps.setInt(2, spot);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				l = new Lugar();
				l.setEvento(rs.getInt("id_evento"));
				l.setId(rs.getInt("id"));
				l.setNumero(rs.getInt("numero"));
				l.setPreco(rs.getDouble("preco"));

				String status = rs.getString("status");

				Status[] a = Status.values();

				for (int i = 0; i < a.length; i++) {
					if (a[i].equals(status)) {
						l.setStatus(a[i]);
					}
				}

				l.setVersao(rs.getInt("versao"));
				l.setToken(new Token(rs.getString("token")));
			}

			rs.close();
			ps.close();
			c.close();
		} catch (NamingException | SQLException e1) {
			System.out.println(e1.getMessage());
		}

		return l;
	}

	public static boolean buySpot(int eventoId, int spot, String cpf) {
		int versao = 0;

		boolean result = false;

		Lugar l = LugarDAO.getSpot(eventoId, spot);
		if (l == null) {
			return result;
		}

		try {
			Connection c = ConnectionFactory.getConnection();

			versao = l.getVersao();
			versao++;

			PreparedStatement ps1 = c.prepareStatement("update lugar set versao = ?, status = ? where id = ?");

			ps1.setInt(1, versao);
			ps1.setString(2, Status.RESERVADO.toString());
			ps1.setInt(3, l.getId());
			result = ps1.execute();

			c.close();
			ps1.close();

		} catch (NamingException | SQLException e1) {
			System.out.println(e1.getMessage());
		}

		return !result;
	}

	public static Lugar updateSpot(Lugar spot) {

		int versao = LugarDAO.getVersion(spot.getEvento(), spot.getNumero());
		spot.setVersao(++versao);

		try {
			Connection c = ConnectionFactory.getConnection();

			versao = LugarDAO.getVersion(spot.getEvento(), spot.getNumero());

			PreparedStatement ps1 = c
					.prepareStatement("update lugar set versao = ?, status = ?, token = ? where id = ?");

			ps1.setInt(1, spot.getVersao());
			ps1.setString(2, spot.getStatus().toString());

			if (spot.getToken() == null) {
				ps1.setObject(3, null);
			} else {
				ps1.setString(3, spot.getToken().getToken());
			}

			ps1.setInt(4, spot.getId());

			ps1.execute();
			c.close();
			ps1.close();

		} catch (NamingException | SQLException e1) {
			System.out.println(e1.getMessage());
			return null;
		}

		// criptar o id do lugar + cpf para gerar o token

		return spot;
	}

	public static Lugar getLugarByToken(String token) {
		Lugar place = null;

		try {
			Connection c = ConnectionFactory.getConnection();
			PreparedStatement s = c.prepareStatement("select * from lugar where token = ?");
			s.setString(1, token);

			ResultSet rs = s.executeQuery();

			if (rs.next()) {
				place = new Lugar();

				String status = rs.getString("status");

				Status[] a = Status.values();

				for (int i = 0; i < a.length; i++) {
					if (a[i].toString().equals(status)) {
						place.setStatus(a[i]);
					}
				}
				
				place.setId(rs.getInt("id"));
				place.setEvento(rs.getInt("id_evento"));
				place.setVersao(rs.getInt("versao"));
				place.setNumero(rs.getInt("numero"));
				place.setToken(new Token(rs.getString("token")));
				place.setPreco(rs.getDouble("preco"));
			}

			rs.close();
			s.close();
			c.close();
		} catch (NamingException | SQLException e) {
			System.out.println(e);
		}

		return place;
	}

}
