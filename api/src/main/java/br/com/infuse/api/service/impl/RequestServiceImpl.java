package br.com.infuse.api.service.impl;

import br.com.infuse.api.models.RequestModel;
import br.com.infuse.api.dtos.RequestRecordDTO;
import br.com.infuse.api.repository.RequestRepository;
import br.com.infuse.api.service.RequestService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

import static br.com.infuse.api.util.CalculateValue.calcDesconto;
@Service
public class RequestServiceImpl implements RequestService {

    private RequestRepository repository;
    public RequestServiceImpl(RequestRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ResponseEntity<Object> saveRequest(List<RequestRecordDTO> prod) {

        if (prod.size() < 11) {
            for (RequestRecordDTO prodList : prod) {
                if (findIdControl(prodList.idControl())) {
                    var requestModel = new RequestModel();
                    BeanUtils.copyProperties(prodList, requestModel);

                    if (requestModel.getDtCadastro() == null) {
                        requestModel.setDtCadastro(new Date());
                    }
                    if (requestModel.getQuantity() == 0) {
                        requestModel.setQuantity(1);
                    }
                    requestModel.setValue(calcDesconto(requestModel.getQuantity(), prodList.value()));

                    repository.save(requestModel);
                } else {
                    return ResponseEntity.status(HttpStatus.CREATED).body("Código Pedido: " + prodList.idControl() + " já em uso!");
                }

            }
            return ResponseEntity.status(HttpStatus.CREATED).body("Pedido(s) Gravados com Sucesso");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Lista de Pedidos ultrapassa a 10 registros");
    }

    @Override
    public ResponseEntity<Object> getAllRequests(String idclient) {
        Optional<List<RequestModel>> requestList = repository.findByIdCliente(idclient);
        if (requestList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não Existem Pedidos para esse Cliente");
        }
        return ResponseEntity.status(HttpStatus.OK).body(requestList);
    }

    @Override
    public ResponseEntity<Object> getOneRequest(int idPedido) {
        Optional<RequestModel> productO = repository.findByIdPedido(idPedido);
        if (productO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não existe");
        }
        return ResponseEntity.status(HttpStatus.OK).body(productO.get());

    }

    public ResponseEntity<Object> getOneDateRequest(String dtCadast) {
        Optional<List<RequestModel>> productO = repository.findByDateCadastro(LocalDate.parse(dtCadast));
        if (productO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não existe");
        }
        return ResponseEntity.status(HttpStatus.OK).body(productO.get());

    }

    public ResponseEntity<Object> getFindOneDateRequest(int idPedido, String dtCadast) {
        Optional<RequestModel> productO = repository.findByOneDateCadastro(dtCadast, idPedido);
        if (productO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não existe");
        }
        return ResponseEntity.status(HttpStatus.OK).body(productO.get());

    }

    @Override
    public ResponseEntity<Object> getFindAllRequests(String idPedido, String dtCadast) {
        if (idPedido != null && dtCadast == null) {
            int idrequest = Integer.parseInt(idPedido);
            return getOneRequest(idrequest);
        } else if (dtCadast != null && idPedido == null) {
            return getOneDateRequest(dtCadast);
        } else if (idPedido != null && dtCadast != null) {
            int idrequest = Integer.parseInt(idPedido);
            return getFindOneDateRequest(idrequest, dtCadast);
        } else {
            List<RequestModel> productO = repository.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(productO);
        }
    }

    public Boolean findIdControl(int id) {
        if (repository.findByIdControl(id) == 0) {
            return true;
        } else {
            return false;
        }
    }
}
