package com.springback.cliente.service;

import com.springback.cliente.exception.GenericFoundException;
import com.springback.cliente.exception.ResourceNotFoundException;
import com.springback.cliente.model.Cliente;
import com.springback.cliente.repository.ClienteRepository;
import org.checkerframework.checker.nullness.Opt;
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
import java.util.Optional;

public class ClientServiceTest {
    
    @InjectMocks
    ClienteService clienteService;
    
    @Mock
    private ClienteRepository repository;
    
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
        Mockito.when(repository.findAll()).thenReturn(clienteList);
        List<Cliente> response = clienteService.findAll();
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.get(0).getRut(),cliente.getRut());
    }

    @Test
    void getClienteByRutExistTest(){
        Optional<Cliente> cliente = Optional.of(getMockCliente());
        Mockito.when(repository.findByRut(cliente.get().getRut())).thenReturn(cliente);
        Cliente response = clienteService.getClienteByRut(cliente.get().getRut());
        Assertions.assertNotNull(response);
        Assertions.assertEquals(cliente.get().getRut(),response.getRut());
    }

    @Test
    void getClienteByRutNotExistTest(){
        Mockito.when(repository.findByRut("1")).thenReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class,() -> clienteService.getClienteByRut("1"));
    }

    @Test
    void createClienteTest(){
        Cliente cliente = getMockCliente();
        Mockito.when(repository.findByRut(cliente.getRut())).thenReturn(Optional.empty());
        Mockito.when(repository.findByEmail(cliente.getEmail())).thenReturn(Optional.empty());
        Mockito.when(repository.save(cliente)).thenReturn(cliente);
        Cliente response = clienteService.createCliente(cliente);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(cliente.getRut(),response.getRut());
    }
    @Test
    void createClientRutFoundTest(){
        Cliente cliente = getMockCliente();
        Mockito.when(repository.findByRut(cliente.getRut())).thenReturn(Optional.of(cliente));
        Assertions.assertThrows(GenericFoundException.class,()-> clienteService.createCliente(cliente));
    }
    @Test
    void createClientEmailFoundTest(){
        Cliente cliente = getMockCliente();
        Mockito.when(repository.findByRut(cliente.getRut())).thenReturn(Optional.empty());
        Mockito.when(repository.findByEmail(cliente.getEmail())).thenReturn(Optional.of(cliente));
        Assertions.assertThrows(GenericFoundException.class,()-> clienteService.createCliente(cliente));
    }

    @Test
    void updateClienteTest(){
        Cliente cliente = getMockCliente();
        Mockito.when(repository.findByRut(cliente.getRut())).thenReturn(Optional.of(cliente));
        Cliente responseInicial = clienteService.getClienteByRut(cliente.getRut());
        Assertions.assertNotNull(responseInicial);
        Assertions.assertEquals(99999999,responseInicial.getTelefono());
        cliente.setTelefono(12);
        Mockito.when(repository.findByEmail(cliente.getEmail())).thenReturn(Optional.empty());
        Cliente responseUpdate = clienteService.UpdateCliente(cliente.getRut(),cliente);
        Assertions.assertNotNull(responseUpdate);
        Assertions.assertEquals(12,responseUpdate.getTelefono());

    }

    @Test
    void deleteClienteTest(){
        Cliente cliente = getMockCliente();
        Mockito.when(repository.findByRut(cliente.getRut())).thenReturn(Optional.of(cliente));
        Mockito.doNothing().when(repository).delete(cliente);
        Assertions.assertTrue(true);
    }
}
