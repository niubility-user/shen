package g.f.c.c.d;

import android.nfc.TagLostException;
import android.nfc.tech.NfcV;
import g.f.c.c.c;
import jd.wjlogin_sdk.util.ReplyCode;

/* loaded from: classes13.dex */
public class a extends c {

    /* renamed from: c  reason: collision with root package name */
    private byte f19566c;

    public a(NfcV nfcV) {
        super(nfcV);
        this.f19566c = c.a(nfcV.getTag().getId());
        String str = "------@ NFCVFMUtil icMfgCode:" + ((int) this.f19566c);
        this.b.f19564i = 4;
    }

    public byte d(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr.length < 4 || bArr2 == null || bArr2.length < 8) {
            return (byte) -16;
        }
        byte[] bArr3 = {new g.f.c.c.a().a(), ReplyCode.reply0xb2, this.f19566c};
        System.arraycopy(bArr, 0, bArr3, 3, 4);
        try {
            byte[] transceive = this.a.transceive(bArr3);
            if (transceive[0] == 0) {
                System.arraycopy(transceive, 1, bArr2, 0, 8);
                return (byte) 0;
            }
            return transceive[1];
        } catch (Exception e2) {
            e2.printStackTrace();
            return (byte) -15;
        }
    }

    public byte e(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr.length < 8 || bArr2 == null || bArr2.length < 8) {
            return (byte) -16;
        }
        byte[] bArr3 = new byte[11];
        bArr3[0] = new g.f.c.c.a().a();
        bArr3[1] = ReplyCode.reply0xb1;
        bArr3[2] = this.f19566c;
        System.arraycopy(bArr, 0, bArr3, 3, 8);
        try {
            byte[] transceive = this.a.transceive(bArr3);
            if (transceive[0] == 0) {
                System.arraycopy(transceive, 1, bArr2, 0, 8);
                return (byte) 0;
            }
            return transceive[1];
        } catch (TagLostException e2) {
            e2.printStackTrace();
            return (byte) -14;
        } catch (Exception e3) {
            e3.printStackTrace();
            return (byte) -15;
        }
    }

    public byte f(byte b, byte b2, byte[] bArr, byte[] bArr2) {
        int i2;
        if (bArr == null || bArr.length < 4 || b2 < 1 || b > 64 || b + b2 > 64 || bArr2 == null || bArr2.length < (i2 = b2 * 4)) {
            return (byte) -16;
        }
        byte[] bArr3 = {new g.f.c.c.a().a(), -42, this.f19566c, b, (byte) (b2 - 1)};
        System.arraycopy(bArr, 0, bArr3, 5, 4);
        try {
            byte[] transceive = this.a.transceive(bArr3);
            if (transceive[0] == 0) {
                System.arraycopy(transceive, 1, bArr2, 0, i2);
                return (byte) 0;
            }
            return transceive[1];
        } catch (TagLostException e2) {
            e2.printStackTrace();
            return (byte) -14;
        } catch (Exception e3) {
            e3.printStackTrace();
            return (byte) -15;
        }
    }

    public byte g(byte[] bArr) {
        if (bArr == null || bArr.length < 4) {
            return (byte) -16;
        }
        try {
            byte[] transceive = this.a.transceive(new byte[]{new g.f.c.c.a().a(), -92, this.f19566c});
            if (transceive[0] == 0) {
                System.arraycopy(transceive, 1, bArr, 0, 4);
                return (byte) 0;
            }
            return transceive[1];
        } catch (TagLostException e2) {
            e2.printStackTrace();
            return (byte) -14;
        } catch (Exception e3) {
            e3.printStackTrace();
            return (byte) -15;
        }
    }

    public byte h(byte[] bArr) {
        if (bArr == null || bArr.length < 4) {
            return (byte) -16;
        }
        try {
            byte[] transceive = this.a.transceive(new byte[]{new g.f.c.c.a().a(), -80, this.f19566c});
            if (transceive[0] == 0) {
                System.arraycopy(transceive, 1, bArr, 0, 4);
                return (byte) 0;
            }
            return transceive[1];
        } catch (TagLostException e2) {
            e2.printStackTrace();
            return (byte) -14;
        } catch (Exception e3) {
            e3.printStackTrace();
            return (byte) -15;
        }
    }
}
