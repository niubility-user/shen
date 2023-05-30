package com.jingdong.app.mall.im.business;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.NinePatch;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.view.View;
import androidx.annotation.NonNull;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.imagepipeline.image.EncodedImage;
import com.jd.mobile.image.ImageRequestListener;
import com.jd.mobile.image.JDImageLoader;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.remoteimage.CalorieImageUtil;
import com.jingdong.remoteimage.RemoteImageManager;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.service.impl.IMRemoteImage;

/* loaded from: classes4.dex */
public class w extends IMRemoteImage {
    private static final String a = "w";

    /* loaded from: classes4.dex */
    class a implements ImageRequestListener<EncodedImage> {
        final /* synthetic */ Context a;
        final /* synthetic */ View b;

        a(w wVar, Context context, View view) {
            this.a = context;
            this.b = view;
        }

        @Override // com.jd.mobile.image.ImageRequestListener
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void onSuccess(EncodedImage encodedImage) {
            Drawable bitmapDrawable;
            Bitmap decodeStream = BitmapFactory.decodeStream(encodedImage.getInputStream());
            byte[] ninePatchChunk = decodeStream.getNinePatchChunk();
            if (ninePatchChunk != null && NinePatch.isNinePatchChunk(ninePatchChunk)) {
                bitmapDrawable = new NinePatchDrawable(this.a.getResources(), new NinePatch(decodeStream, ninePatchChunk, null));
            } else {
                bitmapDrawable = new BitmapDrawable(this.a.getResources(), decodeStream);
            }
            this.b.setBackground(bitmapDrawable);
        }

        @Override // com.jd.mobile.image.ImageRequestListener
        public void onCancel() {
        }

        @Override // com.jd.mobile.image.ImageRequestListener
        public void onFailure(Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.jingdong.service.impl.IMRemoteImage, com.jingdong.service.service.RemoteImageService
    public void display9Background(Context context, View view, String str) {
        String imageUrlSync = CalorieImageUtil.getImageUrlSync(str);
        JDDisplayImageOptions jDDisplayImageOptions = new JDDisplayImageOptions();
        jDDisplayImageOptions.setPlaceholder(18);
        JDImageLoader.getEncodedImage(imageUrlSync, jDDisplayImageOptions, new a(this, context, view), UiThreadImmediateExecutorService.getInstance());
    }

    @Override // com.jingdong.service.impl.IMRemoteImage, com.jingdong.service.service.RemoteImageService
    public String getImageUrlById(@NonNull String str) {
        OKLog.d("bundleicssdkservice", a + "---getImageUrlById\uff1a" + str);
        return RemoteImageManager.getImageUrlById(str);
    }
}
