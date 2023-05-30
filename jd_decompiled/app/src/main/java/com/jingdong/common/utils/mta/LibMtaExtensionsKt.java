package com.jingdong.common.utils.mta;

import android.content.Context;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.common.entity.personal.LibMtaEntity;
import com.jingdong.common.utils.ABTestUtils;
import java.util.HashMap;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\u001d\u0010\u0004\u001a\u00020\u0003*\u0004\u0018\u00010\u00002\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001\u00a2\u0006\u0004\b\u0004\u0010\u0005\u001a\u001d\u0010\u0006\u001a\u00020\u0003*\u0004\u0018\u00010\u00002\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001\u00a2\u0006\u0004\b\u0006\u0010\u0005\u001a'\u0010\t\u001a\u00020\u0003*\u0004\u0018\u00010\u00002\b\u0010\u0002\u001a\u0004\u0018\u00010\u00012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0004\b\t\u0010\n\u001aE\u0010\u000e\u001a\u00020\u0003*\u0004\u0018\u00010\u00002\b\u0010\u0002\u001a\u0004\u0018\u00010\u00012&\u0010\r\u001a\"\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u000bj\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u0001`\f\u00a2\u0006\u0004\b\u000e\u0010\u000f\u001aE\u0010\u0010\u001a\u00020\u0003*\u0004\u0018\u00010\u00002\b\u0010\u0002\u001a\u0004\u0018\u00010\u00012&\u0010\r\u001a\"\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u000bj\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u0001`\f\u00a2\u0006\u0004\b\u0010\u0010\u000f\u001a'\u0010\u0011\u001a\u00020\u0003*\u0004\u0018\u00010\u00002\b\u0010\u0002\u001a\u0004\u0018\u00010\u00012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0004\b\u0011\u0010\n\u001a'\u0010\u0012\u001a\u00020\u0003*\u0004\u0018\u00010\u00002\b\u0010\u0002\u001a\u0004\u0018\u00010\u00012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0004\b\u0012\u0010\n\u00a8\u0006\u0013"}, d2 = {"Lcom/jingdong/common/entity/personal/LibMtaEntity;", "Landroid/content/Context;", AnnoConst.Constructor_Context, "", "clickWithExtMap", "(Lcom/jingdong/common/entity/personal/LibMtaEntity;Landroid/content/Context;)V", "exposeWithExtMap", "", "expoId", "exposeOnlyOnce", "(Lcom/jingdong/common/entity/personal/LibMtaEntity;Landroid/content/Context;Ljava/lang/String;)V", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", ABTestUtils.KEY_BASE_ARCH_CONFIG_NAMESPACE, "clickWithABTest", "(Lcom/jingdong/common/entity/personal/LibMtaEntity;Landroid/content/Context;Ljava/util/HashMap;)V", "exposeWithABTest", "exposeOnlyOnceNoRefreshWithJsonParam", "exposeOnlyOnceNoRefNoBackWithJsonParam", "personallib"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public final class LibMtaExtensionsKt {
    public static final void clickWithABTest(@Nullable LibMtaEntity libMtaEntity, @Nullable Context context, @Nullable HashMap<String, String> hashMap) {
        if (libMtaEntity == null || context == null) {
            return;
        }
        SimpleMtaUtil.clickWithABTest$default(context, libMtaEntity, hashMap, null, 8, null);
    }

    public static final void clickWithExtMap(@Nullable LibMtaEntity libMtaEntity, @Nullable Context context) {
        SimpleMtaUtil.clickWithExtMap(context, libMtaEntity);
    }

    public static final void exposeOnlyOnce(@Nullable LibMtaEntity libMtaEntity, @Nullable Context context, @Nullable String str) {
        if (libMtaEntity == null || context == null) {
            return;
        }
        SimpleMtaUtil.exposeOnlyOnceWithJsonParam(context, libMtaEntity, str);
    }

    public static final void exposeOnlyOnceNoRefNoBackWithJsonParam(@Nullable LibMtaEntity libMtaEntity, @Nullable Context context, @Nullable String str) {
        if (libMtaEntity == null || context == null) {
            return;
        }
        SimpleMtaUtil.exposeOnlyOnceNoRefNoBackWithJsonParam(context, libMtaEntity, str);
    }

    public static final void exposeOnlyOnceNoRefreshWithJsonParam(@Nullable LibMtaEntity libMtaEntity, @Nullable Context context, @Nullable String str) {
        if (libMtaEntity == null || context == null) {
            return;
        }
        SimpleMtaUtil.exposeOnlyOnceNoRefreshWithJsonParam(context, libMtaEntity, str);
    }

    public static final void exposeWithABTest(@Nullable LibMtaEntity libMtaEntity, @Nullable Context context, @Nullable HashMap<String, String> hashMap) {
        if (libMtaEntity == null || context == null) {
            return;
        }
        SimpleMtaUtil.exposeWithABTest$default(context, libMtaEntity, hashMap, null, 8, null);
    }

    public static final void exposeWithExtMap(@Nullable LibMtaEntity libMtaEntity, @Nullable Context context) {
        SimpleMtaUtil.exposeWithExtMap(context, libMtaEntity);
    }
}
