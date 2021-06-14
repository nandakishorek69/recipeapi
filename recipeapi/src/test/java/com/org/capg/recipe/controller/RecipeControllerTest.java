package com.org.capg.recipe.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.capg.recipe.dto.RecipeRequestDTO;
import com.org.capg.recipe.dto.RecipeResponseDTO;
import com.org.capg.recipe.entity.RecipeEntity;
import com.org.capg.recipe.service.RecipeService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(RecipeController.class)
public class RecipeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecipeService recipeService;

    private RecipeEntity recipeEntity;

    @Test
    public void createRecipeTest() throws Exception {

        recipeEntity = new RecipeEntity();
        recipeEntity.setId(1);
        recipeEntity.setDishType("Vegetarian");
        recipeEntity.setNumOfPeople(3);
        recipeEntity.setCookingInstruction("Spicy");
        recipeEntity.setIngredients("Rice");

        RecipeRequestDTO recipeRequestDTO = new RecipeRequestDTO();
        recipeRequestDTO.setDishType("Vegetarian");
        recipeRequestDTO.setNumOfPeople(3);
        recipeRequestDTO.setCookingInstruction("Spicy");

        RecipeResponseDTO recipeResponseDTO = new RecipeResponseDTO();
        recipeResponseDTO.setId(1);
        recipeResponseDTO.setDishType("Vegetarian");
        recipeResponseDTO.setNumOfPeople(3);
        recipeResponseDTO.setCookingInstruction("Spicy");

        Mockito.when(recipeService.createRecipe(recipeEntity)).thenReturn(recipeResponseDTO);

        mockMvc.perform(post("/api/recipe").
                content(asJsonString(recipeRequestDTO)).
                contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().is(201));
    }

    @Test
    public void getRecipeTest() throws Exception {
        RecipeResponseDTO recipeResponseDTO = new RecipeResponseDTO();
        recipeResponseDTO.setId(1);
        recipeResponseDTO.setDishType("Vegetarian");
        recipeResponseDTO.setNumOfPeople(3);
        recipeResponseDTO.setCookingInstruction("Spicy");
        recipeResponseDTO.setIngredients(new ArrayList<String>(Arrays.asList("Rice", "Veges")));

        RecipeRequestDTO recipeRequestDTO = new RecipeRequestDTO();
        recipeRequestDTO.setId(1);

        Mockito.when(recipeService.getRecipeById(recipeRequestDTO.getId())).thenReturn(recipeResponseDTO);

        mockMvc.perform(get("/api/recipe/{id}", recipeRequestDTO.getId()).
                contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk()).andExpect(jsonPath("$.id", Matchers.is(1)));
    }

    @Test
    public void updateRecipe() throws Exception {
        RecipeRequestDTO recipeRequestDTO = new RecipeRequestDTO();
        recipeRequestDTO.setId(1);
        recipeRequestDTO.setDishType("Vegetarian");
        recipeRequestDTO.setNumOfPeople(3);
        recipeRequestDTO.setCookingInstruction("Spicy");

        RecipeResponseDTO recipeResponseDTO = new RecipeResponseDTO();
        recipeResponseDTO.setId(1);
        recipeResponseDTO.setDishType("Vegetarian");
        recipeResponseDTO.setNumOfPeople(3);
        recipeResponseDTO.setCookingInstruction("Spicy");

        Mockito.when(recipeService.updateRecipeById(recipeRequestDTO.getId(), recipeEntity)).thenReturn(recipeResponseDTO);

        mockMvc.perform(put("/api/recipe/1")
                .content(String.valueOf(recipeRequestDTO))
                .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void deleteRecipe() throws Exception {
        int id =1;
        doNothing().when(recipeService).deleteRecipeById(id);

        mockMvc.perform(delete("/api/recipe/{id}", id).
                contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk());
    }

    public String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
