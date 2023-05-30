package com.tencent.open.log;

import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.tencent.connect.common.Constants;
import java.io.File;

/* loaded from: classes9.dex */
public class c {
    public static int a = 60;
    public static int b = 60;

    /* renamed from: c  reason: collision with root package name */
    public static String f17686c = "OpenSDK.Client.File.Tracer";
    public static String d;

    /* renamed from: e  reason: collision with root package name */
    public static String f17687e;

    /* renamed from: f  reason: collision with root package name */
    public static long f17688f;

    /* renamed from: g  reason: collision with root package name */
    public static int f17689g;

    /* renamed from: h  reason: collision with root package name */
    public static int f17690h;

    /* renamed from: i  reason: collision with root package name */
    public static int f17691i;

    /* renamed from: j  reason: collision with root package name */
    public static String f17692j;

    /* renamed from: k  reason: collision with root package name */
    public static String f17693k;

    /* renamed from: l  reason: collision with root package name */
    public static String f17694l;

    /* renamed from: m  reason: collision with root package name */
    public static int f17695m;

    /* renamed from: n  reason: collision with root package name */
    public static long f17696n;
    public static String o;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append("Tencent");
        String str = File.separator;
        sb.append(str);
        sb.append("msflogs");
        sb.append(str);
        sb.append("com");
        sb.append(str);
        sb.append("tencent");
        sb.append(str);
        sb.append("mobileqq");
        sb.append(str);
        d = sb.toString();
        f17687e = ".log";
        f17688f = 8388608L;
        f17689g = 262144;
        f17690h = 1024;
        f17691i = 10000;
        f17692j = "debug.file.blockcount";
        f17693k = "debug.file.keepperiod";
        f17694l = "debug.file.tracelevel";
        f17695m = 24;
        f17696n = Final.SEV_DAY;
        o = Constants.APP_SPECIFIC_ROOT + str + "logs";
    }
}
