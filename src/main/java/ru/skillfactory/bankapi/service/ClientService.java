package ru.skillfactory.bankapi.service;

import ru.skillfactory.bankapi.model.Client;
import ru.skillfactory.bankapi.repository.ClientRepository;

public interface ClientService {
    void updateClient(ClientRepository clientRepository, long id, Client client);
    void takeMoney(ClientRepository clientRepository, long id, String sumToTake);
    void putMoney(ClientRepository clientRepository, long id, String sumToPut);
    void transferMoney(ClientRepository clientRepository, long senderId, long receiverId, String sumToTransfer);
}
