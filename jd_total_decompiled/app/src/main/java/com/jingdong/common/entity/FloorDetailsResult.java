package com.jingdong.common.entity;

import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.jdmiaosha.preload.BottomNavigationConstant;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class FloorDetailsResult {
    private static final String TAG = "FloorDetailsResult";
    public int code;
    public int floorCnt;
    public ArrayList<FloorModel> floorModels;

    /* loaded from: classes5.dex */
    public static class ColItem implements Comparable<ColItem> {
        public int colNum;
        public String functionId;
        public String param;
        public int sortNum;
        public String title;
        public int type;
        public String url;

        ColItem(JSONObject jSONObject) {
            if (jSONObject != null) {
                this.colNum = jSONObject.optInt("colNum");
                this.title = jSONObject.optString("title");
                this.url = jSONObject.optString("url");
                this.sortNum = jSONObject.optInt("sortNum");
                this.functionId = jSONObject.optString("functionId");
                this.type = jSONObject.optInt("type");
                this.param = jSONObject.optString("param");
            }
        }

        public JSONObjectProxy getParamsJson() {
            JSONObjectProxy jSONObjectProxy = new JSONObjectProxy();
            if (!TextUtils.isEmpty(this.param)) {
                for (String str : this.param.trim().split(DYConstants.DY_REGEX_COMMA)) {
                    String[] split = str.trim().split(ContainerUtils.KEY_VALUE_DELIMITER);
                    if (split.length >= 2) {
                        try {
                            jSONObjectProxy.put(split[0], split[1]);
                        } catch (JSONException e2) {
                            if (OKLog.E) {
                                OKLog.e(FloorDetailsResult.TAG, e2);
                            }
                        }
                    }
                }
            }
            return jSONObjectProxy;
        }

        @Override // java.lang.Comparable
        public int compareTo(ColItem colItem) {
            if (colItem == null) {
                return 0;
            }
            int i2 = this.colNum;
            int i3 = colItem.colNum;
            if (i2 < i3) {
                return -1;
            }
            if (i2 > i3) {
                return 1;
            }
            int i4 = this.sortNum;
            int i5 = colItem.sortNum;
            if (i4 < i5) {
                return -1;
            }
            return i4 > i5 ? 1 : 0;
        }
    }

    /* loaded from: classes5.dex */
    public class FloorModel {
        public int colCnt;
        public int floorId;
        public String floorName;
        public ArrayList<ColItem[]> items;

        FloorModel(JSONObject jSONObject) {
            int length;
            if (jSONObject != null) {
                this.floorId = jSONObject.optInt("floorId");
                this.floorName = jSONObject.optString(BottomNavigationConstant.KEY_FLOOR_NAME);
                int optInt = jSONObject.optInt("colCnt");
                this.colCnt = optInt;
                if (optInt > 0) {
                    this.items = new ArrayList<>(this.colCnt);
                    JSONArray optJSONArray = jSONObject.optJSONArray("childModelList");
                    if (optJSONArray == null || (length = optJSONArray.length()) <= 0) {
                        return;
                    }
                    ArrayList arrayList = new ArrayList(length);
                    int i2 = 0;
                    for (int i3 = 0; i3 < length; i3++) {
                        JSONObject optJSONObject = optJSONArray.optJSONObject(i3);
                        if (optJSONObject != null) {
                            arrayList.add(new ColItem(optJSONObject));
                        }
                    }
                    Collections.sort(arrayList);
                    ArrayList arrayList2 = new ArrayList(2);
                    Iterator it = arrayList.iterator();
                    int i4 = 0;
                    while (it.hasNext()) {
                        ColItem colItem = (ColItem) it.next();
                        if (colItem.colNum != i4) {
                            if (arrayList2.size() > 0) {
                                ColItem[] colItemArr = new ColItem[arrayList2.size()];
                                Iterator it2 = arrayList2.iterator();
                                int i5 = 0;
                                while (it2.hasNext()) {
                                    colItemArr[i5] = (ColItem) it2.next();
                                    i5++;
                                }
                                this.items.add(colItemArr);
                                arrayList2.clear();
                            }
                            i4 = colItem.colNum;
                        }
                        arrayList2.add(colItem);
                    }
                    if (arrayList2.size() > 0) {
                        ColItem[] colItemArr2 = new ColItem[arrayList2.size()];
                        Iterator it3 = arrayList2.iterator();
                        while (it3.hasNext()) {
                            colItemArr2[i2] = (ColItem) it3.next();
                            i2++;
                        }
                        this.items.add(colItemArr2);
                        arrayList2.clear();
                    }
                }
            }
        }
    }

    public FloorDetailsResult(JSONObjectProxy jSONObjectProxy) {
        int length;
        this.code = -1;
        this.floorCnt = 0;
        if (jSONObjectProxy != null) {
            try {
                this.code = jSONObjectProxy.getInt("code");
            } catch (JSONException unused) {
                this.code = -1;
            }
            int optInt = jSONObjectProxy.optInt("floorCnt");
            this.floorCnt = optInt;
            if (optInt > 0) {
                this.floorModels = new ArrayList<>(this.floorCnt);
                JSONArray optJSONArray = jSONObjectProxy.optJSONArray("modelFloorList");
                if (optJSONArray == null || (length = optJSONArray.length()) <= 0) {
                    return;
                }
                this.floorCnt = length;
                for (int i2 = 0; i2 < this.floorCnt; i2++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i2);
                    if (optJSONObject != null) {
                        this.floorModels.add(new FloorModel(optJSONObject));
                    }
                }
            }
        }
    }

    public boolean hasDetails() {
        return this.code == 0 && this.floorCnt > 0;
    }
}
