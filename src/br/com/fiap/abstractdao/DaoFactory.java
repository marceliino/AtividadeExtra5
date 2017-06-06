package br.com.fiap.abstractdao;

public abstract class DaoFactory {

	public abstract ClienteDao getClienteDao(); 
	public abstract PedidosDao getPedidosDao();

	public static DaoFactory getDaoFactory() { 
		return new DaoFactoryImpl();
	} 
}