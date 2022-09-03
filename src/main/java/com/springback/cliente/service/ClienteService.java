package com.springback.cliente.service;

import com.springback.cliente.config.ErrorCode;
import com.springback.cliente.exception.GenericFoundException;
import com.springback.cliente.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springback.cliente.model.Cliente;
import com.springback.cliente.repository.ClienteRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
	private final ClienteRepository clienteRepository;

	@Autowired
	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	public Cliente createCliente(Cliente cliente){
		Optional<Cliente> getFoundRut= clienteRepository.findByRut(cliente.getRut());
		if(getFoundRut.isPresent())
			throw new GenericFoundException(ErrorCode.RUT_FOUND, cliente.getRut());
		Optional<Cliente> getMailFound = clienteRepository.findByEmail(cliente.getEmail());
		if(getMailFound.isPresent())
			throw new GenericFoundException(ErrorCode.EMAIL_FOUND, cliente.getEmail());
		return clienteRepository.save(cliente);
	}

	public List<Cliente> findAll(){
		return clienteRepository.findAll();
	}
	public Cliente getClienteByRut(String rut) {
		return clienteRepository.findByRut(rut).orElseThrow(() -> new ResourceNotFoundException(ErrorCode.CLIENTE_NOT_FOUND, rut));
	}
	@Transactional
	public Cliente UpdateCliente(String rut, Cliente cliente){
		Cliente findClienteUpdate = getClienteByRut(rut);
		if(cliente.getNombre()!=null) {
			findClienteUpdate.setNombre(cliente.getNombre());
		}
		if(cliente.getApellido()!=null) {
			findClienteUpdate.setApellido(cliente.getApellido());
		}
		Optional<Cliente> getMailFound = clienteRepository.findByEmail(cliente.getEmail());
		if(getMailFound.isPresent())
			throw new GenericFoundException(ErrorCode.EMAIL_UPDATE_FOUND, cliente.getEmail());
		if(cliente.getEmail()!=null){
			findClienteUpdate.setEmail(cliente.getEmail());
		}
		if(cliente.getTelfono()!=null) {
			findClienteUpdate.setTelfono(cliente.getTelfono());
		}
		if(cliente.getEdad()!=null){
			findClienteUpdate.setEdad(cliente.getEdad());
		}
		return findClienteUpdate;

	}
	public Boolean deleteCliente(String rut){
		Cliente findClienteDelete = getClienteByRut(rut);
		clienteRepository.delete(findClienteDelete);
		return clienteRepository.findByRut(rut).isEmpty();
	}




}
