package cn.com.union.fido.util.asn1;

import java.io.InputStream;

/* loaded from: classes.dex */
public class ASN1ObjectParser {
    ASN1StreamParser _aIn;

    protected ASN1ObjectParser(int i2, int i3, InputStream inputStream) {
        this._aIn = new ASN1StreamParser(inputStream);
    }
}
