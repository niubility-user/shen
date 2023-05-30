package kotlinx.coroutines.internal;

import com.jingdong.common.jdreactFramework.JDReactConstant;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import jpbury.t;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CopyableThrowable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a#\u0010\u0003\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\u0001*\u00020\u00002\u0006\u0010\u0002\u001a\u00028\u0000H\u0000\u00a2\u0006\u0004\b\u0003\u0010\u0004\u001a1\u0010\t\u001a\u0018\u0012\u0004\u0012\u00020\u0000\u0012\u0006\u0012\u0004\u0018\u00010\u0000\u0018\u00010\u0007j\u0004\u0018\u0001`\b2\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0002\u00a2\u0006\u0004\b\t\u0010\n\u001a8\u0010\f\u001a\u0014\u0012\u0004\u0012\u00020\u0000\u0012\u0006\u0012\u0004\u0018\u00010\u00000\u0007j\u0002`\b2\u0014\b\u0004\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00000\u0007H\u0082\b\u00a2\u0006\u0004\b\f\u0010\r\u001a\u001f\u0010\u0011\u001a\u00020\u000f*\u0006\u0012\u0002\b\u00030\u000e2\u0006\u0010\u0010\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0012\u001a\"\u0010\u0014\u001a\u00020\u000f*\u0006\u0012\u0002\b\u00030\u000e2\b\b\u0002\u0010\u0013\u001a\u00020\u000fH\u0082\u0010\u00a2\u0006\u0004\b\u0014\u0010\u0012\"\u0016\u0010\u0015\u001a\u00020\u000f8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0016\"\u0016\u0010\u0018\u001a\u00020\u00178\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0019\"<\u0010\u001b\u001a(\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00000\u000e\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0000\u0012\u0006\u0012\u0004\u0018\u00010\u00000\u0007j\u0002`\b0\u001a8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u001c*(\b\u0002\u0010\u001d\"\u0010\u0012\u0004\u0012\u00020\u0000\u0012\u0006\u0012\u0004\u0018\u00010\u00000\u00072\u0010\u0012\u0004\u0012\u00020\u0000\u0012\u0006\u0012\u0004\u0018\u00010\u00000\u0007\u00a8\u0006\u001e"}, d2 = {"", "E", t.f20145j, "tryCopyException", "(Ljava/lang/Throwable;)Ljava/lang/Throwable;", "Ljava/lang/reflect/Constructor;", "constructor", "Lkotlin/Function1;", "Lkotlinx/coroutines/internal/Ctor;", "createConstructor", "(Ljava/lang/reflect/Constructor;)Lkotlin/jvm/functions/Function1;", JDReactConstant.BLOCK, "safeCtor", "(Lkotlin/jvm/functions/Function1;)Lkotlin/jvm/functions/Function1;", "Ljava/lang/Class;", "", "defaultValue", "fieldsCountOrDefault", "(Ljava/lang/Class;I)I", "accumulator", "fieldsCount", "throwableFields", "I", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "cacheLock", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "Ljava/util/WeakHashMap;", "exceptionCtors", "Ljava/util/WeakHashMap;", "Ctor", "kotlinx-coroutines-core"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class ExceptionsConstuctorKt {
    private static final int throwableFields = fieldsCountOrDefault(Throwable.class, -1);
    private static final ReentrantReadWriteLock cacheLock = new ReentrantReadWriteLock();
    private static final WeakHashMap<Class<? extends Throwable>, Function1<Throwable, Throwable>> exceptionCtors = new WeakHashMap<>();

    private static final Function1<Throwable, Throwable> createConstructor(final Constructor<?> constructor) {
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        int length = parameterTypes.length;
        if (length != 0) {
            if (length != 1) {
                if (length == 2 && Intrinsics.areEqual(parameterTypes[0], String.class) && Intrinsics.areEqual(parameterTypes[1], Throwable.class)) {
                    return new Function1<Throwable, Throwable>() { // from class: kotlinx.coroutines.internal.ExceptionsConstuctorKt$createConstructor$$inlined$safeCtor$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        @Nullable
                        public final Throwable invoke(@NotNull Throwable th) {
                            Object m200constructorimpl;
                            Object newInstance;
                            try {
                                Result.Companion companion = Result.INSTANCE;
                                newInstance = constructor.newInstance(th.getMessage(), th);
                            } catch (Throwable th2) {
                                Result.Companion companion2 = Result.INSTANCE;
                                m200constructorimpl = Result.m200constructorimpl(ResultKt.createFailure(th2));
                            }
                            if (newInstance != null) {
                                m200constructorimpl = Result.m200constructorimpl((Throwable) newInstance);
                                if (Result.m206isFailureimpl(m200constructorimpl)) {
                                    m200constructorimpl = null;
                                }
                                return (Throwable) m200constructorimpl;
                            }
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.Throwable");
                        }
                    };
                }
                return null;
            }
            Class<?> cls = parameterTypes[0];
            if (Intrinsics.areEqual(cls, Throwable.class)) {
                return new Function1<Throwable, Throwable>() { // from class: kotlinx.coroutines.internal.ExceptionsConstuctorKt$createConstructor$$inlined$safeCtor$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    @Nullable
                    public final Throwable invoke(@NotNull Throwable th) {
                        Object m200constructorimpl;
                        Object newInstance;
                        try {
                            Result.Companion companion = Result.INSTANCE;
                            newInstance = constructor.newInstance(th);
                        } catch (Throwable th2) {
                            Result.Companion companion2 = Result.INSTANCE;
                            m200constructorimpl = Result.m200constructorimpl(ResultKt.createFailure(th2));
                        }
                        if (newInstance != null) {
                            m200constructorimpl = Result.m200constructorimpl((Throwable) newInstance);
                            if (Result.m206isFailureimpl(m200constructorimpl)) {
                                m200constructorimpl = null;
                            }
                            return (Throwable) m200constructorimpl;
                        }
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.Throwable");
                    }
                };
            }
            if (Intrinsics.areEqual(cls, String.class)) {
                return new Function1<Throwable, Throwable>() { // from class: kotlinx.coroutines.internal.ExceptionsConstuctorKt$createConstructor$$inlined$safeCtor$3
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    @Nullable
                    public final Throwable invoke(@NotNull Throwable th) {
                        Object m200constructorimpl;
                        Object newInstance;
                        try {
                            Result.Companion companion = Result.INSTANCE;
                            newInstance = constructor.newInstance(th.getMessage());
                        } catch (Throwable th2) {
                            Result.Companion companion2 = Result.INSTANCE;
                            m200constructorimpl = Result.m200constructorimpl(ResultKt.createFailure(th2));
                        }
                        if (newInstance != null) {
                            Throwable th3 = (Throwable) newInstance;
                            th3.initCause(th);
                            m200constructorimpl = Result.m200constructorimpl(th3);
                            if (Result.m206isFailureimpl(m200constructorimpl)) {
                                m200constructorimpl = null;
                            }
                            return (Throwable) m200constructorimpl;
                        }
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.Throwable");
                    }
                };
            }
            return null;
        }
        return new Function1<Throwable, Throwable>() { // from class: kotlinx.coroutines.internal.ExceptionsConstuctorKt$createConstructor$$inlined$safeCtor$4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            @Nullable
            public final Throwable invoke(@NotNull Throwable th) {
                Object m200constructorimpl;
                Object newInstance;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    newInstance = constructor.newInstance(new Object[0]);
                } catch (Throwable th2) {
                    Result.Companion companion2 = Result.INSTANCE;
                    m200constructorimpl = Result.m200constructorimpl(ResultKt.createFailure(th2));
                }
                if (newInstance != null) {
                    Throwable th3 = (Throwable) newInstance;
                    th3.initCause(th);
                    m200constructorimpl = Result.m200constructorimpl(th3);
                    if (Result.m206isFailureimpl(m200constructorimpl)) {
                        m200constructorimpl = null;
                    }
                    return (Throwable) m200constructorimpl;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Throwable");
            }
        };
    }

    private static final int fieldsCount(@NotNull Class<?> cls, int i2) {
        do {
            int length = cls.getDeclaredFields().length;
            int i3 = 0;
            for (int i4 = 0; i4 < length; i4++) {
                if ((!Modifier.isStatic(r0[i4].getModifiers())) != false) {
                    i3++;
                }
            }
            i2 += i3;
            cls = cls.getSuperclass();
        } while (cls != null);
        return i2;
    }

    static /* synthetic */ int fieldsCount$default(Class cls, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i2 = 0;
        }
        return fieldsCount(cls, i2);
    }

    private static final int fieldsCountOrDefault(@NotNull Class<?> cls, int i2) {
        Integer m200constructorimpl;
        JvmClassMappingKt.getKotlinClass(cls);
        try {
            Result.Companion companion = Result.INSTANCE;
            m200constructorimpl = Result.m200constructorimpl(Integer.valueOf(fieldsCount$default(cls, 0, 1, null)));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m200constructorimpl = Result.m200constructorimpl(ResultKt.createFailure(th));
        }
        Integer valueOf = Integer.valueOf(i2);
        if (Result.m206isFailureimpl(m200constructorimpl)) {
            m200constructorimpl = valueOf;
        }
        return ((Number) m200constructorimpl).intValue();
    }

    private static final Function1<Throwable, Throwable> safeCtor(final Function1<? super Throwable, ? extends Throwable> function1) {
        return new Function1<Throwable, Throwable>() { // from class: kotlinx.coroutines.internal.ExceptionsConstuctorKt$safeCtor$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            @Nullable
            public final Throwable invoke(@NotNull Throwable th) {
                Object m200constructorimpl;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    m200constructorimpl = Result.m200constructorimpl((Throwable) Function1.this.invoke(th));
                } catch (Throwable th2) {
                    Result.Companion companion2 = Result.INSTANCE;
                    m200constructorimpl = Result.m200constructorimpl(ResultKt.createFailure(th2));
                }
                if (Result.m206isFailureimpl(m200constructorimpl)) {
                    m200constructorimpl = null;
                }
                return (Throwable) m200constructorimpl;
            }
        };
    }

    @Nullable
    public static final <E extends Throwable> E tryCopyException(@NotNull E e2) {
        Object m200constructorimpl;
        List sortedWith;
        ReentrantReadWriteLock.ReadLock readLock;
        int readHoldCount;
        ReentrantReadWriteLock.WriteLock writeLock;
        if (e2 instanceof CopyableThrowable) {
            try {
                Result.Companion companion = Result.INSTANCE;
                m200constructorimpl = Result.m200constructorimpl(((CopyableThrowable) e2).createCopy());
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                m200constructorimpl = Result.m200constructorimpl(ResultKt.createFailure(th));
            }
            return (E) (Result.m206isFailureimpl(m200constructorimpl) ? null : m200constructorimpl);
        }
        ReentrantReadWriteLock reentrantReadWriteLock = cacheLock;
        ReentrantReadWriteLock.ReadLock readLock2 = reentrantReadWriteLock.readLock();
        readLock2.lock();
        try {
            Function1<Throwable, Throwable> function1 = exceptionCtors.get(e2.getClass());
            if (function1 != null) {
                return (E) function1.invoke(e2);
            }
            int i2 = 0;
            if (throwableFields != fieldsCountOrDefault(e2.getClass(), 0)) {
                readLock = reentrantReadWriteLock.readLock();
                readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
                for (int i3 = 0; i3 < readHoldCount; i3++) {
                    readLock.unlock();
                }
                writeLock = reentrantReadWriteLock.writeLock();
                writeLock.lock();
                try {
                    exceptionCtors.put(e2.getClass(), new Function1() { // from class: kotlinx.coroutines.internal.ExceptionsConstuctorKt$tryCopyException$4$1
                        @Override // kotlin.jvm.functions.Function1
                        @Nullable
                        public final Void invoke(@NotNull Throwable th2) {
                            return null;
                        }
                    });
                    Unit unit = Unit.INSTANCE;
                    return null;
                } finally {
                    while (i2 < readHoldCount) {
                        readLock.lock();
                        i2++;
                    }
                    writeLock.unlock();
                }
            }
            sortedWith = ArraysKt___ArraysKt.sortedWith(e2.getClass().getConstructors(), new Comparator<T>() { // from class: kotlinx.coroutines.internal.ExceptionsConstuctorKt$tryCopyException$$inlined$sortedByDescending$1
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    int compareValues;
                    compareValues = ComparisonsKt__ComparisonsKt.compareValues(Integer.valueOf(((Constructor) t2).getParameterTypes().length), Integer.valueOf(((Constructor) t).getParameterTypes().length));
                    return compareValues;
                }
            });
            Iterator it = sortedWith.iterator();
            Function1<Throwable, Throwable> function12 = null;
            while (it.hasNext() && (function12 = createConstructor((Constructor) it.next())) == null) {
            }
            ReentrantReadWriteLock reentrantReadWriteLock2 = cacheLock;
            readLock = reentrantReadWriteLock2.readLock();
            readHoldCount = reentrantReadWriteLock2.getWriteHoldCount() == 0 ? reentrantReadWriteLock2.getReadHoldCount() : 0;
            for (int i4 = 0; i4 < readHoldCount; i4++) {
                readLock.unlock();
            }
            writeLock = reentrantReadWriteLock2.writeLock();
            writeLock.lock();
            try {
                exceptionCtors.put(e2.getClass(), function12 != null ? function12 : new Function1() { // from class: kotlinx.coroutines.internal.ExceptionsConstuctorKt$tryCopyException$5$1
                    @Override // kotlin.jvm.functions.Function1
                    @Nullable
                    public final Void invoke(@NotNull Throwable th2) {
                        return null;
                    }
                });
                Unit unit2 = Unit.INSTANCE;
                while (i2 < readHoldCount) {
                    readLock.lock();
                    i2++;
                }
                writeLock.unlock();
                if (function12 != null) {
                    return (E) function12.invoke(e2);
                }
                return null;
            } finally {
                while (i2 < readHoldCount) {
                    readLock.lock();
                    i2++;
                }
                writeLock.unlock();
            }
        } finally {
            readLock2.unlock();
        }
    }
}
