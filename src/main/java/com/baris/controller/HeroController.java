package com.baris.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.baris.domain.Hero;
import com.baris.repository.HeroRepository;

@RestController
public class HeroController {
	
	@Autowired
	private HeroRepository heroRepository;
	
	@RequestMapping(value="/heroes", method=RequestMethod.POST)
	public ResponseEntity<?> createHero(@RequestBody Hero hero) {
		hero = heroRepository.save(hero);
		
		HttpHeaders responseHeaders = new HttpHeaders();
		URI newHeroUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(hero.getId()).toUri();
		responseHeaders.setLocation(newHeroUri);
		
		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/heroes", method=RequestMethod.GET)
	public ResponseEntity<Iterable<Hero>> getAllHeroes() {
		Iterable<Hero> allHeroes = heroRepository.findAll();
		return new ResponseEntity<>(allHeroes, HttpStatus.OK); 
	}
	
	@RequestMapping(value="/heroes/{heroId}", method=RequestMethod.GET)
	public ResponseEntity<?> getHero(@PathVariable Long heroId) {
		Hero hero = heroRepository.findOne(heroId);
		return new ResponseEntity<>(hero, HttpStatus.OK); 
	}
	
	@RequestMapping(value="/heroes/{heroId}", method=RequestMethod.PUT)
	public ResponseEntity<?> updateHero(@RequestBody Hero hero, @PathVariable Long heroId) {
		heroRepository.save(hero);
		return new ResponseEntity<>(HttpStatus.OK); 
	}
	
	@RequestMapping(value="/heroes/{heroId}", method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteHero(@PathVariable Long heroId) {
		heroRepository.delete(heroId);
		return new ResponseEntity<>(HttpStatus.OK); 
	}
	
	

}
