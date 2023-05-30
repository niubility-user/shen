package kotlin;

import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.apkcenter.ApkDownloadTable;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a/\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0001\u001a\u00020\u00002\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00030\u0002H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0006\u0010\u0007\u001a\u001f\u0010\u000b\u001a\u00020\u00052\n\u0010\b\u001a\u00020\u0005\"\u00020\u0003H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\t\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\f"}, d2 = {"", ApkDownloadTable.FIELD_SIZE, "Lkotlin/Function1;", "Lkotlin/UByte;", XView2Constants.XVIEW2_ACTION_INIT, "Lkotlin/UByteArray;", "UByteArray", "(ILkotlin/jvm/functions/Function1;)[B", "elements", "ubyteArrayOf-GBYM_sE", "([B)[B", "ubyteArrayOf", "kotlin-stdlib"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class UByteArrayKt {
    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final byte[] UByteArray(int i2, Function1<? super Integer, UByte> function1) {
        byte[] bArr = new byte[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            bArr[i3] = function1.invoke(Integer.valueOf(i3)).getData();
        }
        return UByteArray.m262constructorimpl(bArr);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: ubyteArrayOf-GBYM_sE  reason: not valid java name */
    private static final byte[] m277ubyteArrayOfGBYM_sE(byte... bArr) {
        return bArr;
    }
}
