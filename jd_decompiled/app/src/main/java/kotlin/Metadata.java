package kotlin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;
import kotlin.jvm.JvmName;

@Target({ElementType.TYPE})
@SinceKotlin(version = "1.3")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\n\b\u0087\u0002\u0018\u00002\u00020\u0001Bc\u0012\b\b\u0002\u0010\u0019\u001a\u00020\f\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0012\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u000f\u001a\u00020\f\u00a2\u0006\u0004\b\u001a\u0010\u001bR\u0013\u0010\u0005\u001a\u00020\u00028\u0007@\u0006\u00a2\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004R\u0019\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\u00068\u0007@\u0006\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0019\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\u00068\u0007@\u0006\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\bR\u0016\u0010\u000f\u001a\u00020\f8\u0007@\u0007X\u0087\u0004\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u0011\u001a\u00020\u00028\u0007@\u0007X\u0087\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\u0004R\u0013\u0010\u0015\u001a\u00020\u00128\u0007@\u0006\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\u0017\u001a\u00020\u00128\u0007@\u0006\u00a2\u0006\u0006\u001a\u0004\b\u0016\u0010\u0014R\u0013\u0010\u0019\u001a\u00020\f8\u0007@\u0006\u00a2\u0006\u0006\u001a\u0004\b\u0018\u0010\u000e\u00a8\u0006\u001c"}, d2 = {"Lkotlin/Metadata;", "", "", "xs", "()Ljava/lang/String;", "extraString", "", "d1", "()[Ljava/lang/String;", "data1", "d2", "data2", "", "xi", "()I", "extraInt", "pn", "packageName", "", "mv", "()[I", "metadataVersion", "bv", "bytecodeVersion", "k", "kind", "<init>", "(I[I[ILkotlin/Array;Lkotlin/Array;Ljava/lang/String;Ljava/lang/String;I)V", "kotlin-stdlib"}, k = 1, mv = {1, 4, 0})
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.CLASS})
@Retention(RetentionPolicy.RUNTIME)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
/* loaded from: classes.dex */
public @interface Metadata {
    @JvmName(name = "bv")
    int[] bv() default {};

    @JvmName(name = "d1")
    String[] d1() default {};

    @JvmName(name = "d2")
    String[] d2() default {};

    @JvmName(name = "k")
    int k() default 1;

    @JvmName(name = "mv")
    int[] mv() default {};

    @JvmName(name = "pn")
    String pn() default "";

    @JvmName(name = "xi")
    int xi() default 0;

    @JvmName(name = "xs")
    String xs() default "";
}
