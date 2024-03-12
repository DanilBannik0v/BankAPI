package ru.skillfactory.bankapi.service;

import org.springframework.stereotype.Service;
import ru.skillfactory.bankapi.model.Operation;
import ru.skillfactory.bankapi.repository.OperationRepository;

import java.util.Date;

@Service
public class OperationServiceImpl implements OperationService{
    @Override
    public void saveOperation(OperationRepository operationRepository, long clientId, int type, String sum, Date date) {
        operationRepository.save(new Operation(clientId, type, Integer.parseInt(sum), new Date()));
    }
}
