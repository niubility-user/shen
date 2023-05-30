package com.jingdong.content.component.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import com.huawei.hms.support.api.entity.core.CommonCode;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.sdk.oklog.OKLog;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes12.dex */
public final class VolumeChangeHelper {
    private VolumeBroadCastReceiver a;
    private a b;

    /* renamed from: c  reason: collision with root package name */
    private int f12572c;
    private AudioManager d;
    @NotNull

    /* renamed from: e  reason: collision with root package name */
    private Context f12573e;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\t\u0010\nJ#\u0010\u0007\u001a\u00020\u00062\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0016\u00a2\u0006\u0004\b\u0007\u0010\b\u00a8\u0006\u000b"}, d2 = {"Lcom/jingdong/content/component/util/VolumeChangeHelper$VolumeBroadCastReceiver;", "Landroid/content/BroadcastReceiver;", "Landroid/content/Context;", AnnoConst.Constructor_Context, "Landroid/content/Intent;", CommonCode.Resolution.HAS_RESOLUTION_FROM_APK, "", "onReceive", "(Landroid/content/Context;Landroid/content/Intent;)V", "<init>", "(Lcom/jingdong/content/component/util/VolumeChangeHelper;)V", "content-component-widget_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes12.dex */
    public final class VolumeBroadCastReceiver extends BroadcastReceiver {
        public VolumeBroadCastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(@Nullable Context context, @Nullable Intent intent) {
            a aVar;
            if (Intrinsics.areEqual(intent != null ? intent.getAction() : null, "android.media.VOLUME_CHANGED_ACTION")) {
                if (intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_TYPE", -1) == 3) {
                    AudioManager audioManager = VolumeChangeHelper.this.d;
                    int streamVolume = audioManager != null ? audioManager.getStreamVolume(3) : -1;
                    if (OKLog.D) {
                        OKLog.d("VolumeBroadCastReceiver", "currentVolume:" + VolumeChangeHelper.this.f12572c);
                    }
                    if (streamVolume > VolumeChangeHelper.this.f12572c) {
                        a aVar2 = VolumeChangeHelper.this.b;
                        if (aVar2 != null) {
                            aVar2.onVolumeUp();
                        }
                    } else if (VolumeChangeHelper.this.f12572c == 0 && (aVar = VolumeChangeHelper.this.b) != null) {
                        aVar.onVolumeDownToMin();
                    }
                    VolumeChangeHelper.this.f12572c = streamVolume;
                }
            }
        }
    }

    /* loaded from: classes12.dex */
    public interface a {
        void onVolumeDownToMin();

        void onVolumeUp();
    }

    public VolumeChangeHelper(@NotNull Context context) {
        this.f12573e = context;
        Object systemService = context.getSystemService("audio");
        this.d = (AudioManager) (systemService instanceof AudioManager ? systemService : null);
    }

    public final void e(@NotNull a aVar) {
        this.b = aVar;
        if (this.a == null) {
            this.a = new VolumeBroadCastReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.media.VOLUME_CHANGED_ACTION");
            VolumeBroadCastReceiver volumeBroadCastReceiver = this.a;
            if (volumeBroadCastReceiver != null) {
                this.f12573e.registerReceiver(volumeBroadCastReceiver, intentFilter);
            }
        }
        AudioManager audioManager = this.d;
        this.f12572c = audioManager != null ? audioManager.getStreamVolume(3) : 0;
    }

    public final void f() {
        VolumeBroadCastReceiver volumeBroadCastReceiver = this.a;
        if (volumeBroadCastReceiver != null) {
            Context context = this.f12573e;
            if (volumeBroadCastReceiver == null) {
                Intrinsics.throwNpe();
            }
            context.unregisterReceiver(volumeBroadCastReceiver);
            this.a = null;
        }
    }
}
