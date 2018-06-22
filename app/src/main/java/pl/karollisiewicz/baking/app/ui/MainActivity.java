package pl.karollisiewicz.baking.app.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.ncapdevi.fragnav.FragNavController;

import org.androidannotations.annotations.EActivity;

import pl.karollisiewicz.baking.R;
import pl.karollisiewicz.baking.app.ui.navigation.FragmentNavigation;
import pl.karollisiewicz.baking.app.ui.recipe.list.RecipesFragment_;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements FragmentNavigation {

    private FragNavController navController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navController = FragNavController.newBuilder(savedInstanceState, getSupportFragmentManager(), R.id.main_layout)
                .rootFragment(new RecipesFragment_())
                .build();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (navController != null) {
            navController.onSaveInstanceState(outState);
        }
    }

    @Override
    public void push(@NonNull Fragment fragment) {
        navController.pushFragment(fragment);
    }

    @Override
    public void onBackPressed() {
        if (!navController.popFragment()) {
            super.onBackPressed();
        }
    }
}
