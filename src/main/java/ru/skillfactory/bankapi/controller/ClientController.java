package ru.skillfactory.bankapi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.skillfactory.bankapi.model.Client;
import ru.skillfactory.bankapi.repository.ClientRepository;
import ru.skillfactory.bankapi.service.ClientService;
import ru.skillfactory.bankapi.service.ClientServiceImpl;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/bank-api")
public class ClientController {
    private final ClientService clientService = new ClientServiceImpl();

    @Autowired
    private ClientRepository clientRepository;

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

    @PutMapping(value = "/update/{id}")
    public String updateClient(@PathVariable long id, @RequestBody Client client){
        clientService.updateClient(clientRepository, id, client);
        return "Client updated";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteClient(@PathVariable long id){
        Client deleteClient = clientRepository.findById(id).get();
        clientRepository.delete(deleteClient);
        return "Client with the id: " + id + " deleted";
    }

    @GetMapping(value = "/balance/{id}")
    public String getBalance(@PathVariable long id){
        int balance = 0;
        try {
            balance = clientRepository.findById(id).get().getBalance();
            return String.valueOf(balance);
        } catch (RuntimeException e){
            return "Error during operation (-1) " + Arrays.toString(e.getStackTrace());
        }
    }

    @PutMapping(value = "/takemoney/{id}")
    public String takeMoney(@PathVariable long id, @RequestBody String sumToTake){
        try {
            clientService.takeMoney(clientRepository,id,sumToTake);
            return "Successfully (1) ";
        } catch (RuntimeException e){
            return "Insufficient funds (0) " + Arrays.toString(e.getStackTrace());
        }
    }

    @PutMapping(value = "/putmoney/{id}")
    public String putMoney(@PathVariable long id, @RequestBody String sumToPut){
        try {
            clientService.putMoney(clientRepository, id, sumToPut);
            return "Successfully (1) ";
        } catch (RuntimeException e){
            return "Error during operation (0) " + Arrays.toString(e.getStackTrace());
        }
    }
}
