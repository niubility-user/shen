package com.jingdong.common.utils;

import com.jingdong.common.utils.personal.PersonalStaticConfigCacheHelper;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0004J\r\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004R$\u0010\u0006\u001a\u0004\u0018\u00010\u00058F@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0006\u0010\u0007\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\"\u0010\r\u001a\u00020\f8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\r\u0010\u000e\u001a\u0004\b\r\u0010\u000f\"\u0004\b\u0010\u0010\u0011\u00a8\u0006\u0013"}, d2 = {"Lcom/jingdong/common/utils/PersonalNetDataManager;", "", "", "destory", "()V", "", "staticData", "Ljava/lang/String;", "getStaticData", "()Ljava/lang/String;", "setStaticData", "(Ljava/lang/String;)V", "", "isUpdate", "Z", "()Z", "setUpdate", "(Z)V", "<init>", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public final class PersonalNetDataManager {
    public static final PersonalNetDataManager INSTANCE = new PersonalNetDataManager();
    private static boolean isUpdate;
    @Nullable
    private static String staticData;

    private PersonalNetDataManager() {
    }

    public final void destory() {
        staticData = null;
    }

    @Nullable
    public final String getStaticData() {
        String str = staticData;
        if ((str == null || str.length() == 0) || isUpdate) {
            staticData = PersonalStaticConfigCacheHelper.convertStreamToString();
            isUpdate = false;
        }
        return staticData;
    }

    public final boolean isUpdate() {
        return isUpdate;
    }

    public final void setStaticData(@Nullable String str) {
        staticData = str;
    }

    public final void setUpdate(boolean z) {
        isUpdate = z;
    }
}
