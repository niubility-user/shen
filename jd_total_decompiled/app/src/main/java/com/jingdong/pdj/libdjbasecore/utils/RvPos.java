package com.jingdong.pdj.libdjbasecore.utils;

import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0010\u0018\u00002\u00020\u0001B\u001b\b\u0007\u0012\u0006\u0010\u000f\u001a\u00020\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u001a\u0010\u0004\u001a\u00020\u00032\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001H\u0096\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0016\u00a2\u0006\u0004\b\u0007\u0010\bR\"\u0010\t\u001a\u00020\u00038\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\"\u0010\u000f\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\b\"\u0004\b\u0012\u0010\u0013\u00a8\u0006\u0016"}, d2 = {"Lcom/jingdong/pdj/libdjbasecore/utils/RvPos;", "", "obj", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "enableState", "Z", "getEnableState", "()Z", "setEnableState", "(Z)V", "position", "I", "getPosition", "setPosition", "(I)V", "<init>", "(IZ)V", "libDJBaseCore_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes7.dex */
public final class RvPos {
    private boolean enableState;
    private int position;

    @JvmOverloads
    public RvPos(int i2) {
        this(i2, false, 2, null);
    }

    @JvmOverloads
    public RvPos(int i2, boolean z) {
        this.position = i2;
        this.enableState = z;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof RvPos) {
            RvPos rvPos = (RvPos) obj;
            return this == rvPos || rvPos.position == this.position;
        }
        return false;
    }

    public final boolean getEnableState() {
        return this.enableState;
    }

    public final int getPosition() {
        return this.position;
    }

    public int hashCode() {
        return this.position * 128;
    }

    public final void setEnableState(boolean z) {
        this.enableState = z;
    }

    public final void setPosition(int i2) {
        this.position = i2;
    }

    public /* synthetic */ RvPos(int i2, boolean z, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(i2, (i3 & 2) != 0 ? true : z);
    }
}
