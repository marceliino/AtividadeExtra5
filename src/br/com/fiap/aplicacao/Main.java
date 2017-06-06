package br.com.fiap.aplicacao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.fiap.abstractdao.ClienteDao;
import br.com.fiap.abstractdao.DaoFactory;
import br.com.fiap.abstractdao.PedidosDao;
import br.com.fiap.entity.Cliente;
import br.com.fiap.entity.Pedidos;

public class Main {

	public static void main(String[] args) {

		try {
			ClienteDao clientesDao = DaoFactory.getDaoFactory().getClienteDao();
			List<Pedidos> listaPedidos = new ArrayList<>();
			Cliente cliente = new Cliente();
			cliente.setNome("Marcelo SiLva");
			cliente.setEmail("marceliino1@gmail.com");
			clientesDao.inserirCliente(cliente);

			PedidosDao pedidosDao = DaoFactory.getDaoFactory().getPedidosDao();
			Pedidos pedido = new Pedidos();
			pedido.setData(new Date());
			pedido.setDescricao("Notebook Dell");
			pedido.setValor(9000);
			pedido.setIdCliente(cliente.getId());
			pedidosDao.incluirPedido(pedido);

			listaPedidos = pedidosDao.listarPedidos(cliente.getId());
			for (Pedidos pedidos : listaPedidos) {
				System.out.println(cliente.getNome() +  " - " + pedidos.getDescricao());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}