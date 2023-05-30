package com.jd.cashier.app.jdlibcutter.impl.ui.xview;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import com.jd.cashier.app.jdlibcutter.protocol.callback.CommonCallBack;
import com.jd.cashier.app.jdlibcutter.protocol.ui.xview.IXView;
import com.jd.cashier.app.jdlibcutter.protocol.ui.xview.XViewKey;
import com.jingdong.common.XView.XView;
import com.jingdong.common.XView.XViewEntity;
import com.jingdong.common.XView.XViewHelper;
import java.util.Map;

/* loaded from: classes13.dex */
public class JDXView implements IXView, XViewKey {
    private JDXViewImpl mXViewImpl = null;

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.xview.IXView
    public void onDestroy() {
        JDXViewImpl jDXViewImpl = this.mXViewImpl;
        if (jDXViewImpl != null) {
            jDXViewImpl.onDestroy();
            this.mXViewImpl = null;
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.xview.IXView
    public void showXView(Activity activity, View view, Map<String, String> map, CommonCallBack<String> commonCallBack, CommonCallBack<String> commonCallBack2, CommonCallBack<String> commonCallBack3) {
        XView createXView;
        if (activity != null) {
            try {
                if (activity.isFinishing() || map == null || map.isEmpty()) {
                    return;
                }
                this.mXViewImpl = new JDXViewImpl(view, commonCallBack, commonCallBack2, commonCallBack3);
                XViewEntity xViewEntity = new XViewEntity();
                xViewEntity.url = map.get("url");
                xViewEntity.needCloseButton = !TextUtils.equals("0", map.get(XViewKey.needCloseBtn));
                xViewEntity.needAutoDisplay = false;
                View findViewById = activity.getWindow().getDecorView().findViewById(16908290);
                if (!(findViewById instanceof ViewGroup) || (createXView = XViewHelper.createXView(activity, (ViewGroup) findViewById, activity.getClass().getSimpleName(), xViewEntity, this.mXViewImpl)) == null) {
                    return;
                }
                createXView.startXView();
                this.mXViewImpl.setXView(createXView);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
