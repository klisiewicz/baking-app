package pl.karollisiewicz.baking.domain;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public final class RecipeStep {
    private final int ordinal;
    private final Description description;
    private final ResourceLocation resource;

    private RecipeStep(@NonNull final Builder builder) {
        this.ordinal = builder.ordinal;
        description = new Description(builder.shortDescription, builder.fullDescription);
        resource = new ResourceLocation(builder.videoUrl, builder.thumbnailUrl);
    }

    public int getOrdinal() {
        return ordinal;
    }

    @NonNull
    public String getShortDescription() {
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
        String getBrief() {
            return brief;
        }

        @NonNull
        String getFull() {
            return full;
        }
    }

    private static final class ResourceLocation {
        private final String video;
        private final String thumbnail;

        ResourceLocation(@Nullable final String video, @Nullable final String thumbnail) {
            this.video = video;
            this.thumbnail = thumbnail;
        }

        @Nullable
        String getVideo() {
            return video;
        }

        @Nullable
        String getThumbnail() {
            return thumbnail;
        }
    }

    public static final class Builder {
        private final int ordinal;
        private String shortDescription;
        private String fullDescription;
        private String videoUrl;
        private String thumbnailUrl;

        private Builder(int ordinal) {
            this.ordinal = ordinal;
        }

        public Builder setShortDescription(String shortDescription) {
            this.shortDescription = shortDescription;
            return this;
        }

        public Builder setFullDescription(String fullDescription) {
            this.fullDescription = fullDescription;
            return this;
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

        public static Builder withOrdinalNumber(int ordinal) {
            return new Builder(ordinal);
        }
    }
}
