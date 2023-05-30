package com.jingdong.sdk.lib.puppetlayout.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.sdk.lib.puppetlayout.storage.model.BaseCommonModel;
import com.jingdong.sdk.lib.puppetlayout.storage.model.BaseTemplate;
import java.util.ArrayList;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes8.dex */
public class TemplateUtils {
    public static final String JD_PUPPET_FILE_NAME = "jd_puppet_template_all.json";
    public static final String SP_JD_PUPPET_VERSION = "jd_puppet_template_all_version";
    private static final String TEMPLATES_DD_SP_NAME = "com_jd_lib_sdk_puppet_dd";
    private static final String TEMPLATES_SP_NAME = "com_jd_lib_sdk_puppet";
    private static final String TEMPLATES_VERSION_NAME = "templates_version";

    public static String getDdTemplateXml(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String string = context.getSharedPreferences(TEMPLATES_DD_SP_NAME, 0).getString(str, "");
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        return context.getSharedPreferences(TEMPLATES_SP_NAME, 0).getString(string, "");
    }

    public static JSONArray getLocalStyles(Context context) {
        int lastIndexOf;
        JSONArray jSONArray = null;
        try {
            Map<String, ?> all = context.getSharedPreferences(TEMPLATES_SP_NAME, 0).getAll();
            if (all == null || all.size() <= 0) {
                return null;
            }
            JSONArray jSONArray2 = new JSONArray();
            try {
                for (String str : all.keySet()) {
                    try {
                        if (!TextUtils.isEmpty(str) && (lastIndexOf = str.lastIndexOf(CartConstant.KEY_YB_INFO_LINK)) != -1 && lastIndexOf < str.length() - 1) {
                            String substring = str.substring(0, lastIndexOf);
                            String substring2 = str.substring(lastIndexOf + 1, str.length());
                            if (!TextUtils.isEmpty(substring) && !TextUtils.isEmpty(substring2)) {
                                JSONObject jSONObject = new JSONObject();
                                jSONObject.put(XView2Constants.STYLEID, substring);
                                jSONObject.put("styleVersion", substring2);
                                jSONArray2.put(jSONObject);
                            }
                        }
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                }
                return jSONArray2;
            } catch (Exception e3) {
                e = e3;
                jSONArray = jSONArray2;
                e.printStackTrace();
                return jSONArray;
            }
        } catch (Exception e4) {
            e = e4;
        }
    }

    public static String getTemplateXml(Context context, @NonNull String str, @NonNull String str2) {
        String string = context.getSharedPreferences(TEMPLATES_SP_NAME, 0).getString(str + CartConstant.KEY_YB_INFO_LINK + str2, "");
        return TextUtils.isEmpty(string) ? DefaultTemplateUtils.getTemplateXml(context, str, str2) : string;
    }

    private static boolean saveAndGcTemplate(Context context, ArrayList<BaseTemplate> arrayList, ArrayList<BaseTemplate> arrayList2, int i2) {
        boolean z;
        boolean z2;
        if ((arrayList == null || arrayList.size() == 0) && (arrayList2 == null || arrayList2.size() == 0)) {
            return false;
        }
        SharedPreferences.Editor editor = null;
        SharedPreferences.Editor edit = context.getSharedPreferences(TEMPLATES_SP_NAME, 0).edit();
        if (i2 == 0) {
            edit.clear();
            editor = context.getSharedPreferences(TEMPLATES_DD_SP_NAME, 0).edit();
            editor.clear();
            z = true;
            z2 = true;
        } else if ((i2 == 1 || i2 == 2) && arrayList2 != null && arrayList2.size() > 0) {
            z = false;
            z2 = false;
            for (int i3 = 0; i3 < arrayList2.size(); i3++) {
                BaseTemplate baseTemplate = arrayList2.get(i3);
                if (baseTemplate != null && !TextUtils.isEmpty(baseTemplate.styleId) && !TextUtils.isEmpty(baseTemplate.styleVersion)) {
                    edit.remove(baseTemplate.styleId + CartConstant.KEY_YB_INFO_LINK + baseTemplate.styleVersion);
                    if (TextUtils.isEmpty(baseTemplate.ddTemplate)) {
                        z = true;
                    } else {
                        if (editor == null) {
                            editor = context.getSharedPreferences(TEMPLATES_DD_SP_NAME, 0).edit();
                        }
                        editor.remove(baseTemplate.ddTemplate);
                        z = true;
                        z2 = true;
                    }
                }
            }
        } else {
            z = false;
            z2 = false;
        }
        if (arrayList != null && arrayList.size() > 0) {
            for (int i4 = 0; i4 < arrayList.size(); i4++) {
                BaseTemplate baseTemplate2 = arrayList.get(i4);
                if (baseTemplate2.isValid()) {
                    edit.putString(baseTemplate2.styleId + CartConstant.KEY_YB_INFO_LINK + baseTemplate2.styleVersion, baseTemplate2.xml);
                    if (TextUtils.isEmpty(baseTemplate2.ddTemplate)) {
                        z = true;
                    } else {
                        if (editor == null) {
                            editor = context.getSharedPreferences(TEMPLATES_DD_SP_NAME, 0).edit();
                        }
                        editor.putString(baseTemplate2.ddTemplate, baseTemplate2.styleId + CartConstant.KEY_YB_INFO_LINK + baseTemplate2.styleVersion);
                        z = true;
                        z2 = true;
                    }
                }
            }
        }
        if (z) {
            edit.apply();
        }
        if (z2 && editor != null) {
            editor.apply();
        }
        return true;
    }

    public static synchronized boolean syncLocalData(Context context, JDJSONObject jDJSONObject, int i2) {
        ArrayList<BaseTemplate> arrayList;
        ArrayList<BaseTemplate> arrayList2;
        synchronized (TemplateUtils.class) {
            boolean z = false;
            if (jDJSONObject != null) {
                if (jDJSONObject.optInt("code", -1) == 0) {
                    BaseCommonModel baseCommonModel = (BaseCommonModel) JDJSON.parseObject(jDJSONObject.toJSONString(), BaseCommonModel.class);
                    if (baseCommonModel.isValid() && (((arrayList = baseCommonModel.data) != null && arrayList.size() > 0) || ((arrayList2 = baseCommonModel.gc) != null && arrayList2.size() > 0))) {
                        if (saveAndGcTemplate(context, baseCommonModel.data, baseCommonModel.gc, i2)) {
                            z = true;
                        }
                    }
                    return z;
                }
            }
            return false;
        }
    }

    public static synchronized boolean syncLocalData(Context context, JDJSONObject jDJSONObject, boolean z) {
        ArrayList<BaseTemplate> arrayList;
        synchronized (TemplateUtils.class) {
            boolean z2 = false;
            if (jDJSONObject != null) {
                if (jDJSONObject.optInt("code", -1) == 0) {
                    BaseCommonModel baseCommonModel = (BaseCommonModel) JDJSON.parseObject(jDJSONObject.toJSONString(), BaseCommonModel.class);
                    if (baseCommonModel.isValid() && (arrayList = baseCommonModel.data) != null && arrayList.size() > 0) {
                        if (saveAndGcTemplate(context, baseCommonModel.data, null, z ? 0 : 1)) {
                            z2 = true;
                        }
                    }
                    return z2;
                }
            }
            return false;
        }
    }
}
