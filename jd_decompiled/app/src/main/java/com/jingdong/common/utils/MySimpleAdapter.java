package com.jingdong.common.utils;

import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.frame.IDestroyListener;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.frame.IPauseListener;
import com.jingdong.common.frame.IResumeListener;
import com.jingdong.common.lbs.gis.LocationAddressNextPageLoader;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.common.utils.SimpleBeanAdapter;
import com.jingdong.jdsdk.utils.cache.GlobalImageCache;
import com.jingdong.sdk.oklog.OKLog;
import java.util.List;

/* loaded from: classes6.dex */
public class MySimpleAdapter extends SimpleBeanAdapter implements IDestroyListener, IPauseListener, IResumeListener {
    public static final int THUMB_TYPE_CENTER = 1;
    public static final int THUMB_TYPE_NONE = 0;
    private ImageProcessor mImageProcessor;
    private boolean noNotify;

    /* loaded from: classes6.dex */
    public interface ImageProcessor {
        Bitmap create(ImageUtil.InputWay inputWay, GlobalImageCache.BitmapDigest bitmapDigest);

        void show(SimpleBeanAdapter.SubViewHolder subViewHolder, GlobalImageCache.ImageState imageState);

        void stop();
    }

    public MySimpleAdapter(IMyActivity iMyActivity, List<?> list, int i2, String[] strArr, int[] iArr, boolean z, JDDisplayImageOptions jDDisplayImageOptions) {
        super(iMyActivity.getThisActivity(), list, i2, strArr, iArr, jDDisplayImageOptions);
        this.noNotify = false;
        if (z) {
            iMyActivity.addDestroyListener(this);
            iMyActivity.addPauseListener(this);
            iMyActivity.addResumeListener(this);
        }
    }

    @Override // com.jingdong.common.utils.SimpleBeanAdapter, android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        if (OKLog.D) {
            OKLog.d(MySimpleAdapter.class.getName(), "position = " + i2 + " convertView = " + view + " -->> ");
        }
        View view2 = super.getView(i2, view, viewGroup);
        if (OKLog.D) {
            OKLog.d(MySimpleAdapter.class.getName(), "position = " + i2 + " view = " + view2 + " -->> ");
        }
        return view2;
    }

    public boolean isNoImage() {
        return isAllowNoImage() && NoImageUtils.needNoImage();
    }

    @Override // com.jingdong.common.frame.IDestroyListener
    public void onDestroy() {
        gc();
    }

    @Override // com.jingdong.common.frame.IPauseListener
    public void onPause() {
        ImageProcessor imageProcessor = this.mImageProcessor;
        if (imageProcessor != null) {
            imageProcessor.stop();
        }
    }

    @Override // com.jingdong.common.frame.IResumeListener
    public void onResume() {
        if (this.noNotify) {
            return;
        }
        notifyDataSetChanged();
    }

    public void setImageProcessor(ImageProcessor imageProcessor) {
        this.mImageProcessor = imageProcessor;
    }

    public void setNextPageLoader(LocationAddressNextPageLoader locationAddressNextPageLoader) {
    }

    public void setNextPageLoader(FastjsonNextPageLoader fastjsonNextPageLoader) {
    }

    public void setNextPageLoader(NextPageLoader nextPageLoader) {
    }

    public void setNoNotify(boolean z) {
        this.noNotify = z;
    }

    public MySimpleAdapter(IMyActivity iMyActivity, List<?> list, int i2, String[] strArr, int[] iArr, boolean z) {
        this(iMyActivity, list, i2, strArr, iArr, z, null);
    }

    public MySimpleAdapter(IMyActivity iMyActivity, List<?> list, int i2, String[] strArr, int[] iArr) {
        this(iMyActivity, list, i2, strArr, iArr, true);
    }

    public MySimpleAdapter(IMyActivity iMyActivity, List<?> list, int i2, String[] strArr, int[] iArr, int i3, float f2, float f3) {
        this(iMyActivity, list, i2, strArr, iArr);
    }
}
