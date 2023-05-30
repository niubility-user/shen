package com.facebook.react.views.modal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.R;
import com.facebook.react.bridge.GuardedRunnable;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.uimanager.JSTouchDispatcher;
import com.facebook.react.uimanager.RootView;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.views.common.ContextUtils;
import com.facebook.react.views.view.ReactViewGroup;
import java.util.ArrayList;
import javax.annotation.Nullable;

/* loaded from: classes12.dex */
public class ReactModalHostView extends ViewGroup implements LifecycleEventListener {
    private String mAnimationType;
    @Nullable
    private Dialog mDialog;
    private boolean mHardwareAccelerated;
    private DialogRootViewGroup mHostView;
    @Nullable
    private OnRequestCloseListener mOnRequestCloseListener;
    @Nullable
    private DialogInterface.OnShowListener mOnShowListener;
    private boolean mPropertyRequiresNewDialog;
    private boolean mTransparent;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public static class DialogRootViewGroup extends ReactViewGroup implements RootView {
        private final JSTouchDispatcher mJSTouchDispatcher;

        public DialogRootViewGroup(Context context) {
            super(context);
            this.mJSTouchDispatcher = new JSTouchDispatcher(this);
        }

        private EventDispatcher getEventDispatcher() {
            return ((UIManagerModule) getReactContext().getNativeModule(UIManagerModule.class)).getEventDispatcher();
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
            this.mJSTouchDispatcher.onChildStartedNativeGesture(motionEvent, getEventDispatcher());
        }

        @Override // com.facebook.react.views.view.ReactViewGroup, android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            this.mJSTouchDispatcher.handleTouchEvent(motionEvent, getEventDispatcher());
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
                      (r6v0 'this' com.facebook.react.views.modal.ReactModalHostView$DialogRootViewGroup A[IMMUTABLE_TYPE, THIS])
                      (r9v4 'reactContext' com.facebook.react.bridge.ReactContext)
                      (r3 I:int A[DONT_GENERATE, DONT_INLINE, REMOVE])
                      (r7v0 'i2' int A[DONT_INLINE])
                      (r8v0 'i3' int A[DONT_INLINE])
                     A[MD:(com.facebook.react.views.modal.ReactModalHostView$DialogRootViewGroup, com.facebook.react.bridge.ReactContext, int, int, int):void (m), WRAPPED] (LINE:5) call: com.facebook.react.views.modal.ReactModalHostView.DialogRootViewGroup.1.<init>(com.facebook.react.views.modal.ReactModalHostView$DialogRootViewGroup, com.facebook.react.bridge.ReactContext, int, int, int):void type: CONSTRUCTOR)
                     type: VIRTUAL call: com.facebook.react.bridge.ReactContext.runOnNativeModulesQueueThread(java.lang.Runnable):void A[MD:(java.lang.Runnable):void (m)] (LINE:5) in method: com.facebook.react.views.modal.ReactModalHostView.DialogRootViewGroup.onSizeChanged(int, int, int, int):void, file: classes12.dex
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
                    com.facebook.react.views.modal.ReactModalHostView$DialogRootViewGroup$1 r10 = new com.facebook.react.views.modal.ReactModalHostView$DialogRootViewGroup$1
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
                throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.modal.ReactModalHostView.DialogRootViewGroup.onSizeChanged(int, int, int, int):void");
            }

            @Override // com.facebook.react.views.view.ReactViewGroup, android.view.View
            public boolean onTouchEvent(MotionEvent motionEvent) {
                this.mJSTouchDispatcher.handleTouchEvent(motionEvent, getEventDispatcher());
                super.onTouchEvent(motionEvent);
                return true;
            }

            @Override // android.view.ViewGroup, android.view.ViewParent
            public void requestDisallowInterceptTouchEvent(boolean z) {
            }
        }

        /* loaded from: classes12.dex */
        public interface OnRequestCloseListener {
            void onRequestClose(DialogInterface dialogInterface);
        }

        public ReactModalHostView(Context context) {
            super(context);
            ((ReactContext) context).addLifecycleEventListener(this);
            this.mHostView = new DialogRootViewGroup(context);
        }

        private void dismiss() {
            Activity activity;
            Dialog dialog = this.mDialog;
            if (dialog != null) {
                if (dialog.isShowing() && ((activity = (Activity) ContextUtils.findContextOfType(this.mDialog.getContext(), Activity.class)) == null || !activity.isFinishing())) {
                    this.mDialog.dismiss();
                }
                this.mDialog = null;
                ((ViewGroup) this.mHostView.getParent()).removeViewAt(0);
            }
        }

        private View getContentView() {
            FrameLayout frameLayout = new FrameLayout(getContext());
            frameLayout.addView(this.mHostView);
            frameLayout.setFitsSystemWindows(true);
            return frameLayout;
        }

        @Nullable
        private Activity getCurrentActivity() {
            return ((ReactContext) getContext()).getCurrentActivity();
        }

        private void updateProperties() {
            Assertions.assertNotNull(this.mDialog, "mDialog must exist when we call updateProperties");
            Activity currentActivity = getCurrentActivity();
            if (currentActivity != null) {
                if ((currentActivity.getWindow().getAttributes().flags & 1024) != 0) {
                    this.mDialog.getWindow().addFlags(1024);
                } else {
                    this.mDialog.getWindow().clearFlags(1024);
                }
            }
            if (this.mTransparent) {
                this.mDialog.getWindow().clearFlags(2);
                return;
            }
            this.mDialog.getWindow().setDimAmount(0.5f);
            this.mDialog.getWindow().setFlags(2, 2);
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

        @Override // android.view.ViewGroup, android.view.View
        @TargetApi(23)
        public void dispatchProvideStructure(ViewStructure viewStructure) {
            this.mHostView.dispatchProvideStructure(viewStructure);
        }

        @Override // android.view.ViewGroup
        public View getChildAt(int i2) {
            return this.mHostView.getChildAt(i2);
        }

        @Override // android.view.ViewGroup
        public int getChildCount() {
            return this.mHostView.getChildCount();
        }

        @Nullable
        @VisibleForTesting
        public Dialog getDialog() {
            return this.mDialog;
        }

        public void onDropInstance() {
            ((ReactContext) getContext()).removeLifecycleEventListener(this);
            dismiss();
        }

        @Override // com.facebook.react.bridge.LifecycleEventListener
        public void onHostDestroy() {
            onDropInstance();
        }

        @Override // com.facebook.react.bridge.LifecycleEventListener
        public void onHostPause() {
        }

        @Override // com.facebook.react.bridge.LifecycleEventListener
        public void onHostResume() {
            showOrUpdate();
        }

        @Override // android.view.ViewGroup, android.view.View
        protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
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
            this.mPropertyRequiresNewDialog = true;
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
        public void setOnShowListener(DialogInterface.OnShowListener onShowListener) {
            this.mOnShowListener = onShowListener;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void setTransparent(boolean z) {
            this.mTransparent = z;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void showOrUpdate() {
            if (this.mDialog != null) {
                if (this.mPropertyRequiresNewDialog) {
                    dismiss();
                } else {
                    updateProperties();
                    return;
                }
            }
            this.mPropertyRequiresNewDialog = false;
            int i2 = R.style.Theme_FullScreenDialog;
            if (this.mAnimationType.equals("fade")) {
                i2 = R.style.Theme_FullScreenDialogAnimatedFade;
            } else if (this.mAnimationType.equals("slide")) {
                i2 = R.style.Theme_FullScreenDialogAnimatedSlide;
            }
            Activity currentActivity = getCurrentActivity();
            Dialog dialog = new Dialog(currentActivity == null ? getContext() : currentActivity, i2);
            this.mDialog = dialog;
            dialog.setContentView(getContentView());
            updateProperties();
            this.mDialog.setOnShowListener(this.mOnShowListener);
            this.mDialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.facebook.react.views.modal.ReactModalHostView.1
                @Override // android.content.DialogInterface.OnKeyListener
                public boolean onKey(DialogInterface dialogInterface, int i3, KeyEvent keyEvent) {
                    if (keyEvent.getAction() == 1) {
                        if (i3 == 4) {
                            Assertions.assertNotNull(ReactModalHostView.this.mOnRequestCloseListener, "setOnRequestCloseListener must be called by the manager");
                            ReactModalHostView.this.mOnRequestCloseListener.onRequestClose(dialogInterface);
                            return true;
                        }
                        Activity currentActivity2 = ((ReactContext) ReactModalHostView.this.getContext()).getCurrentActivity();
                        if (currentActivity2 != null) {
                            return currentActivity2.onKeyUp(i3, keyEvent);
                        }
                        return false;
                    }
                    return false;
                }
            });
            this.mDialog.getWindow().setSoftInputMode(16);
            if (this.mHardwareAccelerated) {
                this.mDialog.getWindow().addFlags(16777216);
            }
            if (currentActivity == null || currentActivity.isFinishing()) {
                return;
            }
            this.mDialog.show();
        }
    }
