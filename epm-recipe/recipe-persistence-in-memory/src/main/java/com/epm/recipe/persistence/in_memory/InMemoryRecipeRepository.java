package com.epm.recipe.persistence.in_memory;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.persistence.RecipeRepository;

import java.util.List;

public class InMemoryRecipeRepository implements RecipeRepository {
    @Override
    public List<Recipe> findAll() {
        return List.of(
                new Recipe("Hashbrowns", 10),
                new Recipe("Sandwich", 20)
        );
    }

    @Override
    public Recipe getById(Long id) {
        return null;
    }

    @Override
    public void insert(Recipe recipe) {

    }

    @Override
    public void update(Recipe recipe) {

    }

    @Override
    public void delete(Long recipeId) {

    }
}
