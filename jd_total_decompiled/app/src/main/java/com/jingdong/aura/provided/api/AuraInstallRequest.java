package com.jingdong.aura.provided.api;

import android.text.TextUtils;

/* loaded from: classes4.dex */
public class AuraInstallRequest {
    public static final String AURA_INSTALL_STYLE1 = "aura_install_style1";
    public static final String AURA_INSTALL_STYLE2 = "aura_install_style2";
    public static final int DOWNLOAD_TYPE_MANUAL = 0;
    public static final int DOWNLOAD_TYPE_SLINCE_FORCE = 1;
    public static final int DOWNLOAD_TYPE_SLINCE_WIFI_ONLY = 2;
    private String auraInstallStyle;
    private String bundleName;
    private int downloadType;
    private IInstallListener installListener;

    /* loaded from: classes4.dex */
    public static class Builder {
        private String innerAuraInstallStyle;
        private String innerBundleName;
        private int downloadType = 0;
        private IInstallListener innerInstallListener = new InstallLinstenerImpl();

        public Builder addOnFailerListener(IOnFailerListener iOnFailerListener) {
            this.innerInstallListener.addOnFailerListener(iOnFailerListener);
            return this;
        }

        public Builder addOnSuccessListener(IOnSuccessListener iOnSuccessListener) {
            this.innerInstallListener.addOnSuccessListener(iOnSuccessListener);
            return this;
        }

        public AuraInstallRequest build() {
            return new AuraInstallRequest(this);
        }

        public Builder setAuraInstallStyle(String str) {
            this.innerAuraInstallStyle = str;
            return this;
        }

        public Builder setBundleName(String str) {
            this.innerBundleName = str;
            return this;
        }

        public Builder setDownloadType(int i2) {
            this.downloadType = i2;
            return this;
        }
    }

    /* loaded from: classes4.dex */
    public interface IInstallListener {
        void addOnFailerListener(IOnFailerListener iOnFailerListener);

        void addOnSuccessListener(IOnSuccessListener iOnSuccessListener);

        IOnFailerListener getOnFailerListener();

        IOnSuccessListener getOnSuccessListener();
    }

    /* loaded from: classes4.dex */
    public interface IOnFailerListener {
        void onFailure(Exception exc);
    }

    /* loaded from: classes4.dex */
    public interface IOnSuccessListener {
        void onSuccess();
    }

    /* loaded from: classes4.dex */
    static class InstallLinstenerImpl implements IInstallListener {
        IOnFailerListener onFailerListener;
        IOnSuccessListener onSuccessListener;

        InstallLinstenerImpl() {
        }

        @Override // com.jingdong.aura.provided.api.AuraInstallRequest.IInstallListener
        public void addOnFailerListener(IOnFailerListener iOnFailerListener) {
            this.onFailerListener = iOnFailerListener;
        }

        @Override // com.jingdong.aura.provided.api.AuraInstallRequest.IInstallListener
        public void addOnSuccessListener(IOnSuccessListener iOnSuccessListener) {
            this.onSuccessListener = iOnSuccessListener;
        }

        @Override // com.jingdong.aura.provided.api.AuraInstallRequest.IInstallListener
        public IOnFailerListener getOnFailerListener() {
            return this.onFailerListener;
        }

        @Override // com.jingdong.aura.provided.api.AuraInstallRequest.IInstallListener
        public IOnSuccessListener getOnSuccessListener() {
            return this.onSuccessListener;
        }
    }

    public String getAuraInstallStyle() {
        return this.auraInstallStyle;
    }

    public String getBundleName() {
        return this.bundleName;
    }

    public int getDownloadType() {
        return this.downloadType;
    }

    public IInstallListener getInstallListener() {
        return this.installListener;
    }

    private AuraInstallRequest(Builder builder) {
        this.downloadType = 0;
        this.bundleName = builder.innerBundleName;
        this.auraInstallStyle = builder.innerAuraInstallStyle;
        this.installListener = builder.innerInstallListener;
        this.downloadType = builder.downloadType;
        if (TextUtils.isEmpty(this.bundleName)) {
            throw new IllegalArgumentException("bundleName is empty !!");
        }
    }
}
