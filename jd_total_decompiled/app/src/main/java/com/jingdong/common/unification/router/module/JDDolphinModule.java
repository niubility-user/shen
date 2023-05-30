package com.jingdong.common.unification.router.module;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.jump.Action;
import com.jingdong.common.jump.JumpNetDataProvider;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.JDRouterUtil;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.utils.PackageInfoUtil;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JDDolphinModule implements IJDModule {
    private volatile boolean mFusing = false;
    private volatile boolean mFusingReverse = false;
    private boolean mGotoBabel = false;
    private JumpEntity mJump;

    private void checkSwitch(final Context context, final Bundle bundle, final CallBackListener callBackListener) {
        String applicationId = getApplicationId(bundle);
        Action action = new Action() { // from class: com.jingdong.common.unification.router.module.JDDolphinModule.1
            @Override // com.jingdong.common.jump.Action
            public void onEnd(HttpResponse httpResponse) {
                try {
                    JDJSONObject jSONObject = httpResponse.getFastJsonObject().getJSONObject("result");
                    if (jSONObject != null) {
                        JDDolphinModule.this.mGotoBabel = jSONObject.optInt("gotoBabel", 0) == 1;
                        JDJSONObject jSONObject2 = jSONObject.getJSONObject("jump");
                        if (jSONObject2 != null) {
                            JDDolphinModule.this.mJump = (JumpEntity) JDJSON.parseObject(jSONObject2.toJSONString(), JumpEntity.class);
                        }
                    }
                    JDDolphinModule jDDolphinModule = JDDolphinModule.this;
                    jDDolphinModule.switchEntry(context, bundle, jDDolphinModule.mGotoBabel, JDDolphinModule.this.mJump, callBackListener);
                } catch (Exception unused) {
                    JDDolphinModule.this.switchEntry(context, bundle, false, null, callBackListener);
                }
            }

            @Override // com.jingdong.common.jump.Action
            public void onError(HttpError httpError) {
                JDDolphinModule.this.switchEntry(context, bundle, false, null, callBackListener);
            }
        };
        Map<String, Object> bundleParams = getBundleParams(bundle);
        bundleParams.put("applicationId", applicationId);
        JumpNetDataProvider.getInstance().request("layoutSwicth", bundleParams, new boolean[0]);
        JumpNetDataProvider.getInstance().register("layoutSwicth", action);
        new Handler().postDelayed(new Runnable() { // from class: com.jingdong.common.unification.router.module.JDDolphinModule.2
            @Override // java.lang.Runnable
            public void run() {
                if (JDDolphinModule.this.mFusing) {
                    return;
                }
                JDDolphinModule.this.mFusingReverse = true;
                JDDolphinModule.startDolphinActivity(context, bundle, 0, callBackListener);
            }
        }, 1000L);
    }

    private String getApplicationId(Bundle bundle) {
        return bundle.containsKey("dolphinId") ? bundle.getString("dolphinId") : "";
    }

    private Map<String, Object> getBundleParams(Bundle bundle) {
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

    private static void startBabel(Context context, JumpEntity jumpEntity, CallBackListener callBackListener) {
        JumpUtil.execJump(context, jumpEntity, -1);
        if (callBackListener != null) {
            callBackListener.onComplete();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void startDolphinActivity(Context context, Bundle bundle, int i2, CallBackListener callBackListener) {
        try {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(DeepLinkCommonHelper.HOST_DOLPHIN_ACTIVITY).toString(), bundle, i2);
            if (callBackListener != null) {
                callBackListener.onComplete();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            if (callBackListener != null) {
                JDRouterUtil.callBackError(callBackListener, 703);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void switchEntry(Context context, Bundle bundle, boolean z, JumpEntity jumpEntity, CallBackListener callBackListener) {
        if (z && jumpEntity != null) {
            if (this.mFusingReverse) {
                return;
            }
            this.mFusing = true;
            JumpUtil.execJump(context, jumpEntity, -1);
            startBabel(context, jumpEntity, callBackListener);
        } else if (this.mFusingReverse) {
        } else {
            this.mFusing = true;
            startDolphinActivity(context, bundle, 0, callBackListener);
        }
    }

    @Override // com.jingdong.common.unification.router.module.IJDModule
    public void show(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        showHome(context, jSONObject, bundle, callBackListener);
    }

    public void showHome(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context == null || jSONObject == null) {
            if (callBackListener != null) {
                JDRouterUtil.callBackError(callBackListener, 703);
                return;
            }
            return;
        }
        jSONObject.optString("dolphinId", "");
        jSONObject.optString("channelId", "");
        if (bundle == null) {
            bundle = new Bundle();
        }
        if (jSONObject != null) {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                bundle.putString(next, jSONObject.optString(next));
            }
        }
        checkSwitch(context, bundle, callBackListener);
    }
}
