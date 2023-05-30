package com.tencent.smtt.sdk;

import android.content.Context;
import android.os.Handler;
import com.tencent.smtt.sdk.TbsReaderView;
import java.util.LinkedList;

/* loaded from: classes9.dex */
public class TbsReaderPredownload {
    public static final int READER_SO_SUCCESS = 2;
    public static final int READER_WAIT_IN_QUEUE = 3;
    static final String[] b = {"docx", "pptx", "xlsx", "pdf", "epub", "txt"};
    Handler a = null;

    /* renamed from: c  reason: collision with root package name */
    LinkedList<String> f17768c = new LinkedList<>();
    boolean d = false;

    /* renamed from: e  reason: collision with root package name */
    ReaderWizard f17769e = null;

    /* renamed from: f  reason: collision with root package name */
    TbsReaderView.ReaderCallback f17770f = null;

    /* renamed from: g  reason: collision with root package name */
    Object f17771g = null;

    /* renamed from: h  reason: collision with root package name */
    Context f17772h = null;

    /* renamed from: i  reason: collision with root package name */
    ReaderPreDownloadCallback f17773i = null;

    /* renamed from: j  reason: collision with root package name */
    String f17774j = "";

    /* loaded from: classes9.dex */
    public interface ReaderPreDownloadCallback {
        public static final int NOTIFY_PLUGIN_FAILED = -1;
        public static final int NOTIFY_PLUGIN_SUCCESS = 0;

        void onEvent(String str, int i2, boolean z);
    }

    public TbsReaderPredownload(ReaderPreDownloadCallback readerPreDownloadCallback) {
        throw new IllegalAccessError("Current SDK not support Reader.");
    }

    private void a() {
        b(3);
    }

    void a(int i2) {
        if (this.f17773i != null) {
            this.f17773i.onEvent(this.f17774j, i2, this.f17768c.isEmpty());
        }
    }

    void a(int i2, int i3) {
        this.a.sendMessageDelayed(this.a.obtainMessage(i2), i3);
    }

    void b(int i2) {
        this.a.removeMessages(i2);
    }

    boolean c(int i2) {
        return this.a.hasMessages(i2);
    }

    public boolean init(Context context) {
        if (context == null) {
            return false;
        }
        this.f17772h = context.getApplicationContext();
        boolean a = TbsReaderView.a(context.getApplicationContext());
        TbsReaderView.ReaderCallback readerCallback = new TbsReaderView.ReaderCallback() { // from class: com.tencent.smtt.sdk.TbsReaderPredownload.1
            @Override // com.tencent.smtt.sdk.TbsReaderView.ReaderCallback
            public void onCallBackAction(Integer num, Object obj, Object obj2) {
                int intValue;
                if (num.intValue() == 5012 && 5014 != (intValue = ((Integer) obj).intValue())) {
                    if (5013 == intValue || intValue == 0) {
                        TbsReaderPredownload.this.a(0);
                    } else {
                        TbsReaderPredownload.this.a(-1);
                    }
                    TbsReaderPredownload tbsReaderPredownload = TbsReaderPredownload.this;
                    tbsReaderPredownload.f17774j = "";
                    tbsReaderPredownload.a(3, 100);
                }
            }
        };
        this.f17770f = readerCallback;
        try {
            if (this.f17769e == null) {
                this.f17769e = new ReaderWizard(readerCallback);
            }
            if (this.f17771g == null) {
                this.f17771g = this.f17769e.getTbsReader();
            }
            Object obj = this.f17771g;
            return obj != null ? this.f17769e.initTbsReader(obj, context.getApplicationContext()) : a;
        } catch (NullPointerException unused) {
            return false;
        }
    }

    public void pause() {
        this.d = true;
    }

    public void shutdown() {
        this.f17773i = null;
        this.d = false;
        this.f17768c.clear();
        a();
        ReaderWizard readerWizard = this.f17769e;
        if (readerWizard != null) {
            readerWizard.destroy(this.f17771g);
            this.f17771g = null;
        }
        this.f17772h = null;
    }

    public void start(String str) {
        this.d = false;
        b(3);
        this.f17768c.add(str);
        a(3, 100);
    }

    public void startAll() {
        this.d = false;
        if (!false && !c(3)) {
            a(3, 100);
        }
    }
}
