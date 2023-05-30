package tv.danmaku.ijk.media.widget.controller;

import android.app.Activity;
import android.view.ViewGroup;
import com.jd.aips.verify.VerifyEngine;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes11.dex */
public class JDControllerOptions {
    public Activity activity;
    public int defSpeedIndex;
    public boolean enableErrorTip;
    public boolean enableFullSwitch;
    public boolean enableLoading;
    public boolean enableMuteSwitch;
    public boolean enableNonWifiTip;
    public boolean enableReplay;
    public boolean enableSpeedSwitch;
    public FullMode fullMode;
    public ViewGroup fullRootView;
    public boolean manualControlVisible;
    public int nonWifiStrRes;
    public int progressInterval;
    public List<String> speedList;
    public int systemBarFlag;
    public UIMode uiMode;

    /* loaded from: classes11.dex */
    public static final class Builder {
        private Activity activity;
        private ViewGroup fullRootView;
        private UIMode uiMode = UIMode.UI_DEFAULT;
        private FullMode fullMode = FullMode.FULL_LAND;
        private int systemBarFlag = -1;
        private boolean manualControlVisible = false;
        private boolean enableFullSwitch = true;
        private boolean enableMuteSwitch = true;
        private boolean enableSpeedSwitch = false;
        private List<String> speedList = Arrays.asList("0.5", "0.8", "1.0", "1.25", VerifyEngine.VERIFY_SDK_VERSION);
        private int defSpeedIndex = 2;
        private boolean enableReplay = true;
        private boolean enableNonWifiTip = true;
        private boolean enableErrorTip = true;
        private int nonWifiStrRes = -1;
        private int progressInterval = 500;
        private boolean enableLoading = true;

        public Builder activity(Activity activity) {
            this.activity = activity;
            return this;
        }

        public JDControllerOptions build() {
            return new JDControllerOptions(this);
        }

        public Builder enableErrorTip(boolean z) {
            this.enableErrorTip = z;
            return this;
        }

        public Builder enableFullSwitch(boolean z) {
            this.enableFullSwitch = z;
            return this;
        }

        public Builder enableLoading(boolean z) {
            this.enableLoading = z;
            return this;
        }

        public Builder enableMuteSwitch(boolean z) {
            this.enableMuteSwitch = z;
            return this;
        }

        public Builder enableNonWifiTip(boolean z) {
            this.enableNonWifiTip = z;
            return this;
        }

        public Builder enableReplay(boolean z) {
            this.enableReplay = z;
            return this;
        }

        public Builder enableSpeedSwitch(boolean z) {
            this.enableSpeedSwitch = z;
            return this;
        }

        public Builder fullMode(FullMode fullMode) {
            this.fullMode = fullMode;
            return this;
        }

        @Deprecated
        public Builder fullRootView(ViewGroup viewGroup) {
            this.fullRootView = viewGroup;
            return this;
        }

        public Builder manualControlVisible(boolean z) {
            this.manualControlVisible = z;
            return this;
        }

        public Builder nonWifiStrRes(int i2) {
            this.nonWifiStrRes = i2;
            return this;
        }

        public Builder progressInterval(int i2) {
            this.progressInterval = i2;
            return this;
        }

        public Builder systemBarFlag(int i2) {
            this.systemBarFlag = i2;
            return this;
        }

        public Builder uiMode(UIMode uIMode) {
            this.uiMode = uIMode;
            return this;
        }

        public Builder enableSpeedSwitch(int i2, List<String> list) {
            if (list != null && !list.isEmpty()) {
                this.speedList = list;
                this.defSpeedIndex = i2;
                this.enableMuteSwitch = true;
            }
            return this;
        }
    }

    /* loaded from: classes11.dex */
    public enum FullMode {
        FULL_PORT,
        FULL_LAND,
        FULL_SENSOR,
        FULL_AUTO
    }

    /* loaded from: classes11.dex */
    public enum UIMode {
        UI_DEFAULT,
        UI_ITEM_SMALL,
        UI_ITEM_BIG
    }

    private JDControllerOptions(Builder builder) {
        this.fullMode = builder.fullMode;
        this.uiMode = builder.uiMode;
        this.activity = builder.activity;
        this.fullRootView = builder.fullRootView;
        this.systemBarFlag = builder.systemBarFlag;
        this.manualControlVisible = builder.manualControlVisible;
        this.enableFullSwitch = builder.enableFullSwitch;
        if (builder.enableSpeedSwitch) {
            this.speedList = builder.speedList;
            this.defSpeedIndex = builder.defSpeedIndex;
            this.enableSpeedSwitch = true;
        }
        this.enableMuteSwitch = builder.enableMuteSwitch;
        this.enableReplay = builder.enableReplay;
        this.enableNonWifiTip = builder.enableNonWifiTip;
        this.nonWifiStrRes = builder.nonWifiStrRes;
        this.progressInterval = builder.progressInterval;
        this.enableErrorTip = builder.enableErrorTip;
        this.enableLoading = builder.enableLoading;
    }
}
