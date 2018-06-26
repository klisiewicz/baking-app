package pl.karollisiewicz.baking.app.ui.recipe.details.step.video;

public final class RecipeStepVideoViewState {
    private final String title;
    private final String videoUrl;
    private final String description;

    public RecipeStepVideoViewState(final String title,
                                    final String videoUrl,
                                    final String description) {
        this.title = title;
        this.videoUrl = videoUrl;
        this.description = description;
    }

    String getTitle() {
        return title;
    }

    String getVideoUrl() {
        return videoUrl;
    }

    String getDescription() {
        return description;
    }
}
