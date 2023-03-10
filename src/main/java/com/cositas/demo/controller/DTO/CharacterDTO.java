package com.cositas.demo.controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CharacterDTO {
    private UUID uuid;
    private String name;
    private String className;
   // private List<Integer> stats;
    private String alignment;
}
