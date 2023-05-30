package com.jingdong.sdk.lib.puppetlayout.ylayout;

import android.text.TextUtils;
import com.jingdong.sdk.lib.puppetlayout.ylayout.model.Action;
import java.util.ArrayList;

/* loaded from: classes8.dex */
public class ActionProcessor {
    private boolean canHandle(PuppetContext puppetContext, String str) {
        if (!TextUtils.isEmpty(str)) {
            if (puppetContext.isEditing) {
                if (!"0".equals(str)) {
                    if ("1".equals(str)) {
                        return false;
                    }
                    if (!"2".equals(str) && "3".equals(str)) {
                        return false;
                    }
                }
            } else if (!"0".equals(str)) {
                if ("1".equals(str) || "2".equals(str)) {
                    return false;
                }
                "3".equals(str);
            }
        }
        return true;
    }

    public void process(PuppetContext puppetContext, ArrayList<Action> arrayList, boolean z) {
        if (arrayList == null || arrayList.size() == 0) {
            return;
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            Action action = arrayList.get(i2);
            if (action != null && canHandle(puppetContext, action.getHiddenType())) {
                if (z) {
                    if (action.isExpoType()) {
                        puppetContext.manager.handle("action", action);
                    }
                } else if (!action.isExpoType()) {
                    puppetContext.manager.handle("action", action);
                }
            }
        }
    }

    public void processCountdownAction(PuppetContext puppetContext, ArrayList<Action> arrayList) {
        if (puppetContext == null || arrayList == null || arrayList.size() == 0) {
            return;
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            Action action = arrayList.get(i2);
            if (action != null) {
                puppetContext.manager.handle("action", action);
            }
        }
    }
}
