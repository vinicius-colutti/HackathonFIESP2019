package br.com.code.traumatec.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

	public static Connection conectar() throws Exception {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

			conn = DriverManager.getConnection("jdbc:mysql://db-fiesphack.mysql.uhserver.com:3306/db_fiesphack?useSSL=false&useTimezone=true&serverTimezone=UTC", "codestage",
					"x3Re61f@");
			return conn;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	public static void desconectar(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
