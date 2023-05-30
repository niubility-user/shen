package com.jingdong.common.utils;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.cps.JDUnionUtils;
import com.jingdong.common.web.IRouterParams;
import com.jingdong.common.web.managers.WebPerfManager;
import com.jingdong.common.web.ui.CommonMFragment;
import com.jingdong.jdma.JDMaInterface;
import com.jingdong.union.dependency.IJumpDispatchCallBack;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JDUnionJsUtil {
    public static final String ERR_NOERR = "0";
    public static final String ERR_PARAMSERR = "1";
    public static final String ERR_REQUESTERR = "2";

    private static String getWebUrl(Context context) {
        if (context instanceof BaseActivity) {
            Fragment findFragmentByTag = ((BaseActivity) context).getSupportFragmentManager().findFragmentByTag("TAG_CommonMFragment");
            if (findFragmentByTag instanceof CommonMFragment) {
                return ((CommonMFragment) findFragmentByTag).getUrl();
            }
        }
        return "";
    }

    public static void jumpJdUnion(final IRouterParams iRouterParams) {
        String str;
        String str2;
        if (iRouterParams == null) {
            return;
        }
        try {
            if (iRouterParams.getRouterParam() == null || TextUtils.isEmpty(iRouterParams.getRouterParam())) {
                str = "";
                str2 = str;
            } else {
                JSONObject jSONObject = new JSONObject(iRouterParams.getRouterParam());
                str2 = jSONObject.optString("url", "");
                str = jSONObject.optString("showloading", "0");
            }
            if (!TextUtils.isEmpty(str2)) {
                Bundle bundle = new Bundle();
                bundle.putString("url", str2);
                bundle.putString("from", "bridge");
                if (iRouterParams.getContext() != null) {
                    if (iRouterParams.getContext().getClass() != null) {
                        bundle.putString(WebPerfManager.PAGE_NAME, iRouterParams.getContext().getClass().getName());
                    }
                    bundle.putString("ref", getWebUrl(iRouterParams.getContext()));
                }
                IJumpDispatchCallBack iJumpDispatchCallBack = new IJumpDispatchCallBack() { // from class: com.jingdong.common.utils.JDUnionJsUtil.1
                    @Override // com.jingdong.union.dependency.IJumpDispatchCallBack
                    public void onDispatch(Context context, String str3, String str4, Bundle bundle2, String str5) {
                        IRouterParams.this.onCallBack(JDUnionJsUtil.resultJSonData(str3, str4, JDMaInterface.getUnpl(), "0"));
                    }

                    @Override // com.jingdong.union.dependency.IJumpDispatchCallBack
                    public void onFaile(Context context, String str3) {
                        IRouterParams.this.onCallBack(JDUnionJsUtil.resultJSonData(str3, "", "", "2"));
                    }
                };
                if ("0".equals(str)) {
                    JDUnionUtils.jumpUnionNoLoading(iRouterParams.getContext(), bundle, iJumpDispatchCallBack);
                    return;
                } else {
                    JDUnionUtils.jumpUnion(iRouterParams.getContext(), bundle, iJumpDispatchCallBack);
                    return;
                }
            }
            iRouterParams.onCallBack(resultJSonData("", "", "", "1"));
        } catch (Exception unused) {
            iRouterParams.onCallBack(resultJSonData("", "", "", "1"));
        }
    }

    public static JSONObject resultJSonData(String str, String str2, String str3, String str4) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("errCode", str4);
            jSONObject.put("unpl", str3);
            jSONObject.put("cpsUrl", str);
            jSONObject.put("desUrl", str2);
        } catch (Exception unused) {
        }
        return jSONObject;
    }
}
