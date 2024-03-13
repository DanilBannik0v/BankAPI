package ru.skillfactory.bankapi.service;

import ru.skillfactory.bankapi.model.Operation;
import ru.skillfactory.bankapi.repository.OperationRepository;

import java.time.LocalDate;
import java.util.List;

public interface OperationService {
    void saveOperation(OperationRepository operationRepository, long clientId, int type, String sum, LocalDate localDate);
    List<Operation> findOperationsAfter(OperationRepository operationRepository, long clientId, String date);
    List<Operation> findOperationsBefore(OperationRepository operationRepository, long clientId, String date);
    List<Operation> findOperationsBetween(OperationRepository operationRepository, long clientId, String dates);
}
