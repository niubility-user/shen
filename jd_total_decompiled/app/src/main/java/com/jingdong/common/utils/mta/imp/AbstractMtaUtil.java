package com.jingdong.common.utils.mta.imp;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import com.facebook.react.uimanager.events.TouchesHelper;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.app.mall.e;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.utils.mta.base.CommonMtaInterface;
import com.jingdong.common.utils.mta.base.JdPersonalMtaInterface;
import com.jingdong.common.utils.mta.base.MtaLifecycleObserver;
import com.jingdong.common.web.managers.WebPerfManager;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\b&\u0018\u0000 :2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001:B\u0007\u00a2\u0006\u0004\b9\u0010*J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u000b\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b\u000b\u0010\fJ\u001b\u0010\u0010\u001a\u00020\u00062\n\u0010\u000f\u001a\u00060\rj\u0002`\u000eH\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0011JU\u0010\u001a\u001a\u00020\u00062\b\u0010\u0013\u001a\u0004\u0018\u00010\u00122\b\u0010\u0014\u001a\u0004\u0018\u00010\u00042\b\u0010\u0015\u001a\u0004\u0018\u00010\u00042\b\u0010\u0016\u001a\u0004\u0018\u00010\u00042\b\u0010\u0017\u001a\u0004\u0018\u00010\u00042\b\u0010\u0018\u001a\u0004\u0018\u00010\u00042\b\u0010\u0019\u001a\u0004\u0018\u00010\u0004H\u0016\u00a2\u0006\u0004\b\u001a\u0010\u001bJ}\u0010\u001f\u001a\u00020\u00062\b\u0010\u0013\u001a\u0004\u0018\u00010\u00122\b\u0010\u0014\u001a\u0004\u0018\u00010\u00042\b\u0010\u0015\u001a\u0004\u0018\u00010\u00042\b\u0010\u0016\u001a\u0004\u0018\u00010\u00042\b\u0010\u0017\u001a\u0004\u0018\u00010\u00042\b\u0010\u0018\u001a\u0004\u0018\u00010\u00042\b\u0010\u0019\u001a\u0004\u0018\u00010\u00042&\u0010\u001e\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u001cj\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u001dH\u0016\u00a2\u0006\u0004\b\u001f\u0010 JU\u0010!\u001a\u00020\u00062\b\u0010\u0013\u001a\u0004\u0018\u00010\u00122\b\u0010\u0014\u001a\u0004\u0018\u00010\u00042\b\u0010\u0015\u001a\u0004\u0018\u00010\u00042\b\u0010\u0016\u001a\u0004\u0018\u00010\u00042\b\u0010\u0017\u001a\u0004\u0018\u00010\u00042\b\u0010\u0018\u001a\u0004\u0018\u00010\u00042\b\u0010\u0019\u001a\u0004\u0018\u00010\u0004H\u0016\u00a2\u0006\u0004\b!\u0010\u001bJ}\u0010\"\u001a\u00020\u00062\b\u0010\u0013\u001a\u0004\u0018\u00010\u00122\b\u0010\u0014\u001a\u0004\u0018\u00010\u00042\b\u0010\u0015\u001a\u0004\u0018\u00010\u00042\b\u0010\u0016\u001a\u0004\u0018\u00010\u00042\b\u0010\u0017\u001a\u0004\u0018\u00010\u00042\b\u0010\u0018\u001a\u0004\u0018\u00010\u00042\b\u0010\u0019\u001a\u0004\u0018\u00010\u00042&\u0010\u001e\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u001cj\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u001dH\u0016\u00a2\u0006\u0004\b\"\u0010 J!\u0010%\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010$\u001a\u0004\u0018\u00010#H\u0016\u00a2\u0006\u0004\b%\u0010&J!\u0010%\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010$\u001a\u0004\u0018\u00010'H\u0016\u00a2\u0006\u0004\b%\u0010(J\u000f\u0010)\u001a\u00020\u0006H\u0016\u00a2\u0006\u0004\b)\u0010*J\u000f\u0010+\u001a\u0004\u0018\u00010\u0012\u00a2\u0006\u0004\b+\u0010,J\u0017\u0010-\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b-\u0010\bJ{\u00100\u001a\u00020\u00062\b\u0010\u0014\u001a\u0004\u0018\u00010\u00042\b\u0010\u0015\u001a\u0004\u0018\u00010\u00042\b\u0010\u0016\u001a\u0004\u0018\u00010\u00042\b\u0010\u0017\u001a\u0004\u0018\u00010\u00042\b\u0010\u0018\u001a\u0004\u0018\u00010\u00042\b\u0010\u0019\u001a\u0004\u0018\u00010\u00042\u0006\u0010/\u001a\u00020.2(\b\u0002\u0010\u001e\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u001cj\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u001d\u00a2\u0006\u0004\b0\u00101R\u0018\u0010\u0013\u001a\u0004\u0018\u00010\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0013\u00102RA\u00108\u001a&\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0006\u0012\u0004\u0018\u0001030\u001cj\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0006\u0012\u0004\u0018\u000103`\u001d8B@\u0002X\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b4\u00105\u001a\u0004\b6\u00107\u00a8\u0006;"}, d2 = {"Lcom/jingdong/common/utils/mta/imp/AbstractMtaUtil;", "Lcom/jingdong/common/utils/mta/base/JdPersonalMtaInterface;", "Lcom/jingdong/common/utils/mta/base/MtaLifecycleObserver;", "Lcom/jingdong/common/utils/mta/base/CommonMtaInterface;", "", "key", "", "registerLifecycleObserver", "(Ljava/lang/String;)V", "", "t", "logThrowableExp", "(Ljava/lang/Throwable;)V", "Ljava/lang/Exception;", "Lkotlin/Exception;", e.a, "logException", "(Ljava/lang/Exception;)V", "Landroid/content/Context;", AnnoConst.Constructor_Context, "eventId", "eventParam", "pageId", WebPerfManager.PAGE_NAME, "pageParam", "jsonParam", "clickMta", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "ext", "clickMtaExtMap", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/HashMap;)V", "exposureMta", "exposureMtaExtMap", "Landroidx/fragment/app/Fragment;", TouchesHelper.TARGET_KEY, "attachLifecycleOwner", "(Ljava/lang/String;Landroidx/fragment/app/Fragment;)V", "Lcom/jingdong/common/BaseActivity;", "(Ljava/lang/String;Lcom/jingdong/common/BaseActivity;)V", "onPause", "()V", "getContext", "()Landroid/content/Context;", "release", "", "isClickMta", "logMtaWithId", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/util/HashMap;)V", "Landroid/content/Context;", "Landroidx/lifecycle/LifecycleOwner;", "lifecycleOwners$delegate", "Lkotlin/Lazy;", "getLifecycleOwners", "()Ljava/util/HashMap;", "lifecycleOwners", "<init>", "Companion", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public abstract class AbstractMtaUtil implements JdPersonalMtaInterface, MtaLifecycleObserver, CommonMtaInterface {
    @NotNull
    public static final String MTA_TAG = "MtaEntity";
    @NotNull
    public static final String TAG = "AbstractMtaUtil";
    private Context context;

    /* renamed from: lifecycleOwners$delegate  reason: from kotlin metadata */
    private final Lazy lifecycleOwners;

    public AbstractMtaUtil() {
        Lazy lazy;
        lazy = LazyKt__LazyJVMKt.lazy(new Function0<HashMap<String, LifecycleOwner>>() { // from class: com.jingdong.common.utils.mta.imp.AbstractMtaUtil$lifecycleOwners$2
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final HashMap<String, LifecycleOwner> invoke() {
                return new HashMap<>();
            }
        });
        this.lifecycleOwners = lazy;
    }

    private final HashMap<String, LifecycleOwner> getLifecycleOwners() {
        return (HashMap) this.lifecycleOwners.getValue();
    }

    private final void logException(Exception r3) {
        if (OKLog.D) {
            Log.d(TAG, "Exception : " + r3.getMessage());
        }
    }

    public static /* synthetic */ void logMtaWithId$default(AbstractMtaUtil abstractMtaUtil, String str, String str2, String str3, String str4, String str5, String str6, boolean z, HashMap hashMap, int i2, Object obj) {
        if (obj == null) {
            abstractMtaUtil.logMtaWithId(str, str2, str3, str4, str5, str6, z, (i2 & 128) != 0 ? null : hashMap);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: logMtaWithId");
    }

    private final void logThrowableExp(Throwable t) {
        if (OKLog.D) {
            Log.d(TAG, "Throwable : " + t.getMessage());
        }
    }

    private final void registerLifecycleObserver(String key) {
        Lifecycle lifecycle;
        LifecycleOwner lifecycleOwner = getLifecycleOwners().get(key);
        if (lifecycleOwner == null || (lifecycle = lifecycleOwner.getLifecycle()) == null) {
            return;
        }
        lifecycle.addObserver(this);
    }

    @Override // com.jingdong.common.utils.mta.base.CommonMtaInterface
    public void attachLifecycleOwner(@NotNull String key, @Nullable Fragment r4) {
        this.context = r4 != null ? r4.getContext() : null;
        getLifecycleOwners().put(key, r4 != null ? r4.getViewLifecycleOwner() : null);
        registerLifecycleObserver(key);
    }

    @Override // com.jingdong.common.utils.mta.base.JdPersonalMtaInterface
    public void clickMta(@Nullable Context r14, @Nullable String eventId, @Nullable String eventParam, @Nullable String pageId, @Nullable String r18, @Nullable String pageParam, @Nullable String jsonParam) {
        if (r14 != null) {
            try {
                JDMtaUtils.sendClickDataWithExt(r14, eventId, eventParam, "", pageId, r18, pageParam, "", jsonParam, null);
                logMtaWithId$default(this, eventId, eventParam, pageId, r18, pageParam, jsonParam, true, null, 128, null);
            } catch (NullPointerException e2) {
                logException(e2);
            } catch (Throwable th) {
                logThrowableExp(th);
            }
        }
    }

    @Override // com.jingdong.common.utils.mta.base.JdPersonalMtaInterface
    public void clickMtaExtMap(@Nullable Context r22, @Nullable String eventId, @Nullable String eventParam, @Nullable String pageId, @Nullable String r26, @Nullable String pageParam, @Nullable String jsonParam, @Nullable HashMap<String, String> ext) {
        if (r22 != null) {
            try {
                JDMtaUtils.sendClickDataWithExt(r22, eventId, eventParam, "", pageId, r26, pageParam, "", jsonParam, ext);
                logMtaWithId(eventId, eventParam, pageId, r26, pageParam, jsonParam, true, ext);
            } catch (NullPointerException e2) {
                logException(e2);
            } catch (Throwable th) {
                logThrowableExp(th);
            }
        }
    }

    @Override // com.jingdong.common.utils.mta.base.JdPersonalMtaInterface
    public void exposureMta(@Nullable Context r21, @Nullable String eventId, @Nullable String eventParam, @Nullable String pageId, @Nullable String r25, @Nullable String pageParam, @Nullable String jsonParam) {
        if (r21 != null) {
            try {
                JDMtaUtils.sendExposureDataWithExt(r21, eventId, eventParam, pageId, r25, pageParam, jsonParam, null);
                logMtaWithId$default(this, eventId, eventParam, pageId, r25, pageParam, jsonParam, false, null, 128, null);
            } catch (Exception e2) {
                logException(e2);
            }
        }
    }

    @Override // com.jingdong.common.utils.mta.base.JdPersonalMtaInterface
    public void exposureMtaExtMap(@Nullable Context r11, @Nullable String eventId, @Nullable String eventParam, @Nullable String pageId, @Nullable String r15, @Nullable String pageParam, @Nullable String jsonParam, @Nullable HashMap<String, String> ext) {
        if (r11 != null) {
            try {
                JDMtaUtils.sendExposureDataWithExt(r11, eventId, eventParam, pageId, r15, pageParam, jsonParam, ext);
                logMtaWithId(eventId, eventParam, pageId, r15, pageParam, jsonParam, false, ext);
            } catch (Exception e2) {
                logException(e2);
            }
        }
    }

    @Nullable
    public final Context getContext() {
        Context context = this.context;
        if (context != null) {
            return context.getApplicationContext();
        }
        return null;
    }

    public final void logMtaWithId(@Nullable String eventId, @Nullable String eventParam, @Nullable String pageId, @Nullable String r5, @Nullable String pageParam, @Nullable String jsonParam, boolean isClickMta, @Nullable HashMap<String, String> ext) {
        if (OKLog.D) {
            String str = isClickMta ? "\u70b9\u51fb\u57cb\u70b9\u53c2\u6570" : "\u66dd\u5149\u57cb\u70b9\u53c2\u6570";
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append("\uff1a eventId:");
            sb.append(eventId);
            sb.append("  eventParam:");
            sb.append(eventParam);
            sb.append("  pageId:");
            sb.append(pageId);
            sb.append(" pageName:");
            sb.append(r5);
            sb.append(" pageParam:");
            sb.append(pageParam);
            sb.append(" jsonParam:");
            sb.append(jsonParam);
            sb.append("  extMap:");
            sb.append(ext != null ? ext.toString() : null);
            OKLog.d(MTA_TAG, sb.toString());
        }
    }

    @Override // com.jingdong.common.utils.mta.base.MtaLifecycleObserver
    public void onPause() {
        doMta();
        if (OKLog.D) {
            OKLog.d(TAG, "onPause");
        }
    }

    @Override // com.jingdong.common.utils.mta.base.CommonMtaInterface
    public void release(@NotNull String key) {
        LifecycleOwner it = getLifecycleOwners().get(key);
        if (it != null) {
            Intrinsics.checkExpressionValueIsNotNull(it, "it");
            Lifecycle lifecycle = it.getLifecycle();
            if (lifecycle != null) {
                lifecycle.removeObserver(this);
            }
        }
        getLifecycleOwners().put(key, null);
    }

    @Override // com.jingdong.common.utils.mta.base.CommonMtaInterface
    public void attachLifecycleOwner(@NotNull String key, @Nullable BaseActivity r3) {
        this.context = r3;
        getLifecycleOwners().put(key, r3);
        registerLifecycleObserver(key);
    }
}
