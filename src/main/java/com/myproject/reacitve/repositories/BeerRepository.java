package com.myproject.reacitve.repositories;

import com.myproject.reacitve.domain.Beer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BeerRepository extends ReactiveCrudRepository<Beer, Integer> {


}
