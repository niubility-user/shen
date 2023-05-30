package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkArVrPublicInterfaceHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkSwitch;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.jdsdk.constant.PDConstant;
import com.jingdong.sdk.jdhttpdns.config.HttpDnsConfig;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Des(des = JumpUtil.VALUE_DES_ARVR_PUBLIC_INTERFACE)
/* loaded from: classes19.dex */
public class JumpToArVrPublicInterface extends a {
    private void s(Context context, Bundle bundle, String str, String str2) {
        if (!w()) {
            if (Log.D) {
                Log.d(this.a, "BundleFitShoesSwitchOpen is false");
                return;
            }
            return;
        }
        bundle.putString("className", "com.jd.lib.fitshoes.CacheManager");
        if (HttpDnsConfig.PREDOWNLOAD_PARAMS.equals(str2)) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                String string = jSONObject.getString("sku");
                String string2 = jSONObject.getString("type");
                if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2)) {
                    JSONArray jSONArray = new JSONArray();
                    jSONArray.put(string);
                    jSONArray.put(string2);
                    bundle.putString(DeepLinkArVrPublicInterfaceHelper.BUNDLE_KEY_METHOD_PARAMS, jSONArray.toString());
                    DeepLinkArVrPublicInterfaceHelper.execMethod(context, bundle, true);
                }
            } catch (JSONException e2) {
                if (Log.D) {
                    Log.e(this.a, e2.getMessage());
                }
            }
        } else if ("cleanCache".equals(str2)) {
            bundle.putString(DeepLinkArVrPublicInterfaceHelper.BUNDLE_KEY_METHOD_PARAMS, "");
            DeepLinkArVrPublicInterfaceHelper.execMethod(context, bundle, false);
        }
    }

    private void t(Context context, Bundle bundle) {
        String string = bundle.getString("module");
        String string2 = bundle.getString("methodName");
        String string3 = bundle.getString(DeepLinkArVrPublicInterfaceHelper.BUNDLE_KEY_METHOD_PARAMS);
        if (TextUtils.isEmpty(string2)) {
            if (Log.D) {
                Log.e(this.a, "methodName is null");
            }
        } else if ("threedtryclothes".equals(string)) {
            if (Log.D) {
                Log.d(this.a, "module\uff1a" + string);
            }
            if (x()) {
                bundle.putString("className", "com.jd.lib.threedtryclothes.PublicInterface");
                if (!HttpDnsConfig.PREDOWNLOAD_PARAMS.equals(string2) || TextUtils.isEmpty(string3)) {
                    return;
                }
                try {
                    String string4 = new JSONObject(string3).getString("sku");
                    if (TextUtils.isEmpty(string4)) {
                        return;
                    }
                    JSONArray jSONArray = new JSONArray();
                    jSONArray.put(string4);
                    bundle.putString(DeepLinkArVrPublicInterfaceHelper.BUNDLE_KEY_METHOD_PARAMS, jSONArray.toString());
                    DeepLinkArVrPublicInterfaceHelper.execMethod(context, bundle, true);
                } catch (JSONException e2) {
                    if (Log.D) {
                        Log.e(this.a, e2.getMessage());
                    }
                }
            } else if (Log.D) {
                Log.d(this.a, "BundleThreedTryclothesSwitchOpen is false");
            }
        } else if (JumpUtil.VALUE_DES_PRODUCT_THREED.equals(string)) {
            if (Log.D) {
                Log.d(this.a, "module\uff1a" + string);
            }
            if (w()) {
                bundle.putString("className", "com.jd.lib.threedproduct.CacheManager");
                if (HttpDnsConfig.PREDOWNLOAD_PARAMS.equals(string2)) {
                    if (TextUtils.isEmpty(string3)) {
                        return;
                    }
                    try {
                        JSONObject jSONObject = new JSONObject(string3);
                        String string5 = jSONObject.getString("sku");
                        String string6 = jSONObject.getString(PDConstant.EXTRA_MODEL_ID);
                        if (TextUtils.isEmpty(string5) || TextUtils.isEmpty(string6)) {
                            return;
                        }
                        JSONArray jSONArray2 = new JSONArray();
                        jSONArray2.put(string5);
                        jSONArray2.put(string6);
                        bundle.putString(DeepLinkArVrPublicInterfaceHelper.BUNDLE_KEY_METHOD_PARAMS, jSONArray2.toString());
                        DeepLinkArVrPublicInterfaceHelper.execMethod(context, bundle, true);
                    } catch (JSONException e3) {
                        if (Log.D) {
                            Log.e(this.a, e3.getMessage());
                        }
                    }
                } else if ("cleanCache".equals(string2)) {
                    bundle.putString(DeepLinkArVrPublicInterfaceHelper.BUNDLE_KEY_METHOD_PARAMS, "");
                    DeepLinkArVrPublicInterfaceHelper.execMethod(context, bundle, false);
                }
            } else if (Log.D) {
                Log.d(this.a, "BundleThreedProductSwitchOpen is false");
            }
        } else if ("makeup".equals(string)) {
            if (Log.D) {
                Log.d(this.a, "module\uff1a" + string);
            }
            if (v()) {
                bundle.putString("className", "com.jd.lib.armakeup.PublicInterface");
                if (HttpDnsConfig.PREDOWNLOAD_PARAMS.equals(string2)) {
                    if (!TextUtils.isEmpty(string3)) {
                        try {
                            JSONObject jSONObject2 = new JSONObject(string3);
                            String optString = jSONObject2.optString("sku");
                            String optString2 = jSONObject2.optString("type");
                            if (TextUtils.isEmpty(optString) || TextUtils.isEmpty(optString2)) {
                                return;
                            }
                            JSONArray jSONArray3 = new JSONArray();
                            jSONArray3.put(optString);
                            jSONArray3.put(optString2);
                            bundle.putString(DeepLinkArVrPublicInterfaceHelper.BUNDLE_KEY_METHOD_PARAMS, jSONArray3.toString());
                            DeepLinkArVrPublicInterfaceHelper.execMethod(context, bundle, true);
                            return;
                        } catch (JSONException e4) {
                            if (Log.D) {
                                Log.e(this.a, e4.getMessage());
                                return;
                            }
                            return;
                        }
                    }
                    bundle.putString(DeepLinkArVrPublicInterfaceHelper.BUNDLE_KEY_METHOD_PARAMS, "");
                    DeepLinkArVrPublicInterfaceHelper.execMethod(context, bundle, true);
                }
            } else if (Log.D) {
                Log.d(this.a, "isBundleMakeupSwitchOpen is false");
            }
        } else if (JumpUtil.VALUE_DES_FIT_SHOES.equals(string)) {
            s(context, bundle, string3, string2);
        } else if ("virtualhuman".equals(string)) {
            u(context, bundle, string3, string2);
        }
    }

    private static boolean v() {
        return DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(60));
    }

    private static boolean w() {
        return DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(48));
    }

    private static boolean x() {
        return DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(69));
    }

    private static boolean y() {
        return true;
    }

    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (Log.D) {
            Log.d(this.a, "forwardArVrPublicInterface");
        }
        t(context, bundle);
        c(context);
    }

    public void u(Context context, Bundle bundle, String str, String str2) {
        if (!y()) {
            if (Log.D) {
                Log.d(this.a, "BundleVirtualHumanSwitchOpen is false");
                return;
            }
            return;
        }
        bundle.putString("className", "com.jd.lib.virtualhuman.CacheManager");
        if (HttpDnsConfig.PREDOWNLOAD_PARAMS.equals(str2)) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                String string = jSONObject.getString("tempId");
                String string2 = jSONObject.getString("channel");
                JSONArray jSONArray = new JSONArray();
                jSONArray.put(string);
                jSONArray.put(string2);
                bundle.putString(DeepLinkArVrPublicInterfaceHelper.BUNDLE_KEY_METHOD_PARAMS, jSONArray.toString());
                DeepLinkArVrPublicInterfaceHelper.execMethod(context, bundle, true);
            } catch (JSONException e2) {
                if (Log.D) {
                    Log.e(this.a, e2.getMessage());
                }
            }
        } else if ("cleanCache".equals(str2)) {
            bundle.putString(DeepLinkArVrPublicInterfaceHelper.BUNDLE_KEY_METHOD_PARAMS, "");
            DeepLinkArVrPublicInterfaceHelper.execMethod(context, bundle, false);
        }
    }
}
