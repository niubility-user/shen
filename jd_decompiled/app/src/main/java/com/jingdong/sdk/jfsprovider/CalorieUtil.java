package com.jingdong.sdk.jfsprovider;

import android.graphics.Bitmap;
import android.view.View;
import com.facebook.imagepipeline.image.ImageInfo;
import com.jd.mobile.image.ImageRequestListener;
import com.jd.mobile.image.JDImageLoader;
import com.jingdong.app.util.image.JDDisplayImageOptions;

/* loaded from: classes7.dex */
public class CalorieUtil {
    private static final String TAG = "CalorieUtil";

    /* loaded from: classes7.dex */
    public interface IFetchForCalorie {
        void onFetch(Bitmap bitmap);
    }

    public static void fetchDiskImageForCalorie(String str, final IFetchForCalorie iFetchForCalorie) {
        JDImageLoader.getBitmap(str, null, new ImageRequestListener<Bitmap>() { // from class: com.jingdong.sdk.jfsprovider.CalorieUtil.1
            @Override // com.jd.mobile.image.ImageRequestListener
            public void onCancel() {
            }

            @Override // com.jd.mobile.image.ImageRequestListener
            public void onFailure(Throwable th) {
            }

            @Override // com.jd.mobile.image.ImageRequestListener
            public void onSuccess(Bitmap bitmap) {
                IFetchForCalorie iFetchForCalorie2 = IFetchForCalorie.this;
                if (iFetchForCalorie2 != null) {
                    iFetchForCalorie2.onFetch(bitmap);
                }
            }
        });
    }

    public static void prefetchToDiskForCalorie(String str) {
        JDImageLoader.prefetchToDiskCache(str, null);
    }

    public static void setBackgroundBitmapFromUrl(String str, View view) {
        JDImageLoader.display(str, view, (JDDisplayImageOptions) null, (ImageRequestListener<ImageInfo>) null);
    }
}
