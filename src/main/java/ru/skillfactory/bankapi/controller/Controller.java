package ru.skillfactory.bankapi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.skillfactory.bankapi.model.Client;
import ru.skillfactory.bankapi.model.Operation;
import ru.skillfactory.bankapi.repository.ClientRepository;
import ru.skillfactory.bankapi.repository.OperationRepository;
import ru.skillfactory.bankapi.service.ClientService;
import ru.skillfactory.bankapi.service.ClientServiceImpl;
import ru.skillfactory.bankapi.service.OperationService;
import ru.skillfactory.bankapi.service.OperationServiceImpl;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/bank-api")
public class Controller {
    private final ClientService clientService = new ClientServiceImpl();
    private final OperationService operationService = new OperationServiceImpl();

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private OperationRepository operationRepository;

    @GetMapping(value = "/")
    public String getPage(){
        return "/welcome";
    }

    @GetMapping(value = "/clients")
    public List<Client> getClients(){
        return clientRepository.findAll();
    }

    @PostMapping(value = "/save")
    public String saveClient(@RequestBody Client client){
        clientRepository.save(client);
        return "Client saved";
    }

    @PutMapping(value = "/update/{clientId}")
    public String updateClient(@PathVariable long clientId, @RequestBody Client client){
        clientService.updateClient(clientRepository, clientId, client);
        return "Client updated";
    }

    @DeleteMapping(value = "/delete/{clientId}")
    public String deleteClient(@PathVariable long clientId){
        Client deleteClient = clientRepository.findById(clientId).get();
        clientRepository.delete(deleteClient);
        return "Client with the id: " + clientId + " deleted";
    }

    @GetMapping(value = "/balance/{clientId}")
    public String getBalance(@PathVariable long clientId){
        int balance = 0;
        try {
            balance = clientRepository.findById(clientId).get().getBalance();
            return String.valueOf(balance);
        } catch (RuntimeException e){
            return "Error during operation (-1) ";
        }
    }

    @PutMapping(value = "/takemoney/{clientId}")
    public String takeMoney(@PathVariable long clientId, @RequestBody String sum){
        try {
            clientService.takeMoney(clientRepository, clientId, sum);
            operationService.saveOperation(operationRepository, clientId, 1, sum, LocalDate.now());
            return "Successfully (1) ";
        } catch (RuntimeException e){
            return "Insufficient funds (0) ";
        }
    }

    @PutMapping(value = "/putmoney/{clientId}")
    public String putMoney(@PathVariable long clientId, @RequestBody String sum){
        try {
            clientService.putMoney(clientRepository, clientId, sum);
            operationService.saveOperation(operationRepository, clientId, 2, sum, LocalDate.now());
            return "Successfully (1) ";
        } catch (RuntimeException e){
            return "Error during operation (0) ";
        }
    }

    @PutMapping(value = "/transfermoney/{senderId}/{receiverId}")
    public String transferMoney(@PathVariable long senderId, @PathVariable long receiverId, @RequestBody String sum){
        try {
            clientService.transferMoney(clientRepository, senderId, receiverId, sum);
            operationService.saveOperation(operationRepository, senderId, 3, sum, LocalDate.now());
            operationService.saveOperation(operationRepository, receiverId, 4, sum, LocalDate.now());
            return "Successfully (1) ";
        } catch (RuntimeException e){
            return "Insufficient funds (0) ";
        }
    }

    @GetMapping(value = "/operations")
    public List<Operation> getOperations(){
        return operationRepository.findAll();
    }

    @GetMapping(value = "/operationsafter/{clientId}")
    public List<Operation> getOperationsAfter(@PathVariable long clientId, @RequestBody String date){
        return operationService.findOperationsAfter(operationRepository, clientId, date);
    }

    @GetMapping(value = "/operationsbefore/{clientId}")
    public List<Operation> getOperationsBefore(@PathVariable long clientId, @RequestBody String date){
        return operationService.findOperationsBefore(operationRepository, clientId, date);
    }

    @GetMapping(value = "/operationsbetween/{clientId}")
    public List<Operation> getOperationsBetween(@PathVariable long clientId, @RequestBody String dates){
        return operationService.findOperationsBetween(operationRepository, clientId, dates);
    }
}
