package com.huawei.hms.common.size;

import com.huawei.hms.common.internal.Objects;
import com.jingdong.jdsdk.constant.JshopConst;
import com.tencent.smtt.sdk.ProxyConfig;

/* loaded from: classes12.dex */
public class Size {
    private final int a;
    private final int b;

    public Size(int i2, int i3) {
        this.a = i2;
        this.b = i3;
    }

    public static Size parseSize(String str) {
        try {
            int indexOf = str.indexOf(JshopConst.JSHOP_PROMOTIO_X);
            if (indexOf < 0) {
                indexOf = str.indexOf(ProxyConfig.MATCH_ALL_SCHEMES);
            }
            return new Size(Integer.parseInt(str.substring(0, indexOf)), Integer.parseInt(str.substring(indexOf + 1)));
        } catch (Exception unused) {
            throw new IllegalArgumentException("Size parses failed");
        }
    }

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof Size) {
            Size size = (Size) obj;
            return this.a == size.a && this.b == size.b;
        }
        return false;
    }

    public final int getHeight() {
        return this.b;
    }

    public final int getWidth() {
        return this.a;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(getWidth()), Integer.valueOf(getHeight()));
    }

    public final String toString() {
        return "Width is " + this.a + " Height is " + this.b;
    }
}
