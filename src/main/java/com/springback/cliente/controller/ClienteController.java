package com.springback.cliente.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springback.cliente.model.Cliente;
import com.springback.cliente.service.ClienteService;

@RestController
@RequestMapping("/api/v1")
public class ClienteController {

	private final ClienteService clienteService;

	@Autowired
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@GetMapping(value = "/clientes")
	public ResponseEntity<List<Cliente>> getAllClientes(){
		return ResponseEntity.ok(clienteService.findAll());
	}

	@GetMapping(value = "/cliente/{rut}")
	public ResponseEntity<Cliente> getCliente(@PathVariable String rut){
		Cliente getCliente =clienteService.getClienteByRut(rut);
		return ResponseEntity.ok(getCliente);
	}

	@PostMapping(value = "/cliente/crear")
	public ResponseEntity<Cliente> saveCliente(@RequestBody Cliente cliente){
		Cliente response = clienteService.createCliente(cliente);
		return ResponseEntity.ok(response);
	}

	@PutMapping(value = {"/cliente/{rut}"})
	public ResponseEntity<Cliente> updateCliente(@PathVariable String rut, Cliente cliente){
		Cliente response = clienteService.UpdateCliente(rut, cliente);
		return ResponseEntity.ok(response);
	}

	
	@DeleteMapping(value = "/cliente/{rut}")
	public ResponseEntity<Boolean> deleteCliente (@PathVariable String rut){
		Boolean response = clienteService.deleteCliente(rut);
		return ResponseEntity.ok(response);
	}
	
}
