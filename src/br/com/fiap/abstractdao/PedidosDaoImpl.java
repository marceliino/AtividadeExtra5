package br.com.fiap.abstractdao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.entity.Pedidos;

public class PedidosDaoImpl implements PedidosDao{
	

	Connection cn = null;
	PreparedStatement stmt;
	ResultSet rs = null;
	
	@Override
	public void incluirPedido(Pedidos pedido) throws Exception { 
		
		try {
			cn=DaoFactoryImpl.abrirConexao();
			
			String sql="INSERT INTO PEDIDOS (IDCLIENTE,DATA,DESCRICAO,VALOR) VALUES (?,?,?,?)";
			stmt = cn.prepareStatement(sql);
			stmt.setInt(1, pedido.getIdCliente());
			stmt.setDate(2, new Date(pedido.getData().getTime()));
			stmt.setString(3, pedido.getDescricao());
			stmt.setDouble(4, pedido.getValor());
			stmt.execute();
			
			sql="SELECT LAST_INSERT_ID()";
			stmt = cn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next()){
				pedido.setId(rs.getInt(1));
			}
		} catch (Exception e) {
			throw e;
		}
		finally{
			cn.close();
			if (stmt != null) stmt.close();
			if (rs != null) rs.close();
 		}
	}
	
	@Override
	public List<Pedidos> listarPedidos(int idCliente) throws Exception { 
		List<Pedidos> pedidos = new ArrayList<>();
		
		try {
			cn=DaoFactoryImpl.abrirConexao();
			String sql="SELECT id,idcliente, data, descricao, valor FROM PEDIDOS WHERE idcliente=?";
			stmt = cn.prepareStatement(sql);
			stmt.setInt(1, idCliente);
			rs = stmt.executeQuery();
			while (rs.next()){
				pedidos.add(new Pedidos(rs.getDate("DATA"),rs.getString("DESCRICAO"), rs.getDouble("VALOR"),
						rs.getInt("ID"), idCliente));
			}
		} catch (Exception e) {
			throw e;
		}
		finally{
			cn.close();
			if (stmt != null) stmt.close();
			if (rs != null) rs.close();
		}
		return pedidos;
	}

	@Override
	public void removerPedido(int id) throws Exception {
	}
}