package com.jingdong.jdreact.plugin.jdmodal;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.GuardedRunnable;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.uimanager.JSTouchDispatcher;
import com.facebook.react.uimanager.RootView;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.views.view.ReactViewGroup;
import java.util.ArrayList;

/* loaded from: classes13.dex */
public class JDReactModalHostView extends ViewGroup implements LifecycleEventListener {
    String backgroundColor;
    View backgroundview;
    private Animation inAnim;
    private String mAnimationType;
    private boolean mDismiss;
    private boolean mHardwareAccelerated;
    private final DialogRootViewGroup mHostView;
    private OnRequestCloseListener mOnRequestCloseListener;
    private OnShow2Listener mOnShow2Listener;
    private OnShowListener mOnShowListener;
    private boolean mPropertyRequiresNewDialog;
    private boolean mTransparent;
    private Animation outAnim;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public static class DialogRootViewGroup extends ReactViewGroup implements RootView {
        private final JSTouchDispatcher mJSTouchDispatcher;

        public DialogRootViewGroup(Context context) {
            super(context);
            this.mJSTouchDispatcher = new JSTouchDispatcher(this);
        }

        private EventDispatcher getEventDispatcher() {
            return ((UIManagerModule) ((ReactContext) getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ReactContext getReactContext() {
            return (ReactContext) getContext();
        }

        @Override // com.facebook.react.uimanager.RootView
        public void handleException(Throwable th) {
            getReactContext().handleException(new RuntimeException(th));
        }

        @Override // com.facebook.react.uimanager.RootView
        public void onChildStartedNativeGesture(MotionEvent motionEvent) {
            if (getAnimation() == null || getAnimation().hasEnded()) {
                try {
                    this.mJSTouchDispatcher.onChildStartedNativeGesture(motionEvent, getEventDispatcher());
                } catch (Exception unused) {
                }
            }
        }

        @Override // com.facebook.react.views.view.ReactViewGroup, android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (getAnimation() == null || getAnimation().hasEnded()) {
                try {
                    this.mJSTouchDispatcher.handleTouchEvent(motionEvent, getEventDispatcher());
                } catch (Exception unused) {
                }
            }
            return super.onInterceptTouchEvent(motionEvent);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.facebook.react.views.view.ReactViewGroup, android.view.View
        public void onSizeChanged(final int i2, final int i3, int i4, int i5) {
            super.onSizeChanged(i2, i3, i4, i5);
            if (getChildCount() > 0) {
                getChildAt(0).getId();
                ReactContext reactContext = getReactContext();
                reactContext.runOnNativeModulesQueueThread(new GuardedRunnable
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0020: INVOKE 
                      (r9v4 'reactContext' com.facebook.react.bridge.ReactContext)
                      (wrap: com.facebook.react.bridge.GuardedRunnable : 0x001d: CONSTRUCTOR 
                      (r6v0 'this' com.jingdong.jdreact.plugin.jdmodal.JDReactModalHostView$DialogRootViewGroup A[IMMUTABLE_TYPE, THIS])
                      (r9v4 'reactContext' com.facebook.react.bridge.ReactContext)
                      (r3 I:int A[DONT_GENERATE, DONT_INLINE, REMOVE])
                      (r7v0 'i2' int A[DONT_INLINE])
                      (r8v0 'i3' int A[DONT_INLINE])
                     A[MD:(com.jingdong.jdreact.plugin.jdmodal.JDReactModalHostView$DialogRootViewGroup, com.facebook.react.bridge.ReactContext, int, int, int):void (m), WRAPPED] (LINE:5) call: com.jingdong.jdreact.plugin.jdmodal.JDReactModalHostView.DialogRootViewGroup.1.<init>(com.jingdong.jdreact.plugin.jdmodal.JDReactModalHostView$DialogRootViewGroup, com.facebook.react.bridge.ReactContext, int, int, int):void type: CONSTRUCTOR)
                     type: VIRTUAL call: com.facebook.react.bridge.ReactContext.runOnNativeModulesQueueThread(java.lang.Runnable):void A[MD:(java.lang.Runnable):void (m)] (LINE:5) in method: com.jingdong.jdreact.plugin.jdmodal.JDReactModalHostView.DialogRootViewGroup.onSizeChanged(int, int, int, int):void, file: classes13.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                    	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                    	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
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
                    	... 23 more
                    */
                /*
                    this = this;
                    super.onSizeChanged(r7, r8, r9, r10)
                    int r9 = r6.getChildCount()
                    if (r9 <= 0) goto L23
                    r9 = 0
                    android.view.View r9 = r6.getChildAt(r9)
                    int r3 = r9.getId()
                    com.facebook.react.bridge.ReactContext r9 = r6.getReactContext()
                    com.jingdong.jdreact.plugin.jdmodal.JDReactModalHostView$DialogRootViewGroup$1 r10 = new com.jingdong.jdreact.plugin.jdmodal.JDReactModalHostView$DialogRootViewGroup$1
                    r0 = r10
                    r1 = r6
                    r2 = r9
                    r4 = r7
                    r5 = r8
                    r0.<init>(r2)
                    r9.runOnNativeModulesQueueThread(r10)
                L23:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.jingdong.jdreact.plugin.jdmodal.JDReactModalHostView.DialogRootViewGroup.onSizeChanged(int, int, int, int):void");
            }

            @Override // com.facebook.react.views.view.ReactViewGroup, android.view.View
            public boolean onTouchEvent(MotionEvent motionEvent) {
                if (getAnimation() == null || getAnimation().hasEnded()) {
                    try {
                        this.mJSTouchDispatcher.handleTouchEvent(motionEvent, getEventDispatcher());
                    } catch (Exception unused) {
                    }
                }
                super.onTouchEvent(motionEvent);
                return true;
            }

            @Override // android.view.ViewGroup, android.view.ViewParent
            public void requestDisallowInterceptTouchEvent(boolean z) {
            }
        }

        /* loaded from: classes13.dex */
        public interface OnRequestCloseListener {
            void onRequestClose();
        }

        /* loaded from: classes13.dex */
        public interface OnShow2Listener {
            void onShow();
        }

        /* loaded from: classes13.dex */
        public interface OnShowListener {
            void onShow();
        }

        public JDReactModalHostView(Context context) {
            super(context);
            this.mDismiss = false;
            this.backgroundColor = "";
            ((ReactContext) context).addLifecycleEventListener(this);
            this.mHostView = new DialogRootViewGroup(context);
        }

        private void dismiss() {
            Activity currentActivity;
            final View decorView;
            View view;
            if (this.backgroundview == null || (currentActivity = ((ReactContext) getContext()).getCurrentActivity()) == null || currentActivity.getWindow() == null || (decorView = currentActivity.getWindow().getDecorView()) == null || !(decorView instanceof ViewGroup) || (view = this.backgroundview) == null) {
                return;
            }
            Animation animation = this.outAnim;
            if (animation != null) {
                animation.setAnimationListener(new Animation.AnimationListener() { // from class: com.jingdong.jdreact.plugin.jdmodal.JDReactModalHostView.1
                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationEnd(Animation animation2) {
                        decorView.post(new Runnable() { // from class: com.jingdong.jdreact.plugin.jdmodal.JDReactModalHostView.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                                ((ViewGroup) decorView).removeView(JDReactModalHostView.this.backgroundview);
                                JDReactModalHostView.this.backgroundview = null;
                            }
                        });
                    }

                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationRepeat(Animation animation2) {
                    }

                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationStart(Animation animation2) {
                    }
                });
                this.mHostView.startAnimation(this.outAnim);
                return;
            }
            ((ViewGroup) decorView).removeView(view);
            this.backgroundview = null;
        }

        private void dispatchDismissEvent() {
            ReactContext reactContext = (ReactContext) getContext();
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            writableNativeMap.putInt("modalID", getId());
            if (reactContext != null) {
                ((DeviceEventManagerModule.RCTDeviceEventEmitter) reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("modalDismissed", writableNativeMap);
            }
        }

        private View getContentView() {
            Resources resources = getContext().getResources();
            int identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
            if (identifier > 0) {
                resources.getDimension(identifier);
            }
            FrameLayout frameLayout = new FrameLayout(getContext());
            DialogRootViewGroup dialogRootViewGroup = this.mHostView;
            if (dialogRootViewGroup != null) {
                ViewParent parent = dialogRootViewGroup.getParent();
                if (parent != null) {
                    ((ViewGroup) parent).removeView(this.mHostView);
                }
                frameLayout.addView(this.mHostView);
            }
            frameLayout.setFitsSystemWindows(true);
            return frameLayout;
        }

        private void updateProperties() {
            if (this.backgroundview == null || TextUtils.isEmpty(this.backgroundColor)) {
                return;
            }
            this.backgroundview.setBackgroundColor(Color.parseColor(this.backgroundColor));
        }

        @Override // android.view.ViewGroup, android.view.View
        public void addChildrenForAccessibility(ArrayList<View> arrayList) {
        }

        @Override // android.view.ViewGroup
        public void addView(View view, int i2) {
            this.mHostView.addView(view, i2);
        }

        @Override // android.view.View
        public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
            return false;
        }

        @Override // android.view.ViewGroup
        public View getChildAt(int i2) {
            return this.mHostView.getChildAt(i2);
        }

        @Override // android.view.ViewGroup
        public int getChildCount() {
            return this.mHostView.getChildCount();
        }

        public Animation getInAnimation(String str) {
            if (this.mAnimationType.equals("fade")) {
                return AnimationUtils.loadAnimation(getContext(), R.anim.catalyst_fade_in);
            }
            if (this.mAnimationType.equals("slide")) {
                return AnimationUtils.loadAnimation(getContext(), R.anim.catalyst_slide_up);
            }
            return null;
        }

        public Animation getOutAnimation() {
            if (this.mAnimationType.equals("fade")) {
                return AnimationUtils.loadAnimation(getContext(), R.anim.catalyst_fade_out);
            }
            if (this.mAnimationType.equals("slide")) {
                return AnimationUtils.loadAnimation(getContext(), R.anim.catalyst_slide_down);
            }
            return null;
        }

        public void onDropInstance() {
            if (this.mDismiss) {
                dispatchDismissEvent();
            }
            ((ReactContext) getContext()).removeLifecycleEventListener(this);
            dismiss();
        }

        @Override // com.facebook.react.bridge.LifecycleEventListener
        public void onHostDestroy() {
            onDropInstance();
        }

        @Override // com.facebook.react.bridge.LifecycleEventListener
        public void onHostPause() {
            dismiss();
        }

        @Override // com.facebook.react.bridge.LifecycleEventListener
        public void onHostResume() {
            showOrUpdate();
        }

        @Override // android.view.ViewGroup, android.view.View
        protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        }

        public void refreshLayout() {
            Activity currentActivity;
            if (this.backgroundview == null || (currentActivity = ((ReactContext) getContext()).getCurrentActivity()) == null || currentActivity.getWindow() == null) {
                return;
            }
            View decorView = currentActivity.getWindow().getDecorView();
            int i2 = 0;
            View findViewById = decorView.findViewById(16908290);
            if (findViewById != null && (findViewById instanceof FrameLayout)) {
                i2 = findViewById.getHeight();
            }
            if (decorView == null || !(decorView instanceof ViewGroup)) {
                return;
            }
            this.mHostView.clearAnimation();
            if (this.backgroundview.getParent() != null) {
                ((ViewGroup) decorView).removeView(this.backgroundview);
            }
            View contentView = getContentView();
            this.backgroundview = contentView;
            ViewGroup viewGroup = (ViewGroup) decorView;
            if (i2 == 0) {
                i2 = -1;
            }
            viewGroup.addView(contentView, new ViewGroup.LayoutParams(-1, i2));
            if (this.backgroundview != null && !TextUtils.isEmpty(this.backgroundColor)) {
                this.backgroundview.setBackgroundColor(Color.parseColor(this.backgroundColor));
            }
            this.backgroundview.setOnKeyListener(new View.OnKeyListener() { // from class: com.jingdong.jdreact.plugin.jdmodal.JDReactModalHostView.2
                @Override // android.view.View.OnKeyListener
                public boolean onKey(View view, int i3, KeyEvent keyEvent) {
                    if (keyEvent.getAction() == 1) {
                        if (i3 == 4) {
                            Assertions.assertNotNull(JDReactModalHostView.this.mOnRequestCloseListener, "setOnRequestCloseListener must be called by the manager");
                            JDReactModalHostView.this.mOnRequestCloseListener.onRequestClose();
                            return true;
                        }
                        Activity currentActivity2 = ((ReactContext) JDReactModalHostView.this.getContext()).getCurrentActivity();
                        if (currentActivity2 != null) {
                            return currentActivity2.onKeyUp(i3, keyEvent);
                        }
                        return false;
                    }
                    return false;
                }
            });
        }

        @Override // android.view.ViewGroup, android.view.ViewManager
        public void removeView(View view) {
            this.mHostView.removeView(view);
        }

        @Override // android.view.ViewGroup
        public void removeViewAt(int i2) {
            this.mHostView.removeView(getChildAt(i2));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void setAnimationType(String str) {
            this.mAnimationType = str;
            this.inAnim = getInAnimation(str);
            this.outAnim = getOutAnimation();
            this.mPropertyRequiresNewDialog = true;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void setDismiss(boolean z) {
            this.mDismiss = z;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void setHardwareAccelerated(boolean z) {
            this.mHardwareAccelerated = z;
            this.mPropertyRequiresNewDialog = true;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void setOnRequestCloseListener(OnRequestCloseListener onRequestCloseListener) {
            this.mOnRequestCloseListener = onRequestCloseListener;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void setOnShow2Listener(OnShow2Listener onShow2Listener) {
            this.mOnShow2Listener = onShow2Listener;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void setOnShowListener(OnShowListener onShowListener) {
            this.mOnShowListener = onShowListener;
        }

        public void setOverlayColor(String str) {
            this.backgroundColor = str;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void setTransparent(boolean z) {
            this.mTransparent = z;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void showOrUpdate() {
            if (this.backgroundview != null) {
                if (this.mPropertyRequiresNewDialog) {
                    dismiss();
                } else {
                    updateProperties();
                    return;
                }
            }
            int i2 = 0;
            this.mPropertyRequiresNewDialog = false;
            if (!this.mAnimationType.equals("fade")) {
                this.mAnimationType.equals("slide");
            }
            Activity currentActivity = ((ReactContext) getContext()).getCurrentActivity();
            if (currentActivity == null || currentActivity.getWindow() == null) {
                return;
            }
            View decorView = currentActivity.getWindow().getDecorView();
            View findViewById = decorView.findViewById(16908290);
            if (findViewById != null && (findViewById instanceof FrameLayout)) {
                i2 = findViewById.getHeight();
            }
            if (decorView == null || !(decorView instanceof ViewGroup)) {
                return;
            }
            this.mHostView.clearAnimation();
            View view = this.backgroundview;
            if (view == null) {
                View contentView = getContentView();
                this.backgroundview = contentView;
                contentView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.jdreact.plugin.jdmodal.JDReactModalHostView.3
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        Assertions.assertNotNull(JDReactModalHostView.this.mOnRequestCloseListener, "setOnRequestCloseListener must be called by the manager");
                        JDReactModalHostView.this.mOnRequestCloseListener.onRequestClose();
                    }
                });
            } else if (view.getParent() != null) {
                ((ViewGroup) decorView).removeView(this.backgroundview);
            }
            ViewGroup viewGroup = (ViewGroup) decorView;
            View view2 = this.backgroundview;
            if (i2 == 0) {
                i2 = -1;
            }
            viewGroup.addView(view2, new ViewGroup.LayoutParams(-1, i2));
            if (this.backgroundview != null && !TextUtils.isEmpty(this.backgroundColor)) {
                this.backgroundview.setBackgroundColor(Color.parseColor(this.backgroundColor));
            }
            this.backgroundview.setOnKeyListener(new View.OnKeyListener() { // from class: com.jingdong.jdreact.plugin.jdmodal.JDReactModalHostView.4
                @Override // android.view.View.OnKeyListener
                public boolean onKey(View view3, int i3, KeyEvent keyEvent) {
                    if (keyEvent.getAction() == 1) {
                        if (i3 == 4) {
                            Assertions.assertNotNull(JDReactModalHostView.this.mOnRequestCloseListener, "setOnRequestCloseListener must be called by the manager");
                            JDReactModalHostView.this.mOnRequestCloseListener.onRequestClose();
                            return true;
                        }
                        Activity currentActivity2 = ((ReactContext) JDReactModalHostView.this.getContext()).getCurrentActivity();
                        if (currentActivity2 != null) {
                            return currentActivity2.onKeyUp(i3, keyEvent);
                        }
                        return false;
                    }
                    return false;
                }
            });
            OnShowListener onShowListener = this.mOnShowListener;
            if (onShowListener != null) {
                onShowListener.onShow();
            }
            OnShow2Listener onShow2Listener = this.mOnShow2Listener;
            if (onShow2Listener != null && this.mDismiss) {
                onShow2Listener.onShow();
            }
            Animation animation = this.inAnim;
            if (animation != null) {
                this.mHostView.startAnimation(animation);
            }
        }
    }
