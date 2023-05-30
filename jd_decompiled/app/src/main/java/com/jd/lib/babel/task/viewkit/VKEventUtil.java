package com.jd.lib.babel.task.viewkit;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.jd.lib.babel.servicekit.BabelServer;
import com.jd.lib.babel.servicekit.iservice.IClick;
import com.jd.lib.babel.servicekit.iservice.IEvent;
import com.jd.lib.babel.servicekit.iservice.IFrame;
import com.jd.lib.babel.servicekit.model.BabelJumpEntity;
import com.jd.lib.babel.servicekit.model.MtaData;
import com.jd.viewkit.thirdinterface.model.JDViewKitEventCallBack;
import com.jd.viewkit.thirdinterface.model.JDViewKitEventModel;
import org.json.JSONObject;

/* loaded from: classes14.dex */
public class VKEventUtil {
    public static final String DES_BABEL_BACKPREVIOUSPAGE = "backPreviousPage";
    public static final String DES_BABEL_CALLBACK = "callback";
    public static final String DES_BABEL_CLICKCOPY = "clickCopy";
    public static final String DES_BABEL_COUPON = "freeCoupon";
    public static final String DES_BABEL_FLOOR = "floor";
    public static final int FROM_JDTASK = 1000;
    public static final String JUMP_COPYVALUE = "copyValue";
    public static final String JUMP_ERRTOAST = "errToast";
    public static final String JUMP_MODULEID = "moduleId";
    public static final String JUMP_NEEDLOGIN = "needLogin";
    public static final String JUMP_SUCTOAST = "sucToast";
    public static final int VK_CEVENT_BACKPREVIOUSPAGE = 2;
    public static final int VK_CEVENT_CALLBACK = 1;
    public static final int VK_CEVENT_DEF = 0;

    public static void clickBackPreviousPage(Context context, VKEventModelShell vKEventModelShell) {
        Activity activity;
        if (context instanceof Activity) {
            ((Activity) context).finish();
            return;
        }
        IFrame iFrame = (IFrame) BabelServer.get().getService(IFrame.class);
        if (iFrame == null || (activity = iFrame.getActivity(context)) == null) {
            return;
        }
        activity.finish();
    }

    public static void clickCallBack(Context context, VKEventModelShell vKEventModelShell, JDViewKitEventCallBack jDViewKitEventCallBack) {
        new BabelVKCallBackInteractor(context, vKEventModelShell, jDViewKitEventCallBack).callBack();
    }

    public static int clickEventType(String str, String str2) {
        if (!"callback".equals(str) || TextUtils.isEmpty(str2)) {
            return DES_BABEL_BACKPREVIOUSPAGE.equals(str) ? 2 : 0;
        }
        return 1;
    }

    public static void execJump(Context context, BabelJumpEntity babelJumpEntity, JDViewKitEventCallBack jDViewKitEventCallBack) {
        IClick iClick = (IClick) BabelServer.get().getService(IClick.class);
        if (iClick == null || babelJumpEntity == null) {
            return;
        }
        babelJumpEntity.arg = 1000;
        iClick.execJump(context, babelJumpEntity);
        if (jDViewKitEventCallBack != null) {
            jDViewKitEventCallBack.successCallBack(0, null, null);
        }
    }

    public static VKEventModelShell obtainEventModelShell(JDViewKitEventModel jDViewKitEventModel) {
        return new VKEventModelShell(jDViewKitEventModel);
    }

    public static BabelJumpEntity obtainJump(JDViewKitEventModel jDViewKitEventModel) {
        if (jDViewKitEventModel != null) {
            if (jDViewKitEventModel.getJumpJsonString() != null) {
                try {
                } catch (Exception unused) {
                    return null;
                }
            }
            return new BabelJumpEntity(new JSONObject(jDViewKitEventModel.getJumpJsonString()));
        }
        return null;
    }

    public static void onClick(Context context, MtaData mtaData) {
        IEvent iEvent = (IEvent) BabelServer.get().getService(IEvent.class);
        if (iEvent == null || mtaData == null) {
            return;
        }
        iEvent.onClickMta(context, mtaData);
    }
}
