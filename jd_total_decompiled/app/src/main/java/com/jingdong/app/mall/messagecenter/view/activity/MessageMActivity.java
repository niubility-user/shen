package com.jingdong.app.mall.messagecenter.view.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.WebActivity;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.common.utils.StringUtil;
import com.jingdong.common.web.ui.CommonMFragment;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.common.web.uilistener.WebViewScrollListener;
import com.jingdong.common.web.uilistener.impl.WebViewClientListenerImpl;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.tencent.smtt.sdk.WebView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class MessageMActivity extends WebActivity {
    private static final String r = MessageMActivity.class.getSimpleName();

    /* renamed from: g  reason: collision with root package name */
    private View f11247g;

    /* renamed from: h  reason: collision with root package name */
    private String f11248h;

    /* renamed from: i  reason: collision with root package name */
    private int f11249i;

    /* renamed from: j  reason: collision with root package name */
    private int f11250j;

    /* renamed from: k  reason: collision with root package name */
    private String f11251k;

    /* renamed from: l  reason: collision with root package name */
    private String f11252l;

    /* renamed from: m  reason: collision with root package name */
    private String f11253m;

    /* renamed from: n  reason: collision with root package name */
    private List<com.jingdong.app.mall.m.b.a> f11254n;
    private boolean o = false;
    private boolean p = false;
    private int q = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.app.mall.messagecenter.view.activity.MessageMActivity$1  reason: invalid class name */
    /* loaded from: classes4.dex */
    public class AnonymousClass1 implements View.OnClickListener {
        AnonymousClass1() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MessageMActivity.this.f11254n = new ArrayList();
            JDMtaUtils.onClickWithPageId(MessageMActivity.this, "Message_Feedback", AnonymousClass1.class.getName(), "", "", "MessageCenter_Feedback");
            final com.jingdong.app.mall.m.c.a.a aVar = new com.jingdong.app.mall.m.c.a.a(MessageMActivity.this, R.style.f85u_);
            aVar.y(MessageMActivity.this.f11254n);
            aVar.v(MessageMActivity.this.f11251k, MessageMActivity.this.f11252l, MessageMActivity.this.f11253m);
            aVar.show();
            com.jingdong.app.mall.messagecenter.view.manager.a.a(MessageMActivity.this.f11252l, new HttpGroup.OnCommonListener() { // from class: com.jingdong.app.mall.messagecenter.view.activity.MessageMActivity.1.1

                /* renamed from: com.jingdong.app.mall.messagecenter.view.activity.MessageMActivity$1$1$a */
                /* loaded from: classes4.dex */
                class a implements Runnable {

                    /* renamed from: g  reason: collision with root package name */
                    final /* synthetic */ List f11258g;

                    a(List list) {
                        this.f11258g = list;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        MessageMActivity.this.f11254n.clear();
                        MessageMActivity.this.f11254n.addAll(this.f11258g);
                        aVar.w();
                    }
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                public void onEnd(HttpResponse httpResponse) {
                    httpResponse.getFastJsonObject();
                    if (httpResponse.getCode() == 0) {
                        List list = (List) new Gson().fromJson(httpResponse.getFastJsonObject().optString("data"), new TypeToken<List<com.jingdong.app.mall.m.b.a>>(this) { // from class: com.jingdong.app.mall.messagecenter.view.activity.MessageMActivity.1.1.1
                        }.getType());
                        Iterator it = list.iterator();
                        while (it.hasNext()) {
                            com.jingdong.app.mall.m.b.a aVar2 = (com.jingdong.app.mall.m.b.a) it.next();
                            if (StringUtil.isEmpty(aVar2.getId()) || StringUtil.isEmpty(aVar2.getTabValue())) {
                                it.remove();
                            }
                        }
                        MessageMActivity.this.post(new a(list));
                    }
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
                public void onError(HttpError httpError) {
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
                public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements WebViewScrollListener {

        /* renamed from: com.jingdong.app.mall.messagecenter.view.activity.MessageMActivity$a$a  reason: collision with other inner class name */
        /* loaded from: classes4.dex */
        class RunnableC0359a implements Runnable {

            /* renamed from: g  reason: collision with root package name */
            final /* synthetic */ int f11260g;

            RunnableC0359a(int i2) {
                this.f11260g = i2;
            }

            @Override // java.lang.Runnable
            public void run() {
                MessageMActivity.this.f11247g.setVisibility(this.f11260g > 240 ? 8 : 0);
            }
        }

        a() {
        }

        @Override // com.jingdong.common.web.uilistener.WebViewScrollListener
        public void onScrollChanged(int i2, int i3, int i4, int i5) {
            MessageMActivity.this.q = i3;
            MessageMActivity.this.f11247g.post(new RunnableC0359a(i3));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public class b extends WebViewClientListenerImpl {

        /* loaded from: classes4.dex */
        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                if (MessageMActivity.this.f11247g.getVisibility() != 8 || MessageMActivity.this.o) {
                    return;
                }
                MessageMActivity.this.f11247g.setVisibility(0);
                MessageMActivity.this.o = true;
            }
        }

        public b(IWebUiBinder iWebUiBinder) {
            super(iWebUiBinder);
        }

        @Override // com.jingdong.common.web.uilistener.impl.WebViewClientListenerImpl, com.jingdong.common.web.uilistener.WebViewClientListener
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            Log.d(MessageMActivity.r, "onPageFinished\uff1a" + str);
            MessageMActivity.this.f11247g.post(new a());
        }

        @Override // com.jingdong.common.web.uilistener.impl.WebViewClientListenerImpl, com.jingdong.common.web.uilistener.WebViewClientListener
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
        }
    }

    private void E(String str) {
        try {
            JDJSONObject parseObject = JDJSON.parseObject(str);
            this.f11251k = parseObject.optString("msgId");
            this.f11252l = parseObject.optString("businessType");
            this.f11253m = parseObject.optString("url");
        } catch (Exception unused) {
            this.p = true;
        }
    }

    private void F() {
        this.f11247g.setOnClickListener(new AnonymousClass1());
        this.mFragment.getJdWebView().setWebViewClientListener(new b(this.mFragment.getWebUiBinder()));
        this.mFragment.getJdWebView().addWebViewScrollListener(new a());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.WebActivity, com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setPageId("MessageCenter_Feedback");
        this.f11247g = ImageUtil.inflate(this, (int) R.layout.message_center_m_layout, (ViewGroup) null);
        String stringExtra = getIntent().getStringExtra("msg");
        this.f11248h = stringExtra;
        E(stringExtra);
        Log.d(r, this.f11248h);
        this.f11249i = BaseInfo.getScreenHeight();
        this.f11250j = BaseInfo.getScreenWidth();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.WebActivity, com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        CommonMFragment commonMFragment = this.mFragment;
        if (commonMFragment != null && commonMFragment.getJdWebView() != null) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
            double d = this.f11249i;
            Double.isNaN(d);
            layoutParams.topMargin = (int) (d * 0.7d);
            double d2 = this.f11250j;
            Double.isNaN(d2);
            layoutParams.leftMargin = ((int) (d2 * 0.8d)) + 25;
            this.f11247g.setLayoutParams(layoutParams);
            if (((View) this.f11247g.getParent()) == null && !this.p) {
                try {
                    this.mFragment.getJdWebView().addView(this.f11247g);
                } catch (Exception unused) {
                }
            }
            if (!this.o) {
                this.f11247g.setVisibility(8);
            } else {
                this.f11247g.setVisibility(this.q <= 240 ? 0 : 8);
            }
        }
        super.onResume();
        F();
    }
}
