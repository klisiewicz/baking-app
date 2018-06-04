package pl.karollisiewicz.baking.domain;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public final class RecipeStep {
    private final Description description;
    private final ResourceLocation resource;

    private RecipeStep(@NonNull final Description description,
                       @NonNull final ResourceLocation resource) {
        this.description = description;
        this.resource = resource;
    }

    private RecipeStep(@NonNull final Builder builder) {
        description = new Description(builder.shortDescription, builder.fullDescription);
        resource = new ResourceLocation(builder.videoUrl, builder.thumbnailUrl);
    }

    @NonNull
    public String getShortDesription() {
        return description.getBrief();
    }

    @NonNull
    public String getFullDescription() {
        return description.getFull();
    }

    @Nullable
    public String getVideoUrl() {
        return resource.getVideo();
    }

    @Nullable
    public String getThumbnailUrl() {
        return resource.getThumbnail();
    }

    private static final class Description {
        private final String brief;
        private final String full;

        public Description(@NonNull final String brief, @NonNull final String full) {
            this.brief = brief;
            this.full = full;
        }

        @NonNull
        public String getBrief() {
            return brief;
        }

        @NonNull
        public String getFull() {
            return full;
        }
    }

    private static final class ResourceLocation {
        private final String video;
        private final String thumbnail;

        public ResourceLocation(@Nullable final String video, @Nullable final String thumbnail) {
            this.video = video;
            this.thumbnail = thumbnail;
        }

        @Nullable
        public String getVideo() {
            return video;
        }

        @Nullable
        public String getThumbnail() {
            return thumbnail;
        }
    }

    public static final class Builder {
        private final String shortDescription;
        private final String fullDescription;
        private String videoUrl;
        private String thumbnailUrl;

        private Builder(@NonNull final String shortDescription,
                        @NonNull final String fullDescription) {
            this.shortDescription = shortDescription;
            this.fullDescription = fullDescription;
        }

        public Builder setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
            return this;
        }

        public Builder setThumbnailUrl(String thumbnailUrl) {
            this.thumbnailUrl = thumbnailUrl;
            return this;
        }

        public RecipeStep build() {
            return new RecipeStep(this);
        }

        public static Builder withDescription(@NonNull final String shortDescription,
                                              @NonNull final String fullDescription) {
            return new Builder(shortDescription, fullDescription);
        }

    }
}
