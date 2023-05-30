package com.jingdong.jdsdk.utils.cache;

import android.graphics.Bitmap;
import com.facebook.common.logging.FLog;
import com.jingdong.JdImageToolKit;
import com.jingdong.sdk.platform.business.personal.R2;
import com.novoda.imageloader.core.cache.LruBitmapCache;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes14.dex */
public class GlobalImageCache {
    public static final int STATE_FAILURE = 2;
    public static final int STATE_LOADING = 1;
    public static final int STATE_NONE = 0;
    public static final int STATE_SUCCESS = 3;
    private static LruBitmapCache lruBitmapCache;
    private static final Map<BitmapDigest, ImageState> imageMap = new HashMap();
    private static final Map<ImageState, BitmapDigest> digestMap = new HashMap();

    /* loaded from: classes14.dex */
    public static class BitmapDigest {
        private String custom;
        private int height;
        private Map<String, Object> moreParameter;
        private int position;
        private int round;
        private String url;
        private int width;
        private boolean allowRecycle = true;
        private boolean large = false;

        public BitmapDigest(String str) {
            this.url = str;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && BitmapDigest.class == obj.getClass()) {
                BitmapDigest bitmapDigest = (BitmapDigest) obj;
                if (this.allowRecycle != bitmapDigest.allowRecycle) {
                    return false;
                }
                String str = this.custom;
                if (str == null) {
                    if (bitmapDigest.custom != null) {
                        return false;
                    }
                } else if (!str.equals(bitmapDigest.custom)) {
                    return false;
                }
                if (this.height == bitmapDigest.height && this.position == bitmapDigest.position && this.round == bitmapDigest.round) {
                    String str2 = this.url;
                    if (str2 == null) {
                        if (bitmapDigest.url != null) {
                            return false;
                        }
                    } else if (!str2.equals(bitmapDigest.url)) {
                        return false;
                    }
                    return this.width == bitmapDigest.width;
                }
                return false;
            }
            return false;
        }

        public String getCustom() {
            return this.custom;
        }

        public int getHeight() {
            return this.height;
        }

        public Object getMoreParameter(String str) {
            Map<String, Object> map = this.moreParameter;
            if (map == null) {
                return null;
            }
            return map.get(str);
        }

        public int getPosition() {
            return this.position;
        }

        public int getRound() {
            return this.round;
        }

        public String getUrl() {
            return this.url;
        }

        public int getWidth() {
            return this.width;
        }

        public int hashCode() {
            int i2 = ((this.allowRecycle ? R2.attr.layout_constraintWidth : R2.attr.layout_editor_absoluteX) + 31) * 31;
            String str = this.custom;
            int hashCode = (((((((i2 + (str == null ? 0 : str.hashCode())) * 31) + this.height) * 31) + this.position) * 31) + this.round) * 31;
            String str2 = this.url;
            return ((hashCode + (str2 != null ? str2.hashCode() : 0)) * 31) + this.width;
        }

        public boolean isAllowRecycle() {
            return this.allowRecycle;
        }

        public boolean isLarge() {
            return this.large;
        }

        public void putMoreParameter(String str, Object obj) {
            if (this.moreParameter == null) {
                this.moreParameter = new HashMap();
            }
            this.moreParameter.put(str, obj);
        }

        public void setAllowRecycle(boolean z) {
            this.allowRecycle = z;
        }

        public void setCustom(String str) {
            this.custom = str;
        }

        public void setHeight(int i2) {
            this.height = i2;
        }

        public void setLarge(boolean z) {
            this.large = z;
        }

        public void setPosition(int i2) {
            this.position = i2;
        }

        public void setRound(int i2) {
            this.round = i2;
        }

        public void setUrl(String str) {
            this.url = str;
        }

        public void setWidth(int i2) {
            this.width = i2;
        }
    }

    /* loaded from: classes14.dex */
    public static class ImageState {
        private int mState = 0;

        public void failure() {
            this.mState = 2;
        }

        public Bitmap getBitmap() {
            BitmapDigest bitmapDigest = GlobalImageCache.getBitmapDigest(this);
            if (bitmapDigest != null) {
                return GlobalImageCache.getLruBitmapCache().get(bitmapDigest);
            }
            return null;
        }

        public int getState() {
            if (this.mState == 3 && getBitmap() == null) {
                this.mState = 0;
            }
            return this.mState;
        }

        public void loading() {
            FLog.d(GlobalImageCache.class.getName(), "loading() -->> ");
            this.mState = 1;
        }

        public void none() {
            this.mState = 0;
        }

        public void success(Bitmap bitmap) {
            FLog.d(GlobalImageCache.class.getName(), "success() b -->> " + bitmap);
            try {
                GlobalImageCache.getLruBitmapCache().put(GlobalImageCache.getBitmapDigest(this), bitmap);
                this.mState = 3;
            } catch (NullPointerException e2) {
                FLog.e(GlobalImageCache.class, e2.toString());
                failure();
            }
        }

        public String toString() {
            return "ImageState [bitmap=" + getBitmap() + ", mState=" + this.mState + "]";
        }
    }

    public static BitmapDigest getBitmapDigest(ImageState imageState) {
        return digestMap.get(imageState);
    }

    public static synchronized ImageState getImageState(BitmapDigest bitmapDigest) {
        ImageState imageState;
        synchronized (GlobalImageCache.class) {
            Map<BitmapDigest, ImageState> map = imageMap;
            imageState = map.get(bitmapDigest);
            if (imageState == null) {
                imageState = new ImageState();
                map.put(bitmapDigest, imageState);
                digestMap.put(imageState, bitmapDigest);
            }
        }
        return imageState;
    }

    public static synchronized LruBitmapCache getLruBitmapCache() {
        LruBitmapCache lruBitmapCache2;
        synchronized (GlobalImageCache.class) {
            if (lruBitmapCache == null) {
                lruBitmapCache = new LruBitmapCache(JdImageToolKit.getContext(), 30);
            }
            lruBitmapCache2 = lruBitmapCache;
        }
        return lruBitmapCache2;
    }

    public static void remove(BitmapDigest bitmapDigest) {
        FLog.d(GlobalImageCache.class.getName(), "remove() bitmapDigest -->> " + bitmapDigest);
        digestMap.remove(imageMap.remove(bitmapDigest));
    }
}
