package cn.com.union.fido.bean.authenticator.tag;

import cn.com.union.fido.util.CommonTools;
import cn.com.union.fido.util.StringTools;
import cn.com.union.fido.util.Utility;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class TAG_AUTHENTICATOR_INFO {
    public String aaid;
    public String assertionScheme;
    public List<Short> attestationType;
    public byte authenticatorIndex;
    public TAG_AUTHENTICATOR_METADATA authenticatorMetadata;
    public List<String> supportedExtensionID;
    public String tcDisplayContentType;
    public List<TAG_TC_DISPLAY_PNG_CHARACTERISTICS> tcDisplayPNGCharacteristics;

    public void deserialize(byte[] bArr) {
        int i2;
        int byteToShort;
        if (10253 == Utility.byteToShort(bArr, 0, 2)) {
            this.authenticatorIndex = bArr[4];
            i2 = 5;
        } else {
            i2 = 0;
        }
        int i3 = i2 + 2;
        if (11787 == Utility.byteToShort(bArr, i2, i3)) {
            int i4 = i3 + 2;
            int byteToShort2 = Utility.byteToShort(bArr, i3, i4) + i4;
            this.aaid = Utility.byteToStr(bArr, i4, byteToShort2);
            i2 = byteToShort2;
        }
        int i5 = i2 + 2;
        if (10249 == Utility.byteToShort(bArr, i2, i5) && (byteToShort = Utility.byteToShort(bArr, i5, (i2 = i5 + 2))) > 0) {
            byte[] bArr2 = new byte[byteToShort];
            System.arraycopy(bArr, i2, bArr2, 0, byteToShort);
            TAG_AUTHENTICATOR_METADATA tag_authenticator_metadata = new TAG_AUTHENTICATOR_METADATA();
            this.authenticatorMetadata = tag_authenticator_metadata;
            tag_authenticator_metadata.deserialize(bArr2);
            i2 += byteToShort;
        }
        int i6 = i2 + 2;
        if (10252 == Utility.byteToShort(bArr, i2, i6)) {
            int i7 = i6 + 2;
            int byteToShort3 = Utility.byteToShort(bArr, i6, i7) + i7;
            this.tcDisplayContentType = Utility.byteToStr(bArr, i7, byteToShort3);
            i2 = byteToShort3;
        }
        this.tcDisplayPNGCharacteristics = new ArrayList();
        while (10251 == Utility.byteToShort(bArr, i2, i2 + 2)) {
            int i8 = i2 + 2;
            int i9 = i8 + 2;
            int byteToShort4 = Utility.byteToShort(bArr, i8, i9);
            if (byteToShort4 > 0) {
                byte[] bArr3 = new byte[byteToShort4];
                System.arraycopy(bArr, i9, bArr3, 0, byteToShort4);
                TAG_TC_DISPLAY_PNG_CHARACTERISTICS tag_tc_display_png_characteristics = new TAG_TC_DISPLAY_PNG_CHARACTERISTICS();
                tag_tc_display_png_characteristics.deserialize(bArr3);
                this.tcDisplayPNGCharacteristics.add(tag_tc_display_png_characteristics);
                i9 += byteToShort4;
            }
            i2 = i9;
        }
        int i10 = i2 + 2;
        if (10250 == Utility.byteToShort(bArr, i2, i10)) {
            int i11 = i10 + 2;
            int byteToShort5 = Utility.byteToShort(bArr, i10, i11) + i11;
            this.assertionScheme = Utility.byteToStr(bArr, i11, byteToShort5);
            i2 = byteToShort5;
        }
        this.attestationType = new ArrayList();
        while (10247 == Utility.byteToShort(bArr, i2, i2 + 2)) {
            int i12 = i2 + 2;
            int i13 = i12 + 2;
            i2 = Utility.byteToShort(bArr, i12, i13) + i13;
            this.attestationType.add(Short.valueOf((short) Utility.byteToShort(bArr, i13, i2)));
        }
        this.supportedExtensionID = new ArrayList();
        while (10258 == Utility.byteToShort(bArr, i2, i2 + 2)) {
            int i14 = i2 + 2;
            int i15 = i14 + 2;
            i2 = Utility.byteToShort(bArr, i14, i15) + i15;
            this.supportedExtensionID.add(Utility.byteToStr(bArr, i15, i2));
        }
    }

    public byte[] serialize() {
        int i2;
        byte[] bArr = new byte[1024];
        Utility.shortToByte(bArr, 0, 2, R2.drawable.manto_actionbar_close_black);
        Utility.shortToByte(bArr, 2, 4, 1);
        bArr[4] = this.authenticatorIndex;
        int i3 = 5;
        if (StringTools.isValidateString(this.aaid)) {
            Utility.shortToByte(bArr, 5, 7, R2.drawable.vf_nonfull_c_00003);
            int length = this.aaid.getBytes().length;
            Utility.shortToByte(bArr, 7, 9, length);
            int i4 = length + 9;
            Utility.strToByte(bArr, 9, i4, this.aaid);
            i3 = i4;
        }
        int i5 = i3 + 2;
        Utility.shortToByte(bArr, i3, i5, R2.drawable.main_navigation_bg_5);
        TAG_AUTHENTICATOR_METADATA tag_authenticator_metadata = this.authenticatorMetadata;
        if (tag_authenticator_metadata != null) {
            byte[] serialize = tag_authenticator_metadata.serialize();
            int length2 = serialize.length;
            int i6 = i5 + 2;
            Utility.shortToByte(bArr, i5, i6, length2);
            System.arraycopy(serialize, 0, bArr, i6, length2);
            i2 = i6 + length2;
        } else {
            i2 = i5 + 2;
            Utility.shortToByte(bArr, i5, i2, 0);
        }
        if (StringTools.isValidateString(this.tcDisplayContentType)) {
            int i7 = i2 + 2;
            Utility.shortToByte(bArr, i2, i7, R2.drawable.manto_actionbar_back_selector);
            int length3 = this.tcDisplayContentType.getBytes().length;
            int i8 = i7 + 2;
            Utility.shortToByte(bArr, i7, i8, length3);
            i2 = i8 + length3;
            Utility.strToByte(bArr, i8, i2, this.tcDisplayContentType);
        }
        if (CommonTools.isValidateList(this.tcDisplayPNGCharacteristics)) {
            for (int i9 = 0; i9 < this.tcDisplayPNGCharacteristics.size(); i9++) {
                TAG_TC_DISPLAY_PNG_CHARACTERISTICS tag_tc_display_png_characteristics = this.tcDisplayPNGCharacteristics.get(i9);
                if (tag_tc_display_png_characteristics != null) {
                    byte[] serialize2 = tag_tc_display_png_characteristics.serialize();
                    int i10 = i2 + 2;
                    Utility.shortToByte(bArr, i2, i10, R2.drawable.manto_actionbar_back);
                    int length4 = serialize2.length;
                    int i11 = i10 + 2;
                    Utility.shortToByte(bArr, i10, i11, length4);
                    System.arraycopy(serialize2, 0, bArr, i11, length4);
                    i2 = i11 + length4;
                }
            }
        } else {
            int i12 = i2 + 2;
            Utility.shortToByte(bArr, i2, i12, R2.drawable.manto_actionbar_back);
            i2 = i12 + 2;
            Utility.shortToByte(bArr, i12, i2, 0);
        }
        if (StringTools.isValidateString(this.assertionScheme)) {
            int i13 = i2 + 2;
            Utility.shortToByte(bArr, i2, i13, R2.drawable.main_navigation_bg_dark);
            int length5 = this.assertionScheme.getBytes().length;
            int i14 = i13 + 2;
            Utility.shortToByte(bArr, i13, i14, length5);
            i2 = i14 + length5;
            Utility.strToByte(bArr, i14, i2, this.assertionScheme);
        }
        if (CommonTools.isValidateList(this.attestationType)) {
            for (int i15 = 0; i15 < this.attestationType.size(); i15++) {
                int i16 = i2 + 2;
                Utility.shortToByte(bArr, i2, i16, R2.drawable.main_ic_menu_alert);
                int i17 = i16 + 2;
                Utility.shortToByte(bArr, i16, i17, 2);
                i2 = i17 + 2;
                Utility.shortToByte(bArr, i17, i2, this.attestationType.get(i15).shortValue());
            }
        }
        if (CommonTools.isValidateList(this.supportedExtensionID)) {
            int i18 = 0;
            while (i18 < this.supportedExtensionID.size()) {
                int i19 = i2 + 2;
                Utility.shortToByte(bArr, i2, i19, R2.drawable.manto_actionbar_follow_black_p);
                String str = this.supportedExtensionID.get(i18);
                int length6 = str.getBytes().length;
                int i20 = i19 + 2;
                Utility.shortToByte(bArr, i19, i20, length6);
                int i21 = i20 + length6;
                Utility.strToByte(bArr, i20, i21, str);
                i18++;
                i2 = i21;
            }
        }
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, 0, bArr2, 0, i2);
        return bArr2;
    }
}
