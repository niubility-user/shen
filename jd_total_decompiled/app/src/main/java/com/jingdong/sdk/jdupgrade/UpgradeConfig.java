package com.jingdong.sdk.jdupgrade;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import androidx.annotation.ColorInt;
import com.jingdong.sdk.jdupgrade.a.j.h;
import com.jingdong.sdk.jdupgrade.inner.ui.d;

/* loaded from: classes7.dex */
public final class UpgradeConfig {
    public static int MAX_RETRY_TIMES = 6;
    public static int RETRY_INTERVAL = 5;
    private int acceptUpgradeType;
    private String appId;
    private String appSecret;
    private boolean autoDownloadWithWifi;
    private boolean autoInstallAfterDownload;
    private Class<? extends Activity>[] blackPages;
    private Drawable dialogBackground;
    private int[] dialogLayoutMargin;
    private UpgradeDialogPopupRequest dialogPopupRequest;
    private int downloadRetryInterval;
    private int downloadRetryTimes;
    private String downloadTips;
    private Class<? extends DownloadView> downloadViewClass;
    private boolean ignoreUserRejectInUnlimitedCheck;
    private int installCancelResId;
    private int installConfirmResId;
    private boolean isPreEnvironment;
    private Drawable loadingProgressBarDrawable;
    private Integer logoId;
    private Class<? extends RemindView> remindViewClass;
    private boolean showToast;
    private int upgradeCancelResId;
    private d upgradeCancelTextStyle;
    private int upgradeConfirmResId;
    private d upgradeConfirmTextStyle;
    private int upgradeContentResId;
    private d upgradeContentTextStyle;
    private int upgradeDialogResId;
    private int upgradeHeaderResId;
    private d upgradeTitleTextStyle;
    private Class<? extends Activity>[] whitePages;

    /* loaded from: classes7.dex */
    public static class AcceptUpgradeType {
        public static final int FORCE = 4;
        public static final int GRAY = 1;
        public static final int NORMAL = 2;
    }

    /* loaded from: classes7.dex */
    public static class Builder {
        private String appId;
        private String appSecret;
        private Drawable dialogBackground;
        private int[] dialogLayoutMargin;
        private UpgradeDialogPopupRequest dialogPopupRequest;
        private String downloadTips;
        private int installCancelResId;
        private int installConfirmResId;
        private boolean isPreEnvironment;
        private Drawable loadingProgressBarDrawable;
        private Integer logoId;
        private int upgradeCancelResId;
        private d upgradeCancelTextStyle;
        private int upgradeConfirmResId;
        private d upgradeConfirmTextStyle;
        private int upgradeContentResId;
        private d upgradeContentTextStyle;
        private int upgradeDialogResId;
        private int upgradeHeaderResId;
        private d upgradeTitleTextStyle;
        private boolean autoDownloadWithWifi = false;
        private boolean autoInstallAfterDownload = false;
        private int acceptUpgradeType = 0;
        private Class<? extends RemindView> remindViewClass = null;
        private Class<? extends DownloadView> downloadViewClass = null;
        private Class<? extends Activity>[] whitePages = null;
        private Class<? extends Activity>[] blackPages = null;
        private boolean logEnable = false;
        private boolean ignoreUserRejectInUnlimitedCheck = true;
        private boolean showToast = true;
        private int downloadRetryTimes = UpgradeConfig.MAX_RETRY_TIMES;
        private int downloadRetryInterval = UpgradeConfig.RETRY_INTERVAL;

        public Builder(String str, String str2, int i2) {
            this.appId = str;
            this.appSecret = str2;
            this.logoId = Integer.valueOf(i2);
        }

        public UpgradeConfig build() {
            return new UpgradeConfig(this);
        }

        public Builder setAcceptUpgradeType(int i2) {
            this.acceptUpgradeType = i2;
            return this;
        }

        public Builder setAutoDownloadWithWifi(boolean z) {
            this.autoDownloadWithWifi = z;
            return this;
        }

        public Builder setAutoInstallAfterDownload(boolean z) {
            this.autoInstallAfterDownload = z;
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Deprecated
        public Builder setCustomRemindView(RemindView remindView) {
            return setCustomRemindViewClass(remindView.getClass());
        }

        public Builder setCustomRemindViewClass(Class<? extends RemindView> cls) {
            this.remindViewClass = cls;
            return this;
        }

        public Builder setDefaultDialogBgResId(int i2) {
            this.upgradeDialogResId = i2;
            return this;
        }

        @Deprecated
        public Builder setDefaultDialogCancelBtnBgResId(int i2) {
            this.upgradeCancelResId = i2;
            return this;
        }

        public Builder setDefaultDialogCancelBtnTextStyle(int i2, @ColorInt int i3, boolean z) {
            this.upgradeCancelTextStyle = new d(i2, i3, z);
            return this;
        }

        @Deprecated
        public Builder setDefaultDialogConfirmBtnBgResId(int i2) {
            this.upgradeConfirmResId = i2;
            return this;
        }

        public Builder setDefaultDialogConfirmBtnTextStyle(int i2, @ColorInt int i3, boolean z) {
            this.upgradeConfirmTextStyle = new d(i2, i3, z);
            return this;
        }

        public Builder setDefaultDialogContentBgResId(int i2) {
            this.upgradeContentResId = i2;
            return this;
        }

        public Builder setDefaultDialogContentTextStyle(int i2, @ColorInt int i3, boolean z) {
            this.upgradeContentTextStyle = new d(i2, i3, z);
            return this;
        }

        public Builder setDefaultDialogHeaderResId(int i2) {
            this.upgradeHeaderResId = i2;
            return this;
        }

        public Builder setDefaultDialogTitleTextStyle(int i2, @ColorInt int i3, boolean z) {
            this.upgradeTitleTextStyle = new d(i2, i3, z);
            return this;
        }

        public Builder setDefaultInstallDialogCancelBtnBgResId(int i2) {
            this.installCancelResId = i2;
            return this;
        }

        public Builder setDefaultInstallDialogConfirmBtnBgResId(int i2) {
            this.installConfirmResId = i2;
            return this;
        }

        public Builder setDefaultUpgradeDialogCancelBtnBgResId(int i2) {
            this.upgradeCancelResId = i2;
            return this;
        }

        public Builder setDefaultUpgradeDialogConfirmBtnBgResId(int i2) {
            this.upgradeConfirmResId = i2;
            return this;
        }

        public Builder setDialogBackGroundDrawable(Drawable drawable) {
            this.dialogBackground = drawable;
            return this;
        }

        public Builder setDialogBlackPages(Class<? extends Activity>[] clsArr) {
            this.blackPages = clsArr;
            return this;
        }

        public Builder setDialogLayoutMargin(int i2, int i3) {
            return setDialogLayoutMargin(i2, i3, -1, -1);
        }

        public Builder setDialogLayoutMargin(int i2, int i3, int i4, int i5) {
            this.dialogLayoutMargin = new int[]{i2, i3, i4, i5};
            return this;
        }

        public Builder setDialogPopupRequest(UpgradeDialogPopupRequest upgradeDialogPopupRequest) {
            this.dialogPopupRequest = upgradeDialogPopupRequest;
            return this;
        }

        public Builder setDialogWhitePages(Class<? extends Activity>[] clsArr) {
            this.whitePages = clsArr;
            return this;
        }

        public Builder setDownloadRetryIntervalInSeconds(int i2) {
            if (this.downloadRetryInterval <= 0) {
                return this;
            }
            this.downloadRetryInterval = i2;
            return this;
        }

        public Builder setDownloadRetryTimes(int i2) {
            if (i2 <= 0) {
                return this;
            }
            this.downloadRetryTimes = i2;
            return this;
        }

        public Builder setDownloadTips(String str) {
            this.downloadTips = str;
            return this;
        }

        public Builder setIgnoreUserRejectInUnlimitedCheck(boolean z) {
            this.ignoreUserRejectInUnlimitedCheck = z;
            return this;
        }

        public Builder setLoadingProgressBarDrawable(Drawable drawable) {
            this.loadingProgressBarDrawable = drawable;
            return this;
        }

        public Builder setLogEnable(boolean z) {
            this.logEnable = z;
            return this;
        }

        public Builder setPreEnvironment(boolean z) {
            this.isPreEnvironment = z;
            return this;
        }

        public Builder setShowToast(boolean z) {
            this.showToast = z;
            return this;
        }
    }

    private UpgradeConfig(Builder builder) {
        this.showToast = true;
        this.appId = builder.appId;
        this.appSecret = builder.appSecret;
        this.autoDownloadWithWifi = builder.autoDownloadWithWifi;
        this.autoInstallAfterDownload = builder.autoInstallAfterDownload;
        this.remindViewClass = builder.remindViewClass;
        this.downloadViewClass = builder.downloadViewClass;
        this.acceptUpgradeType = builder.acceptUpgradeType;
        this.blackPages = builder.blackPages;
        this.whitePages = builder.whitePages;
        this.dialogPopupRequest = builder.dialogPopupRequest;
        this.dialogBackground = builder.dialogBackground;
        this.upgradeHeaderResId = builder.upgradeHeaderResId;
        this.upgradeContentResId = builder.upgradeContentResId;
        this.upgradeConfirmResId = builder.upgradeConfirmResId;
        this.upgradeCancelResId = builder.upgradeCancelResId;
        this.installConfirmResId = builder.installConfirmResId;
        this.installCancelResId = builder.installCancelResId;
        this.upgradeConfirmTextStyle = builder.upgradeConfirmTextStyle;
        this.upgradeCancelTextStyle = builder.upgradeCancelTextStyle;
        this.upgradeTitleTextStyle = builder.upgradeTitleTextStyle;
        this.upgradeContentTextStyle = builder.upgradeContentTextStyle;
        this.upgradeDialogResId = builder.upgradeDialogResId;
        this.loadingProgressBarDrawable = builder.loadingProgressBarDrawable;
        this.dialogLayoutMargin = builder.dialogLayoutMargin;
        this.ignoreUserRejectInUnlimitedCheck = builder.ignoreUserRejectInUnlimitedCheck;
        this.showToast = builder.showToast;
        this.logoId = builder.logoId;
        this.isPreEnvironment = builder.isPreEnvironment;
        this.downloadTips = builder.downloadTips;
        this.downloadRetryTimes = builder.downloadRetryTimes;
        this.downloadRetryInterval = builder.downloadRetryInterval;
        h.a(builder.logEnable);
    }

    public int getAcceptUpgradeType() {
        return this.acceptUpgradeType;
    }

    public String getAppId() {
        return this.appId;
    }

    public String getAppSecret() {
        return this.appSecret;
    }

    public Class<? extends Activity>[] getBlackPages() {
        return this.blackPages;
    }

    public DownloadView getCustomDownloadView() {
        try {
            return this.downloadViewClass.newInstance();
        } catch (Throwable unused) {
            return null;
        }
    }

    public RemindView getCustomRemindView() {
        try {
            return this.remindViewClass.newInstance();
        } catch (Throwable unused) {
            return null;
        }
    }

    public Drawable getDialogBackgroundDrawable() {
        return this.dialogBackground;
    }

    public int[] getDialogLayoutMargin() {
        return this.dialogLayoutMargin;
    }

    public UpgradeDialogPopupRequest getDialogPopupRequest() {
        return this.dialogPopupRequest;
    }

    public int getDownloadRetryInterval() {
        return this.downloadRetryInterval;
    }

    public int getDownloadRetryTimes() {
        return this.downloadRetryTimes;
    }

    public String getDownloadTips() {
        return this.downloadTips;
    }

    public int getInstallCancelResId() {
        return this.installCancelResId;
    }

    public int getInstallConfirmResId() {
        return this.installConfirmResId;
    }

    public Drawable getLoadingProgressBarDrawable() {
        return this.loadingProgressBarDrawable;
    }

    public Integer getLogoId() {
        return this.logoId;
    }

    public int getUpgradeCancelResId() {
        return this.upgradeCancelResId;
    }

    public d getUpgradeCancelTextStyle() {
        return this.upgradeCancelTextStyle;
    }

    public int getUpgradeConfirmResId() {
        return this.upgradeConfirmResId;
    }

    public d getUpgradeConfirmTextStyle() {
        return this.upgradeConfirmTextStyle;
    }

    public int getUpgradeContentResId() {
        return this.upgradeContentResId;
    }

    public d getUpgradeContentTextStyle() {
        return this.upgradeContentTextStyle;
    }

    public int getUpgradeDialogResId() {
        return this.upgradeDialogResId;
    }

    public int getUpgradeHeaderResId() {
        return this.upgradeHeaderResId;
    }

    public d getUpgradeTitleTextStyle() {
        return this.upgradeTitleTextStyle;
    }

    public Class<? extends Activity>[] getWhitePages() {
        return this.whitePages;
    }

    public boolean isAutoDownloadWithWifi() {
        return this.autoDownloadWithWifi;
    }

    public boolean isAutoInstallAfterDownload() {
        return this.autoInstallAfterDownload;
    }

    public boolean isIgnoreUserRejectInUnlimitedCheck() {
        return this.ignoreUserRejectInUnlimitedCheck;
    }

    public boolean isPreEnvironment() {
        return this.isPreEnvironment;
    }

    public boolean isShowToast() {
        return this.showToast;
    }

    public boolean isUseCustomDownloadView() {
        return this.downloadViewClass != null;
    }

    public boolean isUseCustomRemindView() {
        return this.remindViewClass != null;
    }

    public boolean isValid() {
        return (TextUtils.isEmpty(this.appId) || TextUtils.isEmpty(this.appSecret)) ? false : true;
    }

    public void setAutoDownloadWithWifi(boolean z) {
        this.autoDownloadWithWifi = z;
    }

    public void setLogoId(Integer num) {
        this.logoId = num;
    }
}
