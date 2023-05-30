package com.jingdong.common.entity;

import android.text.TextUtils;
import com.facebook.react.uimanager.ViewProps;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.res.StringUtil;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import java.io.Serializable;
import java.text.DecimalFormat;

/* loaded from: classes5.dex */
public class ProductDetailPrice implements Serializable {
    private static final String TAG = ProductDetailPrice.class.getSimpleName();
    private static final long serialVersionUID = 3249628065815452856L;
    private Boolean display;
    private String name;
    private String value;

    public ProductDetailPrice() {
    }

    private void update(JDJSONObject jDJSONObject, int i2) {
        if (i2 == 3 || i2 == 1112 || i2 == 1113) {
            setName(jDJSONObject.getString("name"));
            setValue(jDJSONObject.getString("value"));
            setDisplay(jDJSONObject.getBoolean(ViewProps.DISPLAY));
        }
    }

    public String getDefaultValue() {
        return this.value;
    }

    public Boolean getDisplay() {
        Boolean bool = this.display;
        return Boolean.valueOf(bool == null ? false : bool.booleanValue());
    }

    public String getName() {
        return TextUtils.isEmpty(this.name) ? "" : this.name;
    }

    public String getValue() {
        Double valueOf;
        try {
            String str = this.value;
            if (str != null && (valueOf = Double.valueOf(str)) != null && valueOf.doubleValue() > 0.0d) {
                return new DecimalFormat("0.00").format(valueOf);
            }
            return StringUtil.no_price;
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
                return StringUtil.no_price;
            }
            return StringUtil.no_price;
        }
    }

    public void setDisplay(Boolean bool) {
        this.display = bool;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setValue(String str) {
        this.value = str;
    }

    public ProductDetailPrice(JSONObjectProxy jSONObjectProxy, int i2) {
        if (jSONObjectProxy == null) {
            return;
        }
        update(JDJSON.parseObject(jSONObjectProxy.toString()), i2);
    }

    public ProductDetailPrice(JDJSONObject jDJSONObject, int i2) {
        update(jDJSONObject, i2);
    }

    public ProductDetailPrice(String str) {
        setValue(str);
    }
}
