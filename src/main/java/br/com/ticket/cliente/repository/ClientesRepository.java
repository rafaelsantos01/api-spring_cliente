package br.com.ticket.cliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ticket.cliente.entity.Cliente;

public interface ClientesRepository extends JpaRepository<Cliente, Long>{

}
