package com.jd.lib.babel.task.viewkit;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.jd.lib.babel.servicekit.imagekit.ImageLoad;
import com.jd.viewkit.thirdinterface.JDViewKitImageService;
import com.jd.viewkit.thirdinterface.model.JDViewKitImageModel;

/* loaded from: classes14.dex */
public class JDVKitImageServiceImpl implements JDViewKitImageService {
    private static JDVKitImageServiceImpl sJDViewKitImageService;

    private JDVKitImageServiceImpl() {
    }

    public static JDVKitImageServiceImpl getInstance() {
        if (sJDViewKitImageService == null) {
            synchronized (JDVKitImageServiceImpl.class) {
                if (sJDViewKitImageService == null) {
                    sJDViewKitImageService = new JDVKitImageServiceImpl();
                }
            }
        }
        return sJDViewKitImageService;
    }

    @Override // com.jd.viewkit.thirdinterface.JDViewKitImageService
    public void downloadImage(Context context, String str, int i2, int i3, JDViewKitImageService.DownloadImageListener downloadImageListener) {
    }

    @Override // com.jd.viewkit.thirdinterface.JDViewKitImageService
    public void fillImageView(ImageView imageView, JDViewKitImageModel jDViewKitImageModel) {
        if (imageView == null || jDViewKitImageModel == null) {
            return;
        }
        imageView.setScaleType(jDViewKitImageModel.getScaleType());
        ImageLoad.with(imageView).cornersRadii(jDViewKitImageModel.getRadius()).needImageOnLoading(jDViewKitImageModel.isUseDefault()).needImageOnFail(jDViewKitImageModel.isUseDefault()).load(jDViewKitImageModel.getImageUrlStr());
    }

    @Override // com.jd.viewkit.thirdinterface.JDViewKitImageService
    public Drawable getPlaceholderDrawable(Context context, int i2) {
        return null;
    }

    @Override // com.jd.viewkit.thirdinterface.JDViewKitImageService
    public ImageView getThirdImageView(Context context) {
        return ImageLoad.newImageView(context);
    }
}
