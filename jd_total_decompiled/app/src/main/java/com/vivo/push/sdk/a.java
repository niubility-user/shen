package com.vivo.push.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import com.jingdong.sdk.platform.business.personal.R2;
import com.vivo.push.b.x;
import com.vivo.push.c.d;
import com.vivo.push.e;
import com.vivo.push.q;
import com.vivo.push.util.ContextDelegate;
import com.vivo.push.util.p;
import com.vivo.push.util.t;
import com.vivo.push.util.u;
import com.vivo.push.util.z;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes11.dex */
public final class a extends q {

    /* renamed from: c */
    private static a f18292c;

    /* renamed from: e */
    private static final List<Integer> f18293e = Arrays.asList(3);
    private String d;

    /* renamed from: f */
    private String f18294f = "";

    private a() {
    }

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            if (f18292c == null) {
                f18292c = new a();
            }
            aVar = f18292c;
        }
        return aVar;
    }

    private boolean c(Intent intent) {
        if (Build.VERSION.SDK_INT < 18) {
            return true;
        }
        String c2 = z.c(this.a, "com.vivo.pushservice");
        p.d("CommandWorker", " \u914d\u7f6e\u7684\u9a8c\u7b7e\u53c2\u6570 = ".concat(String.valueOf(c2)));
        if (TextUtils.equals(c2, "1")) {
            String stringExtra = intent.getStringExtra("security_avoid_pull_rsa");
            String stringExtra2 = intent.getStringExtra("security_avoid_rsa_public_key");
            if (!TextUtils.isEmpty(stringExtra) && !TextUtils.isEmpty(stringExtra2)) {
                try {
                    if (d.a(this.a).a().a("com.vivo.pushservice".getBytes("UTF-8"), u.a(stringExtra2), Base64.decode(stringExtra, 2))) {
                        p.d("CommandWorker", " RSA\u9a8c\u7b7e\u901a\u8fc7  ");
                        return true;
                    }
                } catch (Exception e2) {
                    p.a("CommandWorker", "checkIntentIsSecurity Exception: " + e2.getMessage());
                }
                p.d("CommandWorker", " RSA\u9a8c\u7b7e \u4e0d\u901a\u8fc7  ");
                return false;
            }
            p.a("CommandWorker", "!decrypt.equals, so securityContent == " + stringExtra + " or publickKey isempty ");
            return false;
        }
        return true;
    }

    private int d(Intent intent) {
        String stringExtra;
        if (!TextUtils.isEmpty(this.f18294f) && this.f18294f.contains("CommandService")) {
            if (!(intent != null && b(intent) && c(intent))) {
                p.a("CommandWorker", " !checkIntentIsSecurity(intent)");
                return R2.attr.transitionEasing;
            }
        }
        String packageName = this.a.getPackageName();
        try {
            stringExtra = intent.getStringExtra("command_type");
        } catch (Exception e2) {
            p.a("CommandWorker", e2);
        }
        if (!TextUtils.isEmpty(stringExtra) && stringExtra.equals("reflect_receiver")) {
            int intExtra = intent.getIntExtra("command", -1);
            if (intExtra < 0) {
                intExtra = intent.getIntExtra("method", -1);
            }
            if (f18293e.contains(Integer.valueOf(intExtra)) && t.c(this.a, packageName) && !t.c(this.a)) {
                p.a("CommandWorker", "METHOD_ON_MESSAGE is not support");
                return R2.attr.transitionPathRotate;
            }
            String action = intent.getAction();
            if (TextUtils.isEmpty(this.d)) {
                String a = a(this.a, packageName, action);
                this.d = a;
                if (TextUtils.isEmpty(a)) {
                    p.d("CommandWorker", " reflectReceiver error: receiver for: " + action + " not found, package: " + packageName);
                    intent.setPackage(packageName);
                    this.a.sendBroadcast(intent);
                    return R2.attr.transitionFlags;
                }
            }
            return 0;
        }
        p.a("CommandWorker", "commandTypeStr is not satisfy == ".concat(String.valueOf(stringExtra)));
        return R2.attr.transitionEasing;
    }

    public final void b() {
        this.d = null;
    }

    @Override // com.vivo.push.q
    public final void b(Message message) {
        Context context;
        Intent intent = (Intent) message.obj;
        if (intent != null && (context = this.a) != null) {
            String packageName = context.getPackageName();
            int d = d(intent);
            if (d > 0) {
                x xVar = new x(d);
                HashMap<String, String> hashMap = new HashMap<>();
                if (intent != null) {
                    Bundle extras = intent.getExtras();
                    hashMap.put("messageID", String.valueOf(extras != null ? extras.getLong("notify_id", 404000044642424832L) : 404000044642424832L));
                }
                String b = z.b(this.a, packageName);
                if (!TextUtils.isEmpty(b)) {
                    hashMap.put("remoteAppId", b);
                }
                xVar.a(hashMap);
                e.a().a(xVar);
                return;
            }
            try {
                Class<?> cls = Class.forName(this.d);
                Object newInstance = cls.getConstructor(new Class[0]).newInstance(new Object[0]);
                Method method = cls.getMethod("onReceive", Context.class, Intent.class);
                intent.setClassName(packageName, this.d);
                method.invoke(newInstance, ContextDelegate.getContext(this.a).getApplicationContext(), intent);
                return;
            } catch (Exception e2) {
                p.b("CommandWorker", "reflect e: ", e2);
                return;
            }
        }
        p.d("CommandWorker", " handleMessage error: intent : " + intent + ", mContext: " + this.a);
    }

    public final void a(String str) {
        this.f18294f = str;
    }

    public final void a(Intent intent) {
        if (intent != null && this.a != null) {
            Message obtain = Message.obtain();
            obtain.obj = intent;
            a(obtain);
            return;
        }
        p.d("CommandWorker", " sendMessage error: intent : " + intent + ", mContext: " + this.a);
    }

    private static String a(Context context, String str, String str2) {
        List<ResolveInfo> queryBroadcastReceivers;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        Intent intent = new Intent(str2);
        intent.setPackage(str);
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null || (queryBroadcastReceivers = packageManager.queryBroadcastReceivers(intent, 64)) == null || queryBroadcastReceivers.size() <= 0) {
                return null;
            }
            return queryBroadcastReceivers.get(0).activityInfo.name;
        } catch (Exception e2) {
            p.a("CommandWorker", "error  " + e2.getMessage());
            return null;
        }
    }

    private boolean b(Intent intent) {
        String stringExtra = intent.getStringExtra("security_avoid_pull");
        if (!TextUtils.isEmpty(stringExtra)) {
            try {
                String b = com.vivo.push.util.a.a(this.a).b(stringExtra);
                if ("com.vivo.pushservice".equals(b)) {
                    return true;
                }
                p.a("CommandWorker", "!decrypt.equals, so decrypt == ".concat(String.valueOf(b)));
                return false;
            } catch (Exception e2) {
                p.a("CommandWorker", "checkIntentIsSecurity Exception: " + e2.getMessage());
                return false;
            }
        }
        p.a("CommandWorker", "checkIntentIsSecurityTextUtils.isEmpty");
        return true;
    }
}