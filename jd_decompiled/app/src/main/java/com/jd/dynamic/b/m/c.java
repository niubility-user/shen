package com.jd.dynamic.b.m;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.lib.utils.t;
import java.io.File;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes13.dex */
public final class c extends h {
    private Handler a;

    /* loaded from: classes13.dex */
    public final class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        private final k f1812g;

        public a(c cVar, @NotNull k kVar) {
            this.f1812g = kVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.f1812g.a();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* loaded from: classes13.dex */
    public final class b implements j {
        @NotNull
        private final String a;

        public b() {
            DynamicSdk.Engine engine = DynamicSdk.getEngine();
            Intrinsics.checkExpressionValueIsNotNull(engine, "DynamicSdk.getEngine()");
            Context context = engine.getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "DynamicSdk.getEngine().context");
            PackageManager packageManager = context.getPackageManager();
            DynamicSdk.Engine engine2 = DynamicSdk.getEngine();
            Intrinsics.checkExpressionValueIsNotNull(engine2, "DynamicSdk.getEngine()");
            Context context2 = engine2.getContext();
            Intrinsics.checkExpressionValueIsNotNull(context2, "DynamicSdk.getEngine().context");
            String str = packageManager.getPackageInfo(context2.getPackageName(), 0).versionName;
            Intrinsics.checkExpressionValueIsNotNull(str, "DynamicSdk.getEngine().c\u2026ackageName,0).versionName");
            this.a = str;
        }

        @Override // com.jd.dynamic.b.m.j
        public boolean a(@NotNull File file) {
            t.e("DYAppVersionFilter", "file is " + file.getName() + ",app version is " + this.a + ",is equals " + TextUtils.equals(file.getName(), this.a));
            return file.isDirectory() && !TextUtils.equals(file.getName(), this.a);
        }
    }

    /* renamed from: com.jd.dynamic.b.m.c$c  reason: collision with other inner class name */
    /* loaded from: classes13.dex */
    public final class C0074c implements j {
        @Override // com.jd.dynamic.b.m.j
        public boolean a(@NotNull File file) {
            return file.length() == 0;
        }
    }

    /* loaded from: classes13.dex */
    public final class d implements j {
        private final String a;

        public d(@NotNull String str) {
            this.a = str;
        }

        @Override // com.jd.dynamic.b.m.j
        public boolean a(@NotNull File file) {
            boolean startsWith$default;
            String name = file.getName();
            Intrinsics.checkExpressionValueIsNotNull(name, "file.name");
            startsWith$default = StringsKt__StringsJVMKt.startsWith$default(name, this.a, false, 2, null);
            return !startsWith$default;
        }
    }

    /* loaded from: classes13.dex */
    public final class e implements j {
        private final long a;
        private final long b;

        public e(long j2, long j3) {
            this.a = j2;
            this.b = j3;
        }

        @Override // com.jd.dynamic.b.m.j
        public boolean a(@NotNull File file) {
            return file.lastModified() + this.b < this.a;
        }
    }

    public c(@NotNull String str) {
        super(str);
        HandlerThread handlerThread = new HandlerThread(str);
        handlerThread.start();
        this.a = new Handler(handlerThread.getLooper());
    }

    @Override // com.jd.dynamic.b.m.h
    public void a(@NotNull k kVar) {
        this.a.post(new a(this, kVar));
    }
}
