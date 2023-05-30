package com.jingdong.manto.jsapi.refact.media;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import androidx.annotation.NonNull;
import com.jingdong.a;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.manto.MantoActivityResult;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.sdk.api.AbsChooseMedia;
import com.jingdong.manto.t.c;
import com.jingdong.manto.t.d;
import com.jingdong.union.common.config.UnionConstants;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class JsApiPreviewImage extends AbstractMantoModule {
    static final String MODULE_NAME = "PreviewImage";
    static final String PREVIEWIMAGE_NAME = "previewImage";
    static final String TAG = "PreviewImage";

    private void handlePreviewImage(MantoActivityResult mantoActivityResult, JSONObject jSONObject, String str, MantoResultCallBack mantoResultCallBack) {
        int i2;
        String str2;
        Bundle bundle = new Bundle();
        JSONArray optJSONArray = jSONObject.optJSONArray("urls");
        if (optJSONArray == null) {
            bundle.putString("message", "no url");
            mantoResultCallBack.onFailed(bundle);
        }
        int length = optJSONArray.length();
        String[] strArr = new String[length];
        for (int i3 = 0; i3 < optJSONArray.length(); i3++) {
            strArr[i3] = optJSONArray.optString(i3);
        }
        Object opt = jSONObject.opt(UnionConstants.BUNDLE_CURRENT);
        if (opt instanceof Integer) {
            i2 = ((Integer) opt).intValue();
        } else if (opt instanceof String) {
            int i4 = 0;
            for (int i5 = 0; i5 < length; i5++) {
                if (opt.equals(strArr[i5])) {
                    i4 = i5;
                }
            }
            i2 = i4;
        } else {
            i2 = 0;
        }
        for (int i6 = 0; i6 < length; i6++) {
            if (!TextUtils.isEmpty(strArr[i6]) && strArr[i6].startsWith("jdfile://")) {
                d g2 = c.g(str, strArr[i6]);
                if (g2 != null) {
                    strArr[i6] = g2.b;
                } else {
                    strArr[i6] = "";
                }
            }
        }
        String[] strArr2 = new String[length];
        for (int i7 = 0; i7 < length; i7++) {
            if (strArr[i7] == null || !strArr[i7].startsWith("data:image/")) {
                strArr2[i7] = strArr[i7];
            } else {
                String str3 = strArr[i7];
                try {
                    str2 = tP(str3.substring(str3.indexOf("base64,") + 7)).trim();
                } catch (Throwable unused) {
                    str2 = "";
                }
                if (TextUtils.isEmpty(str3)) {
                    str2 = "";
                }
                strArr2[i7] = str2;
            }
        }
        startPreview(mantoActivityResult, new ArrayList<>(Arrays.asList(strArr2)), i2);
        mantoResultCallBack.onSuccess(bundle);
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
        return "PreviewImage";
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public final void handleMethod(String str, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(bundle.getString("json"));
        } catch (JSONException e2) {
            e2.printStackTrace();
            jSONObject = null;
        }
        handlePreviewImage(mantoCore.getActivityResultImpl(), jSONObject, bundle.getString(IMantoBaseModule.APP_UNIQUEID_ID_KEY, ""), mantoResultCallBack);
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public final Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        bundle.putString("json", jSONObject.toString());
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule
    protected final void injectJsApiMethod(List<JsApiMethod> list) {
        list.add(new JsApiMethod(PREVIEWIMAGE_NAME, 1));
    }

    public void startPreview(@NonNull MantoActivityResult mantoActivityResult, ArrayList<String> arrayList, int i2) {
        AbsChooseMedia absChooseMedia = (AbsChooseMedia) a.n(AbsChooseMedia.class);
        if (absChooseMedia != null) {
            absChooseMedia.onPreviewImages(mantoActivityResult, arrayList, i2);
        }
    }
}
