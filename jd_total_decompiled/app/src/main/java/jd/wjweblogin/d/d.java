package jd.wjweblogin.d;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import jd.wjweblogin.model.WJClientParams;

/* loaded from: classes11.dex */
public class d {
    public static String a = "ko.jd.com";
    public static String b = "divide.jd.com";

    /* renamed from: c  reason: collision with root package name */
    public static String f20060c = "jd.com";
    public static List<String> d = Collections.synchronizedList(new ArrayList());

    /* renamed from: e  reason: collision with root package name */
    private static WJClientParams f20061e;

    public static void a(WJClientParams wJClientParams) {
        f20061e = wJClientParams;
    }

    public static WJClientParams a() {
        return f20061e;
    }
}
