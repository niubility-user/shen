package com.facebook.react.views.text;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.SparseArray;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

/* loaded from: classes12.dex */
public class ReactFontManager {
    private static final String[] EXTENSIONS = {"", "_bold", "_italic", "_bold_italic"};
    private static final String[] FILE_EXTENSIONS = {".ttf", ".otf"};
    private static final String FONTS_ASSET_PATH = "fonts/";
    private static ReactFontManager sReactFontManagerInstance;
    private Map<String, FontFamily> mFontCache = new HashMap();

    /* loaded from: classes12.dex */
    private static class FontFamily {
        private SparseArray<Typeface> mTypefaceSparseArray;

        public Typeface getTypeface(int i2) {
            return this.mTypefaceSparseArray.get(i2);
        }

        public void setTypeface(int i2, Typeface typeface) {
            this.mTypefaceSparseArray.put(i2, typeface);
        }

        private FontFamily() {
            this.mTypefaceSparseArray = new SparseArray<>(4);
        }
    }

    private ReactFontManager() {
    }

    @Nullable
    private static Typeface createTypeface(String str, int i2, AssetManager assetManager) {
        String str2 = EXTENSIONS[i2];
        for (String str3 : FILE_EXTENSIONS) {
            try {
                return Typeface.createFromAsset(assetManager, FONTS_ASSET_PATH + str + str2 + str3);
            } catch (RuntimeException unused) {
            }
        }
        return Typeface.create(str, i2);
    }

    public static ReactFontManager getInstance() {
        if (sReactFontManagerInstance == null) {
            sReactFontManagerInstance = new ReactFontManager();
        }
        return sReactFontManagerInstance;
    }

    @Nullable
    public Typeface getTypeface(String str, int i2, AssetManager assetManager) {
        FontFamily fontFamily = this.mFontCache.get(str);
        if (fontFamily == null) {
            fontFamily = new FontFamily();
            this.mFontCache.put(str, fontFamily);
        }
        Typeface typeface = fontFamily.getTypeface(i2);
        if (typeface == null && (typeface = createTypeface(str, i2, assetManager)) != null) {
            fontFamily.setTypeface(i2, typeface);
        }
        return typeface;
    }

    public void setTypeface(String str, int i2, Typeface typeface) {
        if (typeface != null) {
            FontFamily fontFamily = this.mFontCache.get(str);
            if (fontFamily == null) {
                fontFamily = new FontFamily();
                this.mFontCache.put(str, fontFamily);
            }
            fontFamily.setTypeface(i2, typeface);
        }
    }
}
