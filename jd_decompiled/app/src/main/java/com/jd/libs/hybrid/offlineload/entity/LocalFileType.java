package com.jd.libs.hybrid.offlineload.entity;

import androidx.annotation.Keep;

@Keep
/* loaded from: classes16.dex */
public enum LocalFileType {
    FILE_TYPE_UNKNOWN(-1),
    FILE_TYPE_HTML_PRELOAD(0),
    FILE_TYPE_PKG(1),
    FILE_TYPE_PKG_SHARED(2),
    FILE_TYPE_GLOBAL(3),
    FILE_TYPE_GLOBAL_BUILD_IN(4);
    
    private int type;

    /* renamed from: com.jd.libs.hybrid.offlineload.entity.LocalFileType$1  reason: invalid class name */
    /* loaded from: classes16.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[LocalFileType.values().length];
            a = iArr;
            try {
                iArr[LocalFileType.FILE_TYPE_PKG.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[LocalFileType.FILE_TYPE_PKG_SHARED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[LocalFileType.FILE_TYPE_HTML_PRELOAD.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[LocalFileType.FILE_TYPE_GLOBAL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[LocalFileType.FILE_TYPE_GLOBAL_BUILD_IN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    LocalFileType(int i2) {
        this.type = i2;
    }

    public String getConfigTypeDesc() {
        int i2 = AnonymousClass1.a[ordinal()];
        return i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? i2 != 5 ? "unknown" : "build-in global file" : "global file" : "pre download HTML" : "shared pack" : "business pack";
    }

    public String getConfigTypeDescCN() {
        int i2 = AnonymousClass1.a[ordinal()];
        return i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? i2 != 5 ? "\u672a\u77e5" : "\u5185\u7f6e\u5168\u5c40\u516c\u5171\u8d44\u6e90\u6587\u4ef6" : "\u5168\u5c40\u516c\u5171\u8d44\u6e90\u6587\u4ef6" : "\u9884\u4e0b\u8f7dHTML\u6587\u4ef6" : "\u516c\u5171\u79bb\u7ebf\u5305\u6587\u4ef6" : "\u9879\u76ee\u5185\u6587\u4ef6";
    }
}
