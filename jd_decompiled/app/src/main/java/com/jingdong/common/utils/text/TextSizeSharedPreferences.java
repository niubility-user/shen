package com.jingdong.common.utils.text;

import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.utils.JDSharedPreferences;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\f\u0010\bJ\u000f\u0010\u0003\u001a\u00020\u0002H\u0007\u00a2\u0006\u0004\b\u0003\u0010\u0004R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0083\u000e\u00a2\u0006\f\n\u0004\b\u0005\u0010\u0006\u0012\u0004\b\u0007\u0010\bR\u0016\u0010\n\u001a\u00020\t8\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\n\u0010\u000b\u00a8\u0006\r"}, d2 = {"Lcom/jingdong/common/utils/text/TextSizeSharedPreferences;", "", "Lcom/jingdong/jdsdk/utils/JDSharedPreferences;", "getSharePreferences", "()Lcom/jingdong/jdsdk/utils/JDSharedPreferences;", "sharePreferences", "Lcom/jingdong/jdsdk/utils/JDSharedPreferences;", "sharePreferences$annotations", "()V", "", "SP_NAME", "Ljava/lang/String;", "<init>", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public final class TextSizeSharedPreferences {
    public static final TextSizeSharedPreferences INSTANCE = new TextSizeSharedPreferences();
    private static final String SP_NAME = "jd_text_size_sp";
    private static JDSharedPreferences sharePreferences;

    private TextSizeSharedPreferences() {
    }

    @JvmStatic
    @NotNull
    public static final synchronized JDSharedPreferences getSharePreferences() {
        JDSharedPreferences jDSharedPreferences;
        synchronized (TextSizeSharedPreferences.class) {
            if (sharePreferences == null) {
                JdSdk jdSdk = JdSdk.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(jdSdk, "JdSdk.getInstance()");
                sharePreferences = new JDSharedPreferences(jdSdk.getApplicationContext(), SP_NAME, 0);
            }
            jDSharedPreferences = sharePreferences;
            if (jDSharedPreferences == null) {
                Intrinsics.throwNpe();
            }
        }
        return jDSharedPreferences;
    }

    @JvmStatic
    private static /* synthetic */ void sharePreferences$annotations() {
    }
}
