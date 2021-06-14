package com.org.capg.recipe.service;

import com.org.capg.recipe.dto.RecipeResponseDTO;
import com.org.capg.recipe.entity.RecipeEntity;
import com.org.capg.recipe.exception.RecipeNotFoundException;
import com.org.capg.recipe.repository.RecipeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The Class RecipeService.
 */
@Service
public class RecipeService {

    Logger logger = LoggerFactory.getLogger(RecipeService.class);

    /**
     * The recipe repository.
     */
    @Autowired
    RecipeRepository recipeRepository;

    /**
     * Creates the recipe.
     *
     * @param recipeEntity
     * @return the recipeResponseDTO
     */
    public RecipeResponseDTO createRecipe(RecipeEntity recipeEntity) {
        logger.info("::: RecipeService ::: saveRecipe :::");
        RecipeResponseDTO recipeResponseDTO = new RecipeResponseDTO();
        return recipeResponseDTO.entityToDtoModel(recipeRepository.save(recipeEntity));
    }

    /**
     * Get the recipe by id.
     *
     * @param id
     * @return the recipeResponseDTO
     */
    public RecipeResponseDTO getRecipeById(int id) {
        logger.info("::: RecipeService ::: getRecipeById :::");
        Optional<RecipeEntity> recipeEntityOptional = recipeRepository.findById(id);
        if(recipeEntityOptional.isPresent()) {
            RecipeEntity recipeEntity = recipeEntityOptional.get();
            RecipeResponseDTO recipeResponseDTO = new RecipeResponseDTO();
            return recipeResponseDTO.entityToDtoModel(recipeEntity);
        } else {
            throw new RecipeNotFoundException("No recipe found for recipe id: " + id);
        }
    }

    /**
     * Update the recipe by id.
     *
     * @param id
     * @param recipeEntity
     * @return the recipeResponseDTO
     */
    public RecipeResponseDTO updateRecipeById(int id, RecipeEntity recipeEntity) {
        logger.info("::: RecipeService ::: updateRecipeById :::");
        Optional<RecipeEntity> recipeEntityOptional = recipeRepository.findById(id);
       String ingredient;
        if(recipeEntityOptional.isPresent()) {
            RecipeEntity recipeDetails = recipeEntityOptional.get();
            recipeDetails.setDishType(recipeEntity.getDishType());
            recipeDetails.setNumOfPeople(recipeEntity.getNumOfPeople());
            recipeDetails.setCookingInstruction(recipeEntity.getCookingInstruction());
            List<String> ingredientsList = new ArrayList<>(Arrays.asList(recipeEntity.getIngredients().split(" , ")));
            ingredient = listIngredients(ingredientsList);
            recipeDetails.setIngredients(ingredient);

            RecipeResponseDTO recipeResponseDTO = new RecipeResponseDTO();
            return recipeResponseDTO.entityToDtoModel(recipeRepository.save(recipeDetails));
        } else {
            throw new RecipeNotFoundException("No recipe found for recipe id: " + id);
        }
    }

    /**
     * Delete recipe by id.
     *
     * @param id
     */
    public void deleteRecipeById(int id) {
        logger.info("::: RecipeService ::: deleteRecipeById :::");
        Optional<RecipeEntity> recipeEntityOptional = recipeRepository.findById(id);
        recipeEntityOptional.map((recipeEntity) -> {
            recipeRepository.delete(recipeEntity);
            return true;
        }).orElseThrow(() -> new RecipeNotFoundException("No recipe found for recipe id: " + id));
    }

    /**
     *
     * @param ingredients
     * @return the ingredientsList
     */
    private String listIngredients(List<String> ingredients) {
        String ingredientsList = null;
        if(ingredients != null) {
            ingredientsList = ingredients.stream().map(n -> String.valueOf(n)).collect(Collectors.joining(",", "{", "}"));
        }
        return ingredientsList;
    }

}
