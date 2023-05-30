package com.jingdong.common.utils.asyncinflate;

import android.view.View;
import android.view.ViewGroup;
import java.util.concurrent.CountDownLatch;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\u0006\u0010-\u001a\u00020\u001c\u0012\u0006\u0010\u001d\u001a\u00020\u001c\u00a2\u0006\u0004\b.\u0010/B\u0019\b\u0016\u0012\u0006\u0010\u001d\u001a\u00020\u001c\u0012\u0006\u00100\u001a\u00020\u0010\u00a2\u0006\u0004\b.\u00101R\"\u0010\u0003\u001a\u00020\u00028\u0000@\u0000X\u0080\u000e\u00a2\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\n\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR$\u0010\u0011\u001a\u0004\u0018\u00010\u00108\u0000@\u0000X\u0080\u000e\u00a2\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0019\u0010\u0018\u001a\u00020\u00178\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\"\u0010\u001d\u001a\u00020\u001c8\u0000@\u0000X\u0080\u000e\u00a2\u0006\u0012\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R$\u0010$\u001a\u0004\u0018\u00010#8\u0000@\u0000X\u0080\u000e\u00a2\u0006\u0012\n\u0004\b$\u0010%\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\"\u0010*\u001a\u00020\u001c8\u0000@\u0000X\u0080\u000e\u00a2\u0006\u0012\n\u0004\b*\u0010\u001e\u001a\u0004\b+\u0010 \"\u0004\b,\u0010\"\u00a8\u00062"}, d2 = {"Lcom/jingdong/common/utils/asyncinflate/PersonalAsyncInflateItem;", "", "", "isInflating", "Z", "isInflating$personallib", "()Z", "setInflating$personallib", "(Z)V", "Landroid/view/View;", "inflatedView", "Landroid/view/View;", "getInflatedView", "()Landroid/view/View;", "setInflatedView", "(Landroid/view/View;)V", "Lcom/jingdong/common/utils/asyncinflate/PersonalOnInflateFinishedCallback;", "callback", "Lcom/jingdong/common/utils/asyncinflate/PersonalOnInflateFinishedCallback;", "getCallback$personallib", "()Lcom/jingdong/common/utils/asyncinflate/PersonalOnInflateFinishedCallback;", "setCallback$personallib", "(Lcom/jingdong/common/utils/asyncinflate/PersonalOnInflateFinishedCallback;)V", "Ljava/util/concurrent/CountDownLatch;", "countDownLatch", "Ljava/util/concurrent/CountDownLatch;", "getCountDownLatch", "()Ljava/util/concurrent/CountDownLatch;", "", "layoutResId", "I", "getLayoutResId$personallib", "()I", "setLayoutResId$personallib", "(I)V", "Landroid/view/ViewGroup;", "parent", "Landroid/view/ViewGroup;", "getParent$personallib", "()Landroid/view/ViewGroup;", "setParent$personallib", "(Landroid/view/ViewGroup;)V", "mPageHashCode", "getMPageHashCode$personallib", "setMPageHashCode$personallib", "activityHashCode", "<init>", "(II)V", "callBack", "(ILcom/jingdong/common/utils/asyncinflate/PersonalOnInflateFinishedCallback;)V", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public final class PersonalAsyncInflateItem {
    @Nullable
    private PersonalOnInflateFinishedCallback callback;
    @NotNull
    private final CountDownLatch countDownLatch = new CountDownLatch(1);
    @Nullable
    private View inflatedView;
    private boolean isInflating;
    private int layoutResId;
    private int mPageHashCode;
    @Nullable
    private ViewGroup parent;

    public PersonalAsyncInflateItem(int i2, int i3) {
        this.mPageHashCode = i2;
        this.layoutResId = i3;
    }

    @Nullable
    /* renamed from: getCallback$personallib  reason: from getter */
    public final PersonalOnInflateFinishedCallback getCallback() {
        return this.callback;
    }

    @NotNull
    public final CountDownLatch getCountDownLatch() {
        return this.countDownLatch;
    }

    @Nullable
    public final View getInflatedView() {
        return this.inflatedView;
    }

    /* renamed from: getLayoutResId$personallib  reason: from getter */
    public final int getLayoutResId() {
        return this.layoutResId;
    }

    /* renamed from: getMPageHashCode$personallib  reason: from getter */
    public final int getMPageHashCode() {
        return this.mPageHashCode;
    }

    @Nullable
    /* renamed from: getParent$personallib  reason: from getter */
    public final ViewGroup getParent() {
        return this.parent;
    }

    /* renamed from: isInflating$personallib  reason: from getter */
    public final boolean getIsInflating() {
        return this.isInflating;
    }

    public final void setCallback$personallib(@Nullable PersonalOnInflateFinishedCallback personalOnInflateFinishedCallback) {
        this.callback = personalOnInflateFinishedCallback;
    }

    public final void setInflatedView(@Nullable View view) {
        this.inflatedView = view;
    }

    public final void setInflating$personallib(boolean z) {
        this.isInflating = z;
    }

    public final void setLayoutResId$personallib(int i2) {
        this.layoutResId = i2;
    }

    public final void setMPageHashCode$personallib(int i2) {
        this.mPageHashCode = i2;
    }

    public final void setParent$personallib(@Nullable ViewGroup viewGroup) {
        this.parent = viewGroup;
    }

    public PersonalAsyncInflateItem(int i2, @NotNull PersonalOnInflateFinishedCallback personalOnInflateFinishedCallback) {
        this.layoutResId = i2;
        this.callback = personalOnInflateFinishedCallback;
    }
}
