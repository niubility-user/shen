package com.jingdong.common.unification.navigationbar.newbar;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.unification.navigationbar.NavigationParam;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.util.regex.Pattern;

/* loaded from: classes6.dex */
public class StateController {
    private static final int MAX_NUMBER = 100;
    private static final String MAX_STR = "99+";
    public int bucketType;
    private String buttonLabel;
    public String functionId;
    private IRefreshLabelListener labelListener;
    private int navigationId;
    private NavigationParam navigationParam;
    private Integer num;
    private IRefreshNumListener numListener;
    private String oldButtonLabel;
    private RedPointView view;
    private int labelLimitLength = 6;
    public String sourceId = "-100";
    public String impr = "";
    public String lablePosition = "-100";

    public StateController(RedPointView redPointView) {
        this.view = redPointView;
    }

    private void reportBubbleExp() {
        Context context;
        try {
            JDJSONObject jDJSONObject = new JDJSONObject();
            jDJSONObject.put("name", (Object) this.lablePosition);
            jDJSONObject.put("sourceid", (Object) this.sourceId);
            jDJSONObject.put("type", (Object) "3");
            jDJSONObject.put("text", (Object) this.buttonLabel);
            jDJSONObject.put("biinfo", (Object) (TextUtils.isEmpty(this.impr) ? "-100" : this.impr));
            jDJSONObject.put("isshow", (Object) (this.bucketType + ""));
            if (this.navigationParam != null) {
                jDJSONObject.put("iconstyle", (Object) (this.navigationParam.shapeType + ""));
                jDJSONObject.put("animationstyle", (Object) (this.navigationParam.dynamicType + ""));
            } else {
                jDJSONObject.put("iconstyle", (Object) "0");
                jDJSONObject.put("animationstyle", (Object) "0");
            }
            if (OKLog.D) {
                OKLog.d("bubble_expo", jDJSONObject.toJSONString());
            }
            RedPointView redPointView = this.view;
            if (redPointView == null || (context = redPointView.getContext()) == null) {
                return;
            }
            JDMtaUtils.sendExposureDataWithExt(context, "NavigationBar_BubbleExpo", "", "NavigationBar_Main", "", "", jDJSONObject.toJSONString(), null);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e("bubble_expo", e2.toString());
            }
        }
    }

    public void addNum(View view) {
        Integer num = this.num;
        setNum(Integer.valueOf(num != null ? 1 + num.intValue() : 1));
    }

    public String getButtonLabel() {
        try {
            if (TextUtils.isEmpty(this.buttonLabel)) {
                return "";
            }
            int i2 = 0;
            int i3 = 0;
            for (int i4 = 0; i4 < this.buttonLabel.length(); i4++) {
                i2 = isChinese(String.valueOf(this.buttonLabel.charAt(i4))) ? i2 + 2 : i2 + 1;
                if (i2 > this.labelLimitLength) {
                    break;
                }
                i3 = i4;
            }
            if (OKLog.D) {
                OKLog.d("NavigationGroup", "navigationId=" + this.navigationId + " getButtonLabel()=" + this.buttonLabel.substring(0, i3 + 1));
            }
            return this.buttonLabel.substring(0, i3 + 1);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e("NavigationGroup", "getButtonLabel()=" + e2);
            }
            return "";
        }
    }

    public int getNavigationId() {
        return this.navigationId;
    }

    public NavigationParam getNavigationParam() {
        return this.navigationParam;
    }

    public String getNum() {
        Integer num = this.num;
        if (num == null) {
            return null;
        }
        return num.intValue() >= 100 ? MAX_STR : this.num.toString();
    }

    public boolean isChinese(String str) {
        return !TextUtils.isEmpty(str) && Pattern.compile("[\u4e00-\u9fa5]").matcher(str).find();
    }

    public boolean isEnglish(String str) {
        return !TextUtils.isEmpty(str) && str.matches("^[a-zA-Z]*");
    }

    public boolean isNumber(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            return Pattern.compile("[0-9]*").matcher(str).matches();
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e("NavigationGroup", "isNumber=" + e2);
            }
            return false;
        }
    }

    public String replaceBlank(String str) {
        try {
            return !TextUtils.isEmpty(str) ? Pattern.compile("\\s*|\t|\r|\n").matcher(str).replaceAll("") : "";
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e("NavigationGroup", "replaceBlank()=" + e2);
                return "";
            }
            return "";
        }
    }

    public void setButtonLabel(String str) {
        setButtonLabel(str, "-100", "", 0);
    }

    public void setLabelListener(IRefreshLabelListener iRefreshLabelListener) {
        this.labelListener = iRefreshLabelListener;
    }

    public void setNavigationId(int i2) {
        this.navigationId = i2;
    }

    public void setNavigationParam(NavigationParam navigationParam) {
        this.navigationParam = navigationParam;
    }

    public void setNum(Integer num) {
        this.num = num;
        if (OKLog.D) {
            OKLog.d("NavigationGroup", "StateController_setNum=" + num + " buttonLabel=" + this.buttonLabel + " bucketType=" + this.bucketType);
        }
        if (TextUtils.isEmpty(this.buttonLabel) || this.bucketType == 1) {
            RedPointView redPointView = this.view;
            if (redPointView != null) {
                redPointView.drawNumAble(true);
                this.view.drawLabelEnable(false);
                this.view.invalidate();
            }
            if (OKLog.D) {
                OKLog.d("NavigationGroup", "StateController_setNum=" + num + " numListener=" + this.numListener);
            }
            IRefreshNumListener iRefreshNumListener = this.numListener;
            if (iRefreshNumListener != null) {
                iRefreshNumListener.refreshNavigationNum(getNum());
            }
        }
    }

    public void setNumListener(IRefreshNumListener iRefreshNumListener) {
        this.numListener = iRefreshNumListener;
    }

    public void setButtonLabel(String str, int i2) {
        setButtonLabel(str, "-100", "", i2);
    }

    public void setButtonLabel(String str, String str2, String str3, int i2) {
        Integer num;
        this.buttonLabel = replaceBlank(str);
        this.sourceId = str2;
        this.impr = str3;
        this.bucketType = i2;
        if (OKLog.D) {
            OKLog.d("NavigationGroup", "setButtonLabel_buttonLabel=" + replaceBlank(str) + " labelListener=" + this.labelListener + " bucketType=" + i2 + " oldButtonLabel=" + this.oldButtonLabel + " num=" + this.num);
        }
        RedPointView redPointView = this.view;
        if (redPointView != null) {
            if (i2 != 1) {
                redPointView.drawNumAble(false);
                this.view.drawLabelEnable(true);
                this.view.invalidate();
            }
            if (!TextUtils.isEmpty(this.buttonLabel) && !TextUtils.equals(this.buttonLabel, this.oldButtonLabel)) {
                reportBubbleExp();
                this.oldButtonLabel = this.buttonLabel;
            }
        }
        IRefreshLabelListener iRefreshLabelListener = this.labelListener;
        if (iRefreshLabelListener != null && i2 != 1) {
            iRefreshLabelListener.refreshNavigationLabel(getButtonLabel());
        }
        if ((TextUtils.isEmpty(str) || (!TextUtils.isEmpty(str) && i2 == 1)) && (num = this.num) != null && num.intValue() > 0) {
            setNum(this.num);
        }
    }
}
