package com.jd.dynamic.apis;

import android.content.Context;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.jd.dynamic.base.IFunctionFactory;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.vivo.push.PushClientConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Keep
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\b\u0007\u0018\u00002\u00020\u0001B1\u0012\b\b\u0001\u00106\u001a\u000205\u0012\b\b\u0001\u0010,\u001a\u00020\u0007\u0012\b\b\u0001\u0010:\u001a\u00020\u0007\u0012\n\b\u0002\u00100\u001a\u0004\u0018\u00010/\u00a2\u0006\u0004\b=\u0010>J\u0015\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\t\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\u000e\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0002\u00a2\u0006\u0004\b\u000e\u0010\u0006J\r\u0010\u000f\u001a\u00020\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u001d\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0012\u00a2\u0006\u0004\b\u0014\u0010\u0015J\r\u0010\u0016\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0010J\r\u0010\u0017\u001a\u00020\u0012\u00a2\u0006\u0004\b\u0017\u0010\u0018J\r\u0010\u0019\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0019\u0010\u0010J\u0015\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u0002\u00a2\u0006\u0004\b\u001b\u0010\u0006J\r\u0010\u001c\u001a\u00020\u0002\u00a2\u0006\u0004\b\u001c\u0010\u0010J\r\u0010\u001d\u001a\u00020\u0002\u00a2\u0006\u0004\b\u001d\u0010\u0010J\u0015\u0010\u001f\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u0002\u00a2\u0006\u0004\b\u001f\u0010\u0006J\u0015\u0010!\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u0002\u00a2\u0006\u0004\b!\u0010\u0006J\r\u0010\"\u001a\u00020\u0002\u00a2\u0006\u0004\b\"\u0010\u0010J\u0015\u0010$\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u0012\u00a2\u0006\u0004\b$\u0010%J\r\u0010&\u001a\u00020\u0012\u00a2\u0006\u0004\b&\u0010\u0018J\u0015\u0010(\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\u0012\u00a2\u0006\u0004\b(\u0010%J\r\u0010)\u001a\u00020\u0012\u00a2\u0006\u0004\b)\u0010\u0018R\u0016\u0010'\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b'\u0010*R\u0016\u0010\u0003\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0003\u0010+R\u0019\u0010,\u001a\u00020\u00078\u0006@\u0006\u00a2\u0006\f\n\u0004\b,\u0010-\u001a\u0004\b.\u0010\fR\u001b\u00100\u001a\u0004\u0018\u00010/8\u0006@\u0006\u00a2\u0006\f\n\u0004\b0\u00101\u001a\u0004\b2\u00103R\u0016\u0010\r\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\r\u0010+R\u0016\u0010\u001e\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001e\u0010+R\u0016\u0010\u0013\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0013\u0010*R\u0016\u0010\u0011\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0011\u0010+R\u0018\u00104\u001a\u0004\u0018\u00010\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b4\u0010-R\u0019\u00106\u001a\u0002058\u0006@\u0006\u00a2\u0006\f\n\u0004\b6\u00107\u001a\u0004\b8\u00109R\u0019\u0010:\u001a\u00020\u00078\u0006@\u0006\u00a2\u0006\f\n\u0004\b:\u0010-\u001a\u0004\b;\u0010\fR\u0016\u0010#\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b#\u0010*R\u0016\u0010<\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b<\u0010+R\u0016\u0010\u001a\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001a\u0010+\u00a8\u0006?"}, d2 = {"Lcom/jd/dynamic/apis/DYContainerConfig;", "", "", "useCacheView", "", "setUseCacheView", "(Z)V", "", "name", "setPackageName", "(Ljava/lang/String;)V", "getPackageName", "()Ljava/lang/String;", "useAsyncItem", "setUseAsyncItem", "isUseAsyncItem", "()Z", "useSkeleton", "", "skeletonHeight", "setSkeletonSetting", "(ZI)V", "isUseSkeleton", "getSkeletonHeight", "()I", "isUseCacheView", "isAutoListen", "shouldAutoListenDarkStatus", "isAutoListenDarkStatus", "isUseTagViewOptimize", "useTagViewOptimize", "setUseTagViewOptimize", "useBackup", "setUseCustomBackup", "getUseCustomBackup", "containerWidth", "setContainerWidth", "(I)V", "getContainerWidth", "containerHeight", "setContainerHeight", "getContainerHeight", "I", "Z", "module", "Ljava/lang/String;", "getModule", "Lcom/jd/dynamic/base/IFunctionFactory;", "factory", "Lcom/jd/dynamic/base/IFunctionFactory;", "getFactory", "()Lcom/jd/dynamic/base/IFunctionFactory;", PushClientConstants.TAG_PKG_NAME, "Landroid/content/Context;", AnnoConst.Constructor_Context, "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "templateId", "getTemplateId", "isUseCustomBackup", "<init>", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Lcom/jd/dynamic/base/IFunctionFactory;)V", "lib_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes13.dex */
public final class DYContainerConfig {
    private int containerHeight;
    private int containerWidth;
    @NotNull
    private final Context context;
    @Nullable
    private final IFunctionFactory factory;
    private boolean isAutoListen;
    private boolean isUseCustomBackup;
    @NotNull
    private final String module;
    private String pkgName;
    private int skeletonHeight;
    @NotNull
    private final String templateId;
    private boolean useAsyncItem;
    private boolean useCacheView;
    private boolean useSkeleton;
    private boolean useTagViewOptimize;

    public DYContainerConfig(@NonNull @NotNull Context context, @NonNull @NotNull String str, @NonNull @NotNull String str2, @Nullable IFunctionFactory iFunctionFactory) {
        this.context = context;
        this.module = str;
        this.templateId = str2;
        this.factory = iFunctionFactory;
        this.isAutoListen = true;
        this.skeletonHeight = 120;
    }

    public /* synthetic */ DYContainerConfig(Context context, String str, String str2, IFunctionFactory iFunctionFactory, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, str, str2, (i2 & 8) != 0 ? null : iFunctionFactory);
    }

    public final int getContainerHeight() {
        return this.containerHeight;
    }

    public final int getContainerWidth() {
        return this.containerWidth;
    }

    @NotNull
    public final Context getContext() {
        return this.context;
    }

    @Nullable
    public final IFunctionFactory getFactory() {
        return this.factory;
    }

    @NotNull
    public final String getModule() {
        return this.module;
    }

    @Nullable
    /* renamed from: getPackageName  reason: from getter */
    public final String getPkgName() {
        return this.pkgName;
    }

    public final int getSkeletonHeight() {
        return this.skeletonHeight;
    }

    @NotNull
    public final String getTemplateId() {
        return this.templateId;
    }

    /* renamed from: getUseCustomBackup  reason: from getter */
    public final boolean getIsUseCustomBackup() {
        return this.isUseCustomBackup;
    }

    /* renamed from: isAutoListenDarkStatus  reason: from getter */
    public final boolean getIsAutoListen() {
        return this.isAutoListen;
    }

    /* renamed from: isUseAsyncItem  reason: from getter */
    public final boolean getUseAsyncItem() {
        return this.useAsyncItem;
    }

    /* renamed from: isUseCacheView  reason: from getter */
    public final boolean getUseCacheView() {
        return this.useCacheView;
    }

    /* renamed from: isUseSkeleton  reason: from getter */
    public final boolean getUseSkeleton() {
        return this.useSkeleton;
    }

    /* renamed from: isUseTagViewOptimize  reason: from getter */
    public final boolean getUseTagViewOptimize() {
        return this.useTagViewOptimize;
    }

    public final void setContainerHeight(int containerHeight) {
        this.containerHeight = containerHeight;
    }

    public final void setContainerWidth(int containerWidth) {
        this.containerWidth = containerWidth;
    }

    public final void setPackageName(@NotNull String name) {
        this.pkgName = name;
    }

    public final void setSkeletonSetting(boolean useSkeleton, int skeletonHeight) {
        this.useSkeleton = useSkeleton;
        this.skeletonHeight = skeletonHeight;
    }

    public final void setUseAsyncItem(boolean useAsyncItem) {
        this.useAsyncItem = useAsyncItem;
    }

    public final void setUseCacheView(boolean useCacheView) {
        this.useCacheView = useCacheView;
    }

    public final void setUseCustomBackup(boolean useBackup) {
        this.isUseCustomBackup = useBackup;
    }

    public final void setUseTagViewOptimize(boolean useTagViewOptimize) {
        this.useTagViewOptimize = useTagViewOptimize;
    }

    public final void shouldAutoListenDarkStatus(boolean isAutoListen) {
        this.isAutoListen = isAutoListen;
    }
}
