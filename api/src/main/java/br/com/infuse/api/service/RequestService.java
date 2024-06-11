package br.com.infuse.api.service;

import br.com.infuse.api.models.RequestModel;
import br.com.infuse.api.dtos.RequestRecordDTO;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RequestService {
    ResponseEntity<Object> saveRequest(List<RequestRecordDTO> prod);
    ResponseEntity<Object> getAllRequests(String idclient);
    ResponseEntity<Object> getOneRequest(int idPedido);
    ResponseEntity<Object> getFindAllRequests(String idPedido, String dtCadast);
}
