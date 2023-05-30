package com.jd.viewkit.templates.view.helper;

import android.view.View;
import android.view.ViewParent;
import com.jd.viewkit.dataSources.model.JDViewKitDataSourceModel;
import com.jd.viewkit.helper.JDViewKitChannelModel;
import com.jd.viewkit.helper.JDViewKitViewListenerParamsModel;
import com.jd.viewkit.templates.model.JDViewKitVirtualView;
import com.jd.viewkit.templates.model.event.JDViewKitVirtualChainEvent;
import com.jd.viewkit.templates.model.event.JDViewKitVirtualEvent;
import com.jd.viewkit.templates.view.JDViewKitCountdownView;
import com.jd.viewkit.templates.view.JDViewKitMultistateView;
import com.jd.viewkit.templates.view.helper.eventcallback.JDViewKitEventAbstractCallBack;
import com.jd.viewkit.templates.view.helper.eventcallback.JDViewKitEventAppointRemindCallBack;
import com.jd.viewkit.templates.view.helper.eventcallback.JDViewKitEventCallbackCallBack;
import com.jd.viewkit.templates.view.helper.eventcallback.JDViewKitEventCopyCallBack;
import com.jd.viewkit.templates.view.helper.eventcallback.JDViewKitEventRefreshFloorCallBack;
import com.jd.viewkit.templates.view.helper.eventcallback.JDViewKitEventSwitchStateCallBack;
import com.jd.viewkit.templates.view.helper.eventcallback.JDViewkitEventRecommendMoreCallBack;
import com.jd.viewkit.thirdinterface.model.JDViewKitEventModel;
import com.jd.viewkit.thirdinterface.model.JDViewKitMtaModel;
import com.jd.viewkit.tool.JSONTool;
import com.jd.viewkit.tool.StringTool;
import java.util.Map;

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

    /* JADX WARN: Code restructure failed: missing block: B:76:0x001f, code lost:
        if ((r3 instanceof com.jd.viewkit.templates.view.JDViewKitMultistateView) == false) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0023, code lost:
        return (com.jd.viewkit.templates.view.JDViewKitMultistateView) r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static JDViewKitMultistateView GetMultistateView(View view) {
        ViewParent parent;
        if (view == null) {
            return null;
        }
        while (view != null) {
            try {
                if ((view instanceof JDViewKitMultistateView) || (parent = view.getParent()) == null || !(parent instanceof View)) {
                    break;
                }
                view = (View) parent;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
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
    /* JADX WARN: Removed duplicated region for block: B:179:0x005d A[Catch: all -> 0x01d1, TryCatch #0 {all -> 0x01d1, blocks: (B:164:0x000c, B:166:0x0022, B:169:0x003e, B:171:0x0042, B:179:0x005d, B:181:0x006a, B:183:0x0071, B:185:0x008b, B:186:0x008f, B:225:0x018e, B:226:0x01a3, B:228:0x01b9, B:180:0x0064, B:172:0x0049, B:174:0x004d, B:176:0x0056, B:188:0x0094, B:190:0x00a0, B:192:0x00a6, B:195:0x00b2, B:197:0x00ba, B:199:0x00c0, B:201:0x00c6, B:202:0x00ee, B:205:0x00f8, B:207:0x00fc, B:212:0x0112, B:208:0x0103, B:210:0x0107, B:211:0x010e, B:213:0x012b, B:215:0x0133, B:216:0x0148, B:218:0x0150, B:219:0x016b, B:221:0x0173), top: B:231:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0064 A[Catch: all -> 0x01d1, TryCatch #0 {all -> 0x01d1, blocks: (B:164:0x000c, B:166:0x0022, B:169:0x003e, B:171:0x0042, B:179:0x005d, B:181:0x006a, B:183:0x0071, B:185:0x008b, B:186:0x008f, B:225:0x018e, B:226:0x01a3, B:228:0x01b9, B:180:0x0064, B:172:0x0049, B:174:0x004d, B:176:0x0056, B:188:0x0094, B:190:0x00a0, B:192:0x00a6, B:195:0x00b2, B:197:0x00ba, B:199:0x00c0, B:201:0x00c6, B:202:0x00ee, B:205:0x00f8, B:207:0x00fc, B:212:0x0112, B:208:0x0103, B:210:0x0107, B:211:0x010e, B:213:0x012b, B:215:0x0133, B:216:0x0148, B:218:0x0150, B:219:0x016b, B:221:0x0173), top: B:231:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0071 A[Catch: all -> 0x01d1, TryCatch #0 {all -> 0x01d1, blocks: (B:164:0x000c, B:166:0x0022, B:169:0x003e, B:171:0x0042, B:179:0x005d, B:181:0x006a, B:183:0x0071, B:185:0x008b, B:186:0x008f, B:225:0x018e, B:226:0x01a3, B:228:0x01b9, B:180:0x0064, B:172:0x0049, B:174:0x004d, B:176:0x0056, B:188:0x0094, B:190:0x00a0, B:192:0x00a6, B:195:0x00b2, B:197:0x00ba, B:199:0x00c0, B:201:0x00c6, B:202:0x00ee, B:205:0x00f8, B:207:0x00fc, B:212:0x0112, B:208:0x0103, B:210:0x0107, B:211:0x010e, B:213:0x012b, B:215:0x0133, B:216:0x0148, B:218:0x0150, B:219:0x016b, B:221:0x0173), top: B:231:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:224:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x01b9 A[Catch: all -> 0x01d1, TRY_LEAVE, TryCatch #0 {all -> 0x01d1, blocks: (B:164:0x000c, B:166:0x0022, B:169:0x003e, B:171:0x0042, B:179:0x005d, B:181:0x006a, B:183:0x0071, B:185:0x008b, B:186:0x008f, B:225:0x018e, B:226:0x01a3, B:228:0x01b9, B:180:0x0064, B:172:0x0049, B:174:0x004d, B:176:0x0056, B:188:0x0094, B:190:0x00a0, B:192:0x00a6, B:195:0x00b2, B:197:0x00ba, B:199:0x00c0, B:201:0x00c6, B:202:0x00ee, B:205:0x00f8, B:207:0x00fc, B:212:0x0112, B:208:0x0103, B:210:0x0107, B:211:0x010e, B:213:0x012b, B:215:0x0133, B:216:0x0148, B:218:0x0150, B:219:0x016b, B:221:0x0173), top: B:231:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:235:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static void eventHandle(JDViewKitVirtualChainEvent jDViewKitVirtualChainEvent, JDViewKitVirtualEvent jDViewKitVirtualEvent, JDViewKitVirtualView jDViewKitVirtualView, JDViewKitDataSourceModel jDViewKitDataSourceModel, View view, int i2, JDViewKitEventAbstractCallBack jDViewKitEventAbstractCallBack, JDViewKitChannelModel jDViewKitChannelModel) {
        boolean z;
        boolean z2;
        JDViewKitEventRefreshFloorCallBack jDViewKitEventRefreshFloorCallBack;
        JDViewKitEventRefreshFloorCallBack jDViewKitEventRefreshFloorCallBack2;
        JDViewKitMultistateView GetMultistateView;
        JDViewKitMultistateView jDViewKitMultistateView;
        boolean UpdateaAtiveState;
        if (jDViewKitVirtualEvent != null && jDViewKitDataSourceModel != null) {
            try {
                String action = jDViewKitVirtualEvent.getAction();
                Map<String, Object> jumpParams = jDViewKitDataSourceModel.getJumpParams(jDViewKitVirtualEvent.getEventKey());
                String jsonString = JSONTool.getJsonString(jumpParams);
                if (!StringTool.isNotEmpty(action)) {
                    return;
                }
                String str = "eventHandle: " + action;
                JDViewKitEventCallbackCallBack jDViewKitEventCallbackCallBack = null;
                r13 = null;
                r13 = null;
                JDViewKitEventRefreshFloorCallBack jDViewKitEventRefreshFloorCallBack3 = null;
                jDViewKitEventCallbackCallBack = null;
                jDViewKitEventCallbackCallBack = null;
                JDViewKitMultistateView GetMultistateView2 = null;
                if (action.equals(ActionType_SwitchState)) {
                    if (jDViewKitEventAbstractCallBack != null) {
                        if (jDViewKitEventAbstractCallBack instanceof JDViewKitEventSwitchStateCallBack) {
                            GetMultistateView = ((JDViewKitEventSwitchStateCallBack) jDViewKitEventAbstractCallBack).getMultistateView();
                        } else if (jDViewKitEventAbstractCallBack instanceof JDViewKitEventCallbackCallBack) {
                            GetMultistateView = ((JDViewKitEventCallbackCallBack) jDViewKitEventAbstractCallBack).getMultistateView();
                        } else {
                            jDViewKitMultistateView = null;
                            if (jDViewKitMultistateView == null) {
                                UpdateaAtiveState = UpdateaAtiveState(jDViewKitVirtualEvent, jDViewKitDataSourceModel, jDViewKitMultistateView);
                            } else {
                                UpdateaAtiveState = UpdateaAtiveState(jDViewKitVirtualEvent, jDViewKitDataSourceModel, view);
                            }
                            boolean z3 = UpdateaAtiveState;
                            if (i2 == JDViewKitEventAbstractCallBack.CallBackType_Chain) {
                                JDViewKitEventSwitchStateCallBack jDViewKitEventSwitchStateCallBack = new JDViewKitEventSwitchStateCallBack(jDViewKitVirtualChainEvent, jDViewKitVirtualEvent, jDViewKitVirtualView, jDViewKitDataSourceModel, view, i2, jDViewKitChannelModel);
                                jDViewKitEventSwitchStateCallBack.setMultistateView(jDViewKitMultistateView);
                                if (z3) {
                                    jDViewKitEventSwitchStateCallBack.successCallBack(0, null, null);
                                } else {
                                    jDViewKitEventSwitchStateCallBack.failCallBack(0, null, null);
                                }
                                jDViewKitEventRefreshFloorCallBack3 = jDViewKitEventSwitchStateCallBack;
                            }
                        }
                    } else {
                        GetMultistateView = GetMultistateView(view);
                    }
                    jDViewKitMultistateView = GetMultistateView;
                    if (jDViewKitMultistateView == null) {
                    }
                    boolean z32 = UpdateaAtiveState;
                    if (i2 == JDViewKitEventAbstractCallBack.CallBackType_Chain) {
                    }
                } else if (action.equals(ActionType_MultiTabFail)) {
                    if (jDViewKitDataSourceModel.getFloorAcrossListener() != null) {
                        jDViewKitDataSourceModel.getFloorAcrossListener().floorAcross();
                    }
                } else {
                    if (action.equals(ActionType_RefreshFloor)) {
                        if (jDViewKitDataSourceModel.getViewListener() != null && StringTool.isNotEmpty(jsonString)) {
                            JDViewKitViewListenerParamsModel jDViewKitViewListenerParamsModel = new JDViewKitViewListenerParamsModel(JDViewKitViewListenerParamsModel.paramsModelTypeRefreshFloor, new JDViewKitEventModel(jsonString));
                            JDViewKitEventRefreshFloorCallBack jDViewKitEventRefreshFloorCallBack4 = new JDViewKitEventRefreshFloorCallBack(jDViewKitVirtualChainEvent, jDViewKitVirtualEvent, jDViewKitVirtualView, jDViewKitDataSourceModel, view, i2, jDViewKitChannelModel);
                            jDViewKitDataSourceModel.getViewListener().floorRefresh(jDViewKitViewListenerParamsModel, jDViewKitEventRefreshFloorCallBack4);
                            jDViewKitEventRefreshFloorCallBack3 = jDViewKitEventRefreshFloorCallBack4;
                        }
                    } else if (action.equals("callback")) {
                        if (jDViewKitEventAbstractCallBack != null) {
                            if (jDViewKitEventAbstractCallBack instanceof JDViewKitEventSwitchStateCallBack) {
                                GetMultistateView2 = ((JDViewKitEventSwitchStateCallBack) jDViewKitEventAbstractCallBack).getMultistateView();
                            } else if (jDViewKitEventAbstractCallBack instanceof JDViewKitEventCallbackCallBack) {
                                GetMultistateView2 = ((JDViewKitEventCallbackCallBack) jDViewKitEventAbstractCallBack).getMultistateView();
                            }
                        } else {
                            GetMultistateView2 = GetMultistateView(view);
                        }
                        JDViewKitEventCallbackCallBack jDViewKitEventCallbackCallBack2 = new JDViewKitEventCallbackCallBack(jDViewKitVirtualChainEvent, jDViewKitVirtualEvent, jDViewKitVirtualView, jDViewKitDataSourceModel, view, i2, jDViewKitChannelModel);
                        jDViewKitEventCallbackCallBack2.setMultistateView(GetMultistateView2);
                        jDViewKitEventCallbackCallBack = jDViewKitEventCallbackCallBack2;
                    } else if (action.equals(ActionType_Copy)) {
                        jDViewKitEventCallbackCallBack = new JDViewKitEventCopyCallBack(jDViewKitVirtualChainEvent, jDViewKitVirtualEvent, jDViewKitVirtualView, jDViewKitDataSourceModel, view, JDViewKitEventAbstractCallBack.CallBackType_Chain, jDViewKitChannelModel);
                    } else if (action.equals(ActionType_AppointRemind)) {
                        JDViewKitEventAppointRemindCallBack jDViewKitEventAppointRemindCallBack = new JDViewKitEventAppointRemindCallBack(jDViewKitVirtualChainEvent, jDViewKitVirtualEvent, jDViewKitVirtualView, jDViewKitDataSourceModel, view, JDViewKitEventAbstractCallBack.CallBackType_Chain, jDViewKitChannelModel);
                        jDViewKitEventAppointRemindCallBack.setmParams(jumpParams);
                        z = true;
                        z2 = false;
                        jDViewKitEventRefreshFloorCallBack = jDViewKitEventAppointRemindCallBack;
                        if (z) {
                            if (jDViewKitEventRefreshFloorCallBack != null) {
                                jDViewKitVirtualView.getServiceModel().getEventService().clickEvent(new JDViewKitEventModel(jsonString), view.getContext(), jDViewKitEventRefreshFloorCallBack);
                            } else {
                                jDViewKitVirtualView.getServiceModel().getEventService().clickEvent(new JDViewKitEventModel(jsonString), view.getContext());
                            }
                        }
                        if (!z2) {
                            return;
                        }
                        jDViewKitVirtualView.getServiceModel().getMtaService().click(new JDViewKitMtaModel(jsonString, jDViewKitDataSourceModel.getParamsModel()), view.getContext());
                    } else if (action.equals(ActionType_RecommendMore)) {
                        jDViewKitEventCallbackCallBack = new JDViewkitEventRecommendMoreCallBack(jDViewKitVirtualChainEvent, jDViewKitVirtualEvent, jDViewKitVirtualView, jDViewKitDataSourceModel, view, JDViewKitEventAbstractCallBack.CallBackType_Chain, jDViewKitChannelModel);
                    }
                    z = true;
                    jDViewKitEventRefreshFloorCallBack2 = jDViewKitEventCallbackCallBack;
                    z2 = true;
                    jDViewKitEventRefreshFloorCallBack = jDViewKitEventRefreshFloorCallBack2;
                    if (z) {
                    }
                    if (!z2) {
                    }
                }
                z = false;
                jDViewKitEventRefreshFloorCallBack2 = jDViewKitEventRefreshFloorCallBack3;
                z2 = true;
                jDViewKitEventRefreshFloorCallBack = jDViewKitEventRefreshFloorCallBack2;
                if (z) {
                }
                if (!z2) {
                }
            } catch (Throwable unused) {
            }
        }
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
