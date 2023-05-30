package cn.com.union.fido.util.asn1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

/* loaded from: classes.dex */
public class BERConstructedOctetString extends DEROctetString {
    private static final int MAX_LENGTH = 1000;
    private Vector octs;

    public BERConstructedOctetString(DEREncodable dEREncodable) {
        super(dEREncodable.getDERObject());
    }

    public BERConstructedOctetString(DERObject dERObject) {
        super(dERObject);
    }

    public BERConstructedOctetString(Vector vector) {
        super(toBytes(vector));
        this.octs = vector;
    }

    public BERConstructedOctetString(byte[] bArr) {
        super(bArr);
    }

    private Vector generateOcts() {
        Vector vector = new Vector();
        int i2 = 0;
        while (true) {
            byte[] bArr = this.string;
            if (i2 >= bArr.length) {
                return vector;
            }
            int i3 = i2 + 1000;
            int length = (i3 > bArr.length ? bArr.length : i3) - i2;
            byte[] bArr2 = new byte[length];
            System.arraycopy(bArr, i2, bArr2, 0, length);
            vector.addElement(new DEROctetString(bArr2));
            i2 = i3;
        }
    }

    private static byte[] toBytes(Vector vector) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i2 = 0; i2 != vector.size(); i2++) {
            try {
                byteArrayOutputStream.write(((DEROctetString) vector.elementAt(i2)).getOctets());
            } catch (IOException e2) {
                throw new IllegalArgumentException("exception converting octets " + e2.toString());
            } catch (ClassCastException unused) {
                throw new IllegalArgumentException(vector.elementAt(i2).getClass().getName() + " found in input should only contain DEROctetString");
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    @Override // cn.com.union.fido.util.asn1.DEROctetString, cn.com.union.fido.util.asn1.ASN1OctetString, cn.com.union.fido.util.asn1.ASN1Object, cn.com.union.fido.util.asn1.DERObject
    public void encode(DEROutputStream dEROutputStream) {
        if (!(dEROutputStream instanceof ASN1OutputStream) && !(dEROutputStream instanceof BEROutputStream)) {
            super.encode(dEROutputStream);
            return;
        }
        dEROutputStream.write(36);
        dEROutputStream.write(128);
        Enumeration objects = getObjects();
        while (objects.hasMoreElements()) {
            dEROutputStream.writeObject(objects.nextElement());
        }
        dEROutputStream.write(0);
        dEROutputStream.write(0);
    }

    public Enumeration getObjects() {
        Vector vector = this.octs;
        return vector == null ? generateOcts().elements() : vector.elements();
    }

    @Override // cn.com.union.fido.util.asn1.ASN1OctetString
    public byte[] getOctets() {
        return this.string;
    }
}
