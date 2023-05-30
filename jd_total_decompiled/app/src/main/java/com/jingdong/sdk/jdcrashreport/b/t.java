package com.jingdong.sdk.jdcrashreport.b;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;

/* loaded from: classes7.dex */
public class t {

    /* renamed from: c  reason: collision with root package name */
    private static b f14883c;
    private static String d;
    private static LinkedList<b> a = new LinkedList<>();
    private static LinkedList<String> b = new LinkedList<>();

    /* renamed from: e  reason: collision with root package name */
    private static LinkedList<String> f14884e = new LinkedList<>();

    /* renamed from: f  reason: collision with root package name */
    private static long f14885f = 0;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes7.dex */
    public static class b {
        String a;
        String b;

        /* renamed from: c  reason: collision with root package name */
        Intent f14886c;
        String d;

        /* renamed from: e  reason: collision with root package name */
        String f14887e;

        private b() {
            this.a = "";
            this.b = "";
            this.f14886c = null;
            this.f14887e = "activity";
        }

        private String a(Bundle bundle) {
            StringBuilder sb = new StringBuilder();
            if (bundle != null) {
                try {
                    sb.append("{");
                    for (String str : bundle.keySet()) {
                        sb.append("\"");
                        sb.append(str);
                        sb.append("\":");
                        Object obj = bundle.get(str);
                        if (obj != null && (obj instanceof Bundle)) {
                            sb.append(a((Bundle) obj));
                        } else {
                            sb.append("\"");
                            sb.append(String.valueOf(bundle.get(str)));
                            sb.append("\"");
                        }
                        sb.append(DYConstants.DY_REGEX_COMMA);
                    }
                    if (sb.lastIndexOf(DYConstants.DY_REGEX_COMMA) == sb.length() - 1) {
                        sb.setLength(sb.length() - 1);
                    }
                    sb.append("}");
                } catch (Throwable unused) {
                }
            }
            return sb.toString();
        }

        public String toString() {
            try {
                Intent intent = this.f14886c;
                Bundle extras = intent != null ? intent.getExtras() : null;
                if (extras != null) {
                    return this.a + " \u3010 " + this.b + " \u3011 {intent:" + a(extras) + "}";
                }
            } catch (Throwable unused) {
            }
            return this.a + " \u3010 " + this.b + " \u3011";
        }
    }

    private static String a(Fragment fragment) {
        return fragment.getClass().getSimpleName() + CartConstant.KEY_YB_INFO_LINK + fragment.getTag() + DYConstants.DY_REGEX_AT + Integer.toHexString(fragment.hashCode());
    }

    private static JSONArray b(List<?> list) {
        JSONArray jSONArray = new JSONArray();
        if (list != null && !list.isEmpty()) {
            Iterator<?> it = list.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next());
            }
        }
        return jSONArray;
    }

    public static void c() {
        f14885f = System.currentTimeMillis();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void d(Activity activity) {
        String str = activity.getClass().getSimpleName() + DYConstants.DY_REGEX_AT + Integer.toHexString(activity.hashCode());
        if (str.equals(d)) {
            return;
        }
        d = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void e(Activity activity, String str) {
        b bVar;
        String str2;
        r.b("JDCrashReport.PageInfoUtil", activity.getClass().getSimpleName() + DYConstants.DY_REGEX_AT + Integer.toHexString(activity.hashCode()) + LangUtils.SINGLE_SPACE + str);
        long currentTimeMillis = System.currentTimeMillis() - f14885f;
        String valueOf = String.valueOf(activity);
        if ("Start".equals(str)) {
            Iterator<b> descendingIterator = a.descendingIterator();
            while (true) {
                if (!descendingIterator.hasNext()) {
                    str2 = "";
                    break;
                }
                b next = descendingIterator.next();
                if (next.a.equals(valueOf)) {
                    str2 = next.b;
                    break;
                }
            }
            str = (!str2.contains("onStop") || str2.contains("onDestroy")) ? "onStart" : "onRestart";
        }
        if ("onCreate".equals(str)) {
            bVar = new b();
            bVar.a = String.valueOf(activity);
            bVar.b = str + "(" + currentTimeMillis + "ms)";
            try {
                bVar.f14886c = new Intent(activity.getIntent());
            } catch (Throwable unused) {
                bVar.f14886c = new Intent();
            }
        } else {
            b peekLast = a.peekLast();
            if (peekLast == null) {
                peekLast = new b();
            }
            if (peekLast.a.equals(valueOf)) {
                a.removeLast();
                peekLast.b = peekLast.b.concat(" > ").concat(str).concat("(" + currentTimeMillis + "ms)");
            } else {
                peekLast = new b();
                peekLast.a = valueOf;
                peekLast.b = str.concat("(" + currentTimeMillis + "ms)");
            }
            bVar = peekLast;
        }
        bVar.f14887e = "activity";
        bVar.d = "";
        if (a.size() >= 15) {
            a.poll();
        }
        a.offerLast(bVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void f(Fragment fragment, String str) {
        r.b("JDCrashReport.PageInfoUtil", a(fragment) + LangUtils.SINGLE_SPACE + str);
        long currentTimeMillis = System.currentTimeMillis() - f14885f;
        FragmentActivity activity = fragment.getActivity();
        if (activity == null) {
            return;
        }
        b peekLast = a.peekLast();
        if (peekLast == null) {
            r.b("JDCrashReport.PageInfoUtil", a(fragment) + LangUtils.SINGLE_SPACE + str);
        } else if (peekLast.a.equals(String.valueOf(activity))) {
            if (XView2Constants.FRAGMENT.equals(peekLast.f14887e) && Integer.toHexString(fragment.hashCode()).equals(peekLast.d)) {
                peekLast.b = peekLast.b.concat(CartConstant.KEY_YB_INFO_LINK + str + "(" + currentTimeMillis + "ms)");
            } else {
                peekLast.b = peekLast.b.concat(" > " + a(fragment) + CartConstant.KEY_YB_INFO_LINK + str + "(" + currentTimeMillis + "ms)");
            }
            peekLast.d = Integer.toHexString(fragment.hashCode());
            peekLast.f14887e = XView2Constants.FRAGMENT;
        } else {
            b bVar = new b();
            bVar.a = String.valueOf(activity);
            bVar.b = a(fragment) + CartConstant.KEY_YB_INFO_LINK + str + "(" + currentTimeMillis + "ms)";
            bVar.d = Integer.toHexString(fragment.hashCode());
            bVar.f14887e = XView2Constants.FRAGMENT;
            if (a.size() >= 15) {
                a.poll();
            }
            a.offerLast(bVar);
        }
    }

    public static void g(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (f14884e.size() >= 15) {
            f14884e.poll();
        }
        f14884e.offerLast(str);
    }

    public static String h() {
        StringBuilder sb = new StringBuilder();
        int size = a.size();
        for (int i2 = 0; i2 < size; i2++) {
            sb.append(a.get(i2).toString());
            sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        Iterator<String> it = b.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            sb.append(" \u2192 ");
        }
        if (sb.length() <= 3) {
            return "";
        }
        sb.setLength(sb.length() - 3);
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void i(Activity activity) {
        long currentTimeMillis = System.currentTimeMillis() - f14885f;
        b bVar = new b();
        f14883c = bVar;
        bVar.a = activity.getClass().getName();
        f14883c.b = "onResume(" + currentTimeMillis + "ms)";
        try {
            f14883c.f14886c = new Intent(activity.getIntent());
        } catch (Throwable unused) {
            f14883c.f14886c = new Intent();
        }
        if (b.size() >= 15) {
            b.poll();
        }
        b.offerLast(activity.getClass().getSimpleName() + DYConstants.DY_REGEX_AT + Integer.toHexString(activity.hashCode()));
    }

    public static String j() {
        return String.valueOf(b(f14884e));
    }

    public static String k() {
        String peekLast = f14884e.peekLast();
        return !TextUtils.isEmpty(peekLast) ? peekLast : "";
    }

    public static String l() {
        int indexOf;
        b bVar = f14883c;
        if (bVar != null) {
            return bVar.a;
        }
        b bVar2 = null;
        LinkedList<b> linkedList = a;
        if (linkedList != null && linkedList.size() > 0) {
            for (int size = a.size() - 1; size >= 0; size--) {
                bVar2 = a.get(size);
                if (!bVar2.b.contains("onStop") && !bVar2.b.contains("onDestroy")) {
                    break;
                }
            }
            if (bVar2 != null && (indexOf = bVar2.a.indexOf(DYConstants.DY_REGEX_AT)) > 0) {
                return bVar2.a.substring(0, indexOf);
            }
        }
        return "";
    }
}
