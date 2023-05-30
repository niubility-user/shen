package com.jingdong.common.utils.adapter;

import android.graphics.Bitmap;
import android.widget.AdapterView;
import com.jingdong.common.BaseApplication;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.common.utils.MySimpleAdapter;
import com.jingdong.common.utils.SimpleBeanAdapter;
import com.jingdong.jdsdk.utils.cache.GlobalImageCache;
import com.jingdong.sdk.oklog.OKLog;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/* loaded from: classes6.dex */
public class SimpleImageProcessor implements MySimpleAdapter.ImageProcessor {
    private static final String TAG = "SimpleImageProcessor";
    private LIFOImageLoaderTaskController mController = new LIFOImageLoaderTaskController();

    /* loaded from: classes6.dex */
    public static class LIFOImageLoaderTaskController implements OnLoadCompleteListener {
        private static final int MAX_TASK_NUM = 6;
        private int runningCount = 0;
        private SizeLimitedStack<ImageLoader> lifoQueue = new SizeLimitedStack<>(6);

        LIFOImageLoaderTaskController() {
        }

        public void clear() {
            this.lifoQueue.clear();
        }

        void executeNext() {
            ImageLoader pop = this.lifoQueue.pop();
            if (pop != null) {
                this.runningCount++;
                if (OKLog.D) {
                    OKLog.d(SimpleImageProcessor.TAG, "executeNext() running count " + this.runningCount);
                }
                if (OKLog.D) {
                    OKLog.d(SimpleImageProcessor.TAG, "executeNext() run  " + pop);
                }
                pop.load();
            }
        }

        void offerTask(ImageLoader imageLoader) {
            this.lifoQueue.push(imageLoader);
            if (OKLog.D) {
                OKLog.d(SimpleImageProcessor.TAG, "offerTask() running count " + this.runningCount);
            }
            executeNext();
        }

        @Override // com.jingdong.common.utils.adapter.SimpleImageProcessor.OnLoadCompleteListener
        public void onCompleted() {
            this.runningCount--;
            if (OKLog.D) {
                OKLog.d(SimpleImageProcessor.TAG, "onCompleted() running count " + this.runningCount);
            }
            executeNext();
        }

        void setMaxTaskNum(int i2) {
            if (i2 > this.lifoQueue.getMaxCapicity()) {
                this.lifoQueue.setMaxCapibity(i2);
            }
        }
    }

    /* loaded from: classes6.dex */
    public interface OnLoadCompleteListener {
        void onCompleted();
    }

    /* loaded from: classes6.dex */
    public static class SizeLimitedStack<E> {
        private LinkedList<E> list = new LinkedList<>();
        private int maxCapicity;

        SizeLimitedStack(int i2) {
            this.maxCapicity = i2;
        }

        private E findDuplcated(E e2) {
            Iterator<E> it = this.list.iterator();
            while (it.hasNext()) {
                E next = it.next();
                if (next.equals(e2)) {
                    return next;
                }
            }
            return null;
        }

        public synchronized void clear() {
            this.list.clear();
        }

        public int getMaxCapicity() {
            return this.maxCapicity;
        }

        public synchronized E pop() {
            E last;
            try {
                last = this.list.getLast();
                this.list.remove(last);
            } catch (NoSuchElementException unused) {
                return null;
            }
            return last;
        }

        public synchronized void push(E e2) {
            E findDuplcated = findDuplcated(e2);
            if (findDuplcated != null) {
                if (findDuplcated != this.list.getLast()) {
                    this.list.remove(findDuplcated);
                    this.list.add(e2);
                }
                return;
            }
            while (this.list.size() >= this.maxCapicity) {
                try {
                    E first = this.list.getFirst();
                    this.list.remove(first);
                    if (OKLog.D && first != null) {
                        OKLog.d(SimpleImageProcessor.TAG, "remove the oldest one,pos= " + first);
                    }
                } catch (NoSuchElementException unused) {
                }
            }
            if (OKLog.D) {
                OKLog.d(SimpleImageProcessor.TAG, "add the new one,pos= " + e2);
            }
            this.list.add(e2);
        }

        public void setMaxCapibity(int i2) {
            this.maxCapicity = i2;
        }
    }

    @Override // com.jingdong.common.utils.MySimpleAdapter.ImageProcessor
    public Bitmap create(ImageUtil.InputWay inputWay, GlobalImageCache.BitmapDigest bitmapDigest) {
        return ImageUtil.createBitmap(inputWay, bitmapDigest);
    }

    public void judgeVisibleItemCount(AdapterView adapterView) {
        if (adapterView != null) {
            int childCount = adapterView.getChildCount();
            LIFOImageLoaderTaskController lIFOImageLoaderTaskController = this.mController;
            double d = childCount;
            Double.isNaN(d);
            lIFOImageLoaderTaskController.setMaxTaskNum((int) (d * 1.5d));
            if (OKLog.D) {
                OKLog.d(TAG, "SimpleImageProcessor.judgeVisibleItemCount() " + childCount);
            }
        } else if (OKLog.D) {
            OKLog.d(TAG, "SimpleImageProcessor.judgeVisibleItemCount() null adapterView!");
        }
    }

    protected void loadImage(SimpleBeanAdapter.SubViewHolder subViewHolder, GlobalImageCache.ImageState imageState) {
        if (OKLog.D) {
            OKLog.d(TAG, "loadImage() load image for " + subViewHolder.getPosition());
        }
        try {
            judgeVisibleItemCount(subViewHolder.getAdapter().getAdapterHelper().getAdapterView());
        } catch (Exception unused) {
        }
        LIFOImageLoaderTaskController lIFOImageLoaderTaskController = this.mController;
        lIFOImageLoaderTaskController.offerTask(new ImageLoader(subViewHolder, imageState, this, lIFOImageLoaderTaskController));
    }

    protected UIRunnable provideUIRunnable(SimpleBeanAdapter.SubViewHolder subViewHolder, GlobalImageCache.ImageState imageState) {
        return new UIRunnable(subViewHolder, imageState);
    }

    @Override // com.jingdong.common.utils.MySimpleAdapter.ImageProcessor
    public void show(SimpleBeanAdapter.SubViewHolder subViewHolder, GlobalImageCache.ImageState imageState) {
        if (OKLog.D) {
            OKLog.d(SimpleImageProcessor.class.getName(), "show() position = " + subViewHolder.getPosition() + " -->> ");
        }
        if (subViewHolder.getSubView() == null && subViewHolder.getAdapter().getAdapterHelper().getAdapterView().getChildCount() < 1) {
            if (OKLog.D) {
                OKLog.d(SimpleImageProcessor.class.getName(), "show() sleep 200 -->> ");
            }
            try {
                Thread.sleep(200L);
            } catch (InterruptedException unused) {
            }
        }
        if (Thread.currentThread() == BaseApplication.getUiThread()) {
            if (OKLog.D) {
                OKLog.d(SimpleImageProcessor.class.getName(), "show() uiThread true -->> ");
            }
            provideUIRunnable(subViewHolder, imageState).run();
        } else {
            if (OKLog.D) {
                OKLog.d(SimpleImageProcessor.class.getName(), "show() uiThread false -->> ");
            }
            subViewHolder.getAdapter().UIWorkCentralized(provideUIRunnable(subViewHolder, imageState));
        }
        boolean z = false;
        Object moreParameter = subViewHolder.getMoreParameter(SimpleBeanAdapter.SubViewHolder.MORE_PARAMETER_LOADED);
        if (moreParameter != null && (moreParameter instanceof Boolean)) {
            z = ((Boolean) moreParameter).booleanValue();
        }
        if (z) {
            subViewHolder.putMoreParameter(SimpleBeanAdapter.SubViewHolder.MORE_PARAMETER_LOADED, Boolean.FALSE);
        }
        if (OKLog.D) {
            OKLog.d(SimpleImageProcessor.class.getName(), "show() is.getState() -->> " + imageState.getState());
            OKLog.d(SimpleImageProcessor.class.getName(), "show() loaded -->> " + z);
        }
        if ((imageState.getState() == 0 || 2 == imageState.getState()) && !z) {
            if (OKLog.D) {
                OKLog.d(SimpleImageProcessor.class.getName(), "STATE_NONE or STATE_FAILURE position = " + subViewHolder.getPosition() + " -->> ");
            }
            loadImage(subViewHolder, imageState);
        }
    }

    @Override // com.jingdong.common.utils.MySimpleAdapter.ImageProcessor
    public void stop() {
        if (OKLog.D) {
            OKLog.d(TAG, "ImageProcessor.stop()");
        }
        this.mController.clear();
    }
}
