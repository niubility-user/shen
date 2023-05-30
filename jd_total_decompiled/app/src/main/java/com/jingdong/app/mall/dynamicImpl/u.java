package com.jingdong.app.mall.dynamicImpl;

import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.widget.TextView;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.base.interfaces.IUniConfigWithAdapter;
import com.jingdong.common.unification.uniconfig.UnIconConfigHelper;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.sdk.bmode.util.JDBModeUtils;
import java.util.List;

/* loaded from: classes3.dex */
public class u implements IUniConfigWithAdapter {
    @Override // com.jd.dynamic.base.interfaces.IUniConfig
    public Bitmap getBitmap(String str) {
        return UnIconConfigHelper.getBitmap(str);
    }

    @Override // com.jd.dynamic.base.interfaces.IUniConfigWithAdapter
    public String getFontSizeLevel() {
        return o.f() ? "1" : "0";
    }

    @Override // com.jd.dynamic.base.interfaces.IUniConfigWithAdapter
    public String getModeInfo(List<String> list) {
        return list == null ? "" : o.c(list);
    }

    @Override // com.jd.dynamic.base.interfaces.IUniConfigWithAdapter
    public float getTextSize(List<Float> list) {
        return o.e(JdSdk.getInstance().getApplicationContext(), list);
    }

    @Override // com.jd.dynamic.base.interfaces.IUniConfig
    public TextView getTextViewOrNull(String str, String str2) {
        return UnIconConfigHelper.getTextViewOrNull(str, str2);
    }

    @Override // com.jd.dynamic.base.interfaces.IUniConfigWithAdapter
    public String getThemeMode() {
        return JDBModeUtils.getCurrentMode();
    }

    @Override // com.jd.dynamic.base.interfaces.IUniConfig
    public Typeface getTypefaceWithName(String str) {
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case -2111534369:
                if (str.equals(DYConstants.DY_JD_BOLD)) {
                    c2 = 0;
                    break;
                }
                break;
            case -1616224575:
                if (str.equals(DYConstants.DY_JD_NORMAL)) {
                    c2 = 1;
                    break;
                }
                break;
            case -1310119795:
                if (str.equals(DYConstants.DY_JD_ZH_BOLD)) {
                    c2 = 2;
                    break;
                }
                break;
            case -1024004100:
                if (str.equals(DYConstants.DY_JD_LIGHT)) {
                    c2 = 3;
                    break;
                }
                break;
            case -255964945:
                if (str.equals(DYConstants.DY_JD_ZH_NORMAL)) {
                    c2 = 4;
                    break;
                }
                break;
            case 2272744:
                if (str.equals("JDZH")) {
                    c2 = 5;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                return FontsUtil.getTypeFace(JdSdk.getInstance().getApplicationContext(), 4097);
            case 1:
                return FontsUtil.getTypeFace(JdSdk.getInstance().getApplicationContext(), 4099);
            case 2:
            case 5:
                return FontsUtil.getTypeFace(JdSdk.getInstance().getApplicationContext(), 4099);
            case 3:
                return FontsUtil.getTypeFace(JdSdk.getInstance().getApplicationContext(), 4098);
            case 4:
                return FontsUtil.getTypeFace(JdSdk.getInstance().getApplicationContext(), 4098);
            default:
                return null;
        }
    }

    @Override // com.jd.dynamic.base.interfaces.IUniConfig
    public void setTextViewProperties(String str, TextView textView) {
        UnIconConfigHelper.setTextViewProperties(str, textView);
    }
}
