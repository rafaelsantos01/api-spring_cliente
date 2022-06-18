package br.com.ticket.cliente.http.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.ticket.cliente.entity.Cliente;
import br.com.ticket.cliente.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente salvar(@RequestBody Cliente cliente) {
		return clienteService.salvar(cliente);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Cliente> listaCliente(){
		return clienteService.listaCliente();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Cliente buscarClientePorId(@PathVariable Long id) {
		return clienteService.buscarPorId(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente Não encontrado."));
	}
	
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removerCliente(@PathVariable Long id){
		clienteService.buscarPorId(id)
				.map(cliente -> {
					clienteService.removerPorId(cliente.getId());
					return Void.TYPE;
				}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente Não encontrado."));
	}
	
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void atualizarCliente(@PathVariable Long id,@RequestBody Cliente cliente) {
		clienteService.buscarPorId(id)
			.map(clienteBase -> {
				modelMapper.map(cliente, clienteBase);
				clienteService.salvar(clienteBase);
				return Void.TYPE;
			}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente Não encontrado."));
	}
	
	
	
}
