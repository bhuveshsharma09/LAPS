package sg.edu.nus.LAPS.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class AuthenticationControllerTest {
    /*@Autowired
    private AuthenticationController authenticationController;*/


    @Test
    void getWelcomePage() throws Exception {
        AuthenticationController authenticationController = new AuthenticationController();
        MockMvc mockMvc = MockMvcBuilders
                .standaloneSetup(authenticationController)
                .build();

        mockMvc.perform(get("/")).andExpect(view().name("welcome"));


    }

    @Test
    void getLoginPage() {
    }

    @Test
    void authenticate() {
    }

    @Test
    void logout() {

    }
}