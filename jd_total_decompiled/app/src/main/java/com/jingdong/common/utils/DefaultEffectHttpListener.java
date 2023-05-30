package com.jingdong.common.utils;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jingdong.common.BaseApplication;
import com.jingdong.common.R;
import com.jingdong.common.frame.IDestroyListener;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.sdk.oklog.OKLog;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public class DefaultEffectHttpListener implements HttpGroup.OnStartListener, HttpGroup.OnEndListener, HttpGroup.OnErrorListener, IDestroyListener {
    private static final String TAG = "DefaultEffectHttpListener";
    private static final Map<IMyActivity, State> stateMap = Collections.synchronizedMap(new HashMap());
    private IMyActivity myActivity;
    private HttpGroup.OnEndListener onEndListener;
    private HttpGroup.OnErrorListener onErrorListener;
    private HttpGroup.OnStartListener onStartListener;
    private boolean onTouchEvent;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class State implements Runnable {
        private static final int WAITING = -1;
        private static final int WAIT_TIME = 50;
        private boolean hasThread;
        private int missionCount;
        private ViewGroup modal;
        private IMyActivity myActivity;
        private ProgressBar progressBar;
        private TextView progressTextView;
        private ViewGroup rootFrameLayout;
        private int waitTime = 50;

        public State(IMyActivity iMyActivity) {
            this.myActivity = iMyActivity;
        }

        private void firstMission() {
            if (this.hasThread) {
                this.waitTime = -1;
                notify();
                return;
            }
            getRootFrameLayout();
            getModal();
            newProgressBar();
            this.myActivity.post(new Runnable
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x001d: INVOKE 
                  (wrap: com.jingdong.common.frame.IMyActivity : 0x0016: IGET (r4v0 'this' com.jingdong.common.utils.DefaultEffectHttpListener$State A[IMMUTABLE_TYPE, THIS]) A[WRAPPED] (LINE:7) com.jingdong.common.utils.DefaultEffectHttpListener.State.myActivity com.jingdong.common.frame.IMyActivity)
                  (wrap: java.lang.Runnable : 0x001a: CONSTRUCTOR 
                  (r4v0 'this' com.jingdong.common.utils.DefaultEffectHttpListener$State A[IMMUTABLE_TYPE, THIS])
                  (r1 I:android.view.ViewGroup A[DONT_GENERATE, DONT_INLINE, REMOVE])
                  (r0 I:android.view.ViewGroup A[DONT_GENERATE, DONT_INLINE, REMOVE])
                 A[MD:(com.jingdong.common.utils.DefaultEffectHttpListener$State, android.view.ViewGroup, android.view.ViewGroup):void (m), WRAPPED] call: com.jingdong.common.utils.DefaultEffectHttpListener.State.3.<init>(com.jingdong.common.utils.DefaultEffectHttpListener$State, android.view.ViewGroup, android.view.ViewGroup):void type: CONSTRUCTOR)
                 type: INTERFACE call: com.jingdong.common.frame.IMyActivity.post(java.lang.Runnable):void A[MD:(java.lang.Runnable):void (m)] (LINE:7) in method: com.jingdong.common.utils.DefaultEffectHttpListener.State.firstMission():void, file: classes6.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                Caused by: java.lang.NullPointerException
                	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:798)
                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1105)
                	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:872)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:421)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
                	... 19 more
                */
            /*
                this = this;
                boolean r0 = r4.hasThread
                if (r0 == 0) goto Lb
                r0 = -1
                r4.waitTime = r0
                r4.notify()
                goto L20
            Lb:
                android.view.ViewGroup r0 = r4.getRootFrameLayout()
                android.view.ViewGroup r1 = r4.getModal()
                r4.newProgressBar()
                com.jingdong.common.frame.IMyActivity r2 = r4.myActivity
                com.jingdong.common.utils.DefaultEffectHttpListener$State$3 r3 = new com.jingdong.common.utils.DefaultEffectHttpListener$State$3
                r3.<init>()
                r2.post(r3)
            L20:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.utils.DefaultEffectHttpListener.State.firstMission():void");
        }

        private ViewGroup getModal() {
            ViewGroup viewGroup = this.modal;
            if (viewGroup != null) {
                return viewGroup;
            }
            RelativeLayout relativeLayout = new RelativeLayout(this.myActivity.getThisActivity());
            this.modal = relativeLayout;
            relativeLayout.setOnTouchListener(new View.OnTouchListener() { // from class: com.jingdong.common.utils.DefaultEffectHttpListener.State.2
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return !DefaultEffectHttpListener.this.isOnTouchEvent();
                }
            });
            return this.modal;
        }

        private ViewGroup getRootFrameLayout() {
            ViewGroup viewGroup = this.rootFrameLayout;
            if (viewGroup != null) {
                return viewGroup;
            }
            ViewGroup viewGroup2 = (ViewGroup) this.myActivity.getThisActivity().getWindow().peekDecorView();
            this.rootFrameLayout = viewGroup2;
            if (viewGroup2 == null) {
                try {
                    Thread.sleep(50L);
                } catch (InterruptedException unused) {
                }
                this.rootFrameLayout = getRootFrameLayout();
            }
            return this.rootFrameLayout;
        }

        private void lastMission() {
            if (this.hasThread) {
                this.waitTime = 50;
                notify();
                return;
            }
            Thread thread = new Thread(this);
            thread.setName("DefaultEffectHttpListener_lastMission");
            thread.start();
            this.hasThread = true;
        }

        private void newProgressBar() {
            this.myActivity.post(new Runnable() { // from class: com.jingdong.common.utils.DefaultEffectHttpListener.State.1
                @Override // java.lang.Runnable
                public void run() {
                    State.this.modal.removeView(State.this.progressBar);
                    try {
                        State.this.progressBar = BaseApplication.getLoadingProgressBar();
                        State.this.modal.addView(State.this.progressBar);
                    } catch (Throwable unused) {
                        if (State.this.progressTextView != null) {
                            State.this.modal.removeView(State.this.progressTextView);
                        } else {
                            State state = State.this;
                            state.progressTextView = state.getLoadingTextView();
                            State.this.progressTextView.setText("\u52a0\u8f7d\u4e2d...");
                        }
                        State.this.modal.addView(State.this.progressTextView);
                    }
                }
            });
        }

        public synchronized boolean addMission() {
            int i2 = this.missionCount + 1;
            this.missionCount = i2;
            if (i2 == 1) {
                firstMission();
                return true;
            }
            return false;
        }

        public TextView getLoadingTextView() {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(13);
            TextView textView = new TextView(this.myActivity.getThisActivity());
            textView.setPadding(10, 10, 10, 10);
            textView.setLayoutParams(layoutParams);
            textView.setGravity(17);
            textView.setBackgroundColor(this.myActivity.getThisActivity().getResources().getColor(R.color.common_textview_bg_color));
            return textView;
        }

        public synchronized boolean removeMission() {
            int i2 = this.missionCount - 1;
            this.missionCount = i2;
            if (i2 < 0) {
                this.missionCount = 0;
                return false;
            } else if (i2 < 1) {
                lastMission();
                return true;
            } else {
                return false;
            }
        }

        @Override // java.lang.Runnable
        public synchronized void run() {
            do {
                int i2 = this.waitTime;
                if (i2 == -1) {
                    try {
                        wait();
                    } catch (InterruptedException e2) {
                        OKLog.e(DefaultEffectHttpListener.TAG, e2);
                    }
                } else {
                    try {
                        this.waitTime = 0;
                        wait(i2);
                    } catch (InterruptedException e3) {
                        OKLog.e(DefaultEffectHttpListener.TAG, e3);
                    }
                }
            } while (this.waitTime != 0);
            getRootFrameLayout();
            getModal();
            this.myActivity.post(new Runnable
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0032: INVOKE 
                  (wrap: com.jingdong.common.frame.IMyActivity : 0x002b: IGET (r5v0 'this' com.jingdong.common.utils.DefaultEffectHttpListener$State A[IMMUTABLE_TYPE, THIS]) A[Catch: all -> 0x003d, WRAPPED] (LINE:10) com.jingdong.common.utils.DefaultEffectHttpListener.State.myActivity com.jingdong.common.frame.IMyActivity)
                  (wrap: java.lang.Runnable : 0x002f: CONSTRUCTOR 
                  (r5v0 'this' com.jingdong.common.utils.DefaultEffectHttpListener$State A[IMMUTABLE_TYPE, THIS])
                  (r1 I:android.view.ViewGroup A[DONT_GENERATE, DONT_INLINE, REMOVE])
                  (r0 I:android.view.ViewGroup A[DONT_GENERATE, DONT_INLINE, REMOVE])
                 A[Catch: all -> 0x003d, MD:(com.jingdong.common.utils.DefaultEffectHttpListener$State, android.view.ViewGroup, android.view.ViewGroup):void (m), WRAPPED] call: com.jingdong.common.utils.DefaultEffectHttpListener.State.4.<init>(com.jingdong.common.utils.DefaultEffectHttpListener$State, android.view.ViewGroup, android.view.ViewGroup):void type: CONSTRUCTOR)
                 type: INTERFACE call: com.jingdong.common.frame.IMyActivity.post(java.lang.Runnable):void A[Catch: all -> 0x003d, MD:(java.lang.Runnable):void (m)] (LINE:10) in method: com.jingdong.common.utils.DefaultEffectHttpListener.State.run():void, file: classes6.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                Caused by: java.lang.NullPointerException
                	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:798)
                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1105)
                	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:872)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:421)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
                	... 19 more
                */
            /*
                this = this;
                monitor-enter(r5)
            L1:
                int r0 = r5.waitTime     // Catch: java.lang.Throwable -> L3d
                r1 = -1
                r2 = 0
                if (r0 != r1) goto L12
                r5.wait()     // Catch: java.lang.InterruptedException -> Lb java.lang.Throwable -> L3d
                goto L1f
            Lb:
                r0 = move-exception
                java.lang.String r1 = "DefaultEffectHttpListener"
                com.jingdong.sdk.oklog.OKLog.e(r1, r0)     // Catch: java.lang.Throwable -> L3d
                goto L1f
            L12:
                r5.waitTime = r2     // Catch: java.lang.InterruptedException -> L19 java.lang.Throwable -> L3d
                long r0 = (long) r0     // Catch: java.lang.InterruptedException -> L19 java.lang.Throwable -> L3d
                r5.wait(r0)     // Catch: java.lang.InterruptedException -> L19 java.lang.Throwable -> L3d
                goto L1f
            L19:
                r0 = move-exception
                java.lang.String r1 = "DefaultEffectHttpListener"
                com.jingdong.sdk.oklog.OKLog.e(r1, r0)     // Catch: java.lang.Throwable -> L3d
            L1f:
                int r0 = r5.waitTime     // Catch: java.lang.Throwable -> L3d
                if (r0 != 0) goto L1
                android.view.ViewGroup r0 = r5.getRootFrameLayout()     // Catch: java.lang.Throwable -> L3d
                android.view.ViewGroup r1 = r5.getModal()     // Catch: java.lang.Throwable -> L3d
                com.jingdong.common.frame.IMyActivity r3 = r5.myActivity     // Catch: java.lang.Throwable -> L3d
                com.jingdong.common.utils.DefaultEffectHttpListener$State$4 r4 = new com.jingdong.common.utils.DefaultEffectHttpListener$State$4     // Catch: java.lang.Throwable -> L3d
                r4.<init>()     // Catch: java.lang.Throwable -> L3d
                r3.post(r4)     // Catch: java.lang.Throwable -> L3d
                r0 = 50
                r5.waitTime = r0     // Catch: java.lang.Throwable -> L3d
                r5.hasThread = r2     // Catch: java.lang.Throwable -> L3d
                monitor-exit(r5)
                return
            L3d:
                r0 = move-exception
                monitor-exit(r5)
                goto L41
            L40:
                throw r0
            L41:
                goto L40
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.utils.DefaultEffectHttpListener.State.run():void");
        }
    }

    public DefaultEffectHttpListener(HttpSetting httpSetting, IMyActivity iMyActivity) {
        if (httpSetting != null) {
            this.onStartListener = httpSetting.getOnStartListener();
            this.onEndListener = httpSetting.getOnEndListener();
            this.onErrorListener = httpSetting.getOnErrorListener();
            setOnTouchEvent(httpSetting.isOnTouchEvent());
        }
        this.myActivity = iMyActivity;
        iMyActivity.addDestroyListener(this);
    }

    private void missionBegins() {
        Map<IMyActivity, State> map = stateMap;
        synchronized (map) {
            if (this.myActivity == null) {
                return;
            }
            if (OKLog.D) {
                OKLog.d(TAG, "state get with -->> " + this.myActivity);
            }
            State state = map.get(this.myActivity);
            if (OKLog.D) {
                OKLog.d(TAG, "state get -->> " + state);
            }
            if (state == null) {
                state = new State(this.myActivity);
                map.put(this.myActivity, state);
            }
            state.addMission();
        }
    }

    private void missionComplete() {
        Map<IMyActivity, State> map = stateMap;
        synchronized (map) {
            IMyActivity iMyActivity = this.myActivity;
            if (iMyActivity == null) {
                return;
            }
            State state = map.get(iMyActivity);
            if (state == null) {
                return;
            }
            state.removeMission();
        }
    }

    public boolean isOnTouchEvent() {
        return this.onTouchEvent;
    }

    @Override // com.jingdong.common.frame.IDestroyListener
    public void onDestroy() {
        Map<IMyActivity, State> map = stateMap;
        synchronized (map) {
            map.remove(this.myActivity);
            this.myActivity = null;
        }
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
    public void onEnd(HttpResponse httpResponse) {
        HttpGroup.OnEndListener onEndListener = this.onEndListener;
        if (onEndListener != null) {
            onEndListener.onEnd(httpResponse);
        }
        missionComplete();
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
    public void onError(HttpError httpError) {
        HttpGroup.OnErrorListener onErrorListener = this.onErrorListener;
        if (onErrorListener != null) {
            onErrorListener.onError(httpError);
        }
        missionComplete();
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
    public void onStart() {
        missionBegins();
        HttpGroup.OnStartListener onStartListener = this.onStartListener;
        if (onStartListener != null) {
            onStartListener.onStart();
        }
    }

    public void setOnTouchEvent(boolean z) {
        this.onTouchEvent = z;
    }
}
