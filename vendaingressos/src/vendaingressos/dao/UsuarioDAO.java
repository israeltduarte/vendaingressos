package vendaingressos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import vendaingressos.helper.ConnectionFactory;
import vendaingressos.model.Usuario;

public class UsuarioDAO {

	public static List<Usuario> getAll() {
		Connection c = null;
		Statement s = null;
		ResultSet rs = null;
		List<Usuario> lista = new ArrayList<Usuario>();

		try {

			c = ConnectionFactory.getConnection();
			if (c != null) {
				s = c.createStatement();
				rs = s.executeQuery("SELECT * FROM usuario");
				while (rs.next())
					lista.add(new Usuario(rs.getInt("id"), rs.getString("name")));
				rs.close();
				s.close();
			}
			c.close();
		} catch (Exception e) {
			System.out.println("pegou erro");
		}

		return (lista);
	}

	public static Usuario getUserByCPF(String cpf) {
		Usuario usuario = null;

		try {
			Connection c = ConnectionFactory.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM usuario where cpf = ?");
			ps.setString(1, cpf);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				usuario = new Usuario();
				usuario.setCpf(rs.getString("cpf"));
				usuario.setEmail(rs.getString("email"));
				usuario.setNome(rs.getString("nome"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setId(rs.getInt("id"));
			}
			rs.close();
			ps.close();
			c.close();

		} catch (Exception e) {
			System.out.println("pegou erro");
		}

		return usuario;
	}

	public static Usuario getUserById(int id) {
		Usuario usuario = null;

		try {
			Connection c = ConnectionFactory.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM usuario where id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				usuario = new Usuario();
				usuario.setCpf(rs.getString("cpf"));
				usuario.setEmail(rs.getString("email"));
				usuario.setNome(rs.getString("nome"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setId(rs.getInt("id"));
			}
			rs.close();
			ps.close();
			c.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return usuario;
	}

	public static boolean checkByCPF(String cpf) {

		boolean exists = false;

		try {
			Connection c = ConnectionFactory.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM usuario where cpf = ?");
			ps.setString(1, cpf);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				exists = true;
			}

			rs.close();
			ps.close();
			c.close();
		} catch (Exception e) {
			System.out.println("pegou erro");
		}

		return exists;

	}

	public static boolean authenticate(String username, String password) {
		boolean exists = false;

		try {
			Connection c = ConnectionFactory.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM usuario where nome = ? and senha = ?");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				exists = true;
			}

			rs.close();
			ps.close();
			c.close();
		} catch (Exception e) {
			System.out.println("usuario e senha nao conferem");
		}

		return exists;
	}

}
