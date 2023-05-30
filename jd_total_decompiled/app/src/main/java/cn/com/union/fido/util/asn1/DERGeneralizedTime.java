package cn.com.union.fido.util.asn1;

import com.jd.lib.un.utils.UnTimeUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import org.eclipse.paho.client.mqttv3.MqttTopic;

/* loaded from: classes.dex */
public class DERGeneralizedTime extends ASN1Object {
    String time;

    public DERGeneralizedTime(String str) {
        this.time = str;
        try {
            getDate();
        } catch (ParseException e2) {
            throw new IllegalArgumentException("invalid date string: " + e2.getMessage());
        }
    }

    public DERGeneralizedTime(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss'Z'");
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        this.time = simpleDateFormat.format(date);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DERGeneralizedTime(byte[] bArr) {
        int length = bArr.length;
        char[] cArr = new char[length];
        for (int i2 = 0; i2 != length; i2++) {
            cArr[i2] = (char) (bArr[i2] & 255);
        }
        this.time = new String(cArr);
    }

    private String calculateGMTOffset() {
        String str;
        TimeZone timeZone = TimeZone.getDefault();
        int rawOffset = timeZone.getRawOffset();
        if (rawOffset < 0) {
            rawOffset = -rawOffset;
            str = "-";
        } else {
            str = MqttTopic.SINGLE_LEVEL_WILDCARD;
        }
        int i2 = rawOffset / UnTimeUtils.HOUR;
        int i3 = (rawOffset - (((i2 * 60) * 60) * 1000)) / 60000;
        try {
            if (timeZone.useDaylightTime() && timeZone.inDaylightTime(getDate())) {
                i2 += str.equals(MqttTopic.SINGLE_LEVEL_WILDCARD) ? 1 : -1;
            }
        } catch (ParseException unused) {
        }
        return "GMT" + str + convert(i2) + ":" + convert(i3);
    }

    private String convert(int i2) {
        return i2 < 10 ? "0".concat(String.valueOf(i2)) : Integer.toString(i2);
    }

    public static DERGeneralizedTime getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(aSN1TaggedObject.getObject());
    }

    public static DERGeneralizedTime getInstance(Object obj) {
        if (obj == null || (obj instanceof DERGeneralizedTime)) {
            return (DERGeneralizedTime) obj;
        }
        if (obj instanceof ASN1OctetString) {
            return new DERGeneralizedTime(((ASN1OctetString) obj).getOctets());
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
    }

    private byte[] getOctets() {
        char[] charArray = this.time.toCharArray();
        byte[] bArr = new byte[charArray.length];
        for (int i2 = 0; i2 != charArray.length; i2++) {
            bArr[i2] = (byte) charArray[i2];
        }
        return bArr;
    }

    private boolean hasFractionalSeconds() {
        return this.time.indexOf(46) == 14;
    }

    @Override // cn.com.union.fido.util.asn1.ASN1Object
    boolean asn1Equals(DERObject dERObject) {
        if (dERObject instanceof DERGeneralizedTime) {
            return this.time.equals(((DERGeneralizedTime) dERObject).time);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // cn.com.union.fido.util.asn1.ASN1Object, cn.com.union.fido.util.asn1.DERObject
    public void encode(DEROutputStream dEROutputStream) {
        dEROutputStream.writeEncoded(24, getOctets());
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0090  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Date getDate() {
        SimpleDateFormat simpleDateFormat;
        SimpleTimeZone simpleTimeZone;
        char charAt;
        String str = this.time;
        if (str.endsWith("Z")) {
            simpleDateFormat = hasFractionalSeconds() ? new SimpleDateFormat("yyyyMMddHHmmss.SSS'Z'") : new SimpleDateFormat("yyyyMMddHHmmss'Z'");
            simpleTimeZone = new SimpleTimeZone(0, "Z");
        } else if (this.time.indexOf(45) <= 0 && this.time.indexOf(43) <= 0) {
            simpleDateFormat = hasFractionalSeconds() ? new SimpleDateFormat("yyyyMMddHHmmss.SSS") : new SimpleDateFormat("yyyyMMddHHmmss");
            simpleDateFormat.setTimeZone(new SimpleTimeZone(0, TimeZone.getDefault().getID()));
            if (hasFractionalSeconds()) {
                String substring = str.substring(14);
                int i2 = 1;
                while (i2 < substring.length() && '0' <= (charAt = substring.charAt(i2)) && charAt <= '9') {
                    i2++;
                }
                if (i2 - 1 > 3) {
                    str = str.substring(0, 14) + (substring.substring(0, 4) + substring.substring(i2));
                }
            }
            return simpleDateFormat.parse(str);
        } else {
            str = getTime();
            simpleDateFormat = hasFractionalSeconds() ? new SimpleDateFormat("yyyyMMddHHmmss.SSSz") : new SimpleDateFormat("yyyyMMddHHmmssz");
            simpleTimeZone = new SimpleTimeZone(0, "Z");
        }
        simpleDateFormat.setTimeZone(simpleTimeZone);
        if (hasFractionalSeconds()) {
        }
        return simpleDateFormat.parse(str);
    }

    public String getTime() {
        if (this.time.charAt(r0.length() - 1) == 'Z') {
            StringBuilder sb = new StringBuilder();
            sb.append(this.time.substring(0, r2.length() - 1));
            sb.append("GMT+00:00");
            return sb.toString();
        }
        int length = this.time.length() - 5;
        char charAt = this.time.charAt(length);
        if (charAt == '-' || charAt == '+') {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(this.time.substring(0, length));
            sb2.append("GMT");
            int i2 = length + 3;
            sb2.append(this.time.substring(length, i2));
            sb2.append(":");
            sb2.append(this.time.substring(i2));
            return sb2.toString();
        }
        int length2 = this.time.length() - 3;
        char charAt2 = this.time.charAt(length2);
        if (charAt2 != '-' && charAt2 != '+') {
            return this.time + calculateGMTOffset();
        }
        return this.time.substring(0, length2) + "GMT" + this.time.substring(length2) + ":00";
    }

    public String getTimeString() {
        return this.time;
    }

    @Override // cn.com.union.fido.util.asn1.ASN1Object, cn.com.union.fido.util.asn1.DERObject, cn.com.union.fido.util.asn1.ASN1Encodable
    public int hashCode() {
        return this.time.hashCode();
    }
}
