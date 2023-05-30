package com.jd.lib.productdetail.core.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.NinePatch;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.net.Uri;
import android.view.View;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.imagepipeline.image.EncodedImage;
import com.jd.mobile.image.ImageRequestListener;
import com.jd.mobile.image.JDImageLoader;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.remoteimage.CalorieImageUtil;

/* loaded from: classes15.dex */
public class PDCalorieImageUtil {
    private static final String PD_CALORIE_MODULEID_1124 = "124";
    private String moduleid = PD_CALORIE_MODULEID_1124;
    private JDDisplayImageOptions options = null;
    private ImageRequestListener imageRequestListener = null;

    public static PDCalorieImageUtil get() {
        return new PDCalorieImageUtil();
    }

    public PDCalorieImageUtil display(String str, View view) {
        JDImageLoader.display(CalorieImageUtil.getRemoteImageUriSync(this.moduleid, str), view, this.options, this.imageRequestListener);
        return this;
    }

    public PDCalorieImageUtil displayPot9(final Context context, String str, final View view) {
        JDImageLoader.getEncodedImage(CalorieImageUtil.getImageUrlSync(this.moduleid + ":" + str), this.options, new ImageRequestListener<EncodedImage>() { // from class: com.jd.lib.productdetail.core.utils.PDCalorieImageUtil.1
            @Override // com.jd.mobile.image.ImageRequestListener
            public void onCancel() {
            }

            @Override // com.jd.mobile.image.ImageRequestListener
            public void onFailure(Throwable th) {
            }

            @Override // com.jd.mobile.image.ImageRequestListener
            public void onSuccess(EncodedImage encodedImage) {
                Drawable bitmapDrawable;
                Bitmap decodeStream = BitmapFactory.decodeStream(encodedImage.getInputStream());
                byte[] ninePatchChunk = decodeStream.getNinePatchChunk();
                if (ninePatchChunk != null && NinePatch.isNinePatchChunk(ninePatchChunk)) {
                    bitmapDrawable = new NinePatchDrawable(context.getResources(), new NinePatch(decodeStream, ninePatchChunk, null));
                } else {
                    bitmapDrawable = new BitmapDrawable(context.getResources(), decodeStream);
                }
                view.setBackgroundDrawable(bitmapDrawable);
            }
        }, UiThreadImmediateExecutorService.getInstance());
        return this;
    }

    public Uri getImageUri(String str) {
        return CalorieImageUtil.getRemoteImageUriSync(this.moduleid, str);
    }

    public PDCalorieImageUtil imageOptions(JDDisplayImageOptions jDDisplayImageOptions) {
        this.options = jDDisplayImageOptions;
        return this;
    }

    public PDCalorieImageUtil loadListener(ImageRequestListener imageRequestListener) {
        this.imageRequestListener = imageRequestListener;
        return this;
    }

    public PDCalorieImageUtil moduleid(String str) {
        this.moduleid = str;
        return this;
    }
}
