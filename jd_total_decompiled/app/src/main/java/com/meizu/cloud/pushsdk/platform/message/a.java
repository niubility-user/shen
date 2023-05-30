package com.meizu.cloud.pushsdk.platform.message;

import android.text.TextUtils;
import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.meizu.cloud.pushsdk.platform.message.SubTagsStatus;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes14.dex */
public class a {
    public static PushSwitchStatus a(String str) {
        DebugLogger.i("StatusSerialize", "register status serialize stringToPushSwitchStatus start, statusText=" + str);
        try {
            PushSwitchStatus pushSwitchStatus = new PushSwitchStatus();
            JSONObject jSONObject = new JSONObject(str);
            PushSwitchStatus pushSwitchStatus2 = (PushSwitchStatus) h(jSONObject, pushSwitchStatus);
            if (!jSONObject.isNull(PushConstants.REGISTER_STATUS_PUSH_ID)) {
                pushSwitchStatus2.setPushId(jSONObject.getString(PushConstants.REGISTER_STATUS_PUSH_ID));
            }
            if (!jSONObject.isNull(PushConstants.PUSH_SWITCH_STATUS_IS_NOTIFICATION_SWITCH)) {
                pushSwitchStatus2.setSwitchNotificationMessage(jSONObject.getBoolean(PushConstants.PUSH_SWITCH_STATUS_IS_NOTIFICATION_SWITCH));
            }
            if (!jSONObject.isNull(PushConstants.PUSH_SWITCH_STATUS_IS_THROUGH_SWITCH)) {
                pushSwitchStatus2.setSwitchThroughMessage(jSONObject.getBoolean(PushConstants.PUSH_SWITCH_STATUS_IS_THROUGH_SWITCH));
            }
            DebugLogger.i("StatusSerialize", "register status serialize stringToPushSwitchStatus success, PushSwitchStatus=" + pushSwitchStatus2);
            return pushSwitchStatus2;
        } catch (JSONException e2) {
            DebugLogger.e("StatusSerialize", "register status serialize stringToPushSwitchStatus error, " + e2.getMessage());
            e2.printStackTrace();
            return null;
        }
    }

    public static String b(PushSwitchStatus pushSwitchStatus) {
        DebugLogger.i("StatusSerialize", "register status serialize pushSwitchStatusToString start, PushSwitchStatus=" + pushSwitchStatus);
        try {
            JSONObject g2 = g(new JSONObject(), pushSwitchStatus);
            if (!TextUtils.isEmpty(pushSwitchStatus.getPushId())) {
                g2.put(PushConstants.REGISTER_STATUS_PUSH_ID, pushSwitchStatus.getPushId());
            }
            g2.put(PushConstants.PUSH_SWITCH_STATUS_IS_NOTIFICATION_SWITCH, pushSwitchStatus.isSwitchNotificationMessage());
            g2.put(PushConstants.PUSH_SWITCH_STATUS_IS_THROUGH_SWITCH, pushSwitchStatus.isSwitchThroughMessage());
            String jSONObject = g2.toString();
            DebugLogger.i("StatusSerialize", "register status serialize pushSwitchStatusToString success, statusText=" + jSONObject);
            return jSONObject;
        } catch (JSONException e2) {
            DebugLogger.e("StatusSerialize", "register status serialize pushSwitchStatusToString error, " + e2.getMessage());
            e2.printStackTrace();
            return null;
        }
    }

    public static String c(RegisterStatus registerStatus) {
        DebugLogger.i("StatusSerialize", "register status serialize registerStatusToString start, RegisterStatus=" + registerStatus);
        try {
            JSONObject g2 = g(new JSONObject(), registerStatus);
            if (!TextUtils.isEmpty(registerStatus.getPushId())) {
                g2.put(PushConstants.REGISTER_STATUS_PUSH_ID, registerStatus.getPushId());
            }
            if (registerStatus.getExpireTime() > 0) {
                g2.put(PushConstants.REGISTER_STATUS_EXPIRE_TIME, registerStatus.getExpireTime());
            }
            String jSONObject = g2.toString();
            DebugLogger.i("StatusSerialize", "register status serialize registerStatusToString success, statusText=" + jSONObject);
            return jSONObject;
        } catch (JSONException e2) {
            DebugLogger.e("StatusSerialize", "register status serialize registerStatusToString error, " + e2.getMessage());
            e2.printStackTrace();
            return null;
        }
    }

    public static String d(SubAliasStatus subAliasStatus) {
        DebugLogger.i("StatusSerialize", "register status serialize subAliasStatusToString start, SubAliasStatus=" + subAliasStatus);
        try {
            JSONObject g2 = g(new JSONObject(), subAliasStatus);
            if (!TextUtils.isEmpty(subAliasStatus.getPushId())) {
                g2.put(PushConstants.REGISTER_STATUS_PUSH_ID, subAliasStatus.getPushId());
            }
            g2.put(PushConstants.SUB_ALIAS_STATUS_NAME, subAliasStatus.getAlias());
            String jSONObject = g2.toString();
            DebugLogger.i("StatusSerialize", "register status serialize subAliasStatusToString success, statusText=" + jSONObject);
            return jSONObject;
        } catch (JSONException e2) {
            DebugLogger.e("StatusSerialize", "register status serialize subAliasStatusToString error, " + e2.getMessage());
            e2.printStackTrace();
            return null;
        }
    }

    public static String e(SubTagsStatus subTagsStatus) {
        DebugLogger.i("StatusSerialize", "register status serialize subTagsStatusToString start, SubTagsStatus=" + subTagsStatus);
        try {
            JSONObject g2 = g(new JSONObject(), subTagsStatus);
            if (!TextUtils.isEmpty(subTagsStatus.getPushId())) {
                g2.put(PushConstants.REGISTER_STATUS_PUSH_ID, subTagsStatus.getPushId());
            }
            if (subTagsStatus.getTagList() != null) {
                JSONArray jSONArray = new JSONArray();
                for (int i2 = 0; i2 < subTagsStatus.getTagList().size(); i2++) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("tag_id", subTagsStatus.getTagList().get(i2).getTagId());
                    jSONObject.put(PushConstants.SUB_TAGS_STATUS_NAME, subTagsStatus.getTagList().get(i2).getTagName());
                    jSONArray.put(jSONObject);
                }
                g2.put("tag_list", jSONArray);
            }
            String jSONObject2 = g2.toString();
            DebugLogger.i("StatusSerialize", "register status serialize subTagsStatusToString success, statusText=" + jSONObject2);
            return jSONObject2;
        } catch (JSONException e2) {
            DebugLogger.e("StatusSerialize", "register status serialize subTagsStatusToString error, " + e2.getMessage());
            e2.printStackTrace();
            return null;
        }
    }

    public static String f(UnRegisterStatus unRegisterStatus) {
        DebugLogger.i("StatusSerialize", "register status serialize unregisterStatusToString start, UnRegisterStatus=" + unRegisterStatus);
        try {
            JSONObject g2 = g(new JSONObject(), unRegisterStatus);
            g2.put(PushConstants.UNREGISTER_STATUS_IS_SUCCESS, unRegisterStatus.isUnRegisterSuccess());
            String jSONObject = g2.toString();
            DebugLogger.i("StatusSerialize", "register status serialize unregisterStatusToString success, statusText=" + jSONObject);
            return jSONObject;
        } catch (JSONException e2) {
            DebugLogger.e("StatusSerialize", "register status serialize unregisterStatusToString error, " + e2.getMessage());
            e2.printStackTrace();
            return null;
        }
    }

    private static JSONObject g(JSONObject jSONObject, BasicPushStatus basicPushStatus) throws JSONException {
        if (jSONObject == null || basicPushStatus == null) {
            return null;
        }
        jSONObject.put("code", basicPushStatus.getCode());
        jSONObject.put("message", basicPushStatus.getMessage());
        return jSONObject;
    }

    private static BasicPushStatus h(JSONObject jSONObject, BasicPushStatus basicPushStatus) throws JSONException {
        if (jSONObject == null || basicPushStatus == null) {
            return null;
        }
        if (!jSONObject.isNull("code")) {
            basicPushStatus.setCode(jSONObject.getString("code"));
        }
        if (!jSONObject.isNull("message")) {
            basicPushStatus.setMessage(jSONObject.getString("message"));
        }
        return basicPushStatus;
    }

    public static RegisterStatus i(String str) {
        DebugLogger.i("StatusSerialize", "register status serialize stringToRegisterStatus start, statusText=" + str);
        try {
            RegisterStatus registerStatus = new RegisterStatus();
            JSONObject jSONObject = new JSONObject(str);
            RegisterStatus registerStatus2 = (RegisterStatus) h(jSONObject, registerStatus);
            if (!jSONObject.isNull(PushConstants.REGISTER_STATUS_PUSH_ID)) {
                registerStatus2.setPushId(jSONObject.getString(PushConstants.REGISTER_STATUS_PUSH_ID));
            }
            if (!jSONObject.isNull(PushConstants.REGISTER_STATUS_EXPIRE_TIME)) {
                registerStatus2.setExpireTime(jSONObject.getInt(PushConstants.REGISTER_STATUS_EXPIRE_TIME));
            }
            DebugLogger.i("StatusSerialize", "register status serialize stringToRegisterStatus success, RegisterStatus=" + registerStatus2);
            return registerStatus2;
        } catch (JSONException e2) {
            DebugLogger.e("StatusSerialize", "register status serialize stringToRegisterStatus error, " + e2.getMessage());
            e2.printStackTrace();
            return null;
        }
    }

    public static SubAliasStatus j(String str) {
        DebugLogger.i("StatusSerialize", "register status serialize stringToSubAliasStatus start, statusText=" + str);
        try {
            SubAliasStatus subAliasStatus = new SubAliasStatus();
            JSONObject jSONObject = new JSONObject(str);
            SubAliasStatus subAliasStatus2 = (SubAliasStatus) h(jSONObject, subAliasStatus);
            if (!jSONObject.isNull(PushConstants.REGISTER_STATUS_PUSH_ID)) {
                subAliasStatus2.setPushId(jSONObject.getString(PushConstants.REGISTER_STATUS_PUSH_ID));
            }
            if (!jSONObject.isNull(PushConstants.SUB_ALIAS_STATUS_NAME)) {
                subAliasStatus2.setAlias(jSONObject.getString(PushConstants.SUB_ALIAS_STATUS_NAME));
            }
            DebugLogger.i("StatusSerialize", "register status serialize stringToSubAliasStatus success, SubAliasStatus=" + subAliasStatus2);
            return subAliasStatus2;
        } catch (JSONException e2) {
            DebugLogger.e("StatusSerialize", "register status serialize stringToSubAliasStatus error, " + e2.getMessage());
            e2.printStackTrace();
            return null;
        }
    }

    public static SubTagsStatus k(String str) {
        DebugLogger.i("StatusSerialize", "register status serialize stringToSubTagsStatus start, statusText=" + str);
        try {
            SubTagsStatus subTagsStatus = new SubTagsStatus();
            JSONObject jSONObject = new JSONObject(str);
            SubTagsStatus subTagsStatus2 = (SubTagsStatus) h(jSONObject, subTagsStatus);
            if (!jSONObject.isNull(PushConstants.REGISTER_STATUS_PUSH_ID)) {
                subTagsStatus2.setPushId(jSONObject.getString(PushConstants.REGISTER_STATUS_PUSH_ID));
            }
            if (!jSONObject.isNull("tag_list")) {
                JSONArray jSONArray = jSONObject.getJSONArray("tag_list");
                ArrayList arrayList = new ArrayList();
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                    subTagsStatus2.getClass();
                    SubTagsStatus.Tag tag = new SubTagsStatus.Tag();
                    if (!jSONObject2.isNull("tag_id")) {
                        tag.setTagId(jSONObject2.getInt("tag_id"));
                    }
                    if (!jSONObject2.isNull(PushConstants.SUB_TAGS_STATUS_NAME)) {
                        tag.setTagName(jSONObject2.getString(PushConstants.SUB_TAGS_STATUS_NAME));
                    }
                    arrayList.add(tag);
                }
                subTagsStatus2.setTagList(arrayList);
            }
            DebugLogger.i("StatusSerialize", "register status serialize stringToSubTagsStatus success, SubTagsStatus=" + subTagsStatus2);
            return subTagsStatus2;
        } catch (JSONException e2) {
            DebugLogger.e("StatusSerialize", "register status serialize stringToSubTagsStatus error, " + e2.getMessage());
            e2.printStackTrace();
            return null;
        }
    }

    public static UnRegisterStatus l(String str) {
        DebugLogger.i("StatusSerialize", "register status serialize stringToUnregisterStatus start, statusText=" + str);
        try {
            UnRegisterStatus unRegisterStatus = new UnRegisterStatus();
            JSONObject jSONObject = new JSONObject(str);
            UnRegisterStatus unRegisterStatus2 = (UnRegisterStatus) h(jSONObject, unRegisterStatus);
            if (!jSONObject.isNull(PushConstants.UNREGISTER_STATUS_IS_SUCCESS)) {
                unRegisterStatus2.setIsUnRegisterSuccess(jSONObject.getBoolean(PushConstants.UNREGISTER_STATUS_IS_SUCCESS));
            }
            DebugLogger.i("StatusSerialize", "register status serialize stringToUnregisterStatus success, UnRegisterStatus=" + unRegisterStatus2);
            return unRegisterStatus2;
        } catch (JSONException e2) {
            DebugLogger.e("StatusSerialize", "register status serialize stringToUnregisterStatus error, " + e2.getMessage());
            e2.printStackTrace();
            return null;
        }
    }
}
