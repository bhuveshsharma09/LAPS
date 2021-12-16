package sg.edu.nus.LAPS.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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