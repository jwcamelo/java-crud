package br.com.safv.jwcamelo.test;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.safv.jwcamelo.DAO.ConnectionFactory;

public class TesteConexao {
	public static void main(String[] args) throws SQLException {

		ConnectionFactory ConnectionFactory = new ConnectionFactory();
		Connection connection = ConnectionFactory.recuperarConexao();

		System.out.println("fechando conexao");
		connection.close();
	}
}
