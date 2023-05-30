package com.jingdong.common.widget.video;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import com.jingdong.common.widget.custom.CustomIjkPlayer;
import com.jingdong.common.widget.video.FullVideoDialog;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes12.dex */
public class DefaultFullVideoChanger {
    private Activity mActivity;
    private BeforeOrientationChangeListener mBeforeOrientationChangeListener;
    private CustomIjkPlayer mCustomIjkPlayer;
    private FullVideoDialog mFullVideoDialog;
    private RotationSensorListener mRotationSensorListener;
    private ScreenChangeListener mScreenChangeListener;
    private int fullScreenOrientation = 6;
    private int smallScreenOrientation = 1;
    private final Runnable measureAndLayout = new Runnable() { // from class: com.jingdong.common.widget.video.DefaultFullVideoChanger.4
        @Override // java.lang.Runnable
        public void run() {
            if (DefaultFullVideoChanger.this.mCustomIjkPlayer == null) {
                return;
            }
            DefaultFullVideoChanger.this.mCustomIjkPlayer.requestLayout();
        }
    };
    private View.OnClickListener fullBtnOnClickListener = new View.OnClickListener() { // from class: com.jingdong.common.widget.video.DefaultFullVideoChanger.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (DefaultFullVideoChanger.this.mFullVideoDialog == null) {
                DefaultFullVideoChanger.this.changeToFullScreen();
            } else {
                DefaultFullVideoChanger.this.changeToSmallScreen();
            }
        }
    };

    /* loaded from: classes12.dex */
    public interface BeforeOrientationChangeListener {
        void onChange(boolean z, int i2);
    }

    /* loaded from: classes12.dex */
    public interface ScreenChangeListener {
        void onChange(boolean z, int i2);
    }

    public DefaultFullVideoChanger(Activity activity, CustomIjkPlayer customIjkPlayer) {
        this.mActivity = activity;
        this.mCustomIjkPlayer = customIjkPlayer;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changeToFullScreen() {
        if (this.mActivity == null) {
            return;
        }
        BeforeOrientationChangeListener beforeOrientationChangeListener = this.mBeforeOrientationChangeListener;
        if (beforeOrientationChangeListener != null) {
            beforeOrientationChangeListener.onChange(false, 1);
        }
        this.mActivity.setRequestedOrientation(this.fullScreenOrientation);
        this.mCustomIjkPlayer.setUiFullScreenState(true);
        final ViewGroup viewGroup = (ViewGroup) this.mCustomIjkPlayer.getParent();
        this.mCustomIjkPlayer.getLayoutParams();
        new Rect(this.mCustomIjkPlayer.getLeft(), this.mCustomIjkPlayer.getTop(), this.mCustomIjkPlayer.getRight(), this.mCustomIjkPlayer.getBottom());
        viewGroup.indexOfChild(this.mCustomIjkPlayer);
        viewGroup.removeView(this.mCustomIjkPlayer);
        FullVideoDialog fullVideoDialog = new FullVideoDialog();
        this.mFullVideoDialog = fullVideoDialog;
        fullVideoDialog.showFullScreen(this.mActivity, this.mCustomIjkPlayer, "");
        this.mFullVideoDialog.setOnDismissListener(new FullVideoDialog.OnDismissListener
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x006a: INVOKE 
              (wrap: com.jingdong.common.widget.video.FullVideoDialog : 0x0061: IGET (r9v0 'this' com.jingdong.common.widget.video.DefaultFullVideoChanger A[IMMUTABLE_TYPE, THIS]) A[WRAPPED] (LINE:14) com.jingdong.common.widget.video.DefaultFullVideoChanger.mFullVideoDialog com.jingdong.common.widget.video.FullVideoDialog)
              (wrap: com.jingdong.common.widget.video.FullVideoDialog$OnDismissListener : 0x0067: CONSTRUCTOR 
              (r9v0 'this' com.jingdong.common.widget.video.DefaultFullVideoChanger A[IMMUTABLE_TYPE, THIS])
              (r4 I:int A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r5v1 'viewGroup' android.view.ViewGroup A[DONT_INLINE])
              (r6 I:android.view.ViewGroup$LayoutParams A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r7 I:android.graphics.Rect A[DONT_GENERATE, DONT_INLINE, REMOVE])
             A[MD:(com.jingdong.common.widget.video.DefaultFullVideoChanger, int, android.view.ViewGroup, android.view.ViewGroup$LayoutParams, android.graphics.Rect):void (m), WRAPPED] call: com.jingdong.common.widget.video.DefaultFullVideoChanger.3.<init>(com.jingdong.common.widget.video.DefaultFullVideoChanger, int, android.view.ViewGroup, android.view.ViewGroup$LayoutParams, android.graphics.Rect):void type: CONSTRUCTOR)
             type: VIRTUAL call: com.jingdong.common.widget.video.FullVideoDialog.setOnDismissListener(com.jingdong.common.widget.video.FullVideoDialog$OnDismissListener):void A[MD:(com.jingdong.common.widget.video.FullVideoDialog$OnDismissListener):void (m)] (LINE:14) in method: com.jingdong.common.widget.video.DefaultFullVideoChanger.changeToFullScreen():void, file: classes12.dex
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
            */
        /*
            this = this;
            android.app.Activity r0 = r9.mActivity
            if (r0 != 0) goto L5
            return
        L5:
            com.jingdong.common.widget.video.DefaultFullVideoChanger$BeforeOrientationChangeListener r0 = r9.mBeforeOrientationChangeListener
            r1 = 1
            if (r0 == 0) goto Le
            r2 = 0
            r0.onChange(r2, r1)
        Le:
            android.app.Activity r0 = r9.mActivity
            int r2 = r9.fullScreenOrientation
            r0.setRequestedOrientation(r2)
            com.jingdong.common.widget.custom.CustomIjkPlayer r0 = r9.mCustomIjkPlayer
            r0.setUiFullScreenState(r1)
            com.jingdong.common.widget.custom.CustomIjkPlayer r0 = r9.mCustomIjkPlayer
            android.view.ViewParent r0 = r0.getParent()
            r5 = r0
            android.view.ViewGroup r5 = (android.view.ViewGroup) r5
            com.jingdong.common.widget.custom.CustomIjkPlayer r0 = r9.mCustomIjkPlayer
            android.view.ViewGroup$LayoutParams r6 = r0.getLayoutParams()
            android.graphics.Rect r7 = new android.graphics.Rect
            com.jingdong.common.widget.custom.CustomIjkPlayer r0 = r9.mCustomIjkPlayer
            int r0 = r0.getLeft()
            com.jingdong.common.widget.custom.CustomIjkPlayer r2 = r9.mCustomIjkPlayer
            int r2 = r2.getTop()
            com.jingdong.common.widget.custom.CustomIjkPlayer r3 = r9.mCustomIjkPlayer
            int r3 = r3.getRight()
            com.jingdong.common.widget.custom.CustomIjkPlayer r4 = r9.mCustomIjkPlayer
            int r4 = r4.getBottom()
            r7.<init>(r0, r2, r3, r4)
            com.jingdong.common.widget.custom.CustomIjkPlayer r0 = r9.mCustomIjkPlayer
            int r4 = r5.indexOfChild(r0)
            com.jingdong.common.widget.custom.CustomIjkPlayer r0 = r9.mCustomIjkPlayer
            r5.removeView(r0)
            com.jingdong.common.widget.video.FullVideoDialog r0 = new com.jingdong.common.widget.video.FullVideoDialog
            r0.<init>()
            r9.mFullVideoDialog = r0
            android.app.Activity r2 = r9.mActivity
            com.jingdong.common.widget.custom.CustomIjkPlayer r3 = r9.mCustomIjkPlayer
            java.lang.String r8 = ""
            r0.showFullScreen(r2, r3, r8)
            com.jingdong.common.widget.video.FullVideoDialog r0 = r9.mFullVideoDialog
            com.jingdong.common.widget.video.DefaultFullVideoChanger$3 r8 = new com.jingdong.common.widget.video.DefaultFullVideoChanger$3
            r2 = r8
            r3 = r9
            r2.<init>()
            r0.setOnDismissListener(r8)
            com.jingdong.common.widget.video.DefaultFullVideoChanger$ScreenChangeListener r0 = r9.mScreenChangeListener
            if (r0 == 0) goto L76
            int r2 = r9.fullScreenOrientation
            r0.onChange(r1, r2)
        L76:
            r9.forceLayout()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.widget.video.DefaultFullVideoChanger.changeToFullScreen():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void forceLayout() {
        CustomIjkPlayer customIjkPlayer = this.mCustomIjkPlayer;
        if (customIjkPlayer == null || !customIjkPlayer.getIsForceLayout()) {
            return;
        }
        this.mCustomIjkPlayer.postDelayed(this.measureAndLayout, 1500L);
    }

    private void initRotateListener() {
        this.mRotationSensorListener = new RotationSensorListener(this.mActivity) { // from class: com.jingdong.common.widget.video.DefaultFullVideoChanger.2
            @Override // com.jingdong.common.widget.video.RotationSensorListener
            public void onRotateChanged(int i2) {
                if (OKLog.D) {
                    OKLog.d("RotationSensorListener", "onOrientationChanged  lastOrientation " + this.lastOrientation + " orientation " + i2);
                }
                if (this.lastOrientation == i2 || !rotationSettingIsOpen(DefaultFullVideoChanger.this.mActivity)) {
                    return;
                }
                if (DefaultFullVideoChanger.this.mFullVideoDialog == null || i2 != 1) {
                    if (DefaultFullVideoChanger.this.mCustomIjkPlayer == null || !DefaultFullVideoChanger.this.mCustomIjkPlayer.isPlaying()) {
                        return;
                    }
                    if ((i2 == 0 || i2 == 8) && DefaultFullVideoChanger.this.mFullVideoDialog == null) {
                        DefaultFullVideoChanger.this.changeToFullScreen();
                        return;
                    }
                    return;
                }
                DefaultFullVideoChanger.this.changeToSmallScreen();
            }
        };
    }

    public void changeToSmallScreen() {
        FullVideoDialog fullVideoDialog = this.mFullVideoDialog;
        if (fullVideoDialog == null) {
            return;
        }
        fullVideoDialog.dismiss(this.mActivity, false);
    }

    public View.OnClickListener getFullBtnOnClickListener() {
        return this.fullBtnOnClickListener;
    }

    public void sensorDisable() {
        RotationSensorListener rotationSensorListener = this.mRotationSensorListener;
        if (rotationSensorListener != null) {
            rotationSensorListener.disable();
        }
    }

    public void sensorEnable() {
        if (this.mRotationSensorListener == null) {
            initRotateListener();
        }
        this.mRotationSensorListener.enable();
    }

    public void setBeforeOrientationChangeListener(BeforeOrientationChangeListener beforeOrientationChangeListener) {
        this.mBeforeOrientationChangeListener = beforeOrientationChangeListener;
    }

    public void setFullScreenOrientation(int i2) {
        if (i2 == 6) {
            this.fullScreenOrientation = 6;
            this.smallScreenOrientation = 1;
        } else if (i2 == 1) {
            this.fullScreenOrientation = 1;
            this.smallScreenOrientation = 1;
        }
    }

    public void setScreenChangeListener(ScreenChangeListener screenChangeListener) {
        this.mScreenChangeListener = screenChangeListener;
    }
}
