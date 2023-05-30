package com.jingdong.common.network;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.network.InternalActivityLifecycleCallbacks;
import com.jingdong.jdsdk.network.JDHttpTookit;
import com.jingdong.jdsdk.network.dependency.IModalViewController;
import com.jingdong.jdsdk.network.toolbox.GlobalExecutorService;
import com.jingdong.sdk.oklog.OKLog;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

/* loaded from: classes5.dex */
public class EffectHttpListener implements InternalActivityLifecycleCallbacks.IDestroyListener {
    public static final String TAG = "EffectHttpListener";
    protected String mActivityName;
    protected WeakReference<ViewGroup> mRootLayout;
    protected WeakReference<Activity> myActivity;
    public boolean onTouchEvent;
    protected static final Map<String, WeakHashMap<ViewGroup, State>> stateMap = Collections.synchronizedMap(new HashMap());
    protected static final List<State> stateList = Collections.synchronizedList(new ArrayList());
    public static String loadingViewTag = "LoadingViewTag";

    /* loaded from: classes5.dex */
    public class State implements Runnable {
        private static final int WAITING = -1;
        private static final int WAIT_TIME = 50;
        private boolean hasThread;
        private int missionCount;
        private ViewGroup modal;
        private View progressBar;
        private TextView progressTextView;
        private WeakReference<ViewGroup> rootLayout;
        private boolean usersTouchEvent;
        private int waitTime = 50;
        private boolean isTouch = false;
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        public State(WeakReference<ViewGroup> weakReference) {
            if (OKLog.D) {
                OKLog.d(EffectHttpListener.TAG, "=======create new State instance======");
            }
            this.rootLayout = weakReference;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addProgressBar2Modal() {
            this.modal.removeView(this.progressBar);
            try {
                View createProgressBar = JDHttpTookit.getEngine().getCustomUIComponentImpl().createProgressBar();
                this.progressBar = createProgressBar;
                this.modal.addView(createProgressBar);
            } catch (Throwable unused) {
                TextView textView = this.progressTextView;
                if (textView == null) {
                    TextView loadingTextView = getLoadingTextView();
                    this.progressTextView = loadingTextView;
                    loadingTextView.setText("\u52a0\u8f7d\u4e2d...");
                } else {
                    this.modal.removeView(textView);
                }
                this.modal.addView(this.progressTextView);
                if (OKLog.D) {
                    OKLog.d("=======>", "add progressTextView : \u52a0\u8f7d\u4e2d...");
                }
            }
        }

        private void firstMission() {
            if (OKLog.D) {
                OKLog.d(EffectHttpListener.TAG, "********firstMission()*******");
            }
            if (this.hasThread) {
                this.waitTime = -1;
                notify();
                return;
            }
            this.mainThreadHandler.post(new Runnable() { // from class: com.jingdong.common.network.EffectHttpListener.State.2
                @Override // java.lang.Runnable
                public void run() {
                    ViewGroup viewGroup = (ViewGroup) State.this.rootLayout.get();
                    ViewGroup modal = State.this.getModal();
                    if (modal == null || viewGroup == null) {
                        return;
                    }
                    State.this.addProgressBar2Modal();
                    try {
                        if (modal.getParent() == null) {
                            viewGroup.addView(modal, new ViewGroup.LayoutParams(-1, -1));
                        }
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                    viewGroup.invalidate();
                    if (OKLog.D) {
                        OKLog.d(EffectHttpListener.TAG, "firstMission -->> \u6dfb\u52a0Loading\u5708\u5b8c\u6bd5");
                    }
                    try {
                        WeakReference<Activity> weakReference = EffectHttpListener.this.myActivity;
                        if (weakReference instanceof IModalViewController) {
                            ((IModalViewController) weakReference).onShowModal();
                        }
                    } catch (Throwable th2) {
                        if (OKLog.D) {
                            th2.printStackTrace();
                        }
                    }
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ViewGroup getModal() {
            try {
                ViewGroup viewGroup = this.modal;
                if (viewGroup != null) {
                    return viewGroup;
                }
                RelativeLayout relativeLayout = new RelativeLayout(JDHttpTookit.getEngine().getApplicationContext());
                this.modal = relativeLayout;
                relativeLayout.setTag(EffectHttpListener.loadingViewTag);
                this.modal.setOnTouchListener(new View.OnTouchListener() { // from class: com.jingdong.common.network.EffectHttpListener.State.1
                    @Override // android.view.View.OnTouchListener
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        if (State.this.isTouch) {
                            return true;
                        }
                        return State.this.isUsersTouchEvent();
                    }
                });
                return this.modal;
            } catch (Throwable unused) {
                return null;
            }
        }

        private void lastMission() {
            if (this.hasThread) {
                this.waitTime = 50;
                notify();
                return;
            }
            try {
                GlobalExecutorService.lightExecutorService().execute(this);
                this.hasThread = true;
            } catch (Throwable unused) {
            }
        }

        public synchronized boolean addMission() {
            this.missionCount++;
            if (OKLog.D) {
                OKLog.d(EffectHttpListener.TAG, "addMission() \u5f53\u524d\u4efb\u52a1\u6570\u91cf \uff1a" + this.missionCount);
            }
            if (this.missionCount == 1) {
                firstMission();
                return true;
            }
            return false;
        }

        public TextView getLoadingTextView() {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(13);
            TextView textView = new TextView(JDHttpTookit.getEngine().getApplicationContext());
            textView.setPadding(10, 10, 10, 10);
            textView.setLayoutParams(layoutParams);
            textView.setGravity(17);
            textView.setBackgroundColor(15396337);
            return textView;
        }

        public WeakReference<ViewGroup> getRootLayout() {
            return this.rootLayout;
        }

        public boolean isUsersTouchEvent() {
            return this.usersTouchEvent;
        }

        public synchronized void release() {
            if (OKLog.D) {
                OKLog.d(EffectHttpListener.TAG, "----------State->release() \u91ca\u653estate\u8d44\u6e90-----------");
            }
            this.waitTime = 50;
            notifyAll();
        }

        public synchronized boolean removeMission() {
            this.missionCount--;
            if (OKLog.D) {
                OKLog.d(EffectHttpListener.TAG, "removeMission() \u5f53\u524d\u4efb\u52a1\u6570\u91cf \uff1a" + this.missionCount);
            }
            int i2 = this.missionCount;
            if (i2 < 0) {
                this.missionCount = 0;
                if (OKLog.D) {
                    OKLog.d(EffectHttpListener.TAG, "removeMission() \u6ca1\u6709\u6253\u5f00loading...");
                }
                return false;
            } else if (i2 < 1) {
                if (OKLog.D) {
                    OKLog.d(EffectHttpListener.TAG, "removeMission() \u6700\u540e\u4e00\u4e2a\u4efb\u52a1\u4e86\uff0c\u5f00\u59cb\u6e05\u9664\u906e\u7f69");
                }
                try {
                    EffectHttpListener.stateList.remove(this);
                    JDHttpTookit.getEngine().getInternalActivityLifecycleCallbacks().removeDestroyListener(EffectHttpListener.this);
                    Map<String, WeakHashMap<ViewGroup, State>> map = EffectHttpListener.stateMap;
                    synchronized (map) {
                        if (map.get(EffectHttpListener.this.mActivityName) != null) {
                            WeakHashMap<ViewGroup, State> weakHashMap = map.get(EffectHttpListener.this.mActivityName);
                            ViewGroup viewGroup = this.rootLayout.get();
                            if (viewGroup != null && weakHashMap != null && weakHashMap.containsKey(viewGroup)) {
                                weakHashMap.remove(viewGroup);
                            }
                        }
                    }
                } catch (Throwable unused) {
                }
                lastMission();
                return true;
            } else {
                return false;
            }
        }

        @Override // java.lang.Runnable
        public synchronized void run() {
            if (OKLog.D) {
                OKLog.d(EffectHttpListener.TAG, "DefaultEffectHttpListener_lastMission run() with threadId " + Thread.currentThread().getId());
            }
            do {
                if (this.waitTime == -1) {
                    try {
                        if (OKLog.D) {
                            OKLog.d(EffectHttpListener.TAG, "\u64a4\u9500\u906e\u7f69\u7ebf\u7a0b\u8fdb\u5165\u957f\u671f\u7b49\u5f85\uff0cthreadId " + Thread.currentThread().getId());
                        }
                        wait();
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                } else {
                    try {
                        if (OKLog.D) {
                            OKLog.d(EffectHttpListener.TAG, "\u64a4\u9500\u906e\u7f69\u7ebf\u7a0b\u7b49\u5f8550ms\u540e\u64a4\u9500\u906e\u7f69\uff0cthreadId " + Thread.currentThread().getId());
                        }
                        int i2 = this.waitTime;
                        this.waitTime = 0;
                        wait(i2);
                    } catch (InterruptedException e3) {
                        e3.printStackTrace();
                    }
                }
            } while (this.waitTime != 0);
            if (OKLog.D) {
                OKLog.d(EffectHttpListener.TAG, "DefaultEffectHttpListener_lastMission \u5f00\u59cb\u64a4\u9500Loading\u5708 with threadId " + Thread.currentThread().getId());
            }
            this.mainThreadHandler.post(new Runnable() { // from class: com.jingdong.common.network.EffectHttpListener.State.3
                @Override // java.lang.Runnable
                public void run() {
                    if (OKLog.D) {
                        OKLog.d(EffectHttpListener.TAG, "UI\u7ebf\u7a0b\u79fb\u9664Loading\u5708 -->> " + State.this.modal);
                    }
                    ViewGroup viewGroup = (ViewGroup) State.this.rootLayout.get();
                    ViewGroup modal = State.this.getModal();
                    if (viewGroup == null || modal == null) {
                        return;
                    }
                    if (modal != null) {
                        modal.setOnTouchListener(null);
                    }
                    JDHttpTookit.getEngine().getCustomUIComponentImpl().releaseResource(State.this.progressBar);
                    viewGroup.removeView(modal);
                    viewGroup.invalidate();
                    Activity activity = EffectHttpListener.this.myActivity.get();
                    if (activity == null) {
                        return;
                    }
                    try {
                        if (activity instanceof IModalViewController) {
                            ((IModalViewController) activity).onHideModal();
                        }
                    } catch (Throwable th) {
                        if (OKLog.D) {
                            th.printStackTrace();
                        }
                    }
                }
            });
            this.waitTime = 50;
            this.hasThread = false;
        }

        public void setUsersTouchEvent(boolean z) {
            this.usersTouchEvent = z;
        }
    }

    public EffectHttpListener(Activity activity, ViewGroup viewGroup) {
        WeakReference<Activity> weakReference = new WeakReference<>(activity);
        this.myActivity = weakReference;
        this.mActivityName = weakReference.get() != null ? this.myActivity.get().toString() : "";
        if (viewGroup != null) {
            this.mRootLayout = new WeakReference<>(viewGroup);
        } else {
            this.mRootLayout = new WeakReference<>(getDefaultRootFrameLayout());
        }
        JDHttpTookit.getEngine().getInternalActivityLifecycleCallbacks().addDestroyListener(this);
    }

    private void release() {
        Map<String, WeakHashMap<ViewGroup, State>> map = stateMap;
        synchronized (map) {
            WeakHashMap<ViewGroup, State> weakHashMap = map.get(this.mActivityName);
            WeakReference<ViewGroup> weakReference = this.mRootLayout;
            ViewGroup viewGroup = weakReference != null ? weakReference.get() : null;
            if (viewGroup == null) {
                expungeHungState();
                return;
            }
            if (OKLog.D) {
                OKLog.d(TAG, "release() rootLayout\u672a\u88ab\u56de\u6536\uff0c\u53ef\u7ee7\u7eed\u91ca\u653e\u8d44\u6e90");
            }
            State state = weakHashMap != null ? weakHashMap.get(viewGroup) : null;
            if (state != null) {
                state.release();
                stateList.remove(state);
            }
        }
    }

    public void clearMission() {
        missionComplete();
    }

    protected void expungeHungState() {
        List<State> list = stateList;
        synchronized (list) {
            try {
                Iterator<State> it = list.iterator();
                while (it.hasNext()) {
                    State next = it.next();
                    if (next.getRootLayout().get() == null) {
                        if (OKLog.D) {
                            OKLog.d(TAG, "===== expungeHungState remove hung state =======");
                        }
                        next.release();
                        it.remove();
                    }
                }
            } catch (Throwable unused) {
            }
        }
    }

    protected ViewGroup getDefaultRootFrameLayout() {
        if (this.myActivity.get() == null) {
            return null;
        }
        ViewGroup viewGroup = (ViewGroup) this.myActivity.get().getWindow().peekDecorView();
        if (viewGroup == null) {
            try {
                Thread.sleep(50L);
            } catch (InterruptedException unused) {
            }
            return getDefaultRootFrameLayout();
        }
        return viewGroup;
    }

    public ViewGroup getProgressBarRootLayout() {
        return this.mRootLayout.get();
    }

    @Override // com.jingdong.common.network.InternalActivityLifecycleCallbacks.IDestroyListener
    public boolean isActivityHolder(Activity activity) {
        if (OKLog.D) {
            OKLog.d("JDHttpTookit", "hold activity : " + this.mActivityName);
            StringBuilder sb = new StringBuilder();
            sb.append("destroyed activity : ");
            sb.append(activity == null ? DYConstants.DY_NULL_STR : activity.toString());
            OKLog.d("JDHttpTookit", sb.toString());
        }
        return TextUtils.equals(this.mActivityName, activity.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void missionBegins() {
        Map<String, WeakHashMap<ViewGroup, State>> map = stateMap;
        synchronized (map) {
            WeakHashMap<ViewGroup, State> weakHashMap = map.get(this.mActivityName);
            if (weakHashMap == null) {
                weakHashMap = new WeakHashMap<>();
            }
            ViewGroup progressBarRootLayout = getProgressBarRootLayout();
            if (progressBarRootLayout == null) {
                return;
            }
            State state = weakHashMap.get(progressBarRootLayout);
            if (OKLog.D) {
                OKLog.d(TAG, "state rootLayout:" + progressBarRootLayout);
                OKLog.d(TAG, "state get -->> " + state);
            }
            if (state == null) {
                state = new State(this.mRootLayout);
                weakHashMap.put(progressBarRootLayout, state);
                map.put(this.mActivityName, weakHashMap);
                stateList.add(state);
            }
            state.setUsersTouchEvent(this.onTouchEvent);
            state.addMission();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void missionComplete() {
        Map<String, WeakHashMap<ViewGroup, State>> map = stateMap;
        synchronized (map) {
            WeakHashMap<ViewGroup, State> weakHashMap = map.get(this.mActivityName);
            WeakReference<ViewGroup> weakReference = this.mRootLayout;
            ViewGroup viewGroup = weakReference != null ? weakReference.get() : null;
            if (viewGroup == null) {
                expungeHungState();
                return;
            }
            if (OKLog.D) {
                OKLog.d(TAG, "missionComplete() rootLayout\u672a\u88ab\u56de\u6536\uff0c\u53ef\u7ee7\u7eed\u540e\u7eed\u5de5\u4f5c");
            }
            State state = weakHashMap != null ? weakHashMap.get(viewGroup) : null;
            if (state != null) {
                state.removeMission();
            }
        }
    }

    @Override // com.jingdong.common.network.InternalActivityLifecycleCallbacks.IDestroyListener
    public void onDestroy() {
        Map<String, WeakHashMap<ViewGroup, State>> map = stateMap;
        synchronized (map) {
            release();
            map.remove(this.mActivityName);
        }
    }

    public void setProgressBarRootLayout(ViewGroup viewGroup) {
        if (viewGroup != null) {
            this.mRootLayout = new WeakReference<>(viewGroup);
        }
    }
}
