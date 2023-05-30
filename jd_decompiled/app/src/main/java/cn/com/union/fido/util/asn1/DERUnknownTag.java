package cn.com.union.fido.util.asn1;

import cn.com.union.fido.util.asn1.util.Arrays;

/* loaded from: classes.dex */
public class DERUnknownTag extends DERObject {
    private byte[] data;
    private boolean isConstructed;
    private int tag;

    public DERUnknownTag(int i2, byte[] bArr) {
        this(false, i2, bArr);
    }

    public DERUnknownTag(boolean z, int i2, byte[] bArr) {
        this.isConstructed = z;
        this.tag = i2;
        this.data = bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // cn.com.union.fido.util.asn1.DERObject
    public void encode(DEROutputStream dEROutputStream) {
        dEROutputStream.writeEncoded(this.isConstructed ? 32 : 0, this.tag, this.data);
    }

    @Override // cn.com.union.fido.util.asn1.DERObject, cn.com.union.fido.util.asn1.ASN1Encodable
    public boolean equals(Object obj) {
        if (obj instanceof DERUnknownTag) {
            DERUnknownTag dERUnknownTag = (DERUnknownTag) obj;
            return this.isConstructed == dERUnknownTag.isConstructed && this.tag == dERUnknownTag.tag && Arrays.areEqual(this.data, dERUnknownTag.data);
        }
        return false;
    }

    public byte[] getData() {
        return this.data;
    }

    public int getTag() {
        return this.tag;
    }

    @Override // cn.com.union.fido.util.asn1.DERObject, cn.com.union.fido.util.asn1.ASN1Encodable
    public int hashCode() {
        return ((this.isConstructed ? -1 : 0) ^ this.tag) ^ Arrays.hashCode(this.data);
    }

    public boolean isConstructed() {
        return this.isConstructed;
    }
}
