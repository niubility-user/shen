package com.jingdong.common.utils.adapter;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import com.jingdong.common.utils.SimpleBeanAdapter;
import com.jingdong.common.utils.SimpleSubViewBinder;
import com.jingdong.common.utils.SubViewBinder;
import com.jingdong.jdsdk.utils.cache.GlobalImageCache;
import com.jingdong.sdk.oklog.OKLog;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes6.dex */
public class MyBitmapDrawable extends BitmapDrawable {
    private GlobalImageCache.BitmapDigest digest;
    private boolean isGc;
    private SimpleBeanAdapter.SubViewHolder subViewHolder;

    public MyBitmapDrawable(Resources resources, SimpleBeanAdapter.SubViewHolder subViewHolder, GlobalImageCache.BitmapDigest bitmapDigest, Bitmap bitmap) {
        super(resources, bitmap);
        this.subViewHolder = subViewHolder;
        this.digest = bitmapDigest;
    }

    @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        try {
            super.draw(canvas);
            if (OKLog.D) {
                OKLog.d(MyBitmapDrawable.class.getSimpleName(), "draw() position=" + this.subViewHolder.getPosition() + " super.draw() -->> ");
            }
        } catch (Throwable unused) {
            if (getBitmap() == null || !getBitmap().isRecycled()) {
                return;
            }
            if (this.isGc) {
                if (OKLog.D) {
                    OKLog.d(MyBitmapDrawable.class.getSimpleName(), "draw() isGc -->> ");
                    return;
                }
                return;
            }
            if (OKLog.D) {
                OKLog.d(MyBitmapDrawable.class.getSimpleName(), "draw() position=" + this.subViewHolder.getPosition() + " isRecycled -->> ");
            }
            SubViewBinder viewBinder = this.subViewHolder.getAdapter().getViewBinder();
            if (viewBinder instanceof SimpleSubViewBinder) {
                SimpleImageProcessor simpleImageProcessor = ((SimpleSubViewBinder) viewBinder).getSimpleImageProcessor();
                GlobalImageCache.ImageState imageState = GlobalImageCache.getImageState(this.digest);
                imageState.none();
                simpleImageProcessor.show(this.subViewHolder, imageState);
                gc();
            }
        }
    }

    public void gc() {
        this.subViewHolder = null;
        this.digest = null;
        this.isGc = true;
    }

    @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        return super.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.BitmapDrawable
    public int getGravity() {
        return super.getGravity();
    }

    @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return super.getIntrinsicHeight();
    }

    @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return super.getIntrinsicWidth();
    }

    @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return super.getOpacity();
    }

    @Override // android.graphics.drawable.BitmapDrawable
    public Shader.TileMode getTileModeX() {
        return super.getTileModeX();
    }

    @Override // android.graphics.drawable.BitmapDrawable
    public Shader.TileMode getTileModeY() {
        return super.getTileModeY();
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws XmlPullParserException, IOException {
        super.inflate(resources, xmlPullParser, attributeSet);
    }

    @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
    public Drawable mutate() {
        return super.mutate();
    }

    @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
    }

    @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        super.setAlpha(i2);
    }

    @Override // android.graphics.drawable.BitmapDrawable
    public void setAntiAlias(boolean z) {
        super.setAntiAlias(z);
    }

    @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        super.setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
    public void setDither(boolean z) {
        super.setDither(z);
    }

    @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
    public void setFilterBitmap(boolean z) {
        super.setFilterBitmap(z);
    }

    @Override // android.graphics.drawable.BitmapDrawable
    public void setGravity(int i2) {
        super.setGravity(i2);
    }

    @Override // android.graphics.drawable.BitmapDrawable
    public void setTargetDensity(Canvas canvas) {
        super.setTargetDensity(canvas);
    }

    @Override // android.graphics.drawable.BitmapDrawable
    public void setTileModeX(Shader.TileMode tileMode) {
        super.setTileModeX(tileMode);
    }

    @Override // android.graphics.drawable.BitmapDrawable
    public void setTileModeXY(Shader.TileMode tileMode, Shader.TileMode tileMode2) {
        super.setTileModeXY(tileMode, tileMode2);
    }

    @Override // android.graphics.drawable.BitmapDrawable
    public void setTargetDensity(DisplayMetrics displayMetrics) {
        super.setTargetDensity(displayMetrics);
    }

    @Override // android.graphics.drawable.BitmapDrawable
    public void setTargetDensity(int i2) {
        super.setTargetDensity(i2);
    }
}
