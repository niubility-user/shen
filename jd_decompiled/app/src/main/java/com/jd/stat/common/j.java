package com.jd.stat.common;

import android.content.Context;
import android.text.TextUtils;
import com.jd.stat.common.process.a;
import com.jingdong.jdsdk.constant.JshopConst;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

/* loaded from: classes18.dex */
public class j {

    /* loaded from: classes18.dex */
    public static class a {
        private d a;
        private c b;

        /* renamed from: c  reason: collision with root package name */
        private f f6996c;

        public a(Context context) {
            this.a = new d();
            this.b = new c();
            this.f6996c = new f(context.getPackageName());
        }

        private String d() {
            try {
                String a = NativeInfo.a();
                if (TextUtils.isEmpty(a)) {
                    return "c";
                }
                com.jd.stat.common.b.b.a("JDMob.MapCheckUtil", "checkEnvVersion:" + a);
                return !"ef00005f".equals(a) ? "d4000141".equals(a) ? "1" : "0" : "1";
            } catch (Throwable th) {
                com.jd.stat.common.b.b.a("JDMob.MapCheckUtil", th.toString());
                return "c";
            }
        }

        private String e() {
            try {
                InetAddress.getByName("127.0.0.1");
                new Socket("127.0.0.1", Integer.parseInt("27042"));
                return "1";
            } catch (IOException unused) {
                return "0";
            } catch (Throwable unused2) {
                return "c";
            }
        }

        private String c() {
            try {
                String prop = NativeInfo.getProp("ro.dalvik.vm.native.bridge");
                if (TextUtils.isEmpty(prop)) {
                    return "c";
                }
                if (!prop.contains("libhoudini.so")) {
                    if (!prop.contains("libnb.so")) {
                        return "0";
                    }
                }
                return "1";
            } catch (Throwable unused) {
                return "c";
            }
        }

        public String a() {
            return a(this.a) + c() + a(this.f6996c) + d();
        }

        public String b() {
            return a(this.b) + e();
        }

        private String a(a.InterfaceC0218a interfaceC0218a) {
            return interfaceC0218a == null ? com.jingdong.jdsdk.a.a.a : interfaceC0218a.a();
        }
    }

    /* loaded from: classes18.dex */
    private static abstract class b implements a.InterfaceC0218a {
        protected String a;

        private b() {
            this.a = com.jingdong.jdsdk.a.a.a;
        }

        @Override // com.jd.stat.common.process.a.InterfaceC0218a
        public String a() {
            return this.a;
        }

        @Override // com.jd.stat.common.process.a.InterfaceC0218a
        public void b() {
            if (com.jingdong.jdsdk.a.a.a.equals(this.a)) {
                this.a = "0";
            }
        }

        @Override // com.jd.stat.common.process.a.InterfaceC0218a
        public void a(Throwable th) {
            this.a = "c";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes18.dex */
    public static class c extends e {
        private c() {
            super();
        }

        @Override // com.jd.stat.common.j.e
        String c() {
            return "frida";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes18.dex */
    public static class d extends e {
        private d() {
            super();
        }

        @Override // com.jd.stat.common.j.e
        String c() {
            return "houdini";
        }
    }

    /* loaded from: classes18.dex */
    private static abstract class e extends b {
        private e() {
            super();
        }

        @Override // com.jd.stat.common.process.a.InterfaceC0218a
        public boolean a(String[] strArr) {
            String str = strArr[strArr.length - 1];
            if (str.endsWith(".so") && str.contains(c())) {
                this.a = "1";
                return true;
            }
            return false;
        }

        abstract String c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes18.dex */
    public static class f extends b {
        private String b;

        /* renamed from: c  reason: collision with root package name */
        private boolean f6997c;

        public f(String str) {
            super();
            this.f6997c = false;
            this.b = str;
        }

        @Override // com.jd.stat.common.process.a.InterfaceC0218a
        public boolean a(String[] strArr) {
            if (strArr[strArr.length - 1].endsWith("libjdJmaEncryptUtil.so")) {
                this.f6997c = true;
                if (strArr.length >= 2) {
                    String str = strArr[1];
                    if (str.length() == 4 && str.contains(JshopConst.JSHOP_PROMOTIO_X)) {
                        this.a = "1";
                        return true;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }

        @Override // com.jd.stat.common.j.b, com.jd.stat.common.process.a.InterfaceC0218a
        public void b() {
            if (!this.f6997c) {
                this.a = "c";
            } else if (com.jingdong.jdsdk.a.a.a.equals(this.a)) {
                this.a = "0";
            }
        }
    }

    public static void a(a aVar) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(aVar.b);
        arrayList.add(aVar.a);
        arrayList.add(aVar.f6996c);
        com.jd.stat.common.process.a.a(arrayList);
    }

    public static String b(Context context) {
        a aVar = new a(context);
        a(aVar);
        return aVar.b();
    }

    public static String a(Context context) {
        a aVar = new a(context);
        a(aVar);
        return aVar.a();
    }
}
