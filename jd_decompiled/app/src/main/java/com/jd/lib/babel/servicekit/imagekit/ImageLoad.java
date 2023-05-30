package com.jd.lib.babel.servicekit.imagekit;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.jd.lib.babel.servicekit.BabelServer;
import com.jd.lib.babel.servicekit.imagekit.ImageArr;

/* loaded from: classes13.dex */
public class ImageLoad {
    private ImageArr.Builder mBuilder;

    private ImageLoad() {
    }

    public static void loadImageArr(ImageArr imageArr) {
        new ImageLoad().load(imageArr);
    }

    public static ImageView newImageView(Context context) {
        return newImageView(context, null);
    }

    public static ImageLoad with(ImageView imageView) {
        return new ImageLoad(imageView);
    }

    public ImageLoad bitmapConfig(Bitmap.Config config) {
        this.mBuilder.setBitmapConfig(config);
        return this;
    }

    public ImageLoad cornersRadii(float f2) {
        this.mBuilder.setCornersRadii(f2);
        return this;
    }

    public ImageLoad isScale(boolean z) {
        this.mBuilder.setScale(z);
        return this;
    }

    public void load(ImageArr imageArr) {
        BabelServer.get().getImageKitServer().displayImage(imageArr);
    }

    public ImageLoad loadingListener(BabelImageLoadingListener babelImageLoadingListener) {
        this.mBuilder.setLoadingListener(babelImageLoadingListener);
        return this;
    }

    public ImageLoad needImageOnFail(boolean z) {
        this.mBuilder.setNeedImageOnFail(z);
        return this;
    }

    public ImageLoad needImageOnLoading(boolean z) {
        this.mBuilder.setNeedImageOnLoading(z);
        return this;
    }

    public ImageLoad roundAsCircle(boolean z) {
        this.mBuilder.setRoundAsCircle(z);
        return this;
    }

    public ImageLoad useOption(boolean z) {
        this.mBuilder.setUseOption(z);
        return this;
    }

    private ImageLoad(ImageView imageView) {
        this.mBuilder = ImageArr.Builder.create(imageView);
    }

    public static ImageView newImageView(Context context, AttributeSet attributeSet) {
        return BabelServer.get().getImageKitServer().newImageView(context, attributeSet);
    }

    public static ImageLoad with(ImageWraper imageWraper) {
        if (imageWraper != null) {
            return new ImageLoad(imageWraper);
        }
        throw new RuntimeException("ImageWraper not null!");
    }

    public ImageLoad cornersRadii(float f2, float f3, float f4, float f5) {
        this.mBuilder.setCornersRadii(f2, f3, f5, f4);
        return this;
    }

    public void load(String str) {
        this.mBuilder.setUrl(str);
        BabelServer.get().getImageKitServer().displayImage(this.mBuilder.buid());
    }

    private ImageLoad(ImageWraper imageWraper) {
        ImageArr.Builder create = ImageArr.Builder.create(imageWraper.getImageView());
        this.mBuilder = create;
        create.setRoundAsCircle(imageWraper.isRoundAsCircle());
        this.mBuilder.setCornersRadii(imageWraper.getCornersRadii());
    }
}
