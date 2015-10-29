package hu.gdf.oop.fogadoiroda.web.controller;

import org.junit.Test;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class SecurityControllerTest extends AbstractControllerTest {

    @Test
    public void login() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(model().size(0))
                .andExpect(view().name("auth/login"));
    }

    @Test
    public void loginError() throws Exception {
        mockMvc.perform(get("/login")
                .param("error", "1"))
                .andExpect(status().isOk())
                .andExpect(model().size(1))
                .andExpect(model().attribute("error", "1"))
                .andExpect(view().name("auth/login"));
    }

    @Test
    public void accessDenied() throws Exception {
        mockMvc.perform(get("/access-denied")
                .with(user("user")))
                .andExpect(status().isOk())
                .andExpect(view().name("auth/access-denied"));
    }


    @Test
    public void accessDeniedNoLogin() throws Exception {
        mockMvc.perform(get("/access-denied"))
                .andExpect(status().isFound());
    }

    @Test
    public void welcome() throws Exception {
        mockMvc.perform(get("/welcome"))
                .andExpect(status().isFound());
    }

    @Test
    public void welcomeNoLogin() throws Exception {
        mockMvc.perform(get("/welcome")
                .with(user("user")))
                .andExpect(status().isOk())
                .andExpect(view().name("welcome"));
    }

    @Test
    public void securedNoLogin() throws Exception {
        mockMvc.perform(get("/admin/welcome"))
                .andExpect(status().isFound());
    }

    @Test
    public void securedWithUser() throws Exception {
        mockMvc.perform(get("/admin/welcome")
                .with(user("user")))
                .andExpect(status().isForbidden());
    }

    @Test
    public void securedWithOperator() throws Exception {
        mockMvc.perform(get("/admin/welcome")
                .with(user("operator").password("a").roles("USER", "OPERATOR")))
                .andExpect(status().isOk())
                .andExpect(view().name("welcome"));
    }
}
