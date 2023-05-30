package com.jingdong.amon.router.module;

/* loaded from: classes18.dex */
public class RouteMeta {
    public final Class<?> annotatedClass;
    public final String constructor;
    public String host;
    public final Class<?>[] interfaceClasses;
    public final String path;
    public String scheme;
    public final boolean singleton;

    public RouteMeta(String str, String str2, String str3, Class<?> cls, boolean z, String str4, Class<?>... clsArr) {
        this.scheme = str;
        this.host = str2;
        this.path = str3;
        this.annotatedClass = cls;
        this.singleton = z;
        this.constructor = str4;
        this.interfaceClasses = clsArr;
    }

    public RouteMeta(String str, String str2, String str3, Class<?> cls, boolean z, Class<?>... clsArr) {
        this(str, str2, str3, cls, z, "", clsArr);
    }
}
