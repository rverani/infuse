package br.com.infuse.api.controller;

import br.com.infuse.api.dtos.RequestRecordDTO;
import br.com.infuse.api.service.impl.RequestServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/request")
public class RequestController {
    private RequestServiceImpl service;

    public RequestController(RequestServiceImpl service) {
        this.service = service;
    }

    @PostMapping(
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<Object> saveRequests(@RequestBody @Valid List<RequestRecordDTO> prod) {
        return service.saveRequest(prod);
    }

    @GetMapping("/cliente/{idClient}")
    public ResponseEntity<Object> getAllRequests(@PathVariable(value = "idClient") String idClient) {
       return service.getAllRequests(idClient);
    }

    @GetMapping("/pedido/{idPedido}")
    public ResponseEntity<Object> getOneRequests(@PathVariable(value = "idPedido") int idPedido) {
        return service.getOneRequest(idPedido);
    }

    @GetMapping("/pedidos")
    public ResponseEntity<Object> getAllRequests(@RequestParam(value = "idPedido", required = false) String idPedido,
                                                 @RequestParam(value = "dtCadast", required = false) String dtCadast) {
        return service.getFindAllRequests(idPedido, dtCadast);
    }

}
