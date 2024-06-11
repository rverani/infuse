package br.com.infuse.api.service.impl;

import br.com.infuse.api.dtos.ClientRecordDTO;
import br.com.infuse.api.models.ClientModel;
import br.com.infuse.api.repository.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;


@SpringBootTest
class ClientServiceImplTest {

    @MockBean
    private ClientServiceImpl service;

    @Mock
    private ClientRepository repository;
    @Mock
    private ClientModel model;
    @Mock
    private ClientRecordDTO recordDTO;
    @Mock
    private List<ClientModel> list;

    @BeforeEach
    void setUp() {
        service = Mockito.mock(ClientServiceImpl.class);
        repository = Mockito.mock(ClientRepository.class);
        model = Mockito.mock(ClientModel.class);
        recordDTO = Mockito.mock(ClientRecordDTO.class);
        recordDTO = new ClientRecordDTO("Paulo Duarte", "Rua Juiz de Fora");
        model.setIdClient(UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6"));
        model.setAddressClient("Rua Juiz de Fora");
        model.setName("Paulo Duarte");
        model.setDtCadastro(new Date());
        list.add(model);
    }

    @Test
    void saveClient() throws Exception {
        Mockito.when(service.saveClient(any())).thenReturn(ResponseEntity.accepted().body(model));
        Mockito.verify(repository, Mockito.times(0)).save(model);

        var result = service.saveClient(recordDTO);

        Assertions.assertNotNull(result);
    }

    @Test
    void getAllClients() throws Exception {
        Mockito.when(service.getAllClients()).thenReturn(ResponseEntity.status(HttpStatus.OK).body(list));
        Mockito.verify(repository, Mockito.times(0)).findAll();

        var result = service.getAllClients();

        Assertions.assertNotNull(result);
    }

    @Test
    void getOneClient() throws Exception {
        Mockito.when(service.getOneClient(model.getIdClient())).thenReturn(ResponseEntity.status(HttpStatus.OK).body(model));
        Mockito.verify(repository, Mockito.times(0)).findById(model.getIdClient());

        var result = service.getOneClient(model.getIdClient());

        Assertions.assertNotNull(result);

    }

    @Test
    void updateClients() throws Exception {
        Mockito.when(service.updateClients(model.getIdClient(), recordDTO)).thenReturn(ResponseEntity.status(HttpStatus.OK).body(model));
        Mockito.verify(repository, Mockito.times(0)).save(model);

        var result = service.updateClients(model.getIdClient(), recordDTO);

        Assertions.assertNotNull(result);
    }

    @Test
    void deleteOneClients() throws Exception {
        Mockito.when(service.deleteOneClients(model.getIdClient())).thenReturn(ResponseEntity.status(HttpStatus.OK).body(model));
        Mockito.verify(repository, Mockito.times(0)).findById(model.getIdClient());
        Mockito.verify(repository, Mockito.times(0)).delete(model);

        var result = service.deleteOneClients(model.getIdClient());

        Assertions.assertNotNull(result);

    }
}