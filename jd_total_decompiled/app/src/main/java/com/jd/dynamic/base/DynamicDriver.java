package com.jd.dynamic.base;

import android.app.Activity;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.jd.dynamic.apis.DYContainerConfig;
import com.jd.dynamic.apis.DYTemplateStatus;
import com.jd.dynamic.apis.DYTemplateStatusImpl;
import com.jd.dynamic.apis.DynamicContainer;
import com.jd.dynamic.apis.IDYContainerListener;
import com.jd.dynamic.apis.IDynamicDriver;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.common.cart.clean.CartCleanConstants;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.jdma.minterface.BaseEvent;
import com.tencent.connect.common.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mp4parser.aspectj.lang.JoinPoint;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\bF\u0010GJ#\u0010\u0006\u001a\u00020\u00052\b\b\u0001\u0010\u0003\u001a\u00020\u00022\b\b\u0001\u0010\u0004\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u001b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\b\u0001\u0010\t\u001a\u00020\bH\u0016\u00a2\u0006\u0004\b\u000b\u0010\fJ%\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\b\u0001\u0010\t\u001a\u00020\b2\b\b\u0001\u0010\u000e\u001a\u00020\rH\u0016\u00a2\u0006\u0004\b\u000b\u0010\u000fJ#\u0010\u0012\u001a\u00020\u00112\b\b\u0001\u0010\u0003\u001a\u00020\u00022\b\b\u0001\u0010\u0010\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0012\u0010\u0013J'\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u0016H\u0016\u00a2\u0006\u0004\b\u0019\u0010\u001aJ!\u0010\u001b\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u001b\u0010\u001cJ%\u0010\u001f\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00022\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00020\u001dH\u0016\u00a2\u0006\u0004\b\u001f\u0010 J\u0019\u0010\"\u001a\u00020\u00052\b\u0010!\u001a\u0004\u0018\u00010\u0002H\u0016\u00a2\u0006\u0004\b\"\u0010#J#\u0010\"\u001a\u00020\u00052\b\u0010!\u001a\u0004\u0018\u00010\u00022\b\u0010$\u001a\u0004\u0018\u00010\u0002H\u0016\u00a2\u0006\u0004\b\"\u0010\u0007J\u001f\u0010(\u001a\u00020\u00052\u0006\u0010$\u001a\u00020%2\u0006\u0010'\u001a\u00020&H\u0016\u00a2\u0006\u0004\b(\u0010)J\u0017\u0010*\u001a\u00020&2\u0006\u0010$\u001a\u00020%H\u0016\u00a2\u0006\u0004\b*\u0010+J\u0017\u0010,\u001a\u00020&2\u0006\u0010$\u001a\u00020%H\u0016\u00a2\u0006\u0004\b,\u0010+J\u001f\u0010.\u001a\u00020\u00052\u0006\u0010$\u001a\u00020%2\u0006\u0010-\u001a\u00020&H\u0016\u00a2\u0006\u0004\b.\u0010)J\u001f\u0010/\u001a\u00020\u00052\u0006\u0010$\u001a\u00020%2\u0006\u0010-\u001a\u00020&H\u0016\u00a2\u0006\u0004\b/\u0010)J\u0017\u00100\u001a\u00020\u00052\u0006\u0010$\u001a\u00020%H\u0016\u00a2\u0006\u0004\b0\u00101J\u0017\u00103\u001a\u0002022\u0006\u0010$\u001a\u00020%H\u0016\u00a2\u0006\u0004\b3\u00104J\u0019\u00105\u001a\u00020\u00182\b\b\u0001\u0010\t\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b5\u00106J\u0015\u00108\u001a\u0002072\u0006\u0010\t\u001a\u00020\b\u00a2\u0006\u0004\b8\u00109R2\u0010=\u001a\u001e\u0012\u0004\u0012\u00020%\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020&0;j\b\u0012\u0004\u0012\u00020&`<0:8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b=\u0010>R\"\u0010?\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020&0:8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b?\u0010>R\u0016\u0010A\u001a\u00020@8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bA\u0010BR.\u0010C\u001a\u001a\u0012\u0004\u0012\u00020\u0002\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00160:0:8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bC\u0010>R\u0016\u0010D\u001a\u00020@8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bD\u0010BR\u0016\u0010E\u001a\u00020@8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bE\u0010B\u00a8\u0006H"}, d2 = {"Lcom/jd/dynamic/base/DynamicDriver;", "Lcom/jd/dynamic/apis/IDynamicDriver;", "", "module", BaseEvent.SCENE, "", JDReactConstant.PREPARE, "(Ljava/lang/String;Ljava/lang/String;)V", "Lcom/jd/dynamic/apis/DYContainerConfig;", "config", "Lcom/jd/dynamic/apis/DynamicContainer;", "createContainer", "(Lcom/jd/dynamic/apis/DYContainerConfig;)Lcom/jd/dynamic/apis/DynamicContainer;", "Lcom/jd/dynamic/apis/IDYContainerListener;", CartCleanConstants.CART_CLEAN_DIALOG_LISTENER, "(Lcom/jd/dynamic/apis/DYContainerConfig;Lcom/jd/dynamic/apis/IDYContainerListener;)Lcom/jd/dynamic/apis/DynamicContainer;", "templateId", "Lcom/jd/dynamic/apis/DYTemplateStatus;", "getTemplateStatus", "(Ljava/lang/String;Ljava/lang/String;)Lcom/jd/dynamic/apis/DYTemplateStatus;", "sysCode", "biz", "Lcom/jd/dynamic/base/IFunctionFactory;", "factory", "", "registerCustomFunctionFactory", "(Ljava/lang/String;Ljava/lang/String;Lcom/jd/dynamic/base/IFunctionFactory;)Z", "getFunctionFactory", "(Ljava/lang/String;Ljava/lang/String;)Lcom/jd/dynamic/base/IFunctionFactory;", "", "bizs", "prepareExp", "(Ljava/lang/String;Ljava/util/List;)V", Constants.PARAM_SCOPE, "clearScopedCache", "(Ljava/lang/String;)V", "key", "Landroid/app/Activity;", "", "group", "cacheContextGroup", "(Landroid/app/Activity;J)V", "getContextGroup", "(Landroid/app/Activity;)J", "removeContextGroup", AnnoConst.Constructor_Context, "cacheContext", "removeContext", "cleanContextCache", "(Landroid/app/Activity;)V", "", "getContextCacheSize", "(Landroid/app/Activity;)I", "checkConfig", "(Lcom/jd/dynamic/apis/DYContainerConfig;)Z", "Lcom/jd/dynamic/lib/utils/FakeDyContainer;", "createFakeContainer", "(Lcom/jd/dynamic/apis/DYContainerConfig;)Lcom/jd/dynamic/lib/utils/FakeDyContainer;", "Ljava/util/HashMap;", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "contextCache", "Ljava/util/HashMap;", "contextGroupCache", "", "contextLock", "Ljava/lang/Object;", "cusFactory", "groupLock", JoinPoint.SYNCHRONIZATION_LOCK, "<init>", "()V", "lib_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes13.dex */
public final class DynamicDriver implements IDynamicDriver {
    private final Object a = new Object();
    private final Object b = new Object();

    /* renamed from: c  reason: collision with root package name */
    private final Object f1825c = new Object();
    private HashMap<String, HashMap<String, IFunctionFactory>> d = new HashMap<>();

    /* renamed from: e  reason: collision with root package name */
    private HashMap<Activity, Long> f1826e = new HashMap<>();

    /* renamed from: f  reason: collision with root package name */
    private HashMap<Activity, ArrayList<Long>> f1827f = new HashMap<>();

    private final boolean a(@NonNull DYContainerConfig dYContainerConfig) {
        return (!(dYContainerConfig.getContext() instanceof Activity) || TextUtils.isEmpty(dYContainerConfig.getModule()) || TextUtils.isEmpty(dYContainerConfig.getTemplateId())) ? false : true;
    }

    @Override // com.jd.dynamic.apis.IDynamicDriver
    public void cacheContext(@NotNull Activity key, long context) {
        synchronized (this.f1825c) {
            ArrayList<Long> arrayList = this.f1827f.get(key);
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                this.f1827f.put(key, arrayList);
            }
            arrayList.add(Long.valueOf(context));
        }
    }

    @Override // com.jd.dynamic.apis.IDynamicDriver
    public void cacheContextGroup(@NotNull Activity key, long group) {
        synchronized (this.b) {
            this.f1826e.put(key, Long.valueOf(group));
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // com.jd.dynamic.apis.IDynamicDriver
    public void cleanContextCache(@NotNull Activity key) {
        synchronized (this.f1825c) {
            this.f1827f.remove(key);
        }
    }

    @Override // com.jd.dynamic.apis.IDynamicDriver
    public void clearScopedCache(@Nullable String scope) {
        if (scope != null) {
            com.jd.dynamic.b.m.d.a.b.b(scope);
        }
    }

    @Override // com.jd.dynamic.apis.IDynamicDriver
    public void clearScopedCache(@Nullable String scope, @Nullable String key) {
        if (scope == null || key == null) {
            return;
        }
        com.jd.dynamic.b.m.d.a.b.d(scope, key);
    }

    @Override // com.jd.dynamic.apis.IDynamicDriver
    @Nullable
    public DynamicContainer createContainer(@NonNull @NotNull DYContainerConfig config) {
        if (a(config)) {
            DynamicContainer dynamicContainer = new DynamicContainer(config.getContext());
            dynamicContainer.attachConfig(config);
            return dynamicContainer;
        }
        return null;
    }

    @Override // com.jd.dynamic.apis.IDynamicDriver
    @Nullable
    public DynamicContainer createContainer(@NonNull @NotNull DYContainerConfig config, @NonNull @NotNull IDYContainerListener listener) {
        if (a(config)) {
            DynamicContainer dynamicContainer = new DynamicContainer(config.getContext());
            dynamicContainer.attachConfig(config);
            dynamicContainer.attachListener(listener);
            listener.onCreate();
            return dynamicContainer;
        }
        return null;
    }

    @NotNull
    public final com.jd.dynamic.lib.utils.r createFakeContainer(@NotNull DYContainerConfig dYContainerConfig) {
        com.jd.dynamic.lib.utils.r rVar = new com.jd.dynamic.lib.utils.r();
        rVar.c(dYContainerConfig);
        return rVar;
    }

    @Override // com.jd.dynamic.apis.IDynamicDriver
    public int getContextCacheSize(@NotNull Activity key) {
        synchronized (this.f1825c) {
            if (this.f1827f.get(key) != null) {
                ArrayList<Long> arrayList = this.f1827f.get(key);
                if (arrayList == null) {
                    Intrinsics.throwNpe();
                }
                return arrayList.size();
            }
            return 0;
        }
    }

    @Override // com.jd.dynamic.apis.IDynamicDriver
    public long getContextGroup(@NotNull Activity key) {
        long j2;
        synchronized (this.b) {
            if (this.f1826e.containsKey(key)) {
                Long l2 = this.f1826e.get(key);
                if (l2 == null) {
                    Intrinsics.throwNpe();
                }
                j2 = l2.longValue();
            } else {
                j2 = -1;
            }
            Unit unit = Unit.INSTANCE;
        }
        return j2;
    }

    @Override // com.jd.dynamic.apis.IDynamicDriver
    @Nullable
    public IFunctionFactory getFunctionFactory(@NotNull String sysCode, @NotNull String biz) {
        IFunctionFactory iFunctionFactory;
        synchronized (this.a) {
            HashMap<String, IFunctionFactory> hashMap = this.d.get(sysCode);
            iFunctionFactory = hashMap == null ? null : hashMap.get(biz);
        }
        return iFunctionFactory;
    }

    @Override // com.jd.dynamic.apis.IDynamicDriver
    @NotNull
    public DYTemplateStatus getTemplateStatus(@NonNull @NotNull String module, @NonNull @NotNull String templateId) {
        return new DYTemplateStatusImpl(com.jd.dynamic.b.e.a.b.t(module, templateId), com.jd.dynamic.b.e.a.b.w(module, templateId));
    }

    @Override // com.jd.dynamic.apis.IDynamicDriver
    public void prepare(@NonNull @NotNull String module, @NonNull @NotNull String scene) {
        DynamicSdk.getEngine().newFetchTemplates(null, true, module);
    }

    @Override // com.jd.dynamic.apis.IDynamicDriver
    public void prepareExp(@NotNull String sysCode, @NotNull List<String> bizs) {
        new com.jd.dynamic.b.j.b().a(sysCode, bizs);
    }

    @Override // com.jd.dynamic.apis.IDynamicDriver
    public boolean registerCustomFunctionFactory(@NotNull String sysCode, @NotNull String biz, @NotNull IFunctionFactory factory) {
        boolean z;
        synchronized (this.a) {
            HashMap<String, IFunctionFactory> hashMap = this.d.get(sysCode);
            if (hashMap == null) {
                hashMap = new HashMap<>();
            }
            if (hashMap.get(biz) != null) {
                z = false;
            } else {
                hashMap.put(biz, factory);
                z = true;
            }
        }
        return z;
    }

    @Override // com.jd.dynamic.apis.IDynamicDriver
    public void removeContext(@NotNull Activity key, long context) {
        synchronized (this.f1825c) {
            ArrayList<Long> arrayList = this.f1827f.get(key);
            if (arrayList != null) {
                arrayList.remove(Long.valueOf(context));
            }
        }
    }

    @Override // com.jd.dynamic.apis.IDynamicDriver
    public long removeContextGroup(@NotNull Activity key) {
        long j2;
        synchronized (this.b) {
            if (this.f1826e.containsKey(key)) {
                Long remove = this.f1826e.remove(key);
                if (remove == null) {
                    Intrinsics.throwNpe();
                }
                j2 = remove.longValue();
            } else {
                j2 = -1;
            }
            Unit unit = Unit.INSTANCE;
        }
        return j2;
    }
}
