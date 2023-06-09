package com.tencent.open;

import android.net.Uri;
import android.webkit.WebView;
import com.tencent.open.log.SLog;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttTopic;

/* loaded from: classes9.dex */
public class b {
    protected HashMap<String, C0808b> a = new HashMap<>();

    /* renamed from: com.tencent.open.b$b  reason: collision with other inner class name */
    /* loaded from: classes9.dex */
    public static class C0808b {
        /* JADX WARN: Code restructure failed: missing block: B:44:0x013c, code lost:
            r13.a((java.lang.Object) null);
         */
        /* JADX WARN: Code restructure failed: missing block: B:45:0x013f, code lost:
            return;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void call(String str, List<String> list, a aVar) {
            Method method;
            Object invoke;
            Method[] declaredMethods = getClass().getDeclaredMethods();
            int length = declaredMethods.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    method = null;
                    break;
                }
                method = declaredMethods[i2];
                if (method.getName().equals(str) && method.getParameterTypes().length == list.size()) {
                    break;
                }
                i2++;
            }
            if (method == null) {
                if (aVar != null) {
                    aVar.a();
                    return;
                }
                return;
            }
            try {
                int size = list.size();
                if (size != 0) {
                    invoke = size != 1 ? size != 2 ? size != 3 ? size != 4 ? size != 5 ? method.invoke(this, list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5)) : method.invoke(this, list.get(0), list.get(1), list.get(2), list.get(3), list.get(4)) : method.invoke(this, list.get(0), list.get(1), list.get(2), list.get(3)) : method.invoke(this, list.get(0), list.get(1), list.get(2)) : method.invoke(this, list.get(0), list.get(1)) : method.invoke(this, list.get(0));
                } else {
                    invoke = method.invoke(this, new Object[0]);
                }
                Class<?> returnType = method.getReturnType();
                SLog.d("openSDK_LOG.JsBridge", "-->call, result: " + invoke + " | ReturnType: " + returnType.getName());
                if (!"void".equals(returnType.getName()) && returnType != Void.class) {
                    if (aVar == null || !customCallback()) {
                        return;
                    }
                    aVar.a(invoke != null ? invoke.toString() : null);
                }
            } catch (Exception e2) {
                SLog.e("openSDK_LOG.JsBridge", "-->handler call mehtod ex. targetMethod: " + method, e2);
                if (aVar != null) {
                    aVar.a();
                }
            }
        }

        public boolean customCallback() {
            return false;
        }
    }

    public void a(C0808b c0808b, String str) {
        this.a.put(str, c0808b);
    }

    public void a(String str, String str2, List<String> list, a aVar) {
        SLog.v("openSDK_LOG.JsBridge", "getResult---objName = " + str + " methodName = " + str2);
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            try {
                list.set(i2, URLDecoder.decode(list.get(i2), "UTF-8"));
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            }
        }
        C0808b c0808b = this.a.get(str);
        if (c0808b != null) {
            SLog.d("openSDK_LOG.JsBridge", "call----");
            c0808b.call(str2, list, aVar);
            return;
        }
        SLog.d("openSDK_LOG.JsBridge", "not call----objName NOT FIND");
        if (aVar != null) {
            aVar.a();
        }
    }

    /* loaded from: classes9.dex */
    public static class a {
        protected WeakReference<WebView> a;
        protected long b;

        /* renamed from: c  reason: collision with root package name */
        protected String f17640c;

        public a(WebView webView, long j2, String str) {
            this.a = new WeakReference<>(webView);
            this.b = j2;
            this.f17640c = str;
        }

        public void a(Object obj) {
            String obj2;
            WebView webView = this.a.get();
            if (webView == null) {
                return;
            }
            if (obj instanceof String) {
                obj2 = "'" + ((Object) ((String) obj).replace("\\", "\\\\").replace("'", "\\'")) + "'";
            } else if (!(obj instanceof Number) && !(obj instanceof Long) && !(obj instanceof Integer) && !(obj instanceof Double) && !(obj instanceof Float)) {
                obj2 = obj instanceof Boolean ? obj.toString() : "'undefined'";
            } else {
                obj2 = obj.toString();
            }
            webView.loadUrl("javascript:window.JsBridge&&JsBridge.callback(" + this.b + ",{'r':0,'result':" + obj2 + "});");
        }

        public void a() {
            WebView webView = this.a.get();
            if (webView == null) {
                return;
            }
            webView.loadUrl("javascript:window.JsBridge&&JsBridge.callback(" + this.b + ",{'r':1,'result':'no such method'})");
        }

        public void a(String str) {
            WebView webView = this.a.get();
            if (webView != null) {
                webView.loadUrl("javascript:" + str);
            }
        }
    }

    public boolean a(WebView webView, String str) {
        SLog.v("openSDK_LOG.JsBridge", "-->canHandleUrl---url = " + str);
        if (str != null && Uri.parse(str).getScheme().equals("jsbridge")) {
            ArrayList arrayList = new ArrayList(Arrays.asList((str + MqttTopic.MULTI_LEVEL_WILDCARD_PATTERN).split("/")));
            if (arrayList.size() < 6) {
                return false;
            }
            List<String> subList = arrayList.subList(4, arrayList.size() - 1);
            a aVar = new a(webView, 4L, str);
            webView.getUrl();
            a((String) arrayList.get(2), (String) arrayList.get(3), subList, aVar);
            return true;
        }
        return false;
    }
}
