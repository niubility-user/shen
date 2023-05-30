package cn.com.union.fido.util.asn1.x509;

import cn.com.union.fido.util.asn1.ASN1InputStream;
import cn.com.union.fido.util.asn1.DERObject;
import cn.com.union.fido.util.asn1.DERObjectIdentifier;
import cn.com.union.fido.util.asn1.DERPrintableString;
import cn.com.union.fido.util.asn1.util.Strings;

/* loaded from: classes.dex */
public abstract class X509NameEntryConverter {
    protected boolean canBePrintable(String str) {
        return DERPrintableString.isPrintableString(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public DERObject convertHexEncoded(String str, int i2) {
        String lowerCase = Strings.toLowerCase(str);
        int length = (lowerCase.length() - i2) / 2;
        byte[] bArr = new byte[length];
        for (int i3 = 0; i3 != length; i3++) {
            int i4 = (i3 * 2) + i2;
            char charAt = lowerCase.charAt(i4);
            char charAt2 = lowerCase.charAt(i4 + 1);
            if (charAt < 'a') {
                bArr[i3] = (byte) ((charAt - '0') << 4);
            } else {
                bArr[i3] = (byte) (((charAt - 'a') + 10) << 4);
            }
            if (charAt2 < 'a') {
                bArr[i3] = (byte) (((byte) (charAt2 - '0')) | bArr[i3]);
            } else {
                bArr[i3] = (byte) (((byte) ((charAt2 - 'a') + 10)) | bArr[i3]);
            }
        }
        return new ASN1InputStream(bArr).readObject();
    }

    public abstract DERObject getConvertedValue(DERObjectIdentifier dERObjectIdentifier, String str);
}
