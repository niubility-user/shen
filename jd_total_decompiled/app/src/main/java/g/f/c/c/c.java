package g.f.c.c;

import android.nfc.tech.NfcV;
import java.util.Arrays;
import jd.wjlogin_sdk.util.ReplyCode;

/* loaded from: classes13.dex */
public class c {
    protected NfcV a;
    protected b b = new b();

    public c(NfcV nfcV) {
        this.a = nfcV;
        c(nfcV.getTag().getId());
        b(this.b);
    }

    public static byte a(byte[] bArr) {
        if (bArr == null || bArr.length < 2) {
            return (byte) 0;
        }
        String str = "------@ getICMfgCodeFromId:" + Arrays.toString(bArr);
        return bArr[bArr.length - 2];
    }

    public byte b(b bVar) {
        if (bVar == null) {
            return (byte) -16;
        }
        try {
            byte[] transceive = this.a.transceive(new byte[]{new a().a(), 43});
            if (transceive[0] == 0) {
                bVar.a = transceive[1] & 1;
                bVar.b = (transceive[1] & 2) >> 1;
                bVar.f19559c = (transceive[1] & 4) >> 2;
                bVar.d = (transceive[1] & 8) >> 3;
                System.arraycopy(transceive, 2, bVar.f19560e, 0, 8);
                int i2 = 10;
                if (1 == bVar.a) {
                    bVar.f19561f = transceive[10];
                    i2 = 11;
                }
                if (1 == bVar.b) {
                    bVar.f19562g = transceive[i2];
                    i2++;
                }
                if (1 == bVar.f19559c) {
                    bVar.f19563h = transceive[i2] + 1;
                    bVar.f19564i = (transceive[i2 + 1] & 31) + 1;
                    i2 += 2;
                }
                if (1 == bVar.d) {
                    bVar.f19565j = transceive[i2];
                }
                return (byte) 0;
            }
            return transceive[1];
        } catch (Exception e2) {
            e2.printStackTrace();
            return (byte) -15;
        }
    }

    public byte c(byte[] bArr) {
        byte[] bArr2 = new byte[10];
        a aVar = new a();
        aVar.f19554e = (byte) 0;
        aVar.f19555f = (byte) 1;
        bArr2[0] = aVar.a();
        bArr2[1] = ReplyCode.reply0x25;
        System.arraycopy(bArr, 0, bArr2, 2, 8);
        try {
            byte[] transceive = this.a.transceive(bArr2);
            if (transceive[0] == 0) {
                return (byte) 0;
            }
            return transceive[1];
        } catch (Exception e2) {
            e2.printStackTrace();
            return (byte) -15;
        }
    }
}
