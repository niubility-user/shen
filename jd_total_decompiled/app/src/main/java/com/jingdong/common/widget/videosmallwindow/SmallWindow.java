package com.jingdong.common.widget.videosmallwindow;

import android.app.AppOpsManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Binder;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.jingdong.common.R;
import com.jingdong.common.unification.video.player.VideoPlayUtil;
import com.jingdong.common.widget.custom.liveplayer.videosmallwindow.TextureVideoViewOutlineProvider;
import com.jingdong.common.widget.videosmallwindow.ISmallWindowManager;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import tv.danmaku.ijk.media.example.widget.media.JDVideoView;

/* loaded from: classes12.dex */
public class SmallWindow {
    private static final String TAG = "SmallWindow";
    private Holder holder;
    private ISmallWindowManager.IPlayerChange iPlayerChange;
    private boolean isHasAlertPermission;
    private boolean isLandScape;
    private boolean isVoiceOn;
    private WindowManager.LayoutParams layoutParams;
    private int layoutParamsTypeWithInit;
    private SmallWindowConfig mSmallWindowConfig;
    private Point metrics;
    private View.OnClickListener onCloseClick;
    private final View.OnClickListener onMuteClicked;
    private View videoView;
    private WindowManager wm;

    /* loaded from: classes12.dex */
    private static class CheckPermission {
        private static final int OP_SYSTEM_ALERT_WINDOW = 24;

        private CheckPermission() {
        }

        static /* synthetic */ boolean access$000() {
            return isNeedPermission();
        }

        /* JADX INFO: Access modifiers changed from: private */
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
        /* JADX WARN: Removed duplicated region for block: B:29:0x003d A[EXC_TOP_SPLITTER, SYNTHETIC] */
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
        */
        private static String getMiuiProperty() {
            Throwable th;
            String str = SmallWindow.TAG;
            ?? properties = new Properties();
            FileInputStream fileInputStream = null;
            try {
            } catch (IOException e2) {
                e = e2;
                OKLog.e(SmallWindow.TAG, (Throwable) e);
            }
            try {
                try {
                    e = new FileInputStream(new File(Environment.getRootDirectory(), "build.prop"));
                } catch (IOException e3) {
                    e = e3;
                    e = 0;
                } catch (Throwable th2) {
                    th = th2;
                    if (fileInputStream != null) {
                    }
                    throw th;
                }
                try {
                    properties.load(e);
                    e.close();
                    e = e;
                } catch (IOException e4) {
                    e = e4;
                    OKLog.e(SmallWindow.TAG, e);
                    if (e != 0) {
                        e.close();
                        e = e;
                    }
                    str = properties.getProperty("ro.miui.ui.version.name", null);
                    return str;
                }
                str = properties.getProperty("ro.miui.ui.version.name", null);
                return str;
            } catch (Throwable th3) {
                th = th3;
                fileInputStream = e;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e5) {
                        OKLog.e(str, e5);
                    }
                }
                throw th;
            }
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

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class Holder {
        private ImageView close;
        private RelativeLayout content;
        private ImageView mute;
        private VideoContent videoContent;

        private Holder() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public interface IOpenOrClose {
        void complete();
    }

    public SmallWindow(Context context, ISmallWindowManager.IPlayerChange iPlayerChange, boolean z, View.OnClickListener onClickListener) {
        this(context, null, iPlayerChange, z, onClickListener);
    }

    private Drawable createBorder() {
        SmallWindowConfig smallWindowConfig = this.mSmallWindowConfig;
        if (smallWindowConfig == null || TextUtils.isEmpty(smallWindowConfig.getBorderColor())) {
            return null;
        }
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setStroke(DPIUtil.dip2px(this.mSmallWindowConfig.getBorderWidth()), Color.parseColor(this.mSmallWindowConfig.getBorderColor()));
        gradientDrawable.setColor(-16777216);
        gradientDrawable.setCornerRadius(DPIUtil.dip2px(this.mSmallWindowConfig.getBorderRadius()));
        return gradientDrawable;
    }

    private void init(Context context) {
        WindowManager windowManager = (WindowManager) context.getApplicationContext().getSystemService("window");
        this.wm = windowManager;
        windowManager.getDefaultDisplay().getSize(this.metrics);
        this.layoutParams = new WindowManager.LayoutParams();
        int i2 = context.getApplicationContext().getResources().getConfiguration().orientation;
        if (this.isLandScape) {
            if (OKLog.D) {
                OKLog.d("MMM", "LIVE_SCREEN_ORIENTATION_LANDSCAPE " + i2);
            }
            if (i2 == 1) {
                this.layoutParams.width = DPIUtil.dip2px(153.0f);
                this.layoutParams.height = DPIUtil.dip2px(86.0f);
            } else if (i2 == 2) {
                this.layoutParams.width = DPIUtil.dip2px(86.0f);
                this.layoutParams.height = DPIUtil.dip2px(153.0f);
            }
        } else {
            if (OKLog.D) {
                OKLog.d("MMM", "LIVE_SCREEN_ORIENTATION_PORTRAIT " + i2);
            }
            if (i2 == 1) {
                this.layoutParams.width = DPIUtil.dip2px(86.0f);
                this.layoutParams.height = DPIUtil.dip2px(153.0f);
            } else if (i2 == 2) {
                this.layoutParams.width = DPIUtil.dip2px(153.0f);
                this.layoutParams.height = DPIUtil.dip2px(86.0f);
            }
        }
        WindowManager.LayoutParams layoutParams = this.layoutParams;
        layoutParams.gravity = 51;
        Point point2 = this.metrics;
        layoutParams.x = point2.x - layoutParams.width;
        layoutParams.y = (point2.y - layoutParams.height) - DPIUtil.dip2px(121.0f);
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
        WindowManager.LayoutParams layoutParams2 = this.layoutParams;
        this.layoutParamsTypeWithInit = layoutParams2.type;
        layoutParams2.flags = 16777256;
        setViews(context);
    }

    private void setViews(Context context) {
        Holder holder = new Holder();
        this.holder = holder;
        holder.content = new RelativeLayout(context);
        SmallWindowConfig smallWindowConfig = this.mSmallWindowConfig;
        if (smallWindowConfig != null && smallWindowConfig.getBorderWidth() > 0) {
            int dip2px = DPIUtil.dip2px(this.mSmallWindowConfig.getBorderWidth());
            this.holder.content.setPadding(dip2px, dip2px, dip2px, dip2px);
            this.holder.content.setBackground(createBorder());
        }
        this.holder.videoContent = new VideoContent(context);
        SmallWindowConfig smallWindowConfig2 = this.mSmallWindowConfig;
        if (smallWindowConfig2 != null && smallWindowConfig2.getBorderRadius() > 0 && Build.VERSION.SDK_INT >= 21) {
            try {
                this.holder.videoContent.setOutlineProvider(new TextureVideoViewOutlineProvider(DPIUtil.dip2px(context, this.mSmallWindowConfig.getBorderRadius() - (this.mSmallWindowConfig.getBorderWidth() - 1))));
                this.holder.videoContent.setClipToOutline(true);
            } catch (Exception unused) {
            }
        }
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(R.drawable.live_smallwinow_player_close);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(DPIUtil.dip2px(16.0f), DPIUtil.dip2px(16.0f));
        layoutParams.addRule(11);
        layoutParams.rightMargin = DPIUtil.dip2px(6.0f);
        layoutParams.topMargin = DPIUtil.dip2px(6.0f);
        imageView.setLayoutParams(layoutParams);
        imageView.setOnClickListener(this.onCloseClick);
        ImageView imageView2 = new ImageView(context);
        imageView2.setId(R.id.live_smallwindow_img_voice_mute);
        imageView2.setImageResource(R.drawable.live_player_mute);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(DPIUtil.dip2px(22.0f), DPIUtil.dip2px(22.0f));
        layoutParams2.addRule(11);
        layoutParams2.addRule(12);
        layoutParams2.rightMargin = DPIUtil.dip2px(6.0f);
        layoutParams2.bottomMargin = DPIUtil.dip2px(6.0f);
        imageView2.setLayoutParams(layoutParams2);
        imageView2.setOnClickListener(this.onMuteClicked);
        this.holder.close = imageView;
        this.holder.mute = imageView2;
        this.holder.content.addView(this.holder.videoContent);
        this.holder.content.addView(this.holder.close);
        this.holder.content.addView(this.holder.mute);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateMuteIcon() {
        Holder holder = this.holder;
        if (holder == null || holder.mute == null) {
            return;
        }
        this.holder.mute.setImageResource(this.isVoiceOn ? R.drawable.live_player_voice_on : R.drawable.live_player_mute);
    }

    public void close(final IOpenOrClose iOpenOrClose) {
        if (this.holder == null) {
            return;
        }
        View view = this.videoView;
        if (view != null && (view instanceof ViewGroup)) {
            traversalNewVideoView((ViewGroup) view).setVolume(1.0f);
        }
        this.holder.content.setVisibility(4);
        if (this.holder.videoContent.getChildCount() == 0) {
            if (iOpenOrClose != null) {
                iOpenOrClose.complete();
                return;
            }
            return;
        }
        this.holder.videoContent.removeAllViews();
        if (this.isHasAlertPermission) {
            this.holder.content.postDelayed(new Runnable() { // from class: com.jingdong.common.widget.videosmallwindow.SmallWindow.2
                @Override // java.lang.Runnable
                public void run() {
                    IOpenOrClose iOpenOrClose2;
                    try {
                        try {
                            SmallWindow.this.wm.removeViewImmediate(SmallWindow.this.holder.content);
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
            }, ("M355".equals(BaseInfo.getDeviceModel()) || "GT-I9200".equals(BaseInfo.getDeviceModel()) || "X9007".equals(BaseInfo.getDeviceModel())) ? 1000 : 100);
        }
    }

    public void open(View view, IOpenOrClose iOpenOrClose) {
        this.videoView = view;
        if (!this.isHasAlertPermission) {
            if (iOpenOrClose != null) {
                iOpenOrClose.complete();
            }
        } else if (this.holder == null || view.getParent() != null) {
            if (iOpenOrClose != null) {
                iOpenOrClose.complete();
            }
        } else {
            WindowManager.LayoutParams layoutParams = this.layoutParams;
            Point point2 = this.metrics;
            layoutParams.x = point2.x - layoutParams.width;
            layoutParams.y = (point2.y - layoutParams.height) - DPIUtil.dip2px(121.0f);
            SmallWindowConfig smallWindowConfig = this.mSmallWindowConfig;
            if (smallWindowConfig != null) {
                if (smallWindowConfig.getLocationX() > 0) {
                    this.layoutParams.x = this.mSmallWindowConfig.getLocationX();
                }
                if (this.mSmallWindowConfig.getLocationY() > 0) {
                    this.layoutParams.y = this.mSmallWindowConfig.getLocationY();
                }
            }
            boolean z = true;
            this.layoutParams.format = 1;
            int i2 = Build.VERSION.SDK_INT;
            if (i2 < 26 && CheckPermission.access$000() && CheckPermission.canDrawOverlay(view.getContext())) {
                this.layoutParams.type = 2003;
            }
            if (Log.D) {
                Log.d(TAG, "open: Build.VERSION.SDK_INT = " + i2);
                Log.d(TAG, "open: CheckPermission.canDrawOverlay(view.getContext()) = " + CheckPermission.canDrawOverlay(view.getContext()));
            }
            if (!CheckPermission.canDrawOverlay(view.getContext())) {
                if (iOpenOrClose != null) {
                    iOpenOrClose.complete();
                    return;
                }
                return;
            }
            try {
                this.wm.addView(this.holder.content, this.layoutParams);
                if (view instanceof ViewGroup) {
                    JDVideoView traversalNewVideoView = traversalNewVideoView((ViewGroup) view);
                    if (traversalNewVideoView != null) {
                        if (traversalNewVideoView.getVolume() == 0.0f) {
                            z = false;
                        }
                        this.isVoiceOn = z;
                    }
                    updateMuteIcon();
                }
                if ((!CheckPermission.access$000() && (this.layoutParamsTypeWithInit != 2005 || this.layoutParams.type == 2005)) || CheckPermission.canDrawOverlay(view.getContext())) {
                    this.holder.videoContent.addView(view);
                    this.holder.content.postDelayed(new Runnable() { // from class: com.jingdong.common.widget.videosmallwindow.SmallWindow.1
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                SmallWindow.this.wm.updateViewLayout(SmallWindow.this.holder.content, SmallWindow.this.layoutParams);
                                SmallWindow.this.holder.content.setVisibility(0);
                            } catch (Exception unused) {
                            }
                        }
                    }, 500L);
                    return;
                }
                this.isHasAlertPermission = false;
                this.wm.removeViewImmediate(this.holder.content);
                if (iOpenOrClose != null) {
                    iOpenOrClose.complete();
                }
            } catch (Exception e2) {
                if (this.holder.content.getParent() != null) {
                    this.wm.removeViewImmediate(this.holder.content);
                }
                if (iOpenOrClose != null) {
                    iOpenOrClose.complete();
                }
                e2.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setSize(Point point2) {
        if (point2 == null) {
            return;
        }
        WindowManager.LayoutParams layoutParams = this.layoutParams;
        layoutParams.width = point2.x;
        layoutParams.height = point2.y;
    }

    public JDVideoView traversalNewVideoView(ViewGroup viewGroup) {
        JDVideoView traversalNewVideoView;
        if (viewGroup instanceof JDVideoView) {
            return (JDVideoView) viewGroup;
        }
        for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if (childAt instanceof JDVideoView) {
                return (JDVideoView) childAt;
            }
            if ((childAt instanceof ViewGroup) && (traversalNewVideoView = traversalNewVideoView((ViewGroup) childAt)) != null) {
                return traversalNewVideoView;
            }
        }
        return null;
    }

    public SmallWindow(Context context, SmallWindowConfig smallWindowConfig, ISmallWindowManager.IPlayerChange iPlayerChange, boolean z, View.OnClickListener onClickListener) {
        this.metrics = new Point();
        this.isHasAlertPermission = true;
        this.onMuteClicked = new View.OnClickListener() { // from class: com.jingdong.common.widget.videosmallwindow.SmallWindow.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SmallWindow smallWindow;
                JDVideoView traversalNewVideoView;
                SmallWindow.this.isVoiceOn = !r0.isVoiceOn;
                SmallWindow.this.updateMuteIcon();
                if (SmallWindow.this.videoView != null && (SmallWindow.this.videoView instanceof ViewGroup) && (traversalNewVideoView = (smallWindow = SmallWindow.this).traversalNewVideoView((ViewGroup) smallWindow.videoView)) != null) {
                    traversalNewVideoView.setVolume(SmallWindow.this.isVoiceOn ? 1.0f : 0.0f);
                }
                if (SmallWindow.this.isVoiceOn) {
                    VideoPlayUtil.muteAudioFocus(view.getContext(), true);
                } else {
                    VideoPlayUtil.muteAudioFocus(view.getContext(), false);
                }
            }
        };
        this.mSmallWindowConfig = smallWindowConfig;
        this.iPlayerChange = iPlayerChange;
        this.isLandScape = z;
        this.onCloseClick = onClickListener;
        init(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class VideoContent extends FrameLayout {
        private int downX;
        private int downX2;
        private int downY;
        private int downY2;
        private boolean isMove;

        public VideoContent(Context context) {
            super(context);
            this.downX = 0;
            this.downY = 0;
            this.isMove = false;
            this.downX2 = 0;
            this.downY2 = 0;
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent motionEvent) {
            int action = motionEvent.getAction();
            if (action == 0) {
                this.downX = (int) motionEvent.getRawX();
                this.downY = (int) motionEvent.getRawY();
                this.downX2 = (int) motionEvent.getRawX();
                this.downY2 = (int) motionEvent.getRawY();
            } else if (action == 1) {
                if ((!this.isMove || (Math.abs(((int) motionEvent.getRawX()) - this.downX2) < 20 && Math.abs(((int) motionEvent.getRawY()) - this.downY2) < 20)) && SmallWindow.this.iPlayerChange != null && SmallWindow.this.holder != null) {
                    SmallWindow.this.iPlayerChange.onSmallClick();
                }
                this.isMove = false;
            } else if (action == 2) {
                this.isMove = true;
                int rawX = (int) motionEvent.getRawX();
                int rawY = (int) motionEvent.getRawY();
                SmallWindow.this.layoutParams.x += rawX - this.downX;
                SmallWindow.this.layoutParams.y += rawY - this.downY;
                if (SmallWindow.this.holder != null) {
                    SmallWindow.this.wm.updateViewLayout(SmallWindow.this.holder.content, SmallWindow.this.layoutParams);
                }
                this.downX = rawX;
                this.downY = rawY;
            }
            return true;
        }

        public VideoContent(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.downX = 0;
            this.downY = 0;
            this.isMove = false;
            this.downX2 = 0;
            this.downY2 = 0;
        }
    }
}
