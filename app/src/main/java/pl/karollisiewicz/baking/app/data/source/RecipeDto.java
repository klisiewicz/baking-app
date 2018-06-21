package pl.karollisiewicz.baking.app.data.source;

import java.util.Collection;
import java.util.List;

public class RecipeDto {
    private int id;
    private String name;
    private String servings;
    private String image;
    private Collection<RecipeIngredientDto> ingredients;
    private List<RecipeStepDto> steps;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServings() {
        return servings;
    }

    public void setServings(String servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Collection<RecipeIngredientDto> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Collection<RecipeIngredientDto> ingredients) {
        this.ingredients = ingredients;
    }

    public List<RecipeStepDto> getSteps() {
        return steps;
    }

    public void setSteps(List<RecipeStepDto> steps) {
        this.steps = steps;
    }
}
