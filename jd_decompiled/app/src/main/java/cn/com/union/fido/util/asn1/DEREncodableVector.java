package cn.com.union.fido.util.asn1;

import java.util.Vector;

/* loaded from: classes.dex */
public class DEREncodableVector {
    Vector v = new Vector();

    public void add(DEREncodable dEREncodable) {
        this.v.addElement(dEREncodable);
    }

    public DEREncodable get(int i2) {
        return (DEREncodable) this.v.elementAt(i2);
    }

    public int size() {
        return this.v.size();
    }
}
