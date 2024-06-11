package br.com.infuse.api.repository;

import br.com.infuse.api.models.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<ClientModel, UUID> {
}
