package com.jingdong.common.ui;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.NinePatch;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Rect;
import android.view.View;
import android.widget.ImageView;
import com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.res.StringUtil;
import com.jingdong.jdsdk.utils.cache.GlobalImageCache;
import com.jingdong.jdsdk.widget.ExceptionDrawable;

/* loaded from: classes6.dex */
public class HandlerRecycleBitmapDrawable extends ExceptionDrawable implements ImageUtil.ImageLoadListener {
    private static final String TAG = "HandlerRecycleBitmapDra";
    private Bitmap bitmap;
    private Rect bitmapRect;
    private HttpGroup group;
    private IMyActivity myActivity;
    private boolean needPadding;
    private NinePatch np;
    private String url;
    private ImageView view;

    public HandlerRecycleBitmapDrawable(Bitmap bitmap, IMyActivity iMyActivity) {
        super(iMyActivity.getThisActivity(), StringUtil.app_name);
        this.bitmap = null;
        this.myActivity = null;
        this.bitmapRect = null;
        this.needPadding = true;
        setBitmap(bitmap);
        this.myActivity = iMyActivity;
        this.bitmapRect = new Rect();
    }

    @Override // com.jingdong.jdsdk.widget.ExceptionDrawable, android.graphics.drawable.Drawable
    public synchronized void draw(Canvas canvas) {
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, 3));
        NinePatch ninePatch = this.np;
        if (ninePatch != null) {
            ninePatch.draw(canvas, getBounds());
        }
        Bitmap bitmap = this.bitmap;
        if (bitmap != null && !bitmap.isRecycled()) {
            Rect copyBounds = copyBounds();
            if (this.needPadding) {
                copyBounds.set(copyBounds.left + 2, copyBounds.top + 2, copyBounds.right - 2, copyBounds.bottom - 2);
            } else {
                copyBounds.set(copyBounds.left, copyBounds.top, copyBounds.right, copyBounds.bottom);
            }
            this.bitmapRect.set(0, 0, this.bitmap.getWidth(), this.bitmap.getHeight());
            canvas.save();
            canvas.drawBitmap(this.bitmap, this.bitmapRect, copyBounds, this.paint);
            canvas.restore();
        } else {
            JDImageUtils.loadImage(this.url, this.view, new JDSimpleImageLoadingListener() { // from class: com.jingdong.common.ui.HandlerRecycleBitmapDrawable.1
                {
                    HandlerRecycleBitmapDrawable.this = this;
                }

                @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingComplete(String str, View view, Bitmap bitmap2) {
                    if (bitmap2 == null || HandlerRecycleBitmapDrawable.this.myActivity == null) {
                        return;
                    }
                    HandlerRecycleBitmapDrawable.this.setBitmap(bitmap2);
                    HandlerRecycleBitmapDrawable.this.myActivity.post(new Runnable() { // from class: com.jingdong.common.ui.HandlerRecycleBitmapDrawable.1.1
                        {
                            AnonymousClass1.this = this;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            HandlerRecycleBitmapDrawable.this.invalidateSelf();
                        }
                    });
                }
            });
        }
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    @Override // com.jingdong.jdsdk.widget.ExceptionDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public void initLoad(ImageView imageView, HttpGroup httpGroup, String str) {
        this.view = imageView;
        this.group = httpGroup;
        this.url = str;
    }

    @Override // com.jingdong.common.utils.ImageUtil.ImageLoadListener
    public void onError(GlobalImageCache.BitmapDigest bitmapDigest) {
    }

    @Override // com.jingdong.common.utils.ImageUtil.ImageLoadListener
    public void onProgress(GlobalImageCache.BitmapDigest bitmapDigest, int i2, int i3) {
    }

    @Override // com.jingdong.common.utils.ImageUtil.ImageLoadListener
    public void onStart(GlobalImageCache.BitmapDigest bitmapDigest) {
    }

    @Override // com.jingdong.common.utils.ImageUtil.ImageLoadListener
    public void onSuccess(GlobalImageCache.BitmapDigest bitmapDigest, Bitmap bitmap) {
        if (bitmap == null || this.myActivity == null) {
            return;
        }
        setBitmap(bitmap);
        this.myActivity.post(new Runnable() { // from class: com.jingdong.common.ui.HandlerRecycleBitmapDrawable.2
            {
                HandlerRecycleBitmapDrawable.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                HandlerRecycleBitmapDrawable.this.invalidateSelf();
            }
        });
    }

    @Override // com.jingdong.jdsdk.widget.ExceptionDrawable, android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
    }

    public void setBackGround(Bitmap bitmap) {
        if (bitmap != null && !bitmap.isRecycled()) {
            this.np = new NinePatch(bitmap, bitmap.getNinePatchChunk(), null);
        } else {
            this.np = null;
        }
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    @Override // com.jingdong.jdsdk.widget.ExceptionDrawable, android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    public void setNeedPadding(boolean z) {
        this.needPadding = z;
    }
}
