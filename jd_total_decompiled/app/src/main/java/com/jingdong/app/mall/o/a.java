package com.jingdong.app.mall.o;

import com.jingdong.common.BaseActivity;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class a {

    /* renamed from: com.jingdong.app.mall.o.a$a  reason: collision with other inner class name */
    /* loaded from: classes4.dex */
    class C0366a implements HttpGroup.OnCommonListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ b f11427g;

        C0366a(b bVar) {
            this.f11427g = bVar;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            if ("0".equals(httpResponse.getJSONObject().optString("code"))) {
                b bVar = this.f11427g;
                if (bVar != null) {
                    bVar.onSuccess();
                    return;
                }
                return;
            }
            b bVar2 = this.f11427g;
            if (bVar2 != null) {
                bVar2.onFail();
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            b bVar = this.f11427g;
            if (bVar != null) {
                bVar.onFail();
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
            if (Log.D) {
                Log.d("GiftShoppingController", "json = " + httpSettingParams.toString());
            }
        }
    }

    /* loaded from: classes4.dex */
    public interface b {
        void onFail();

        void onSuccess();
    }

    public static void a(BaseActivity baseActivity, String str, int i2, b bVar) {
        HttpSetting httpSetting = new HttpSetting();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("skuId", str);
            jSONObject.put("skuNum", i2);
        } catch (Exception e2) {
            if (Log.D) {
                e2.printStackTrace();
            }
        }
        httpSetting.setFunctionId("addGiftToCart");
        httpSetting.setJsonParams(jSONObject);
        httpSetting.setNotifyUser(true);
        httpSetting.setHost(Configuration.getNgwHost());
        httpSetting.setReadTimeout(15000);
        httpSetting.setAttempts(5);
        httpSetting.setEffect(1);
        httpSetting.setListener(new C0366a(bVar));
        baseActivity.getHttpGroupaAsynPool().add(httpSetting);
    }
}
