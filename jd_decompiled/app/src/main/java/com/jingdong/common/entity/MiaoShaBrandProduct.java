package com.jingdong.common.entity;

import com.jingdong.common.utils.LangUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes5.dex */
public class MiaoShaBrandProduct {
    public long brandId;
    public String imageurl;
    public String jdPrice;
    public JumpEntity jump;
    public boolean miaoSha;
    public String miaoShaPrice;
    public long promotionId;
    public int seckillNum;
    public int skuOrder;
    public Integer soldRate;
    public String sourceValue;
    public String spuId;
    public String startTimeShow;
    public String tagText;
    public int tagType;
    public String wareId;
    public String wname;

    public String getJdPrice() {
        Double valueOf;
        try {
            String str = this.jdPrice;
            if (str != null && !LangUtils.SINGLE_SPACE.equals(str) && !"".equals(this.jdPrice) && (valueOf = Double.valueOf(this.jdPrice)) != null && valueOf.doubleValue() > 0.0d) {
                return new DecimalFormat("0.00").format(valueOf);
            }
            return LangUtils.SINGLE_SPACE;
        } catch (Exception unused) {
            return LangUtils.SINGLE_SPACE;
        }
    }

    public String getJdPriceWithOutZero() {
        Double valueOf;
        try {
            String str = this.jdPrice;
            if (str != null && !LangUtils.SINGLE_SPACE.equals(str) && !"".equals(this.jdPrice) && (valueOf = Double.valueOf(this.jdPrice)) != null && valueOf.doubleValue() > 0.0d) {
                return new DecimalFormat("#.##").format(valueOf);
            }
            return LangUtils.SINGLE_SPACE;
        } catch (Exception unused) {
            return LangUtils.SINGLE_SPACE;
        }
    }

    public String getMiaoShaPriceWithOutZero() {
        Double valueOf;
        try {
            String str = this.miaoShaPrice;
            if (str != null && !LangUtils.SINGLE_SPACE.equals(str) && !"".equals(this.miaoShaPrice) && (valueOf = Double.valueOf(this.miaoShaPrice)) != null && valueOf.doubleValue() > 0.0d) {
                return new DecimalFormat("#.##").format(valueOf);
            }
            return LangUtils.SINGLE_SPACE;
        } catch (Exception unused) {
            return LangUtils.SINGLE_SPACE;
        }
    }

    public String getWname() {
        String str = this.wname;
        return str != null ? str : LangUtils.SINGLE_SPACE;
    }

    public void setWname(String str) {
        if (str == null) {
            this.wname = str;
            return;
        }
        try {
            Matcher matcher = Pattern.compile("([^a-zA-Z0-9\uff08\uff09\\(\\) ])([a-zA-Z\uff08\\(])|([^ ])([\uff08\\(])|([\uff08\\(])([^ ])|([A-Z0-9])(\\-)|(\\-)([A-Z0-9])|([0-9]*[A-Z]+[0-9]*)([^a-zA-Z0-9\uff08\uff09\\(\\) ])").matcher(str);
            StringBuffer stringBuffer = new StringBuffer();
            while (matcher.find()) {
                StringBuffer stringBuffer2 = new StringBuffer();
                int i2 = 1;
                while (true) {
                    if (i2 > matcher.groupCount()) {
                        break;
                    } else if (matcher.group(i2) != null) {
                        stringBuffer2.append(matcher.group(i2));
                        stringBuffer2.append(LangUtils.SINGLE_SPACE);
                        stringBuffer2.append(matcher.group(i2 + 1));
                        break;
                    } else {
                        i2++;
                    }
                }
                if (OKLog.D) {
                    OKLog.d("Temp", "name -->> " + str);
                }
                if (OKLog.D) {
                    OKLog.d("Temp", "stringBuffer.toString() -->> " + stringBuffer.toString());
                }
                if (OKLog.D) {
                    OKLog.d("Temp", "sb.toString() -->> " + stringBuffer2.toString());
                }
                matcher.appendReplacement(stringBuffer, stringBuffer2.toString());
            }
            matcher.appendTail(stringBuffer);
            stringBuffer.append(LangUtils.SINGLE_SPACE);
            this.wname = stringBuffer.toString();
        } catch (Exception unused) {
            this.wname = str;
        }
    }
}
