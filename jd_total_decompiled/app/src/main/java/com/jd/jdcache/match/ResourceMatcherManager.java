package com.jd.jdcache.match;

import com.jd.jdcache.match.base.JDCacheResourceMatcher;
import com.jd.jdcache.util.JDCacheLog;
import java.lang.reflect.Modifier;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u00c0\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u001a\u0010\nJ\u001d\u0010\u0006\u001a\u00020\u00052\u000e\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u001d\u0010\b\u001a\u00020\u00052\u000e\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0002\u00a2\u0006\u0004\b\b\u0010\u0007J\r\u0010\t\u001a\u00020\u0005\u00a2\u0006\u0004\b\t\u0010\nJ\u001f\u0010\f\u001a\u0004\u0018\u00010\u00032\u000e\u0010\u000b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0002\u00a2\u0006\u0004\b\f\u0010\rJ\u0013\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\u000e\u00a2\u0006\u0004\b\u000f\u0010\u0010R+\u0010\u0016\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u00020\u00118B@\u0002X\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0016\u0010\u0018\u001a\u00020\u00178\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0019\u00a8\u0006\u001b"}, d2 = {"Lcom/jd/jdcache/match/ResourceMatcherManager;", "", "Ljava/lang/Class;", "Lcom/jd/jdcache/match/base/JDCacheResourceMatcher;", "matcherClass", "", "registerMatcher", "(Ljava/lang/Class;)V", "unregisterMatcher", "clearMatcher", "()V", "clazz", "createMatcher", "(Ljava/lang/Class;)Lcom/jd/jdcache/match/base/JDCacheResourceMatcher;", "Ljava/util/LinkedList;", "createDefaultMatcherList", "()Ljava/util/LinkedList;", "", "defaultMatcherClassList$delegate", "Lkotlin/Lazy;", "getDefaultMatcherClassList", "()Ljava/util/List;", "defaultMatcherClassList", "", "TAG", "Ljava/lang/String;", "<init>", "JDCache_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes13.dex */
public final class ResourceMatcherManager {
    public static final ResourceMatcherManager INSTANCE = new ResourceMatcherManager();
    private static final String TAG = "ResourceMatcherManager";

    /* renamed from: defaultMatcherClassList$delegate  reason: from kotlin metadata */
    private static final Lazy defaultMatcherClassList;

    static {
        Lazy lazy;
        lazy = LazyKt__LazyJVMKt.lazy(new Function0<LinkedList<Class<? extends JDCacheResourceMatcher>>>() { // from class: com.jd.jdcache.match.ResourceMatcherManager$defaultMatcherClassList$2
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final LinkedList<Class<? extends JDCacheResourceMatcher>> invoke() {
                return new LinkedList<>();
            }
        });
        defaultMatcherClassList = lazy;
    }

    private ResourceMatcherManager() {
    }

    private final List<Class<? extends JDCacheResourceMatcher>> getDefaultMatcherClassList() {
        return (List) defaultMatcherClassList.getValue();
    }

    public final void clearMatcher() {
        getDefaultMatcherClassList().clear();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public final LinkedList<JDCacheResourceMatcher> createDefaultMatcherList() {
        LinkedList<JDCacheResourceMatcher> linkedList = new LinkedList<>();
        Iterator<T> it = getDefaultMatcherClassList().iterator();
        while (it.hasNext()) {
            Class cls = (Class) it.next();
            try {
                linkedList.add(cls.newInstance());
            } catch (Throwable th) {
                JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
                if (jDCacheLog.getCanLog()) {
                    jDCacheLog.e(TAG, "Error in creating matcher instance for " + cls.getSimpleName(), th);
                }
            }
        }
        return linkedList;
    }

    @Nullable
    public final JDCacheResourceMatcher createMatcher(@NotNull Class<? extends JDCacheResourceMatcher> clazz) {
        try {
            return clazz.newInstance();
        } catch (Throwable th) {
            JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
            if (jDCacheLog.getCanLog()) {
                jDCacheLog.e(TAG, "Error in creating matcher instance for " + clazz.getSimpleName(), th);
            }
            return null;
        }
    }

    public final void registerMatcher(@NotNull Class<? extends JDCacheResourceMatcher> matcherClass) {
        int modifiers = matcherClass.getModifiers();
        if (!Modifier.isAbstract(modifiers) && !Modifier.isInterface(modifiers)) {
            if (Intrinsics.areEqual(matcherClass, Reflection.getOrCreateKotlinClass(JDCacheResourceMatcher.class))) {
                JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
                if (jDCacheLog.getCanLog()) {
                    jDCacheLog.e(TAG, "Error in adding register matcher class. Cannot add JDCacheResourceMatcher directly, you need to implement this class.");
                    return;
                }
                return;
            }
            getDefaultMatcherClassList().add(matcherClass);
            return;
        }
        JDCacheLog jDCacheLog2 = JDCacheLog.INSTANCE;
        if (jDCacheLog2.getCanLog()) {
            jDCacheLog2.e(TAG, "Error in adding register matcher class. Cannot register abstract class.");
        }
    }

    public final void unregisterMatcher(@NotNull Class<? extends JDCacheResourceMatcher> matcherClass) {
        getDefaultMatcherClassList().remove(matcherClass);
    }
}
