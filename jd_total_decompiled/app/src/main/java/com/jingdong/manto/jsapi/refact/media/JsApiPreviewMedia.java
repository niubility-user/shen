package com.jingdong.manto.jsapi.refact.media;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import androidx.annotation.NonNull;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.t.c;
import com.jingdong.manto.t.d;
import com.jingdong.union.common.config.UnionConstants;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public abstract class JsApiPreviewMedia extends AbstractMantoModule {
    private static final String MODULE_NAME = "m_previewMedia";
    private static final int PREVIEWIMAGE_INDEX = 32;
    private static final String PREVIEWIMAGE_NAME = "previewMedia";
    private static final String TAG = "m_previewMedia";

    private String getRealPath(String str, String str2) {
        if (TextUtils.isEmpty(str2) || !str2.startsWith("jdfile://")) {
            return str2;
        }
        d g2 = c.g(str, str2);
        if (g2 != null) {
            return g2.b;
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v5, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r8v6, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v7 */
    private String tP(String str) {
        byte[] decode = Base64.decode(str, 0);
        if (decode != null && decode.length != 0) {
            File file = new File(com.jingdong.manto.c.a().getCacheDir(), "preview_" + System.currentTimeMillis() + CartConstant.KEY_YB_INFO_LINK + str.hashCode());
            ?? r8 = 0;
            r8 = 0;
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                try {
                    fileOutputStream.write(decode);
                    try {
                        fileOutputStream.close();
                    } catch (IOException unused) {
                    }
                    r8 = file.getAbsolutePath();
                    return r8;
                } catch (Exception unused2) {
                    return "";
                } catch (Throwable th) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException unused3) {
                    }
                    throw th;
                }
            } catch (Exception unused4) {
                r8.close();
            }
        }
        return "";
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public final String getModuleName() {
        return "m_previewMedia";
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public final void handleMethod(String str, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        try {
            JSONObject jSONObject = new JSONObject(bundle.getString("json"));
            String string = bundle.getString(IMantoBaseModule.APP_UNIQUEID_ID_KEY, "");
            JSONArray optJSONArray = jSONObject.optJSONArray("sources");
            int optInt = jSONObject.optInt(UnionConstants.BUNDLE_CURRENT, 0);
            if (optJSONArray == null || optJSONArray.length() <= 0) {
                mantoResultCallBack.onFailed(null);
                return;
            }
            if (optInt <= 0 || optInt >= optJSONArray.length()) {
                optInt = 0;
            }
            String[] strArr = new String[optJSONArray.length()];
            ArrayList<String> arrayList = new ArrayList<>();
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i2);
                String realPath = getRealPath(string, optJSONObject.optString("url", ""));
                String optString = optJSONObject.optString("type", "image");
                String realPath2 = getRealPath(string, optJSONObject.optString("poster", ""));
                if (realPath.startsWith("data:image/")) {
                    try {
                        realPath = tP(realPath.substring(realPath.indexOf("base64,") + 7)).trim();
                    } catch (Throwable unused) {
                        realPath = "";
                    }
                }
                try {
                    optJSONObject.put("url", realPath);
                    optJSONObject.put("type", optString);
                    optJSONObject.put("poster", realPath2);
                } catch (JSONException unused2) {
                }
                arrayList.add(optJSONObject.toString());
                strArr[i2] = realPath;
            }
            String str2 = strArr[optInt];
            Intent intent = new Intent();
            intent.putExtra("manto_selected_url", str2);
            intent.putExtra("manto_urls", strArr);
            intent.putExtra("manto_cur_pos", optInt);
            intent.putStringArrayListExtra("manto_medias", arrayList);
            startPreview(mantoCore.getActivity(), intent);
        } catch (JSONException e2) {
            e2.printStackTrace();
            mantoResultCallBack.onFailed(null);
        }
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public final Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        bundle.putString("json", jSONObject.toString());
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule
    protected final void injectJsApiMethod(List<JsApiMethod> list) {
        list.add(new JsApiMethod(PREVIEWIMAGE_NAME, 32, 1));
    }

    public abstract void startPreview(@NonNull Activity activity, Intent intent);
}
