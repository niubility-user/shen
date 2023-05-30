package com.jd.viewkit.templates.model.event;

import com.jd.viewkit.tool.JSONTool;
import com.jd.viewkit.tool.StringTool;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class JDViewKitVirtualChainEvent extends JDViewKitVirtualEvent {
    public static String eventStartKey = "eventStart";
    public static String typeKey = "type";
    private JSONObject eventJsonObject;
    private String eventStart;
    private String type;

    public JDViewKitVirtualChainEvent(JSONObject jSONObject) {
        super(jSONObject);
        setType(JSONTool.getString(jSONObject, typeKey));
        setEventStart(JSONTool.getString(jSONObject, eventStartKey));
        setEventJsonObject(jSONObject);
    }

    public JDViewKitVirtualEvent getEventByKey(String str) {
        JSONObject jSONObject;
        if (this.eventJsonObject == null || !StringTool.isNotEmpty(str) || (jSONObject = JSONTool.getJSONObject(this.eventJsonObject, str)) == null) {
            return null;
        }
        return new JDViewKitVirtualEvent(jSONObject);
    }

    public JSONObject getEventJsonObject() {
        return this.eventJsonObject;
    }

    public String getEventStart() {
        return this.eventStart;
    }

    @Override // com.jd.viewkit.templates.model.event.JDViewKitVirtualEvent
    public String getType() {
        return this.type;
    }

    public void setEventJsonObject(JSONObject jSONObject) {
        this.eventJsonObject = jSONObject;
    }

    public void setEventStart(String str) {
        this.eventStart = str;
    }

    @Override // com.jd.viewkit.templates.model.event.JDViewKitVirtualEvent
    public void setType(String str) {
        this.type = str;
    }
}
