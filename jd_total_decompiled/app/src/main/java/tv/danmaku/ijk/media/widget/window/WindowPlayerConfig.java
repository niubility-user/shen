package tv.danmaku.ijk.media.widget.window;

import android.content.Context;
import android.graphics.Point;
import android.view.WindowManager;
import com.jingdong.common.widget.videosmallwindow.SmallWindowConfig;
import com.jingdong.sdk.platform.business.personal.R2;
import tv.danmaku.ijk.media.utils.PlayerSystemUtil;

/* loaded from: classes11.dex */
public class WindowPlayerConfig {
    private String borderColor;
    private float borderRadius;
    private int borderWidth;
    private final boolean isLive;
    private final WindowRect rect;
    private final boolean showBorder;
    private final WindowType type;

    /* renamed from: tv.danmaku.ijk.media.widget.window.WindowPlayerConfig$1  reason: invalid class name */
    /* loaded from: classes11.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$tv$danmaku$ijk$media$widget$window$WindowPlayerConfig$WindowType;

        static {
            int[] iArr = new int[WindowType.values().length];
            $SwitchMap$tv$danmaku$ijk$media$widget$window$WindowPlayerConfig$WindowType = iArr;
            try {
                iArr[WindowType.JD_SHOP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$tv$danmaku$ijk$media$widget$window$WindowPlayerConfig$WindowType[WindowType.JD_VOD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$tv$danmaku$ijk$media$widget$window$WindowPlayerConfig$WindowType[WindowType.JD_LIVE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* loaded from: classes11.dex */
    public static class Build {
        private Context context;
        private WindowRect rect = new WindowRect(0, 0, 99, R2.anim.popup_center_enter);
        private float borderRadius = 12.0f;
        private int borderWidth = 2;
        private String borderColor = SmallWindowConfig.DEFAULT_BORDER_COLOR;
        private boolean isLive = false;
        private WindowType type = WindowType.JD_VOD;

        public Build borderColor(String str) {
            this.borderColor = str;
            return this;
        }

        public Build borderRadius(float f2) {
            this.borderRadius = f2;
            return this;
        }

        public Build borderWidth(int i2) {
            this.borderWidth = i2;
            return this;
        }

        public WindowPlayerConfig builder() {
            if (this.context != null) {
                return new WindowPlayerConfig(this, null);
            }
            throw new RuntimeException("WindowPlayerConfig: context can not be null");
        }

        public Build context(Context context) {
            this.context = context;
            return this;
        }

        public Build floatRect(WindowRect windowRect) {
            this.rect = windowRect;
            return this;
        }

        public Build isLive(boolean z) {
            this.isLive = z;
            return this;
        }

        public Build type(WindowType windowType) {
            this.type = windowType;
            return this;
        }
    }

    /* loaded from: classes11.dex */
    public static final class WindowRect {
        public int height;
        public int width;
        public int x;
        public int y;

        public WindowRect(int i2, int i3, int i4, int i5) {
            this.x = i2;
            this.y = i3;
            this.width = i4;
            this.height = i5;
        }
    }

    /* loaded from: classes11.dex */
    public enum WindowType {
        JD_LIVE,
        JD_SHOP,
        JD_VOD
    }

    /* synthetic */ WindowPlayerConfig(Build build, AnonymousClass1 anonymousClass1) {
        this(build);
    }

    public String getBorderColor() {
        return this.borderColor;
    }

    public float getBorderRadius() {
        return this.borderRadius;
    }

    public int getBorderWidth() {
        return this.borderWidth;
    }

    public WindowRect getRect() {
        return this.rect;
    }

    public WindowType getType() {
        return this.type;
    }

    public boolean isLive() {
        return this.isLive;
    }

    public boolean isShowBorder() {
        return this.showBorder;
    }

    private WindowPlayerConfig(Build build) {
        this.borderRadius = 12.0f;
        this.borderWidth = 2;
        this.borderColor = SmallWindowConfig.DEFAULT_BORDER_COLOR;
        Context context = build.context;
        WindowType windowType = build.type;
        this.type = windowType;
        Point point2 = new Point();
        ((WindowManager) context.getApplicationContext().getSystemService("window")).getDefaultDisplay().getSize(point2);
        int i2 = AnonymousClass1.$SwitchMap$tv$danmaku$ijk$media$widget$window$WindowPlayerConfig$WindowType[windowType.ordinal()];
        if (i2 == 1 || i2 == 2) {
            this.rect = new WindowRect((point2.x - PlayerSystemUtil.dip2px(context, 99.0f)) - PlayerSystemUtil.dip2px(context, 20.0f), (point2.y - PlayerSystemUtil.dip2px(context, 176.0f)) - PlayerSystemUtil.dip2px(context, 121.0f), 99, R2.anim.popup_center_enter);
            this.borderRadius = 12.0f;
            this.borderWidth = 2;
            this.borderColor = SmallWindowConfig.DEFAULT_BORDER_COLOR;
            this.showBorder = true;
            this.isLive = false;
        } else if (i2 != 3) {
            this.rect = build.rect;
            this.showBorder = build.borderWidth > 0;
            this.borderColor = build.borderColor;
            this.borderRadius = build.borderRadius;
            this.isLive = build.isLive;
        } else {
            this.rect = new WindowRect((point2.x - PlayerSystemUtil.dip2px(context, 86.0f)) - PlayerSystemUtil.dip2px(context, 20.0f), (point2.y - PlayerSystemUtil.dip2px(context, 153.0f)) - PlayerSystemUtil.dip2px(context, 121.0f), 86, 153);
            this.showBorder = false;
            this.isLive = true;
        }
    }
}
