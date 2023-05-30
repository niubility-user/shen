package com.jd.jdcache.service.base;

import androidx.annotation.Keep;
import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import com.jingdong.manto.sdk.api.IAudioPlayer;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Keep
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0003\u0007\b\tB\t\b\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\u0003\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0003\u0010\u0004\u0082\u0001\u0003\n\u000b\f\u00a8\u0006\r"}, d2 = {"Lcom/jd/jdcache/service/base/InputStreamState;", "", "", "toString", "()Ljava/lang/String;", "<init>", "()V", "Connected", IAudioPlayer.AUDIO_STATE_ERROR, "OnStart", "Lcom/jd/jdcache/service/base/InputStreamState$OnStart;", "Lcom/jd/jdcache/service/base/InputStreamState$Error;", "Lcom/jd/jdcache/service/base/InputStreamState$Connected;", "JDCache_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes13.dex */
public abstract class InputStreamState {

    @Keep
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\b\u0087\b\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\r\u001a\u00020\u0002\u0012\u001e\u0010\u000e\u001a\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0007\u0018\u00010\u0005\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\n\u00a2\u0006\u0004\b \u0010!J\u0010\u0010\u0003\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0003\u0010\u0004J(\u0010\b\u001a\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0007\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0004\b\b\u0010\tJ\u0012\u0010\u000b\u001a\u0004\u0018\u00010\nH\u00c6\u0003\u00a2\u0006\u0004\b\u000b\u0010\fJH\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\r\u001a\u00020\u00022 \b\u0002\u0010\u000e\u001a\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0007\u0018\u00010\u00052\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\nH\u00c6\u0001\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u0006H\u00d6\u0001\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0014\u001a\u00020\u0002H\u00d6\u0001\u00a2\u0006\u0004\b\u0014\u0010\u0004J\u001a\u0010\u0018\u001a\u00020\u00172\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015H\u00d6\u0003\u00a2\u0006\u0004\b\u0018\u0010\u0019R1\u0010\u000e\u001a\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0007\u0018\u00010\u00058\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u000e\u0010\u001a\u001a\u0004\b\u001b\u0010\tR\u0019\u0010\r\u001a\u00020\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\r\u0010\u001c\u001a\u0004\b\u001d\u0010\u0004R\u001b\u0010\u000f\u001a\u0004\u0018\u00010\n8\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u000f\u0010\u001e\u001a\u0004\b\u001f\u0010\f\u00a8\u0006\""}, d2 = {"Lcom/jd/jdcache/service/base/InputStreamState$Connected;", "Lcom/jd/jdcache/service/base/InputStreamState;", "", "component1", "()I", "", "", "", "component2", "()Ljava/util/Map;", "Ljava/io/InputStream;", "component3", "()Ljava/io/InputStream;", "code", "headers", "data", JDViewKitEventHelper.ActionType_Copy, "(ILjava/util/Map;Ljava/io/InputStream;)Lcom/jd/jdcache/service/base/InputStreamState$Connected;", "toString", "()Ljava/lang/String;", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/util/Map;", "getHeaders", "I", "getCode", "Ljava/io/InputStream;", "getData", "<init>", "(ILjava/util/Map;Ljava/io/InputStream;)V", "JDCache_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes13.dex */
    public static final /* data */ class Connected extends InputStreamState {
        private final int code;
        @Nullable
        private final InputStream data;
        @Nullable
        private final Map<String, List<String>> headers;

        /* JADX WARN: Multi-variable type inference failed */
        public Connected(int i2, @Nullable Map<String, ? extends List<String>> map, @Nullable InputStream inputStream) {
            super(null);
            this.code = i2;
            this.headers = map;
            this.data = inputStream;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Connected copy$default(Connected connected, int i2, Map map, InputStream inputStream, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i2 = connected.code;
            }
            if ((i3 & 2) != 0) {
                map = connected.headers;
            }
            if ((i3 & 4) != 0) {
                inputStream = connected.data;
            }
            return connected.copy(i2, map, inputStream);
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
        public final InputStream getData() {
            return this.data;
        }

        @NotNull
        public final Connected copy(int code, @Nullable Map<String, ? extends List<String>> headers, @Nullable InputStream data) {
            return new Connected(code, headers, data);
        }

        public boolean equals(@Nullable Object other) {
            if (this != other) {
                if (other instanceof Connected) {
                    Connected connected = (Connected) other;
                    return this.code == connected.code && Intrinsics.areEqual(this.headers, connected.headers) && Intrinsics.areEqual(this.data, connected.data);
                }
                return false;
            }
            return true;
        }

        public final int getCode() {
            return this.code;
        }

        @Nullable
        public final InputStream getData() {
            return this.data;
        }

        @Nullable
        public final Map<String, List<String>> getHeaders() {
            return this.headers;
        }

        public int hashCode() {
            int i2 = this.code * 31;
            Map<String, List<String>> map = this.headers;
            int hashCode = (i2 + (map != null ? map.hashCode() : 0)) * 31;
            InputStream inputStream = this.data;
            return hashCode + (inputStream != null ? inputStream.hashCode() : 0);
        }

        @Override // com.jd.jdcache.service.base.InputStreamState
        @NotNull
        public String toString() {
            return "Connected(code=" + this.code + ", headers=" + this.headers + ", data=" + this.data + ")";
        }
    }

    @Keep
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\b\u0087\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u0003\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0004\b\u0006\u0010\u0007J&\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\b\u001a\u00020\u00022\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005H\u00c6\u0001\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\r\u001a\u00020\fH\u00d6\u0001\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0002H\u00d6\u0001\u00a2\u0006\u0004\b\u000f\u0010\u0004J\u001a\u0010\u0013\u001a\u00020\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u00d6\u0003\u00a2\u0006\u0004\b\u0013\u0010\u0014R\u0019\u0010\b\u001a\u00020\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\b\u0010\u0015\u001a\u0004\b\u0016\u0010\u0004R\u001b\u0010\t\u001a\u0004\u0018\u00010\u00058\u0006@\u0006\u00a2\u0006\f\n\u0004\b\t\u0010\u0017\u001a\u0004\b\u0018\u0010\u0007\u00a8\u0006\u001b"}, d2 = {"Lcom/jd/jdcache/service/base/InputStreamState$Error;", "Lcom/jd/jdcache/service/base/InputStreamState;", "", "component1", "()I", "", "component2", "()Ljava/lang/Throwable;", "code", "throwable", JDViewKitEventHelper.ActionType_Copy, "(ILjava/lang/Throwable;)Lcom/jd/jdcache/service/base/InputStreamState$Error;", "", "toString", "()Ljava/lang/String;", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "I", "getCode", "Ljava/lang/Throwable;", "getThrowable", "<init>", "(ILjava/lang/Throwable;)V", "JDCache_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes13.dex */
    public static final /* data */ class Error extends InputStreamState {
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
        public final Error copy(int code, @Nullable Throwable throwable) {
            return new Error(code, throwable);
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

        @Override // com.jd.jdcache.service.base.InputStreamState
        @NotNull
        public String toString() {
            return "Error(code=" + this.code + ", throwable=" + this.throwable + ")";
        }
    }

    @Keep
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0003\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u001a\u0010\u0006\u001a\u00020\u00002\b\b\u0002\u0010\u0005\u001a\u00020\u0002H\u00c6\u0001\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\u0002H\u00d6\u0001\u00a2\u0006\u0004\b\b\u0010\u0004J\u0010\u0010\n\u001a\u00020\tH\u00d6\u0001\u00a2\u0006\u0004\b\n\u0010\u000bJ\u001a\u0010\u000f\u001a\u00020\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\fH\u00d6\u0003\u00a2\u0006\u0004\b\u000f\u0010\u0010R\u0019\u0010\u0005\u001a\u00020\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u0011\u001a\u0004\b\u0012\u0010\u0004\u00a8\u0006\u0015"}, d2 = {"Lcom/jd/jdcache/service/base/InputStreamState$OnStart;", "Lcom/jd/jdcache/service/base/InputStreamState;", "", "component1", "()Ljava/lang/String;", "url", JDViewKitEventHelper.ActionType_Copy, "(Ljava/lang/String;)Lcom/jd/jdcache/service/base/InputStreamState$OnStart;", "toString", "", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getUrl", "<init>", "(Ljava/lang/String;)V", "JDCache_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes13.dex */
    public static final /* data */ class OnStart extends InputStreamState {
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
        public final OnStart copy(@NotNull String url) {
            return new OnStart(url);
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

        @Override // com.jd.jdcache.service.base.InputStreamState
        @NotNull
        public String toString() {
            return "OnStart(url=" + this.url + ")";
        }
    }

    private InputStreamState() {
    }

    @NotNull
    public String toString() {
        if (this instanceof Connected) {
            return "InputStreamState[Connected, code=" + ((Connected) this).getCode() + ']';
        } else if (!(this instanceof Error)) {
            if (this instanceof OnStart) {
                return "InputStreamState[OnStart]";
            }
            throw new NoWhenBranchMatchedException();
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("InputStreamState[Error, code=");
            Error error = (Error) this;
            sb.append(error.getCode());
            sb.append("] exception: ");
            Throwable throwable = error.getThrowable();
            sb.append(throwable != null ? throwable.getMessage() : null);
            sb.append(']');
            return sb.toString();
        }
    }

    public /* synthetic */ InputStreamState(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
