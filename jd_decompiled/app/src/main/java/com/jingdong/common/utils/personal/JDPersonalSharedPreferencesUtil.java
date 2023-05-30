package com.jingdong.common.utils.personal;

import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.utils.JDSharedPreferences;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\f\u0010\bJ\u000f\u0010\u0003\u001a\u00020\u0002H\u0007\u00a2\u0006\u0004\b\u0003\u0010\u0004R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0083\u000e\u00a2\u0006\f\n\u0004\b\u0005\u0010\u0006\u0012\u0004\b\u0007\u0010\bR\u0016\u0010\n\u001a\u00020\t8\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\n\u0010\u000b\u00a8\u0006\r"}, d2 = {"Lcom/jingdong/common/utils/personal/JDPersonalSharedPreferencesUtil;", "", "Lcom/jingdong/jdsdk/utils/JDSharedPreferences;", "getSharedPreferences", "()Lcom/jingdong/jdsdk/utils/JDSharedPreferences;", "sharedPreferences", "Lcom/jingdong/jdsdk/utils/JDSharedPreferences;", "sharedPreferences$annotations", "()V", "", "FILE_NAME", "Ljava/lang/String;", "<init>", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public final class JDPersonalSharedPreferencesUtil {
    private static final String FILE_NAME = "jd_personal_sp";
    public static final JDPersonalSharedPreferencesUtil INSTANCE = new JDPersonalSharedPreferencesUtil();
    private static JDSharedPreferences sharedPreferences;

    private JDPersonalSharedPreferencesUtil() {
    }

    @JvmStatic
    @NotNull
    public static final synchronized JDSharedPreferences getSharedPreferences() {
        JDSharedPreferences jDSharedPreferences;
        synchronized (JDPersonalSharedPreferencesUtil.class) {
            if (sharedPreferences == null) {
                JdSdk jdSdk = JdSdk.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(jdSdk, "JdSdk.getInstance()");
                sharedPreferences = new JDSharedPreferences(jdSdk.getApplicationContext(), FILE_NAME, 0);
            }
            jDSharedPreferences = sharedPreferences;
            if (jDSharedPreferences == null) {
                Intrinsics.throwNpe();
            }
        }
        return jDSharedPreferences;
    }

    @JvmStatic
    private static /* synthetic */ void sharedPreferences$annotations() {
    }
}
