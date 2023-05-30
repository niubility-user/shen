package tv.danmaku.ijk.media.widget.orientation;

import android.app.Activity;
import android.graphics.Rect;
import android.view.ViewGroup;
import tv.danmaku.ijk.media.widget.JDPlayerView;
import tv.danmaku.ijk.media.widget.controller.JDControllerOptions;
import tv.danmaku.ijk.media.widget.orientation.FullPlayerDialog;

/* loaded from: classes11.dex */
public class FullPlayerChanger {
    private final JDPlayerView jdPlayerView;
    private final Activity mActivity;
    private JDControllerOptions.FullMode mFullMode;
    private FullPlayerDialog mFullVideoDialog;
    private int mOrientation;
    private PlayerRotationSensorListener mRotationSensorListener;
    private ScreenChangeListener mScreenChangeListener;

    /* renamed from: tv.danmaku.ijk.media.widget.orientation.FullPlayerChanger$4 */
    /* loaded from: classes11.dex */
    public static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$tv$danmaku$ijk$media$widget$controller$JDControllerOptions$FullMode;

        static {
            int[] iArr = new int[JDControllerOptions.FullMode.values().length];
            $SwitchMap$tv$danmaku$ijk$media$widget$controller$JDControllerOptions$FullMode = iArr;
            try {
                iArr[JDControllerOptions.FullMode.FULL_LAND.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$tv$danmaku$ijk$media$widget$controller$JDControllerOptions$FullMode[JDControllerOptions.FullMode.FULL_PORT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$tv$danmaku$ijk$media$widget$controller$JDControllerOptions$FullMode[JDControllerOptions.FullMode.FULL_SENSOR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* loaded from: classes11.dex */
    public interface ScreenChangeListener {
        void onChange(boolean z, int i2);
    }

    public FullPlayerChanger(Activity activity, JDPlayerView jDPlayerView, JDControllerOptions.FullMode fullMode) {
        this.mOrientation = 0;
        this.mActivity = activity;
        this.jdPlayerView = jDPlayerView;
        this.mFullMode = fullMode;
        sensorDisable();
        int i2 = AnonymousClass4.$SwitchMap$tv$danmaku$ijk$media$widget$controller$JDControllerOptions$FullMode[fullMode.ordinal()];
        if (i2 == 1) {
            this.mOrientation = 0;
        } else if (i2 == 2) {
            this.mOrientation = 1;
        } else if (i2 != 3) {
        } else {
            this.mOrientation = 0;
            sensorEnable();
        }
    }

    public void forceLayout() {
        JDPlayerView jDPlayerView = this.jdPlayerView;
        if (jDPlayerView == null || !jDPlayerView.isForceLayout()) {
            return;
        }
        this.jdPlayerView.post(new Runnable() { // from class: tv.danmaku.ijk.media.widget.orientation.FullPlayerChanger.3
            {
                FullPlayerChanger.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                FullPlayerChanger.this.jdPlayerView.requestLayout();
            }
        });
    }

    private void initRotateListener() {
        this.mRotationSensorListener = new PlayerRotationSensorListener(this.mActivity) { // from class: tv.danmaku.ijk.media.widget.orientation.FullPlayerChanger.1
            {
                FullPlayerChanger.this = this;
            }

            @Override // tv.danmaku.ijk.media.widget.orientation.PlayerRotationSensorListener
            public void onRotateChanged(int i2) {
                if (this.lastOrientation == i2 || !rotationSettingIsOpen(FullPlayerChanger.this.mActivity)) {
                    return;
                }
                if (FullPlayerChanger.this.mFullVideoDialog == null || i2 != 1) {
                    if (FullPlayerChanger.this.jdPlayerView == null || !FullPlayerChanger.this.jdPlayerView.isPlaying()) {
                        return;
                    }
                    if ((i2 == 0 || i2 == 8) && FullPlayerChanger.this.mFullVideoDialog == null) {
                        FullPlayerChanger.this.changeToFullScreen();
                        return;
                    }
                    return;
                }
                FullPlayerChanger.this.changeToHalfScreen();
            }
        };
    }

    public void changeToFullScreen() {
        if (this.mActivity != null && this.jdPlayerView != null) {
            try {
                new Rect(this.jdPlayerView.getLeft(), this.jdPlayerView.getTop(), this.jdPlayerView.getRight(), this.jdPlayerView.getBottom());
                this.mActivity.setRequestedOrientation(this.mOrientation);
                final ViewGroup viewGroup = (ViewGroup) this.jdPlayerView.getParent();
                this.jdPlayerView.getLayoutParams();
                viewGroup.indexOfChild(this.jdPlayerView);
                viewGroup.removeView(this.jdPlayerView);
                FullPlayerDialog fullPlayerDialog = new FullPlayerDialog();
                this.mFullVideoDialog = fullPlayerDialog;
                fullPlayerDialog.enterFullScreen(this.mActivity, this.jdPlayerView, this.mOrientation == 0);
                this.mFullVideoDialog.setOnDismissListener(new FullPlayerDialog.OnDismissListener
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0066: INVOKE 
                      (wrap: tv.danmaku.ijk.media.widget.orientation.FullPlayerDialog : 0x005d: IGET (r9v0 'this' tv.danmaku.ijk.media.widget.orientation.FullPlayerChanger A[IMMUTABLE_TYPE, THIS]) A[Catch: Exception -> 0x0072, WRAPPED] (LINE:11) tv.danmaku.ijk.media.widget.orientation.FullPlayerChanger.mFullVideoDialog tv.danmaku.ijk.media.widget.orientation.FullPlayerDialog)
                      (wrap: tv.danmaku.ijk.media.widget.orientation.FullPlayerDialog$OnDismissListener : 0x0063: CONSTRUCTOR 
                      (r9v0 'this' tv.danmaku.ijk.media.widget.orientation.FullPlayerChanger A[IMMUTABLE_TYPE, THIS])
                      (r3 I:int A[DONT_GENERATE, DONT_INLINE, REMOVE])
                      (r4v1 'viewGroup' android.view.ViewGroup A[DONT_INLINE])
                      (r5 I:android.view.ViewGroup$LayoutParams A[DONT_GENERATE, DONT_INLINE, REMOVE])
                      (r6 I:android.graphics.Rect A[DONT_GENERATE, DONT_INLINE, REMOVE])
                     A[Catch: Exception -> 0x0072, MD:(tv.danmaku.ijk.media.widget.orientation.FullPlayerChanger, int, android.view.ViewGroup, android.view.ViewGroup$LayoutParams, android.graphics.Rect):void (m), WRAPPED] call: tv.danmaku.ijk.media.widget.orientation.FullPlayerChanger.2.<init>(tv.danmaku.ijk.media.widget.orientation.FullPlayerChanger, int, android.view.ViewGroup, android.view.ViewGroup$LayoutParams, android.graphics.Rect):void type: CONSTRUCTOR)
                     type: VIRTUAL call: tv.danmaku.ijk.media.widget.orientation.FullPlayerDialog.setOnDismissListener(tv.danmaku.ijk.media.widget.orientation.FullPlayerDialog$OnDismissListener):void A[Catch: Exception -> 0x0072, MD:(tv.danmaku.ijk.media.widget.orientation.FullPlayerDialog$OnDismissListener):void (m)] (LINE:11) in method: tv.danmaku.ijk.media.widget.orientation.FullPlayerChanger.changeToFullScreen():void, file: classes11.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                    	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                    	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:302)
                    	at jadx.core.dex.regions.TryCatchRegion.generate(TryCatchRegion.java:85)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                    	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
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
                    	... 27 more
                    */
                /*
                    this = this;
                    android.app.Activity r0 = r9.mActivity
                    if (r0 == 0) goto L72
                    tv.danmaku.ijk.media.widget.JDPlayerView r0 = r9.jdPlayerView
                    if (r0 != 0) goto L9
                    goto L72
                L9:
                    android.graphics.Rect r6 = new android.graphics.Rect     // Catch: java.lang.Exception -> L72
                    tv.danmaku.ijk.media.widget.JDPlayerView r0 = r9.jdPlayerView     // Catch: java.lang.Exception -> L72
                    int r0 = r0.getLeft()     // Catch: java.lang.Exception -> L72
                    tv.danmaku.ijk.media.widget.JDPlayerView r1 = r9.jdPlayerView     // Catch: java.lang.Exception -> L72
                    int r1 = r1.getTop()     // Catch: java.lang.Exception -> L72
                    tv.danmaku.ijk.media.widget.JDPlayerView r2 = r9.jdPlayerView     // Catch: java.lang.Exception -> L72
                    int r2 = r2.getRight()     // Catch: java.lang.Exception -> L72
                    tv.danmaku.ijk.media.widget.JDPlayerView r3 = r9.jdPlayerView     // Catch: java.lang.Exception -> L72
                    int r3 = r3.getBottom()     // Catch: java.lang.Exception -> L72
                    r6.<init>(r0, r1, r2, r3)     // Catch: java.lang.Exception -> L72
                    android.app.Activity r0 = r9.mActivity     // Catch: java.lang.Exception -> L72
                    int r1 = r9.mOrientation     // Catch: java.lang.Exception -> L72
                    r0.setRequestedOrientation(r1)     // Catch: java.lang.Exception -> L72
                    tv.danmaku.ijk.media.widget.JDPlayerView r0 = r9.jdPlayerView     // Catch: java.lang.Exception -> L72
                    android.view.ViewParent r0 = r0.getParent()     // Catch: java.lang.Exception -> L72
                    r4 = r0
                    android.view.ViewGroup r4 = (android.view.ViewGroup) r4     // Catch: java.lang.Exception -> L72
                    tv.danmaku.ijk.media.widget.JDPlayerView r0 = r9.jdPlayerView     // Catch: java.lang.Exception -> L72
                    android.view.ViewGroup$LayoutParams r5 = r0.getLayoutParams()     // Catch: java.lang.Exception -> L72
                    tv.danmaku.ijk.media.widget.JDPlayerView r0 = r9.jdPlayerView     // Catch: java.lang.Exception -> L72
                    int r3 = r4.indexOfChild(r0)     // Catch: java.lang.Exception -> L72
                    tv.danmaku.ijk.media.widget.JDPlayerView r0 = r9.jdPlayerView     // Catch: java.lang.Exception -> L72
                    r4.removeView(r0)     // Catch: java.lang.Exception -> L72
                    tv.danmaku.ijk.media.widget.orientation.FullPlayerDialog r0 = new tv.danmaku.ijk.media.widget.orientation.FullPlayerDialog     // Catch: java.lang.Exception -> L72
                    r0.<init>()     // Catch: java.lang.Exception -> L72
                    r9.mFullVideoDialog = r0     // Catch: java.lang.Exception -> L72
                    android.app.Activity r1 = r9.mActivity     // Catch: java.lang.Exception -> L72
                    tv.danmaku.ijk.media.widget.JDPlayerView r2 = r9.jdPlayerView     // Catch: java.lang.Exception -> L72
                    int r7 = r9.mOrientation     // Catch: java.lang.Exception -> L72
                    r8 = 1
                    if (r7 != 0) goto L59
                    r7 = 1
                    goto L5a
                L59:
                    r7 = 0
                L5a:
                    r0.enterFullScreen(r1, r2, r7)     // Catch: java.lang.Exception -> L72
                    tv.danmaku.ijk.media.widget.orientation.FullPlayerDialog r0 = r9.mFullVideoDialog     // Catch: java.lang.Exception -> L72
                    tv.danmaku.ijk.media.widget.orientation.FullPlayerChanger$2 r7 = new tv.danmaku.ijk.media.widget.orientation.FullPlayerChanger$2     // Catch: java.lang.Exception -> L72
                    r1 = r7
                    r2 = r9
                    r1.<init>()     // Catch: java.lang.Exception -> L72
                    r0.setOnDismissListener(r7)     // Catch: java.lang.Exception -> L72
                    tv.danmaku.ijk.media.widget.orientation.FullPlayerChanger$ScreenChangeListener r0 = r9.mScreenChangeListener     // Catch: java.lang.Exception -> L72
                    if (r0 == 0) goto L72
                    int r1 = r9.mOrientation     // Catch: java.lang.Exception -> L72
                    r0.onChange(r8, r1)     // Catch: java.lang.Exception -> L72
                L72:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: tv.danmaku.ijk.media.widget.orientation.FullPlayerChanger.changeToFullScreen():void");
            }

            public void changeToHalfScreen() {
                FullPlayerDialog fullPlayerDialog = this.mFullVideoDialog;
                if (fullPlayerDialog == null) {
                    return;
                }
                fullPlayerDialog.dismiss(this.mActivity, false);
            }

            public void sensorDisable() {
                PlayerRotationSensorListener playerRotationSensorListener = this.mRotationSensorListener;
                if (playerRotationSensorListener != null) {
                    playerRotationSensorListener.disable();
                }
            }

            public void sensorEnable() {
                if (this.mRotationSensorListener == null) {
                    initRotateListener();
                }
                this.mRotationSensorListener.enable();
            }

            public void setScreenChangeListener(ScreenChangeListener screenChangeListener) {
                this.mScreenChangeListener = screenChangeListener;
            }

            public void updateFullMode(boolean z) {
                if (this.mFullMode != JDControllerOptions.FullMode.FULL_AUTO) {
                    return;
                }
                this.mOrientation = !z ? 1 : 0;
            }
        }
