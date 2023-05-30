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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.Date getDate() {
        /*
            r8 = this;
            java.lang.String r0 = r8.time
            java.lang.String r1 = "Z"
            boolean r2 = r0.endsWith(r1)
            r3 = 0
            if (r2 == 0) goto L2b
            boolean r2 = r8.hasFractionalSeconds()
            if (r2 == 0) goto L1a
            java.text.SimpleDateFormat r2 = new java.text.SimpleDateFormat
            java.lang.String r4 = "yyyyMMddHHmmss.SSS'Z'"
            r2.<init>(r4)
            goto L22
        L1a:
            java.text.SimpleDateFormat r2 = new java.text.SimpleDateFormat
            java.lang.String r4 = "yyyyMMddHHmmss'Z'"
            r2.<init>(r4)
        L22:
            java.util.SimpleTimeZone r4 = new java.util.SimpleTimeZone
            r4.<init>(r3, r1)
        L27:
            r2.setTimeZone(r4)
            goto L8a
        L2b:
            java.lang.String r2 = r8.time
            r4 = 45
            int r2 = r2.indexOf(r4)
            if (r2 > 0) goto L69
            java.lang.String r2 = r8.time
            r4 = 43
            int r2 = r2.indexOf(r4)
            if (r2 <= 0) goto L40
            goto L69
        L40:
            boolean r1 = r8.hasFractionalSeconds()
            if (r1 == 0) goto L4f
            java.text.SimpleDateFormat r1 = new java.text.SimpleDateFormat
            java.lang.String r2 = "yyyyMMddHHmmss.SSS"
            r1.<init>(r2)
            goto L57
        L4f:
            java.text.SimpleDateFormat r1 = new java.text.SimpleDateFormat
            java.lang.String r2 = "yyyyMMddHHmmss"
            r1.<init>(r2)
        L57:
            r2 = r1
            java.util.SimpleTimeZone r1 = new java.util.SimpleTimeZone
            java.util.TimeZone r4 = java.util.TimeZone.getDefault()
            java.lang.String r4 = r4.getID()
            r1.<init>(r3, r4)
            r2.setTimeZone(r1)
            goto L8a
        L69:
            java.lang.String r0 = r8.getTime()
            boolean r2 = r8.hasFractionalSeconds()
            if (r2 == 0) goto L7c
            java.text.SimpleDateFormat r2 = new java.text.SimpleDateFormat
            java.lang.String r4 = "yyyyMMddHHmmss.SSSz"
            r2.<init>(r4)
            goto L84
        L7c:
            java.text.SimpleDateFormat r2 = new java.text.SimpleDateFormat
            java.lang.String r4 = "yyyyMMddHHmmssz"
            r2.<init>(r4)
        L84:
            java.util.SimpleTimeZone r4 = new java.util.SimpleTimeZone
            r4.<init>(r3, r1)
            goto L27
        L8a:
            boolean r1 = r8.hasFractionalSeconds()
            if (r1 == 0) goto Ldc
            r1 = 14
            java.lang.String r4 = r0.substring(r1)
            r5 = 1
        L97:
            int r6 = r4.length()
            if (r5 >= r6) goto Lac
            char r6 = r4.charAt(r5)
            r7 = 48
            if (r7 > r6) goto Lac
            r7 = 57
            if (r6 > r7) goto Lac
            int r5 = r5 + 1
            goto L97
        Lac:
            int r6 = r5 + (-1)
            r7 = 3
            if (r6 <= r7) goto Ldc
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r7 = 4
            java.lang.String r7 = r4.substring(r3, r7)
            r6.append(r7)
            java.lang.String r4 = r4.substring(r5)
            r6.append(r4)
            java.lang.String r4 = r6.toString()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r0 = r0.substring(r3, r1)
            r5.append(r0)
            r5.append(r4)
            java.lang.String r0 = r5.toString()
        Ldc:
            java.util.Date r0 = r2.parse(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.union.fido.util.asn1.DERGeneralizedTime.getDate():java.util.Date");
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
