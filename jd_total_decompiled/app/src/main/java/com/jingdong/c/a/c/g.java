package com.jingdong.c.a.c;

import com.jingdong.common.utils.ShareUtil;

/* loaded from: classes7.dex */
public class g {
    public int a;
    public String b;

    /* renamed from: c  reason: collision with root package name */
    public String f12287c;
    public String d;

    public void a(String str) {
        String[] splitTransaction = ShareUtil.splitTransaction(str);
        this.f12287c = ShareUtil.urlDecode(splitTransaction[0]);
        this.d = splitTransaction[1];
    }
}
