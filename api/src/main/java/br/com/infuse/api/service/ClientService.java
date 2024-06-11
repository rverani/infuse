package br.com.infuse.api.service;

import br.com.infuse.api.dtos.ClientRecordDTO;
import br.com.infuse.api.models.ClientModel;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface ClientService {
    ResponseEntity<ClientModel> saveClient(ClientRecordDTO recordDTO);
    ResponseEntity<List<ClientModel>> getAllClients();
    ResponseEntity<Object> getOneClient(UUID id);
    ResponseEntity<Object> updateClients(UUID id, ClientRecordDTO recordDTO);
    ResponseEntity<Object> deleteOneClients(UUID id);

}
