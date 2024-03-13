package ru.skillfactory.bankapi.service;

import org.springframework.stereotype.Service;
import ru.skillfactory.bankapi.model.Operation;
import ru.skillfactory.bankapi.repository.OperationRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class OperationServiceImpl implements OperationService{
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    private static final String SPACE = " ";

    @Override
    public void saveOperation(OperationRepository operationRepository, long clientId, int type, String sum, LocalDate date) {
        operationRepository.save(new Operation(clientId, type, Integer.parseInt(sum), LocalDate.now()));
    }

    @Override
    public List<Operation> findOperationsAfter(OperationRepository operationRepository, long clientId, String date) {
        LocalDate localDate = LocalDate.parse(date, formatter);
        return operationRepository.findAll()
                .stream()
                .filter(operation -> operation.getClientId() == clientId && operation.getLocalDate().isAfter(localDate))
                .toList();
    }

    @Override
    public List<Operation> findOperationsBefore(OperationRepository operationRepository, long clientId, String date) {
        LocalDate localDate = LocalDate.parse(date, formatter);
        return operationRepository.findAll()
                .stream()
                .filter(operation -> operation.getClientId() == clientId && operation.getLocalDate().isBefore(localDate))
                .toList();
    }

    @Override
    public List<Operation> findOperationsBetween(OperationRepository operationRepository, long clientId, String dates) {
        LocalDate dateAfter = LocalDate.parse(dates.split(SPACE)[0], formatter);
        LocalDate dateBefore = LocalDate.parse(dates.split(SPACE)[1], formatter);
        return operationRepository.findAll()
                .stream()
                .filter(operation -> operation.getClientId() == clientId &&
                        operation.getLocalDate().isAfter(dateAfter) &&
                        operation.getLocalDate().isBefore(dateBefore))
                .toList();
    }
}
