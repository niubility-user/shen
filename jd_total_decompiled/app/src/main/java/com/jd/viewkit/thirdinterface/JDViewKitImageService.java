package com.jd.viewkit.thirdinterface;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.jd.viewkit.thirdinterface.model.JDViewKitImageModel;

/* loaded from: classes18.dex */
public interface JDViewKitImageService {
    public static final int PLACEHOLDER_MID = 1;

    /* loaded from: classes18.dex */
    public interface DownloadImageListener {
        void onEnd(String str, Bitmap bitmap);

        void onError(String str);
    }

    void downloadImage(Context context, String str, int i2, int i3, DownloadImageListener downloadImageListener);

    void fillImageView(ImageView imageView, JDViewKitImageModel jDViewKitImageModel);

    Drawable getPlaceholderDrawable(Context context, int i2);

    ImageView getThirdImageView(Context context);
}
