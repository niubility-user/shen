package com.jingdong.common.utils.mta;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import com.jd.dynamic.DYConstants;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.common.abmta.ABMtaUtils;
import com.jingdong.common.entity.personal.LibMtaEntity;
import com.jingdong.common.utils.ABTestUtils;
import com.jingdong.common.utils.mta.controller.ExposeOnlyOnceController;
import com.jingdong.common.utils.mta.imp.AbstractMtaUtil;
import com.jingdong.sdk.jdcrashreport.JdCrashReport;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u00c7\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b0\u0010\u0019J#\u0010\u0007\u001a\u00020\u00062\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007\u00a2\u0006\u0004\b\u0007\u0010\bJu\u0010\u000e\u001a\u00020\u00062\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042&\u0010\f\u001a\"\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tj\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0018\u0001`\u000b2(\b\u0002\u0010\r\u001a\"\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tj\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0018\u0001`\u000bH\u0007\u00a2\u0006\u0004\b\u000e\u0010\u000fJ#\u0010\u0010\u001a\u00020\u00062\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007\u00a2\u0006\u0004\b\u0010\u0010\bJu\u0010\u0011\u001a\u00020\u00062\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042&\u0010\f\u001a\"\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tj\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0018\u0001`\u000b2(\b\u0002\u0010\r\u001a\"\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tj\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0018\u0001`\u000bH\u0007\u00a2\u0006\u0004\b\u0011\u0010\u000fJ-\u0010\u0014\u001a\u00020\u00062\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\u0012\u001a\u0004\u0018\u00010\u00042\b\u0010\u0013\u001a\u0004\u0018\u00010\nH\u0007\u00a2\u0006\u0004\b\u0014\u0010\u0015J-\u0010\u0016\u001a\u00020\u00062\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\u0012\u001a\u0004\u0018\u00010\u00042\b\u0010\u0013\u001a\u0004\u0018\u00010\nH\u0007\u00a2\u0006\u0004\b\u0016\u0010\u0015J-\u0010\u0017\u001a\u00020\u00062\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\u0012\u001a\u0004\u0018\u00010\u00042\b\u0010\u0013\u001a\u0004\u0018\u00010\nH\u0007\u00a2\u0006\u0004\b\u0017\u0010\u0015J\u000f\u0010\u0018\u001a\u00020\u0006H\u0007\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u000f\u0010\u001a\u001a\u00020\u0006H\u0007\u00a2\u0006\u0004\b\u001a\u0010\u0019J\u000f\u0010\u001b\u001a\u00020\u0006H\u0007\u00a2\u0006\u0004\b\u001b\u0010\u0019J\u000f\u0010\u001c\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u001c\u0010\u0019J\u001b\u0010\u001d\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0002\u00a2\u0006\u0004\b\u001d\u0010\u001eJ#\u0010 \u001a\u00020\u00062\b\u0010\u001f\u001a\u0004\u0018\u00010\n2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0016\u00a2\u0006\u0004\b \u0010!J#\u0010#\u001a\u00020\u00062\b\u0010\u001f\u001a\u0004\u0018\u00010\n2\b\u0010\"\u001a\u0004\u0018\u00010\nH\u0016\u00a2\u0006\u0004\b#\u0010$J\u000f\u0010%\u001a\u00020\u0006H\u0016\u00a2\u0006\u0004\b%\u0010\u0019R\u0016\u0010&\u001a\u00020\n8\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b&\u0010'R\u0085\u0001\u0010/\u001aj\u0012\u0006\u0012\u0004\u0018\u00010\n\u0012(\u0012&\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0018\u0012\u0016\u0012\u0006\u0012\u0004\u0018\u00010\n0)j\n\u0012\u0006\u0012\u0004\u0018\u00010\n`*\u0018\u00010(0\tj4\u0012\u0006\u0012\u0004\u0018\u00010\n\u0012(\u0012&\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0018\u0012\u0016\u0012\u0006\u0012\u0004\u0018\u00010\n0)j\n\u0012\u0006\u0012\u0004\u0018\u00010\n`*\u0018\u00010(`\u000b8B@\u0002X\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b+\u0010,\u001a\u0004\b-\u0010.\u00a8\u00061"}, d2 = {"Lcom/jingdong/common/utils/mta/SimpleMtaUtil;", "Lcom/jingdong/common/utils/mta/imp/AbstractMtaUtil;", "Landroid/content/Context;", AnnoConst.Constructor_Context, "Lcom/jingdong/common/entity/personal/LibMtaEntity;", "mtaEntity", "", "clickWithExtMap", "(Landroid/content/Context;Lcom/jingdong/common/entity/personal/LibMtaEntity;)V", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", ABTestUtils.KEY_BASE_ARCH_CONFIG_NAMESPACE, "ext", "clickWithABTest", "(Landroid/content/Context;Lcom/jingdong/common/entity/personal/LibMtaEntity;Ljava/util/HashMap;Ljava/util/HashMap;)V", "exposeWithExtMap", "exposeWithABTest", "entity", "expoId", "exposeOnlyOnceWithJsonParam", "(Landroid/content/Context;Lcom/jingdong/common/entity/personal/LibMtaEntity;Ljava/lang/String;)V", "exposeOnlyOnceNoRefreshWithJsonParam", "exposeOnlyOnceNoRefNoBackWithJsonParam", "exposeOnlyOnceReset", "()V", "exposeOnlyOnceResetNoRefNoBack", "exposeOnlyOnceResetNoRef", "handleDoMta", "checkEmpty", "(Lcom/jingdong/common/entity/personal/LibMtaEntity;)Lcom/jingdong/common/entity/personal/LibMtaEntity;", "refId", "addExposureEntity", "(Ljava/lang/String;Lcom/jingdong/common/entity/personal/LibMtaEntity;)V", "jsonParam", "addJsonParam", "(Ljava/lang/String;Ljava/lang/String;)V", "doMta", "DEFAULT_PAGE_NAME", "Ljava/lang/String;", "Lkotlin/Pair;", "Ljava/util/LinkedHashSet;", "Lkotlin/collections/LinkedHashSet;", "mtaMap$delegate", "Lkotlin/Lazy;", "getMtaMap", "()Ljava/util/HashMap;", "mtaMap", "<init>", "personallib"}, k = 1, mv = {1, 4, 0})
@SuppressLint({"StaticFieldLeak"})
/* loaded from: classes6.dex */
public final class SimpleMtaUtil extends AbstractMtaUtil {
    private static final String DEFAULT_PAGE_NAME = "JDPersonalFragment";
    public static final SimpleMtaUtil INSTANCE = new SimpleMtaUtil();

    /* renamed from: mtaMap$delegate  reason: from kotlin metadata */
    private static final Lazy mtaMap;

    static {
        Lazy lazy;
        lazy = LazyKt__LazyJVMKt.lazy(new Function0<HashMap<String, Pair<? extends LibMtaEntity, ? extends LinkedHashSet<String>>>>() { // from class: com.jingdong.common.utils.mta.SimpleMtaUtil$mtaMap$2
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final HashMap<String, Pair<? extends LibMtaEntity, ? extends LinkedHashSet<String>>> invoke() {
                return new HashMap<>();
            }
        });
        mtaMap = lazy;
    }

    private SimpleMtaUtil() {
    }

    private final LibMtaEntity checkEmpty(LibMtaEntity mtaEntity) {
        String str = mtaEntity != null ? mtaEntity.eventId : null;
        if ((str == null || str.length() == 0) && mtaEntity != null) {
            mtaEntity.eventId = "";
        }
        String str2 = mtaEntity != null ? mtaEntity.eventParam : null;
        if ((str2 == null || str2.length() == 0) && mtaEntity != null) {
            mtaEntity.eventParam = "";
        }
        String str3 = mtaEntity != null ? mtaEntity.pageId : null;
        if ((str3 == null || str3.length() == 0) && mtaEntity != null) {
            mtaEntity.pageId = "";
        }
        String str4 = mtaEntity != null ? mtaEntity.pageParam : null;
        if ((str4 == null || str4.length() == 0) && mtaEntity != null) {
            mtaEntity.pageParam = "";
        }
        String str5 = mtaEntity != null ? mtaEntity.jsonParam : null;
        if ((str5 == null || str5.length() == 0) && mtaEntity != null) {
            mtaEntity.jsonParam = "";
        }
        return mtaEntity;
    }

    @JvmStatic
    public static final void clickWithABTest(@Nullable Context context, @Nullable LibMtaEntity mtaEntity, @Nullable HashMap<String, String> abTest, @Nullable HashMap<String, String> ext) {
        String str;
        String str2;
        if (context == null || mtaEntity == null) {
            return;
        }
        SimpleMtaUtil simpleMtaUtil = INSTANCE;
        LibMtaEntity checkEmpty = simpleMtaUtil.checkEmpty(mtaEntity);
        if (context instanceof Activity) {
            str = context.getClass().getName();
            Intrinsics.checkExpressionValueIsNotNull(str, "context::class.java.name");
        } else {
            str = "JDPersonalFragment";
        }
        String str3 = str;
        if (checkEmpty != null) {
            try {
                str2 = checkEmpty.eventId;
            } catch (Exception e2) {
                JdCrashReport.postCaughtException(new IllegalArgumentException("AbstractMtaUtil mtaWithABTest get error: " + e2));
                return;
            }
        } else {
            str2 = null;
        }
        ABMtaUtils.sendClickDataWithExt(context, str2, checkEmpty != null ? checkEmpty.eventParam : null, "", checkEmpty != null ? checkEmpty.pageId : null, str3, checkEmpty != null ? checkEmpty.pageParam : null, "", checkEmpty != null ? checkEmpty.jsonParam : null, ext, abTest);
        simpleMtaUtil.logMtaWithId(checkEmpty != null ? checkEmpty.eventId : null, checkEmpty != null ? checkEmpty.eventParam : null, checkEmpty != null ? checkEmpty.pageId : null, str3, checkEmpty != null ? checkEmpty.pageParam : null, checkEmpty != null ? checkEmpty.jsonParam : null, true, abTest);
    }

    public static /* synthetic */ void clickWithABTest$default(Context context, LibMtaEntity libMtaEntity, HashMap hashMap, HashMap hashMap2, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            hashMap2 = null;
        }
        clickWithABTest(context, libMtaEntity, hashMap, hashMap2);
    }

    @JvmStatic
    public static final void clickWithExtMap(@Nullable Context context, @Nullable LibMtaEntity mtaEntity) {
        String str;
        if (context == null || mtaEntity == null) {
            return;
        }
        SimpleMtaUtil simpleMtaUtil = INSTANCE;
        LibMtaEntity checkEmpty = simpleMtaUtil.checkEmpty(mtaEntity);
        if (context instanceof Activity) {
            str = context.getClass().getName();
            Intrinsics.checkExpressionValueIsNotNull(str, "context::class.java.name");
        } else {
            str = "JDPersonalFragment";
        }
        simpleMtaUtil.clickMtaExtMap(context, checkEmpty != null ? checkEmpty.eventId : null, checkEmpty != null ? checkEmpty.eventParam : null, checkEmpty != null ? checkEmpty.pageId : null, str, checkEmpty != null ? checkEmpty.pageParam : null, checkEmpty != null ? checkEmpty.jsonParam : null, checkEmpty != null ? checkEmpty.extendParam : null);
    }

    @JvmStatic
    public static final void exposeOnlyOnceNoRefNoBackWithJsonParam(@Nullable Context context, @Nullable LibMtaEntity entity, @Nullable String expoId) {
        if (ExposeOnlyOnceController.INSTANCE.addNoRefNoBackExposedId(expoId)) {
            exposeWithExtMap(context, entity);
        }
    }

    @JvmStatic
    public static final void exposeOnlyOnceNoRefreshWithJsonParam(@Nullable Context context, @Nullable LibMtaEntity entity, @Nullable String expoId) {
        if (ExposeOnlyOnceController.INSTANCE.addNoRefExposedId(expoId)) {
            exposeWithExtMap(context, entity);
        }
    }

    @JvmStatic
    public static final void exposeOnlyOnceReset() {
        ExposeOnlyOnceController.INSTANCE.reset();
    }

    @JvmStatic
    public static final void exposeOnlyOnceResetNoRef() {
        ExposeOnlyOnceController.INSTANCE.resetNoRef();
    }

    @JvmStatic
    public static final void exposeOnlyOnceResetNoRefNoBack() {
        ExposeOnlyOnceController.INSTANCE.resetNoRefNoBack();
    }

    @JvmStatic
    public static final void exposeOnlyOnceWithJsonParam(@Nullable Context context, @Nullable LibMtaEntity entity, @Nullable String expoId) {
        if (ExposeOnlyOnceController.INSTANCE.addExposeId(expoId)) {
            exposeWithExtMap(context, entity);
        }
    }

    @JvmStatic
    public static final void exposeWithABTest(@Nullable Context context, @Nullable LibMtaEntity mtaEntity, @Nullable HashMap<String, String> abTest, @Nullable HashMap<String, String> ext) {
        String str;
        String str2;
        if (context == null || mtaEntity == null) {
            return;
        }
        SimpleMtaUtil simpleMtaUtil = INSTANCE;
        LibMtaEntity checkEmpty = simpleMtaUtil.checkEmpty(mtaEntity);
        if (context instanceof Activity) {
            str = context.getClass().getName();
            Intrinsics.checkExpressionValueIsNotNull(str, "context::class.java.name");
        } else {
            str = "JDPersonalFragment";
        }
        String str3 = str;
        if (checkEmpty != null) {
            try {
                str2 = checkEmpty.eventId;
            } catch (Exception e2) {
                JdCrashReport.postCaughtException(new IllegalArgumentException("AbstractMtaUtil exposureJsonParamWithABTest get error: " + e2));
                return;
            }
        } else {
            str2 = null;
        }
        ABMtaUtils.sendExposureDataWithExt(context, str2, checkEmpty != null ? checkEmpty.eventParam : null, checkEmpty != null ? checkEmpty.pageId : null, str3, checkEmpty != null ? checkEmpty.pageParam : null, checkEmpty != null ? checkEmpty.jsonParam : null, ext, abTest);
        simpleMtaUtil.logMtaWithId(checkEmpty != null ? checkEmpty.eventId : null, checkEmpty != null ? checkEmpty.eventParam : null, checkEmpty != null ? checkEmpty.pageId : null, str3, checkEmpty != null ? checkEmpty.pageParam : null, checkEmpty != null ? checkEmpty.jsonParam : null, false, abTest);
    }

    public static /* synthetic */ void exposeWithABTest$default(Context context, LibMtaEntity libMtaEntity, HashMap hashMap, HashMap hashMap2, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            hashMap2 = null;
        }
        exposeWithABTest(context, libMtaEntity, hashMap, hashMap2);
    }

    @JvmStatic
    public static final void exposeWithExtMap(@Nullable Context context, @Nullable LibMtaEntity mtaEntity) {
        String str;
        if (context == null || mtaEntity == null) {
            return;
        }
        SimpleMtaUtil simpleMtaUtil = INSTANCE;
        LibMtaEntity checkEmpty = simpleMtaUtil.checkEmpty(mtaEntity);
        if (context instanceof Activity) {
            str = context.getClass().getName();
            Intrinsics.checkExpressionValueIsNotNull(str, "context::class.java.name");
        } else {
            str = "JDPersonalFragment";
        }
        simpleMtaUtil.exposureMtaExtMap(context, checkEmpty != null ? checkEmpty.eventId : null, checkEmpty != null ? checkEmpty.eventParam : null, checkEmpty != null ? checkEmpty.pageId : null, str, checkEmpty != null ? checkEmpty.pageParam : null, checkEmpty != null ? checkEmpty.jsonParam : null, checkEmpty != null ? checkEmpty.extendParam : null);
    }

    private final HashMap<String, Pair<LibMtaEntity, LinkedHashSet<String>>> getMtaMap() {
        return (HashMap) mtaMap.getValue();
    }

    private final void handleDoMta() {
        LibMtaEntity first;
        String joinToString$default;
        for (Map.Entry<String, Pair<LibMtaEntity, LinkedHashSet<String>>> entry : getMtaMap().entrySet()) {
            entry.getKey();
            Pair<LibMtaEntity, LinkedHashSet<String>> value = entry.getValue();
            if (value != null && (first = value.getFirst()) != null) {
                LinkedHashSet<String> second = value.getSecond();
                if (second != null) {
                    if (!(second.size() != 0)) {
                        second = null;
                    }
                    if (second != null) {
                        joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(second, DYConstants.DY_REGEX_COMMA, "[", "]", 0, null, null, 56, null);
                        first.jsonParam = joinToString$default;
                        second.clear();
                    }
                }
                exposeWithExtMap(INSTANCE.getContext(), value.getFirst());
            }
        }
        getMtaMap().clear();
    }

    @Override // com.jingdong.common.utils.mta.base.CommonMtaInterface
    public void addExposureEntity(@Nullable String refId, @Nullable LibMtaEntity mtaEntity) {
        HashMap<String, Pair<LibMtaEntity, LinkedHashSet<String>>> mtaMap2 = getMtaMap();
        if ((!mtaMap2.containsKey(refId)) == false) {
            mtaMap2 = null;
        }
        if (mtaMap2 != null) {
            INSTANCE.getMtaMap().put(refId, new Pair<>(mtaEntity, new LinkedHashSet()));
        }
    }

    @Override // com.jingdong.common.utils.mta.base.CommonMtaInterface
    public void addJsonParam(@Nullable String refId, @Nullable String jsonParam) {
        LinkedHashSet<String> second;
        Pair<LibMtaEntity, LinkedHashSet<String>> pair = getMtaMap().get(refId);
        if (pair == null || (second = pair.getSecond()) == null) {
            return;
        }
        second.add(jsonParam);
    }

    @Override // com.jingdong.common.utils.mta.base.CommonMtaInterface
    public void doMta() {
        handleDoMta();
    }
}
