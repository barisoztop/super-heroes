package com.baris.it;


import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.baris.SuperHeroesApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SuperHeroesApplication.class)
@WebAppConfiguration
public class HeroControllerIT {

	@Autowired
	private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
	
    @Before
    public void setup() {
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }
    
    @Test
    public void testGetAllHeroes() throws Exception {
    	mockMvc.perform(get("/v1/heroes"))
    			.andExpect(status().isOk())
    			.andExpect(jsonPath("$", hasSize(1)));
    }
}