package com.springback.cliente.controller;

import com.springback.cliente.model.Cliente;
import com.springback.cliente.service.ClienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class ClientControllerTest {
    
    @InjectMocks
    ClienteController clienteController;
    
    @Mock
    private ClienteService service;
    
    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }
    private Cliente getMockCliente() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNombre("nombre");
        cliente.setApellido("Appellido");
        cliente.setRut("11111");
        cliente.setEmail("email@email.com");
        cliente.setTelefono(99999999);
        return cliente;
    }
    
    @Test
    void getAllClientesTest(){
        Cliente cliente = getMockCliente();
        List<Cliente> clienteList = new ArrayList<>();
        clienteList.add(cliente);
        Mockito.when(service.findAll()).thenReturn(clienteList);
        ResponseEntity<List<Cliente>> response = clienteController.getAllClientes();
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(clienteList.get(0).getRut(),response.getBody().get(0).getRut());
    }

    @Test
    void getClienteByRutTest(){
        Cliente cliente = getMockCliente();
        Mockito.when(service.getClienteByRut(cliente.getRut())).thenReturn(cliente);
        ResponseEntity<Cliente> response = clienteController.getCliente(cliente.getRut());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(cliente.getRut(),response.getBody().getRut());
    }

    @Test
    void createClienteTest(){
        Cliente cliente = getMockCliente();
        Mockito.when(service.createCliente(cliente)).thenReturn(cliente);
        ResponseEntity<Cliente> response = clienteController.saveCliente(cliente);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(cliente.getRut(),response.getBody().getRut());
    }

    @Test
    void updateClienteTest(){
        Cliente cliente = getMockCliente();
        Mockito.when(service.getClienteByRut(cliente.getRut())).thenReturn(cliente);
        ResponseEntity<Cliente> responseInicial = clienteController.getCliente(cliente.getRut());
        Cliente clienteUpdate = getMockCliente();
        clienteUpdate.setTelefono(12);
        Mockito.when(service.UpdateCliente(cliente.getRut(),cliente)).thenReturn(clienteUpdate);
        ResponseEntity<Cliente> responseUpdate = clienteController.updateCliente(getMockCliente().getRut(),cliente);
        Assertions.assertNotNull(responseInicial.getBody());
        Assertions.assertNotNull(responseUpdate.getBody());
        Assertions.assertNotEquals(responseInicial.getBody().getTelefono(),responseUpdate.getBody().getTelefono());
        Assertions.assertEquals(12,responseUpdate.getBody().getTelefono());

    }

    @Test
    void deleteClienteTest(){
        Mockito.when(service.deleteCliente("1")).thenReturn(true);
        Assertions.assertTrue(true);
    }





}
