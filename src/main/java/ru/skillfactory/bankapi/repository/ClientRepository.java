package ru.skillfactory.bankapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skillfactory.bankapi.models.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
