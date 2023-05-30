package com.jingdong.common.unification.album;

import android.net.Uri;
import android.widget.ImageView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.jingdong.common.R;

/* loaded from: classes6.dex */
public class UnImageTools {
    public static void loadImage(String str, ImageView imageView, ResizeOptions resizeOptions) {
        if (imageView != null && (imageView instanceof SimpleDraweeView)) {
            ImageRequest build = ImageRequestBuilder.newBuilderWithSource(Uri.parse(str)).setResizeOptions(resizeOptions).build();
            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) imageView;
            GenericDraweeHierarchy hierarchy = simpleDraweeView.getHierarchy();
            int i2 = R.drawable.pl_white_mid_raw;
            hierarchy.setPlaceholderImage(i2);
            hierarchy.setFailureImage(i2);
            simpleDraweeView.setController(Fresco.newDraweeControllerBuilder().setOldController(simpleDraweeView.getController()).setImageRequest(build).build());
        }
    }

    public static void loadImageWithAlbum(String str, ImageView imageView) {
        if (imageView == null) {
            return;
        }
        loadImage(str, imageView, new ResizeOptions(100, 100));
    }
}
