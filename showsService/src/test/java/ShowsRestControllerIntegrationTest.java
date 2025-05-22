import com.hiberus.ApplicationShowsService;
import com.hiberus.exception.InvalidShowException;
import com.hiberus.exception.ShowNotFoundException;
import com.hiberus.usecase.GetAllShowsUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = ApplicationShowsService.class, properties = "spring.profiles.active=showsService")
@AutoConfigureMockMvc
class ShowsRestControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetAllShowsUseCase getAllShowsUseCase;

    @Test
    void shouldReturnInternalServerErrorWhenUnexpectedErrorOccurs() throws Exception {
        // Simular un error inesperado
        when(getAllShowsUseCase.getAllShows()).thenThrow(new RuntimeException("Unexpected error"));

        // Prueba del endpoint
        mockMvc.perform(get("/v1/shows")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message").value("Unexpected error"));
    }

    @Test
    void shouldReturnNotFoundForShowNotFoundException() throws Exception {
        // Simular la excepción personalizada ShowNotFoundException
        when(getAllShowsUseCase.getAllShows()).thenThrow(new ShowNotFoundException(UUID.randomUUID()));

        // Prueba del endpoint
        mockMvc.perform(get("/v1/shows")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(org.hamcrest.Matchers.containsString("Show with ID")));
    }

    @Test
    void shouldReturnBadRequestForInvalidShowException() throws Exception {
        // Simular la excepción personalizada InvalidShowException
        when(getAllShowsUseCase.getAllShows()).thenThrow(new InvalidShowException());

        // Prueba del endpoint
        mockMvc.perform(get("/v1/shows")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("El show o su título no pueden ser nulos o vacíos"));
    }
}