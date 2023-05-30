package com.google.zxing.aztec.decoder;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.google.zxing.FormatException;
import com.google.zxing.aztec.AztecDetectorResult;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonDecoder;
import com.google.zxing.common.reedsolomon.ReedSolomonException;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jd.dynamic.DYConstants;
import com.jd.libs.jdmbridge.base.proxy.share.IShareAdapter;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.app.mall.e;
import com.jingdong.common.entity.personal.PersonalConstants;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.jdsdk.a.a;
import com.jingdong.jdsdk.auraSetting.AuraConstants;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import com.meizu.cloud.pushsdk.notification.model.NotifyType;
import com.tencent.smtt.sdk.ProxyConfig;
import java.util.Arrays;
import org.eclipse.paho.client.mqttv3.MqttTopic;

/* loaded from: classes12.dex */
public final class Decoder {
    private AztecDetectorResult ddata;
    private static final String[] UPPER_TABLE = {"CTRL_PS", LangUtils.SINGLE_SPACE, "A", "B", "C", AuraConstants.MESSAGE_COUPON_TYPE_WILL_EXPIRE, "E", "F", "G", DYConstants.LETTER_H, "I", "J", "K", "L", "M", AuraConstants.MESSAGE_COUPON_TYPE_NEW, IShareAdapter.SHARE_ACTION_OPEN, IShareAdapter.SHARE_ACTION_PANE, "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "CTRL_LL", "CTRL_ML", "CTRL_DL", "CTRL_BS"};
    private static final String[] LOWER_TABLE = {"CTRL_PS", LangUtils.SINGLE_SPACE, a.a, "b", "c", "d", e.a, "f", "g", JshopConst.JSHOP_PROMOTIO_H, "i", "j", "k", NotifyType.LIGHTS, "m", PersonalConstants.ICON_STYLE_N, "o", "p", "q", "r", "s", "t", "u", "v", JshopConst.JSHOP_PROMOTIO_W, JshopConst.JSHOP_PROMOTIO_X, JshopConst.JSHOP_PROMOTIO_Y, "z", "CTRL_US", "CTRL_ML", "CTRL_DL", "CTRL_BS"};
    private static final String[] MIXED_TABLE = {"CTRL_PS", LangUtils.SINGLE_SPACE, "\u0001", "\u0002", "\u0003", "\u0004", "\u0005", "\u0006", "\u0007", "\b", "\t", ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE, "\u000b", "\f", "\r", "\u001b", "\u001c", "\u001d", "\u001e", "\u001f", DYConstants.DY_REGEX_AT, "\\", "^", CartConstant.KEY_YB_INFO_LINK, "`", "|", "~", "\u007f", "CTRL_LL", "CTRL_UL", "CTRL_PL", "CTRL_BS"};
    private static final String[] PUNCT_TABLE = {"", "\r", "\r\n", ". ", ", ", ": ", "!", "\"", "#", "$", "%", ContainerUtils.FIELD_DELIMITER, "'", "(", ")", ProxyConfig.MATCH_ALL_SCHEMES, MqttTopic.SINGLE_LEVEL_WILDCARD, DYConstants.DY_REGEX_COMMA, "-", OrderISVUtil.MONEY_DECIMAL, "/", ":", ";", "<", ContainerUtils.KEY_VALUE_DELIMITER, ">", "?", "[", "]", "{", "}", "CTRL_UL"};
    private static final String[] DIGIT_TABLE = {"CTRL_PS", LangUtils.SINGLE_SPACE, "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", DYConstants.DY_REGEX_COMMA, OrderISVUtil.MONEY_DECIMAL, "CTRL_UL", "CTRL_US"};

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.zxing.aztec.decoder.Decoder$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table;

        static {
            int[] iArr = new int[Table.values().length];
            $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table = iArr;
            try {
                iArr[Table.UPPER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table[Table.LOWER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table[Table.MIXED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table[Table.PUNCT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table[Table.DIGIT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public enum Table {
        UPPER,
        LOWER,
        MIXED,
        DIGIT,
        PUNCT,
        BINARY
    }

    private boolean[] correctBits(boolean[] zArr) throws FormatException {
        GenericGF genericGF;
        int i2 = 8;
        if (this.ddata.getNbLayers() <= 2) {
            i2 = 6;
            genericGF = GenericGF.AZTEC_DATA_6;
        } else if (this.ddata.getNbLayers() <= 8) {
            genericGF = GenericGF.AZTEC_DATA_8;
        } else if (this.ddata.getNbLayers() <= 22) {
            i2 = 10;
            genericGF = GenericGF.AZTEC_DATA_10;
        } else {
            i2 = 12;
            genericGF = GenericGF.AZTEC_DATA_12;
        }
        int nbDatablocks = this.ddata.getNbDatablocks();
        int length = zArr.length / i2;
        if (length >= nbDatablocks) {
            int length2 = zArr.length % i2;
            int i3 = length - nbDatablocks;
            int[] iArr = new int[length];
            int i4 = 0;
            while (i4 < length) {
                iArr[i4] = readCode(zArr, length2, i2);
                i4++;
                length2 += i2;
            }
            try {
                new ReedSolomonDecoder(genericGF).decode(iArr, i3);
                int i5 = (1 << i2) - 1;
                int i6 = 0;
                for (int i7 = 0; i7 < nbDatablocks; i7++) {
                    int i8 = iArr[i7];
                    if (i8 == 0 || i8 == i5) {
                        throw FormatException.getFormatInstance();
                    }
                    if (i8 == 1 || i8 == i5 - 1) {
                        i6++;
                    }
                }
                boolean[] zArr2 = new boolean[(nbDatablocks * i2) - i6];
                int i9 = 0;
                for (int i10 = 0; i10 < nbDatablocks; i10++) {
                    int i11 = iArr[i10];
                    if (i11 == 1 || i11 == i5 - 1) {
                        Arrays.fill(zArr2, i9, (i9 + i2) - 1, i11 > 1);
                        i9 += i2 - 1;
                    } else {
                        int i12 = i2 - 1;
                        while (i12 >= 0) {
                            int i13 = i9 + 1;
                            zArr2[i9] = ((1 << i12) & i11) != 0;
                            i12--;
                            i9 = i13;
                        }
                    }
                }
                return zArr2;
            } catch (ReedSolomonException unused) {
                throw FormatException.getFormatInstance();
            }
        }
        throw FormatException.getFormatInstance();
    }

    private static String getCharacter(Table table, int i2) {
        int i3 = AnonymousClass1.$SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table[table.ordinal()];
        if (i3 != 1) {
            if (i3 != 2) {
                if (i3 != 3) {
                    if (i3 != 4) {
                        if (i3 == 5) {
                            return DIGIT_TABLE[i2];
                        }
                        throw new IllegalStateException("Bad table");
                    }
                    return PUNCT_TABLE[i2];
                }
                return MIXED_TABLE[i2];
            }
            return LOWER_TABLE[i2];
        }
        return UPPER_TABLE[i2];
    }

    private static String getEncodedData(boolean[] zArr) {
        int length = zArr.length;
        Table table = Table.UPPER;
        StringBuilder sb = new StringBuilder(20);
        Table table2 = table;
        int i2 = 0;
        while (i2 < length) {
            if (table != Table.BINARY) {
                int i3 = table == Table.DIGIT ? 4 : 5;
                if (length - i2 < i3) {
                    break;
                }
                int readCode = readCode(zArr, i2, i3);
                i2 += i3;
                String character = getCharacter(table, readCode);
                if (character.startsWith("CTRL_")) {
                    Table table3 = getTable(character.charAt(5));
                    if (character.charAt(6) == 'L') {
                        table = table3;
                        table2 = table;
                    } else {
                        table = table3;
                    }
                } else {
                    sb.append(character);
                    table = table2;
                }
            } else if (length - i2 < 5) {
                break;
            } else {
                int readCode2 = readCode(zArr, i2, 5);
                i2 += 5;
                if (readCode2 == 0) {
                    if (length - i2 < 11) {
                        break;
                    }
                    readCode2 = readCode(zArr, i2, 11) + 31;
                    i2 += 11;
                }
                int i4 = 0;
                while (true) {
                    if (i4 >= readCode2) {
                        break;
                    } else if (length - i2 < 8) {
                        i2 = length;
                        break;
                    } else {
                        sb.append((char) readCode(zArr, i2, 8));
                        i2 += 8;
                        i4++;
                    }
                }
                table = table2;
            }
        }
        return sb.toString();
    }

    private static Table getTable(char c2) {
        if (c2 != 'B') {
            if (c2 != 'D') {
                if (c2 != 'P') {
                    if (c2 != 'L') {
                        if (c2 != 'M') {
                            return Table.UPPER;
                        }
                        return Table.MIXED;
                    }
                    return Table.LOWER;
                }
                return Table.PUNCT;
            }
            return Table.DIGIT;
        }
        return Table.BINARY;
    }

    public static String highLevelDecode(boolean[] zArr) {
        return getEncodedData(zArr);
    }

    private static int readCode(boolean[] zArr, int i2, int i3) {
        int i4 = 0;
        for (int i5 = i2; i5 < i2 + i3; i5++) {
            i4 <<= 1;
            if (zArr[i5]) {
                i4 |= 1;
            }
        }
        return i4;
    }

    private static int totalBitsInLayer(int i2, boolean z) {
        return ((z ? 88 : 112) + (i2 * 16)) * i2;
    }

    public DecoderResult decode(AztecDetectorResult aztecDetectorResult) throws FormatException {
        this.ddata = aztecDetectorResult;
        return new DecoderResult(null, getEncodedData(correctBits(extractBits(aztecDetectorResult.getBits()))), null, null);
    }

    boolean[] extractBits(BitMatrix bitMatrix) {
        boolean isCompact = this.ddata.isCompact();
        int nbLayers = this.ddata.getNbLayers();
        int i2 = isCompact ? (nbLayers * 4) + 11 : (nbLayers * 4) + 14;
        int[] iArr = new int[i2];
        boolean[] zArr = new boolean[totalBitsInLayer(nbLayers, isCompact)];
        int i3 = 2;
        if (isCompact) {
            for (int i4 = 0; i4 < i2; i4++) {
                iArr[i4] = i4;
            }
        } else {
            int i5 = i2 / 2;
            int i6 = ((i2 + 1) + (((i5 - 1) / 15) * 2)) / 2;
            for (int i7 = 0; i7 < i5; i7++) {
                iArr[(i5 - i7) - 1] = (i6 - r12) - 1;
                iArr[i5 + i7] = (i7 / 15) + i7 + i6 + 1;
            }
        }
        int i8 = 0;
        int i9 = 0;
        while (i8 < nbLayers) {
            int i10 = (nbLayers - i8) * 4;
            int i11 = isCompact ? i10 + 9 : i10 + 12;
            int i12 = i8 * 2;
            int i13 = (i2 - 1) - i12;
            int i14 = 0;
            while (i14 < i11) {
                int i15 = i14 * 2;
                int i16 = 0;
                while (i16 < i3) {
                    int i17 = i12 + i16;
                    int i18 = i12 + i14;
                    zArr[i9 + i15 + i16] = bitMatrix.get(iArr[i17], iArr[i18]);
                    int i19 = iArr[i18];
                    int i20 = i13 - i16;
                    zArr[(i11 * 2) + i9 + i15 + i16] = bitMatrix.get(i19, iArr[i20]);
                    int i21 = i13 - i14;
                    zArr[(i11 * 4) + i9 + i15 + i16] = bitMatrix.get(iArr[i20], iArr[i21]);
                    zArr[(i11 * 6) + i9 + i15 + i16] = bitMatrix.get(iArr[i21], iArr[i17]);
                    i16++;
                    nbLayers = nbLayers;
                    isCompact = isCompact;
                    i3 = 2;
                }
                i14++;
                i3 = 2;
            }
            i9 += i11 * 8;
            i8++;
            i3 = 2;
        }
        return zArr;
    }
}
