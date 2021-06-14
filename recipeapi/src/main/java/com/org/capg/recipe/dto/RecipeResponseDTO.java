package com.org.capg.recipe.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.org.capg.recipe.entity.RecipeEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * RecipeResponseDTO
 */
@Data
public class RecipeResponseDTO {

    private int id;
    @JsonFormat(pattern="dd-MM-yyyy HH:mm")
    private Date creationDate;
    private String dishType;
    private int numOfPeople;
    public List<String> ingredients;
    private String cookingInstruction;

    /**
     * Entity to dto model
     *
     * @param recipeEntity
     * @return recipeResponseDTO
     */
    public RecipeResponseDTO entityToDtoModel(RecipeEntity recipeEntity) {
        RecipeResponseDTO recipeResponseDTO = new RecipeResponseDTO();
        recipeResponseDTO.setId(recipeEntity.getId());
        recipeResponseDTO.setCreationDate(recipeEntity.getCreationDate());
        recipeResponseDTO.setDishType(recipeEntity.getDishType());
        recipeResponseDTO.setNumOfPeople(recipeEntity.getNumOfPeople());
        recipeResponseDTO.setCookingInstruction(recipeEntity.getCookingInstruction());
        List<String> ingredientsList = new ArrayList<>(Arrays.asList(recipeEntity.getIngredients().split(" , ")));
        recipeResponseDTO.setIngredients(ingredientsList);

        return recipeResponseDTO;
    }

}
