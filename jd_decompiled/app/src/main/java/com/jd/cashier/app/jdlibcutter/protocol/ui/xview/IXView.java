package com.jd.cashier.app.jdlibcutter.protocol.ui.xview;

import android.app.Activity;
import android.view.View;
import com.jd.cashier.app.jdlibcutter.protocol.callback.CommonCallBack;
import java.util.Map;

/* loaded from: classes13.dex */
public interface IXView {
    void onDestroy();

    void showXView(Activity activity, View view, Map<String, String> map, CommonCallBack<String> commonCallBack, CommonCallBack<String> commonCallBack2, CommonCallBack<String> commonCallBack3);
}
