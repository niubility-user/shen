package org.apache.commons.codec.language;

import com.jd.dynamic.DYConstants;
import com.jd.libs.jdmbridge.base.proxy.share.IShareAdapter;
import com.jingdong.jdsdk.auraSetting.AuraConstants;
import com.jingdong.jdsdk.constant.JshopConst;
import com.meizu.cloud.pushsdk.notification.model.NotifyType;
import java.util.Locale;

/* loaded from: classes11.dex */
public class Caverphone1 extends AbstractCaverphone {
    private static final String SIX_1 = "111111";

    @Override // org.apache.commons.codec.StringEncoder
    public String encode(String str) {
        if (str == null || str.length() == 0) {
            return SIX_1;
        }
        return (str.toLowerCase(Locale.ENGLISH).replaceAll("[^a-z]", "").replaceAll("^cough", "cou2f").replaceAll("^rough", "rou2f").replaceAll("^tough", "tou2f").replaceAll("^enough", "enou2f").replaceAll("^gn", "2n").replaceAll("mb$", "m2").replaceAll("cq", "2q").replaceAll("ci", "si").replaceAll("ce", "se").replaceAll("cy", "sy").replaceAll("tch", "2ch").replaceAll("c", "k").replaceAll("q", "k").replaceAll(JshopConst.JSHOP_PROMOTIO_X, "k").replaceAll("v", "f").replaceAll("dg", "2g").replaceAll("tio", "sio").replaceAll("tia", "sia").replaceAll("d", "t").replaceAll("ph", "fh").replaceAll("b", "p").replaceAll("sh", "s2").replaceAll("z", "s").replaceAll("^[aeiou]", "A").replaceAll("[aeiou]", "3").replaceAll("3gh3", "3kh3").replaceAll("gh", "22").replaceAll("g", "k").replaceAll(DYConstants.REGEX_s, "S").replaceAll("t+", "T").replaceAll("p+", IShareAdapter.SHARE_ACTION_PANE).replaceAll("k+", "K").replaceAll("f+", "F").replaceAll(DYConstants.REGEX_m, "M").replaceAll("n+", AuraConstants.MESSAGE_COUPON_TYPE_NEW).replaceAll("w3", "W3").replaceAll("wy", "Wy").replaceAll("wh3", "Wh3").replaceAll("why", "Why").replaceAll(JshopConst.JSHOP_PROMOTIO_W, "2").replaceAll("^h", "A").replaceAll(JshopConst.JSHOP_PROMOTIO_H, "2").replaceAll("r3", "R3").replaceAll("ry", "Ry").replaceAll("r", "2").replaceAll("l3", "L3").replaceAll("ly", "Ly").replaceAll(NotifyType.LIGHTS, "2").replaceAll("j", JshopConst.JSHOP_PROMOTIO_Y).replaceAll("y3", "Y3").replaceAll(JshopConst.JSHOP_PROMOTIO_Y, "2").replaceAll("2", "").replaceAll("3", "") + SIX_1).substring(0, 6);
    }
}
