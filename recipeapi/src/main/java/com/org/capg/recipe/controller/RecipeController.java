package com.org.capg.recipe.controller;

import com.org.capg.recipe.dto.RecipeRequestDTO;
import com.org.capg.recipe.dto.RecipeResponseDTO;
import com.org.capg.recipe.service.RecipeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * The Class RecipeController.
 */
@RestController
@RequestMapping("/api/recipe")
public class RecipeController {

    Logger logger = LoggerFactory.getLogger(RecipeController.class);

    /**
     * The recipe service.
     */
    @Autowired
    RecipeService recipeService;

    /**
     * This method creates recipe with given request body
     *
     * @param recipeRequestDTO the recipe request
     * @return the response entity
     */
    @PostMapping
    public ResponseEntity<RecipeResponseDTO> createRecipe(@Valid @RequestBody RecipeRequestDTO recipeRequestDTO) {
        logger.info("Entering RecipeController ::: createRecipe");
        RecipeResponseDTO recipeResponseDTO = recipeService.createRecipe(recipeRequestDTO.dtoToEntityModel());
        return new ResponseEntity<>(recipeResponseDTO, HttpStatus.CREATED);
    }

    /**
     * This method returns the recipe by id.
     *
     * @param id
     * @return the response entity
     */
    @GetMapping("/{id}")
    public ResponseEntity<RecipeResponseDTO> getRecipe(@PathVariable("id") int id) {
        logger.info("Entering RecipeController ::: getRecipe");
        RecipeResponseDTO recipeResponseDTO = recipeService.getRecipeById(id);
        return new ResponseEntity<>(recipeResponseDTO, HttpStatus.OK);
    }

    /**
     * This method updates recipe of given id.
     *
     * @param id
     * @param recipeRequestDTO the recipe request
     * @return the response entity
     */
    @PutMapping("/{id}")
    public ResponseEntity<RecipeResponseDTO> updateRecipe(@PathVariable("id") int id, @Valid @RequestBody RecipeRequestDTO recipeRequestDTO) {
        logger.info("Entering RecipeController ::: updateRecipe");
        RecipeResponseDTO recipeResponseDTO = recipeService.updateRecipeById(id, recipeRequestDTO.dtoToEntityModel());
        return new ResponseEntity<>(recipeResponseDTO, HttpStatus.OK);
    }

    /**
     * This method deletes recipe by given id.
     *
     * @param id
     */
    @DeleteMapping("/{id}")
    public void deleteRecipe(@PathVariable("id") int id) {
        logger.info("Entering RecipeController ::: deleteRecipe");
        recipeService.deleteRecipeById(id);
    }
}
