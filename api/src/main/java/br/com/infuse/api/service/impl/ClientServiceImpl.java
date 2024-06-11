package br.com.infuse.api.service.impl;

import br.com.infuse.api.controller.ClientController;
import br.com.infuse.api.dtos.ClientRecordDTO;
import br.com.infuse.api.models.ClientModel;
import br.com.infuse.api.repository.ClientRepository;
import br.com.infuse.api.service.ClientService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository repository;

    public ClientServiceImpl(ClientRepository repository) {
        this.repository = repository;
    }


    @Override
    public ResponseEntity<ClientModel> saveClient(ClientRecordDTO recordDTO) {
        var clientModel = new ClientModel();
        clientModel.setDtCadastro(new Date());
        BeanUtils.copyProperties(recordDTO, clientModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(clientModel));
    }

    @Override
    public ResponseEntity<List<ClientModel>> getAllClients() {
        List<ClientModel> clientList = repository.findAll();
        if (!clientList.isEmpty()) {
            for (ClientModel client : clientList) {
                UUID id = client.getIdClient();
                client.add(linkTo(methodOn(ClientController.class).getOneClient(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(clientList);
    }

    @Override
    public ResponseEntity<Object> getOneClient(UUID id) {
        Optional<ClientModel> clientO = repository.findById(id);
        if (clientO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não existe");
        }
        clientO.get().add(linkTo(methodOn(ClientController.class).getAllClients()).withSelfRel());
        return ResponseEntity.status(HttpStatus.OK).body(clientO.get());
    }

    @Override
    public ResponseEntity<Object> updateClients(UUID id, ClientRecordDTO recordDTO) {
        Optional<ClientModel> clientO = repository.findById(id);
        if (clientO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não existe");
        }
        var clientModel = clientO.get();
        BeanUtils.copyProperties(recordDTO, clientModel);
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(clientModel));
    }

    @Override
    public ResponseEntity<Object> deleteOneClients(UUID id) {
        Optional<ClientModel> clientO = repository.findById(id);
        if (clientO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não existe");
        }
        repository.delete(clientO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Cliente Deletado");
    }
}
