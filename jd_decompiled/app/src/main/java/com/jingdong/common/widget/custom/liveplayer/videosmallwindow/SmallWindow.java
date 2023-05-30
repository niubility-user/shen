package com.jingdong.common.widget.custom.liveplayer.videosmallwindow;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.app.AppOpsManager;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;
import com.facebook.common.util.UriUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.common.Interpolator.AccelerateDecelerateInterpolator;
import com.jingdong.common.R;
import com.jingdong.common.widget.custom.liveplayer.LivePlayer;
import com.jingdong.common.widget.custom.liveplayer.LiveUIConfigBean;
import com.jingdong.common.widget.custom.liveplayer.callback.SmallWindowUICallback;
import com.jingdong.common.widget.custom.liveplayer.decoration.IDecorator;
import com.jingdong.common.widget.custom.liveplayer.videosmallwindow.ISmallWindowManager;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.platform.business.personal.R2;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import tv.danmaku.ijk.media.ext.mta.bean.PlayerReportInfoEntity;

/* loaded from: classes12.dex */
public class SmallWindow {
    public static float DEFAULT_ATTACH_SIDE_HEIGHT_SIZE = 100.0f;
    public static final float DEFAULT_ATTACH_SIDE_LANSCAPE_PERCENT = 0.2f;
    public static float DEFAULT_ATTACH_SIDE_PERCENT = 0.3f;
    public static final float DEFAULT_ATTACH_SIDE_PORTRAIT_PERCENT = 0.3f;
    public static final String DEFAULT_BORDER_COLOR = "#F5253C";
    public static final int DEFAULT_BORDER_WIDTH = 2;
    public static final int DEFAULT_BOTTOM_SAFE_DISTANCE = 118;
    public static final int DEFAULT_CLOSE_ICON_RIGHTMARGIN = 6;
    public static final int DEFAULT_CLOSE_ICON_SIZE = 22;
    public static final int DEFAULT_CLOSE_ICON_TOPMARGIN = 6;
    public static final int DEFAULT_CORNER_RADIUS = 12;
    public static final int DEFAULT_LANDSCAPE_HEIGHT = 90;
    public static final int DEFAULT_LANDSCAPE_WIDTH = 160;
    public static final int DEFAULT_LEFT_RIGHT_SAFE_DISTANCE = 10;
    public static final int DEFAULT_MUTE_ICON_BOTTOM_MARGIN = 6;
    public static final int DEFAULT_MUTE_ICON_RIGHT_MARGIN = 6;
    public static final int DEFAULT_MUTE_ICON_SIZE = 26;
    public static final int DEFAULT_PLAY_STATUS_ICON_LEFTMARGIN = 6;
    public static final int DEFAULT_PLAY_STATUS_ICON_SIZE_HEIGHT = 12;
    public static final int DEFAULT_PLAY_STATUS_ICON_SIZE_WIDTH = -2;
    public static final int DEFAULT_PLAY_STATUS_ICON_TOPMARGIN = 6;
    public static final int DEFAULT_PORTRAIT_HEIGHT = 180;
    public static final int DEFAULT_PORTRAIT_WIDTH = 102;
    public static final String DEFAULT_REPLAY_BORDER_COLOR = "#8040FF";
    public static final boolean DEFAULT_SHOW_MUTE = true;
    public static final int DEFAULT_TOP_SAFE_DISTANCE = 30;
    public static final int DIRECTION_LEFT = 1;
    public static final int DIRECTION_RIGHT = 2;
    public static final float PLAY_STATUS_LIVE_SIZE_SCALE = 2.5625f;
    public static final float PLAY_STATUS_REPLAY_SIZE_SCALE = 3.25f;
    private static final String TAG = "SmallWindow";
    public static Point sLastDismissPosition = new Point();
    private String borderColor;
    private float borderRadius;
    private int borderWidth;
    private int bottomSafeDistance;
    private FrameLayout.LayoutParams contentLayoutParams;
    private Holder holder;
    private ISmallWindowManager.IPlayerChange iPlayerChange;
    private boolean isHasAlertPermission;
    private boolean isLandScape;
    private boolean isVoiceOn;
    private WindowManager.LayoutParams layoutParams;
    private int layoutParamsTypeWithInit;
    private int leftRightSafeDistance;
    private AnimatorSet mAttachSideAnimatorSet;
    private String mBlurImageUrl;
    private Context mContext;
    private IDecorator mIDecorator;
    private boolean mIsInPostponeSmallWindowStatus;
    private boolean mNeedAttach;
    public float mNeedChangeHeightSize;
    private boolean mNeedOpenHeightChangeAnimation;
    private boolean mNeedShowMuteIcon;
    public int mOriginalContentHeightSize;
    private int mPlayStatusIconSizeHeight;
    private int mRootMeasuredHeight;
    private int mRootMeasuredWidth;
    private SmallWindowUICallback mSmallWindowUICallBack;
    private Set<SmallWindowUICallback> mSmallWindowUICallBacks;
    private Runnable mUpdateSmallwindowRunnable;
    private Point metrics;
    private AnimatorSet mpackUpSmallWindowAnimatorSet;
    private View.OnClickListener onCloseClick;
    private View.OnClickListener onMuteClicked;
    private boolean showBorder;
    private int topSafeDistance;
    private LiveUIConfigBean uiConfigBean;
    private int windowPosX;
    private int windowPosY;
    private WindowManager wm;

    /* loaded from: classes12.dex */
    public static class CheckPermission {
        private static final int OP_SYSTEM_ALERT_WINDOW = 24;

        private CheckPermission() {
        }

        static /* synthetic */ boolean access$300() {
            return isNeedPermission();
        }

        public static boolean canDrawOverlay(Context context) {
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 23) {
                return Settings.canDrawOverlays(context);
            }
            if (i2 > 18) {
                return checkOp(context, 24);
            }
            return true;
        }

        @RequiresApi(api = 19)
        private static boolean checkOp(Context context, int i2) {
            AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService("appops");
            try {
                Class cls = Integer.TYPE;
                return ((Integer) AppOpsManager.class.getDeclaredMethod("checkOp", cls, cls, String.class).invoke(appOpsManager, Integer.valueOf(i2), Integer.valueOf(Binder.getCallingUid()), context.getPackageName())).intValue() == 0;
            } catch (Exception e2) {
                OKLog.e(SmallWindow.TAG, e2);
                return false;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:68:0x003d A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Type inference failed for: r1v0, types: [java.util.Properties] */
        /* JADX WARN: Type inference failed for: r3v0, types: [java.lang.Throwable, java.io.IOException] */
        /* JADX WARN: Type inference failed for: r3v2 */
        /* JADX WARN: Type inference failed for: r3v3 */
        /* JADX WARN: Type inference failed for: r3v4, types: [java.io.FileInputStream] */
        /* JADX WARN: Type inference failed for: r3v5 */
        /* JADX WARN: Type inference failed for: r3v6, types: [java.io.FileInputStream, java.io.InputStream] */
        /* JADX WARN: Type inference failed for: r3v7 */
        /* JADX WARN: Type inference failed for: r3v8 */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private static java.lang.String getMiuiProperty() {
            /*
                java.lang.String r0 = "SmallWindow"
                java.util.Properties r1 = new java.util.Properties
                r1.<init>()
                r2 = 0
                java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L21 java.io.IOException -> L23
                java.io.File r4 = new java.io.File     // Catch: java.lang.Throwable -> L21 java.io.IOException -> L23
                java.io.File r5 = android.os.Environment.getRootDirectory()     // Catch: java.lang.Throwable -> L21 java.io.IOException -> L23
                java.lang.String r6 = "build.prop"
                r4.<init>(r5, r6)     // Catch: java.lang.Throwable -> L21 java.io.IOException -> L23
                r3.<init>(r4)     // Catch: java.lang.Throwable -> L21 java.io.IOException -> L23
                r1.load(r3)     // Catch: java.io.IOException -> L1f java.lang.Throwable -> L39
                r3.close()     // Catch: java.io.IOException -> L2e
                goto L32
            L1f:
                r4 = move-exception
                goto L25
            L21:
                r1 = move-exception
                goto L3b
            L23:
                r4 = move-exception
                r3 = r2
            L25:
                com.jingdong.sdk.oklog.OKLog.e(r0, r4)     // Catch: java.lang.Throwable -> L39
                if (r3 == 0) goto L32
                r3.close()     // Catch: java.io.IOException -> L2e
                goto L32
            L2e:
                r3 = move-exception
                com.jingdong.sdk.oklog.OKLog.e(r0, r3)
            L32:
                java.lang.String r0 = "ro.miui.ui.version.name"
                java.lang.String r0 = r1.getProperty(r0, r2)
                return r0
            L39:
                r1 = move-exception
                r2 = r3
            L3b:
                if (r2 == 0) goto L45
                r2.close()     // Catch: java.io.IOException -> L41
                goto L45
            L41:
                r2 = move-exception
                com.jingdong.sdk.oklog.OKLog.e(r0, r2)
            L45:
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.widget.custom.liveplayer.videosmallwindow.SmallWindow.CheckPermission.getMiuiProperty():java.lang.String");
        }

        private static int getMiuiVersion() {
            String str;
            try {
                str = getMiuiProperty();
            } catch (Exception e2) {
                OKLog.e(SmallWindow.TAG, e2);
                str = null;
            }
            if (str != null) {
                try {
                    return Integer.parseInt(str.substring(1));
                } catch (Exception e3) {
                    OKLog.e(SmallWindow.TAG, e3);
                    return -1;
                }
            }
            return -1;
        }

        private static boolean isAboveHuaWei7() {
            String deviceBrand = BaseInfo.getDeviceBrand();
            return deviceBrand != null && "HUAWEI".equalsIgnoreCase(deviceBrand) && Build.VERSION.SDK_INT > 24;
        }

        private static boolean isAboveMiUi7() {
            return getMiuiVersion() >= 8;
        }

        private static boolean isNeedPermission() {
            return isAboveHuaWei7() || isAboveMiUi7();
        }
    }

    /* loaded from: classes12.dex */
    public class CusGradientDrawable extends GradientDrawable {
        public CusGradientDrawable() {
            SmallWindow.this = r1;
        }

        @Override // android.graphics.drawable.GradientDrawable, android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            super.draw(canvas);
        }
    }

    /* loaded from: classes12.dex */
    public class CusImagView extends AppCompatImageView {
        private int downX;
        private int downY;
        private long touchDownTime;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public CusImagView(Context context) {
            super(context);
            SmallWindow.this = r1;
            this.downX = 0;
            this.downY = 0;
            this.touchDownTime = 0L;
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent motionEvent) {
            int action = motionEvent.getAction();
            if (action != 0) {
                if (action == 1 && SmallWindow.isClick(motionEvent.getRawX(), this.downX, motionEvent.getRawY(), this.downY, System.currentTimeMillis(), this.touchDownTime)) {
                    performClick();
                }
            } else {
                this.downX = (int) motionEvent.getRawX();
                this.downY = (int) motionEvent.getRawY();
                this.touchDownTime = System.currentTimeMillis();
            }
            return true;
        }

        @Override // android.view.View
        public boolean performClick() {
            return super.performClick();
        }
    }

    /* loaded from: classes12.dex */
    public class Holder {
        public ImageView close;
        public WindowContent content;
        public RelativeLayout decorationContanier;
        public ImageView mute;
        public LinearLayout playingStatusContainer;
        public FrameLayout videoContent;
        public CardView weltCoverLayer;
        public SmallwindowWrapperLayout windowContentWrapper;

        public Holder() {
            SmallWindow.this = r1;
        }
    }

    /* loaded from: classes12.dex */
    public interface IOpenOrClose {
        void complete();
    }

    /* loaded from: classes12.dex */
    public class SmallwindowWrapperLayout extends FrameLayout {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SmallwindowWrapperLayout(@NonNull Context context) {
            super(context);
            SmallWindow.this = r1;
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent motionEvent) {
            if (SmallWindow.this.holder != null && SmallWindow.this.holder.content != null) {
                return SmallWindow.this.holder.content.dispatchTouchEvent(motionEvent);
            }
            return super.dispatchTouchEvent(motionEvent);
        }
    }

    /* loaded from: classes12.dex */
    public class WindowContent extends RelativeLayout {
        private int downX;
        private int downX2;
        private int downY;
        private int downY2;
        private boolean isMove;
        private long touchDownTime;

        public WindowContent(SmallWindow smallWindow, Context context) {
            this(context, null);
        }

        private void handleSmallwindowEvent(MotionEvent motionEvent) {
            int action = motionEvent.getAction();
            if (action == 0) {
                this.downX = (int) motionEvent.getRawX();
                this.downY = (int) motionEvent.getRawY();
                this.downX2 = (int) motionEvent.getRawX();
                this.downY2 = (int) motionEvent.getRawY();
                this.touchDownTime = System.currentTimeMillis();
            } else if (action == 1) {
                if (SmallWindow.isClick(motionEvent.getRawX(), this.downX2, motionEvent.getRawY(), this.downY2, System.currentTimeMillis(), this.touchDownTime)) {
                    return;
                }
                int i2 = SmallWindow.this.layoutParams.x;
                int i3 = SmallWindow.this.layoutParams.y;
                if (SmallWindow.this.mNeedAttach) {
                    int width = getWidth() / 2;
                    int i4 = SmallWindow.this.mRootMeasuredWidth / 2;
                    int dip2px = i2 - DPIUtil.dip2px(SmallWindow.this.leftRightSafeDistance);
                    int width2 = ((SmallWindow.this.mRootMeasuredWidth - i2) - getWidth()) - DPIUtil.dip2px(SmallWindow.this.leftRightSafeDistance);
                    int dip2px2 = DPIUtil.dip2px(SmallWindow.this.topSafeDistance);
                    int dip2px3 = i3 < dip2px2 ? dip2px2 - i3 : DPIUtil.dip2px(SmallWindow.this.bottomSafeDistance) - ((SmallWindow.this.mRootMeasuredHeight - i3) - getHeight());
                    if (((getWidth() + i2) - width >= SmallWindow.this.mRootMeasuredWidth || i2 + width <= 0) && dip2px3 < 0) {
                        if (width + i2 <= 0) {
                            SmallWindow.this.packUpSmallWindow(1, i2, (int) ((getWidth() * (1.0f - SmallWindow.DEFAULT_ATTACH_SIDE_PERCENT)) + i2));
                        } else {
                            SmallWindow.this.packUpSmallWindow(2, i2, (int) ((getWidth() * (1.0f - SmallWindow.DEFAULT_ATTACH_SIDE_PERCENT)) - ((getWidth() + i2) - SmallWindow.this.mRootMeasuredWidth)));
                        }
                    } else if (width + i2 <= i4) {
                        SmallWindow.this.attachToSide(1, i2, dip2px, i3, dip2px3);
                    } else {
                        SmallWindow.this.attachToSide(2, i2, width2, i3, dip2px3);
                    }
                }
            } else if (action != 2) {
            } else {
                int rawX = (int) motionEvent.getRawX();
                int rawY = (int) motionEvent.getRawY();
                SmallWindow.this.layoutParams.x += rawX - this.downX;
                SmallWindow.this.layoutParams.y += rawY - this.downY;
                String str = "X:" + SmallWindow.this.layoutParams.x + "--Y:" + SmallWindow.this.layoutParams.y;
                String str2 = "windowWidth:" + SmallWindow.this.layoutParams.width;
                if (SmallWindow.this.holder != null) {
                    if (SmallWindow.this.mNeedOpenHeightChangeAnimation) {
                        SmallWindow.this.changeSmallwindowToFixedSize();
                    }
                    SmallWindow.this.wm.updateViewLayout(SmallWindow.this.holder.windowContentWrapper, SmallWindow.this.layoutParams);
                    SmallWindow.this.changeWeltCoverLayerAlpha();
                }
                this.downX = rawX;
                this.downY = rawY;
            }
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent motionEvent) {
            if (SmallWindow.this.holder != null && SmallWindow.this.holder.windowContentWrapper != null) {
                if (!isEnabled()) {
                    return super.dispatchTouchEvent(motionEvent);
                }
                handleSmallwindowEvent(motionEvent);
                return super.dispatchTouchEvent(motionEvent);
            }
            return super.dispatchTouchEvent(motionEvent);
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent motionEvent) {
            int action = motionEvent.getAction();
            if (action == 0) {
                this.isMove = false;
            } else if (action != 1) {
                if (action == 2) {
                    this.isMove = true;
                }
            } else if ((!this.isMove || SmallWindow.isClick(motionEvent.getRawX(), this.downX2, motionEvent.getRawY(), this.downY2, System.currentTimeMillis(), this.touchDownTime)) && SmallWindow.this.iPlayerChange != null && SmallWindow.this.holder != null) {
                if (SmallWindow.this.mAttachSideAnimatorSet != null) {
                    SmallWindow.this.mAttachSideAnimatorSet.cancel();
                }
                if (SmallWindow.this.mpackUpSmallWindowAnimatorSet != null) {
                    SmallWindow.this.mpackUpSmallWindowAnimatorSet.cancel();
                }
                performClick();
                SmallWindow.this.iPlayerChange.onSmallClick();
            }
            return super.onTouchEvent(motionEvent);
        }

        @Override // android.view.View
        public boolean performClick() {
            return super.performClick();
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public WindowContent(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            SmallWindow.this = r3;
            this.downX = 0;
            this.downY = 0;
            this.isMove = false;
            this.downX2 = 0;
            this.downY2 = 0;
            this.touchDownTime = 0L;
            r3.mRootMeasuredWidth = DPIUtil.getWidth(context);
            r3.mRootMeasuredHeight = DPIUtil.getHeight(context);
        }
    }

    public SmallWindow(Context context, ISmallWindowManager.IPlayerChange iPlayerChange, boolean z, View.OnClickListener onClickListener) {
        this(context, iPlayerChange, z, onClickListener, false, 0.0f, 2, DEFAULT_BORDER_COLOR);
    }

    public void attachToSide(final int i2, final int i3, int i4, final int i5, int i6) {
        ValueAnimator duration;
        this.mAttachSideAnimatorSet = new AnimatorSet();
        ValueAnimator duration2 = ValueAnimator.ofInt(i4).setDuration(500L);
        duration2.setInterpolator(new AccelerateDecelerateInterpolator());
        duration2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.common.widget.custom.liveplayer.videosmallwindow.SmallWindow.7
            {
                SmallWindow.this = this;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                if (i2 == 1) {
                    SmallWindow.this.layoutParams.x = i3 - intValue;
                } else {
                    SmallWindow.this.layoutParams.x = i3 + intValue;
                }
                String str = "attachX:" + SmallWindow.this.layoutParams.x;
                if (SmallWindow.this.holder != null) {
                    try {
                        SmallWindow.this.wm.updateViewLayout(SmallWindow.this.holder.windowContentWrapper, SmallWindow.this.layoutParams);
                        SmallWindow.this.changeWeltCoverLayerAlpha();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        });
        duration2.addListener(new AnimatorListenerAdapter() { // from class: com.jingdong.common.widget.custom.liveplayer.videosmallwindow.SmallWindow.8
            {
                SmallWindow.this = this;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                SmallWindow.this.hideArrowImageAndWeltCoverLayer();
                super.onAnimationEnd(animator);
            }
        });
        WindowManager.LayoutParams layoutParams = this.layoutParams;
        int i7 = layoutParams.x;
        ValueAnimator valueAnimator = null;
        if (i7 < 0 || i7 + layoutParams.width > this.mRootMeasuredWidth) {
            final int i8 = this.contentLayoutParams.height;
            duration = ValueAnimator.ofInt(this.mOriginalContentHeightSize - i8).setDuration(500L);
            duration.setInterpolator(new AccelerateDecelerateInterpolator());
            duration.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.common.widget.custom.liveplayer.videosmallwindow.SmallWindow.9
                {
                    SmallWindow.this = this;
                }

                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                    int intValue = ((Integer) valueAnimator2.getAnimatedValue()).intValue();
                    if (SmallWindow.this.contentLayoutParams != null) {
                        String str = "contentLayoutHeight:" + SmallWindow.this.contentLayoutParams.height;
                        SmallWindow.this.contentLayoutParams.height = i8 + intValue;
                    }
                }
            });
        } else {
            duration = null;
        }
        if (i6 > 0) {
            final int dip2px = DPIUtil.dip2px(this.topSafeDistance);
            FrameLayout.LayoutParams layoutParams2 = this.contentLayoutParams;
            valueAnimator = ValueAnimator.ofInt(i6 + ((layoutParams2 == null || i5 <= dip2px) ? 0 : this.mOriginalContentHeightSize - layoutParams2.height)).setDuration(500L);
            valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.common.widget.custom.liveplayer.videosmallwindow.SmallWindow.10
                {
                    SmallWindow.this = this;
                }

                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                    if (i5 < dip2px) {
                        SmallWindow.this.layoutParams.y = i5 + ((Integer) valueAnimator2.getAnimatedValue()).intValue();
                    } else {
                        SmallWindow.this.layoutParams.y = i5 - ((Integer) valueAnimator2.getAnimatedValue()).intValue();
                    }
                    if (SmallWindow.this.holder != null) {
                        try {
                            SmallWindow.this.wm.updateViewLayout(SmallWindow.this.holder.windowContentWrapper, SmallWindow.this.layoutParams);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            });
        }
        AnimatorSet.Builder play = this.mAttachSideAnimatorSet.play(duration2);
        if (duration != null && this.mNeedOpenHeightChangeAnimation) {
            play.with(duration);
        }
        if (valueAnimator != null) {
            play.with(valueAnimator);
        }
        this.mAttachSideAnimatorSet.start();
    }

    private void changeAttachLayerCoverArrowWitdh(int i2) {
        CardView cardView;
        Holder holder = this.holder;
        if (holder == null || (cardView = holder.weltCoverLayer) == null) {
            return;
        }
        CusImagView cusImagView = (CusImagView) cardView.findViewById(R.id.live_smallwindow_img_left_arrow);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) cusImagView.getLayoutParams();
        float f2 = i2;
        layoutParams.width = (int) (DEFAULT_ATTACH_SIDE_PERCENT * f2);
        cusImagView.setLayoutParams(layoutParams);
        CusImagView cusImagView2 = (CusImagView) this.holder.weltCoverLayer.findViewById(R.id.live_smallwindow_img_right_arrow);
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) cusImagView2.getLayoutParams();
        layoutParams2.width = (int) (f2 * DEFAULT_ATTACH_SIDE_PERCENT);
        cusImagView2.setLayoutParams(layoutParams2);
    }

    public void changeSmallwindowToFixedSize() {
        Holder holder;
        float f2;
        WindowManager.LayoutParams layoutParams = this.layoutParams;
        if (layoutParams == null || this.contentLayoutParams == null || (holder = this.holder) == null || holder.content == null) {
            return;
        }
        int i2 = layoutParams.width;
        float f3 = i2 * (1.0f - DEFAULT_ATTACH_SIDE_PERCENT);
        int i3 = layoutParams.x;
        if (i3 < 0) {
            f2 = -i3;
        } else {
            int i4 = i3 + i2;
            int i5 = this.mRootMeasuredWidth;
            if (i4 <= i5) {
                return;
            }
            f2 = i2 - (i5 - i3);
        }
        float f4 = f2 / f3;
        if (f4 > 1.0f || f4 < 0.0f) {
            return;
        }
        String str = "sizePercent:" + f4;
        this.contentLayoutParams.height = (int) (this.mOriginalContentHeightSize - (f4 * this.mNeedChangeHeightSize));
    }

    public void changeWeltCoverLayerAlpha() {
        Holder holder;
        CardView cardView;
        WindowManager.LayoutParams layoutParams = this.layoutParams;
        if (layoutParams == null || (holder = this.holder) == null || (cardView = holder.weltCoverLayer) == null) {
            return;
        }
        int i2 = layoutParams.width;
        float f2 = i2 * (1.0f - DEFAULT_ATTACH_SIDE_PERCENT);
        int i3 = layoutParams.x;
        if (i3 < 0) {
            cardView.setAlpha(Math.min((-i3) / f2, 1.0f));
        } else {
            if (i3 + i2 > this.mRootMeasuredWidth) {
                cardView.setAlpha(Math.min((i2 - (r6 - i3)) / f2, 1.0f));
            } else {
                cardView.setAlpha(0.0f);
            }
        }
        String str = this.holder.weltCoverLayer.getAlpha() + "";
    }

    private Drawable createBorder(int i2, float f2, String str) {
        if (this.showBorder) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(0);
            gradientDrawable.setStroke(DPIUtil.dip2px(i2 == 0 ? 2.0f : Math.abs(i2)), parseColor(str));
            gradientDrawable.setColor(0);
            gradientDrawable.setCornerRadius(DPIUtil.dip2px(f2));
            return gradientDrawable;
        }
        return null;
    }

    private Drawable createPlayingStatusDrawable() {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setColors(new int[]{Color.parseColor("#F54958"), Color.parseColor("#FA3725")});
        gradientDrawable.setCornerRadius(DPIUtil.dip2px(70.0f));
        return gradientDrawable;
    }

    public void exposeFromRight() {
        Holder holder;
        CardView cardView;
        CusImagView cusImagView;
        if (this.layoutParams == null || (holder = this.holder) == null || (cardView = holder.weltCoverLayer) == null || (cusImagView = (CusImagView) cardView.findViewById(R.id.live_smallwindow_img_left_arrow)) == null) {
            return;
        }
        cusImagView.setVisibility(8);
        int i2 = this.mRootMeasuredWidth;
        WindowManager.LayoutParams layoutParams = this.layoutParams;
        attachToSide(2, this.layoutParams.x, ((i2 - layoutParams.x) - layoutParams.width) - DPIUtil.dip2px(this.leftRightSafeDistance), 0, 0);
    }

    public void exppseFromLeft() {
        Holder holder;
        CardView cardView;
        CusImagView cusImagView;
        if (this.layoutParams == null || (holder = this.holder) == null || (cardView = holder.weltCoverLayer) == null || (cusImagView = (CusImagView) cardView.findViewById(R.id.live_smallwindow_img_right_arrow)) == null) {
            return;
        }
        cusImagView.setVisibility(8);
        attachToSide(1, this.layoutParams.x, this.layoutParams.x - DPIUtil.dip2px(this.leftRightSafeDistance), 0, 0);
    }

    public void hideArrowImageAndWeltCoverLayer() {
        CardView cardView;
        Holder holder = this.holder;
        if (holder == null || (cardView = holder.weltCoverLayer) == null) {
            return;
        }
        ((CusImagView) cardView.findViewById(R.id.live_smallwindow_img_left_arrow)).setVisibility(8);
        ((CusImagView) this.holder.weltCoverLayer.findViewById(R.id.live_smallwindow_img_right_arrow)).setVisibility(8);
        if (this.holder.weltCoverLayer.getAlpha() != 0.0f) {
            this.holder.weltCoverLayer.setAlpha(0.0f);
        }
    }

    private void init(Context context) {
        this.mContext = context;
        WindowManager windowManager = (WindowManager) context.getApplicationContext().getSystemService("window");
        this.wm = windowManager;
        windowManager.getDefaultDisplay().getSize(this.metrics);
        this.layoutParams = new WindowManager.LayoutParams();
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        this.contentLayoutParams = layoutParams;
        layoutParams.gravity = 51;
        int i2 = context.getApplicationContext().getResources().getConfiguration().orientation;
        if (this.isLandScape) {
            DEFAULT_ATTACH_SIDE_PERCENT = 0.2f;
            if (OKLog.D) {
                OKLog.d("MMM", "LIVE_SCREEN_ORIENTATION_LANDSCAPE " + i2);
            }
            this.contentLayoutParams.width = DPIUtil.dip2px(160.0f);
            this.contentLayoutParams.height = DPIUtil.dip2px(90.0f);
            WindowManager.LayoutParams layoutParams2 = this.layoutParams;
            layoutParams2.width = this.contentLayoutParams.width;
            layoutParams2.height = Math.max(DPIUtil.dip2px(90.0f), DPIUtil.dip2px(DEFAULT_ATTACH_SIDE_HEIGHT_SIZE));
        } else {
            DEFAULT_ATTACH_SIDE_PERCENT = 0.3f;
            if (OKLog.D) {
                OKLog.d("MMM", "LIVE_SCREEN_ORIENTATION_PORTRAIT " + i2);
            }
            this.contentLayoutParams.width = DPIUtil.dip2px(102.0f);
            this.contentLayoutParams.height = DPIUtil.dip2px(180.0f);
            WindowManager.LayoutParams layoutParams3 = this.layoutParams;
            layoutParams3.width = this.contentLayoutParams.width;
            layoutParams3.height = Math.max(DPIUtil.dip2px(180.0f), DPIUtil.dip2px(DEFAULT_ATTACH_SIDE_HEIGHT_SIZE));
        }
        this.mOriginalContentHeightSize = this.layoutParams.height;
        this.mNeedChangeHeightSize = this.contentLayoutParams.height - DPIUtil.dip2px(DEFAULT_ATTACH_SIDE_HEIGHT_SIZE);
        WindowManager.LayoutParams layoutParams4 = this.layoutParams;
        layoutParams4.gravity = 51;
        layoutParams4.x = (this.metrics.x - this.contentLayoutParams.width) - DPIUtil.dip2px(this.leftRightSafeDistance);
        this.layoutParams.y = (this.metrics.y - this.contentLayoutParams.height) - DPIUtil.dip2px(this.bottomSafeDistance);
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 26) {
            this.layoutParams.type = R2.attr.textIsDisplayable;
        } else if (i3 > 24) {
            this.layoutParams.type = 2002;
        } else if (i3 >= 19) {
            this.layoutParams.type = 2005;
        } else {
            this.layoutParams.type = 2003;
        }
        WindowManager.LayoutParams layoutParams5 = this.layoutParams;
        this.layoutParamsTypeWithInit = layoutParams5.type;
        layoutParams5.flags = 16777768;
        setViews(context);
    }

    public static boolean isClick(float f2, float f3, float f4, float f5, long j2, long j3) {
        return Math.abs(f2 - f3) < 20.0f && Math.abs(f4 - f5) < 20.0f && j2 - j3 < 500;
    }

    private boolean isMuted() {
        return ((AudioManager) JdSdk.getInstance().getApplicationContext().getSystemService("audio")).getStreamVolume(3) == 0;
    }

    public void packUpSmallWindow(final int i2, final int i3, int i4) {
        this.mpackUpSmallWindowAnimatorSet = new AnimatorSet();
        ValueAnimator duration = ValueAnimator.ofInt(i4).setDuration(500L);
        duration.setInterpolator(new AccelerateDecelerateInterpolator());
        duration.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.common.widget.custom.liveplayer.videosmallwindow.SmallWindow.11
            {
                SmallWindow.this = this;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (i2 == 1) {
                    SmallWindow.this.layoutParams.x = i3 - ((Integer) valueAnimator.getAnimatedValue()).intValue();
                } else {
                    SmallWindow.this.layoutParams.x = i3 + ((Integer) valueAnimator.getAnimatedValue()).intValue();
                }
                String str = "pack-X\uff1a" + SmallWindow.this.layoutParams.x;
                if (SmallWindow.this.holder != null) {
                    try {
                        SmallWindow.this.wm.updateViewLayout(SmallWindow.this.holder.windowContentWrapper, SmallWindow.this.layoutParams);
                        SmallWindow.this.changeWeltCoverLayerAlpha();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        });
        duration.addListener(new AnimatorListenerAdapter() { // from class: com.jingdong.common.widget.custom.liveplayer.videosmallwindow.SmallWindow.12
            {
                SmallWindow.this = this;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                SmallWindow.this.showArrowImage();
                super.onAnimationEnd(animator);
            }
        });
        final int i5 = this.contentLayoutParams.height;
        ValueAnimator duration2 = ValueAnimator.ofInt(i5 - DPIUtil.dip2px(DEFAULT_ATTACH_SIDE_HEIGHT_SIZE)).setDuration(500L);
        duration2.setInterpolator(new AccelerateDecelerateInterpolator());
        duration2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.common.widget.custom.liveplayer.videosmallwindow.SmallWindow.13
            {
                SmallWindow.this = this;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                if (SmallWindow.this.contentLayoutParams != null) {
                    String str = "contentLayoutHeight:" + SmallWindow.this.contentLayoutParams.height;
                    SmallWindow.this.contentLayoutParams.height = i5 - intValue;
                }
            }
        });
        AnimatorSet.Builder play = this.mpackUpSmallWindowAnimatorSet.play(duration);
        if (duration2 != null && this.mNeedOpenHeightChangeAnimation) {
            play.with(duration2);
        }
        this.mpackUpSmallWindowAnimatorSet.start();
    }

    private int parseColor(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                str = DEFAULT_BORDER_COLOR;
            }
            return Color.parseColor(str);
        } catch (Exception unused) {
            return Color.parseColor(DEFAULT_BORDER_COLOR);
        }
    }

    private void refreshVoiceIcon(ImageView imageView) {
        imageView.setImageResource(this.isVoiceOn ? R.drawable.live_player_voice_on : R.drawable.live_player_mute);
    }

    private void setViews(Context context) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        Holder holder = new Holder();
        this.holder = holder;
        holder.windowContentWrapper = new SmallwindowWrapperLayout(context);
        this.holder.content = new WindowContent(this, context);
        this.holder.videoContent = new FrameLayout(context);
        if (this.borderRadius > 0.0f && Build.VERSION.SDK_INT >= 21) {
            try {
                if (Log.D) {
                    Log.e("Arthur", "borderRadius = " + DPIUtil.dip2px(context, this.borderRadius - (this.borderWidth - 1)));
                }
                this.holder.videoContent.setOutlineProvider(new TextureVideoViewOutlineProvider(DPIUtil.dip2px(context, this.borderRadius)));
                this.holder.videoContent.setClipToOutline(true);
            } catch (Exception unused) {
                if (Log.D) {
                    Log.e(PlayerReportInfoEntity.PAGE_ID, "FloatingWindow open() exception when add corner on video view");
                }
            }
        }
        ImageView imageView = new ImageView(context);
        imageView.setId(R.id.live_smallwindow_img_close);
        imageView.setImageResource(R.drawable.live_smallwinow_player_close);
        int i7 = 22;
        LiveUIConfigBean liveUIConfigBean = this.uiConfigBean;
        int i8 = 6;
        if (liveUIConfigBean != null) {
            i7 = liveUIConfigBean.getCloseSize();
            i2 = this.uiConfigBean.getCloseIconSizeTopMargin();
            i3 = this.uiConfigBean.getCloseIconSizeRightMargin();
        } else {
            i2 = 6;
            i3 = 6;
        }
        float f2 = i7;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(DPIUtil.dip2px(f2), DPIUtil.dip2px(f2));
        layoutParams.addRule(11);
        layoutParams.topMargin = DPIUtil.dip2px(i2);
        layoutParams.rightMargin = DPIUtil.dip2px(i3);
        imageView.setLayoutParams(layoutParams);
        imageView.setOnClickListener(this.onCloseClick);
        ImageView imageView2 = new ImageView(context);
        imageView2.setId(R.id.live_smallwindow_img_voice_mute);
        imageView2.setImageResource(R.drawable.live_player_mute);
        int i9 = 26;
        LiveUIConfigBean liveUIConfigBean2 = this.uiConfigBean;
        if (liveUIConfigBean2 != null) {
            i9 = liveUIConfigBean2.getMuteSize();
            i4 = this.uiConfigBean.getMuteIconSizeRightMargin();
            i5 = this.uiConfigBean.getMuteIconSizeBottomMargin();
        } else {
            i4 = 6;
            i5 = 6;
        }
        float f3 = i9;
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(DPIUtil.dip2px(f3), DPIUtil.dip2px(f3));
        layoutParams2.addRule(11);
        layoutParams2.addRule(12);
        layoutParams2.rightMargin = DPIUtil.dip2px(i4);
        layoutParams2.bottomMargin = DPIUtil.dip2px(i5);
        imageView2.setLayoutParams(layoutParams2);
        imageView2.setOnClickListener(this.onMuteClicked);
        this.holder.playingStatusContainer = new LinearLayout(context);
        this.holder.playingStatusContainer.setGravity(16);
        this.holder.playingStatusContainer.setOrientation(0);
        LiveUIConfigBean liveUIConfigBean3 = this.uiConfigBean;
        if (liveUIConfigBean3 != null) {
            this.mPlayStatusIconSizeHeight = liveUIConfigBean3.getPlayStatusIconSizeHeight();
            i8 = this.uiConfigBean.getPlayStatusIconTopMargin();
            i6 = this.uiConfigBean.getPlayStatusIconLeftMargin();
        } else {
            i6 = 6;
        }
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.addRule(9);
        layoutParams3.addRule(10);
        layoutParams3.topMargin = DPIUtil.dip2px(i8);
        layoutParams3.leftMargin = DPIUtil.dip2px(i6);
        this.holder.playingStatusContainer.setLayoutParams(layoutParams3);
        this.holder.playingStatusContainer.setGravity(17);
        this.holder.playingStatusContainer.setBackground(createPlayingStatusDrawable());
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(context);
        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(DPIUtil.dip2px(9.0f), DPIUtil.dip2px(9.0f));
        layoutParams4.leftMargin = DPIUtil.dip2px(4.0f);
        layoutParams4.gravity = 16;
        simpleDraweeView.setLayoutParams(layoutParams4);
        simpleDraweeView.setController(Fresco.newDraweeControllerBuilder().setUri(new Uri.Builder().scheme(UriUtil.LOCAL_RESOURCE_SCHEME).path(String.valueOf(R.drawable.live_tag)).build()).setAutoPlayAnimations(true).build());
        simpleDraweeView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        simpleDraweeView.setLayoutParams(layoutParams4);
        TextView textView = new TextView(context);
        textView.setText("\u76f4\u64ad\u4e2d");
        textView.setIncludeFontPadding(false);
        textView.setId(R.id.live_smallwindow_tv_status_content);
        textView.setTextSize(0, DPIUtil.dip2px((float) (this.mPlayStatusIconSizeHeight - 3)));
        textView.setTextColor(Color.parseColor("#FFFFFF"));
        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams5.gravity = 16;
        layoutParams5.bottomMargin = DPIUtil.dip2px(1.5f);
        layoutParams5.topMargin = DPIUtil.dip2px(1.5f);
        layoutParams5.leftMargin = DPIUtil.dip2px(4.0f);
        layoutParams5.rightMargin = DPIUtil.dip2px(4.0f);
        textView.setLayoutParams(layoutParams5);
        this.holder.playingStatusContainer.addView(textView);
        this.holder.decorationContanier = new RelativeLayout(context);
        this.holder.decorationContanier.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        if (this.showBorder) {
            this.holder.decorationContanier.setBackground(createBorder(this.borderWidth, this.borderRadius, this.borderColor));
        }
        if (Log.D) {
            Log.d(TAG, "borderRadius = " + this.borderRadius + ", borderWidth = " + this.borderWidth);
        }
        this.holder.weltCoverLayer = new CardView(context);
        this.holder.weltCoverLayer.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        this.holder.weltCoverLayer.setRadius(DPIUtil.dip2px(this.borderRadius));
        this.holder.weltCoverLayer.setCardElevation(0.0f);
        this.holder.weltCoverLayer.setMaxCardElevation(0.0f);
        this.holder.weltCoverLayer.setAlpha(0.0f);
        this.holder.weltCoverLayer.setCardBackgroundColor(0);
        SimpleDraweeView simpleDraweeView2 = new SimpleDraweeView(context);
        simpleDraweeView2.setId(R.id.live_smallwindow_img_blur);
        simpleDraweeView2.setScaleType(ImageView.ScaleType.CENTER_CROP);
        simpleDraweeView2.setBackground(new ColorDrawable(Color.parseColor("#B2B2B2")));
        FrameLayout.LayoutParams layoutParams6 = new FrameLayout.LayoutParams(-1, -1);
        CusImagView cusImagView = new CusImagView(context);
        cusImagView.setId(R.id.live_smallwindow_img_left_arrow);
        cusImagView.setImageResource(R.drawable.live_smallwindow_left_arrow);
        cusImagView.setScaleType(ImageView.ScaleType.CENTER);
        cusImagView.setVisibility(8);
        FrameLayout.LayoutParams layoutParams7 = new FrameLayout.LayoutParams((int) (this.layoutParams.width * DEFAULT_ATTACH_SIDE_PERCENT), -1);
        layoutParams7.gravity = 19;
        cusImagView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.custom.liveplayer.videosmallwindow.SmallWindow.4
            {
                SmallWindow.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SmallWindow.this.exposeFromRight();
            }
        });
        CusImagView cusImagView2 = new CusImagView(context);
        cusImagView2.setId(R.id.live_smallwindow_img_right_arrow);
        cusImagView2.setScaleType(ImageView.ScaleType.CENTER);
        cusImagView2.setVisibility(8);
        cusImagView2.setImageResource(R.drawable.live_smallwindow_right_arrow);
        FrameLayout.LayoutParams layoutParams8 = new FrameLayout.LayoutParams((int) (this.layoutParams.width * DEFAULT_ATTACH_SIDE_PERCENT), -1);
        layoutParams8.gravity = 21;
        cusImagView2.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.custom.liveplayer.videosmallwindow.SmallWindow.5
            {
                SmallWindow.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SmallWindow.this.exppseFromLeft();
            }
        });
        this.holder.weltCoverLayer.addView(simpleDraweeView2, layoutParams6);
        this.holder.weltCoverLayer.addView(cusImagView, layoutParams7);
        this.holder.weltCoverLayer.addView(cusImagView2, layoutParams8);
        Holder holder2 = this.holder;
        holder2.close = imageView;
        holder2.mute = imageView2;
        holder2.content.addView(holder2.videoContent);
        Holder holder3 = this.holder;
        holder3.content.addView(holder3.close);
        Holder holder4 = this.holder;
        holder4.content.addView(holder4.mute);
        Holder holder5 = this.holder;
        holder5.content.addView(holder5.playingStatusContainer);
        Holder holder6 = this.holder;
        holder6.content.addView(holder6.decorationContanier);
        Holder holder7 = this.holder;
        holder7.content.addView(holder7.weltCoverLayer);
        Holder holder8 = this.holder;
        holder8.windowContentWrapper.addView(holder8.content, this.contentLayoutParams);
    }

    public void showArrowImage() {
        CardView cardView;
        Holder holder = this.holder;
        if (holder == null || (cardView = holder.weltCoverLayer) == null) {
            return;
        }
        ((CusImagView) cardView.findViewById(R.id.live_smallwindow_img_left_arrow)).setVisibility(0);
        ((CusImagView) this.holder.weltCoverLayer.findViewById(R.id.live_smallwindow_img_right_arrow)).setVisibility(0);
    }

    public void updateMuteBtnVisibility(LivePlayer.PlayerStatus playerStatus) {
        if (playerStatus == LivePlayer.PlayerStatus.STATE_PLAYING) {
            ImageView imageView = this.holder.mute;
            if (imageView == null || !this.mNeedShowMuteIcon) {
                return;
            }
            imageView.setVisibility(0);
            return;
        }
        ImageView imageView2 = this.holder.mute;
        if (imageView2 != null) {
            imageView2.setVisibility(8);
        }
    }

    public void addSmallWindowUICallBack(SmallWindowUICallback smallWindowUICallback) {
        if (this.mSmallWindowUICallBacks == null) {
            this.mSmallWindowUICallBacks = new HashSet();
        }
        try {
            this.mSmallWindowUICallBacks.add(smallWindowUICallback);
        } catch (Exception e2) {
            e2.printStackTrace();
            Log.e(TAG, "add failed");
        }
    }

    public void clearSmallWindowUICallBacks() {
        Set<SmallWindowUICallback> set = this.mSmallWindowUICallBacks;
        if (set != null) {
            set.clear();
        }
    }

    public void close(IOpenOrClose iOpenOrClose, boolean z) {
        close(iOpenOrClose);
        SmallWindowUICallback smallWindowUICallback = this.mSmallWindowUICallBack;
        if (smallWindowUICallback != null) {
            smallWindowUICallback.onClose(z);
        }
        Set<SmallWindowUICallback> set = this.mSmallWindowUICallBacks;
        if (set != null) {
            Iterator<SmallWindowUICallback> it = set.iterator();
            while (it.hasNext()) {
                it.next().onClose(z);
            }
        }
        clearSmallWindowUICallBacks();
    }

    public void exposeSmallWindow() {
        WindowManager.LayoutParams layoutParams = this.layoutParams;
        if (layoutParams == null) {
            return;
        }
        int i2 = layoutParams.x;
        if (i2 < 0) {
            exppseFromLeft();
        }
        if (i2 + this.layoutParams.width > this.mRootMeasuredWidth) {
            exposeFromRight();
        }
    }

    public Holder getUIHoder() {
        Holder holder = this.holder;
        if (holder == null) {
            return null;
        }
        return holder;
    }

    public void hideSmallWindow() {
        int i2;
        FrameLayout.LayoutParams layoutParams = this.contentLayoutParams;
        if (layoutParams == null) {
            return;
        }
        int i3 = layoutParams.width;
        int i4 = this.layoutParams.x;
        int i5 = i3 / 2;
        int i6 = this.mRootMeasuredWidth;
        int i7 = i6 / 2;
        if (i4 < 0 || (i2 = i4 + i3) > i6) {
            return;
        }
        if (i5 + i4 <= i7) {
            packUpSmallWindow(1, i4, (int) ((i3 * (1.0f - DEFAULT_ATTACH_SIDE_PERCENT)) + i4));
        } else {
            packUpSmallWindow(2, i4, (int) ((i3 * (1.0f - DEFAULT_ATTACH_SIDE_PERCENT)) - (i2 - i6)));
        }
    }

    public boolean isInPostponeSmallWindowStatus() {
        return this.mIsInPostponeSmallWindowStatus;
    }

    public boolean isLandScape() {
        return this.isLandScape;
    }

    public boolean isSmallWindowShow() {
        SmallwindowWrapperLayout smallwindowWrapperLayout;
        Holder holder = this.holder;
        return (holder == null || (smallwindowWrapperLayout = holder.windowContentWrapper) == null || smallwindowWrapperLayout.getParent() == null) ? false : true;
    }

    public void notifyDecorationChange() {
        Holder holder;
        WindowContent windowContent;
        IDecorator iDecorator = this.mIDecorator;
        if (iDecorator == null || (holder = this.holder) == null || (windowContent = holder.content) == null) {
            return;
        }
        iDecorator.setDecorateView(holder.decorationContanier, windowContent);
    }

    public void open(View view, IOpenOrClose iOpenOrClose, boolean z) {
        ImageView imageView;
        open(view, iOpenOrClose);
        if (this.isVoiceOn != z) {
            this.isVoiceOn = z;
            Holder holder = this.holder;
            if (holder == null || (imageView = holder.mute) == null) {
                return;
            }
            refreshVoiceIcon(imageView);
        }
    }

    public void postoneDisplaySmallwindow() {
        Holder holder = this.holder;
        if (holder == null) {
            return;
        }
        this.mIsInPostponeSmallWindowStatus = true;
        WindowManager.LayoutParams layoutParams = this.layoutParams;
        if (layoutParams != null) {
            layoutParams.flags |= 16;
        }
        SmallwindowWrapperLayout smallwindowWrapperLayout = holder.windowContentWrapper;
        if (smallwindowWrapperLayout != null) {
            smallwindowWrapperLayout.setAlpha(0.0f);
        }
    }

    public void removeSmallWindowUICallBack(SmallWindowUICallback smallWindowUICallback) {
        Set<SmallWindowUICallback> set = this.mSmallWindowUICallBacks;
        if (set != null) {
            try {
                Iterator<SmallWindowUICallback> it = set.iterator();
                while (it.hasNext()) {
                    if (it.next().equals(smallWindowUICallback)) {
                        it.remove();
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                Log.e(TAG, "remove failed");
            }
        }
    }

    public void setBlurImageURL(String str, boolean z) {
        this.mBlurImageUrl = str;
    }

    public void setBottomSafeDistance(int i2) {
        this.bottomSafeDistance = i2;
    }

    public void setCloseIconMargin(int i2, int i3) {
        ImageView imageView;
        Holder holder = this.holder;
        if (holder == null || (imageView = holder.close) == null) {
            return;
        }
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
        layoutParams.rightMargin = DPIUtil.dip2px(i3);
        layoutParams.topMargin = DPIUtil.dip2px(i2);
        this.holder.close.setLayoutParams(layoutParams);
    }

    public void setDecoration(IDecorator iDecorator) {
        this.mIDecorator = iDecorator;
    }

    public void setLeftRightSafeDistance(int i2) {
        this.leftRightSafeDistance = i2;
    }

    public void setPlayStatusIconMargin(int i2, int i3) {
        LinearLayout linearLayout;
        Holder holder = this.holder;
        if (holder == null || (linearLayout = holder.playingStatusContainer) == null) {
            return;
        }
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) linearLayout.getLayoutParams();
        layoutParams.leftMargin = DPIUtil.dip2px(i3);
        layoutParams.topMargin = DPIUtil.dip2px(i2);
        this.holder.playingStatusContainer.setLayoutParams(layoutParams);
    }

    public void setPos(int i2, int i3) {
        this.windowPosX = i2;
        this.windowPosY = i3;
    }

    public void setShowMuteIcon(boolean z) {
        ImageView imageView;
        this.mNeedShowMuteIcon = z;
        Holder holder = this.holder;
        if (holder == null || (imageView = holder.mute) == null) {
            return;
        }
        imageView.setVisibility(z ? 0 : 8);
    }

    public void setSize(Point point2) {
        int i2;
        int i3;
        if (point2 != null && (i2 = point2.x) > 0 && (i3 = point2.y) > 0) {
            FrameLayout.LayoutParams layoutParams = this.contentLayoutParams;
            layoutParams.width = i2;
            layoutParams.height = i3;
            this.mOriginalContentHeightSize = i3;
            WindowManager.LayoutParams layoutParams2 = this.layoutParams;
            layoutParams2.width = i2;
            layoutParams2.height = Math.max(i3, DPIUtil.dip2px(DEFAULT_ATTACH_SIDE_HEIGHT_SIZE));
            this.mNeedChangeHeightSize = this.contentLayoutParams.height - DPIUtil.dip2px(DEFAULT_ATTACH_SIDE_HEIGHT_SIZE);
            changeAttachLayerCoverArrowWitdh(this.layoutParams.width);
        }
    }

    public void setSmallWindowPlayStatus(int i2) {
        LinearLayout linearLayout;
        TextView textView;
        Holder holder = this.holder;
        if (holder == null || (linearLayout = holder.playingStatusContainer) == null || (textView = (TextView) linearLayout.findViewById(R.id.live_smallwindow_tv_status_content)) == null) {
            return;
        }
        if (i2 == 1) {
            textView.setText("\u76f4\u64ad\u4e2d");
        } else if (i2 == 3) {
            textView.setText("\u76f4\u64ad\u56de\u653e");
        } else if (i2 == 8) {
            textView.setText("\u76f4\u64ad\u8bb2\u89e3");
        } else {
            textView.setVisibility(8);
        }
    }

    public void setSmallWindowUICallBack(SmallWindowUICallback smallWindowUICallback) {
        this.mSmallWindowUICallBack = smallWindowUICallback;
    }

    public void setTopSafeDistance(int i2) {
        this.topSafeDistance = i2;
    }

    public void setmNeedAttach(boolean z) {
        CardView cardView;
        CardView cardView2;
        this.mNeedAttach = z;
        if (z) {
            this.layoutParams.flags = 16777768;
            Holder holder = this.holder;
            if (holder == null || (cardView2 = holder.weltCoverLayer) == null) {
                return;
            }
            cardView2.setVisibility(0);
            return;
        }
        this.layoutParams.flags = 16777256;
        Holder holder2 = this.holder;
        if (holder2 == null || (cardView = holder2.weltCoverLayer) == null) {
            return;
        }
        cardView.setVisibility(8);
    }

    public void startDisplaySmallwindow() {
        WindowManager.LayoutParams layoutParams;
        SmallwindowWrapperLayout smallwindowWrapperLayout;
        Holder holder = this.holder;
        if (holder == null) {
            return;
        }
        WindowManager windowManager = this.wm;
        if (windowManager != null && (layoutParams = this.layoutParams) != null && (smallwindowWrapperLayout = holder.windowContentWrapper) != null) {
            layoutParams.flags = (layoutParams.flags | 16) ^ 16;
            try {
                windowManager.updateViewLayout(smallwindowWrapperLayout, layoutParams);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        SmallwindowWrapperLayout smallwindowWrapperLayout2 = this.holder.windowContentWrapper;
        if (smallwindowWrapperLayout2 != null) {
            smallwindowWrapperLayout2.setAlpha(1.0f);
        }
        this.mIsInPostponeSmallWindowStatus = false;
    }

    public SmallWindow(Context context, ISmallWindowManager.IPlayerChange iPlayerChange, boolean z, View.OnClickListener onClickListener, boolean z2, float f2, int i2, String str) {
        this(context, iPlayerChange, z, onClickListener, z2, f2, i2, str, null);
    }

    public SmallWindow(Context context, ISmallWindowManager.IPlayerChange iPlayerChange, boolean z, View.OnClickListener onClickListener, boolean z2, float f2, int i2, String str, LiveUIConfigBean liveUIConfigBean) {
        this.mNeedShowMuteIcon = true;
        this.mNeedAttach = true;
        this.mNeedChangeHeightSize = 0.0f;
        this.mOriginalContentHeightSize = 0;
        this.mPlayStatusIconSizeHeight = 12;
        this.bottomSafeDistance = 118;
        this.topSafeDistance = 30;
        this.leftRightSafeDistance = 10;
        this.metrics = new Point();
        this.isHasAlertPermission = true;
        this.borderRadius = 0.0f;
        this.borderWidth = 2;
        this.borderColor = DEFAULT_BORDER_COLOR;
        this.windowPosX = -1;
        this.windowPosY = -1;
        this.mRootMeasuredWidth = 0;
        this.mRootMeasuredHeight = 0;
        this.mIsInPostponeSmallWindowStatus = false;
        this.mNeedOpenHeightChangeAnimation = false;
        this.mUpdateSmallwindowRunnable = new Runnable() { // from class: com.jingdong.common.widget.custom.liveplayer.videosmallwindow.SmallWindow.1
            {
                SmallWindow.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    SmallWindow.this.wm.updateViewLayout(SmallWindow.this.holder.windowContentWrapper, SmallWindow.this.layoutParams);
                    SmallWindow.this.holder.windowContentWrapper.setVisibility(0);
                } catch (Exception unused) {
                }
            }
        };
        this.onMuteClicked = new View.OnClickListener() { // from class: com.jingdong.common.widget.custom.liveplayer.videosmallwindow.SmallWindow.6
            {
                SmallWindow.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (SmallWindow.this.iPlayerChange != null) {
                    SmallWindow.this.isVoiceOn = !r2.isVoiceOn;
                    if (SmallWindow.this.holder != null && SmallWindow.this.holder.mute != null) {
                        SmallWindow.this.holder.mute.setImageResource(SmallWindow.this.isVoiceOn ? R.drawable.live_player_voice_on : R.drawable.live_player_mute);
                    }
                    SmallWindow.this.iPlayerChange.onMuteClick();
                }
            }
        };
        this.iPlayerChange = iPlayerChange;
        this.isLandScape = z;
        this.onCloseClick = onClickListener;
        this.showBorder = z2;
        this.borderRadius = f2;
        this.borderWidth = i2;
        this.borderColor = str;
        this.uiConfigBean = liveUIConfigBean;
        init(context);
    }

    public void open(View view, IOpenOrClose iOpenOrClose) {
        int i2;
        if (!this.isHasAlertPermission) {
            if (iOpenOrClose != null) {
                iOpenOrClose.complete();
            }
        } else if (this.holder == null || view.getParent() != null) {
            if (iOpenOrClose != null) {
                iOpenOrClose.complete();
            }
        } else {
            int i3 = this.windowPosX;
            if (i3 >= 0 && (i2 = this.windowPosY) >= 0) {
                WindowManager.LayoutParams layoutParams = this.layoutParams;
                layoutParams.x = i3;
                layoutParams.y = i2;
            } else {
                this.layoutParams.x = (this.metrics.x - this.contentLayoutParams.width) - DPIUtil.dip2px(this.leftRightSafeDistance);
                this.layoutParams.y = (this.metrics.y - this.contentLayoutParams.height) - DPIUtil.dip2px(this.bottomSafeDistance);
            }
            this.layoutParams.format = 1;
            int i4 = Build.VERSION.SDK_INT;
            if (i4 < 26 && CheckPermission.access$300() && CheckPermission.canDrawOverlay(view.getContext())) {
                this.layoutParams.type = 2003;
            }
            if (Log.D) {
                Log.d(TAG, "open: Build.VERSION.SDK_INT = " + i4);
                Log.d(TAG, "open: CheckPermission.canDrawOverlay(view.getContext()) = " + CheckPermission.canDrawOverlay(view.getContext()));
            }
            if (!CheckPermission.canDrawOverlay(view.getContext())) {
                if (iOpenOrClose != null) {
                    iOpenOrClose.complete();
                }
                SmallWindowUICallback smallWindowUICallback = this.mSmallWindowUICallBack;
                if (smallWindowUICallback != null) {
                    smallWindowUICallback.onError(10007, LiveSmallWindow.sErrMap.get(10007));
                }
                Set<SmallWindowUICallback> set = this.mSmallWindowUICallBacks;
                if (set != null) {
                    Iterator<SmallWindowUICallback> it = set.iterator();
                    while (it.hasNext()) {
                        it.next().onError(10007, LiveSmallWindow.sErrMap.get(10007));
                    }
                    return;
                }
                return;
            }
            try {
                this.wm.addView(this.holder.windowContentWrapper, this.layoutParams);
                SmallWindowUICallback smallWindowUICallback2 = this.mSmallWindowUICallBack;
                if (smallWindowUICallback2 != null) {
                    smallWindowUICallback2.onShowSmallWindow();
                }
                Set<SmallWindowUICallback> set2 = this.mSmallWindowUICallBacks;
                if (set2 != null) {
                    Iterator<SmallWindowUICallback> it2 = set2.iterator();
                    while (it2.hasNext()) {
                        it2.next().onShowSmallWindow();
                    }
                }
                if (view instanceof LivePlayer) {
                    if (Log.D) {
                        Log.e("Arthur", "Small window, update voice status ...");
                    }
                    LivePlayer livePlayer = (LivePlayer) view;
                    this.isVoiceOn = livePlayer.isVoiceOn();
                    updateMuteBtnVisibility(livePlayer.getStatus());
                    livePlayer.setStatusChangedListenerSmall(new LivePlayer.StatusChangedListener() { // from class: com.jingdong.common.widget.custom.liveplayer.videosmallwindow.SmallWindow.2
                        {
                            SmallWindow.this = this;
                        }

                        @Override // com.jingdong.common.widget.custom.liveplayer.LivePlayer.StatusChangedListener
                        public void onStatusChanged(LivePlayer.PlayerStatus playerStatus) {
                            SmallWindow.this.updateMuteBtnVisibility(playerStatus);
                        }
                    });
                }
                ImageView imageView = this.holder.mute;
                if (imageView != null) {
                    refreshVoiceIcon(imageView);
                }
                if ((CheckPermission.access$300() || (this.layoutParamsTypeWithInit == 2005 && this.layoutParams.type != 2005)) && !CheckPermission.canDrawOverlay(view.getContext())) {
                    this.isHasAlertPermission = false;
                    this.wm.removeViewImmediate(this.holder.windowContentWrapper);
                    if (iOpenOrClose != null) {
                        iOpenOrClose.complete();
                        return;
                    }
                    return;
                }
                this.holder.videoContent.addView(view);
                IDecorator iDecorator = this.mIDecorator;
                if (iDecorator != null) {
                    Holder holder = this.holder;
                    iDecorator.setDecorateView(holder.decorationContanier, holder.content);
                }
                hideArrowImageAndWeltCoverLayer();
                this.holder.windowContentWrapper.postDelayed(this.mUpdateSmallwindowRunnable, 500L);
            } catch (Exception e2) {
                if (this.holder.windowContentWrapper.getParent() != null) {
                    this.wm.removeViewImmediate(this.holder.windowContentWrapper);
                }
                if (iOpenOrClose != null) {
                    iOpenOrClose.complete();
                }
                e2.printStackTrace();
            }
        }
    }

    public void close(final IOpenOrClose iOpenOrClose) {
        SmallwindowWrapperLayout smallwindowWrapperLayout;
        Holder holder = this.holder;
        if (holder == null || (smallwindowWrapperLayout = holder.windowContentWrapper) == null) {
            return;
        }
        smallwindowWrapperLayout.setVisibility(4);
        FrameLayout frameLayout = this.holder.videoContent;
        if (frameLayout != null) {
            frameLayout.removeAllViews();
        }
        if (!this.isHasAlertPermission) {
            if (iOpenOrClose != null) {
                iOpenOrClose.complete();
                return;
            }
            return;
        }
        int i2 = ("M355".equals(BaseInfo.getDeviceModel()) || "GT-I9200".equals(BaseInfo.getDeviceModel()) || "X9007".equals(BaseInfo.getDeviceModel())) ? 1000 : 100;
        if (this.layoutParams != null) {
            WindowManager.LayoutParams layoutParams = this.layoutParams;
            sLastDismissPosition = new Point(layoutParams.x, layoutParams.y);
        }
        this.holder.windowContentWrapper.removeCallbacks(this.mUpdateSmallwindowRunnable);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.jingdong.common.widget.custom.liveplayer.videosmallwindow.SmallWindow.3
            {
                SmallWindow.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                IOpenOrClose iOpenOrClose2;
                try {
                    try {
                        SmallWindow.this.wm.removeViewImmediate(SmallWindow.this.holder.windowContentWrapper);
                        iOpenOrClose2 = iOpenOrClose;
                        if (iOpenOrClose2 == null) {
                            return;
                        }
                    } catch (Exception e2) {
                        OKLog.e(SmallWindow.TAG, e2);
                        iOpenOrClose2 = iOpenOrClose;
                        if (iOpenOrClose2 == null) {
                            return;
                        }
                    }
                    iOpenOrClose2.complete();
                } catch (Throwable th) {
                    IOpenOrClose iOpenOrClose3 = iOpenOrClose;
                    if (iOpenOrClose3 != null) {
                        iOpenOrClose3.complete();
                    }
                    throw th;
                }
            }
        }, i2);
    }
}
