package com.cositas.demo.service;

import com.cositas.demo.controller.DTO.CharacterDTO;
import com.cositas.demo.model.Character;

import java.util.List;
import java.util.UUID;

public interface CharacterServiceInterface {

    Character createCharacter(CharacterDTO characterDTO);
    Character updateCharacter(CharacterDTO characterDTO);
    Character removeCharacter(UUID uuid);
    Character getCharacter(UUID uuid);
    List<CharacterDTO> getAllCharacters();
}
