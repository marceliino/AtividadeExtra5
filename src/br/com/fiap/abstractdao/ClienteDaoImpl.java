package br.com.fiap.abstractdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.entity.Cliente;
import br.com.fiap.entity.Pedidos;

public class ClienteDaoImpl implements ClienteDao{

	Connection cn = null;
	PreparedStatement stmt;
	ResultSet rs = null;

	@Override
	public void inserirCliente(Cliente cliente) throws Exception{

		try {
			cn = DaoFactoryImpl.abrirConexao();
			stmt = cn.prepareStatement("INSERT INTO CLIENTES (NOME, EMAIL) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getEmail());
			stmt.execute();
			rs = stmt.getGeneratedKeys();
			
			while(rs.next()) {
				cliente.setId(rs.getInt(1));;
			}
		} catch (Exception e) { 
			throw e;
		} finally { 
			cn.close();
			if (stmt != null) stmt.close();
		} 
	}
	
	@Override
	public Cliente buscarCliente(int id) throws Exception { 

		Cliente cliente = null;
		List<Pedidos> pedidos = new ArrayList<>();
		try {
			cn=DaoFactoryImpl.abrirConexao();
			String sql="SELECT ID,DATA,DESCRICAO,VALOR FROM PEDIDOS WHERE IDCLIENTE=?";
			stmt = cn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while (rs.next()){
				pedidos.add(new Pedidos(rs.getDate("DATA"),rs.getString("DESCRICAO"), rs.getDouble("VALOR"),
						rs.getInt("ID"), id));
			}

			sql="SELECT NOME,EMAIL FROM CLIENTES WHERE ID=?";
			stmt = cn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()){
				cliente = new Cliente(rs.getString("NOME"), rs.getString("EMAIL"), pedidos);
			}
		} catch (Exception e) {
			throw e;
		}
		finally{
			cn.close();
			stmt.close();
			if (stmt != null) stmt.close();
			if (rs != null) rs.close();
		}
		return cliente;

	}

	@Override
	public void removerCliente(int id) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public Cliente alterarCliente(Cliente cliente) throws Exception {
		// TODO Auto-generated method stub
		return null;
	} 
}