package cn.com.union.fido.ui;

import com.jd.dynamic.DYConstants;
import com.jdcn.fido.sdk.IFidoCallback;

/* loaded from: classes.dex */
public class FIDOUISDK {
    public static int abnormalHintColor = 0;
    public static String abnormalHintText = "";
    public static int btnBottomColor = 0;
    public static String btnBottomText = "";
    public static int btnLeftColor = 0;
    public static String btnLeftText = "";
    public static int btnRinghtColor = 0;
    public static String btnRinghtText = "";
    private static IFidoCallback callback = null;
    public static IActivityPresenter fidoPresenter = null;
    public static String hasOtherAuthMode = "true";
    public static int normalHintColor = 0;
    public static String normalHintText = "";

    public static int getAbnormalHintColor() {
        return abnormalHintColor;
    }

    public static String getAbnormalHintText() {
        return abnormalHintText;
    }

    public static int getBtnBottomColor() {
        return btnBottomColor;
    }

    public static String getBtnBottomText() {
        return btnBottomText;
    }

    public static int getBtnLeftColor() {
        return btnLeftColor;
    }

    public static String getBtnLeftText() {
        return btnLeftText;
    }

    public static int getBtnRinghtColor() {
        return btnRinghtColor;
    }

    public static String getBtnRinghtText() {
        return btnRinghtText;
    }

    public static IFidoCallback getCallback() {
        return callback;
    }

    public static IActivityPresenter getFidoPresenter() {
        return fidoPresenter;
    }

    public static String getHasOtherAuthMode() {
        return hasOtherAuthMode;
    }

    public static int getNormalHintColor() {
        return normalHintColor;
    }

    public static String getNormalHintText() {
        return normalHintText;
    }

    public static void paramsReset() {
        normalHintText = "";
        normalHintColor = 0;
        btnBottomText = "";
        btnBottomColor = 0;
        abnormalHintText = "";
        abnormalHintColor = 0;
        btnLeftText = "";
        btnLeftColor = 0;
        btnRinghtText = "";
        btnRinghtColor = 0;
        hasOtherAuthMode = DYConstants.DY_TRUE;
        callback = null;
    }

    public static void setAbnormalHintColor(int i2) {
        abnormalHintColor = i2;
    }

    public static void setAbnormalHintText(String str) {
        abnormalHintText = str;
    }

    public static void setBtnBottomColor(int i2) {
        btnBottomColor = i2;
    }

    public static void setBtnBottomText(String str) {
        btnBottomText = str;
    }

    public static void setBtnLeftColor(int i2) {
        btnLeftColor = i2;
    }

    public static void setBtnLeftText(String str) {
        btnLeftText = str;
    }

    public static void setBtnRinghtColor(int i2) {
        btnRinghtColor = i2;
    }

    public static void setBtnRinghtText(String str) {
        btnRinghtText = str;
    }

    public static void setCallback(IFidoCallback iFidoCallback) {
        callback = iFidoCallback;
    }

    public static void setFidoPresenter(IActivityPresenter iActivityPresenter) {
        fidoPresenter = iActivityPresenter;
    }

    public static void setHasOtherAuthMode(String str) {
        hasOtherAuthMode = str;
    }

    public static void setNormalHintColor(int i2) {
        normalHintColor = i2;
    }

    public static void setNormalHintText(String str) {
        normalHintText = str;
    }
}
