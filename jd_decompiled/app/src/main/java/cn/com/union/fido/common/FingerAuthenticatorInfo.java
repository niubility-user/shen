package cn.com.union.fido.common;

import android.text.TextUtils;
import cn.com.union.fido.bean.authenticator.tag.TAG_TC_DISPLAY_PNG_CHARACTERISTICS;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class FingerAuthenticatorInfo {
    public static final String aaid = "004B#0001";
    public static final String assertionScheme = "UAFV1TLV";
    public static final List<Short> attestationType;
    public static String authProviderName = null;
    public static short authenticationAlg = 0;
    public static final int authenticatorIndex = 0;
    public static final short authenticatorType = 0;
    public static String authkeystoreName = null;
    public static String authkeystoreType = null;
    public static final short keyProtection = 1;
    public static final short matcherProtection = 1;
    public static final byte maxKeyHandles = 8;
    public static final String privateKey = "V-ZoTI0SC8XmUeQLuAijjco52P4VVFnn0gIT_FG6uxursprQa5PJGYZm2H138D0z";
    public static final short publicKeyFormats = 257;
    public static final List<String> supportedExtensionID = null;
    public static final List<TAG_TC_DISPLAY_PNG_CHARACTERISTICS> tag_tc_display_png_characteristics;
    public static final String tcDisplayContentType = "text/plain";
    public static final short transactionConfirmationDisplay = 1;
    public static final int userVerification = 2;

    static {
        ArrayList arrayList = new ArrayList();
        attestationType = arrayList;
        arrayList.add(Short.valueOf((short) UAFPredefinedValues.TAG_ATTESTATION_BASIC_FULL));
        ArrayList arrayList2 = new ArrayList();
        tag_tc_display_png_characteristics = arrayList2;
        TAG_TC_DISPLAY_PNG_CHARACTERISTICS tag_tc_display_png_characteristics2 = new TAG_TC_DISPLAY_PNG_CHARACTERISTICS();
        tag_tc_display_png_characteristics2.bitDepth = (byte) 16;
        tag_tc_display_png_characteristics2.colorType = (byte) 2;
        tag_tc_display_png_characteristics2.compression = (byte) 0;
        tag_tc_display_png_characteristics2.filter = (byte) 0;
        tag_tc_display_png_characteristics2.height = 240;
        tag_tc_display_png_characteristics2.interlace = (byte) 0;
        tag_tc_display_png_characteristics2.width = 320;
        arrayList2.add(tag_tc_display_png_characteristics2);
        manufacturerInit();
    }

    public static boolean manufacturerCheck() {
        try {
            if (TextUtils.isEmpty(authkeystoreName)) {
                if (TextUtils.isEmpty(BaseInfo.getDeviceManufacture())) {
                    return false;
                }
                manufacturerInit();
                if (TextUtils.isEmpty(authkeystoreName)) {
                    return false;
                }
            }
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:63:0x00c2
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:81)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:47)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    private static void manufacturerInit() {
        /*
            Method dump skipped, instructions count: 292
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.union.fido.common.FingerAuthenticatorInfo.manufacturerInit():void");
    }
}
