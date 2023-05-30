package com.jingdong.app.mall.l.a;

import android.text.TextUtils;
import com.huawei.hms.actions.SearchIntents;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.jdvideo.view.JDVideoHostFragment;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.unification.navigationbar.NavigationBase;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;

/* loaded from: classes4.dex */
public class a {
    public static a b;
    private boolean a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.app.mall.l.a.a$a  reason: collision with other inner class name */
    /* loaded from: classes4.dex */
    public class C0341a implements HttpGroup.OnCommonListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ String f11148g;

        C0341a(String str) {
            this.f11148g = str;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            if (httpResponse == null || httpResponse.getFastJsonObject() == null) {
                return;
            }
            JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
            String optString = fastJsonObject.optString("code", "");
            if (Log.D) {
                Log.d("VideoTabRedPointManager", "onEnd: code = " + optString);
            }
            if (TextUtils.equals(optString, "0") && TextUtils.equals(this.f11148g, SearchIntents.EXTRA_QUERY)) {
                int optInt = fastJsonObject.optInt("redPointShow", -1);
                if (Log.D) {
                    Log.d("VideoTabRedPointManager", "onEnd: redPointShow = " + optInt);
                }
                if (optInt == 1) {
                    a.this.j(true);
                }
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ boolean f11150g;

        b(boolean z) {
            this.f11150g = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            NavigationBase.getInstance().showRedpoint(6, this.f11150g);
            if (!this.f11150g && a.this.a) {
                JDVideoHostFragment.a().b(true);
                a.this.i("remove");
            }
            a.this.a = this.f11150g;
        }
    }

    private a() {
    }

    public static void d() {
        b = null;
    }

    public static a e() {
        if (b == null) {
            synchronized (a.class) {
                if (b == null) {
                    b = new a();
                }
            }
        }
        return b;
    }

    private boolean f() {
        if (Log.D) {
            Log.d("VideoTabRedPointManager", "isInVideoTab: NavigationBase.getInstance().mCurrentIndex = " + NavigationBase.getInstance().mCurrentIndex);
        }
        return NavigationBase.getInstance().mCurrentIndex == 6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i(String str) {
        if (Log.D) {
            Log.d("VideoTabRedPointManager", "request: action = " + str);
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("discVideoRedPoint");
        httpSetting.putJsonParam("action", str);
        httpSetting.setHost(Configuration.getPortalHost());
        httpSetting.setEffect(0);
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setListener(new C0341a(str));
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    public boolean g() {
        return this.a;
    }

    public void h() {
        if (NavigationBase.getInstance().isNavigationType(2)) {
            if (Log.D) {
                Log.d("VideoTabRedPointManager", "queryRedPoint");
            }
            i(SearchIntents.EXTRA_QUERY);
        }
    }

    public void j(boolean z) {
        if (NavigationBase.getInstance().isNavigationType(2)) {
            if (Log.D) {
                Log.d("VideoTabRedPointManager", "showRedPoint: isShow = " + z + ", isRedPointShown = " + this.a);
            }
            BaseFrameUtil baseFrameUtil = BaseFrameUtil.getInstance();
            if (baseFrameUtil.getMainFrameActivity() == null || baseFrameUtil.getMainFrameActivity().getHandler() == null) {
                return;
            }
            if (z || this.a) {
                if (z && f()) {
                    return;
                }
                baseFrameUtil.getMainFrameActivity().getHandler().post(new b(z));
            }
        }
    }
}
