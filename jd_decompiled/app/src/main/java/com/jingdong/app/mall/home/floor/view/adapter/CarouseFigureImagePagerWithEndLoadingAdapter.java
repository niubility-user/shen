package com.jingdong.app.mall.home.floor.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.ctrl.f;
import com.jingdong.app.mall.utils.ui.view.CarouseFigureImagePagerAdapter;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener;
import com.jingdong.app.util.image.placeholder.JDPlaceholderDrawable;
import com.jingdong.common.utils.JDImageUtils;

/* loaded from: classes4.dex */
public class CarouseFigureImagePagerWithEndLoadingAdapter extends CarouseFigureImagePagerAdapter {
    protected b a;

    /* loaded from: classes4.dex */
    class a extends JDSimpleImageLoadingListener {
        a() {
        }

        @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingComplete(String str, View view, Bitmap bitmap) {
            CarouseFigureImagePagerWithEndLoadingAdapter carouseFigureImagePagerWithEndLoadingAdapter = CarouseFigureImagePagerWithEndLoadingAdapter.this;
            if (carouseFigureImagePagerWithEndLoadingAdapter.a != null) {
                CarouseFigureImagePagerWithEndLoadingAdapter.this.a.onPagerEndLoading(carouseFigureImagePagerWithEndLoadingAdapter.getRealIndex(view.getId()));
            }
        }
    }

    /* loaded from: classes4.dex */
    public interface b {
        void onPagerEndLoading(int i2);
    }

    public CarouseFigureImagePagerWithEndLoadingAdapter(Context context, boolean z, CarouseFigureImagePagerAdapter.CarouseFigureImageAdapterListener carouseFigureImageAdapterListener, b bVar) {
        super(context, z, carouseFigureImageAdapterListener);
        this.a = null;
        this.a = bVar;
    }

    public void b(ImageView imageView, String str, JDDisplayImageOptions jDDisplayImageOptions, JDImageLoadingListener jDImageLoadingListener) {
        if (this.displayOptions == null) {
            if (jDDisplayImageOptions == null) {
                this.displayOptions = f.a().resetViewBeforeLoading(false).showImageForEmptyUri(new JDPlaceholderDrawable(21)).showImageOnLoading(new JDPlaceholderDrawable(21)).showImageOnFail(new JDPlaceholderDrawable(21)).setPlaceholder(21);
            } else {
                this.displayOptions = jDDisplayImageOptions;
            }
        }
        this.displayOptions.bitmapConfig(Bitmap.Config.RGB_565);
        int i2 = CarouseFigureImagePagerAdapter.LAST_URL;
        Object tag = imageView.getTag(i2);
        Object tag2 = imageView.getTag(JDImageUtils.STATUS_TAG);
        if (tag != null && str != null && str.equals(tag)) {
            if (tag2 == null) {
                return;
            }
            if (tag2.equals(2) && jDImageLoadingListener != null) {
                jDImageLoadingListener.onLoadingComplete(str, imageView, null);
            }
            if (!tag2.equals(3)) {
                return;
            }
        }
        imageView.setTag(i2, str);
        imageView.setContentDescription("\u8425\u9500\u6d3b\u52a8");
        if (tag != null) {
            e.j(str, imageView, this.displayOptions, false, jDImageLoadingListener, null);
        } else {
            e.j(str, imageView, this.displayOptions, true, jDImageLoadingListener, null);
        }
    }

    @Override // com.jingdong.app.mall.utils.ui.view.CarouseFigureImagePagerAdapter
    public void displayImage(ImageView imageView, String str, JDDisplayImageOptions jDDisplayImageOptions) {
        if (this.a == null) {
            super.displayImage(imageView, str, jDDisplayImageOptions);
        } else {
            b(imageView, str, jDDisplayImageOptions, new a());
        }
    }
}
