package ru.skillfactory.bankapi.service;

import org.springframework.stereotype.Service;
import ru.skillfactory.bankapi.model.Client;
import ru.skillfactory.bankapi.repository.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService{
    @Override
    public void updateClient(ClientRepository clientRepository, long id, Client client) {
        Client updatedClient = clientRepository.findById(id).get();
        updatedClient.setBalance(client.getBalance());
        clientRepository.save(updatedClient);
    }

    @Override
    public void takeMoney(ClientRepository clientRepository, long id, String sumToTake) {
        Client client = clientRepository.findById(id).get();
        client.setBalance(client.getBalance() - Integer.parseInt(sumToTake));
        clientRepository.save(client);
    }

    @Override
    public void putMoney(ClientRepository clientRepository, long id, String sumToPut) {
        Client client = clientRepository.findById(id).get();
        client.setBalance(client.getBalance() + Integer.parseInt(sumToPut));
        clientRepository.save(client);
    }
}

