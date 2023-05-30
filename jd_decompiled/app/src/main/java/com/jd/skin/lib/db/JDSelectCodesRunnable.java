package com.jd.skin.lib.db;

import com.jd.skin.lib.bean.ResourceItems;
import com.jd.skin.lib.controller.JDSkinDataController;
import java.util.List;
import java.util.Map;

/* loaded from: classes18.dex */
public class JDSelectCodesRunnable implements Runnable {
    private ResultListCallback callback;
    private List<String> clomn;
    private boolean mIsDark;
    private SelectType type;

    /* renamed from: com.jd.skin.lib.db.JDSelectCodesRunnable$2  reason: invalid class name */
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

    public JDSelectCodesRunnable(SelectType selectType, List<String> list, boolean z, ResultListCallback resultListCallback) {
        this.type = selectType;
        this.clomn = list;
        this.callback = resultListCallback;
        this.mIsDark = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        final Map<String, ResourceItems> queryDataByCodes = AnonymousClass2.$SwitchMap$com$jd$skin$lib$db$SelectType[this.type.ordinal()] != 1 ? null : JDSkinDBController.queryDataByCodes(this.clomn, this.mIsDark);
        JDSkinDataController.getInstance().getMainHandler().post(new Runnable() { // from class: com.jd.skin.lib.db.JDSelectCodesRunnable.1
            @Override // java.lang.Runnable
            public void run() {
                if (JDSelectCodesRunnable.this.callback != null) {
                    if (queryDataByCodes != null) {
                        JDSelectCodesRunnable.this.callback.result(true, queryDataByCodes);
                    } else {
                        JDSelectCodesRunnable.this.callback.result(false, queryDataByCodes);
                    }
                }
            }
        });
    }
}
