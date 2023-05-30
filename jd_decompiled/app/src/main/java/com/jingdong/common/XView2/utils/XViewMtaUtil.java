package com.jingdong.common.XView2.utils;

import android.content.Context;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.XView2.entity.MtaEntity;
import com.jingdong.common.XView2.entity.PreDownLoadResourceEntity;
import com.jingdong.common.XView2.entity.XViewConfigEntity;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.sdk.jdhttpdns.config.HttpDnsConfig;
import java.util.Iterator;
import tv.danmaku.ijk.media.ext.report.ReportConstant;

/* loaded from: classes5.dex */
public class XViewMtaUtil {
    public static void controlClickMta(Context context, XViewConfigEntity.LayersEntity layersEntity, XViewConfigEntity.TargetsEntity targetsEntity, XViewConfigEntity.ControlEntity controlEntity) {
        if (context == null) {
            return;
        }
        MtaEntity mtaEntity = new MtaEntity();
        mtaEntity.eventId = XView2Constants.XVIEW_CONTROL;
        JDJSONObject jDJSONObject = new JDJSONObject();
        if (targetsEntity != null) {
            jDJSONObject.put("targetName", (Object) convertTargetName(targetsEntity.targetName));
            jDJSONObject.put("targetId", (Object) Integer.valueOf(targetsEntity.targetId));
            jDJSONObject.put("targetType", (Object) Integer.valueOf(targetsEntity.targetType));
        }
        if (layersEntity != null) {
            jDJSONObject.put(XView2Constants.LAYER_ID, (Object) layersEntity.layerId);
            jDJSONObject.put(ReportConstant.PlayStatus.RENDER_TYPE, (Object) Integer.valueOf(layersEntity.renderType));
            jDJSONObject.put(CartConstant.KEY_JUMPURL, (Object) layersEntity.androidJumpUrl);
            jDJSONObject.put("name", (Object) layersEntity.name);
            String str = layersEntity.channel;
            if (str == null) {
                str = "";
            }
            jDJSONObject.put("channel", (Object) str);
        }
        if (controlEntity != null) {
            jDJSONObject.put("controlName", (Object) controlEntity.name);
            jDJSONObject.put("controlType", (Object) controlEntity.type);
            jDJSONObject.put("controlJumpUrl", (Object) controlEntity.androidJumpUrl);
        }
        mtaEntity.jsonObjParam = jDJSONObject;
        mtaEntity.pageId = XView2Constants.PAGE_ID;
        sendClickMta(context, mtaEntity);
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:55:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String convertTargetName(java.lang.String r7) {
        /*
            java.lang.String r0 = "fragment"
            boolean r1 = android.text.TextUtils.isEmpty(r7)
            java.lang.String r2 = ""
            if (r1 == 0) goto Lb
            return r2
        Lb:
            r1 = 0
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch: org.json.JSONException -> L2d
            r3.<init>(r7)     // Catch: org.json.JSONException -> L2d
            int r4 = r3.length()     // Catch: org.json.JSONException -> L2d
            if (r4 <= 0) goto L2d
            r4 = 1
            java.lang.String r5 = "activity"
            java.lang.String r5 = r3.optString(r5)     // Catch: org.json.JSONException -> L2d
            boolean r6 = r3.has(r0)     // Catch: org.json.JSONException -> L2b
            if (r6 == 0) goto L29
            java.lang.String r0 = r3.optString(r0)     // Catch: org.json.JSONException -> L2b
            r2 = r0
        L29:
            r1 = 1
            goto L2e
        L2b:
            goto L2e
        L2d:
            r5 = r2
        L2e:
            if (r1 == 0) goto L4b
            boolean r7 = android.text.TextUtils.isEmpty(r2)
            if (r7 == 0) goto L37
            return r5
        L37:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r7.append(r5)
            java.lang.String r0 = "|"
            r7.append(r0)
            r7.append(r2)
            java.lang.String r7 = r7.toString()
        L4b:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.XView2.utils.XViewMtaUtil.convertTargetName(java.lang.String):java.lang.String");
    }

    public static String getPreDownloadMtaValue(XViewConfigEntity.LayersEntity layersEntity) {
        if (layersEntity != null && !XView2Utils.isListEmpty(layersEntity.preDownLoadList)) {
            Iterator<PreDownLoadResourceEntity> it = layersEntity.preDownLoadList.iterator();
            while (it.hasNext()) {
                PreDownLoadResourceEntity next = it.next();
                if (XView2Utils.isConvertBool(next.canPreload) || XView2Utils.isConvertBool(next.popAfterDownload)) {
                    return "1";
                }
            }
        }
        return "0";
    }

    public static long getRenderTime(XViewConfigEntity.LayersEntity layersEntity) {
        if (layersEntity == null) {
            return 0L;
        }
        long j2 = layersEntity.renderEndTime;
        long j3 = layersEntity.renderStartTime;
        if (j2 - j3 <= 0) {
            return 0L;
        }
        return j2 - j3;
    }

    public static void jumpClickMta(Context context, XViewConfigEntity.LayersEntity layersEntity, XViewConfigEntity.TargetsEntity targetsEntity) {
        if (context == null) {
            return;
        }
        MtaEntity mtaEntity = new MtaEntity();
        mtaEntity.eventId = XView2Constants.XVIEW_JUMP_CLICK;
        JDJSONObject jDJSONObject = new JDJSONObject();
        if (targetsEntity != null) {
            jDJSONObject.put("targetName", (Object) convertTargetName(targetsEntity.targetName));
            jDJSONObject.put("targetId", (Object) Integer.valueOf(targetsEntity.targetId));
            jDJSONObject.put("targetType", (Object) Integer.valueOf(targetsEntity.targetType));
        }
        if (layersEntity != null) {
            jDJSONObject.put(XView2Constants.LAYER_ID, (Object) layersEntity.layerId);
            jDJSONObject.put(ReportConstant.PlayStatus.RENDER_TYPE, (Object) Integer.valueOf(layersEntity.renderType));
            jDJSONObject.put(CartConstant.KEY_JUMPURL, (Object) layersEntity.androidJumpUrl);
            jDJSONObject.put("name", (Object) layersEntity.name);
            String str = layersEntity.channel;
            if (str == null) {
                str = "";
            }
            jDJSONObject.put("channel", (Object) str);
            XViewConfigEntity.RenderDataEntity renderDataEntity = layersEntity.renderData;
            if (renderDataEntity != null) {
                jDJSONObject.put("renderUrl", (Object) renderDataEntity.url);
            }
        }
        mtaEntity.jsonObjParam = jDJSONObject;
        mtaEntity.pageId = XView2Constants.PAGE_ID;
        sendClickMta(context, mtaEntity);
    }

    public static void popClickMta(Context context, XViewConfigEntity.LayersEntity layersEntity, XViewConfigEntity.TargetsEntity targetsEntity, String str) {
        if (context != null && SwitchQueryFetcher.getSwitchBooleanValue(XView2Constants.XVIEW_POP_REPORT, false)) {
            MtaEntity mtaEntity = new MtaEntity();
            mtaEntity.eventId = XView2Constants.XVIEW_INVOKE;
            JDJSONObject jDJSONObject = new JDJSONObject();
            if (targetsEntity != null) {
                jDJSONObject.put("targetId", (Object) Integer.valueOf(targetsEntity.targetId));
            }
            if (SwitchQueryFetcher.getSwitchBooleanValue(XView2Constants.XVIEW_GLOBALMODEL, false) && layersEntity != null && layersEntity.isGlobalWindow) {
                jDJSONObject.put("targetId", (Object) 57);
            }
            if (layersEntity != null) {
                jDJSONObject.put(XView2Constants.LAYER_ID, (Object) layersEntity.layerId);
                jDJSONObject.put(ReportConstant.PlayStatus.RENDER_TYPE, (Object) Integer.valueOf(layersEntity.renderType));
                jDJSONObject.put("name", (Object) layersEntity.name);
                String str2 = layersEntity.channel;
                if (str2 == null) {
                    str2 = "";
                }
                jDJSONObject.put("channel", (Object) str2);
            }
            jDJSONObject.put(HttpDnsConfig.PREDOWNLOAD_PARAMS, (Object) str);
            mtaEntity.jsonObjParam = jDJSONObject;
            mtaEntity.pageId = XView2Constants.PAGE_ID;
            sendClickMta(context, mtaEntity);
        }
    }

    public static void sendClickMta(Context context, MtaEntity mtaEntity) {
        if (mtaEntity == null) {
            return;
        }
        String str = mtaEntity.eventId;
        String str2 = mtaEntity.eventParam;
        String str3 = mtaEntity.pageId;
        String str4 = mtaEntity.pageName;
        JDJSONObject jDJSONObject = mtaEntity.jsonObjParam;
        JDMtaUtils.sendClickDataWithExt(context, str, str2, "", str3, str4, "", "", jDJSONObject != null ? jDJSONObject.toString() : "", null);
    }

    public static void sendExpMta(Context context, MtaEntity mtaEntity) {
        if (mtaEntity == null) {
            return;
        }
        String str = mtaEntity.eventId;
        String str2 = mtaEntity.eventParam;
        String str3 = mtaEntity.pageId;
        String str4 = mtaEntity.pageName;
        JDJSONObject jDJSONObject = mtaEntity.jsonObjParam;
        JDMtaUtils.sendExposureDataWithExt(context, str, str2, str3, str4, "", jDJSONObject != null ? jDJSONObject.toString() : "", null);
    }

    public static void sendLayerCloseMta(Context context, XViewConfigEntity.LayersEntity layersEntity, XViewConfigEntity.TargetsEntity targetsEntity, int i2) {
        if (context == null || layersEntity == null || layersEntity.renderType != 6) {
            return;
        }
        MtaEntity mtaEntity = new MtaEntity();
        mtaEntity.eventId = XView2Constants.XVIEW_CLOSE;
        JDJSONObject jDJSONObject = new JDJSONObject();
        if (targetsEntity != null) {
            jDJSONObject.put("targetName", (Object) convertTargetName(targetsEntity.targetName));
            jDJSONObject.put("targetId", (Object) Integer.valueOf(targetsEntity.targetId));
            jDJSONObject.put("targetType", (Object) Integer.valueOf(targetsEntity.targetType));
        }
        if (SwitchQueryFetcher.getSwitchBooleanValue(XView2Constants.XVIEW_GLOBALMODEL, false) && layersEntity != null && layersEntity.isGlobalWindow) {
            jDJSONObject.put("targetId", (Object) 57);
            jDJSONObject.put("targetName", (Object) XView2Constants.JDGLOBAL_WINDOWPAGE);
            jDJSONObject.put("targetType", (Object) 1);
        }
        jDJSONObject.put(XView2Constants.LAYER_ID, (Object) layersEntity.layerId);
        jDJSONObject.put(ReportConstant.PlayStatus.RENDER_TYPE, (Object) Integer.valueOf(layersEntity.renderType));
        jDJSONObject.put("name", (Object) layersEntity.name);
        String str = layersEntity.channel;
        if (str == null) {
            str = "";
        }
        jDJSONObject.put("channel", (Object) str);
        XViewConfigEntity.RenderDataEntity renderDataEntity = layersEntity.renderData;
        if (renderDataEntity != null) {
            jDJSONObject.put("renderUrl", (Object) renderDataEntity.url);
        }
        jDJSONObject.put("closeReason", (Object) Integer.valueOf(i2));
        mtaEntity.jsonObjParam = jDJSONObject;
        mtaEntity.pageId = XView2Constants.PAGE_ID;
        if (context != null) {
            sendExpMta(context, mtaEntity);
        }
    }

    public static void sendLayerExpMta(Context context, XViewConfigEntity.LayersEntity layersEntity, XViewConfigEntity.TargetsEntity targetsEntity) {
        if (context == null || layersEntity == null) {
            return;
        }
        MtaEntity mtaEntity = new MtaEntity();
        mtaEntity.eventId = XView2Constants.XVIEW_LAYEREXPO;
        JDJSONObject jDJSONObject = new JDJSONObject();
        if (targetsEntity != null) {
            jDJSONObject.put("targetName", (Object) convertTargetName(targetsEntity.targetName));
            jDJSONObject.put("targetId", (Object) Integer.valueOf(targetsEntity.targetId));
            jDJSONObject.put("targetType", (Object) Integer.valueOf(targetsEntity.targetType));
        }
        if (SwitchQueryFetcher.getSwitchBooleanValue(XView2Constants.XVIEW_GLOBALMODEL, false) && layersEntity != null && layersEntity.isGlobalWindow) {
            jDJSONObject.put("targetName", (Object) XView2Constants.JDGLOBAL_WINDOWPAGE);
            jDJSONObject.put("targetType", (Object) 1);
            jDJSONObject.put("targetId", (Object) 57);
        }
        jDJSONObject.put(XView2Constants.LAYER_ID, (Object) layersEntity.layerId);
        jDJSONObject.put(ReportConstant.PlayStatus.RENDER_TYPE, (Object) Integer.valueOf(layersEntity.renderType));
        jDJSONObject.put("name", (Object) layersEntity.name);
        String str = layersEntity.channel;
        if (str == null) {
            str = "";
        }
        jDJSONObject.put("channel", (Object) str);
        XViewConfigEntity.RenderDataEntity renderDataEntity = layersEntity.renderData;
        if (renderDataEntity != null) {
            jDJSONObject.put("renderUrl", (Object) renderDataEntity.url);
        }
        mtaEntity.jsonObjParam = jDJSONObject;
        mtaEntity.pageId = XView2Constants.PAGE_ID;
        if (context != null) {
            sendExpMta(context, mtaEntity);
        }
    }

    public static void sendLayerPerformanceMta(Context context, XViewConfigEntity.LayersEntity layersEntity, XViewConfigEntity.TargetsEntity targetsEntity) {
        if (context == null || layersEntity == null || layersEntity.renderType != 6) {
            return;
        }
        MtaEntity mtaEntity = new MtaEntity();
        mtaEntity.eventId = XView2Constants.XVIEW_PERFORMANCE;
        JDJSONObject jDJSONObject = new JDJSONObject();
        if (targetsEntity != null) {
            jDJSONObject.put("targetName", (Object) convertTargetName(targetsEntity.targetName));
            jDJSONObject.put("targetId", (Object) Integer.valueOf(targetsEntity.targetId));
            jDJSONObject.put("targetType", (Object) Integer.valueOf(targetsEntity.targetType));
        }
        if (SwitchQueryFetcher.getSwitchBooleanValue(XView2Constants.XVIEW_GLOBALMODEL, false) && layersEntity != null && layersEntity.isGlobalWindow) {
            jDJSONObject.put("targetId", (Object) 57);
            jDJSONObject.put("targetName", (Object) XView2Constants.JDGLOBAL_WINDOWPAGE);
            jDJSONObject.put("targetType", (Object) 1);
        }
        jDJSONObject.put(XView2Constants.LAYER_ID, (Object) layersEntity.layerId);
        jDJSONObject.put(ReportConstant.PlayStatus.RENDER_TYPE, (Object) Integer.valueOf(layersEntity.renderType));
        jDJSONObject.put("name", (Object) layersEntity.name);
        String str = layersEntity.channel;
        if (str == null) {
            str = "";
        }
        jDJSONObject.put("channel", (Object) str);
        XViewConfigEntity.RenderDataEntity renderDataEntity = layersEntity.renderData;
        if (renderDataEntity != null) {
            jDJSONObject.put("renderUrl", (Object) renderDataEntity.url);
        }
        jDJSONObject.put("preDownload", (Object) getPreDownloadMtaValue(layersEntity));
        jDJSONObject.put("createCost", (Object) Long.valueOf(getRenderTime(layersEntity)));
        mtaEntity.jsonObjParam = jDJSONObject;
        mtaEntity.pageId = XView2Constants.PAGE_ID;
        if (context != null) {
            sendExpMta(context, mtaEntity);
        }
    }

    public static void setSuccessDownLoadMta(Context context, String str, int i2, String str2) {
        MtaEntity mtaEntity = new MtaEntity();
        mtaEntity.eventId = XView2Constants.XVIEW_PRELOAD;
        JDJSONObject jDJSONObject = new JDJSONObject();
        jDJSONObject.put(XView2Constants.LAYER_ID, (Object) str);
        jDJSONObject.put("targetId", (Object) Integer.valueOf(i2));
        jDJSONObject.put("dslUrl", (Object) str2);
        mtaEntity.jsonObjParam = jDJSONObject;
        mtaEntity.pageId = XView2Constants.PAGE_ID;
        if (context != null) {
            sendExpMta(context, mtaEntity);
        }
    }

    public static void controlClickMta(Context context, XViewConfigEntity.LayersEntity layersEntity, XViewConfigEntity.TargetsEntity targetsEntity, JDJSONObject jDJSONObject) {
        if (context == null) {
            return;
        }
        MtaEntity mtaEntity = new MtaEntity();
        mtaEntity.eventId = XView2Constants.XVIEW_CONTROL;
        JDJSONObject jDJSONObject2 = new JDJSONObject();
        if (targetsEntity != null) {
            jDJSONObject2.put("targetName", (Object) convertTargetName(targetsEntity.targetName));
            jDJSONObject2.put("targetId", (Object) Integer.valueOf(targetsEntity.targetId));
            jDJSONObject2.put("targetType", (Object) Integer.valueOf(targetsEntity.targetType));
        }
        if (SwitchQueryFetcher.getSwitchBooleanValue(XView2Constants.XVIEW_GLOBALMODEL, false) && layersEntity != null && layersEntity.isGlobalWindow) {
            jDJSONObject2.put("targetName", (Object) XView2Constants.JDGLOBAL_WINDOWPAGE);
            jDJSONObject2.put("targetType", (Object) 1);
            jDJSONObject2.put("targetId", (Object) 57);
        }
        if (layersEntity != null) {
            jDJSONObject2.put(XView2Constants.LAYER_ID, (Object) layersEntity.layerId);
            jDJSONObject2.put(ReportConstant.PlayStatus.RENDER_TYPE, (Object) Integer.valueOf(layersEntity.renderType));
            jDJSONObject2.put("name", (Object) layersEntity.name);
            String str = layersEntity.channel;
            if (str == null) {
                str = "";
            }
            jDJSONObject2.put("channel", (Object) str);
        }
        if (jDJSONObject != null) {
            jDJSONObject2.put("controlName", (Object) jDJSONObject.optString("name"));
            jDJSONObject2.put("controlType", (Object) jDJSONObject.optString("type"));
            jDJSONObject2.put("controlJumpUrl", (Object) jDJSONObject.optString("openapp"));
        }
        mtaEntity.jsonObjParam = jDJSONObject2;
        mtaEntity.pageId = XView2Constants.PAGE_ID;
        sendClickMta(context, mtaEntity);
    }
}
