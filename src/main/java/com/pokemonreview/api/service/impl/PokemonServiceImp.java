package com.pokemonreview.api.service.impl;

import com.pokemonreview.api.dto.PokemonDto;
import com.pokemonreview.api.dto.PokemonResponse;
import com.pokemonreview.api.exception.PokemonNotFoundException;
import com.pokemonreview.api.models.Pokemon;
import com.pokemonreview.api.repository.PokemonRepository;
import com.pokemonreview.api.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.PageRanges;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class PokemonServiceImp implements PokemonService {

   private PokemonRepository pokemonRepository;

   @Autowired
    public PokemonServiceImp(com.pokemonreview.api.repository.PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public PokemonDto createPokemon(PokemonDto pokemonDto) {
        Pokemon pokemon= new Pokemon();
        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType());

        Pokemon newPokemon= pokemonRepository.save(pokemon);
        PokemonDto pokemonResponse = new PokemonDto();

        pokemonResponse.setName(newPokemon.getName());
        pokemonResponse.setType(newPokemon.getType());
        return pokemonResponse;
    }

    @Override
    public PokemonResponse getAllPokemon(int pageNo,int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Pokemon> pokemons = pokemonRepository.findAll(pageable);
        List<Pokemon> listOfPokemon = pokemons.getContent();
        List <PokemonDto> content=listOfPokemon.stream().map(p->mapToDto(p)).collect(Collectors.toList());

        PokemonResponse pokemonResponse = new PokemonResponse();
        pokemonResponse.setContent(content);
        pokemonResponse.setPageNo(pokemons.getNumber());
        pokemonResponse.setPageSize(pokemons.getSize());
        pokemonResponse.setTotalPages(pokemons.getTotalPages());
        pokemonResponse.setTotalElements(pokemons.getTotalElements());
        pokemonResponse.setLast(pokemons.isLast());
        return pokemonResponse;
    }

    @Override
    public void deletePokemon(int id) {

       Pokemon pokemon=pokemonRepository.findById(id).orElseThrow(() -> new PokemonNotFoundException("fhifhihgihejgdf"));

       pokemonRepository.delete(pokemon);
   }

    @Override
    public PokemonDto updatePokemon(PokemonDto pokemonDto, int id) {
        Pokemon pokemon= pokemonRepository.findById(id).orElseThrow(()->new PokemonNotFoundException("Not found"));

        pokemon.setType(pokemonDto.getType());
        pokemon.setName(pokemonDto.getName());

        Pokemon updatePokemon= pokemonRepository.save(pokemon);

        return mapToDto(updatePokemon);
    }

    @Override
    public PokemonDto getPokemonById(int id) {
        Pokemon pokemon= pokemonRepository.findById(id).orElseThrow(()-> new PokemonNotFoundException("fhifhihgihejgdf"));
        return mapToDto(pokemon);
    }

    private  PokemonDto mapToDto(Pokemon pokemon){
       PokemonDto result = new PokemonDto();
       result.setName(pokemon.getName());
       result.setType(pokemon.getType());
       return result;
    }

    private  Pokemon mapToEntity(PokemonDto pokemon){
        Pokemon result = new Pokemon();
        result.setName(pokemon.getName());
        result.setType(pokemon.getType());
        return result;
    }
}
