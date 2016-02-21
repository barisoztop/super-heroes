package com.baris.controller;

import java.net.URI;

import javax.validation.Valid;

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
import com.baris.dto.error.ErrorDetail;
import com.baris.exception.ResourceNotFoundException;
import com.baris.repository.HeroRepository;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@RestController
@Api(value="heroes", description="Super Heroes API")
public class HeroController {
	
	@Autowired
	private HeroRepository heroRepository;
	
	@RequestMapping(value="/heroes", method=RequestMethod.POST)
	@ApiOperation(value="Creates a new hero", notes="The newly created Hero Id will be sent in the location response header", 
	response=Void.class)
	@ApiResponses(value={@ApiResponse(code=201, message="Hero Created Successfully", response=Void.class),  
			@ApiResponse(code=500, message="Error creating Hero", response=ErrorDetail.class) } )
	public ResponseEntity<Void> createHero(@Valid @RequestBody Hero hero) {
		hero = heroRepository.save(hero);
		
		HttpHeaders responseHeaders = new HttpHeaders();
		URI newHeroUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(hero.getId()).toUri();
		responseHeaders.setLocation(newHeroUri);
		
		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/heroes", method=RequestMethod.GET)
	@ApiOperation(value="Retrieves all the heroes", response=Hero.class, responseContainer="List")
	public ResponseEntity<Iterable<Hero>> getAllHeroes() {
		Iterable<Hero> allHeroes = heroRepository.findAll();
		return new ResponseEntity<>(allHeroes, HttpStatus.OK); 
	}
	
	@RequestMapping(value="/heroes/{heroId}", method=RequestMethod.GET)
	@ApiOperation(value="Retrieves given Hero", response=Hero.class)
	@ApiResponses(value={@ApiResponse(code=200, message="", response=Hero.class),  
			@ApiResponse(code=404, message="Unable to find Hero", response=ErrorDetail.class) } )
	public ResponseEntity<?> getHero(@PathVariable Long heroId) {
		verifyHero(heroId);
		Hero hero = heroRepository.findOne(heroId);
		return new ResponseEntity<>(hero, HttpStatus.OK); 
	}
	
	@RequestMapping(value="/heroes/{heroId}", method=RequestMethod.PUT)
	@ApiOperation(value="Updates given Hero", response=Void.class)
	@ApiResponses(value={@ApiResponse(code=200, message="", response=Void.class),  
			@ApiResponse(code=404, message="Unable to find Hero", response=ErrorDetail.class) } )
	public ResponseEntity<Void> updateHero(@RequestBody Hero hero, @PathVariable Long heroId) {
		verifyHero(heroId);
		heroRepository.save(hero);
		return new ResponseEntity<>(HttpStatus.OK); 
	}
	
	@RequestMapping(value="/heroes/{heroId}", method=RequestMethod.DELETE)
	@ApiOperation(value="Deletes given Hero", response=Void.class)
	@ApiResponses(value={@ApiResponse(code=200, message="", response=Void.class),  
			@ApiResponse(code=404, message="Unable to find Hero", response=ErrorDetail.class) } )
	public ResponseEntity<?> deleteHero(@PathVariable Long heroId) {
		verifyHero(heroId);
		heroRepository.delete(heroId);
		return new ResponseEntity<>(HttpStatus.OK); 
	}
	
	protected void verifyHero(Long heroId) throws ResourceNotFoundException {
		Hero hero = heroRepository.findOne(heroId);
		if (hero == null) {
			throw new ResourceNotFoundException("No hero found with id " + heroId);
		}
	}

}
