package br.com.safv.jwcamelo.DAO;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
	public DataSource ds;
	
	public ConnectionFactory() {
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		cpds.setJdbcUrl("jdbc:mysql://localhost/SAFV?useTimezone=true&serverTimezone=true&serverTimezone=UTC");
		cpds.setUser("root");
		cpds.setPassword("");
		cpds.setMaxPoolSize(15);
		
		this.ds = cpds;
	}
	
	public Connection recuperarConexao() throws SQLException{
		return ds.getConnection();
	}
}
