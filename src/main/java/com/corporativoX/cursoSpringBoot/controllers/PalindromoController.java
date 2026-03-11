package com.corporativoX.cursoSpringBoot.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PalindromoController {
    @GetMapping("IsPalindromo/{word}")
    public boolean IsPalindromo(@PathVariable String word){
        char[] letters = word.toCharArray();

        for (int position = 0; position < letters.length; position++) {
            // if(letters[position] != lettersInverse[position]) return false;
            if(letters[position] != letters[(letters.length - 1) - position]) return false;
        }

        return true;
    }
}
