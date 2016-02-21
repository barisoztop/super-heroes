package com.baris.repository;

import org.springframework.data.repository.CrudRepository;

import com.baris.domain.Hero;

public interface HeroRepository extends CrudRepository<Hero, Long> { }

