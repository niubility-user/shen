package com.tencent.smtt.sdk;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.common.messagecenter.NotificationMessageSummary;
import com.tencent.smtt.export.external.DexLoader;

/* loaded from: classes9.dex */
public class TbsVideoCacheTask {
    public static final String KEY_VIDEO_CACHE_PARAM_FILENAME = "filename";
    public static final String KEY_VIDEO_CACHE_PARAM_FOLDERPATH = "folderPath";
    public static final String KEY_VIDEO_CACHE_PARAM_HEADER = "header";
    public static final String KEY_VIDEO_CACHE_PARAM_URL = "url";
    Context a;
    TbsVideoCacheListener b;

    /* renamed from: e  reason: collision with root package name */
    private String f17779e;

    /* renamed from: f  reason: collision with root package name */
    private String f17780f;

    /* renamed from: c  reason: collision with root package name */
    private boolean f17778c = false;
    private o d = null;

    /* renamed from: g  reason: collision with root package name */
    private Object f17781g = null;

    public TbsVideoCacheTask(Context context, Bundle bundle, TbsVideoCacheListener tbsVideoCacheListener) {
        this.a = null;
        this.b = null;
        this.a = context;
        this.b = tbsVideoCacheListener;
        if (bundle != null) {
            this.f17779e = bundle.getString(NotificationMessageSummary.TASK_ID);
            this.f17780f = bundle.getString("url");
        }
        a(bundle);
    }

    private void a(Bundle bundle) {
        TbsVideoCacheListener tbsVideoCacheListener;
        String str;
        DexLoader dexLoader;
        if (this.d == null) {
            f.a(true).a(this.a, false, false);
            s a = f.a(true).a();
            if (a != null) {
                dexLoader = a.c();
            } else {
                this.b.onVideoDownloadError(this, -1, "init engine error!", null);
                dexLoader = null;
            }
            if (dexLoader != null) {
                this.d = new o(dexLoader);
            } else {
                this.b.onVideoDownloadError(this, -1, "Java dexloader invalid!", null);
            }
        }
        o oVar = this.d;
        if (oVar != null) {
            Object a2 = oVar.a(this.a, this, bundle);
            this.f17781g = a2;
            if (a2 != null) {
                return;
            }
            tbsVideoCacheListener = this.b;
            str = "init task error!";
        } else {
            tbsVideoCacheListener = this.b;
            if (tbsVideoCacheListener == null) {
                return;
            }
            str = "init error!";
        }
        tbsVideoCacheListener.onVideoDownloadError(this, -1, str, null);
    }

    public long getContentLength() {
        o oVar = this.d;
        if (oVar == null || this.f17781g == null) {
            TbsVideoCacheListener tbsVideoCacheListener = this.b;
            if (tbsVideoCacheListener != null) {
                tbsVideoCacheListener.onVideoDownloadError(this, -1, "getContentLength failed, init uncompleted!", null);
                return 0L;
            }
            return 0L;
        }
        return oVar.d();
    }

    public int getDownloadedSize() {
        o oVar = this.d;
        if (oVar == null || this.f17781g == null) {
            TbsVideoCacheListener tbsVideoCacheListener = this.b;
            if (tbsVideoCacheListener != null) {
                tbsVideoCacheListener.onVideoDownloadError(this, -1, "getDownloadedSize failed, init uncompleted!", null);
                return 0;
            }
            return 0;
        }
        return oVar.e();
    }

    public int getProgress() {
        o oVar = this.d;
        if (oVar == null || this.f17781g == null) {
            TbsVideoCacheListener tbsVideoCacheListener = this.b;
            if (tbsVideoCacheListener != null) {
                tbsVideoCacheListener.onVideoDownloadError(this, -1, "getProgress failed, init uncompleted!", null);
                return 0;
            }
            return 0;
        }
        return oVar.f();
    }

    public String getTaskID() {
        return this.f17779e;
    }

    public String getTaskUrl() {
        return this.f17780f;
    }

    public void pauseTask() {
        o oVar = this.d;
        if (oVar != null && this.f17781g != null) {
            oVar.a();
            return;
        }
        TbsVideoCacheListener tbsVideoCacheListener = this.b;
        if (tbsVideoCacheListener != null) {
            tbsVideoCacheListener.onVideoDownloadError(this, -1, "pauseTask failed, init uncompleted!", null);
        }
    }

    public void removeTask(boolean z) {
        o oVar = this.d;
        if (oVar != null && this.f17781g != null) {
            oVar.a(z);
            return;
        }
        TbsVideoCacheListener tbsVideoCacheListener = this.b;
        if (tbsVideoCacheListener != null) {
            tbsVideoCacheListener.onVideoDownloadError(this, -1, "removeTask failed, init uncompleted!", null);
        }
    }

    public void resumeTask() {
        o oVar = this.d;
        if (oVar != null && this.f17781g != null) {
            oVar.b();
            return;
        }
        TbsVideoCacheListener tbsVideoCacheListener = this.b;
        if (tbsVideoCacheListener != null) {
            tbsVideoCacheListener.onVideoDownloadError(this, -1, "resumeTask failed, init uncompleted!", null);
        }
    }

    public void stopTask() {
        o oVar = this.d;
        if (oVar != null && this.f17781g != null) {
            oVar.c();
            return;
        }
        TbsVideoCacheListener tbsVideoCacheListener = this.b;
        if (tbsVideoCacheListener != null) {
            tbsVideoCacheListener.onVideoDownloadError(this, -1, "stopTask failed, init uncompleted!", null);
        }
    }
}
