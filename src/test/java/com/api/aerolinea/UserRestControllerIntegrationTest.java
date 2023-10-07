package com.api.aerolinea;

import com.api.aerolinea.Controllers.UserController;
import com.api.aerolinea.Entities.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserRestControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserController userController;

    @Test
    public void retornarJSONsDeUsuarios() throws Exception {
    }
}
