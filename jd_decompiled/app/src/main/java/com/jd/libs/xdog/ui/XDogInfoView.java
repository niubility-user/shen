package com.jd.libs.xdog.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jd.libs.xdog.R;
import java.util.Map;

/* loaded from: classes16.dex */
public class XDogInfoView extends RelativeLayout {

    /* renamed from: g */
    private TextView f6209g;

    /* renamed from: h */
    private double f6210h;

    /* renamed from: i */
    private double f6211i;

    /* renamed from: j */
    private final Handler f6212j;

    /* renamed from: k */
    private final StringBuilder f6213k;

    /* renamed from: l */
    private String f6214l;

    public XDogInfoView(Context context, String str) {
        super(context);
        this.f6212j = new Handler(Looper.getMainLooper());
        this.f6213k = new StringBuilder();
        this.f6214l = str;
        b();
    }

    private synchronized String a(Map<String, Map<String, String>> map) {
        if (map.containsKey(this.f6214l)) {
            Map<String, String> map2 = map.get(this.f6214l);
            if (map2 != null) {
                this.f6213k.setLength(0);
                StringBuilder sb = this.f6213k;
                sb.append("\u57fa\u7840\u6570\u636e");
                sb.append("\r\n");
                if (TextUtils.isEmpty(map2.get("HTML\u9884\u4e0b\u8f7d"))) {
                    map2.put("HTML\u9884\u4e0b\u8f7d", "no");
                }
                if (TextUtils.isEmpty(map2.get("\u547d\u4e2d\u79bb\u7ebf\u5305"))) {
                    map2.put("\u547d\u4e2d\u79bb\u7ebf\u5305", "no");
                }
                if (TextUtils.isEmpty(map2.get("\u63a5\u53e3\u9884\u52a0\u8f7d"))) {
                    map2.put("\u63a5\u53e3\u9884\u52a0\u8f7d", "no");
                }
                for (String str : map2.keySet()) {
                    if (!"URL\u5404\u9636\u6bb5\u8017\u65f6".equals(str) && !"\u5185\u5b58\u72b6\u6001".equals(str)) {
                        StringBuilder sb2 = this.f6213k;
                        sb2.append(str);
                        sb2.append(": ");
                        sb2.append(map2.get(str));
                        sb2.append("\r\n");
                    }
                }
                if (map2.containsKey("URL\u5404\u9636\u6bb5\u8017\u65f6")) {
                    StringBuilder sb3 = this.f6213k;
                    sb3.append("\r\n");
                    sb3.append("URL\u5404\u9636\u6bb5\u8017\u65f6");
                    sb3.append(": ");
                    sb3.append(map2.get("URL\u5404\u9636\u6bb5\u8017\u65f6"));
                    sb3.append("\r\n");
                }
                if (map2.containsKey("\u5185\u5b58\u72b6\u6001")) {
                    StringBuilder sb4 = this.f6213k;
                    sb4.append("\r\n");
                    sb4.append("\u5185\u5b58\u72b6\u6001");
                    sb4.append(": ");
                    sb4.append(map2.get("\u5185\u5b58\u72b6\u6001"));
                    sb4.append("\r\n");
                }
            }
            return this.f6213k.toString();
        }
        return null;
    }

    /* renamed from: c */
    public /* synthetic */ boolean d(View view, MotionEvent motionEvent) {
        double rawX = motionEvent.getRawX();
        double rawY = motionEvent.getRawY();
        if (motionEvent.getAction() == 0) {
            this.f6210h = rawX;
            this.f6211i = rawY;
            return true;
        } else if (motionEvent.getAction() == 2) {
            double d = this.f6210h;
            Double.isNaN(rawX);
            double d2 = this.f6211i;
            Double.isNaN(rawY);
            TextView textView = this.f6209g;
            double translationX = textView.getTranslationX();
            Double.isNaN(translationX);
            textView.setTranslationX((float) (translationX + (rawX - d)));
            TextView textView2 = this.f6209g;
            double translationY = textView2.getTranslationY();
            Double.isNaN(translationY);
            textView2.setTranslationY((float) (translationY + (rawY - d2)));
            this.f6210h = rawX;
            this.f6211i = rawY;
            return true;
        } else {
            return true;
        }
    }

    /* renamed from: e */
    public /* synthetic */ void f(Map map) {
        String a = a(map);
        if (TextUtils.isEmpty(a)) {
            return;
        }
        if (this.f6209g.getVisibility() == 8) {
            this.f6209g.setVisibility(0);
        }
        this.f6209g.setText(a);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public void b() {
        View.inflate(getContext(), R.layout.xdog_hybrid_info_view, this);
        TextView textView = (TextView) findViewById(R.id.hybrid_info_tv);
        this.f6209g = textView;
        textView.setVisibility(8);
        String a = a(com.jd.libs.xdog.f.b().c());
        if (!TextUtils.isEmpty(a)) {
            this.f6209g.setVisibility(0);
            this.f6209g.setText(a);
        }
        this.f6209g.setOnTouchListener(new View.OnTouchListener() { // from class: com.jd.libs.xdog.ui.a
            {
                XDogInfoView.this = this;
            }

            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return XDogInfoView.this.d(view, motionEvent);
            }
        });
    }

    public void g(final Map<String, Map<String, String>> map) {
        Runnable runnable = new Runnable() { // from class: com.jd.libs.xdog.ui.b
            {
                XDogInfoView.this = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                XDogInfoView.this.f(map);
            }
        };
        if (Looper.myLooper() != Looper.getMainLooper()) {
            this.f6212j.post(runnable);
        } else {
            runnable.run();
        }
    }
}
