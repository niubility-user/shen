package com.jd.lib.cashier.sdk.core.utils;

import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.share.IShare;
import com.jd.cashier.app.jdlibcutter.protocol.share.ShareKey;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes14.dex */
public class b0 {

    /* loaded from: classes14.dex */
    public static class a {
        private String a;
        private String b;

        /* renamed from: c  reason: collision with root package name */
        private String f3091c;
        private String d;

        /* renamed from: e  reason: collision with root package name */
        private String f3092e;

        public a a(String str) {
            this.b = str;
            return this;
        }

        public a b(String str) {
            this.d = str;
            return this;
        }

        public a c(String str) {
            this.f3091c = str;
            return this;
        }

        public a d(String str) {
            this.a = str;
            return this;
        }

        public a e(String str) {
            this.f3092e = str;
            return this;
        }

        public Map<String, String> f() {
            HashMap hashMap = new HashMap();
            if (!TextUtils.isEmpty(this.b)) {
                hashMap.put(ShareKey.shareChannel, this.b);
            }
            if (!TextUtils.isEmpty(this.a)) {
                hashMap.put("title", this.a);
            }
            if (!TextUtils.isEmpty(this.f3091c)) {
                hashMap.put("description", this.f3091c);
            }
            if (!TextUtils.isEmpty(this.d)) {
                hashMap.put("imageUrl", this.d);
            }
            if (!TextUtils.isEmpty(this.f3092e)) {
                hashMap.put("shareUrl", this.f3092e);
            }
            return hashMap;
        }
    }

    public static void a(FragmentActivity fragmentActivity, Map<String, String> map) {
        IShare share;
        if (!g0.a(fragmentActivity) || (share = DependInitializer.getShare()) == null) {
            return;
        }
        share.doShare(fragmentActivity, map);
    }
}
