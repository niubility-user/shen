package com.jd.libs.xdog.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.jd.libs.xdog.R;
import com.jd.xbridge.XBridgeManager;

/* loaded from: classes16.dex */
public class XDogPanelView extends RelativeLayout {

    /* renamed from: g */
    private XDogWebView f6215g;

    /* renamed from: h */
    private Button f6216h;

    /* renamed from: i */
    private View f6217i;

    /* renamed from: j */
    private double f6218j;

    /* renamed from: k */
    private double f6219k;

    /* renamed from: l */
    private double f6220l;

    /* renamed from: m */
    private double f6221m;

    /* renamed from: n */
    private final Handler f6222n;
    private Boolean o;

    /* loaded from: classes16.dex */
    public class a extends WebViewClient {
        a() {
            XDogPanelView.this = r1;
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            if (XDogPanelView.this.o.booleanValue()) {
                return;
            }
            com.jd.libs.xdog.f.b().q(XDogPanelView.this.f6215g);
            XDogPanelView.this.o = Boolean.TRUE;
            com.jd.libs.xdog.f.x = true;
        }
    }

    /* loaded from: classes16.dex */
    public class b extends WebChromeClient {
        b(XDogPanelView xDogPanelView) {
        }

        @Override // android.webkit.WebChromeClient
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            Log.println(3, "XDogCyber", consoleMessage.message());
            return super.onConsoleMessage(consoleMessage);
        }
    }

    public XDogPanelView(Context context) {
        super(context);
        this.f6222n = new Handler(Looper.getMainLooper());
        this.o = Boolean.FALSE;
        f();
    }

    private void d() {
        if (this.f6215g.getVisibility() == 0) {
            this.f6222n.post(new Runnable() { // from class: com.jd.libs.xdog.ui.e
                {
                    XDogPanelView.this = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    XDogPanelView.this.h();
                }
            });
        }
    }

    /* renamed from: g */
    public /* synthetic */ void h() {
        this.f6215g.setVisibility(8);
        this.f6215g.setAnimation(com.jd.libs.xdog.g.b.b());
        this.f6216h.setVisibility(0);
        this.f6217i.setVisibility(8);
    }

    /* renamed from: i */
    public /* synthetic */ void j(String str) {
        this.f6215g.loadUrl(str);
    }

    /* renamed from: k */
    public /* synthetic */ void l(View view) {
        d();
    }

    /* renamed from: m */
    public /* synthetic */ boolean n(View view, MotionEvent motionEvent) {
        double rawX = motionEvent.getRawX();
        double rawY = motionEvent.getRawY();
        int action = motionEvent.getAction();
        if (action == 0) {
            this.f6220l = rawX;
            this.f6221m = rawY;
            this.f6218j = rawX;
            this.f6219k = rawY;
        } else if (action != 1) {
            if (action == 2) {
                double d = this.f6220l;
                Double.isNaN(rawX);
                double d2 = this.f6221m;
                Double.isNaN(rawY);
                Button button = this.f6216h;
                double translationX = button.getTranslationX();
                Double.isNaN(translationX);
                button.setTranslationX((float) (translationX + (rawX - d)));
                Button button2 = this.f6216h;
                double translationY = button2.getTranslationY();
                Double.isNaN(translationY);
                button2.setTranslationY((float) (translationY + (rawY - d2)));
                this.f6220l = rawX;
                this.f6221m = rawY;
            }
        } else if (Math.abs(this.f6218j - this.f6220l) < 1.5d && Math.abs(this.f6219k - this.f6221m) < 1.5d) {
            if (this.f6215g.getVisibility() == 0) {
                this.f6215g.setVisibility(8);
                this.f6216h.setVisibility(0);
                this.f6217i.setVisibility(8);
            } else {
                this.f6215g.setVisibility(0);
                this.f6216h.setVisibility(8);
                this.f6217i.setVisibility(0);
                this.f6215g.setAnimation(com.jd.libs.xdog.g.b.a());
            }
        }
        return true;
    }

    public void e() {
        d();
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public void f() {
        View.inflate(getContext(), R.layout.xdog_web_view, this);
        XBridgeManager.INSTANCE.registerPlugin("DogDoorPlugin", com.jd.libs.xdog.c.class);
        this.f6215g = new XDogWebView(getContext());
        double d = getContext().getResources().getDisplayMetrics().heightPixels;
        Double.isNaN(d);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, (int) (d * 0.8d));
        layoutParams.addRule(12);
        this.f6215g.setLayoutParams(layoutParams);
        addView(this.f6215g);
        final String str = com.jd.libs.xdog.f.w ? "https://xdog-test1.local-pf.jd.com/board" : "https://xdog-pro.pf.jd.com/board";
        this.f6215g.getView().post(new Runnable() { // from class: com.jd.libs.xdog.ui.c
            {
                XDogPanelView.this = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                XDogPanelView.this.j(str);
            }
        });
        this.f6215g.setWebViewClient(new a());
        this.f6215g.setWebChromeClient(new b(this));
        this.f6215g.setVisibility(8);
        int i2 = R.id.hybrid_view;
        this.f6217i = findViewById(i2);
        this.f6216h = (Button) findViewById(R.id.hybrid_log_btn);
        findViewById(i2).setOnClickListener(new View.OnClickListener() { // from class: com.jd.libs.xdog.ui.f
            {
                XDogPanelView.this = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                XDogPanelView.this.l(view);
            }
        });
        this.f6216h.setOnTouchListener(new View.OnTouchListener() { // from class: com.jd.libs.xdog.ui.d
            {
                XDogPanelView.this = this;
            }

            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return XDogPanelView.this.n(view, motionEvent);
            }
        });
    }

    public void o(Object obj, String str) {
        com.jd.libs.xdog.g.e.a(this.f6215g, str, "0", obj, "");
    }
}
