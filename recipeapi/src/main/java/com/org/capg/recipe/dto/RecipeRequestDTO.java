package com.org.capg.recipe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.org.capg.recipe.entity.RecipeEntity;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * RecipeRequestDTO
 */
@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class RecipeRequestDTO {

    private int id;

    @NotNull
    private String dishType;

    @NotNull
    private int numOfPeople;

    @NotNull
    public List<String> ingredients;

    @NotNull
    private String cookingInstruction;

    /**
     * Dto to recipe entity model
     *
     * @return recipeEntity
     */
    public RecipeEntity dtoToEntityModel() {
        String ingredients;
        RecipeEntity recipeEntity = new RecipeEntity();
        recipeEntity.setCreationDate(new Date());
        recipeEntity.setDishType(this.dishType);
        recipeEntity.setNumOfPeople(this.numOfPeople);
        recipeEntity.setCookingInstruction(this.cookingInstruction);
        ingredients = listIngredients(this.getIngredients());
        recipeEntity.setIngredients(ingredients);

        return recipeEntity;
    }

    /**
     *
     * @param ingredients
     * @return ingredientsList
     */
    private String listIngredients(List<String> ingredients) {
        String ingredientsList = null;
        if(ingredients != null) {
            ingredientsList = ingredients.stream().map(n -> String.valueOf(n)).collect(Collectors.joining(",", "{", "}"));
        }
        return ingredientsList;
    }
}
