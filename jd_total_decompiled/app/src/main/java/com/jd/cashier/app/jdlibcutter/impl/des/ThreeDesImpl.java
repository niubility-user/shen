package com.jd.cashier.app.jdlibcutter.impl.des;

import com.jd.cashier.app.jdlibcutter.protocol.des.IDes;
import com.jingdong.common.entity.DesCommonUtils;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes13.dex */
public class ThreeDesImpl implements IDes {
    @Override // com.jd.cashier.app.jdlibcutter.protocol.des.IDes
    public String decrypt(String str, String str2) {
        try {
            return DesCommonUtils.decryptThreeDESECB(str, str2);
        } catch (Exception e2) {
            if (Log.E) {
                e2.printStackTrace();
                return "";
            }
            return "";
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.des.IDes
    public String encrypt(String str, String str2) {
        try {
            return DesCommonUtils.encryptThreeDESECB(str, str2);
        } catch (Exception e2) {
            if (Log.E) {
                e2.printStackTrace();
                return "";
            }
            return "";
        }
    }
}
