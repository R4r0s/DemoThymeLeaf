package com.cositas.demo.service;

import com.cositas.demo.controller.DTO.CharacterDTO;
import com.cositas.demo.model.Character;
import com.cositas.demo.repository.CharacterRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
public class CharacterService implements CharacterServiceInterface{

    private final ModelMapper modelMapper;
    private final CharacterRepository characterRepository;
    public CharacterService(CharacterRepository characterRepository, ModelMapper modelMapper){
        this.modelMapper = modelMapper;
        this.characterRepository = characterRepository;
    }
    @Override
    @Transactional
    public Character createCharacter(CharacterDTO characterDTO) {
        Character charac = modelMapper.map(characterDTO, Character.class);
        charac.setUuid(UUID.randomUUID());
        return characterRepository.save(charac);
    }

    @Override
    public Character updateCharacter(CharacterDTO characterDTO) {
        Character charac = modelMapper.map(characterDTO, Character.class);
        final Character updatedCharac = characterRepository.findByUuid(characterDTO.getUuid())
                .orElse(new Character(charac.getId(),charac.getUuid(), charac.getName(), charac.getClassName(), /*charac.getStats(),*/ charac.getAlignment()));
        updatedCharac.setName(charac.getName());
        updatedCharac.setClassName(charac.getClassName());
        //updatedCharac.setStats(charac.getStats());
        updatedCharac.setAlignment(charac.getAlignment());
        characterRepository.save(updatedCharac);
        return updatedCharac;
    }

    @Override
    @Transactional
    public void removeCharacter(UUID uuid) {
        characterRepository.removeByUuid(uuid);
    }

    @Override
    public CharacterDTO getCharacter(UUID uuid) {
        return characterRepository.findByUuid(uuid).map(character -> modelMapper.map(character, CharacterDTO.class))
                .orElseThrow(() -> new IllegalArgumentException("No character was found with id: " + uuid));
    }

    @Override
    public List<CharacterDTO> getAllCharacters() {
        return characterRepository.findAll().stream().map(character -> modelMapper.map(character, CharacterDTO.class)).collect(Collectors.toList());
    }
}
