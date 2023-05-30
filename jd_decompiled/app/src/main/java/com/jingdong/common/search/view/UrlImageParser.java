package com.jingdong.common.search.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.text.TextUtils;
import android.widget.TextView;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.jd.mobile.image.ImageRequestListener;
import com.jd.mobile.image.JDImageLoader;
import com.jingdong.common.utils.JDImageUtils;
import java.io.File;

/* loaded from: classes6.dex */
public class UrlImageParser {
    private Context mContext;
    private TextView mTextView;

    public UrlImageParser(TextView textView, Context context) {
        this.mTextView = textView;
        this.mContext = context;
    }

    private Bitmap getUrlBitmap(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        File imageDiskCacheFile = JDImageUtils.getImageDiskCacheFile(str);
        if (imageDiskCacheFile != null) {
            return BitmapFactory.decodeFile(imageDiskCacheFile.getPath());
        }
        JDImageLoader.prefetchToDiskCache(str, null);
        return null;
    }

    public Drawable getDrawable(String str, final int i2) {
        final LevelListDrawable levelListDrawable = new LevelListDrawable();
        Bitmap urlBitmap = getUrlBitmap(str);
        if (urlBitmap != null && urlBitmap.getHeight() != 0) {
            levelListDrawable.addLevel(1, 1, new BitmapDrawable(this.mContext.getResources(), urlBitmap));
            levelListDrawable.setBounds(0, 0, (int) (i2 * ((urlBitmap.getWidth() * 1.0f) / urlBitmap.getHeight())), i2);
            levelListDrawable.setLevel(1);
            return levelListDrawable;
        }
        JDImageLoader.getBitmap(str, null, new ImageRequestListener<Bitmap>() { // from class: com.jingdong.common.search.view.UrlImageParser.1
            @Override // com.jd.mobile.image.ImageRequestListener
            public void onCancel() {
            }

            @Override // com.jd.mobile.image.ImageRequestListener
            public void onFailure(Throwable th) {
            }

            @Override // com.jd.mobile.image.ImageRequestListener
            public void onSuccess(Bitmap bitmap) {
                if (bitmap == null || bitmap.getHeight() == 0) {
                    return;
                }
                float width = (bitmap.getWidth() * 1.0f) / bitmap.getHeight();
                levelListDrawable.addLevel(1, 1, new BitmapDrawable(UrlImageParser.this.mContext.getResources(), bitmap));
                LevelListDrawable levelListDrawable2 = levelListDrawable;
                int i3 = i2;
                levelListDrawable2.setBounds(0, 0, (int) (i3 * width), i3);
                levelListDrawable.setLevel(1);
                UrlImageParser.this.mTextView.setText(UrlImageParser.this.mTextView.getText());
                UrlImageParser.this.mTextView.invalidate();
            }
        }, UiThreadImmediateExecutorService.getInstance());
        return levelListDrawable;
    }
}
