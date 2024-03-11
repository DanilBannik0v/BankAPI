package ru.skillfactory.bankapi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.skillfactory.bankapi.models.Client;
import ru.skillfactory.bankapi.repository.ClientRepository;

import java.util.Arrays;
import java.util.List;

@RestController
public class Controller {
    
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping(value = "/")
    public String getPage(){
        return "welcome";
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

    @PutMapping(value = "update/{id}")
    public String updateClient(@PathVariable long id, @RequestBody Client client){
        Client updatedClient = clientRepository.findById(id).get();
        updatedClient.setBalance(client.getBalance());
        clientRepository.save(updatedClient);
        return "Client updated";
    }

    @DeleteMapping(value = "delete/{id}")
    public String deleteClient(@PathVariable long id){
        Client deleteClient = clientRepository.findById(id).get();
        clientRepository.delete(deleteClient);
        return "Client with the id: " + id + " deleted";
    }

    @GetMapping(value = "balance/{id}")
    public String getBalance(@PathVariable long id){
        int balance = 0;
        try {
            balance = clientRepository.findById(id).get().getBalance();
            return String.valueOf(balance);
        } catch (RuntimeException e){
            return "Error during operation (-1) " + Arrays.toString(e.getStackTrace());
        }
    }

    @PutMapping(value = "takemoney/{id}")
    public String takeMoney(@PathVariable long id, @RequestBody String sumToTake){
        try {
            Client client = clientRepository.findById(id).get();
            client.setBalance(client.getBalance() - Integer.parseInt(sumToTake));
            clientRepository.save(client);
            return "Successfully (1) ";
        } catch (RuntimeException e){
            return "Insufficient funds (0) " + Arrays.toString(e.getStackTrace());
        }
    }

    @PutMapping(value = "putmoney/{id}")
    public String putMoney(@PathVariable long id, @RequestBody String sumToPut){
        try {
            Client client = clientRepository.findById(id).get();
            client.setBalance(client.getBalance() + Integer.parseInt(sumToPut));
            clientRepository.save(client);
            return "Successfully (1) ";
        } catch (RuntimeException e){
            return "Error during operation (0) " + Arrays.toString(e.getStackTrace());
        }
    }
}
