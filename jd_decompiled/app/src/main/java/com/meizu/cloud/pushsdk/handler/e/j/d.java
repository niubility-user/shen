package com.meizu.cloud.pushsdk.handler.e.j;

import android.text.TextUtils;
import com.meizu.cloud.pushsdk.constants.PushConstants;

/* loaded from: classes14.dex */
public class d {
    private String a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private String f15963c;
    private String d;

    /* loaded from: classes14.dex */
    public static class a {
        private String a;
        private String b;

        /* renamed from: c  reason: collision with root package name */
        private String f15964c;
        private String d;

        public a a(String str) {
            this.d = str;
            return this;
        }

        public d b() {
            return new d(this);
        }

        public a d(String str) {
            this.f15964c = str;
            return this;
        }

        public a f(String str) {
            this.b = str;
            return this;
        }

        public a h(String str) {
            this.a = str;
            return this;
        }
    }

    public d() {
    }

    public d(a aVar) {
        this.a = !TextUtils.isEmpty(aVar.a) ? aVar.a : "";
        this.b = !TextUtils.isEmpty(aVar.b) ? aVar.b : "";
        this.f15963c = !TextUtils.isEmpty(aVar.f15964c) ? aVar.f15964c : "";
        this.d = TextUtils.isEmpty(aVar.d) ? "" : aVar.d;
    }

    public static a a() {
        return new a();
    }

    public String b() {
        return this.d;
    }

    public String c() {
        return this.f15963c;
    }

    public String d() {
        return this.b;
    }

    public String e() {
        return this.a;
    }

    public String f() {
        com.meizu.cloud.pushsdk.f.b.c cVar = new com.meizu.cloud.pushsdk.f.b.c();
        cVar.a(PushConstants.TASK_ID, this.a);
        cVar.a(PushConstants.SEQ_ID, this.b);
        cVar.a(PushConstants.PUSH_TIMESTAMP, this.f15963c);
        cVar.a(PushConstants.DEVICE_ID, this.d);
        return cVar.toString();
    }
}
