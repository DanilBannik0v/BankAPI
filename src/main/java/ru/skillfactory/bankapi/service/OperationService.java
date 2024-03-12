package ru.skillfactory.bankapi.service;

import ru.skillfactory.bankapi.repository.OperationRepository;

import java.util.Date;

public interface OperationService {
    void saveOperation(OperationRepository operationRepository, long clientId, int type, String sum, Date date);
}
