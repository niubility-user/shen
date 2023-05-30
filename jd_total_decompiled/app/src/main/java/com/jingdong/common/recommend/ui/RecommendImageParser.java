package com.jingdong.common.recommend.ui;

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
public class RecommendImageParser {
    private Context mContext;
    private TextView mTextView;

    public RecommendImageParser(TextView textView, Context context) {
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
            int width = urlBitmap.getWidth() / urlBitmap.getHeight();
            levelListDrawable.addLevel(1, 1, new BitmapDrawable(this.mContext.getResources(), urlBitmap));
            levelListDrawable.setBounds(0, 0, width * i2, i2);
            levelListDrawable.setLevel(1);
            return levelListDrawable;
        }
        JDImageLoader.getBitmap(str, null, new ImageRequestListener<Bitmap>() { // from class: com.jingdong.common.recommend.ui.RecommendImageParser.1
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
                int width2 = bitmap.getWidth() / bitmap.getHeight();
                levelListDrawable.addLevel(1, 1, new BitmapDrawable(RecommendImageParser.this.mContext.getResources(), bitmap));
                LevelListDrawable levelListDrawable2 = levelListDrawable;
                int i3 = i2;
                levelListDrawable2.setBounds(0, 0, width2 * i3, i3);
                levelListDrawable.setLevel(1);
                if (RecommendImageParser.this.mTextView instanceof AutoWarpTextView) {
                    ((AutoWarpTextView) RecommendImageParser.this.mTextView).splitText(RecommendImageParser.this.mTextView.getWidth());
                } else {
                    RecommendImageParser.this.mTextView.setText(RecommendImageParser.this.mTextView.getText());
                }
                RecommendImageParser.this.mTextView.invalidate();
            }
        }, UiThreadImmediateExecutorService.getInstance());
        return levelListDrawable;
    }
}
