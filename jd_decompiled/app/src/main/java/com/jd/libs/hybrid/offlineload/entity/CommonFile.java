package com.jd.libs.hybrid.offlineload.entity;

import androidx.annotation.Keep;
import java.util.Map;

@Keep
/* loaded from: classes16.dex */
public class CommonFile {
    private boolean available;
    private boolean canMatchImg;
    private String filePath;
    private Map<String, String> headerParams;
    private String url;
    private int version;

    /* loaded from: classes16.dex */
    public static class Builder {
        String a;
        String b;

        /* renamed from: c  reason: collision with root package name */
        int f5974c;
        boolean d;

        /* renamed from: e  reason: collision with root package name */
        Map<String, String> f5975e;

        /* renamed from: f  reason: collision with root package name */
        boolean f5976f;

        public CommonFile build() {
            return new CommonFile(this);
        }

        public Builder setAvailable(boolean z) {
            this.d = z;
            return this;
        }

        public Builder setCanMatchImg(boolean z) {
            this.f5976f = z;
            return this;
        }

        public Builder setFilePath(String str) {
            this.b = str;
            return this;
        }

        public Builder setHeaderParams(Map<String, String> map) {
            this.f5975e = map;
            return this;
        }

        public Builder setUrl(String str) {
            this.a = str;
            return this;
        }

        public Builder setVersion(int i2) {
            this.f5974c = i2;
            return this;
        }
    }

    public CommonFile(Builder builder) {
        if (builder != null) {
            this.url = builder.a;
            this.filePath = builder.b;
            this.version = builder.f5974c;
            this.available = builder.d;
            this.headerParams = builder.f5975e;
            this.canMatchImg = builder.f5976f;
        }
    }

    public String getFilePath() {
        return this.filePath;
    }

    public Map<String, String> getHeaderParams() {
        return this.headerParams;
    }

    public String getUrl() {
        return this.url;
    }

    public int getVersion() {
        return this.version;
    }

    public boolean isAvailable() {
        return this.available;
    }

    public boolean isCanMatchImg() {
        return this.canMatchImg;
    }
}
