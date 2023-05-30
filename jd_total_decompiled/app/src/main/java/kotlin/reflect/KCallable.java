package kotlin.reflect;

import androidx.core.app.NotificationCompat;
import com.facebook.imagepipeline.producers.DecodeProducer;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\bf\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002J'\u0010\u0006\u001a\u00028\u00002\u0016\u0010\u0005\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00040\u0003\"\u0004\u0018\u00010\u0004H&\u00a2\u0006\u0004\b\u0006\u0010\u0007J%\u0010\n\u001a\u00028\u00002\u0014\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0006\u0012\u0004\u0018\u00010\u00040\bH&\u00a2\u0006\u0004\b\n\u0010\u000bR\u001c\u0010\r\u001a\u00020\f8&@'X\u00a7\u0004\u00a2\u0006\f\u0012\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000eR\u001c\u0010\u0011\u001a\u00020\f8&@'X\u00a7\u0004\u00a2\u0006\f\u0012\u0004\b\u0012\u0010\u0010\u001a\u0004\b\u0011\u0010\u000eR\u001c\u0010\u0013\u001a\u00020\f8&@'X\u00a7\u0004\u00a2\u0006\f\u0012\u0004\b\u0014\u0010\u0010\u001a\u0004\b\u0013\u0010\u000eR\u001e\u0010\u0019\u001a\u0004\u0018\u00010\u00158&@'X\u00a7\u0004\u00a2\u0006\f\u0012\u0004\b\u0018\u0010\u0010\u001a\u0004\b\u0016\u0010\u0017R\u001c\u0010\u001a\u001a\u00020\f8&@'X\u00a7\u0004\u00a2\u0006\f\u0012\u0004\b\u001b\u0010\u0010\u001a\u0004\b\u001a\u0010\u000eR\u001c\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\t0\u001c8&@&X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u0016\u0010#\u001a\u00020 8&@&X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b!\u0010\"R\"\u0010'\u001a\b\u0012\u0004\u0012\u00020$0\u001c8&@'X\u00a7\u0004\u00a2\u0006\f\u0012\u0004\b&\u0010\u0010\u001a\u0004\b%\u0010\u001eR\u0016\u0010+\u001a\u00020(8&@&X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b)\u0010*\u00a8\u0006,"}, d2 = {"Lkotlin/reflect/KCallable;", "R", "Lkotlin/reflect/KAnnotatedElement;", "", "", "args", NotificationCompat.CATEGORY_CALL, "([Ljava/lang/Object;)Ljava/lang/Object;", "", "Lkotlin/reflect/KParameter;", "callBy", "(Ljava/util/Map;)Ljava/lang/Object;", "", "isOpen", "()Z", "isOpen$annotations", "()V", "isAbstract", "isAbstract$annotations", DecodeProducer.EXTRA_IS_FINAL, "isFinal$annotations", "Lkotlin/reflect/KVisibility;", "getVisibility", "()Lkotlin/reflect/KVisibility;", "visibility$annotations", "visibility", "isSuspend", "isSuspend$annotations", "", "getParameters", "()Ljava/util/List;", PushConstants.PARAMS, "Lkotlin/reflect/KType;", "getReturnType", "()Lkotlin/reflect/KType;", "returnType", "Lkotlin/reflect/KTypeParameter;", "getTypeParameters", "typeParameters$annotations", "typeParameters", "", "getName", "()Ljava/lang/String;", "name", "kotlin-stdlib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public interface KCallable<R> extends KAnnotatedElement {

    @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes11.dex */
    public static final class DefaultImpls {
        @SinceKotlin(version = "1.1")
        public static /* synthetic */ void isAbstract$annotations() {
        }

        @SinceKotlin(version = "1.1")
        public static /* synthetic */ void isFinal$annotations() {
        }

        @SinceKotlin(version = "1.1")
        public static /* synthetic */ void isOpen$annotations() {
        }

        @SinceKotlin(version = "1.3")
        public static /* synthetic */ void isSuspend$annotations() {
        }

        @SinceKotlin(version = "1.1")
        public static /* synthetic */ void typeParameters$annotations() {
        }

        @SinceKotlin(version = "1.1")
        public static /* synthetic */ void visibility$annotations() {
        }
    }

    R call(@NotNull Object... objArr);

    R callBy(@NotNull Map<KParameter, ? extends Object> args);

    @NotNull
    String getName();

    @NotNull
    List<KParameter> getParameters();

    @NotNull
    KType getReturnType();

    @NotNull
    List<KTypeParameter> getTypeParameters();

    @Nullable
    KVisibility getVisibility();

    boolean isAbstract();

    boolean isFinal();

    boolean isOpen();

    boolean isSuspend();
}
