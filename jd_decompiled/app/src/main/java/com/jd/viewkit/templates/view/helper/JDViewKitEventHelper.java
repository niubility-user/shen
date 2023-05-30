package com.jd.viewkit.templates.view.helper;

import android.view.View;
import android.view.ViewParent;
import com.jd.viewkit.dataSources.model.JDViewKitDataSourceModel;
import com.jd.viewkit.helper.JDViewKitChannelModel;
import com.jd.viewkit.templates.model.JDViewKitVirtualView;
import com.jd.viewkit.templates.model.event.JDViewKitVirtualChainEvent;
import com.jd.viewkit.templates.model.event.JDViewKitVirtualEvent;
import com.jd.viewkit.templates.view.JDViewKitCountdownView;
import com.jd.viewkit.templates.view.JDViewKitMultistateView;
import com.jd.viewkit.templates.view.helper.eventcallback.JDViewKitEventAbstractCallBack;
import com.jd.viewkit.thirdinterface.model.JDViewKitMtaModel;
import com.jd.viewkit.tool.JSONTool;

/* loaded from: classes18.dex */
public class JDViewKitEventHelper {
    public static final String ActionType_AppointRemind = "appointRemind";
    public static final String ActionType_Callback = "callback";
    public static final String ActionType_Copy = "copy";
    public static final String ActionType_DataUpload = "dataUpload";
    public static final String ActionType_MultiTabFail = "MultiTabFail_ActionId";
    public static final String ActionType_RecommendMore = "recommendMore";
    public static final String ActionType_RefreshFloor = "refreshFloor";
    public static final String ActionType_SwitchState = "switchState";
    public static String Action_AppointRemind = "appointRemind";
    public static String Action_Callback = "callback";
    public static String Action_MultiTabFail = "MultiTabFail_ActionId";
    public static String Action_RefreshFloor = "refreshFloor";
    public static String Action_SwitchState = "switchState";
    public static String EventId_MultiTabFail = "MultiTabFail_EventId";
    private static final String TAG = "JDViewKitEventHelper";

    /* JADX WARN: Code restructure failed: missing block: B:47:0x001f, code lost:
        if ((r3 instanceof com.jd.viewkit.templates.view.JDViewKitMultistateView) == false) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0023, code lost:
        return (com.jd.viewkit.templates.view.JDViewKitMultistateView) r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.jd.viewkit.templates.view.JDViewKitMultistateView GetMultistateView(android.view.View r3) {
        /*
            r0 = 0
            if (r3 != 0) goto L4
            return r0
        L4:
            if (r3 == 0) goto L1b
            boolean r1 = r3 instanceof com.jd.viewkit.templates.view.JDViewKitMultistateView     // Catch: java.lang.Exception -> L19
            if (r1 == 0) goto Lb
            goto L1b
        Lb:
            android.view.ViewParent r1 = r3.getParent()     // Catch: java.lang.Exception -> L19
            if (r1 == 0) goto L1b
            boolean r2 = r1 instanceof android.view.View     // Catch: java.lang.Exception -> L19
            if (r2 == 0) goto L1b
            r3 = r1
            android.view.View r3 = (android.view.View) r3     // Catch: java.lang.Exception -> L19
            goto L4
        L19:
            r3 = move-exception
            goto L24
        L1b:
            if (r3 == 0) goto L27
            boolean r1 = r3 instanceof com.jd.viewkit.templates.view.JDViewKitMultistateView     // Catch: java.lang.Exception -> L19
            if (r1 == 0) goto L27
            com.jd.viewkit.templates.view.JDViewKitMultistateView r3 = (com.jd.viewkit.templates.view.JDViewKitMultistateView) r3     // Catch: java.lang.Exception -> L19
            return r3
        L24:
            r3.printStackTrace()
        L27:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.viewkit.templates.view.helper.JDViewKitEventHelper.GetMultistateView(android.view.View):com.jd.viewkit.templates.view.JDViewKitMultistateView");
    }

    public static boolean UpdateMultiState(View view, String str) {
        if (view != null && str != null) {
            try {
                if (view instanceof JDViewKitMultistateView) {
                    return ((JDViewKitMultistateView) view).updataByState(str);
                }
                while (view != null && !(view instanceof JDViewKitMultistateView)) {
                    ViewParent parent = view.getParent();
                    if (parent == null || !(parent instanceof View)) {
                        break;
                    }
                    view = (View) parent;
                }
                if (view != null && (view instanceof JDViewKitMultistateView)) {
                    return ((JDViewKitMultistateView) view).updataByState(str);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }

    public static boolean UpdateaAtiveState(JDViewKitVirtualEvent jDViewKitVirtualEvent, JDViewKitDataSourceModel jDViewKitDataSourceModel, View view) {
        if (jDViewKitVirtualEvent == null || jDViewKitVirtualEvent.getParamMap() == null || jDViewKitDataSourceModel == null || jDViewKitDataSourceModel.getDataMap() == null || jDViewKitVirtualEvent.getParamMap() == null || jDViewKitVirtualEvent.getParamMap().get("activeState") == null) {
            return false;
        }
        if (jDViewKitDataSourceModel != null && jDViewKitDataSourceModel.getDataMap() != null) {
            jDViewKitDataSourceModel.getDataMap().put(JDViewKitCountdownView.useTimeOutKey, 0);
        }
        return UpdateMultiState(view, jDViewKitVirtualEvent.getParamMap().get("activeState").toString());
    }

    public static void click(JDViewKitVirtualView jDViewKitVirtualView, JDViewKitDataSourceModel jDViewKitDataSourceModel, View view, JDViewKitChannelModel jDViewKitChannelModel) {
        if (jDViewKitVirtualView == null) {
            return;
        }
        JDViewKitVirtualEvent virtualEventByType = jDViewKitVirtualView.getVirtualEventByType(JDViewKitVirtualEvent.typeClick);
        if (virtualEventByType != null && (virtualEventByType instanceof JDViewKitVirtualChainEvent)) {
            eventChainHandle((JDViewKitVirtualChainEvent) virtualEventByType, jDViewKitVirtualView, jDViewKitDataSourceModel, view, jDViewKitChannelModel);
        } else {
            eventHandle(null, virtualEventByType, jDViewKitVirtualView, jDViewKitDataSourceModel, view, JDViewKitEventAbstractCallBack.CallBackType_Single, null, jDViewKitChannelModel);
        }
    }

    public static void eventChainHandle(JDViewKitVirtualChainEvent jDViewKitVirtualChainEvent, JDViewKitVirtualView jDViewKitVirtualView, JDViewKitDataSourceModel jDViewKitDataSourceModel, View view, JDViewKitChannelModel jDViewKitChannelModel) {
        if (jDViewKitVirtualChainEvent != null) {
            eventChainHandleInfo(jDViewKitVirtualChainEvent, jDViewKitVirtualChainEvent.getEventStart(), jDViewKitVirtualView, jDViewKitDataSourceModel, view, null, jDViewKitChannelModel);
        }
    }

    public static void eventChainHandleInfo(JDViewKitVirtualChainEvent jDViewKitVirtualChainEvent, String str, JDViewKitVirtualView jDViewKitVirtualView, JDViewKitDataSourceModel jDViewKitDataSourceModel, View view, JDViewKitEventAbstractCallBack jDViewKitEventAbstractCallBack, JDViewKitChannelModel jDViewKitChannelModel) {
        if (jDViewKitVirtualChainEvent == null || jDViewKitDataSourceModel == null || view == null) {
            return;
        }
        eventHandle(jDViewKitVirtualChainEvent, jDViewKitVirtualChainEvent.getEventByKey(str), jDViewKitVirtualView, jDViewKitDataSourceModel, view, JDViewKitEventAbstractCallBack.CallBackType_Chain, jDViewKitEventAbstractCallBack, jDViewKitChannelModel);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:100:0x005d A[Catch: all -> 0x01d1, TryCatch #0 {all -> 0x01d1, blocks: (B:85:0x000c, B:87:0x0022, B:90:0x003e, B:92:0x0042, B:100:0x005d, B:102:0x006a, B:104:0x0071, B:106:0x008b, B:107:0x008f, B:146:0x018e, B:147:0x01a3, B:149:0x01b9, B:101:0x0064, B:93:0x0049, B:95:0x004d, B:97:0x0056, B:109:0x0094, B:111:0x00a0, B:113:0x00a6, B:116:0x00b2, B:118:0x00ba, B:120:0x00c0, B:122:0x00c6, B:123:0x00ee, B:126:0x00f8, B:128:0x00fc, B:133:0x0112, B:129:0x0103, B:131:0x0107, B:132:0x010e, B:134:0x012b, B:136:0x0133, B:137:0x0148, B:139:0x0150, B:140:0x016b, B:142:0x0173), top: B:152:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0064 A[Catch: all -> 0x01d1, TryCatch #0 {all -> 0x01d1, blocks: (B:85:0x000c, B:87:0x0022, B:90:0x003e, B:92:0x0042, B:100:0x005d, B:102:0x006a, B:104:0x0071, B:106:0x008b, B:107:0x008f, B:146:0x018e, B:147:0x01a3, B:149:0x01b9, B:101:0x0064, B:93:0x0049, B:95:0x004d, B:97:0x0056, B:109:0x0094, B:111:0x00a0, B:113:0x00a6, B:116:0x00b2, B:118:0x00ba, B:120:0x00c0, B:122:0x00c6, B:123:0x00ee, B:126:0x00f8, B:128:0x00fc, B:133:0x0112, B:129:0x0103, B:131:0x0107, B:132:0x010e, B:134:0x012b, B:136:0x0133, B:137:0x0148, B:139:0x0150, B:140:0x016b, B:142:0x0173), top: B:152:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0071 A[Catch: all -> 0x01d1, TryCatch #0 {all -> 0x01d1, blocks: (B:85:0x000c, B:87:0x0022, B:90:0x003e, B:92:0x0042, B:100:0x005d, B:102:0x006a, B:104:0x0071, B:106:0x008b, B:107:0x008f, B:146:0x018e, B:147:0x01a3, B:149:0x01b9, B:101:0x0064, B:93:0x0049, B:95:0x004d, B:97:0x0056, B:109:0x0094, B:111:0x00a0, B:113:0x00a6, B:116:0x00b2, B:118:0x00ba, B:120:0x00c0, B:122:0x00c6, B:123:0x00ee, B:126:0x00f8, B:128:0x00fc, B:133:0x0112, B:129:0x0103, B:131:0x0107, B:132:0x010e, B:134:0x012b, B:136:0x0133, B:137:0x0148, B:139:0x0150, B:140:0x016b, B:142:0x0173), top: B:152:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:145:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x01b9 A[Catch: all -> 0x01d1, TRY_LEAVE, TryCatch #0 {all -> 0x01d1, blocks: (B:85:0x000c, B:87:0x0022, B:90:0x003e, B:92:0x0042, B:100:0x005d, B:102:0x006a, B:104:0x0071, B:106:0x008b, B:107:0x008f, B:146:0x018e, B:147:0x01a3, B:149:0x01b9, B:101:0x0064, B:93:0x0049, B:95:0x004d, B:97:0x0056, B:109:0x0094, B:111:0x00a0, B:113:0x00a6, B:116:0x00b2, B:118:0x00ba, B:120:0x00c0, B:122:0x00c6, B:123:0x00ee, B:126:0x00f8, B:128:0x00fc, B:133:0x0112, B:129:0x0103, B:131:0x0107, B:132:0x010e, B:134:0x012b, B:136:0x0133, B:137:0x0148, B:139:0x0150, B:140:0x016b, B:142:0x0173), top: B:152:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:156:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void eventHandle(com.jd.viewkit.templates.model.event.JDViewKitVirtualChainEvent r17, com.jd.viewkit.templates.model.event.JDViewKitVirtualEvent r18, com.jd.viewkit.templates.model.JDViewKitVirtualView r19, com.jd.viewkit.dataSources.model.JDViewKitDataSourceModel r20, android.view.View r21, int r22, com.jd.viewkit.templates.view.helper.eventcallback.JDViewKitEventAbstractCallBack r23, com.jd.viewkit.helper.JDViewKitChannelModel r24) {
        /*
            Method dump skipped, instructions count: 466
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.viewkit.templates.view.helper.JDViewKitEventHelper.eventHandle(com.jd.viewkit.templates.model.event.JDViewKitVirtualChainEvent, com.jd.viewkit.templates.model.event.JDViewKitVirtualEvent, com.jd.viewkit.templates.model.JDViewKitVirtualView, com.jd.viewkit.dataSources.model.JDViewKitDataSourceModel, android.view.View, int, com.jd.viewkit.templates.view.helper.eventcallback.JDViewKitEventAbstractCallBack, com.jd.viewkit.helper.JDViewKitChannelModel):void");
    }

    public static void sendExpo(JDViewKitVirtualView jDViewKitVirtualView, JDViewKitVirtualEvent jDViewKitVirtualEvent, JDViewKitDataSourceModel jDViewKitDataSourceModel, View view) {
        if (jDViewKitVirtualView != null && jDViewKitVirtualEvent != null && jDViewKitDataSourceModel != null && view != null) {
            try {
                if (!ActionType_DataUpload.equals(jDViewKitVirtualEvent.getType())) {
                    return;
                }
                jDViewKitVirtualView.getServiceModel().getMtaService().sendExpo(new JDViewKitMtaModel(JSONTool.getJsonString(jDViewKitDataSourceModel.getJumpParams(jDViewKitVirtualEvent.getEventKey())), jDViewKitDataSourceModel.getParamsModel()), view.getContext());
            } catch (Throwable unused) {
            }
        }
    }

    public static void timeOut(JDViewKitVirtualView jDViewKitVirtualView, JDViewKitDataSourceModel jDViewKitDataSourceModel, View view, JDViewKitChannelModel jDViewKitChannelModel) {
        if (jDViewKitVirtualView == null) {
            return;
        }
        JDViewKitVirtualEvent virtualEventByType = jDViewKitVirtualView.getVirtualEventByType(JDViewKitVirtualEvent.typeTimeOutEvent);
        if (virtualEventByType != null && (virtualEventByType instanceof JDViewKitVirtualChainEvent)) {
            eventChainHandle((JDViewKitVirtualChainEvent) virtualEventByType, jDViewKitVirtualView, jDViewKitDataSourceModel, view, jDViewKitChannelModel);
        } else {
            eventHandle(null, virtualEventByType, jDViewKitVirtualView, jDViewKitDataSourceModel, view, JDViewKitEventAbstractCallBack.CallBackType_Single, null, jDViewKitChannelModel);
        }
    }
}
