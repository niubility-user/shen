package com.jingdong.pdj.libdjbasecore.utils;

import android.os.SystemClock;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B=\b\u0007\u0012\u0006\u0010\u0014\u001a\u00020\u0002\u0012\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\r0\u001c\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0004\b#\u0010$J\r\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004R\"\u0010\u0006\u001a\u00020\u00058\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0006\u0010\u0007\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR(\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\"\u0010\u0014\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0004\"\u0004\b\u0017\u0010\u0018R\"\u0010\u0019\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0019\u0010\u0015\u001a\u0004\b\u001a\u0010\u0004\"\u0004\b\u001b\u0010\u0018R.\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\r0\u001c8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"\u00a8\u0006%"}, d2 = {"Lcom/jingdong/pdj/libdjbasecore/utils/DownData;", "", "", "getRemainTime", "()J", "", "enableState", "Z", "getEnableState", "()Z", "setEnableState", "(Z)V", "Lkotlin/Function0;", "", "onFinish", "Lkotlin/jvm/functions/Function0;", "getOnFinish", "()Lkotlin/jvm/functions/Function0;", "setOnFinish", "(Lkotlin/jvm/functions/Function0;)V", "downTime", "J", "getDownTime", "setDownTime", "(J)V", "endTime", "getEndTime", "setEndTime", "Lkotlin/Function1;", "onTick", "Lkotlin/jvm/functions/Function1;", "getOnTick", "()Lkotlin/jvm/functions/Function1;", "setOnTick", "(Lkotlin/jvm/functions/Function1;)V", "<init>", "(JLkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Z)V", "libDJBaseCore_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes7.dex */
public final class DownData {
    private long downTime;
    private boolean enableState;
    private long endTime;
    @NotNull
    private Function0<Unit> onFinish;
    @NotNull
    private Function1<? super Long, Unit> onTick;

    @JvmOverloads
    public DownData(long j2, @NotNull Function1<? super Long, Unit> function1, @NotNull Function0<Unit> function0) {
        this(j2, function1, function0, false, 8, null);
    }

    @JvmOverloads
    public DownData(long j2, @NotNull Function1<? super Long, Unit> function1, @NotNull Function0<Unit> function0, boolean z) {
        this.downTime = j2;
        this.onTick = function1;
        this.onFinish = function0;
        this.enableState = z;
        this.endTime = SystemClock.elapsedRealtime() + this.downTime;
    }

    public final long getDownTime() {
        return this.downTime;
    }

    public final boolean getEnableState() {
        return this.enableState;
    }

    public final long getEndTime() {
        return this.endTime;
    }

    @NotNull
    public final Function0<Unit> getOnFinish() {
        return this.onFinish;
    }

    @NotNull
    public final Function1<Long, Unit> getOnTick() {
        return this.onTick;
    }

    public final long getRemainTime() {
        return this.endTime - SystemClock.elapsedRealtime();
    }

    public final void setDownTime(long j2) {
        this.downTime = j2;
    }

    public final void setEnableState(boolean z) {
        this.enableState = z;
    }

    public final void setEndTime(long j2) {
        this.endTime = j2;
    }

    public final void setOnFinish(@NotNull Function0<Unit> function0) {
        this.onFinish = function0;
    }

    public final void setOnTick(@NotNull Function1<? super Long, Unit> function1) {
        this.onTick = function1;
    }

    public /* synthetic */ DownData(long j2, Function1 function1, Function0 function0, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(j2, function1, function0, (i2 & 8) != 0 ? true : z);
    }
}
