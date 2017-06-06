package br.com.fiap.abstractdao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DaoFactoryImpl extends DaoFactory{
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/atividade5?createDatabaseIfNotExist=true";

	public static Connection abrirConexao() throws Exception{ 
		return DriverManager.getConnection(URL,"root","fiap");
	}

	@Override
	public ClienteDao getClienteDao() {
		return new ClienteDaoImpl();
	}

	@Override
	public PedidosDao getPedidosDao() {
		return new PedidosDaoImpl();
	}
}