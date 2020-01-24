package pl.martyna.catering.app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.martyna.catering.app.dto.auth.LoginRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.martyna.catering.app.dto.auth.RegisterRequest;
import pl.martyna.catering.app.entity.auth.Address;

import java.util.HashSet;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AuthenticationControllerTest {

    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    private final String mockUserMail = "admin@mail.com";

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void firstTestRegisterRequest() throws Exception {

        RegisterRequest request = new RegisterRequest(
                "admin", "admin","test", "user", mockUserMail,
                "123",new Address(), new HashSet<String>());

        mockMvc.perform(post("/api/users")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content( objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    public void secondTestShouldAuthenticate() throws Exception {

        LoginRequest loginRequest = new LoginRequest("admin", "admin");
        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isCreated());
    }

    @Test
    public void thirdTestDeleteUser() throws Exception{
        mockMvc.perform((MockMvcRequestBuilders
                    .delete("/api/users/{email}", mockUserMail)
                    .contentType(MediaType.APPLICATION_JSON))
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
