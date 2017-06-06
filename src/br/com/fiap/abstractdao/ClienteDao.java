package br.com.fiap.abstractdao;

import br.com.fiap.entity.Cliente;

public interface ClienteDao {
	void inserirCliente(Cliente cliente) throws Exception;
	void removerCliente(int id) throws Exception;
	Cliente buscarCliente(int id) throws Exception;
	Cliente alterarCliente(Cliente cliente) throws Exception;
}