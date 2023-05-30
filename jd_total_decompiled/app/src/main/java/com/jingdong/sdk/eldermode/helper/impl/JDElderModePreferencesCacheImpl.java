package com.jingdong.sdk.eldermode.helper.impl;

import android.content.Context;
import android.content.SharedPreferences;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.sdk.eldermode.helper.IElderModeCache;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u000e\b\u0016\u0018\u0000 %2\u00020\u0001:\u0001%B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b#\u0010$J'\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0014\u00a2\u0006\u0004\b\t\u0010\nJ\u0017\u0010\f\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\f\u0010\rJ\u001f\u0010\f\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\f\u0010\u000fJ\u001f\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u001f\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0006H\u0016\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0017\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u000b\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u001f\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0018H\u0016\u00a2\u0006\u0004\b\u001b\u0010\u001cR\u0016\u0010\u0003\u001a\u00020\u00028\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u001dR\u001d\u0010\"\u001a\u00020\b8B@\u0002X\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!\u00a8\u0006&"}, d2 = {"Lcom/jingdong/sdk/eldermode/helper/impl/JDElderModePreferencesCacheImpl;", "Lcom/jingdong/sdk/eldermode/helper/IElderModeCache;", "Landroid/content/Context;", AnnoConst.Constructor_Context, "", "name", "", "mode", "Landroid/content/SharedPreferences;", "createSharedPreferences", "(Landroid/content/Context;Ljava/lang/String;I)Landroid/content/SharedPreferences;", "key", "getString", "(Ljava/lang/String;)Ljava/lang/String;", "defaultValue", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "value", "", "putString", "(Ljava/lang/String;Ljava/lang/String;)V", "getInt", "(Ljava/lang/String;)I", "putInt", "(Ljava/lang/String;I)V", "", "getLong", "(Ljava/lang/String;)J", "putLong", "(Ljava/lang/String;J)V", "Landroid/content/Context;", "sharedPreferences$delegate", "Lkotlin/Lazy;", "getSharedPreferences", "()Landroid/content/SharedPreferences;", "sharedPreferences", "<init>", "(Landroid/content/Context;)V", "Companion", "eldermodelib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes7.dex */
public class JDElderModePreferencesCacheImpl implements IElderModeCache {
    private static final String SP_FILE_NAME = "elder_mode_sp";
    private final Context context;

    /* renamed from: sharedPreferences$delegate  reason: from kotlin metadata */
    private final Lazy sharedPreferences;

    public JDElderModePreferencesCacheImpl(@NotNull Context context) {
        Lazy lazy;
        this.context = context;
        lazy = LazyKt__LazyJVMKt.lazy(new Function0<SharedPreferences>() { // from class: com.jingdong.sdk.eldermode.helper.impl.JDElderModePreferencesCacheImpl$sharedPreferences$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final SharedPreferences invoke() {
                Context context2;
                JDElderModePreferencesCacheImpl jDElderModePreferencesCacheImpl = JDElderModePreferencesCacheImpl.this;
                context2 = jDElderModePreferencesCacheImpl.context;
                return jDElderModePreferencesCacheImpl.createSharedPreferences(context2, "elder_mode_sp", 0);
            }
        });
        this.sharedPreferences = lazy;
    }

    private final SharedPreferences getSharedPreferences() {
        return (SharedPreferences) this.sharedPreferences.getValue();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public SharedPreferences createSharedPreferences(@NotNull Context context, @NotNull String name, int mode) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, mode);
        Intrinsics.checkExpressionValueIsNotNull(sharedPreferences, "context.getSharedPreferences(name, mode)");
        return sharedPreferences;
    }

    @Override // com.jingdong.sdk.eldermode.helper.IElderModeCache
    public int getInt(@NotNull String key) {
        return getSharedPreferences().getInt(key, 0);
    }

    @Override // com.jingdong.sdk.eldermode.helper.IElderModeCache
    public long getLong(@NotNull String key) {
        return getSharedPreferences().getLong(key, 0L);
    }

    @Override // com.jingdong.sdk.eldermode.helper.IElderModeCache
    @NotNull
    public String getString(@NotNull String key) {
        String string = getSharedPreferences().getString(key, "");
        return string != null ? string : "";
    }

    @Override // com.jingdong.sdk.eldermode.helper.IElderModeCache
    public void putInt(@NotNull String key, int value) {
        getSharedPreferences().edit().putInt(key, value).apply();
    }

    @Override // com.jingdong.sdk.eldermode.helper.IElderModeCache
    public void putLong(@NotNull String key, long value) {
        getSharedPreferences().edit().putLong(key, value).apply();
    }

    @Override // com.jingdong.sdk.eldermode.helper.IElderModeCache
    public void putString(@NotNull String key, @NotNull String value) {
        getSharedPreferences().edit().putString(key, value).apply();
    }

    @Override // com.jingdong.sdk.eldermode.helper.IElderModeCache
    @NotNull
    public String getString(@NotNull String key, @NotNull String defaultValue) {
        String string = getSharedPreferences().getString(key, defaultValue);
        return string != null ? string : "";
    }
}
