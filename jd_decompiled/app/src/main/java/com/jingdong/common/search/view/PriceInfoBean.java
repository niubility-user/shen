package com.jingdong.common.search.view;

import com.jd.framework.json.JDJSONObject;
import java.io.Serializable;

/* loaded from: classes6.dex */
public class PriceInfoBean implements Serializable {
    public String alwaysShow;
    public MtaData bpData;
    public String color;
    public String colorDark;
    public String decFont;
    public String describe;
    public String font;
    public String fontFamily;
    public String iconCode;
    public String iconCodeDark;
    public String iconText;
    public String leftMargin;
    public JDJSONObject mta;
    public String noPriceMsg;
    public String noPriceMsgFont;
    public String price;
    public String priceType;
    public String text;
    public String textLineFlag;
    public String type;
    public String uniqueId;
    public String unitFont;
    public String zoominRatio;

    /* loaded from: classes6.dex */
    public static class MtaData implements Serializable {
        public String toHandNum;
    }
}
