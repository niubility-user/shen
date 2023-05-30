package cn.com.union.fido.bean.authenticator.tag;

import cn.com.union.fido.util.CommonTools;
import cn.com.union.fido.util.StringTools;
import cn.com.union.fido.util.Utility;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class TAG_ATTESTATION_BASIC_FULL {
    public List<String> certificate;
    public String keyStoreType;
    public byte[] signature;

    public void deserialize(byte[] bArr) {
        int i2 = 0;
        if (11782 == Utility.byteToShort(bArr, 0, 2)) {
            int byteToShort = Utility.byteToShort(bArr, 2, 4);
            byte[] bArr2 = new byte[byteToShort];
            this.signature = bArr2;
            System.arraycopy(bArr, 4, bArr2, 0, byteToShort);
            i2 = byteToShort + 4;
        }
        this.certificate = new ArrayList();
        while (11781 == Utility.byteToShort(bArr, i2, i2 + 2)) {
            int i3 = i2 + 2;
            int i4 = i3 + 2;
            i2 = Utility.byteToShort(bArr, i3, i4) + i4;
            this.certificate.add(Utility.byteToStr(bArr, i4, i2));
        }
        int i5 = i2 + 2;
        if (11783 == Utility.byteToShort(bArr, i2, i5)) {
            int i6 = i5 + 2;
            this.keyStoreType = Utility.byteToStr(bArr, i6, Utility.byteToShort(bArr, i5, i6) + i6);
        }
    }

    public byte[] serialize() {
        int i2;
        byte[] bArr = new byte[10240];
        Utility.shortToByte(bArr, 0, 2, R2.id.recommend_year_festival_shot_pd_img);
        if (CommonTools.isValidateByteArray(this.signature)) {
            Utility.shortToByte(bArr, 4, 6, R2.drawable.vf_nonfull_b_00033);
            int length = this.signature.length;
            Utility.shortToByte(bArr, 6, 8, length);
            System.arraycopy(this.signature, 0, bArr, 8, length);
            i2 = length + 8;
        } else {
            i2 = 4;
        }
        if (CommonTools.isValidateList(this.certificate)) {
            for (int i3 = 0; i3 < this.certificate.size(); i3++) {
                String str = this.certificate.get(i3);
                int i4 = i2 + 2;
                Utility.shortToByte(bArr, i2, i4, R2.drawable.vf_nonfull_b_00032);
                int length2 = str.getBytes().length;
                int i5 = i4 + 2;
                Utility.shortToByte(bArr, i4, i5, length2);
                i2 = length2 + i5;
                Utility.strToByte(bArr, i5, i2, str);
            }
        }
        if (StringTools.isValidateString(this.keyStoreType)) {
            int i6 = i2 + 2;
            Utility.shortToByte(bArr, i2, i6, R2.drawable.vf_nonfull_b_anim);
            int length3 = this.keyStoreType.getBytes().length;
            int i7 = i6 + 2;
            Utility.shortToByte(bArr, i6, i7, length3);
            i2 = length3 + i7;
            Utility.strToByte(bArr, i7, i2, this.keyStoreType);
        }
        Utility.shortToByte(bArr, 2, 4, i2 - 4);
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, 0, bArr2, 0, i2);
        return bArr2;
    }
}
