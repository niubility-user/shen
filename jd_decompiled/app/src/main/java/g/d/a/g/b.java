package g.d.a.g;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.heytap.mcssdk.constant.McsEventConstant$EventId;
import com.heytap.msp.push.mode.BaseMode;
import com.heytap.msp.push.mode.DataMessage;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class b extends c {
    @Override // g.d.a.g.d
    public BaseMode a(Context context, int i2, Intent intent) {
        if (4103 == i2 || 4098 == i2 || 4108 == i2) {
            BaseMode d = d(intent, i2);
            g.d.a.i.a.a(context, McsEventConstant$EventId.EVENT_ID_PUSH_TRANSMIT, (DataMessage) d);
            return d;
        }
        return null;
    }

    public String c(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return new JSONObject(str).optString("msg_command");
        } catch (JSONException e2) {
            g.d.a.j.d.a(e2.getMessage());
            return "";
        }
    }

    public BaseMode d(Intent intent, int i2) {
        try {
            DataMessage dataMessage = new DataMessage();
            dataMessage.setMessageID(g.d.a.j.b.e(intent.getStringExtra("messageID")));
            dataMessage.setTaskID(g.d.a.j.b.e(intent.getStringExtra("taskID")));
            dataMessage.setGlobalId(g.d.a.j.b.e(intent.getStringExtra("globalID")));
            dataMessage.setAppPackage(g.d.a.j.b.e(intent.getStringExtra("appPackage")));
            dataMessage.setTitle(g.d.a.j.b.e(intent.getStringExtra("title")));
            dataMessage.setContent(g.d.a.j.b.e(intent.getStringExtra("content")));
            dataMessage.setDescription(g.d.a.j.b.e(intent.getStringExtra("description")));
            String e2 = g.d.a.j.b.e(intent.getStringExtra("notifyID"));
            int i3 = 0;
            dataMessage.setNotifyID(TextUtils.isEmpty(e2) ? 0 : Integer.parseInt(e2));
            dataMessage.setMiniProgramPkg(g.d.a.j.b.e(intent.getStringExtra("miniProgramPkg")));
            dataMessage.setMessageType(i2);
            dataMessage.setEventId(g.d.a.j.b.e(intent.getStringExtra("eventId")));
            dataMessage.setStatisticsExtra(g.d.a.j.b.e(intent.getStringExtra("statistics_extra")));
            String e3 = g.d.a.j.b.e(intent.getStringExtra("data_extra"));
            dataMessage.setDataExtra(e3);
            String c2 = c(e3);
            if (!TextUtils.isEmpty(c2)) {
                i3 = Integer.parseInt(c2);
            }
            dataMessage.setMsgCommand(i3);
            dataMessage.setBalanceTime(g.d.a.j.b.e(intent.getStringExtra("balanceTime")));
            dataMessage.setStartDate(g.d.a.j.b.e(intent.getStringExtra("startDate")));
            dataMessage.setEndDate(g.d.a.j.b.e(intent.getStringExtra("endDate")));
            dataMessage.setTimeRanges(g.d.a.j.b.e(intent.getStringExtra("timeRanges")));
            dataMessage.setRule(g.d.a.j.b.e(intent.getStringExtra("rule")));
            dataMessage.setForcedDelivery(g.d.a.j.b.e(intent.getStringExtra("forcedDelivery")));
            dataMessage.setDistinctContent(g.d.a.j.b.e(intent.getStringExtra("distinctBycontent")));
            dataMessage.setAppId(g.d.a.j.b.e(intent.getStringExtra("appID")));
            return dataMessage;
        } catch (Exception e4) {
            g.d.a.j.d.a("OnHandleIntent--" + e4.getMessage());
            return null;
        }
    }
}
