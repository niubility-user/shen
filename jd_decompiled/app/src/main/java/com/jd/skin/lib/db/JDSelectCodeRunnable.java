package com.jd.skin.lib.db;

import com.jd.skin.lib.bean.ResourceItems;
import com.jd.skin.lib.controller.JDSkinDataController;

/* loaded from: classes18.dex */
public class JDSelectCodeRunnable implements Runnable {
    private ResultCallback callback;
    private String clomn;
    private boolean mIsDark;
    private SelectType type;

    /* renamed from: com.jd.skin.lib.db.JDSelectCodeRunnable$2  reason: invalid class name */
    /* loaded from: classes18.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$jd$skin$lib$db$SelectType;

        static {
            int[] iArr = new int[SelectType.values().length];
            $SwitchMap$com$jd$skin$lib$db$SelectType = iArr;
            try {
                iArr[SelectType.CODE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public JDSelectCodeRunnable(SelectType selectType, String str, boolean z, ResultCallback resultCallback) {
        this.type = selectType;
        this.clomn = str;
        this.callback = resultCallback;
        this.mIsDark = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        final ResourceItems queryDataByCode = AnonymousClass2.$SwitchMap$com$jd$skin$lib$db$SelectType[this.type.ordinal()] != 1 ? null : JDSkinDBController.queryDataByCode(this.clomn, this.mIsDark);
        JDSkinDataController.getInstance().getMainHandler().post(new Runnable() { // from class: com.jd.skin.lib.db.JDSelectCodeRunnable.1
            @Override // java.lang.Runnable
            public void run() {
                if (JDSelectCodeRunnable.this.callback != null) {
                    if (queryDataByCode != null) {
                        JDSelectCodeRunnable.this.callback.result(true, queryDataByCode);
                    } else {
                        JDSelectCodeRunnable.this.callback.result(false, queryDataByCode);
                    }
                }
            }
        });
    }
}
