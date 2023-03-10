package com.cositas.demo.controller;

import com.cositas.demo.controller.DTO.CharacterDTO;
import com.cositas.demo.service.CharacterService;
import com.cositas.demo.service.PdfGenerator;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("characters")
public class CharacterController {

    private CharacterService characterService;

    private PdfGenerator generator;

    public CharacterController(CharacterService characterService, PdfGenerator generator){

        this.characterService = characterService;
        this.generator = generator;
    }
    @GetMapping
    public ResponseEntity<?> getAllCharacters(){
        return ResponseEntity.ok(characterService.getAllCharacters());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<?> getCharacterByUuid(@PathVariable UUID uuid){
        return ResponseEntity.ok(characterService.getCharacter(uuid));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> deleteCharacter(@PathVariable UUID uuid){
        characterService.removeCharacter(uuid);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> createCharacter(@RequestBody CharacterDTO characterDTO){
        return ResponseEntity.ok(characterService.createCharacter(characterDTO));
    }

    @PutMapping
    public ResponseEntity<?> updateCharacter(@RequestBody CharacterDTO characterDTO){
        return ResponseEntity.ok(characterService.updateCharacter(characterDTO));
    }

    @GetMapping("/pdf/{uuid}")
    public HttpEntity<byte[]> generatePDF(@PathVariable UUID uuid) throws IOException {
        CharacterDTO charac = characterService.getCharacter(uuid);

        byte[] documentBody = this.generator.generatePdfFromHtml(generator.parseThymeleafTemplate(charac));
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_PDF);
        header.set(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=character.pdf");
        header.setContentLength(documentBody.length);

        return new HttpEntity<byte[]>(documentBody, header);
    }



}
