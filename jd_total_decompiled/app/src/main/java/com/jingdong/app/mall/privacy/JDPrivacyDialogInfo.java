package com.jingdong.app.mall.privacy;

import android.app.Activity;
import android.graphics.Color;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class JDPrivacyDialogInfo {
    private static final String TAG = "JDPrivacyDialogInfo";
    TextInfo agreeText;
    String channelId;
    String configKey;
    TextInfo disagreeText;
    private final JDJSONObject expoJson = new JDJSONObject();
    private boolean hasTimes;
    int maxTimes;
    String source;
    List<TextInfo> textList;
    String timesKey;
    private String timesSaveKey;
    TextInfo title;
    String version;

    static int getColor(String str, int i2) {
        if (TextUtils.isEmpty(str)) {
            return i2;
        }
        try {
            return Color.parseColor(TextUtils.split(str.trim(), DYConstants.DY_REGEX_COMMA)[0].trim());
        } catch (Exception e2) {
            e2.printStackTrace();
            return i2;
        }
    }

    private void log(String str) {
    }

    private JDPrivacyDialogInfo setAgreeText(TextInfo textInfo) {
        if (textInfo != null && textInfo.isValid()) {
            this.agreeText = textInfo;
        } else {
            this.agreeText = new TextInfo("\u540c\u610f");
        }
        return this;
    }

    private JDPrivacyDialogInfo setDisagreeText(TextInfo textInfo) {
        if (textInfo != null && textInfo.isValid()) {
            this.disagreeText = textInfo;
        } else {
            this.disagreeText = new TextInfo("\u4e0d\u540c\u610f");
        }
        return this;
    }

    private JDPrivacyDialogInfo setTextList(List<TextInfo> list) {
        this.textList = list;
        return this;
    }

    private JDPrivacyDialogInfo setTitle(TextInfo textInfo) {
        if (textInfo != null && textInfo.isValid()) {
            this.title = textInfo;
        } else {
            this.title = new TextInfo("\u9690\u79c1\u653f\u7b56\u6807\u9898");
        }
        return this;
    }

    private JDPrivacyDialogInfo setVersion(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.version = str;
        } else {
            this.version = "1.0.0";
        }
        setTimesSaveKey();
        return this;
    }

    int addShowTimes() {
        if (TextUtils.isEmpty(this.timesSaveKey)) {
            log("\u8ba1\u6b21\u5b58\u50a8Key\u521d\u59cb\u5316\u5f02\u5e38\uff0c\u672a\u751f\u6210\u6709\u6548\u7684\u8ba1\u6b21\u4fe1\u606f");
            return Integer.MAX_VALUE;
        }
        int max = Math.max(JDPrivacyDialogUtil.getShowTimes(this.timesSaveKey), JDPrivacyFile.getIntValue(this.timesSaveKey)) + 1;
        CommonBase.getJdSharedPreferences().edit().putInt(this.timesSaveKey, max).apply();
        JDPrivacyFile.saveIntValue(this.timesSaveKey, max);
        return max;
    }

    public JDPrivacyDialogInfo build(String str, String str2, String str3) {
        return build(str, str2, str3, str3, 1);
    }

    int getShowTimes() {
        if (TextUtils.isEmpty(this.timesSaveKey)) {
            log("\u8ba1\u6b21\u5b58\u50a8Key\u521d\u59cb\u5316\u5f02\u5e38\uff0c\u672a\u751f\u6210\u6709\u6548\u7684\u8ba1\u6b21\u4fe1\u606f");
            return Integer.MAX_VALUE;
        }
        return Math.max(JDPrivacyDialogUtil.getShowTimes(this.timesSaveKey), JDPrivacyFile.getIntValue(this.timesSaveKey));
    }

    public List<TextInfo> getTextList() {
        return this.textList;
    }

    public boolean hasTimes() {
        return this.hasTimes;
    }

    public boolean isValid() {
        List<TextInfo> list = this.textList;
        return list != null && list.size() > 0;
    }

    public void onAgree() {
        sendClick("ChannelPrivacyToast_Agree", this.expoJson.toJSONString());
        addShowTimes();
    }

    void onClickUrl(String str) {
        JDJSONObject jDJSONObject = new JDJSONObject();
        jDJSONObject.put("source", (Object) (TextUtils.isEmpty(this.source) ? "" : this.source));
        jDJSONObject.put("channelid", (Object) (TextUtils.isEmpty(this.channelId) ? "" : this.channelId));
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        jDJSONObject.put("url", (Object) str);
        sendClick("ChannelPrivacyToast_Url", jDJSONObject.toJSONString());
    }

    public void onDisagree() {
        sendClick("ChannelPrivacyToast_disagree", this.expoJson.toJSONString());
    }

    public void onShow() {
        JDMtaUtils.sendExposureDataWithExt(JdSdk.getInstance().getApplicationContext(), "ChannelPrivacyToastExpo", "", "", "", "", this.expoJson.toJSONString(), null);
    }

    void sendClick(String str, String str2) {
        JDMtaUtils.sendClickDataWithExt(JdSdk.getInstance().getApplicationContext(), str, "", "", "", "", "", "", str2, null);
    }

    public JDPrivacyDialogInfo setJsonInfo(JDJSONObject jDJSONObject) {
        if (jDJSONObject != null) {
            if (jDJSONObject.size() >= 1) {
                JDJSONArray optJSONArray = jDJSONObject.optJSONArray("texts");
                if (optJSONArray == null) {
                    log("\u672a\u914d\u7f6e\u6587\u672c\u5217\u8868\u4fe1\u606f\uff0c\u8bf7\u68c0\u67e5\u914d\u7f6e");
                    return this;
                }
                this.expoJson.put("source", (Object) (TextUtils.isEmpty(this.source) ? "" : this.source));
                this.expoJson.put("channelid", (Object) (TextUtils.isEmpty(this.channelId) ? "" : this.channelId));
                setVersion(jDJSONObject.optString("version", "1.0.0"));
                setTitle(new TextInfo(jDJSONObject.optJSONObject("title")));
                setAgreeText(new TextInfo(jDJSONObject.optJSONObject("agree")));
                setDisagreeText(new TextInfo(jDJSONObject.optJSONObject("disAgree")));
                int showTimes = getShowTimes();
                boolean z = showTimes < this.maxTimes;
                this.hasTimes = z;
                if (!z) {
                    log(this.timesSaveKey + " :\u5f39\u7a97\u6b21\u6570\u4e0d\u8db3\uff0c\u5df2\u5f39\u51fa\uff1a " + showTimes + " \u6b21\uff0c\u6700\u5927\u53ef\u5f39\u51fa\uff1a " + this.maxTimes + " \u6b21");
                    return this;
                }
                log(this.timesSaveKey + " :\u5f39\u7a97\u6b21\u6570\uff0c\u5df2\u5f39\u51fa\uff1a " + showTimes + " \u6b21\uff0c\u6700\u5927\u53ef\u5f39\u51fa\uff1a " + this.maxTimes + " \u6b21");
                int size = optJSONArray.size();
                ArrayList arrayList = new ArrayList();
                for (int i2 = 0; i2 < size; i2++) {
                    arrayList.add(new TextInfo(optJSONArray.getJSONObject(i2)));
                }
                setTextList(arrayList);
                return this;
            }
        }
        log("\u53c2\u6570\u4fe1\u606f\u672a\u83b7\u53d6\uff0c\u8bf7\u68c0\u67e5\u4f20\u5165\u4fe1\u606f \u6216 \u68c0\u67e5SwitchQuery\u63a5\u53e3\u662f\u5426\u8fd4\u56de\u53c2\u6570");
        return this;
    }

    void setTimesSaveKey() {
        if (TextUtils.isEmpty(this.timesKey)) {
            return;
        }
        this.timesSaveKey = this.timesKey.concat(CartConstant.KEY_YB_INFO_LINK).concat(this.version);
    }

    public JDPrivacyDialogInfo build(String str, String str2, String str3, String str4) {
        return build(str, str2, str3, str4, 1);
    }

    public JDPrivacyDialogInfo build(String str, String str2, String str3, String str4, int i2) {
        this.source = str;
        this.channelId = str2;
        this.timesKey = str3;
        this.configKey = str4;
        this.maxTimes = Math.max(i2, 1);
        if (!TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str4)) {
            setJsonInfo(JDJSON.parseObject(SwitchQueryFetcher.getSwitchStringValue(str4, "")));
            return this;
        }
        log("timesKey/configKey \u4e0d\u53ef\u4ee5\u4e3a\u7a7a");
        return this;
    }

    /* loaded from: classes4.dex */
    public static class TextInfo {
        String bgColor;
        int startIndex;
        String text;
        String textColor;
        JDJSONObject textObj;
        String textUrl;

        public TextInfo(JDJSONObject jDJSONObject) {
            if (jDJSONObject == null) {
                return;
            }
            this.textObj = jDJSONObject;
            this.text = jDJSONObject.optString("text", "");
            this.textColor = jDJSONObject.optString("color", "");
            this.bgColor = jDJSONObject.optString(DYConstants.DY_BG_COLOR, "");
            this.textUrl = jDJSONObject.optString("url", "");
        }

        public int getBgColor(int i2) {
            return JDPrivacyDialogInfo.getColor(this.bgColor, i2);
        }

        public int getEndIndex() {
            return this.startIndex + this.text.length();
        }

        public int getStartIndex() {
            return this.startIndex;
        }

        public int getTextColor(int i2) {
            return JDPrivacyDialogInfo.getColor(this.textColor, i2);
        }

        public boolean isStopSign() {
            return TextUtils.equals("\u3001", this.text);
        }

        public boolean isValid() {
            return !TextUtils.isEmpty(this.text);
        }

        public boolean needSpan() {
            return !TextUtils.isEmpty(this.textUrl) || isStopSign();
        }

        public void onClickSpan(Activity activity, JDPrivacyDialogInfo jDPrivacyDialogInfo) {
            jDPrivacyDialogInfo.onClickUrl(this.textUrl);
            JDPrivacyManager.getInstance().startAppWebActivity(activity, this.textUrl);
        }

        public void setStartIndex(int i2) {
            this.startIndex = i2;
        }

        public TextInfo(String str) {
            this.text = str;
        }

        public TextInfo(String str, String str2) {
            this.text = str;
            this.textColor = str2;
        }

        public TextInfo(String str, String str2, String str3, String str4) {
            this.text = str;
            this.textColor = str2;
            this.bgColor = str3;
            this.textUrl = str4;
        }
    }
}
