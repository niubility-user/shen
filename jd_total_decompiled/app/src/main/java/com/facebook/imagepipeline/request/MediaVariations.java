package com.facebook.imagepipeline.request;

import android.net.Uri;
import com.facebook.common.internal.Objects;
import com.facebook.imagepipeline.request.ImageRequest;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
/* loaded from: classes.dex */
public class MediaVariations {
    public static final String SOURCE_ID_EXTRACTOR = "id_extractor";
    public static final String SOURCE_IMAGE_REQUEST = "request";
    public static final String SOURCE_INDEX_DB = "index_db";
    private final boolean mForceRequestForSpecifiedUri;
    private final String mMediaId;
    private final String mSource;
    @Nullable
    private final List<Variant> mVariants;

    /* loaded from: classes.dex */
    public static class Builder {
        private boolean mForceRequestForSpecifiedUri;
        private final String mMediaId;
        private String mSource;
        private List<Variant> mVariants;

        private Builder(String str) {
            this.mForceRequestForSpecifiedUri = false;
            this.mSource = "request";
            this.mMediaId = str;
        }

        public Builder addVariant(Uri uri, int i2, int i3) {
            return addVariant(uri, i2, i3, null);
        }

        public Builder addVariant(Uri uri, int i2, int i3, ImageRequest.CacheChoice cacheChoice) {
            if (this.mVariants == null) {
                this.mVariants = new ArrayList();
            }
            this.mVariants.add(new Variant(uri, i2, i3, cacheChoice));
            return this;
        }

        public MediaVariations build() {
            return new MediaVariations(this);
        }

        public Builder setForceRequestForSpecifiedUri(boolean z) {
            this.mForceRequestForSpecifiedUri = z;
            return this;
        }

        public Builder setSource(String str) {
            this.mSource = str;
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface Source {
    }

    /* loaded from: classes.dex */
    public static final class Variant {
        @Nullable
        private final ImageRequest.CacheChoice mCacheChoice;
        private final int mHeight;
        private final Uri mUri;
        private final int mWidth;

        public Variant(Uri uri, int i2, int i3) {
            this(uri, i2, i3, null);
        }

        public Variant(Uri uri, int i2, int i3, @Nullable ImageRequest.CacheChoice cacheChoice) {
            this.mUri = uri;
            this.mWidth = i2;
            this.mHeight = i3;
            this.mCacheChoice = cacheChoice;
        }

        public boolean equals(Object obj) {
            if (obj instanceof Variant) {
                Variant variant = (Variant) obj;
                return Objects.equal(this.mUri, variant.mUri) && this.mWidth == variant.mWidth && this.mHeight == variant.mHeight && this.mCacheChoice == variant.mCacheChoice;
            }
            return false;
        }

        @Nullable
        public ImageRequest.CacheChoice getCacheChoice() {
            return this.mCacheChoice;
        }

        public int getHeight() {
            return this.mHeight;
        }

        public Uri getUri() {
            return this.mUri;
        }

        public int getWidth() {
            return this.mWidth;
        }

        public int hashCode() {
            return (((this.mUri.hashCode() * 31) + this.mWidth) * 31) + this.mHeight;
        }

        public String toString() {
            return String.format(null, "%dx%d %s %s", Integer.valueOf(this.mWidth), Integer.valueOf(this.mHeight), this.mUri, this.mCacheChoice);
        }
    }

    private MediaVariations(Builder builder) {
        this.mMediaId = builder.mMediaId;
        this.mVariants = builder.mVariants;
        this.mForceRequestForSpecifiedUri = builder.mForceRequestForSpecifiedUri;
        this.mSource = builder.mSource;
    }

    @Nullable
    public static MediaVariations forMediaId(@Nullable String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        return newBuilderForMediaId(str).build();
    }

    public static Builder newBuilderForMediaId(String str) {
        return new Builder(str);
    }

    public boolean equals(Object obj) {
        if (obj instanceof MediaVariations) {
            MediaVariations mediaVariations = (MediaVariations) obj;
            return Objects.equal(this.mMediaId, mediaVariations.mMediaId) && this.mForceRequestForSpecifiedUri == mediaVariations.mForceRequestForSpecifiedUri && Objects.equal(this.mVariants, mediaVariations.mVariants);
        }
        return false;
    }

    public String getMediaId() {
        return this.mMediaId;
    }

    public List<Variant> getSortedVariants(Comparator<Variant> comparator) {
        int variantsCount = getVariantsCount();
        if (variantsCount == 0) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(variantsCount);
        for (int i2 = 0; i2 < variantsCount; i2++) {
            arrayList.add(this.mVariants.get(i2));
        }
        Collections.sort(arrayList, comparator);
        return arrayList;
    }

    public String getSource() {
        return this.mSource;
    }

    public Variant getVariant(int i2) {
        return this.mVariants.get(i2);
    }

    public int getVariantsCount() {
        List<Variant> list = this.mVariants;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public int hashCode() {
        return Objects.hashCode(this.mMediaId, Boolean.valueOf(this.mForceRequestForSpecifiedUri), this.mVariants, this.mSource);
    }

    public boolean shouldForceRequestForSpecifiedUri() {
        return this.mForceRequestForSpecifiedUri;
    }

    public String toString() {
        return String.format(null, "%s-%b-%s-%s", this.mMediaId, Boolean.valueOf(this.mForceRequestForSpecifiedUri), this.mVariants, this.mSource);
    }
}
