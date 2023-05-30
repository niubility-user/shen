package com.jd.jdcache.service.base;

import androidx.annotation.Keep;
import androidx.core.app.NotificationCompat;
import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.manto.jsapi.refact.lbs.JsApiLocation;
import com.jingdong.manto.sdk.api.IAudioPlayer;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Keep
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0005\b\t\n\u000b\fB\t\b\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\u0004\u001a\u00020\u0003H\u0016\u00a2\u0006\u0004\b\u0004\u0010\u0005\u0082\u0001\u0005\r\u000e\u000f\u0010\u0011\u00a8\u0006\u0012"}, d2 = {"Lcom/jd/jdcache/service/base/NetState;", "T", "", "", "toString", "()Ljava/lang/String;", "<init>", "()V", "Complete", IAudioPlayer.AUDIO_STATE_ERROR, "OnProgress", "OnStart", "Redirect", "Lcom/jd/jdcache/service/base/NetState$OnStart;", "Lcom/jd/jdcache/service/base/NetState$OnProgress;", "Lcom/jd/jdcache/service/base/NetState$Redirect;", "Lcom/jd/jdcache/service/base/NetState$Error;", "Lcom/jd/jdcache/service/base/NetState$Complete;", "JDCache_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes13.dex */
public abstract class NetState<T> {

    @Keep
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\b\u0087\b\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u00028\u00010\u0002B?\u0012\u0006\u0010\u0010\u001a\u00020\u0003\u0012\u001e\u0010\u0011\u001a\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\b\u0018\u00010\u0006\u0012\u0006\u0010\u0012\u001a\u00020\u000b\u0012\u0006\u0010\u0013\u001a\u00028\u0001\u00a2\u0006\u0004\b&\u0010'J\u0010\u0010\u0004\u001a\u00020\u0003H\u00c6\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J(\u0010\t\u001a\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\b\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0004\b\t\u0010\nJ\u0010\u0010\f\u001a\u00020\u000bH\u00c6\u0003\u00a2\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00028\u0001H\u00c6\u0003\u00a2\u0006\u0004\b\u000e\u0010\u000fJV\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\b\b\u0002\u0010\u0010\u001a\u00020\u00032 \b\u0002\u0010\u0011\u001a\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\b\u0018\u00010\u00062\b\b\u0002\u0010\u0012\u001a\u00020\u000b2\b\b\u0002\u0010\u0013\u001a\u00028\u0001H\u00c6\u0001\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u0016\u001a\u00020\u0007H\u00d6\u0001\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0018\u001a\u00020\u0003H\u00d6\u0001\u00a2\u0006\u0004\b\u0018\u0010\u0005J\u001a\u0010\u001c\u001a\u00020\u001b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019H\u00d6\u0003\u00a2\u0006\u0004\b\u001c\u0010\u001dR\u0019\u0010\u0013\u001a\u00028\u00018\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0013\u0010\u001e\u001a\u0004\b\u001f\u0010\u000fR1\u0010\u0011\u001a\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\b\u0018\u00010\u00068\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0011\u0010 \u001a\u0004\b!\u0010\nR\u0019\u0010\u0012\u001a\u00020\u000b8\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0012\u0010\"\u001a\u0004\b#\u0010\rR\u0019\u0010\u0010\u001a\u00020\u00038\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0010\u0010$\u001a\u0004\b%\u0010\u0005\u00a8\u0006("}, d2 = {"Lcom/jd/jdcache/service/base/NetState$Complete;", "T", "Lcom/jd/jdcache/service/base/NetState;", "", "component1", "()I", "", "", "", "component2", "()Ljava/util/Map;", "", "component3", "()J", "component4", "()Ljava/lang/Object;", "code", "headers", CartConstant.KEY_LENGTH, "data", JDViewKitEventHelper.ActionType_Copy, "(ILjava/util/Map;JLjava/lang/Object;)Lcom/jd/jdcache/service/base/NetState$Complete;", "toString", "()Ljava/lang/String;", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/Object;", "getData", "Ljava/util/Map;", "getHeaders", "J", "getLength", "I", "getCode", "<init>", "(ILjava/util/Map;JLjava/lang/Object;)V", "JDCache_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes13.dex */
    public static final /* data */ class Complete<T> extends NetState<T> {
        private final int code;
        private final T data;
        @Nullable
        private final Map<String, List<String>> headers;
        private final long length;

        /* JADX WARN: Multi-variable type inference failed */
        public Complete(int i2, @Nullable Map<String, ? extends List<String>> map, long j2, T t) {
            super(null);
            this.code = i2;
            this.headers = map;
            this.length = j2;
            this.data = t;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Complete copy$default(Complete complete, int i2, Map map, long j2, Object obj, int i3, Object obj2) {
            if ((i3 & 1) != 0) {
                i2 = complete.code;
            }
            Map<String, List<String>> map2 = map;
            if ((i3 & 2) != 0) {
                map2 = complete.headers;
            }
            Map map3 = map2;
            if ((i3 & 4) != 0) {
                j2 = complete.length;
            }
            long j3 = j2;
            T t = obj;
            if ((i3 & 8) != 0) {
                t = complete.data;
            }
            return complete.copy(i2, map3, j3, t);
        }

        /* renamed from: component1  reason: from getter */
        public final int getCode() {
            return this.code;
        }

        @Nullable
        public final Map<String, List<String>> component2() {
            return this.headers;
        }

        /* renamed from: component3  reason: from getter */
        public final long getLength() {
            return this.length;
        }

        public final T component4() {
            return this.data;
        }

        @NotNull
        public final Complete<T> copy(int code, @Nullable Map<String, ? extends List<String>> headers, long length, T data) {
            return new Complete<>(code, headers, length, data);
        }

        public boolean equals(@Nullable Object other) {
            if (this != other) {
                if (other instanceof Complete) {
                    Complete complete = (Complete) other;
                    return this.code == complete.code && Intrinsics.areEqual(this.headers, complete.headers) && this.length == complete.length && Intrinsics.areEqual(this.data, complete.data);
                }
                return false;
            }
            return true;
        }

        public final int getCode() {
            return this.code;
        }

        public final T getData() {
            return this.data;
        }

        @Nullable
        public final Map<String, List<String>> getHeaders() {
            return this.headers;
        }

        public final long getLength() {
            return this.length;
        }

        public int hashCode() {
            int i2 = this.code * 31;
            Map<String, List<String>> map = this.headers;
            int hashCode = map != null ? map.hashCode() : 0;
            long j2 = this.length;
            int i3 = (((i2 + hashCode) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31;
            T t = this.data;
            return i3 + (t != null ? t.hashCode() : 0);
        }

        @Override // com.jd.jdcache.service.base.NetState
        @NotNull
        public String toString() {
            return "Complete(code=" + this.code + ", headers=" + this.headers + ", length=" + this.length + ", data=" + this.data + ")";
        }
    }

    @Keep
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\b\u0087\b\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u00028\u00010\u0002B\u0019\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0010\u0010\u0004\u001a\u00020\u0003H\u00c6\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0004\b\u0007\u0010\bJ,\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\b\b\u0002\u0010\t\u001a\u00020\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0006H\u00c6\u0001\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u000e\u001a\u00020\rH\u00d6\u0001\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u0003H\u00d6\u0001\u00a2\u0006\u0004\b\u0010\u0010\u0005J\u001a\u0010\u0014\u001a\u00020\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011H\u00d6\u0003\u00a2\u0006\u0004\b\u0014\u0010\u0015R\u001b\u0010\n\u001a\u0004\u0018\u00010\u00068\u0006@\u0006\u00a2\u0006\f\n\u0004\b\n\u0010\u0016\u001a\u0004\b\u0017\u0010\bR\u0019\u0010\t\u001a\u00020\u00038\u0006@\u0006\u00a2\u0006\f\n\u0004\b\t\u0010\u0018\u001a\u0004\b\u0019\u0010\u0005\u00a8\u0006\u001c"}, d2 = {"Lcom/jd/jdcache/service/base/NetState$Error;", "T", "Lcom/jd/jdcache/service/base/NetState;", "", "component1", "()I", "", "component2", "()Ljava/lang/Throwable;", "code", "throwable", JDViewKitEventHelper.ActionType_Copy, "(ILjava/lang/Throwable;)Lcom/jd/jdcache/service/base/NetState$Error;", "", "toString", "()Ljava/lang/String;", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/Throwable;", "getThrowable", "I", "getCode", "<init>", "(ILjava/lang/Throwable;)V", "JDCache_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes13.dex */
    public static final /* data */ class Error<T> extends NetState<T> {
        private final int code;
        @Nullable
        private final Throwable throwable;

        public Error(int i2, @Nullable Throwable th) {
            super(null);
            this.code = i2;
            this.throwable = th;
        }

        public static /* synthetic */ Error copy$default(Error error, int i2, Throwable th, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i2 = error.code;
            }
            if ((i3 & 2) != 0) {
                th = error.throwable;
            }
            return error.copy(i2, th);
        }

        /* renamed from: component1  reason: from getter */
        public final int getCode() {
            return this.code;
        }

        @Nullable
        /* renamed from: component2  reason: from getter */
        public final Throwable getThrowable() {
            return this.throwable;
        }

        @NotNull
        public final Error<T> copy(int code, @Nullable Throwable throwable) {
            return new Error<>(code, throwable);
        }

        public boolean equals(@Nullable Object other) {
            if (this != other) {
                if (other instanceof Error) {
                    Error error = (Error) other;
                    return this.code == error.code && Intrinsics.areEqual(this.throwable, error.throwable);
                }
                return false;
            }
            return true;
        }

        public final int getCode() {
            return this.code;
        }

        @Nullable
        public final Throwable getThrowable() {
            return this.throwable;
        }

        public int hashCode() {
            int i2 = this.code * 31;
            Throwable th = this.throwable;
            return i2 + (th != null ? th.hashCode() : 0);
        }

        @Override // com.jd.jdcache.service.base.NetState
        @NotNull
        public String toString() {
            return "Error(code=" + this.code + ", throwable=" + this.throwable + ")";
        }
    }

    @Keep
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\b\u0087\b\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u00028\u00010\u0002B\u0017\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u0004\u001a\u00020\u0003H\u00c6\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u0003H\u00c6\u0003\u00a2\u0006\u0004\b\u0006\u0010\u0005J*\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0003H\u00c6\u0001\u00a2\u0006\u0004\b\t\u0010\nJ\u0010\u0010\f\u001a\u00020\u000bH\u00d6\u0001\u00a2\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000f\u001a\u00020\u000eH\u00d6\u0001\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u001a\u0010\u0014\u001a\u00020\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011H\u00d6\u0003\u00a2\u0006\u0004\b\u0014\u0010\u0015R\u0019\u0010\u0007\u001a\u00020\u00038\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010\u0016\u001a\u0004\b\u0017\u0010\u0005R\u0019\u0010\b\u001a\u00020\u00038\u0006@\u0006\u00a2\u0006\f\n\u0004\b\b\u0010\u0016\u001a\u0004\b\u0018\u0010\u0005\u00a8\u0006\u001b"}, d2 = {"Lcom/jd/jdcache/service/base/NetState$OnProgress;", "T", "Lcom/jd/jdcache/service/base/NetState;", "", "component1", "()J", "component2", NotificationCompat.CATEGORY_PROGRESS, "max", JDViewKitEventHelper.ActionType_Copy, "(JJ)Lcom/jd/jdcache/service/base/NetState$OnProgress;", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "J", "getProgress", "getMax", "<init>", "(JJ)V", "JDCache_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes13.dex */
    public static final /* data */ class OnProgress<T> extends NetState<T> {
        private final long max;
        private final long progress;

        public OnProgress(long j2, long j3) {
            super(null);
            this.progress = j2;
            this.max = j3;
        }

        public static /* synthetic */ OnProgress copy$default(OnProgress onProgress, long j2, long j3, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                j2 = onProgress.progress;
            }
            if ((i2 & 2) != 0) {
                j3 = onProgress.max;
            }
            return onProgress.copy(j2, j3);
        }

        /* renamed from: component1  reason: from getter */
        public final long getProgress() {
            return this.progress;
        }

        /* renamed from: component2  reason: from getter */
        public final long getMax() {
            return this.max;
        }

        @NotNull
        public final OnProgress<T> copy(long progress, long max) {
            return new OnProgress<>(progress, max);
        }

        public boolean equals(@Nullable Object other) {
            if (this != other) {
                if (other instanceof OnProgress) {
                    OnProgress onProgress = (OnProgress) other;
                    return this.progress == onProgress.progress && this.max == onProgress.max;
                }
                return false;
            }
            return true;
        }

        public final long getMax() {
            return this.max;
        }

        public final long getProgress() {
            return this.progress;
        }

        public int hashCode() {
            long j2 = this.progress;
            long j3 = this.max;
            return (((int) (j2 ^ (j2 >>> 32))) * 31) + ((int) (j3 ^ (j3 >>> 32)));
        }

        @Override // com.jd.jdcache.service.base.NetState
        @NotNull
        public String toString() {
            return "OnProgress(progress=" + this.progress + ", max=" + this.max + ")";
        }
    }

    @Keep
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0087\b\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u00028\u00010\u0002B\u000f\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u0004\u001a\u00020\u0003H\u00c6\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J \u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\b\b\u0002\u0010\u0006\u001a\u00020\u0003H\u00c6\u0001\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00020\u0003H\u00d6\u0001\u00a2\u0006\u0004\b\t\u0010\u0005J\u0010\u0010\u000b\u001a\u00020\nH\u00d6\u0001\u00a2\u0006\u0004\b\u000b\u0010\fJ\u001a\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u00d6\u0003\u00a2\u0006\u0004\b\u0010\u0010\u0011R\u0019\u0010\u0006\u001a\u00020\u00038\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u0012\u001a\u0004\b\u0013\u0010\u0005\u00a8\u0006\u0016"}, d2 = {"Lcom/jd/jdcache/service/base/NetState$OnStart;", "T", "Lcom/jd/jdcache/service/base/NetState;", "", "component1", "()Ljava/lang/String;", "url", JDViewKitEventHelper.ActionType_Copy, "(Ljava/lang/String;)Lcom/jd/jdcache/service/base/NetState$OnStart;", "toString", "", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getUrl", "<init>", "(Ljava/lang/String;)V", "JDCache_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes13.dex */
    public static final /* data */ class OnStart<T> extends NetState<T> {
        @NotNull
        private final String url;

        public OnStart(@NotNull String str) {
            super(null);
            this.url = str;
        }

        public static /* synthetic */ OnStart copy$default(OnStart onStart, String str, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = onStart.url;
            }
            return onStart.copy(str);
        }

        @NotNull
        /* renamed from: component1  reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        @NotNull
        public final OnStart<T> copy(@NotNull String url) {
            return new OnStart<>(url);
        }

        public boolean equals(@Nullable Object other) {
            if (this != other) {
                return (other instanceof OnStart) && Intrinsics.areEqual(this.url, ((OnStart) other).url);
            }
            return true;
        }

        @NotNull
        public final String getUrl() {
            return this.url;
        }

        public int hashCode() {
            String str = this.url;
            if (str != null) {
                return str.hashCode();
            }
            return 0;
        }

        @Override // com.jd.jdcache.service.base.NetState
        @NotNull
        public String toString() {
            return "OnStart(url=" + this.url + ")";
        }
    }

    @Keep
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\b\u000b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\b\u0087\b\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u00028\u00010\u0002B;\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012 \u0010\u000e\u001a\u001c\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0018\u00010\b\u0018\u00010\u0006\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0004\b\u001f\u0010 J\u0010\u0010\u0004\u001a\u00020\u0003H\u00c6\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J*\u0010\t\u001a\u001c\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0018\u00010\b\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0004\b\t\u0010\nJ\u0012\u0010\u000b\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003\u00a2\u0006\u0004\b\u000b\u0010\fJP\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\b\b\u0002\u0010\r\u001a\u00020\u00032\"\b\u0002\u0010\u000e\u001a\u001c\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0018\u00010\b\u0018\u00010\u00062\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0007H\u00c6\u0001\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u0007H\u00d6\u0001\u00a2\u0006\u0004\b\u0012\u0010\fJ\u0010\u0010\u0013\u001a\u00020\u0003H\u00d6\u0001\u00a2\u0006\u0004\b\u0013\u0010\u0005J\u001a\u0010\u0017\u001a\u00020\u00162\b\u0010\u0015\u001a\u0004\u0018\u00010\u0014H\u00d6\u0003\u00a2\u0006\u0004\b\u0017\u0010\u0018R\u001b\u0010\u000f\u001a\u0004\u0018\u00010\u00078\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u000f\u0010\u0019\u001a\u0004\b\u001a\u0010\fR3\u0010\u000e\u001a\u001c\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0018\u00010\b\u0018\u00010\u00068\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u000e\u0010\u001b\u001a\u0004\b\u001c\u0010\nR\u0019\u0010\r\u001a\u00020\u00038\u0006@\u0006\u00a2\u0006\f\n\u0004\b\r\u0010\u001d\u001a\u0004\b\u001e\u0010\u0005\u00a8\u0006!"}, d2 = {"Lcom/jd/jdcache/service/base/NetState$Redirect;", "T", "Lcom/jd/jdcache/service/base/NetState;", "", "component1", "()I", "", "", "", "component2", "()Ljava/util/Map;", "component3", "()Ljava/lang/String;", "code", "headers", ThemeTitleConstant.TITLE_LOCATION_DRAWABLE_ID, JDViewKitEventHelper.ActionType_Copy, "(ILjava/util/Map;Ljava/lang/String;)Lcom/jd/jdcache/service/base/NetState$Redirect;", "toString", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", JsApiLocation.GETLOCATION_NAME, "Ljava/util/Map;", "getHeaders", "I", "getCode", "<init>", "(ILjava/util/Map;Ljava/lang/String;)V", "JDCache_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes13.dex */
    public static final /* data */ class Redirect<T> extends NetState<T> {
        private final int code;
        @Nullable
        private final Map<String, List<String>> headers;
        @Nullable
        private final String location;

        /* JADX WARN: Multi-variable type inference failed */
        public Redirect(int i2, @Nullable Map<String, ? extends List<String>> map, @Nullable String str) {
            super(null);
            this.code = i2;
            this.headers = map;
            this.location = str;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Redirect copy$default(Redirect redirect, int i2, Map map, String str, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i2 = redirect.code;
            }
            if ((i3 & 2) != 0) {
                map = redirect.headers;
            }
            if ((i3 & 4) != 0) {
                str = redirect.location;
            }
            return redirect.copy(i2, map, str);
        }

        /* renamed from: component1  reason: from getter */
        public final int getCode() {
            return this.code;
        }

        @Nullable
        public final Map<String, List<String>> component2() {
            return this.headers;
        }

        @Nullable
        /* renamed from: component3  reason: from getter */
        public final String getLocation() {
            return this.location;
        }

        @NotNull
        public final Redirect<T> copy(int code, @Nullable Map<String, ? extends List<String>> headers, @Nullable String location) {
            return new Redirect<>(code, headers, location);
        }

        public boolean equals(@Nullable Object other) {
            if (this != other) {
                if (other instanceof Redirect) {
                    Redirect redirect = (Redirect) other;
                    return this.code == redirect.code && Intrinsics.areEqual(this.headers, redirect.headers) && Intrinsics.areEqual(this.location, redirect.location);
                }
                return false;
            }
            return true;
        }

        public final int getCode() {
            return this.code;
        }

        @Nullable
        public final Map<String, List<String>> getHeaders() {
            return this.headers;
        }

        @Nullable
        public final String getLocation() {
            return this.location;
        }

        public int hashCode() {
            int i2 = this.code * 31;
            Map<String, List<String>> map = this.headers;
            int hashCode = (i2 + (map != null ? map.hashCode() : 0)) * 31;
            String str = this.location;
            return hashCode + (str != null ? str.hashCode() : 0);
        }

        @Override // com.jd.jdcache.service.base.NetState
        @NotNull
        public String toString() {
            return "Redirect(code=" + this.code + ", headers=" + this.headers + ", location=" + this.location + ")";
        }
    }

    private NetState() {
    }

    @NotNull
    public String toString() {
        if (this instanceof OnStart) {
            return "NetResult[OnStart] url: " + ((OnStart) this).getUrl();
        } else if (this instanceof OnProgress) {
            StringBuilder sb = new StringBuilder();
            sb.append("NetResult[OnProgress] ");
            OnProgress onProgress = (OnProgress) this;
            sb.append(onProgress.getProgress());
            sb.append('/');
            sb.append(onProgress.getMax());
            return sb.toString();
        } else if (this instanceof Complete) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("NetResult[Complete, code=");
            Complete complete = (Complete) this;
            sb2.append(complete.getCode());
            sb2.append("] data: ");
            sb2.append(complete.getData());
            return sb2.toString();
        } else if (this instanceof Error) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("NetResult[Error, code=");
            Error error = (Error) this;
            sb3.append(error.getCode());
            sb3.append("] exception: ");
            Throwable throwable = error.getThrowable();
            sb3.append(throwable != null ? throwable.getMessage() : null);
            sb3.append(']');
            return sb3.toString();
        } else if (this instanceof Redirect) {
            StringBuilder sb4 = new StringBuilder();
            sb4.append("NetResult[Redirect, code=");
            Redirect redirect = (Redirect) this;
            sb4.append(redirect.getCode());
            sb4.append("] location: ");
            sb4.append(redirect.getLocation());
            return sb4.toString();
        } else {
            throw new NoWhenBranchMatchedException();
        }
    }

    public /* synthetic */ NetState(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
