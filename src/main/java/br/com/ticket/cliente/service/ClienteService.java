package br.com.ticket.cliente.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ticket.cliente.entity.Cliente;
import br.com.ticket.cliente.repository.ClientesRepository;

@Service
public class ClienteService {

	@Autowired
	private ClientesRepository clientesRepository;
	
	
	public Cliente salvar(Cliente cliente) {
		return clientesRepository.save(cliente);
	}
	
	public  List<Cliente> listaCliente(){
		return clientesRepository.findAll();
	}
	
	public Optional<Cliente> buscarPorId(Long id){
		return clientesRepository.findById(id);
	}
	
	public void removerPorId(long id) {
		clientesRepository.deleteById(id);
	}
	
	
}
