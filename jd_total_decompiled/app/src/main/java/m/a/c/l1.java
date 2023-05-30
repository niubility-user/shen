package m.a.c;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import sun.misc.HexDumpEncoder;

/* loaded from: classes11.dex */
public class l1 implements PublicKey {
    private static final long serialVersionUID = -5359250853002055002L;
    protected e algid;
    protected byte[] encodedKey;
    @Deprecated
    protected byte[] key = null;
    private int unusedBits = 0;
    private m.a.b.a bitStringKey = null;

    public l1() {
    }

    static PublicKey a(e eVar, m.a.b.a aVar) throws IOException, InvalidKeyException {
        Provider provider;
        Class<?> loadClass;
        m.a.b.h hVar = new m.a.b.h();
        b(hVar, eVar, aVar);
        try {
            return KeyFactory.getInstance(eVar.getName()).generatePublic(new X509EncodedKeySpec(hVar.toByteArray()));
        } catch (NoSuchAlgorithmException unused) {
            try {
                provider = Security.getProvider("SUN");
            } catch (ClassNotFoundException | InstantiationException unused2) {
            } catch (IllegalAccessException unused3) {
                throw new IOException(" [internal error]");
            }
            if (provider != null) {
                String property = provider.getProperty("PublicKey.X.509." + eVar.getName());
                if (property != null) {
                    try {
                        loadClass = Class.forName(property);
                    } catch (ClassNotFoundException unused4) {
                        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
                        loadClass = systemClassLoader != null ? systemClassLoader.loadClass(property) : null;
                    }
                    Object newInstance = loadClass != null ? loadClass.newInstance() : null;
                    if (newInstance instanceof l1) {
                        l1 l1Var = (l1) newInstance;
                        l1Var.algid = eVar;
                        l1Var.e(aVar);
                        l1Var.d();
                        return l1Var;
                    }
                    return new l1(eVar, aVar);
                }
                throw new InstantiationException();
            }
            throw new InstantiationException();
        } catch (InvalidKeySpecException e2) {
            throw new InvalidKeyException(e2.getMessage(), e2);
        }
    }

    static void b(m.a.b.h hVar, e eVar, m.a.b.a aVar) throws IOException {
        m.a.b.h hVar2 = new m.a.b.h();
        eVar.encode(hVar2);
        hVar2.x(aVar);
        hVar.y((byte) 48, hVar2);
    }

    public static PublicKey parse(m.a.b.i iVar) throws IOException {
        if (iVar.a == 48) {
            try {
                PublicKey a = a(e.parse(iVar.f20295c.e()), iVar.f20295c.q());
                if (iVar.f20295c.a() == 0) {
                    return a;
                }
                throw new IOException("excess subject key");
            } catch (InvalidKeyException e2) {
                throw new IOException("subject key, " + e2.getMessage());
            }
        }
        throw new IOException("corrupt subject key");
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException {
        try {
            decode(objectInputStream);
        } catch (InvalidKeyException e2) {
            e2.printStackTrace();
            throw new IOException("deserialized key is invalid: " + e2.getMessage());
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.write(getEncoded());
    }

    protected m.a.b.a c() {
        byte[] bArr = this.key;
        m.a.b.a aVar = new m.a.b.a((bArr.length * 8) - this.unusedBits, bArr);
        this.bitStringKey = aVar;
        return (m.a.b.a) aVar.clone();
    }

    protected void d() throws IOException, InvalidKeyException {
        encode();
    }

    public void decode(InputStream inputStream) throws InvalidKeyException {
        try {
            m.a.b.i iVar = new m.a.b.i(inputStream);
            if (iVar.a == 48) {
                this.algid = e.parse(iVar.f20295c.e());
                e(iVar.f20295c.q());
                d();
                if (iVar.f20295c.a() != 0) {
                    throw new InvalidKeyException("excess key data");
                }
                return;
            }
            throw new InvalidKeyException("invalid key format");
        } catch (IOException e2) {
            throw new InvalidKeyException("IOException: " + e2.getMessage());
        }
    }

    protected void e(m.a.b.a aVar) {
        this.bitStringKey = (m.a.b.a) aVar.clone();
        this.key = aVar.g();
        int b = aVar.b() % 8;
        this.unusedBits = b == 0 ? 0 : 8 - b;
    }

    public final void encode(m.a.b.h hVar) throws IOException {
        b(hVar, this.algid, c());
    }

    public boolean equals(Object obj) {
        byte[] encoded;
        if (this == obj) {
            return true;
        }
        if (obj instanceof Key) {
            try {
                byte[] encodedInternal = getEncodedInternal();
                if (obj instanceof l1) {
                    encoded = ((l1) obj).getEncodedInternal();
                } else {
                    encoded = ((Key) obj).getEncoded();
                }
                return Arrays.equals(encodedInternal, encoded);
            } catch (InvalidKeyException unused) {
                return false;
            }
        }
        return false;
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return this.algid.getName();
    }

    public e getAlgorithmId() {
        return this.algid;
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        try {
            return (byte[]) getEncodedInternal().clone();
        } catch (InvalidKeyException unused) {
            return null;
        }
    }

    public byte[] getEncodedInternal() throws InvalidKeyException {
        byte[] bArr = this.encodedKey;
        if (bArr == null) {
            try {
                m.a.b.h hVar = new m.a.b.h();
                encode(hVar);
                bArr = hVar.toByteArray();
                this.encodedKey = bArr;
            } catch (IOException e2) {
                throw new InvalidKeyException("IOException : " + e2.getMessage());
            }
        }
        return bArr;
    }

    @Override // java.security.Key
    public String getFormat() {
        return "X.509";
    }

    public int hashCode() {
        try {
            byte[] encodedInternal = getEncodedInternal();
            int length = encodedInternal.length;
            for (byte b : encodedInternal) {
                length += (b & 255) * 37;
            }
            return length;
        } catch (InvalidKeyException unused) {
            return 0;
        }
    }

    public String toString() {
        return "algorithm = " + this.algid.toString() + ", unparsed keybits = \n" + new HexDumpEncoder().encodeBuffer(this.key);
    }

    public byte[] encode() throws InvalidKeyException {
        return (byte[]) getEncodedInternal().clone();
    }

    private l1(e eVar, m.a.b.a aVar) throws InvalidKeyException {
        this.algid = eVar;
        e(aVar);
        encode();
    }

    public void decode(byte[] bArr) throws InvalidKeyException {
        decode(new ByteArrayInputStream(bArr));
    }
}
