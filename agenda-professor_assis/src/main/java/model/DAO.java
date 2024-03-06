package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class DAO {

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/dbagenda?userTimezone=true&Timezone=UTC";
	private String senha = "Root@123";
	private String usuario = "root";

	private Connection conectar() {

		Connection con = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, usuario, senha);

			return con;

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}

	public void testarconexao() {
		try {
			Connection con = conectar();
			System.out.println(con);

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	// crud create
	public void inserirContato(JavaBeans contato) {

		Connection con = conectar();
		String sql = " insert into contatos (nome,fone,email) values (?,?,?)";

		try {

			// Preparar a query para execução no banco de dados
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getFone());
			stmt.setString(3, contato.getEmail());
			stmt.executeUpdate();
			con.close();
			System.out.println("cadatrado");
			JOptionPane.showMessageDialog(null, "Cadastrado com suscesso!");

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public ArrayList<JavaBeans> listaContatos() {

		ArrayList<JavaBeans> contatos = new ArrayList<>();

		String sql = " select * from contatos order by nome";
		try {

			Connection con = conectar();

			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				String idcon = rs.getString(1);
				String nome = rs.getString(2);
				String fone = rs.getString(3);
				String email = rs.getString(4);

				contatos.add(new JavaBeans(idcon, nome, fone, email));
			}

			return contatos;

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}

//**CRUD UPDATE**//
	// selecionar o contato
	public void selecionarContato(JavaBeans contato) {

		String sql = "select * from contatos where idcon=?";

		try {
			Connection con = conectar();

			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, contato.getIdcon());

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				contato.setIdcon(rs.getString(1));
				contato.setNome(rs.getString(2));
				contato.setFone(rs.getString(3));
				contato.setEmail(rs.getString(4));

			}
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

//** Alterar Contato
	public void alterarContato(JavaBeans contato) {
		// **
		String sql = "update contatos set nome=?,fone=?,email=? where idcon=?";

		try {
			Connection con = conectar();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, contato.getNome());
			
			System.out.println("nome é:" + contato.getNome());
			
			stmt.setString(2, contato.getFone());			
			System.out.println("fone é:" + contato.getFone());
			
			stmt.setString(3, contato.getEmail());
			
			System.out.println("email"+contato.getEmail());
			stmt.setString(4, contato.getIdcon());
			System.out.println(contato.getIdcon());
			stmt.executeUpdate();

			con.close();
		}

		catch (Exception e) {
			System.out.println(e);
		}

	}
	
	public void deletarContato(JavaBeans contato) {
		
		String sql = "delete from contatos where idcon=?";
		
		try {
			Connection con=conectar();
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, contato.getIdcon());
			stmt.executeUpdate();
			con.close();
			
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
}
