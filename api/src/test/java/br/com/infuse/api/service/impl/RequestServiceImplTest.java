package br.com.infuse.api.service.impl;

import br.com.infuse.api.dtos.RequestRecordDTO;
import br.com.infuse.api.models.RequestModel;
import br.com.infuse.api.repository.RequestRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class RequestServiceImplTest {

    @MockBean
    private RequestServiceImpl requestService;
    @Mock
    private RequestModel model;
    @Mock
    private RequestRepository repository;

    @Mock
    private RequestRecordDTO recordDTO;

    @Mock
    private List<RequestRecordDTO> recordDTOS;

    @BeforeEach
    void setUp() {
        model = Mockito.mock(RequestModel.class);
        requestService = Mockito.mock(RequestServiceImpl.class);
        repository = Mockito.mock(RequestRepository.class);
        recordDTO = Mockito.mock(RequestRecordDTO.class);

        recordDTO = new RequestRecordDTO(2, new Date(), "Martelo", new BigDecimal("50.00"), 100,"3fa85f64-5717-4562-b3fc-2c963f66afa6");
        model = new RequestModel();
        model.setValue(new BigDecimal("50.00"));
        model.setName("Martelo");
        model.setQuantity(2);
        model.setIdControl(2);
        model.setIdClient("3fa85f64-5717-4562-b3fc-2c963f66afa6");
        model.setDtCadastro(new Date());
        recordDTOS.add(recordDTO);
    }

    @Test
    void saveRequest() throws Exception {
        Mockito.when(requestService.saveRequest(any())).thenReturn(ResponseEntity.accepted().body(recordDTO));
        Mockito.verify(repository, Mockito.times(0)).save(model);

        var result = requestService.saveRequest(recordDTOS);

        Assertions.assertNotNull(result);
    }

    @Test
    void getAllRequests() throws Exception {
        String idClient = "3fa85f64-5717-4562-b3fc-2c963f66afa6";
        Mockito.when(requestService.getAllRequests(idClient)).thenReturn(ResponseEntity.accepted().body(model));
        Mockito.verify(repository, Mockito.times(0)).findByIdCliente(idClient);

        var result = requestService.getAllRequests(idClient);

        Assertions.assertNotNull(result);
    }

    @Test
    void getOneRequest() throws Exception {
        int idPedido = 2;
        Mockito.when(requestService.getOneRequest(idPedido)).thenReturn(ResponseEntity.accepted().body(model));
        Mockito.verify(repository, Mockito.times(0)).findByIdPedido(idPedido);

        var result = requestService.getOneRequest(idPedido);

        Assertions.assertNotNull(result);

    }

    @Test
    void getOneDateRequest() throws Exception {
        String dtCadast = "2024-06-10";
        Mockito.when(requestService.getOneDateRequest(dtCadast)).thenReturn(ResponseEntity.accepted().body(model));
        Mockito.verify(repository, Mockito.times(0)).findByDateCadastro(LocalDate.parse(dtCadast));

        var result = requestService.getOneDateRequest(dtCadast);

        Assertions.assertNotNull(result);

    }

    @Test
    void getFindOneDateRequest() throws Exception {
        int idPedido = 2;
        String dtCadast = "2024-06-10";
        Mockito.when(requestService.getFindOneDateRequest(idPedido, dtCadast)).thenReturn(ResponseEntity.accepted().body(model));
        Mockito.verify(repository, Mockito.times(0)).findByOneDateCadastro(dtCadast, idPedido);

        var result = requestService.getFindOneDateRequest(idPedido, dtCadast);

        Assertions.assertNotNull(result);

    }

    @Test
    void getFindAllRequests() throws Exception {
        String idPedido = "2";
        String dtCadast = "2024-06-10";
        Mockito.when(requestService.getFindAllRequests(idPedido, dtCadast)).thenReturn(ResponseEntity.accepted().body(model));
        Mockito.verify(repository, Mockito.times(0)).findAll();

        var result = requestService.getFindAllRequests(idPedido, dtCadast);

        Assertions.assertNotNull(result);

    }

    @Test
    void findIdControlFalse() throws Exception{
        int id = 2;
        Mockito.when(requestService.findIdControl(id)).thenReturn(false);
        Mockito.verify(repository, Mockito.times(0)).findByIdControl(id);
        var result = requestService.findIdControl(id);

        Assertions.assertFalse(result);

    }
    @Test
    void findIdControltrue() throws Exception{
        int id = 20;
        Mockito.when(requestService.findIdControl(id)).thenReturn(true);
        Mockito.verify(repository, Mockito.times(0)).findByIdControl(id);
        var result = requestService.findIdControl(id);

        Assertions.assertTrue(result);

    }
}