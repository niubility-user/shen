package com.jd.jdcache.service;

import com.jd.jdcache.service.base.AbstractDelegate;
import com.jd.jdcache.util.JDCacheLog;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u00c0\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0019J)\u0010\u0006\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\u0003*\u00020\u00022\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u001e\u0010\u0006\u001a\u0004\u0018\u00018\u0000\"\n\b\u0000\u0010\u0003\u0018\u0001*\u00020\u0002H\u0086\b\u00a2\u0006\u0004\b\u0006\u0010\bJ\u001d\u0010\u000b\u001a\u00020\n2\u000e\u0010\t\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u0004\u00a2\u0006\u0004\b\u000b\u0010\fR\u0016\u0010\u000e\u001a\u00020\r8\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u000fRe\u0010\u0017\u001aJ\u0012\u0004\u0012\u00020\r\u0012\u001a\u0012\u0018\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00110\u0010j$\u0012\u0004\u0012\u00020\r\u0012\u001a\u0012\u0018\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0011`\u00128B@\u0002X\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\u00a8\u0006\u001a"}, d2 = {"Lcom/jd/jdcache/service/DelegateManager;", "", "Lcom/jd/jdcache/service/base/AbstractDelegate;", "T", "Ljava/lang/Class;", "delegateType", "getDelegate", "(Ljava/lang/Class;)Lcom/jd/jdcache/service/base/AbstractDelegate;", "()Lcom/jd/jdcache/service/base/AbstractDelegate;", "delegateClass", "", "addDelegateClass", "(Ljava/lang/Class;)V", "", "TAG", "Ljava/lang/String;", "Ljava/util/HashMap;", "Lkotlin/Pair;", "Lkotlin/collections/HashMap;", "delegateMap$delegate", "Lkotlin/Lazy;", "getDelegateMap", "()Ljava/util/HashMap;", "delegateMap", "<init>", "()V", "JDCache_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes13.dex */
public final class DelegateManager {
    public static final DelegateManager INSTANCE = new DelegateManager();
    private static final String TAG = "DelegateManager";

    /* renamed from: delegateMap$delegate  reason: from kotlin metadata */
    private static final Lazy delegateMap;

    static {
        Lazy lazy;
        lazy = LazyKt__LazyJVMKt.lazy(new Function0<HashMap<String, Pair<? extends Class<? extends AbstractDelegate>, ? extends AbstractDelegate>>>() { // from class: com.jd.jdcache.service.DelegateManager$delegateMap$2
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final HashMap<String, Pair<? extends Class<? extends AbstractDelegate>, ? extends AbstractDelegate>> invoke() {
                return new HashMap<>();
            }
        });
        delegateMap = lazy;
    }

    private DelegateManager() {
    }

    private final HashMap<String, Pair<Class<? extends AbstractDelegate>, AbstractDelegate>> getDelegateMap() {
        return (HashMap) delegateMap.getValue();
    }

    public final void addDelegateClass(@NotNull Class<? extends AbstractDelegate> delegateClass) {
        if (Intrinsics.areEqual(delegateClass, AbstractDelegate.class)) {
            JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
            if (jDCacheLog.getCanLog()) {
                jDCacheLog.e(TAG, "Error in adding delegate class. Cannot add AbstractDelegate directly, you need to implement this class.");
                return;
            }
            return;
        }
        int modifiers = delegateClass.getModifiers();
        if (!Modifier.isAbstract(modifiers) && !Modifier.isInterface(modifiers)) {
            Class<? super Object> superclass = delegateClass.getSuperclass();
            if (superclass == null || !AbstractDelegate.class.isAssignableFrom(superclass)) {
                superclass = null;
            }
            Class<? super Object> cls = delegateClass;
            while (true) {
                if (superclass == null || !(!Intrinsics.areEqual(superclass, AbstractDelegate.class)) == true) {
                    break;
                }
                Class<? super Object> superclass2 = superclass.getSuperclass();
                if (superclass2 == null || !AbstractDelegate.class.isAssignableFrom(superclass2)) {
                    superclass2 = null;
                }
                Class<? super Object> cls2 = superclass2;
                cls = superclass;
                superclass = cls2;
            }
            String name = cls.getName();
            if (name != null) {
                if (!(name.length() == 0)) {
                    JDCacheLog jDCacheLog2 = JDCacheLog.INSTANCE;
                    if (jDCacheLog2.getCanLog()) {
                        jDCacheLog2.d(TAG, "Add delegate: " + name + " -> " + delegateClass.getName());
                    }
                    getDelegateMap().put(name, TuplesKt.to(delegateClass, null));
                    return;
                }
            }
            JDCacheLog jDCacheLog3 = JDCacheLog.INSTANCE;
            if (jDCacheLog3.getCanLog()) {
                jDCacheLog3.e(TAG, "Error in adding delegate class. Cannot find valid delegate type for your class: " + delegateClass);
                return;
            }
            return;
        }
        JDCacheLog jDCacheLog4 = JDCacheLog.INSTANCE;
        if (jDCacheLog4.getCanLog()) {
            jDCacheLog4.e(TAG, "Error in adding delegate class. Cannot add abstract class.");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v4, types: [com.jd.jdcache.service.base.AbstractDelegate] */
    @Nullable
    public final <T extends AbstractDelegate> T getDelegate(@NotNull Class<? extends AbstractDelegate> delegateType) {
        String name = delegateType.getName();
        if (name != null) {
            if (!(name.length() == 0)) {
                Pair<Class<? extends AbstractDelegate>, AbstractDelegate> pair = getDelegateMap().get(name);
                if (pair == null) {
                    JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
                    if (jDCacheLog.getCanLog()) {
                        jDCacheLog.e(TAG, "Error in getting delegate instance. Cannot find delegate for type " + name + ", you must register it first.");
                    }
                    return null;
                }
                Class<? extends AbstractDelegate> component1 = pair.component1();
                T t = (T) pair.component2();
                if (t == null) {
                    try {
                        t = component1.newInstance();
                    } catch (Exception e2) {
                        JDCacheLog jDCacheLog2 = JDCacheLog.INSTANCE;
                        if (jDCacheLog2.getCanLog()) {
                            jDCacheLog2.e(TAG, "Cannot create delegate's instance.", e2);
                        }
                    }
                    getDelegateMap().put(name, TuplesKt.to(component1, t));
                }
                if (t != null) {
                    return t;
                }
                throw new TypeCastException("null cannot be cast to non-null type T");
            }
        }
        JDCacheLog jDCacheLog3 = JDCacheLog.INSTANCE;
        if (jDCacheLog3.getCanLog()) {
            jDCacheLog3.e(TAG, "Error in getting delegate instance. Delegate type (" + delegateType + ") is illegal.");
        }
        return null;
    }

    @Nullable
    public final /* synthetic */ <T extends AbstractDelegate> T getDelegate() {
        Intrinsics.reifiedOperationMarker(4, "T");
        return (T) getDelegate(AbstractDelegate.class);
    }
}
