package br.com.infuse.api.controller;

import br.com.infuse.api.dtos.RequestRecordDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RequestController.class)
class RequestControllerTest {

    @MockBean
    private RequestController controller;
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private List<RequestRecordDTO> list;

    @BeforeEach
    void setUp() {
        controller = Mockito.mock(RequestController.class);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        list.add(new RequestRecordDTO(2, new Date(), "Martelo Anatomico", new BigDecimal("50.00"), 100, "3fa85f64-5717-4562-b3fc-2c963f66afa6"));
    }


    @Test
    void saveRequests() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/request")
                        .content(list.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

    }

    @Test
    void getAllRequests() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/cliente/{idClient}", String.valueOf(UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6"))))
                .andDo(print())
                .andExpect(status().isNotFound());

    }

    @Test
    void getOneRequests() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/pedido/{idPedido}", 2))
                .andDo(print())
                .andExpect(status().isNotFound());

    }

    @Test
    void testGetAllRequests() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/pedidos")
                        .param("idPedido", String.valueOf(2))
                        .param("dtCadast", String.valueOf(new Date())))
                .andDo(print())
                .andExpect(status().isNotFound());


    }
}