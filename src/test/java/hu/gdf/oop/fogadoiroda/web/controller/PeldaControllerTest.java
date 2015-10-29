package hu.gdf.oop.fogadoiroda.web.controller;

import org.junit.Test;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class PeldaControllerTest extends AbstractControllerTest {

    @Test
    public void testOsszead() throws Exception {
        mockMvc.perform(get("/osszead")
                .with(user("user")) // spring-security bekapcsolása miatt kell
                .param("a", "2")
                .param("b", "3"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("osszeg", 5))
                .andExpect(view().name("eredmeny"));
    }

    @Test
    public void testOsszeadHiba() throws Exception {
        mockMvc.perform(get("/osszead")
                .with(user("user")) // spring-security bekapcsolása miatt kell
                .param("a", "3"))
                .andExpect(status().isBadRequest());
    }
}
