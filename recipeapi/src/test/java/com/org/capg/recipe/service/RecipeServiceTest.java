package com.org.capg.recipe.service;

import com.org.capg.recipe.dto.RecipeRequestDTO;
import com.org.capg.recipe.dto.RecipeResponseDTO;
import com.org.capg.recipe.entity.RecipeEntity;
import com.org.capg.recipe.repository.RecipeRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeServiceTest {

    @Autowired
    private RecipeService recipeService;

    @MockBean
    private RecipeRepository recipeRepository;

    private RecipeEntity recipeEntity;

    @BeforeEach
    void setUp() {
        recipeEntity = new RecipeEntity();
        recipeEntity.setId(1);
        recipeEntity.setDishType("Vegetarian");
        recipeEntity.setNumOfPeople(3);
        recipeEntity.setCookingInstruction("Spicy");
        recipeEntity.setIngredients("Rice");
    }

    @Test
    public void createRecipeServiceTest() {
        RecipeRequestDTO recipeRequestDTO = new RecipeRequestDTO();
        recipeRequestDTO.setId(1);
        recipeRequestDTO.setDishType("Vegetarian");
        recipeRequestDTO.setNumOfPeople(3);
        recipeRequestDTO.setCookingInstruction("Spicy");
        recipeRequestDTO.setIngredients(Arrays.asList("Rice","Veges"));

        RecipeResponseDTO recipeResponseDTO = new RecipeResponseDTO();
        recipeResponseDTO.setId(1);
        recipeResponseDTO.setDishType("Vegetarian");
        recipeResponseDTO.setNumOfPeople(3);
        recipeResponseDTO.setCookingInstruction("Spicy");
        recipeResponseDTO.setIngredients(Arrays.asList("Rice","Veges"));

        Mockito.when(recipeRepository.save(recipeEntity)).thenReturn(recipeEntity);
        assertThat(recipeResponseDTO.getId()).isEqualTo(1);
    }

    @Test
    public void getRecipeById() {
        RecipeEntity recipeEntity = new RecipeEntity();
        recipeEntity.setId(1);
        recipeEntity.setDishType("Vegetarian");
        recipeEntity.setNumOfPeople(3);
        recipeEntity.setCookingInstruction("Spicy");
        recipeEntity.setIngredients("Rice");

        Mockito.when(recipeRepository.findById(1)).thenReturn(Optional.of(recipeEntity));

        Optional<RecipeEntity> recipeEntityOptional = recipeRepository.findById(1);

        assertThat(recipeEntityOptional).isPresent();
    }

    @Test
    public void updateRecipeById() {
        RecipeEntity recipeEntity = new RecipeEntity();
        recipeEntity.setId(1);
        recipeEntity.setDishType("Vegetarian");
        recipeEntity.setNumOfPeople(3);
        recipeEntity.setCookingInstruction("Spicy");
        recipeEntity.setIngredients("Rice");

        Mockito.when(recipeRepository.save(recipeEntity)).thenReturn(recipeEntity);
        RecipeEntity recipe = recipeRepository.save(recipeEntity);
        assertThat(recipe.getId()).isEqualTo(recipeEntity.getId());
    }

    @Test
    public void deleteRecipeById() {
        int id = 18;
        recipeRepository.deleteById(id);
        assertThat(recipeRepository.count()).isZero();
    }
}
