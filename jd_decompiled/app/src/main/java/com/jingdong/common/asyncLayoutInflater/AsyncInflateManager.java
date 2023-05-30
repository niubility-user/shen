package com.jingdong.common.asyncLayoutInflater;

import android.app.Activity;
import android.content.Context;
import android.content.MutableContextWrapper;
import android.content.res.XmlResourceParser;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.LayoutInflaterCompat;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JDSoftReference;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.threadpool.ThreadManager;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes5.dex */
public class AsyncInflateManager {
    private static final String TAG = "AsyncInflateManager";
    private static AsyncInflateManager sInstance;
    private ConcurrentHashMap<String, JDSoftReference<LinkedBlockingQueue>> mInflateMap = new ConcurrentHashMap<>();
    Handler mHandler = new Handler(Looper.getMainLooper());

    /* loaded from: classes5.dex */
    private static class BasicInflater extends LayoutInflater {
        private static final String[] S_CLASS_PREFIX_LIST = {"android.widget.", "android.webkit.", "android.app."};

        BasicInflater(Context context) {
            super(context);
            if (context instanceof AppCompatActivity) {
                if (Log.D) {
                    Log.e(AsyncInflateManager.TAG, "AppCompatActivity\u5f00\u59cb\u52a0\u8f7dview");
                }
                AppCompatDelegate delegate = ((AppCompatActivity) context).getDelegate();
                if (delegate instanceof LayoutInflater.Factory2) {
                    LayoutInflaterCompat.setFactory2(this, (LayoutInflater.Factory2) delegate);
                }
                if (Log.D) {
                    Log.e(AsyncInflateManager.TAG, "AppCompatActivity\u7c7b\u578bview \u52a0\u8f7d\u6210\u529f");
                }
            }
        }

        @Override // android.view.LayoutInflater
        public LayoutInflater cloneInContext(Context context) {
            return new BasicInflater(context);
        }

        @Override // android.view.LayoutInflater
        protected View onCreateView(String str, AttributeSet attributeSet) throws ClassNotFoundException {
            View createView;
            for (String str2 : S_CLASS_PREFIX_LIST) {
                try {
                    createView = createView(str, str2, attributeSet);
                } catch (ClassNotFoundException unused) {
                }
                if (createView != null) {
                    return createView;
                }
            }
            return super.onCreateView(str, attributeSet);
        }
    }

    private AsyncInflateManager() {
    }

    private void advanceToRootNode(XmlPullParser xmlPullParser) throws InflateException, IOException, XmlPullParserException {
        int next;
        do {
            next = xmlPullParser.next();
            if (next == 2) {
                break;
            }
        } while (next != 1);
        if (next == 2) {
            return;
        }
        throw new InflateException(xmlPullParser.getPositionDescription() + ": No start tag found!");
    }

    private String getInflateKey(int i2, int i3) {
        return i2 + MqttTopic.SINGLE_LEVEL_WILDCARD + i3;
    }

    public static AsyncInflateManager getInstance() {
        if (sInstance == null) {
            sInstance = new AsyncInflateManager();
        }
        return sInstance;
    }

    private void inflateWithThreadPool(final AsyncInflateItem asyncInflateItem) {
        ThreadManager.light().post(new Runnable() { // from class: com.jingdong.common.asyncLayoutInflater.AsyncInflateManager.1
            @Override // java.lang.Runnable
            public void run() {
                Handler handler;
                Runnable runnable;
                if (asyncInflateItem.isInflating()) {
                    return;
                }
                try {
                    try {
                        AsyncInflateItem asyncInflateItem2 = asyncInflateItem;
                        if (asyncInflateItem2.callback == null) {
                            AsyncInflateManager.this.onAsyncInflateStart(asyncInflateItem2);
                        }
                        MutableContextWrapper mutableContextWrapper = new MutableContextWrapper(JdSdk.getInstance().getApplicationContext());
                        long currentTimeMillis = System.currentTimeMillis();
                        AsyncInflateItem asyncInflateItem3 = asyncInflateItem;
                        BasicInflater basicInflater = new BasicInflater(mutableContextWrapper);
                        AsyncInflateItem asyncInflateItem4 = asyncInflateItem;
                        asyncInflateItem3.inflatedView = basicInflater.inflate(asyncInflateItem4.layoutResId, asyncInflateItem4.parent, false);
                        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
                        if (Log.D) {
                            Log.e(AsyncInflateManager.TAG, JdSdk.getInstance().getApplicationContext().getResources().getResourceName(asyncInflateItem.layoutResId) + " cost: " + currentTimeMillis2 + "ms");
                        }
                        AsyncInflateManager.this.onAsyncInflateEnd(asyncInflateItem, true);
                    } catch (RuntimeException e2) {
                        if (Log.D) {
                            Log.e(AsyncInflateManager.TAG, "Failed to inflate resource in the background! Retrying on the UI thread", e2);
                        }
                        AsyncInflateManager.this.onAsyncInflateEnd(asyncInflateItem, false);
                        if (asyncInflateItem.callback == null) {
                            return;
                        }
                        handler = AsyncInflateManager.this.mHandler;
                        runnable = new Runnable() { // from class: com.jingdong.common.asyncLayoutInflater.AsyncInflateManager.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                AsyncInflateItem asyncInflateItem5 = asyncInflateItem;
                                asyncInflateItem5.callback.onInflateFinished(asyncInflateItem5);
                            }
                        };
                    }
                    if (asyncInflateItem.callback != null) {
                        handler = AsyncInflateManager.this.mHandler;
                        runnable = new Runnable() { // from class: com.jingdong.common.asyncLayoutInflater.AsyncInflateManager.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                AsyncInflateItem asyncInflateItem5 = asyncInflateItem;
                                asyncInflateItem5.callback.onInflateFinished(asyncInflateItem5);
                            }
                        };
                        handler.post(runnable);
                    }
                } catch (Throwable th) {
                    if (asyncInflateItem.callback != null) {
                        AsyncInflateManager.this.mHandler.post(new Runnable() { // from class: com.jingdong.common.asyncLayoutInflater.AsyncInflateManager.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                AsyncInflateItem asyncInflateItem5 = asyncInflateItem;
                                asyncInflateItem5.callback.onInflateFinished(asyncInflateItem5);
                            }
                        });
                    }
                    throw th;
                }
            }
        });
    }

    private boolean isAsyncSwitcherClose() {
        return !parseStringToBoolean(JDMobileConfig.getInstance().getConfig("JDCommune", "asyncInflaterSwitcher", "asyncInflaterSwitcher", "0"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAsyncInflateEnd(AsyncInflateItem asyncInflateItem, boolean z) {
        asyncInflateItem.setInflating(false);
        asyncInflateItem.getCountDownLatch().countDown();
        if (Log.D) {
            Log.e(TAG, "inflate done\uff1f=" + z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAsyncInflateStart(AsyncInflateItem asyncInflateItem) {
        LinkedBlockingQueue linkedBlockingQueue;
        asyncInflateItem.setInflating(true);
        JDSoftReference<LinkedBlockingQueue> jDSoftReference = this.mInflateMap.get(getInflateKey(asyncInflateItem.layoutResId, asyncInflateItem.mPageHashCode));
        if (jDSoftReference != null && (jDSoftReference.get() instanceof LinkedBlockingQueue)) {
            linkedBlockingQueue = jDSoftReference.get();
        } else {
            linkedBlockingQueue = new LinkedBlockingQueue(5);
            this.mInflateMap.put(getInflateKey(asyncInflateItem.layoutResId, asyncInflateItem.mPageHashCode), new JDSoftReference<>(linkedBlockingQueue));
        }
        try {
            linkedBlockingQueue.put(asyncInflateItem);
        } catch (InterruptedException e2) {
            e2.printStackTrace();
            if (Log.D) {
                Log.e(TAG, "Queue is full");
            }
        }
    }

    private boolean parseStringToBoolean(String str) {
        return !TextUtils.isEmpty(str) && TextUtils.equals("1", str);
    }

    public static void replaceContextForView(View view, Context context) {
        if (view == null || context == null) {
            return;
        }
        Context context2 = view.getContext();
        if (context2 instanceof MutableContextWrapper) {
            ((MutableContextWrapper) context2).setBaseContext(context);
        }
    }

    private void setLayoutParams(View view, int i2, @Nullable ViewGroup viewGroup, boolean z) {
        if (viewGroup != null) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams == null) {
                XmlResourceParser layout = JdSdk.getInstance().getApplicationContext().getResources().getLayout(i2);
                try {
                    try {
                        AttributeSet asAttributeSet = Xml.asAttributeSet(layout);
                        advanceToRootNode(layout);
                        layoutParams = viewGroup.generateLayoutParams(asAttributeSet);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                } finally {
                    layout.close();
                }
            }
            if (!z) {
                if (layoutParams != null) {
                    view.setLayoutParams(layoutParams);
                }
            } else if (layoutParams == null) {
                viewGroup.addView(view);
            } else {
                viewGroup.addView(view, layoutParams);
            }
        }
    }

    @UiThread
    public void asyncInflate(AsyncInflateItem asyncInflateItem) {
        OnInflateFinishedCallback onInflateFinishedCallback;
        if (isAsyncSwitcherClose()) {
            if (Log.D) {
                Log.e(TAG, "\u5f02\u6b65\u52a0\u8f7d\u5f00\u5173\u5173\u95ed\uff0c\u505c\u6b62\u5f02\u6b65\u52a0\u8f7d");
            }
            if (asyncInflateItem == null || (onInflateFinishedCallback = asyncInflateItem.callback) == null) {
                return;
            }
            onInflateFinishedCallback.onInflateFinished(asyncInflateItem);
        } else if (asyncInflateItem == null || asyncInflateItem.layoutResId == 0 || asyncInflateItem.isInflating()) {
        } else {
            inflateWithThreadPool(asyncInflateItem);
        }
    }

    public void clearData(Activity activity) {
        if (isAsyncSwitcherClose() || this.mInflateMap == null) {
            return;
        }
        if (Log.D) {
            Log.e(TAG, "clearData:" + activity.getLocalClassName() + "--" + activity.hashCode());
        }
        String valueOf = String.valueOf(activity.hashCode());
        StringBuilder sb = new StringBuilder("");
        for (String str : this.mInflateMap.keySet()) {
            if (str != null && str.endsWith(valueOf)) {
                this.mInflateMap.remove(str);
                if (Log.D) {
                    sb.setLength(0);
                    sb.append("clearData:");
                    sb.append(str);
                    Log.e(TAG, sb.toString());
                }
            }
        }
    }

    public View getInflatedView(Activity activity, int i2) {
        return getInflatedView(activity, i2, (ViewGroup) null);
    }

    public View getInflatedView(Activity activity, int i2, @Nullable ViewGroup viewGroup) {
        return getInflatedView(activity, i2, viewGroup, false);
    }

    public View getInflatedView(Activity activity, int i2, boolean z) {
        return getInflatedView(activity, i2, null, z);
    }

    public View getInflatedView(@NonNull Activity activity, int i2, @Nullable ViewGroup viewGroup, boolean z) {
        return getInflatedView(activity, i2, viewGroup, z, false);
    }

    @NonNull
    @UiThread
    public View getInflatedView(@NonNull Activity activity, int i2, @Nullable ViewGroup viewGroup, boolean z, boolean z2) {
        JDSoftReference<LinkedBlockingQueue> jDSoftReference;
        LinkedBlockingQueue linkedBlockingQueue;
        if (isAsyncSwitcherClose()) {
            if (Log.D) {
                Log.e(TAG, "\u5f02\u6b65\u52a0\u8f7d\u5f00\u5173\u5173\u95ed\uff0c\u5728\u4e3b\u7ebf\u7a0binflate");
            }
            return ImageUtil.inflate(activity, i2, viewGroup, z2);
        }
        int hashCode = activity.hashCode();
        if (z) {
            asyncInflate(new AsyncInflateItem(hashCode, i2));
        }
        if (this.mInflateMap.containsKey(getInflateKey(i2, hashCode)) && (jDSoftReference = this.mInflateMap.get(getInflateKey(i2, hashCode))) != null) {
            LinkedBlockingQueue linkedBlockingQueue2 = jDSoftReference.get();
            if ((linkedBlockingQueue2 instanceof LinkedBlockingQueue) && (linkedBlockingQueue = linkedBlockingQueue2) != null && linkedBlockingQueue.size() > 0) {
                Object poll = linkedBlockingQueue.poll();
                if (poll instanceof AsyncInflateItem) {
                    AsyncInflateItem asyncInflateItem = (AsyncInflateItem) poll;
                    View view = asyncInflateItem.inflatedView;
                    if (view != null) {
                        replaceContextForView(view, activity);
                        if (Log.D) {
                            Log.e(TAG, JdSdk.getInstance().getApplicationContext().getResources().getResourceName(asyncInflateItem.layoutResId) + " is async inflated success");
                        }
                        setLayoutParams(view, i2, viewGroup, z2);
                        return view;
                    } else if (asyncInflateItem.isInflating() && asyncInflateItem.getCountDownLatch() != null) {
                        try {
                            if (Log.D) {
                                Log.e(TAG, JdSdk.getInstance().getApplicationContext().getResources().getResourceName(asyncInflateItem.layoutResId) + " inflating");
                            }
                            asyncInflateItem.getCountDownLatch().await();
                        } catch (InterruptedException e2) {
                            if (Log.D) {
                                Log.e(TAG, e2.getMessage(), e2);
                            }
                        }
                        View view2 = asyncInflateItem.inflatedView;
                        if (view2 != null) {
                            replaceContextForView(view2, activity);
                            if (Log.D) {
                                Log.e(TAG, JdSdk.getInstance().getApplicationContext().getResources().getResourceName(asyncInflateItem.layoutResId) + " inflating wait success");
                            }
                            setLayoutParams(view2, i2, viewGroup, z2);
                            return view2;
                        }
                    }
                }
            }
        }
        if (Log.D) {
            Log.e(TAG, JdSdk.getInstance().getApplicationContext().getResources().getResourceName(i2) + " \u5f02\u6b65\u52a0\u8f7d\u5931\u8d25\uff0c\u5728\u4e3b\u7ebf\u7a0binflate");
        }
        return ImageUtil.inflate(activity, i2, viewGroup, z2);
    }
}
