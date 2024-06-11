package br.com.infuse.api.controller;

import br.com.infuse.api.dtos.ClientRecordDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientController.class)
class ClientControllerTest {

    @MockBean
    private ClientController controller;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        controller = Mockito.mock(ClientController.class);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void saveClient() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/clients")
                        .content(asJsonString(new ClientRecordDTO("Rubens Verani", "Rua Caçapava")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void getAllClients() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/clients"))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    void getOneClient() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/clients")
                        .param("id", String.valueOf(UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6"))))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void updateClients() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/clients/{id}", String.valueOf(UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6")))
                        .content(asJsonString(new ClientRecordDTO("Rubens Verani", "Rua Caçapava")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteOneClients() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/clients/{id}",String.valueOf(UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}