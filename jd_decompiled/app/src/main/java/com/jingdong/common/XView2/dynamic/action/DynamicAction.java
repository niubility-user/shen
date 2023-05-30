package com.jingdong.common.XView2.dynamic.action;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.XView2.dynamic.context.ExpressContextDelegate;
import com.jingdong.common.XView2.entity.dynamic.ActionEntity;
import com.jingdong.common.XView2.utils.XView2Utils;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes5.dex */
public class DynamicAction {
    private DynamicActionDelegate mActionDelegate;
    private Context mContext;
    private ExpressContextDelegate mExpressDelegate;

    /* loaded from: classes5.dex */
    public interface IDynamicDataCallBack<T> {
        void fail(String str);

        void success(T t);
    }

    public DynamicAction(Context context, DynamicActionDelegate dynamicActionDelegate, ExpressContextDelegate expressContextDelegate) {
        this.mContext = context;
        this.mActionDelegate = dynamicActionDelegate;
        this.mExpressDelegate = expressContextDelegate;
    }

    public String caculateValue(String str) {
        if ((str instanceof String) && str.trim().startsWith("$")) {
            String substring = str.substring(1);
            ExpressContextDelegate expressContextDelegate = this.mExpressDelegate;
            if (expressContextDelegate != null) {
                expressContextDelegate.runScript(substring);
            }
            return "";
        }
        return str;
    }

    public void executeActionList(ArrayList<ActionEntity> arrayList) {
        JDJSONObject jDJSONObject;
        if (this.mActionDelegate == null || arrayList == null || !XView2Utils.isListEmpty(arrayList)) {
            return;
        }
        Iterator<ActionEntity> it = arrayList.iterator();
        while (it.hasNext()) {
            final ActionEntity next = it.next();
            if ("request".equals(next.name)) {
                JDJSONObject jDJSONObject2 = next.params;
                if (jDJSONObject2 != null) {
                    String optString = jDJSONObject2.optString("url");
                    JDJSONObject optJSONObject = next.params.optJSONObject("params");
                    JDJSONObject optJSONObject2 = next.params.optJSONObject(XView2Constants.XVIEW2_REQUEST_ACTION_OPS);
                    String optString2 = next.params.optString(XView2Constants.XVIEW2_REQUEST_ACTION_FUNCNAME);
                    if (!TextUtils.isEmpty(optString)) {
                        this.mActionDelegate.requestUrl(optString, optJSONObject, optJSONObject2);
                    } else if (!TextUtils.isEmpty(optString2)) {
                        this.mActionDelegate.requestUrl(optString, optJSONObject, optJSONObject2);
                    }
                    if (!XView2Utils.isListEmpty(next.success)) {
                        executeActionList(next.success);
                    }
                    if (!XView2Utils.isListEmpty(next.fail)) {
                        executeActionList(next.fail);
                    }
                }
            } else if (!"status".equals(next.name)) {
                if (XView2Constants.XVIEW2_ACTION_TOAST.equals(next.name)) {
                    JDJSONObject jDJSONObject3 = next.params;
                    if (jDJSONObject3 != null && jDJSONObject3.size() > 0) {
                        this.mActionDelegate.showToastTitle(this.mContext, next.params.optString("title"), next.params.optString("subTitle"), next.params.optInt("status"));
                        if (!XView2Utils.isListEmpty(next.success)) {
                            executeActionList(next.success);
                        }
                    }
                } else if (XView2Constants.XVIEW2_ACTION_DIALOG.equals(next.name) && (jDJSONObject = next.params) != null && jDJSONObject.size() > 0) {
                    this.mActionDelegate.showDialogTitle(next.params.optString("title"), next.params.optString("subTitle"), next.params.optString(XView2Constants.XVIEW2_DIALOG_ACTION_OKTXT), next.params.optString(XView2Constants.XVIEW2_DIALOG_ACTION_CANCELTEXT), new View.OnClickListener() { // from class: com.jingdong.common.XView2.dynamic.action.DynamicAction.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                        }
                    }, new View.OnClickListener() { // from class: com.jingdong.common.XView2.dynamic.action.DynamicAction.2
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            if (XView2Utils.isListEmpty(next.success)) {
                                return;
                            }
                            DynamicAction.this.executeActionList(next.success);
                        }
                    });
                }
            }
        }
    }
}
