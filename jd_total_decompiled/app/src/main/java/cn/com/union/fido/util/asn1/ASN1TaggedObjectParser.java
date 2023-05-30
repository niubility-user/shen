package cn.com.union.fido.util.asn1;

/* loaded from: classes.dex */
public interface ASN1TaggedObjectParser extends DEREncodable {
    DEREncodable getObjectParser(int i2, boolean z);

    int getTagNo();
}
