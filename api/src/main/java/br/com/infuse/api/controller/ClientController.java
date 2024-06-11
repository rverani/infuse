package br.com.infuse.api.controller;

import br.com.infuse.api.dtos.ClientRecordDTO;
import br.com.infuse.api.models.ClientModel;
import br.com.infuse.api.service.impl.ClientServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping(value = "/clients", name = "CLIENTES")
public class ClientController {

    private ClientServiceImpl service;

    public ClientController(ClientServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ClientModel> saveClient(@RequestBody @Valid ClientRecordDTO prod) {
        return service.saveClient(prod);
    }

    @GetMapping
    public ResponseEntity<List<ClientModel>> getAllClients() {
        return service.getAllClients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneClient(@PathVariable(value = "id") UUID id) {

        return service.getOneClient(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateClients(@PathVariable(value = "id") UUID id, @RequestBody @Valid ClientRecordDTO recordDTO) {
        return service.updateClients(id, recordDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteOneClients(@PathVariable(value = "id") UUID id) {
        return service.deleteOneClients(id);
    }
}

