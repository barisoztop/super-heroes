package com.baris.unit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import com.baris.domain.Hero;
import com.baris.repository.HeroRepository;
import com.baris.v1.controller.HeroController;
import com.google.common.collect.Lists;

public class HeroControllerTestMock {
	
	@Mock
	private HeroRepository heroRepository;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetAllHeroes() {
		HeroController heroController  = new HeroController();
    	ReflectionTestUtils.setField(heroController, "heroRepository", heroRepository);
    	
    	when(heroRepository.findAll()).thenReturn(new ArrayList<Hero>());
		ResponseEntity<Iterable<Hero>> allHeroesEntity = heroController.getAllHeroes();
		verify(heroRepository, times(1)).findAll();
		assertEquals(HttpStatus.OK, allHeroesEntity.getStatusCode());
		assertEquals(0, Lists.newArrayList(allHeroesEntity.getBody()).size());
	}

}
