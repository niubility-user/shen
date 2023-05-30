package com.jingdong.common.utils.adapter;

import android.graphics.Bitmap;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.common.utils.MySimpleAdapter;
import com.jingdong.common.utils.SimpleBeanAdapter;
import com.jingdong.common.utils.adapter.SimpleImageProcessor;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.cache.GlobalImageCache;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes6.dex */
public class ImageLoader implements HttpGroup.OnCommonListener {
    private SimpleImageProcessor.OnLoadCompleteListener completeListener;
    private MySimpleAdapter.ImageProcessor imageProcessor;
    private GlobalImageCache.ImageState imageState;
    private Boolean manualGetImage;
    private SimpleBeanAdapter.SubViewHolder subViewHolder;

    public ImageLoader(SimpleBeanAdapter.SubViewHolder subViewHolder, GlobalImageCache.ImageState imageState, MySimpleAdapter.ImageProcessor imageProcessor, SimpleImageProcessor.OnLoadCompleteListener onLoadCompleteListener) {
        this.manualGetImage = Boolean.FALSE;
        this.imageState = imageState;
        this.imageProcessor = imageProcessor;
        SimpleBeanAdapter.SubViewHolder subViewHolder2 = new SimpleBeanAdapter.SubViewHolder();
        this.subViewHolder = subViewHolder2;
        this.completeListener = onLoadCompleteListener;
        subViewHolder2.setAdapter(subViewHolder.getAdapter());
        this.subViewHolder.setPosition(subViewHolder.getPosition());
        this.subViewHolder.setSubViewId(subViewHolder.getSubViewId());
        this.subViewHolder.setItemData(subViewHolder.getItemData());
        this.subViewHolder.setSubData(subViewHolder.getSubData());
        Object moreParameter = subViewHolder.getMoreParameter(SimpleBeanAdapter.SubViewHolder.MORE_PARAMETER_MANUAL_GET_IMAGE);
        if (moreParameter == null || !(moreParameter instanceof Boolean)) {
            return;
        }
        this.manualGetImage = (Boolean) moreParameter;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ImageLoader) {
            return this.subViewHolder.getPosition() == ((ImageLoader) obj).subViewHolder.getPosition();
        }
        return super.equals(obj);
    }

    public void gc() {
        this.imageState = null;
        this.imageProcessor = null;
        this.subViewHolder = null;
    }

    public void load() {
        GlobalImageCache.BitmapDigest bitmapDigest = GlobalImageCache.getBitmapDigest(this.imageState);
        if (bitmapDigest == null) {
            SimpleImageProcessor.OnLoadCompleteListener onLoadCompleteListener = this.completeListener;
            if (onLoadCompleteListener != null) {
                onLoadCompleteListener.onCompleted();
                return;
            }
            return;
        }
        this.imageState.loading();
        String url = bitmapDigest.getUrl();
        if (OKLog.D) {
            OKLog.d(ImageLoader.class.getName(), "load() url -->> " + url);
            OKLog.d(ImageLoader.class.getName(), "load() position -->> " + this.subViewHolder.getPosition());
        }
        SimpleBeanAdapter adapter = this.subViewHolder.getAdapter();
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setEffect(0);
        httpSetting.setForeverCache(adapter.isForeverCacheImage());
        httpSetting.setUrl(url);
        if (!this.manualGetImage.booleanValue() && adapter.allowNoImageAndIsNoImage()) {
            httpSetting.setCacheMode(1);
            this.subViewHolder.putMoreParameter(SimpleBeanAdapter.SubViewHolder.MORE_PARAMETER_LOCAL_LOAD_IMAGE, Boolean.TRUE);
        }
        if (adapter.isAssetsCache()) {
            httpSetting.setCacheMode(3);
        }
        httpSetting.setListener(this);
        adapter.getAdapterHelper().getImageHttpGroup().add(httpSetting);
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
    public void onEnd(HttpResponse httpResponse) {
        SimpleImageProcessor.OnLoadCompleteListener onLoadCompleteListener;
        if (OKLog.D) {
            OKLog.d(ImageLoader.class.getName(), "onEnd() position -->> " + this.subViewHolder.getPosition());
        }
        try {
            GlobalImageCache.BitmapDigest bitmapDigest = GlobalImageCache.getBitmapDigest(this.imageState);
            if (OKLog.D) {
                OKLog.d(ImageLoader.class.getName(), "onEnd() bitmapDigest -->> " + bitmapDigest);
            }
            if (bitmapDigest == null) {
                if (OKLog.D) {
                    OKLog.d(ImageLoader.class.getName(), "onEnd() bitmapDigest is null position -->> " + this.subViewHolder.getPosition());
                }
                if (onLoadCompleteListener != null) {
                    return;
                }
                return;
            }
            Bitmap create = this.imageProcessor.create(ImageUtil.InputWay.createInputWay(httpResponse), bitmapDigest);
            if (create == null) {
                if (OKLog.D) {
                    OKLog.d(ImageLoader.class.getName(), "onEnd() bitmap is null position -->> " + this.subViewHolder.getPosition());
                }
                this.imageState.none();
                gc();
                SimpleImageProcessor.OnLoadCompleteListener onLoadCompleteListener2 = this.completeListener;
                if (onLoadCompleteListener2 != null) {
                    onLoadCompleteListener2.onCompleted();
                    return;
                }
                return;
            }
            this.imageState.success(create);
            this.subViewHolder.putMoreParameter(SimpleBeanAdapter.SubViewHolder.MORE_PARAMETER_LOADED, Boolean.TRUE);
            this.imageProcessor.show(this.subViewHolder, this.imageState);
            gc();
            SimpleImageProcessor.OnLoadCompleteListener onLoadCompleteListener3 = this.completeListener;
            if (onLoadCompleteListener3 != null) {
                onLoadCompleteListener3.onCompleted();
            }
        } finally {
            gc();
            onLoadCompleteListener = this.completeListener;
            if (onLoadCompleteListener != null) {
                onLoadCompleteListener.onCompleted();
            }
        }
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
    public void onError(HttpError httpError) {
        if (OKLog.D) {
            OKLog.d(ImageLoader.class.getName(), "onError() position -->> " + this.subViewHolder.getPosition());
        }
        try {
            this.imageState.failure();
            this.subViewHolder.putMoreParameter(SimpleBeanAdapter.SubViewHolder.MORE_PARAMETER_LOADED, Boolean.TRUE);
            this.imageProcessor.show(this.subViewHolder, this.imageState);
        } finally {
            gc();
            SimpleImageProcessor.OnLoadCompleteListener onLoadCompleteListener = this.completeListener;
            if (onLoadCompleteListener != null) {
                onLoadCompleteListener.onCompleted();
            }
        }
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
    public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
    }

    public String toString() {
        return String.format("ImageLoader:pos=%d", Integer.valueOf(this.subViewHolder.getPosition()));
    }
}
