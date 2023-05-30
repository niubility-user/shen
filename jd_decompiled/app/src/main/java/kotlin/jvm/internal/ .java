package kotlin.jvm.internal;

import com.facebook.imagepipeline.producers.DecodeProducer;
import com.jd.dynamic.DYConstants;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Function;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.SinceKotlin;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.KotlinReflectionNotSupportedError;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function10;
import kotlin.jvm.functions.Function11;
import kotlin.jvm.functions.Function12;
import kotlin.jvm.functions.Function13;
import kotlin.jvm.functions.Function14;
import kotlin.jvm.functions.Function15;
import kotlin.jvm.functions.Function16;
import kotlin.jvm.functions.Function17;
import kotlin.jvm.functions.Function18;
import kotlin.jvm.functions.Function19;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function20;
import kotlin.jvm.functions.Function21;
import kotlin.jvm.functions.Function22;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.functions.Function8;
import kotlin.jvm.functions.Function9;
import kotlin.reflect.KCallable;
import kotlin.reflect.KClass;
import kotlin.reflect.KFunction;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeParameter;
import kotlin.reflect.KVisibility;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u001b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 P2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001PB\u0013\u0012\n\u0010\"\u001a\u0006\u0012\u0002\b\u00030!\u00a2\u0006\u0004\bN\u0010OJ\u000f\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0019\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0002H\u0017\u00a2\u0006\u0004\b\t\u0010\nJ\u001a\u0010\f\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0002H\u0096\u0002\u00a2\u0006\u0004\b\f\u0010\nJ\u000f\u0010\u000e\u001a\u00020\rH\u0016\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\u0010H\u0016\u00a2\u0006\u0004\b\u0011\u0010\u0012R\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00108V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\u0012R\u0018\u0010\u0016\u001a\u0004\u0018\u00010\u00108V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0015\u0010\u0012R\u001c\u0010\u0017\u001a\u00020\b8V@\u0017X\u0097\u0004\u00a2\u0006\f\u0012\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0017\u0010\u0018R\"\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b8V@\u0017X\u0097\u0004\u00a2\u0006\f\u0012\u0004\b\u001f\u0010\u001a\u001a\u0004\b\u001d\u0010\u001eR \u0010\"\u001a\u0006\u0012\u0002\b\u00030!8\u0016@\u0016X\u0096\u0004\u00a2\u0006\f\n\u0004\b\"\u0010#\u001a\u0004\b$\u0010%R\u001e\u0010*\u001a\u0004\u0018\u00010&8V@\u0017X\u0097\u0004\u00a2\u0006\f\u0012\u0004\b)\u0010\u001a\u001a\u0004\b'\u0010(R\u001c\u0010+\u001a\u00020\b8V@\u0017X\u0097\u0004\u00a2\u0006\f\u0012\u0004\b,\u0010\u001a\u001a\u0004\b+\u0010\u0018R\"\u00101\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020.0-8V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b/\u00100R \u00103\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00010-8V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b2\u00100R\u001c\u00104\u001a\u00020\b8V@\u0017X\u0097\u0004\u00a2\u0006\f\u0012\u0004\b5\u0010\u001a\u001a\u0004\b4\u0010\u0018R\u001c\u00106\u001a\u00020\b8V@\u0017X\u0097\u0004\u00a2\u0006\f\u0012\u0004\b7\u0010\u001a\u001a\u0004\b6\u0010\u0018R\u0018\u0010:\u001a\u0004\u0018\u00010\u00028V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b8\u00109R\u001c\u0010;\u001a\u00020\b8V@\u0017X\u0097\u0004\u00a2\u0006\f\u0012\u0004\b<\u0010\u001a\u001a\u0004\b;\u0010\u0018R*\u0010?\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u00010\u001b8V@\u0017X\u0097\u0004\u00a2\u0006\f\u0012\u0004\b>\u0010\u001a\u001a\u0004\b=\u0010\u001eR\u001c\u0010B\u001a\b\u0012\u0004\u0012\u00020@0\u001b8V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\bA\u0010\u001eR\u001c\u0010C\u001a\u00020\b8V@\u0017X\u0097\u0004\u00a2\u0006\f\u0012\u0004\bD\u0010\u001a\u001a\u0004\bC\u0010\u0018R\u001c\u0010E\u001a\u00020\b8V@\u0017X\u0097\u0004\u00a2\u0006\f\u0012\u0004\bF\u0010\u001a\u001a\u0004\bE\u0010\u0018R\"\u0010J\u001a\b\u0012\u0004\u0012\u00020G0\u001b8V@\u0017X\u0097\u0004\u00a2\u0006\f\u0012\u0004\bI\u0010\u001a\u001a\u0004\bH\u0010\u001eR \u0010M\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030K0-8V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\bL\u00100\u00a8\u0006Q"}, d2 = {"Lkotlin/jvm/internal/ClassReference;", "Lkotlin/reflect/KClass;", "", "Lkotlin/jvm/internal/ClassBasedDeclarationContainer;", "", "error", "()Ljava/lang/Void;", "value", "", "isInstance", "(Ljava/lang/Object;)Z", "other", "equals", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "getSimpleName", "simpleName", "getQualifiedName", "qualifiedName", "isInner", "()Z", "isInner$annotations", "()V", "", "Lkotlin/reflect/KTypeParameter;", "getTypeParameters", "()Ljava/util/List;", "typeParameters$annotations", "typeParameters", "Ljava/lang/Class;", "jClass", "Ljava/lang/Class;", "getJClass", "()Ljava/lang/Class;", "Lkotlin/reflect/KVisibility;", "getVisibility", "()Lkotlin/reflect/KVisibility;", "visibility$annotations", "visibility", "isData", "isData$annotations", "", "Lkotlin/reflect/KFunction;", "getConstructors", "()Ljava/util/Collection;", "constructors", "getNestedClasses", "nestedClasses", DecodeProducer.EXTRA_IS_FINAL, "isFinal$annotations", "isOpen", "isOpen$annotations", "getObjectInstance", "()Ljava/lang/Object;", "objectInstance", "isAbstract", "isAbstract$annotations", "getSealedSubclasses", "sealedSubclasses$annotations", "sealedSubclasses", "", "getAnnotations", "annotations", "isSealed", "isSealed$annotations", "isCompanion", "isCompanion$annotations", "Lkotlin/reflect/KType;", "getSupertypes", "supertypes$annotations", "supertypes", "Lkotlin/reflect/KCallable;", "getMembers", "members", "<init>", "(Ljava/lang/Class;)V", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 4, 0})
/* renamed from: kotlin.jvm.internal.ClassReference  reason: from toString */
/* loaded from: classes11.dex */
public final class   implements KClass<Object>, ClassBasedDeclarationContainer {

    /* renamed from: Companion  reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Map<Class<? extends Function<?>>, Integer> FUNCTION_CLASSES;
    private static final HashMap<String, String> classFqNames;
    private static final HashMap<String, String> primitiveFqNames;
    private static final HashMap<String, String> primitiveWrapperFqNames;
    private static final Map<String, String> simpleNames;
    @NotNull
    private final Class<?> jClass;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u001b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u001b\u0010\u0007\u001a\u0004\u0018\u00010\u00042\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0002\u00a2\u0006\u0004\b\u0007\u0010\u0006J#\u0010\n\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u00012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0002\u00a2\u0006\u0004\b\n\u0010\u000bR.\u0010\u000f\u001a\u001a\u0012\u0010\u0012\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\r0\u0002\u0012\u0004\u0012\u00020\u000e0\f8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u0010R2\u0010\u0013\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0011j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004`\u00128\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0014R2\u0010\u0015\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0011j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004`\u00128\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0014R2\u0010\u0016\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0011j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004`\u00128\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0014R\"\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\f8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0010\u00a8\u0006\u001a"}, d2 = {"Lkotlin/jvm/internal/ClassReference$Companion;", "", "Ljava/lang/Class;", "jClass", "", "getClassSimpleName", "(Ljava/lang/Class;)Ljava/lang/String;", "getClassQualifiedName", "value", "", "isInstance", "(Ljava/lang/Object;Ljava/lang/Class;)Z", "", "Lkotlin/Function;", "", "FUNCTION_CLASSES", "Ljava/util/Map;", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "classFqNames", "Ljava/util/HashMap;", "primitiveFqNames", "primitiveWrapperFqNames", "simpleNames", "<init>", "()V", "kotlin-stdlib"}, k = 1, mv = {1, 4, 0})
    /* renamed from: kotlin.jvm.internal.ClassReference$Companion  reason: from kotlin metadata */
    /* loaded from: classes11.dex */
    public static final class Companion {
        private Companion() {
        }

        @Nullable
        public final String getClassQualifiedName(@NotNull Class<?> jClass) {
            String str;
            String str2 = null;
            if (jClass.isAnonymousClass() || jClass.isLocalClass()) {
                return null;
            }
            if (!jClass.isArray()) {
                String str3 = (String)  .classFqNames.get(jClass.getName());
                return str3 != null ? str3 : jClass.getCanonicalName();
            }
            Class<?> componentType = jClass.getComponentType();
            Intrinsics.checkExpressionValueIsNotNull(componentType, "componentType");
            if (componentType.isPrimitive() && (str = (String)  .classFqNames.get(componentType.getName())) != null) {
                str2 = str + "Array";
            }
            return str2 != null ? str2 : "kotlin.Array";
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x003b, code lost:
            if (r1 != null) goto L16;
         */
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.String getClassSimpleName(@org.jetbrains.annotations.NotNull java.lang.Class<?> r8) {
            /*
                r7 = this;
                boolean r0 = r8.isAnonymousClass()
                java.lang.String r1 = "Array"
                r2 = 0
                if (r0 == 0) goto Lc
            L9:
                r1 = r2
                goto Lbc
            Lc:
                boolean r0 = r8.isLocalClass()
                if (r0 == 0) goto L6e
                java.lang.String r0 = r8.getSimpleName()
                java.lang.reflect.Method r1 = r8.getEnclosingMethod()
                java.lang.String r3 = "$"
                r4 = 2
                java.lang.String r5 = "name"
                if (r1 == 0) goto L3e
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r5)
                java.lang.StringBuilder r6 = new java.lang.StringBuilder
                r6.<init>()
                java.lang.String r1 = r1.getName()
                r6.append(r1)
                r6.append(r3)
                java.lang.String r1 = r6.toString()
                java.lang.String r1 = kotlin.text.StringsKt.substringAfter$default(r0, r1, r2, r4, r2)
                if (r1 == 0) goto L3e
                goto L61
            L3e:
                java.lang.reflect.Constructor r8 = r8.getEnclosingConstructor()
                if (r8 == 0) goto L60
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r5)
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r8 = r8.getName()
                r1.append(r8)
                r1.append(r3)
                java.lang.String r8 = r1.toString()
                java.lang.String r8 = kotlin.text.StringsKt.substringAfter$default(r0, r8, r2, r4, r2)
                r1 = r8
                goto L61
            L60:
                r1 = r2
            L61:
                if (r1 == 0) goto L64
                goto Lbc
            L64:
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r5)
                r8 = 36
                java.lang.String r1 = kotlin.text.StringsKt.substringAfter$default(r0, r8, r2, r4, r2)
                goto Lbc
            L6e:
                boolean r0 = r8.isArray()
                if (r0 == 0) goto La6
                java.lang.Class r8 = r8.getComponentType()
                java.lang.String r0 = "componentType"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r8, r0)
                boolean r0 = r8.isPrimitive()
                if (r0 == 0) goto La2
                java.util.Map r0 = kotlin.jvm.internal. .access$getSimpleNames$cp()
                java.lang.String r8 = r8.getName()
                java.lang.Object r8 = r0.get(r8)
                java.lang.String r8 = (java.lang.String) r8
                if (r8 == 0) goto La2
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                r0.append(r8)
                r0.append(r1)
                java.lang.String r2 = r0.toString()
            La2:
                if (r2 == 0) goto Lbc
                goto L9
            La6:
                java.util.Map r0 = kotlin.jvm.internal. .access$getSimpleNames$cp()
                java.lang.String r1 = r8.getName()
                java.lang.Object r0 = r0.get(r1)
                r1 = r0
                java.lang.String r1 = (java.lang.String) r1
                if (r1 == 0) goto Lb8
                goto Lbc
            Lb8:
                java.lang.String r1 = r8.getSimpleName()
            Lbc:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.jvm.internal. .Companion.getClassSimpleName(java.lang.Class):java.lang.String");
        }

        public final boolean isInstance(@Nullable Object value, @NotNull Class<?> jClass) {
            Map map =  .FUNCTION_CLASSES;
            if (map != null) {
                Integer num = (Integer) map.get(jClass);
                if (num != null) {
                    return TypeIntrinsics.isFunctionOfArity(value, num.intValue());
                }
                if (jClass.isPrimitive()) {
                    jClass = JvmClassMappingKt.getJavaObjectType(JvmClassMappingKt.getKotlinClass(jClass));
                }
                return jClass.isInstance(value);
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.Map<K, V>");
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    static {
        List listOf;
        int collectionSizeOrDefault;
        Map<Class<? extends Function<?>>, Integer> map;
        int mapCapacity;
        String substringAfterLast$default;
        String substringAfterLast$default2;
        int i2 = 0;
        listOf = CollectionsKt__CollectionsKt.listOf((Object[]) new Class[]{Function0.class, Function1.class, Function2.class, Function3.class, Function4.class, Function5.class, Function6.class, Function7.class, Function8.class, Function9.class, Function10.class, Function11.class, Function12.class, Function13.class, Function14.class, Function15.class, Function16.class, Function17.class, Function18.class, Function19.class, Function20.class, Function21.class, Function22.class});
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(listOf, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (Object obj : listOf) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            arrayList.add(TuplesKt.to((Class) obj, Integer.valueOf(i2)));
            i2 = i3;
        }
        map = MapsKt__MapsKt.toMap(arrayList);
        FUNCTION_CLASSES = map;
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("boolean", "kotlin.Boolean");
        hashMap.put(DYConstants.DY_CHAR, "kotlin.Char");
        hashMap.put("byte", "kotlin.Byte");
        hashMap.put("short", "kotlin.Short");
        hashMap.put("int", "kotlin.Int");
        hashMap.put("float", "kotlin.Float");
        hashMap.put("long", "kotlin.Long");
        hashMap.put("double", "kotlin.Double");
        primitiveFqNames = hashMap;
        HashMap<String, String> hashMap2 = new HashMap<>();
        hashMap2.put("java.lang.Boolean", "kotlin.Boolean");
        hashMap2.put("java.lang.Character", "kotlin.Char");
        hashMap2.put("java.lang.Byte", "kotlin.Byte");
        hashMap2.put("java.lang.Short", "kotlin.Short");
        hashMap2.put("java.lang.Integer", "kotlin.Int");
        hashMap2.put("java.lang.Float", "kotlin.Float");
        hashMap2.put("java.lang.Long", "kotlin.Long");
        hashMap2.put("java.lang.Double", "kotlin.Double");
        primitiveWrapperFqNames = hashMap2;
        HashMap<String, String> hashMap3 = new HashMap<>();
        hashMap3.put("java.lang.Object", "kotlin.Any");
        hashMap3.put("java.lang.String", "kotlin.String");
        hashMap3.put("java.lang.CharSequence", "kotlin.CharSequence");
        hashMap3.put("java.lang.Throwable", "kotlin.Throwable");
        hashMap3.put("java.lang.Cloneable", "kotlin.Cloneable");
        hashMap3.put("java.lang.Number", "kotlin.Number");
        hashMap3.put("java.lang.Comparable", "kotlin.Comparable");
        hashMap3.put("java.lang.Enum", "kotlin.Enum");
        hashMap3.put("java.lang.annotation.Annotation", "kotlin.Annotation");
        hashMap3.put("java.lang.Iterable", "kotlin.collections.Iterable");
        hashMap3.put("java.util.Iterator", "kotlin.collections.Iterator");
        hashMap3.put("java.util.Collection", "kotlin.collections.Collection");
        hashMap3.put("java.util.List", "kotlin.collections.List");
        hashMap3.put("java.util.Set", "kotlin.collections.Set");
        hashMap3.put("java.util.ListIterator", "kotlin.collections.ListIterator");
        hashMap3.put("java.util.Map", "kotlin.collections.Map");
        hashMap3.put("java.util.Map$Entry", "kotlin.collections.Map.Entry");
        hashMap3.put("kotlin.jvm.internal.StringCompanionObject", "kotlin.String.Companion");
        hashMap3.put("kotlin.jvm.internal.EnumCompanionObject", "kotlin.Enum.Companion");
        hashMap3.putAll(hashMap);
        hashMap3.putAll(hashMap2);
        Collection<String> values = hashMap.values();
        Intrinsics.checkExpressionValueIsNotNull(values, "primitiveFqNames.values");
        for (String kotlinName : values) {
            StringBuilder sb = new StringBuilder();
            sb.append("kotlin.jvm.internal.");
            Intrinsics.checkExpressionValueIsNotNull(kotlinName, "kotlinName");
            substringAfterLast$default2 = StringsKt__StringsKt.substringAfterLast$default(kotlinName, (char) OrderISVUtil.MONEY_DECIMAL_CHAR, (String) null, 2, (Object) null);
            sb.append(substringAfterLast$default2);
            sb.append("CompanionObject");
            Pair pair = TuplesKt.to(sb.toString(), kotlinName + ".Companion");
            hashMap3.put(pair.getFirst(), pair.getSecond());
        }
        for (Map.Entry<Class<? extends Function<?>>, Integer> entry : FUNCTION_CLASSES.entrySet()) {
            hashMap3.put(entry.getKey().getName(), "kotlin.Function" + entry.getValue().intValue());
        }
        classFqNames = hashMap3;
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(hashMap3.size());
        LinkedHashMap linkedHashMap = new LinkedHashMap(mapCapacity);
        for (Map.Entry entry2 : hashMap3.entrySet()) {
            Object key = entry2.getKey();
            substringAfterLast$default = StringsKt__StringsKt.substringAfterLast$default((String) entry2.getValue(), (char) OrderISVUtil.MONEY_DECIMAL_CHAR, (String) null, 2, (Object) null);
            linkedHashMap.put(key, substringAfterLast$default);
        }
        simpleNames = linkedHashMap;
    }

    public  (@NotNull Class<?> cls) {
        this.jClass = cls;
    }

    private final Void error() {
        throw new KotlinReflectionNotSupportedError();
    }

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

    @Override // kotlin.reflect.KClass
    public boolean equals(@Nullable Object other) {
        return (other instanceof  ) && Intrinsics.areEqual(JvmClassMappingKt.getJavaObjectType(this), JvmClassMappingKt.getJavaObjectType((KClass) other));
    }

    @Override // kotlin.reflect.KAnnotatedElement
    @NotNull
    public List<Annotation> getAnnotations() {
        error();
        throw null;
    }

    @Override // kotlin.reflect.KClass
    @NotNull
    public Collection<KFunction<Object>> getConstructors() {
        error();
        throw null;
    }

    @Override // kotlin.jvm.internal.ClassBasedDeclarationContainer
    @NotNull
    public Class<?> getJClass() {
        return this.jClass;
    }

    @Override // kotlin.reflect.KClass, kotlin.reflect.KDeclarationContainer
    @NotNull
    public Collection<KCallable<?>> getMembers() {
        error();
        throw null;
    }

    @Override // kotlin.reflect.KClass
    @NotNull
    public Collection<KClass<?>> getNestedClasses() {
        error();
        throw null;
    }

    @Override // kotlin.reflect.KClass
    @Nullable
    public Object getObjectInstance() {
        error();
        throw null;
    }

    @Override // kotlin.reflect.KClass
    @Nullable
    public String getQualifiedName() {
        return INSTANCE.getClassQualifiedName(getJClass());
    }

    @Override // kotlin.reflect.KClass
    @NotNull
    public List<KClass<? extends Object>> getSealedSubclasses() {
        error();
        throw null;
    }

    @Override // kotlin.reflect.KClass
    @Nullable
    public String getSimpleName() {
        return INSTANCE.getClassSimpleName(getJClass());
    }

    @Override // kotlin.reflect.KClass
    @NotNull
    public List<KType> getSupertypes() {
        error();
        throw null;
    }

    @Override // kotlin.reflect.KClass
    @NotNull
    public List<KTypeParameter> getTypeParameters() {
        error();
        throw null;
    }

    @Override // kotlin.reflect.KClass
    @Nullable
    public KVisibility getVisibility() {
        error();
        throw null;
    }

    @Override // kotlin.reflect.KClass
    public int hashCode() {
        return JvmClassMappingKt.getJavaObjectType(this).hashCode();
    }

    @Override // kotlin.reflect.KClass
    public boolean isAbstract() {
        error();
        throw null;
    }

    @Override // kotlin.reflect.KClass
    public boolean isCompanion() {
        error();
        throw null;
    }

    @Override // kotlin.reflect.KClass
    public boolean isData() {
        error();
        throw null;
    }

    @Override // kotlin.reflect.KClass
    public boolean isFinal() {
        error();
        throw null;
    }

    @Override // kotlin.reflect.KClass
    public boolean isInner() {
        error();
        throw null;
    }

    @Override // kotlin.reflect.KClass
    @SinceKotlin(version = "1.1")
    public boolean isInstance(@Nullable Object value) {
        return INSTANCE.isInstance(value, getJClass());
    }

    @Override // kotlin.reflect.KClass
    public boolean isOpen() {
        error();
        throw null;
    }

    @Override // kotlin.reflect.KClass
    public boolean isSealed() {
        error();
        throw null;
    }

    @NotNull
    public String toString() {
        return getJClass().toString() + " (Kotlin reflection is not available)";
    }
}
