package com.jingdong.c.a.c;

import android.content.Intent;

/* loaded from: classes7.dex */
public class e {
    public int a;
    public long b;

    /* renamed from: c  reason: collision with root package name */
    public String f12273c;
    public String d;

    /* renamed from: e  reason: collision with root package name */
    public String f12274e;

    /* renamed from: f  reason: collision with root package name */
    public String f12275f;

    public void a(Intent intent) {
        if (intent == null) {
            return;
        }
        this.a = intent.getIntExtra("ruleType", 0);
        this.b = intent.getLongExtra("activityId", 0L);
        if (intent.hasExtra("bizId")) {
            this.d = intent.getStringExtra("bizId");
        }
        if (intent.hasExtra("sourceType")) {
            this.f12273c = intent.getStringExtra("sourceType");
        }
        if (intent.hasExtra("ruleContent")) {
            this.f12275f = intent.getStringExtra("ruleContent");
        }
    }
}
