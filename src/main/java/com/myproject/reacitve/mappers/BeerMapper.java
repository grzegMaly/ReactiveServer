package com.myproject.reacitve.mappers;

import com.myproject.reacitve.domain.Beer;
import com.myproject.reacitve.model.BeerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {

    Beer beetDtoToBeer(BeerDTO beer);
    BeerDTO beerToBeerDto(Beer beer);
}
