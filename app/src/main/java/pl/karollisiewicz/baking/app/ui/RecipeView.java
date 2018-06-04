package pl.karollisiewicz.baking.app.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import pl.karollisiewicz.baking.R;
import pl.karollisiewicz.baking.domain.Recipe;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

@EViewGroup(R.layout.recipe_list_item)
public class RecipeView extends LinearLayout {

    @ViewById(value = R.id.recipe_thumbnail)
    AppCompatImageView recipeThumbnail;

    @ViewById(value = R.id.recipe_name)
    AppCompatTextView recipeName;

    @ViewById(value = R.id.recipe_serving)
    AppCompatTextView recipeServing;

    public RecipeView(Context context) {
        this(context, null);
    }

    public RecipeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RecipeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLayoutParams(new LayoutParams(MATCH_PARENT, WRAP_CONTENT));
        setOrientation(HORIZONTAL);
    }

    public void bind(@NonNull final Recipe recipe) {
        recipeName.setText(recipe.getName());
        recipeServing.setText(recipe.getServings());
        loadImage(recipe.getImageUrl());
    }

    private void loadImage(final String imageUrl) {
        if (TextUtils.isEmpty(imageUrl)) return;

        Picasso.with(getContext())
                .load(imageUrl)
                .error(R.drawable.ic_cupcake)
                .into(recipeThumbnail);
    }
}
