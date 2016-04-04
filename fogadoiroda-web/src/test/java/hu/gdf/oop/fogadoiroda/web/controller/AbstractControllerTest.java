package hu.gdf.oop.fogadoiroda.web.controller;

import hu.gdf.oop.fogadoiroda.config.SpringRootConfig;
import hu.gdf.oop.fogadoiroda.config.SpringSecurityConfig;
import hu.gdf.oop.fogadoiroda.config.SpringWebConfig;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import javax.servlet.Filter;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringRootConfig.class, SpringWebConfig.class, SpringSecurityConfig.class})
public abstract class AbstractControllerTest {

    @Resource
    private WebApplicationContext wac;

    @Resource
    private Filter springSecurityFilterChain;

    protected MockMvc mockMvc;

    @Before
    public void setup() {

        // tesztelés spring-security nélkül
        // mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

        // tesztelés spring-security-vel
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).addFilters(springSecurityFilterChain).build();
    }
}
