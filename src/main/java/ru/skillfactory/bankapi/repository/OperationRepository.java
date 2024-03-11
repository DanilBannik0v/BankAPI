package ru.skillfactory.bankapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skillfactory.bankapi.model.Operation;

public interface OperationRepository extends JpaRepository<Operation, Long> {
}
