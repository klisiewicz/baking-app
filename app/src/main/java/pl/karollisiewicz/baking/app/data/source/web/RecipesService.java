package pl.karollisiewicz.baking.app.data.source.web;

import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import java.util.Collection;

import pl.karollisiewicz.baking.app.data.source.RecipeDto;

@Rest(rootUrl = "https://d17h27t6h515a5.cloudfront.net/", converters = {GsonHttpMessageConverter.class}
)
public interface RecipesService {

    @Get("topher/2017/May/59121517_baking/baking.json")
    Collection<RecipeDto> fetchAll();
}