package kotlin.reflect;

import com.facebook.imagepipeline.producers.DecodeProducer;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u00032\u00020\u00042\u00020\u0005J\u0019\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001H'\u00a2\u0006\u0004\b\b\u0010\tJ\u001a\u0010\u000b\u001a\u00020\u00072\b\u0010\n\u001a\u0004\u0018\u00010\u0001H\u00a6\u0002\u00a2\u0006\u0004\b\u000b\u0010\tJ\u000f\u0010\r\u001a\u00020\fH&\u00a2\u0006\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u00020\u00078&@'X\u00a7\u0004\u00a2\u0006\f\u0012\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0013\u001a\u00020\u00078&@'X\u00a7\u0004\u00a2\u0006\f\u0012\u0004\b\u0014\u0010\u0012\u001a\u0004\b\u0013\u0010\u0010R\u001c\u0010\u0015\u001a\u00020\u00078&@'X\u00a7\u0004\u00a2\u0006\f\u0012\u0004\b\u0016\u0010\u0012\u001a\u0004\b\u0015\u0010\u0010R\"\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00180\u00178&@&X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR*\u0010 \u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u00000\u001c8&@'X\u00a7\u0004\u00a2\u0006\f\u0012\u0004\b\u001f\u0010\u0012\u001a\u0004\b\u001d\u0010\u001eR\"\u0010$\u001a\b\u0012\u0004\u0012\u00020!0\u001c8&@'X\u00a7\u0004\u00a2\u0006\f\u0012\u0004\b#\u0010\u0012\u001a\u0004\b\"\u0010\u001eR\u001c\u0010%\u001a\u00020\u00078&@'X\u00a7\u0004\u00a2\u0006\f\u0012\u0004\b&\u0010\u0012\u001a\u0004\b%\u0010\u0010R \u0010(\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00000\u00178&@&X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b'\u0010\u001aR\u0018\u0010+\u001a\u0004\u0018\u00018\u00008&@&X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b)\u0010*R\u001c\u0010,\u001a\u00020\u00078&@'X\u00a7\u0004\u00a2\u0006\f\u0012\u0004\b-\u0010\u0012\u001a\u0004\b,\u0010\u0010R\u001c\u0010.\u001a\u00020\u00078&@'X\u00a7\u0004\u00a2\u0006\f\u0012\u0004\b/\u0010\u0012\u001a\u0004\b.\u0010\u0010R\u001e\u00104\u001a\u0004\u0018\u0001008&@'X\u00a7\u0004\u00a2\u0006\f\u0012\u0004\b3\u0010\u0012\u001a\u0004\b1\u00102R\"\u00108\u001a\b\u0012\u0004\u0012\u0002050\u001c8&@'X\u00a7\u0004\u00a2\u0006\f\u0012\u0004\b7\u0010\u0012\u001a\u0004\b6\u0010\u001eR\u0018\u0010<\u001a\u0004\u0018\u0001098&@&X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b:\u0010;R\u001c\u0010=\u001a\u00020\u00078&@'X\u00a7\u0004\u00a2\u0006\f\u0012\u0004\b>\u0010\u0012\u001a\u0004\b=\u0010\u0010R\u0018\u0010@\u001a\u0004\u0018\u0001098&@&X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b?\u0010;R \u0010C\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030A0\u00178&@&X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\bB\u0010\u001a\u00a8\u0006D"}, d2 = {"Lkotlin/reflect/KClass;", "", "T", "Lkotlin/reflect/KDeclarationContainer;", "Lkotlin/reflect/KAnnotatedElement;", "Lkotlin/reflect/KClassifier;", "value", "", "isInstance", "(Ljava/lang/Object;)Z", "other", "equals", "", "hashCode", "()I", "isInner", "()Z", "isInner$annotations", "()V", "isData", "isData$annotations", "isAbstract", "isAbstract$annotations", "", "Lkotlin/reflect/KFunction;", "getConstructors", "()Ljava/util/Collection;", "constructors", "", "getSealedSubclasses", "()Ljava/util/List;", "sealedSubclasses$annotations", "sealedSubclasses", "Lkotlin/reflect/KType;", "getSupertypes", "supertypes$annotations", "supertypes", "isOpen", "isOpen$annotations", "getNestedClasses", "nestedClasses", "getObjectInstance", "()Ljava/lang/Object;", "objectInstance", DecodeProducer.EXTRA_IS_FINAL, "isFinal$annotations", "isCompanion", "isCompanion$annotations", "Lkotlin/reflect/KVisibility;", "getVisibility", "()Lkotlin/reflect/KVisibility;", "visibility$annotations", "visibility", "Lkotlin/reflect/KTypeParameter;", "getTypeParameters", "typeParameters$annotations", "typeParameters", "", "getSimpleName", "()Ljava/lang/String;", "simpleName", "isSealed", "isSealed$annotations", "getQualifiedName", "qualifiedName", "Lkotlin/reflect/KCallable;", "getMembers", "members", "kotlin-stdlib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public interface KClass<T> extends KDeclarationContainer, KAnnotatedElement, KClassifier {

    @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes11.dex */
    public static final class DefaultImpls {
        @SinceKotlin(version = "1.1")
        public static /* synthetic */ void isAbstract$annotations() {
        }

        @SinceKotlin(version = "1.1")
        public static /* synthetic */ void isCompanion$annotations() {
        }

        @SinceKotlin(version = "1.1")
        public static /* synthetic */ void isData$annotations() {
        }

        @SinceKotlin(version = "1.1")
        public static /* synthetic */ void isFinal$annotations() {
        }

        @SinceKotlin(version = "1.1")
        public static /* synthetic */ void isInner$annotations() {
        }

        @SinceKotlin(version = "1.1")
        public static /* synthetic */ void isOpen$annotations() {
        }

        @SinceKotlin(version = "1.1")
        public static /* synthetic */ void isSealed$annotations() {
        }

        @SinceKotlin(version = "1.3")
        public static /* synthetic */ void sealedSubclasses$annotations() {
        }

        @SinceKotlin(version = "1.1")
        public static /* synthetic */ void supertypes$annotations() {
        }

        @SinceKotlin(version = "1.1")
        public static /* synthetic */ void typeParameters$annotations() {
        }

        @SinceKotlin(version = "1.1")
        public static /* synthetic */ void visibility$annotations() {
        }
    }

    boolean equals(@Nullable Object other);

    @NotNull
    Collection<KFunction<T>> getConstructors();

    @Override // kotlin.reflect.KDeclarationContainer
    @NotNull
    Collection<KCallable<?>> getMembers();

    @NotNull
    Collection<KClass<?>> getNestedClasses();

    @Nullable
    T getObjectInstance();

    @Nullable
    String getQualifiedName();

    @NotNull
    List<KClass<? extends T>> getSealedSubclasses();

    @Nullable
    String getSimpleName();

    @NotNull
    List<KType> getSupertypes();

    @NotNull
    List<KTypeParameter> getTypeParameters();

    @Nullable
    KVisibility getVisibility();

    int hashCode();

    boolean isAbstract();

    boolean isCompanion();

    boolean isData();

    boolean isFinal();

    boolean isInner();

    @SinceKotlin(version = "1.1")
    boolean isInstance(@Nullable Object value);

    boolean isOpen();

    boolean isSealed();
}
