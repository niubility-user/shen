package com.jingdong.common.utils.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import com.jingdong.common.utils.AdapterHelper;
import com.jingdong.common.utils.SimpleBeanAdapter;
import com.jingdong.common.utils.SimpleSubViewBinder;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.res.StringUtil;
import com.jingdong.jdsdk.utils.cache.GlobalImageCache;
import com.jingdong.jdsdk.widget.ExceptionDrawable;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes6.dex */
public class UIRunnable implements Runnable {
    private final String APP_NAME = StringUtil.app_name;
    private final String NEED_LONG_CLICK = StringUtil.need_long_click;
    private GlobalImageCache.ImageState imageState;
    private SimpleBeanAdapter.SubViewHolder subViewHolder;
    private static final String TAG = UIRunnable.class.getSimpleName();
    private static final Context CONTEXT = JdSdk.getInstance().getApplication();

    /* loaded from: classes6.dex */
    public static class ViewLongClickForNoImage implements View.OnLongClickListener {
        private GlobalImageCache.BitmapDigest bitmapDigest;
        private SimpleImageProcessor imageProcessor;
        private SimpleBeanAdapter.SubViewHolder subViewHolder;

        public ViewLongClickForNoImage(SimpleBeanAdapter.SubViewHolder subViewHolder, GlobalImageCache.BitmapDigest bitmapDigest) {
            this.subViewHolder = subViewHolder;
            this.bitmapDigest = bitmapDigest;
            this.imageProcessor = ((SimpleSubViewBinder) subViewHolder.getAdapter().getViewBinder()).getSimpleImageProcessor();
        }

        private void gc() {
            this.subViewHolder = null;
            this.bitmapDigest = null;
            this.imageProcessor = null;
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            view.setLongClickable(false);
            if (OKLog.D) {
                OKLog.d(UIRunnable.TAG, "ViewLongClickForNoImage v.getId -->> " + view.getId());
                OKLog.d(UIRunnable.TAG, "ViewLongClickForNoImage subViewHolder -->> " + this.subViewHolder);
            }
            SimpleBeanAdapter.SubViewHolder subViewHolder = this.subViewHolder;
            if (subViewHolder != null) {
                subViewHolder.putMoreParameter(SimpleBeanAdapter.SubViewHolder.MORE_PARAMETER_MANUAL_GET_IMAGE, Boolean.TRUE);
                this.imageProcessor.show(this.subViewHolder, GlobalImageCache.getImageState(this.bitmapDigest));
            }
            gc();
            return true;
        }
    }

    public UIRunnable(SimpleBeanAdapter.SubViewHolder subViewHolder, GlobalImageCache.ImageState imageState) {
        this.subViewHolder = subViewHolder;
        this.imageState = imageState;
    }

    private void gc() {
        this.subViewHolder = null;
        this.imageState = null;
    }

    public View getItemView() {
        AdapterHelper adapterHelper = getSubViewHolder().getAdapter().getAdapterHelper();
        SimpleBeanAdapter.SubViewHolder subViewHolder = getSubViewHolder();
        Object item = subViewHolder.getAdapter().getItem(subViewHolder.getPosition());
        if (OKLog.D) {
            OKLog.d(UIRunnable.class.getName(), "getItemView() old item -->> " + subViewHolder.getItemData());
            OKLog.d(UIRunnable.class.getName(), "getItemView() new item -->> " + item);
        }
        if (item != null && subViewHolder.getItemData() == item) {
            if (OKLog.D) {
                OKLog.d(UIRunnable.class.getName(), "oldItemData and newItemData is equals -->> ");
            }
            return adapterHelper.getItemView(getSubViewHolder().getPosition(), true);
        } else if (OKLog.D) {
            OKLog.d(UIRunnable.class.getName(), "oldItemData and newItemData not equals -->> ");
            return null;
        } else {
            return null;
        }
    }

    public SimpleBeanAdapter.SubViewHolder getSubViewHolder() {
        return this.subViewHolder;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (OKLog.D) {
            OKLog.d(UIRunnable.class.getName(), "run() position -->> " + getSubViewHolder().getPosition());
        }
        View itemView = this.subViewHolder.getItemView();
        if (itemView == null) {
            itemView = getItemView();
        }
        if (itemView == null) {
            if (OKLog.D) {
                OKLog.d(UIRunnable.class.getName(), "run() itemView null -->> ");
            }
            gc();
            return;
        }
        if (OKLog.D) {
            OKLog.d(UIRunnable.class.getName(), "run() itemView -->> " + itemView);
        }
        View findViewById = itemView.findViewById(getSubViewHolder().getSubViewId());
        if (OKLog.D) {
            OKLog.d(UIRunnable.class.getName(), "run() subView -->> " + findViewById);
        }
        if (findViewById != null || itemView.getId() != getSubViewHolder().getSubViewId()) {
            itemView = findViewById;
        }
        SimpleBeanAdapter adapter = this.subViewHolder.getAdapter();
        if (itemView instanceof ImageView) {
            ImageView imageView = (ImageView) itemView;
            Context context = CONTEXT;
            ExceptionDrawable exceptionDrawable = new ExceptionDrawable(context, StringUtil.need_long_click);
            ExceptionDrawable exceptionDrawable2 = new ExceptionDrawable(context, StringUtil.app_name);
            int state = this.imageState.getState();
            if (state == 0) {
                if (OKLog.D) {
                    OKLog.d(UIRunnable.class.getName(), "STATE_NONE position -->> " + getSubViewHolder().getPosition());
                }
                if (adapter.allowNoImageAndIsNoImage()) {
                    imageView.setImageDrawable(exceptionDrawable);
                } else {
                    imageView.setImageDrawable(exceptionDrawable2);
                }
            } else if (state == 1) {
                if (OKLog.D) {
                    OKLog.d(UIRunnable.class.getName(), "STATE_LOADING position -->> " + getSubViewHolder().getPosition());
                }
                if (adapter.allowNoImageAndIsNoImage()) {
                    imageView.setImageDrawable(exceptionDrawable);
                } else {
                    imageView.setImageDrawable(exceptionDrawable2);
                }
            } else if (state == 2) {
                if (OKLog.D) {
                    OKLog.d(UIRunnable.class.getName(), "STATE_FAILURE position -->> " + getSubViewHolder().getPosition());
                }
                Boolean bool = Boolean.FALSE;
                Object moreParameter = this.subViewHolder.getMoreParameter(SimpleBeanAdapter.SubViewHolder.MORE_PARAMETER_LOCAL_LOAD_IMAGE);
                if (moreParameter != null && (moreParameter instanceof Boolean)) {
                    bool = (Boolean) moreParameter;
                }
                if (OKLog.D) {
                    OKLog.d(UIRunnable.class.getName(), "STATE_FAILURE localLoadImage -->> " + bool);
                }
                if (adapter.allowNoImageAndIsNoImage()) {
                    imageView.setImageDrawable(exceptionDrawable);
                    imageView.setOnLongClickListener(new ViewLongClickForNoImage(this.subViewHolder, GlobalImageCache.getBitmapDigest(this.imageState)));
                } else {
                    imageView.setImageDrawable(exceptionDrawable2);
                }
            } else if (state == 3) {
                if (OKLog.D) {
                    OKLog.d(UIRunnable.class.getName(), "STATE_SUCCESS position -->> " + getSubViewHolder().getPosition());
                }
                GlobalImageCache.BitmapDigest bitmapDigest = GlobalImageCache.getBitmapDigest(this.imageState);
                Bitmap bitmap = this.imageState.getBitmap();
                if (bitmap != null && (bitmapDigest != null || !bitmap.isRecycled())) {
                    imageView.setImageDrawable(new MyBitmapDrawable(imageView.getResources(), this.subViewHolder, bitmapDigest, bitmap));
                } else {
                    imageView.setImageDrawable(exceptionDrawable2);
                    this.imageState.none();
                }
            }
        }
        gc();
    }
}
