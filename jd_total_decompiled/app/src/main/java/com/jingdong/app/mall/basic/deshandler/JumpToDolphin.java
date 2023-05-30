package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.jump.Action;
import com.jingdong.common.jump.JumpNetDataProvider;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.utils.PackageInfoUtil;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;
import java.util.HashMap;
import java.util.Map;

@Des(des = JumpUtil.VALUE_DES_DOLPHIN)
/* loaded from: classes19.dex */
public class JumpToDolphin extends com.jingdong.app.mall.basic.deshandler.a {
    private volatile boolean b = false;

    /* renamed from: c  reason: collision with root package name */
    private volatile boolean f8024c = false;
    private boolean d = false;

    /* renamed from: e  reason: collision with root package name */
    private JumpEntity f8025e;

    /* loaded from: classes19.dex */
    class a extends Action {
        final /* synthetic */ Context a;
        final /* synthetic */ Bundle b;

        a(Context context, Bundle bundle) {
            this.a = context;
            this.b = bundle;
        }

        @Override // com.jingdong.common.jump.Action
        public void onEnd(HttpResponse httpResponse) {
            try {
                JDJSONObject jSONObject = httpResponse.getFastJsonObject().getJSONObject("result");
                if (jSONObject != null) {
                    JumpToDolphin jumpToDolphin = JumpToDolphin.this;
                    boolean z = true;
                    if (jSONObject.optInt("gotoBabel", 0) != 1) {
                        z = false;
                    }
                    jumpToDolphin.d = z;
                    JDJSONObject jSONObject2 = jSONObject.getJSONObject("jump");
                    if (jSONObject2 != null) {
                        JumpToDolphin.this.f8025e = (JumpEntity) JDJSON.parseObject(jSONObject2.toJSONString(), JumpEntity.class);
                    }
                }
                JumpToDolphin jumpToDolphin2 = JumpToDolphin.this;
                jumpToDolphin2.D(this.a, this.b, jumpToDolphin2.d, JumpToDolphin.this.f8025e);
            } catch (Exception unused) {
                JumpToDolphin.this.D(this.a, this.b, false, null);
            }
        }

        @Override // com.jingdong.common.jump.Action
        public void onError(HttpError httpError) {
            JumpToDolphin.this.D(this.a, this.b, false, null);
        }
    }

    /* loaded from: classes19.dex */
    class b implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Context f8027g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ Bundle f8028h;

        b(Context context, Bundle bundle) {
            this.f8027g = context;
            this.f8028h = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (JumpToDolphin.this.b) {
                return;
            }
            JumpToDolphin.this.f8024c = true;
            JumpToDolphin.C(this.f8027g, this.f8028h, 0);
        }
    }

    private String A(Bundle bundle) {
        return bundle.containsKey("dolphinId") ? bundle.getString("dolphinId") : "";
    }

    private Map<String, Object> B(Bundle bundle) {
        HashMap hashMap = new HashMap();
        if (bundle != null) {
            for (String str : bundle.keySet()) {
                hashMap.put(str, bundle.get(str));
            }
        }
        hashMap.put("client", "android");
        hashMap.put(HybridSDK.APP_VERSION, TextUtils.isEmpty(PackageInfoUtil.getVersionName()) ? "" : PackageInfoUtil.getVersionName());
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void C(Context context, Bundle bundle, int i2) {
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(DeepLinkCommonHelper.HOST_DOLPHIN_ACTIVITY).toString(), bundle, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void D(Context context, Bundle bundle, boolean z, JumpEntity jumpEntity) {
        if (z && jumpEntity != null) {
            if (this.f8024c) {
                return;
            }
            this.b = true;
            JumpUtil.execJump(context, jumpEntity, -1);
        } else if (this.f8024c) {
        } else {
            this.b = true;
            C(context, bundle, 0);
        }
    }

    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        String A = A(bundle);
        a aVar = new a(context, bundle);
        Map<String, Object> B = B(bundle);
        B.put("applicationId", A);
        JumpNetDataProvider.getInstance().request("layoutSwicth", B, new boolean[0]);
        JumpNetDataProvider.getInstance().register("layoutSwicth", aVar);
        new Handler().postDelayed(new b(context, bundle), 1000L);
        c(context);
    }
}
