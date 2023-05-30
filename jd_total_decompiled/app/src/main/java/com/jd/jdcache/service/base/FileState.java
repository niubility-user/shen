package com.jd.jdcache.service.base;

import androidx.annotation.Keep;
import androidx.core.app.NotificationCompat;
import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.manto.sdk.api.IAudioPlayer;
import java.io.File;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Keep
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0004\u0007\b\t\nB\t\b\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\u0003\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0003\u0010\u0004\u0082\u0001\u0004\u000b\f\r\u000e\u00a8\u0006\u000f"}, d2 = {"Lcom/jd/jdcache/service/base/FileState;", "", "", "toString", "()Ljava/lang/String;", "<init>", "()V", "Complete", IAudioPlayer.AUDIO_STATE_ERROR, "OnProgress", "OnStart", "Lcom/jd/jdcache/service/base/FileState$OnStart;", "Lcom/jd/jdcache/service/base/FileState$OnProgress;", "Lcom/jd/jdcache/service/base/FileState$Error;", "Lcom/jd/jdcache/service/base/FileState$Complete;", "JDCache_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes13.dex */
public abstract class FileState {

    @Keep
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\b\u0087\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0010\u001a\u00020\u0002\u0012\u0006\u0010\u0011\u001a\u00020\u0005\u0012\u001e\u0010\u0012\u001a\u001a\u0012\u0006\u0012\u0004\u0018\u00010\t\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\n\u0018\u00010\b\u0012\u0006\u0010\u0013\u001a\u00020\r\u00a2\u0006\u0004\b&\u0010'J\u0010\u0010\u0003\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0006\u001a\u00020\u0005H\u00c6\u0003\u00a2\u0006\u0004\b\u0006\u0010\u0007J(\u0010\u000b\u001a\u001a\u0012\u0006\u0012\u0004\u0018\u00010\t\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\n\u0018\u00010\bH\u00c6\u0003\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u000e\u001a\u00020\rH\u00c6\u0003\u00a2\u0006\u0004\b\u000e\u0010\u000fJP\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0010\u001a\u00020\u00022\b\b\u0002\u0010\u0011\u001a\u00020\u00052 \b\u0002\u0010\u0012\u001a\u001a\u0012\u0006\u0012\u0004\u0018\u00010\t\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\n\u0018\u00010\b2\b\b\u0002\u0010\u0013\u001a\u00020\rH\u00c6\u0001\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u0016\u001a\u00020\tH\u00d6\u0001\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0018\u001a\u00020\u0002H\u00d6\u0001\u00a2\u0006\u0004\b\u0018\u0010\u0004J\u001a\u0010\u001c\u001a\u00020\u001b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019H\u00d6\u0003\u00a2\u0006\u0004\b\u001c\u0010\u001dR\u0019\u0010\u0013\u001a\u00020\r8\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0013\u0010\u001e\u001a\u0004\b\u001f\u0010\u000fR1\u0010\u0012\u001a\u001a\u0012\u0006\u0012\u0004\u0018\u00010\t\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\n\u0018\u00010\b8\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0012\u0010 \u001a\u0004\b!\u0010\fR\u0019\u0010\u0010\u001a\u00020\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0010\u0010\"\u001a\u0004\b#\u0010\u0004R\u0019\u0010\u0011\u001a\u00020\u00058\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0011\u0010$\u001a\u0004\b%\u0010\u0007\u00a8\u0006("}, d2 = {"Lcom/jd/jdcache/service/base/FileState$Complete;", "Lcom/jd/jdcache/service/base/FileState;", "", "component1", "()I", "", "component2", "()J", "", "", "", "component3", "()Ljava/util/Map;", "Ljava/io/File;", "component4", "()Ljava/io/File;", "code", CartConstant.KEY_LENGTH, "headers", "data", JDViewKitEventHelper.ActionType_Copy, "(IJLjava/util/Map;Ljava/io/File;)Lcom/jd/jdcache/service/base/FileState$Complete;", "toString", "()Ljava/lang/String;", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/io/File;", "getData", "Ljava/util/Map;", "getHeaders", "I", "getCode", "J", "getLength", "<init>", "(IJLjava/util/Map;Ljava/io/File;)V", "JDCache_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes13.dex */
    public static final /* data */ class Complete extends FileState {
        private final int code;
        @NotNull
        private final File data;
        @Nullable
        private final Map<String, List<String>> headers;
        private final long length;

        /* JADX WARN: Multi-variable type inference failed */
        public Complete(int i2, long j2, @Nullable Map<String, ? extends List<String>> map, @NotNull File file) {
            super(null);
            this.code = i2;
            this.length = j2;
            this.headers = map;
            this.data = file;
        }

        public static /* synthetic */ Complete copy$default(Complete complete, int i2, long j2, Map map, File file, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i2 = complete.code;
            }
            if ((i3 & 2) != 0) {
                j2 = complete.length;
            }
            long j3 = j2;
            Map<String, List<String>> map2 = map;
            if ((i3 & 4) != 0) {
                map2 = complete.headers;
            }
            Map map3 = map2;
            if ((i3 & 8) != 0) {
                file = complete.data;
            }
            return complete.copy(i2, j3, map3, file);
        }

        /* renamed from: component1  reason: from getter */
        public final int getCode() {
            return this.code;
        }

        /* renamed from: component2  reason: from getter */
        public final long getLength() {
            return this.length;
        }

        @Nullable
        public final Map<String, List<String>> component3() {
            return this.headers;
        }

        @NotNull
        /* renamed from: component4  reason: from getter */
        public final File getData() {
            return this.data;
        }

        @NotNull
        public final Complete copy(int code, long length, @Nullable Map<String, ? extends List<String>> headers, @NotNull File data) {
            return new Complete(code, length, headers, data);
        }

        public boolean equals(@Nullable Object other) {
            if (this != other) {
                if (other instanceof Complete) {
                    Complete complete = (Complete) other;
                    return this.code == complete.code && this.length == complete.length && Intrinsics.areEqual(this.headers, complete.headers) && Intrinsics.areEqual(this.data, complete.data);
                }
                return false;
            }
            return true;
        }

        public final int getCode() {
            return this.code;
        }

        @NotNull
        public final File getData() {
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
            long j2 = this.length;
            int i2 = ((this.code * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31;
            Map<String, List<String>> map = this.headers;
            int hashCode = (i2 + (map != null ? map.hashCode() : 0)) * 31;
            File file = this.data;
            return hashCode + (file != null ? file.hashCode() : 0);
        }

        @Override // com.jd.jdcache.service.base.FileState
        @NotNull
        public String toString() {
            return "Complete(code=" + this.code + ", length=" + this.length + ", headers=" + this.headers + ", data=" + this.data + ")";
        }
    }

    @Keep
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\b\u0087\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u0003\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0004\b\u0006\u0010\u0007J&\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\b\u001a\u00020\u00022\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005H\u00c6\u0001\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\r\u001a\u00020\fH\u00d6\u0001\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0002H\u00d6\u0001\u00a2\u0006\u0004\b\u000f\u0010\u0004J\u001a\u0010\u0013\u001a\u00020\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u00d6\u0003\u00a2\u0006\u0004\b\u0013\u0010\u0014R\u001b\u0010\t\u001a\u0004\u0018\u00010\u00058\u0006@\u0006\u00a2\u0006\f\n\u0004\b\t\u0010\u0015\u001a\u0004\b\u0016\u0010\u0007R\u0019\u0010\b\u001a\u00020\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\b\u0010\u0017\u001a\u0004\b\u0018\u0010\u0004\u00a8\u0006\u001b"}, d2 = {"Lcom/jd/jdcache/service/base/FileState$Error;", "Lcom/jd/jdcache/service/base/FileState;", "", "component1", "()I", "", "component2", "()Ljava/lang/Throwable;", "code", "throwable", JDViewKitEventHelper.ActionType_Copy, "(ILjava/lang/Throwable;)Lcom/jd/jdcache/service/base/FileState$Error;", "", "toString", "()Ljava/lang/String;", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/Throwable;", "getThrowable", "I", "getCode", "<init>", "(ILjava/lang/Throwable;)V", "JDCache_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes13.dex */
    public static final /* data */ class Error extends FileState {
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

        @Override // com.jd.jdcache.service.base.FileState
        @NotNull
        public String toString() {
            return "Error(code=" + this.code + ", throwable=" + this.throwable + ")";
        }
    }

    @Keep
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0010\u0010\u0003\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0005\u0010\u0004J$\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u0002H\u00c6\u0001\u00a2\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u000b\u001a\u00020\nH\u00d6\u0001\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u000e\u001a\u00020\rH\u00d6\u0001\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u001a\u0010\u0013\u001a\u00020\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u00d6\u0003\u00a2\u0006\u0004\b\u0013\u0010\u0014R\u0019\u0010\u0007\u001a\u00020\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010\u0015\u001a\u0004\b\u0016\u0010\u0004R\u0019\u0010\u0006\u001a\u00020\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u0015\u001a\u0004\b\u0017\u0010\u0004\u00a8\u0006\u001a"}, d2 = {"Lcom/jd/jdcache/service/base/FileState$OnProgress;", "Lcom/jd/jdcache/service/base/FileState;", "", "component1", "()J", "component2", NotificationCompat.CATEGORY_PROGRESS, "max", JDViewKitEventHelper.ActionType_Copy, "(JJ)Lcom/jd/jdcache/service/base/FileState$OnProgress;", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "J", "getMax", "getProgress", "<init>", "(JJ)V", "JDCache_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes13.dex */
    public static final /* data */ class OnProgress extends FileState {
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
        public final OnProgress copy(long progress, long max) {
            return new OnProgress(progress, max);
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

        @Override // com.jd.jdcache.service.base.FileState
        @NotNull
        public String toString() {
            return "OnProgress(progress=" + this.progress + ", max=" + this.max + ")";
        }
    }

    @Keep
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0003\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u001a\u0010\u0006\u001a\u00020\u00002\b\b\u0002\u0010\u0005\u001a\u00020\u0002H\u00c6\u0001\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\u0002H\u00d6\u0001\u00a2\u0006\u0004\b\b\u0010\u0004J\u0010\u0010\n\u001a\u00020\tH\u00d6\u0001\u00a2\u0006\u0004\b\n\u0010\u000bJ\u001a\u0010\u000f\u001a\u00020\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\fH\u00d6\u0003\u00a2\u0006\u0004\b\u000f\u0010\u0010R\u0019\u0010\u0005\u001a\u00020\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u0011\u001a\u0004\b\u0012\u0010\u0004\u00a8\u0006\u0015"}, d2 = {"Lcom/jd/jdcache/service/base/FileState$OnStart;", "Lcom/jd/jdcache/service/base/FileState;", "", "component1", "()Ljava/lang/String;", "url", JDViewKitEventHelper.ActionType_Copy, "(Ljava/lang/String;)Lcom/jd/jdcache/service/base/FileState$OnStart;", "toString", "", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getUrl", "<init>", "(Ljava/lang/String;)V", "JDCache_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes13.dex */
    public static final /* data */ class OnStart extends FileState {
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

        @Override // com.jd.jdcache.service.base.FileState
        @NotNull
        public String toString() {
            return "OnStart(url=" + this.url + ")";
        }
    }

    private FileState() {
    }

    @NotNull
    public String toString() {
        if (this instanceof OnStart) {
            return "FileState[OnStart] url: " + ((OnStart) this).getUrl();
        } else if (this instanceof OnProgress) {
            StringBuilder sb = new StringBuilder();
            sb.append("FileState[OnProgress] ");
            OnProgress onProgress = (OnProgress) this;
            sb.append(onProgress.getProgress());
            sb.append('/');
            sb.append(onProgress.getMax());
            return sb.toString();
        } else if (this instanceof Complete) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("FileState[Complete, code=");
            Complete complete = (Complete) this;
            sb2.append(complete.getCode());
            sb2.append("] path: ");
            sb2.append(complete.getData().getPath());
            return sb2.toString();
        } else if (this instanceof Error) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("FileState[Error, code=");
            Error error = (Error) this;
            sb3.append(error.getCode());
            sb3.append("] exception: ");
            Throwable throwable = error.getThrowable();
            sb3.append(throwable != null ? throwable.getMessage() : null);
            sb3.append(']');
            return sb3.toString();
        } else {
            throw new NoWhenBranchMatchedException();
        }
    }

    public /* synthetic */ FileState(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
