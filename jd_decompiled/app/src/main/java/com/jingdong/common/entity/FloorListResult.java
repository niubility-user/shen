package com.jingdong.common.entity;

import com.jingdong.common.jdmiaosha.preload.BottomNavigationConstant;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class FloorListResult {
    public int code;
    public ArrayList<Floor> floors;

    /* loaded from: classes5.dex */
    public class Floor {
        public String create;
        public int floorId;
        public String floorName;
        public int floorSortNo;
        public String modify;
        public int status;
        public int templeType;

        Floor(JSONObject jSONObject) {
            if (jSONObject != null) {
                this.floorId = jSONObject.optInt("floorId");
                this.floorSortNo = jSONObject.optInt("floorSortNo");
                this.floorName = jSONObject.optString(BottomNavigationConstant.KEY_FLOOR_NAME);
                this.templeType = jSONObject.optInt("templeType");
                this.status = jSONObject.optInt("status");
                this.create = jSONObject.optString("create");
                this.modify = jSONObject.optString("modify");
            }
        }
    }

    public FloorListResult(JSONObjectProxy jSONObjectProxy) {
        this.code = -1;
        if (jSONObjectProxy != null) {
            try {
                this.code = jSONObjectProxy.getInt("code");
            } catch (JSONException unused) {
                this.code = -1;
            }
            this.floors = new ArrayList<>(0);
            JSONArray optJSONArray = jSONObjectProxy.optJSONArray("floorList");
            if (optJSONArray == null || optJSONArray.length() <= 0) {
                return;
            }
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i2);
                if (optJSONObject != null) {
                    this.floors.add(new Floor(optJSONObject));
                }
            }
        }
    }

    public boolean hasFloor() {
        ArrayList<Floor> arrayList;
        return this.code == 0 && (arrayList = this.floors) != null && arrayList.size() > 0;
    }
}
