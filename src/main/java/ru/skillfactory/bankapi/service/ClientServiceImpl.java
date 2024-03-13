package ru.skillfactory.bankapi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional
    @Override
    public void takeMoney(ClientRepository clientRepository, long id, String sumToTake) {
        Client client = clientRepository.findById(id).get();
        client.setBalance(client.getBalance() - Integer.parseInt(sumToTake));
        clientRepository.save(client);
    }

    @Transactional
    @Override
    public void putMoney(ClientRepository clientRepository, long id, String sumToPut) {
        Client client = clientRepository.findById(id).get();
        client.setBalance(client.getBalance() + Integer.parseInt(sumToPut));
        clientRepository.save(client);
    }

    @Transactional
    @Override
    public void transferMoney(ClientRepository clientRepository, long senderId, long receiverId, String sumToTransfer) {
        Client sender = clientRepository.findById(senderId).get();
        if (sender.getBalance() > Integer.parseInt(sumToTransfer)) {
            Client receiver = clientRepository.findById(receiverId).get();

            sender.setBalance(sender.getBalance() - Integer.parseInt(sumToTransfer));
            clientRepository.save(sender);
            receiver.setBalance(receiver.getBalance() + Integer.parseInt(sumToTransfer));
            clientRepository.save(receiver);
        } else {
            throw new RuntimeException();
        }
    }
}

