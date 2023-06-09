package kotlin.random;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0007\u0010\bR\u001c\u0010\u0003\u001a\u00020\u00028\u0016@\u0016X\u0096\u0004\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\t"}, d2 = {"Lkotlin/random/PlatformRandom;", "Lkotlin/random/AbstractPlatformRandom;", "Ljava/util/Random;", "impl", "Ljava/util/Random;", "getImpl", "()Ljava/util/Random;", "<init>", "(Ljava/util/Random;)V", "kotlin-stdlib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes11.dex */
final class PlatformRandom extends AbstractPlatformRandom {
    @NotNull
    private final java.util.Random impl;

    public PlatformRandom(@NotNull java.util.Random random) {
        this.impl = random;
    }

    @Override // kotlin.random.AbstractPlatformRandom
    @NotNull
    public java.util.Random getImpl() {
        return this.impl;
    }
}
