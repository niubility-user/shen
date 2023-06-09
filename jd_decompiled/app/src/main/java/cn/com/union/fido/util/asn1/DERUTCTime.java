package cn.com.union.fido.util.asn1;

import com.tencent.connect.common.Constants;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

/* loaded from: classes.dex */
public class DERUTCTime extends ASN1Object {
    String time;

    public DERUTCTime(String str) {
        this.time = str;
        try {
            getDate();
        } catch (ParseException e2) {
            throw new IllegalArgumentException("invalid date string: " + e2.getMessage());
        }
    }

    public DERUTCTime(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMddHHmmss'Z'");
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        this.time = simpleDateFormat.format(date);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DERUTCTime(byte[] bArr) {
        int length = bArr.length;
        char[] cArr = new char[length];
        for (int i2 = 0; i2 != length; i2++) {
            cArr[i2] = (char) (bArr[i2] & 255);
        }
        this.time = new String(cArr);
    }

    public static DERUTCTime getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(aSN1TaggedObject.getObject());
    }

    public static DERUTCTime getInstance(Object obj) {
        if (obj == null || (obj instanceof DERUTCTime)) {
            return (DERUTCTime) obj;
        }
        if (obj instanceof ASN1OctetString) {
            return new DERUTCTime(((ASN1OctetString) obj).getOctets());
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

    @Override // cn.com.union.fido.util.asn1.ASN1Object
    boolean asn1Equals(DERObject dERObject) {
        if (dERObject instanceof DERUTCTime) {
            return this.time.equals(((DERUTCTime) dERObject).time);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // cn.com.union.fido.util.asn1.ASN1Object, cn.com.union.fido.util.asn1.DERObject
    public void encode(DEROutputStream dEROutputStream) {
        dEROutputStream.writeEncoded(23, getOctets());
    }

    public Date getAdjustedDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssz");
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        return simpleDateFormat.parse(getAdjustedTime());
    }

    public String getAdjustedTime() {
        String time = getTime();
        char charAt = time.charAt(0);
        return (charAt < '5' ? "20" : Constants.VIA_ACT_TYPE_NINETEEN).concat(String.valueOf(time));
    }

    public Date getDate() {
        return new SimpleDateFormat("yyMMddHHmmssz").parse(getTime());
    }

    public String getTime() {
        StringBuilder sb;
        String substring;
        if (this.time.indexOf(45) >= 0 || this.time.indexOf(43) >= 0) {
            int indexOf = this.time.indexOf(45);
            if (indexOf < 0) {
                indexOf = this.time.indexOf(43);
            }
            String str = this.time;
            if (indexOf == str.length() - 3) {
                str = str + "00";
            }
            if (indexOf == 10) {
                sb = new StringBuilder();
                sb.append(str.substring(0, 10));
                sb.append("00GMT");
                sb.append(str.substring(10, 13));
                sb.append(":");
                substring = str.substring(13, 15);
            } else {
                sb = new StringBuilder();
                sb.append(str.substring(0, 12));
                sb.append("GMT");
                sb.append(str.substring(12, 15));
                sb.append(":");
                substring = str.substring(15, 17);
            }
        } else if (this.time.length() == 11) {
            sb = new StringBuilder();
            sb.append(this.time.substring(0, 10));
            substring = "00GMT+00:00";
        } else {
            sb = new StringBuilder();
            sb.append(this.time.substring(0, 12));
            substring = "GMT+00:00";
        }
        sb.append(substring);
        return sb.toString();
    }

    @Override // cn.com.union.fido.util.asn1.ASN1Object, cn.com.union.fido.util.asn1.DERObject, cn.com.union.fido.util.asn1.ASN1Encodable
    public int hashCode() {
        return this.time.hashCode();
    }

    public String toString() {
        return this.time;
    }
}
