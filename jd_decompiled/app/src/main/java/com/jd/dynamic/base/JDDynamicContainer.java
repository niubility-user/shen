package com.jd.dynamic.base;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.jd.dynamic.base.DynamicFetcher;
import com.jd.dynamic.base.JDDynamicContainer;
import com.jd.dynamic.base.NewDynamicFetcher;
import com.jd.dynamic.entity.ResultEntity;
import com.jd.dynamic.entity.ViewNode;
import com.jd.dynamic.lib.dynamic.parser.NewDynamicXParser;
import com.jd.dynamic.lib.error.DowngradeException;
import java.io.InputStream;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class JDDynamicContainer extends FrameLayout {

    /* renamed from: g  reason: collision with root package name */
    private FrameLayout f1906g;

    /* renamed from: h  reason: collision with root package name */
    private View f1907h;

    /* renamed from: i  reason: collision with root package name */
    private JSONObject f1908i;

    /* renamed from: j  reason: collision with root package name */
    private IFunctionFactory f1909j;

    /* renamed from: k  reason: collision with root package name */
    private String f1910k;

    /* renamed from: l  reason: collision with root package name */
    private InputStream f1911l;

    /* renamed from: m  reason: collision with root package name */
    private String f1912m;

    /* renamed from: n  reason: collision with root package name */
    private DynamicTemplateEngine f1913n;
    private boolean o;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jd.dynamic.base.JDDynamicContainer$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    public class AnonymousClass1 extends NewDynamicFetcher.Listener2 {
        final /* synthetic */ NewDynamicFetcher.Listener a;
        final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f1914c;

        AnonymousClass1(NewDynamicFetcher.Listener listener, String str, String str2) {
            this.a = listener;
            this.b = str;
            this.f1914c = str2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void a() {
            if (JDDynamicContainer.this.f1907h != null) {
                JDDynamicContainer.this.f1907h.setVisibility(8);
            }
            if (JDDynamicContainer.this.f1906g != null) {
                JDDynamicContainer.this.f1906g.setVisibility(0);
            }
        }

        @Override // com.jd.dynamic.base.NewDynamicFetcher.Listener2
        public void onEnd(ResultEntity resultEntity, String str) {
            if (resultEntity != null && resultEntity.viewNode != null) {
                JDDynamicContainer.this.post(new Runnable() { // from class: com.jd.dynamic.base.d1
                    @Override // java.lang.Runnable
                    public final void run() {
                        JDDynamicContainer.AnonymousClass1.this.a();
                    }
                });
                JDDynamicContainer.this.f1913n = new DynamicTemplateEngine(JDDynamicContainer.this.f1912m, (Activity) JDDynamicContainer.this.getContext(), JDDynamicContainer.this.f1906g, JDDynamicContainer.this.f1909j);
                JDDynamicContainer.this.f1913n.setBizField(this.b);
                JDDynamicContainer.this.f1913n.setSystemCode(this.f1914c);
                JDDynamicContainer.this.f1913n.shouldAutoListenDarkStatus(JDDynamicContainer.this.o);
                JDDynamicContainer.this.f1913n.entity = resultEntity;
                JDDynamicContainer.this.f1913n.newInitTemplate(resultEntity.viewNode, JDDynamicContainer.this.f1908i, str);
            }
            JDDynamicContainer.g(this.a, resultEntity, str);
        }

        @Override // com.jd.dynamic.base.NewDynamicFetcher.Listener, com.jd.dynamic.base.DynamicFetcher.Listener
        public void onError(Exception exc) {
            NewDynamicFetcher.Listener listener = this.a;
            if (listener != null) {
                listener.onError(exc);
            }
            if (exc instanceof DowngradeException) {
                return;
            }
            JDDynamicContainer.this.newLoadCacheXml(this.a, this.f1914c, this.b);
        }

        @Override // com.jd.dynamic.base.NewDynamicFetcher.Listener, com.jd.dynamic.base.DynamicFetcher.Listener
        public void onStart() {
            NewDynamicFetcher.Listener listener = this.a;
            if (listener != null) {
                listener.onStart();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jd.dynamic.base.JDDynamicContainer$2  reason: invalid class name */
    /* loaded from: classes13.dex */
    public class AnonymousClass2 implements DynamicFetcher.Listener {
        final /* synthetic */ DynamicFetcher.Listener a;
        final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f1915c;

        AnonymousClass2(DynamicFetcher.Listener listener, String str, String str2) {
            this.a = listener;
            this.b = str;
            this.f1915c = str2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void a() {
            if (JDDynamicContainer.this.f1907h != null) {
                JDDynamicContainer.this.f1907h.setVisibility(8);
            }
            if (JDDynamicContainer.this.f1906g != null) {
                JDDynamicContainer.this.f1906g.setVisibility(0);
            }
        }

        @Override // com.jd.dynamic.base.DynamicFetcher.Listener
        public void onEnd(ViewNode viewNode, String str) {
            if (viewNode != null) {
                JDDynamicContainer.this.post(new Runnable() { // from class: com.jd.dynamic.base.e1
                    @Override // java.lang.Runnable
                    public final void run() {
                        JDDynamicContainer.AnonymousClass2.this.a();
                    }
                });
                JDDynamicContainer.this.f1913n = new DynamicTemplateEngine(JDDynamicContainer.this.f1912m, (Activity) JDDynamicContainer.this.getContext(), JDDynamicContainer.this.f1906g, JDDynamicContainer.this.f1909j);
                JDDynamicContainer.this.f1913n.setBizField(this.b);
                JDDynamicContainer.this.f1913n.setSystemCode(this.f1915c);
                JDDynamicContainer.this.f1913n.shouldAutoListenDarkStatus(JDDynamicContainer.this.o);
                JDDynamicContainer.this.f1913n.newInitTemplate(viewNode, JDDynamicContainer.this.f1908i, str);
            }
            DynamicFetcher.Listener listener = this.a;
            if (listener == null || viewNode == null) {
                return;
            }
            listener.onEnd(viewNode, str);
        }

        @Override // com.jd.dynamic.base.DynamicFetcher.Listener
        public void onError(Exception exc) {
            DynamicFetcher.Listener listener = this.a;
            if (listener != null) {
                listener.onError(exc);
            }
            if (exc instanceof DowngradeException) {
                return;
            }
            JDDynamicContainer.this.loadCacheXml(this.a, this.f1915c, this.b);
        }

        @Override // com.jd.dynamic.base.DynamicFetcher.Listener
        public void onStart() {
            DynamicFetcher.Listener listener = this.a;
            if (listener != null) {
                listener.onStart();
            }
        }
    }

    public JDDynamicContainer(@NonNull Context context) {
        this(context, null);
    }

    public JDDynamicContainer(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public JDDynamicContainer(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }

    @RequiresApi(api = 21)
    public JDDynamicContainer(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c() {
        View view = this.f1907h;
        if (view != null) {
            view.setVisibility(8);
        }
        FrameLayout frameLayout = this.f1906g;
        if (frameLayout != null) {
            frameLayout.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void f() {
        View view = this.f1907h;
        if (view != null) {
            view.setVisibility(8);
        }
        FrameLayout frameLayout = this.f1906g;
        if (frameLayout != null) {
            frameLayout.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void g(DynamicFetcher.Listener listener, ResultEntity resultEntity, String str) {
        if (listener == null || resultEntity == null) {
            return;
        }
        if (listener instanceof NewDynamicFetcher.Listener2) {
            ((NewDynamicFetcher.Listener2) listener).onEnd(resultEntity, str);
        } else {
            listener.onEnd(resultEntity.viewNode, str);
        }
    }

    public FrameLayout getDynamicLayout() {
        return this.f1906g;
    }

    public void init() {
        View view = this.f1907h;
        if (view != null) {
            view.setVisibility(0);
        }
        FrameLayout frameLayout = this.f1906g;
        if (frameLayout != null) {
            frameLayout.setVisibility(8);
        }
    }

    public void loadCacheXml(DynamicFetcher.Listener listener, String str, String str2) {
        ViewNode templateFromLocal;
        if (TextUtils.isEmpty(this.f1910k) && this.f1911l == null) {
            if (listener instanceof DynamicFetcher.ListenerWithLocalError) {
                ((DynamicFetcher.ListenerWithLocalError) listener).onLocalTemplateError(new IllegalArgumentException("\u515c\u5e95\u6a21\u677f\u4e3a\u7a7a"));
                return;
            }
            return;
        }
        try {
            if (TextUtils.isEmpty(this.f1910k)) {
                InputStream inputStream = this.f1911l;
                templateFromLocal = inputStream != null ? DynamicFetcher.getTemplateFromLocal(inputStream, str, str2) : null;
            } else {
                XMLParse xMLParse = new XMLParse(getContext().getAssets().open(this.f1910k));
                templateFromLocal = xMLParse.parse();
                templateFromLocal.unBindMaps = xMLParse.unBindMaps;
            }
            if (templateFromLocal == null) {
                if (listener instanceof DynamicFetcher.ListenerWithLocalError) {
                    ((DynamicFetcher.ListenerWithLocalError) listener).onLocalTemplateError(new IllegalArgumentException("\u515c\u5e95\u6a21\u677f\u89e3\u6790\u5931\u8d25"));
                    return;
                }
                return;
            }
            post(new Runnable() { // from class: com.jd.dynamic.base.f1
                @Override // java.lang.Runnable
                public final void run() {
                    JDDynamicContainer.this.c();
                }
            });
            DynamicTemplateEngine dynamicTemplateEngine = new DynamicTemplateEngine(this.f1912m, (Activity) getContext(), this.f1906g, this.f1909j);
            this.f1913n = dynamicTemplateEngine;
            dynamicTemplateEngine.setBizField(str2);
            this.f1913n.setSystemCode(str);
            this.f1913n.shouldAutoListenDarkStatus(this.o);
            this.f1913n.newInitTemplate(templateFromLocal, this.f1908i, null);
            if (listener != null) {
                listener.onEnd(templateFromLocal, null);
            }
        } catch (Exception e2) {
            if (listener instanceof DynamicFetcher.ListenerWithLocalError) {
                ((DynamicFetcher.ListenerWithLocalError) listener).onLocalTemplateError(e2);
            }
        }
    }

    public void loadDynamicView(String str, String str2, DynamicFetcher.Listener listener) {
        DynamicTemplateEngine dynamicTemplateEngine = this.f1913n;
        if (dynamicTemplateEngine != null && TextUtils.equals(str2, dynamicTemplateEngine.getBizField())) {
            View view = this.f1907h;
            if (view != null) {
                view.setVisibility(8);
            }
            FrameLayout frameLayout = this.f1906g;
            if (frameLayout != null) {
                frameLayout.setVisibility(0);
            }
            this.f1913n.newRefreshView(this.f1908i);
            return;
        }
        DynamicTemplateEngine dynamicTemplateEngine2 = this.f1913n;
        if (dynamicTemplateEngine2 != null) {
            dynamicTemplateEngine2.release();
        }
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            if (listener != null) {
                listener.onError(new IllegalArgumentException("systemCode or bizField is null!!"));
            }
            loadCacheXml(listener, str, str2);
            return;
        }
        try {
            DynamicFetcher.requestDynamicConfigByBizField(str, str2, null, new AnonymousClass2(listener, str2, str));
        } catch (Exception e2) {
            if (listener != null) {
                listener.onError(e2);
            }
            loadCacheXml(listener, str, str2);
        }
    }

    public void newLoadCacheXml(DynamicFetcher.Listener listener, String str, String str2) {
        ResultEntity resultEntity;
        ViewNode viewNode;
        if (TextUtils.isEmpty(this.f1910k) && this.f1911l == null) {
            if (listener instanceof NewDynamicFetcher.ListenerWithLocalError) {
                ((NewDynamicFetcher.ListenerWithLocalError) listener).onLocalTemplateError(new IllegalArgumentException("\u515c\u5e95\u6a21\u677f\u4e3a\u7a7a"));
                return;
            }
            return;
        }
        try {
            if (TextUtils.isEmpty(this.f1910k)) {
                InputStream inputStream = this.f1911l;
                if (inputStream != null) {
                    viewNode = NewDynamicFetcher.getTemplateFromLocal(inputStream, str, str2);
                    resultEntity = null;
                } else {
                    resultEntity = null;
                    viewNode = null;
                }
            } else if (this.f1910k.endsWith(".zip")) {
                resultEntity = NewDynamicXParser.parseBinaryToResultEntity(this.f1910k, true, str2, str, (String) null);
                viewNode = null;
            } else {
                XMLParse xMLParse = new XMLParse(getContext().getAssets().open(this.f1910k));
                viewNode = xMLParse.parse();
                viewNode.unBindMaps = xMLParse.unBindMaps;
                resultEntity = null;
            }
            if (resultEntity == null) {
                resultEntity = new ResultEntity();
                resultEntity.viewNode = viewNode;
            }
            if (resultEntity.viewNode == null) {
                if (listener instanceof NewDynamicFetcher.ListenerWithLocalError) {
                    ((NewDynamicFetcher.ListenerWithLocalError) listener).onLocalTemplateError(new IllegalArgumentException("\u515c\u5e95\u6a21\u677f\u89e3\u6790\u5931\u8d25"));
                    return;
                }
                return;
            }
            post(new Runnable() { // from class: com.jd.dynamic.base.g1
                @Override // java.lang.Runnable
                public final void run() {
                    JDDynamicContainer.this.f();
                }
            });
            DynamicTemplateEngine dynamicTemplateEngine = new DynamicTemplateEngine(this.f1912m, (Activity) getContext(), this.f1906g, this.f1909j);
            this.f1913n = dynamicTemplateEngine;
            dynamicTemplateEngine.setBizField(str2);
            this.f1913n.setSystemCode(str);
            this.f1913n.shouldAutoListenDarkStatus(this.o);
            DynamicTemplateEngine dynamicTemplateEngine2 = this.f1913n;
            dynamicTemplateEngine2.entity = resultEntity;
            dynamicTemplateEngine2.newInitTemplate(resultEntity.viewNode, this.f1908i, null);
            g(listener, resultEntity, null);
        } catch (Exception e2) {
            if (listener instanceof NewDynamicFetcher.ListenerWithLocalError) {
                ((NewDynamicFetcher.ListenerWithLocalError) listener).onLocalTemplateError(e2);
            }
        }
    }

    public void newLoadDynamicView(String str, String str2, NewDynamicFetcher.Listener listener) {
        newLoadDynamicView(true, str, str2, listener);
    }

    public void newLoadDynamicView(boolean z, String str, String str2, NewDynamicFetcher.Listener listener) {
        DynamicTemplateEngine dynamicTemplateEngine = this.f1913n;
        if (dynamicTemplateEngine != null && TextUtils.equals(str2, dynamicTemplateEngine.getBizField())) {
            View view = this.f1907h;
            if (view != null) {
                view.setVisibility(8);
            }
            FrameLayout frameLayout = this.f1906g;
            if (frameLayout != null) {
                frameLayout.setVisibility(0);
            }
            this.f1913n.newRefreshView(this.f1908i);
            return;
        }
        DynamicTemplateEngine dynamicTemplateEngine2 = this.f1913n;
        if (dynamicTemplateEngine2 != null) {
            dynamicTemplateEngine2.release();
        }
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            if (listener != null) {
                listener.onError(new IllegalArgumentException("systemCode or bizField is null!!"));
            }
            newLoadCacheXml(listener, str, str2);
            return;
        }
        try {
            NewDynamicFetcher.requestDynamicConfigByBizField(z, str, str2, null, new AnonymousClass1(listener, str2, str));
        } catch (Exception e2) {
            if (listener != null) {
                listener.onError(e2);
            }
            newLoadCacheXml(listener, str, str2);
        }
    }

    public void setDynamicJsonData(JSONObject jSONObject) {
        this.f1908i = jSONObject;
    }

    @Deprecated
    public void setInitParams(IFunctionFactory iFunctionFactory, String str, View view, String str2, boolean z) {
        this.f1909j = iFunctionFactory;
        this.f1910k = str;
        this.f1907h = view;
        this.f1912m = str2;
        this.o = z;
        if (getChildCount() > 0) {
            removeAllViews();
        }
        FrameLayout frameLayout = this.f1906g;
        if (frameLayout != null) {
            frameLayout.removeAllViews();
            this.f1906g = null;
        }
        this.f1906g = new FrameLayout(getContext());
        if (this.f1907h != null) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
            layoutParams.gravity = 17;
            addView(this.f1907h, layoutParams);
        }
        addView(this.f1906g, new FrameLayout.LayoutParams(-1, -2));
        this.f1906g.setVisibility(8);
    }

    public void setInitParamsWithLocalStream(IFunctionFactory iFunctionFactory, InputStream inputStream, View view, String str, boolean z) {
        setInitParams(iFunctionFactory, null, view, str, z);
        this.f1911l = inputStream;
    }
}
