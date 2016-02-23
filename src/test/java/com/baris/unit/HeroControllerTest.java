package com.baris.unit;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.baris.SuperHeroesApplication;
import com.baris.domain.Hero;
import com.baris.repository.HeroRepository;
import com.baris.v1.controller.HeroController;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SuperHeroesApplication.class)
@ContextConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class HeroControllerTest {
	@Mock
	private HeroRepository heoRepository;
	
	@InjectMocks
	HeroController heroController;

	private MockMvc mockMvc;
	
    @Before
    public void setUp() throws Exception {
    	MockitoAnnotations.initMocks(this);
    	mockMvc = standaloneSetup(heroController).build();
    }
    
    @Test
    public void testGetAllPolls() throws Exception {
    	when(heoRepository.findAll()).thenReturn(new ArrayList<Hero>());
    	mockMvc.perform(get("/v1/heroes"))
    			.andExpect(status().isOk())
    			.andExpect(content().string("[]")); 
    }
}

