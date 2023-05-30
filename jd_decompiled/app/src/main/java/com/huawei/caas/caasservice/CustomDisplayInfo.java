package com.huawei.caas.caasservice;

import android.text.TextUtils;

/* loaded from: classes12.dex */
public final class CustomDisplayInfo {
    String caasa;
    String caasb;
    String caasc;
    String caasd;

    /* loaded from: classes12.dex */
    public static class Builder {
        private String mCalleeDisplayInfo;
        private String mCallerAppName;
        private String mCallerDisplayInfo1;
        private String mCallerDisplayInfo2;

        public Builder(String str, String str2, String str3) {
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str.replaceAll("\\s+", ""))) {
                this.mCallerAppName = str;
            }
            this.mCallerDisplayInfo1 = str2;
            this.mCalleeDisplayInfo = str3;
        }

        private boolean isValidCustomDisplayInfo() {
            return isValidString(this.mCallerAppName) && isValidString(this.mCalleeDisplayInfo) && isValidString(this.mCallerDisplayInfo1) && isValidString(this.mCallerDisplayInfo2);
        }

        private boolean isValidString(String str) {
            return str == null || str.length() <= 40;
        }

        public CustomDisplayInfo build() {
            if (this.mCallerAppName == null || this.mCallerDisplayInfo1 == null || this.mCalleeDisplayInfo == null || !isValidCustomDisplayInfo()) {
                return null;
            }
            return new CustomDisplayInfo(this, (byte) 0);
        }

        public Builder setCallerDisplayInfo2(String str) {
            this.mCallerDisplayInfo2 = str;
            return this;
        }
    }

    private CustomDisplayInfo(Builder builder) {
        this.caasa = builder.mCallerAppName;
        this.caasb = builder.mCalleeDisplayInfo;
        this.caasc = builder.mCallerDisplayInfo2;
        this.caasd = builder.mCallerDisplayInfo1;
    }

    /* synthetic */ CustomDisplayInfo(Builder builder, byte b) {
        this(builder);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CustomDisplayInfo(String str) {
        this.caasa = str;
    }
}
