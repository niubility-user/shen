package kotlin.coroutines.jvm.internal;

import com.jingdong.common.entity.personal.PersonalConstants;
import com.meizu.cloud.pushsdk.notification.model.NotifyType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.annotation.AnnotationTarget;
import kotlin.jvm.JvmName;

@Target({ElementType.TYPE})
@SinceKotlin(version = "1.3")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0006\b\u0081\u0002\u0018\u00002\u00020\u0001Bc\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0016\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0007\u0012\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0007\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0003\u00a2\u0006\u0004\b\u001a\u0010\u001bR\u0019\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0007@\u0006\u00a2\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0013\u0010\n\u001a\u00020\u00078\u0007@\u0006\u00a2\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0013\u0010\r\u001a\u00020\u00038\u0007@\u0006\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0007@\u0006\u00a2\u0006\u0006\u001a\u0004\b\u000e\u0010\u0005R\u0013\u0010\u0011\u001a\u00020\u00078\u0007@\u0006\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\tR\u0013\u0010\u0013\u001a\u00020\u00038\u0007@\u0006\u00a2\u0006\u0006\u001a\u0004\b\u0012\u0010\fR\u0013\u0010\u0015\u001a\u00020\u00038\u0007@\u0006\u00a2\u0006\u0006\u001a\u0004\b\u0014\u0010\fR\u0013\u0010\u0019\u001a\u00020\u00168\u0007@\u0006\u00a2\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018\u00a8\u0006\u001c"}, d2 = {"Lkotlin/coroutines/jvm/internal/DebugMetadata;", "", "", "", "s", "()[Ljava/lang/String;", "spilled", "", NotifyType.LIGHTS, "()[I", "lineNumbers", "m", "()Ljava/lang/String;", "methodName", PersonalConstants.ICON_STYLE_N, "localNames", "i", "indexToLabel", "c", "className", "f", "sourceFile", "", "v", "()I", "version", "<init>", "(ILjava/lang/String;[ILkotlin/Array;Lkotlin/Array;[ILjava/lang/String;Ljava/lang/String;)V", "kotlin-stdlib"}, k = 1, mv = {1, 4, 0})
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.CLASS})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes.dex */
public @interface DebugMetadata {
    @JvmName(name = "c")
    String c() default "";

    @JvmName(name = "f")
    String f() default "";

    @JvmName(name = "i")
    int[] i() default {};

    @JvmName(name = NotifyType.LIGHTS)
    int[] l() default {};

    @JvmName(name = "m")
    String m() default "";

    @JvmName(name = PersonalConstants.ICON_STYLE_N)
    String[] n() default {};

    @JvmName(name = "s")
    String[] s() default {};

    @JvmName(name = "v")
    int v() default 1;
}
