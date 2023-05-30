package com.jingdong.app.mall.bundle.styleinfoview.temp;

import android.text.TextUtils;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.bundle.styleinfoview.floor.FloorConstant;
import com.jingdong.corelib.utils.Log;
import com.jingdong.sdk.platform.floor.entity.BaseTemplateEntity;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes3.dex */
public class FloorTemplateEntity {
    public ArrayList<BaseTemplateEntity> templates;

    public FloorTemplateEntity(JDJSONArray jDJSONArray, JDJSONObject jDJSONObject) {
        parseTemplates(jDJSONArray, jDJSONObject);
    }

    private FloorTemplate parseTemplate(JDJSONObject jDJSONObject) {
        Class<?> cls;
        String optString = jDJSONObject.optString("mId");
        if (TextUtils.isEmpty(optString)) {
            return null;
        }
        String str = FloorConstant.TEMPLATE_ID_CLASS.get(optString);
        if (TextUtils.isEmpty(str)) {
            str = FloorTemplate.class.getName();
        }
        try {
            cls = Class.forName(str);
        } catch (Exception e2) {
            if (Log.D) {
                Log.d("FloorTemplateEntity", e2.getMessage());
            }
            cls = null;
        }
        if (Log.D) {
            Log.d("FloorTemplateEntity", "createTemplate by Constructor" + str);
        }
        try {
            Constructor<?> constructor = cls.getConstructors()[0];
            constructor.setAccessible(true);
            return (FloorTemplate) constructor.newInstance(jDJSONObject);
        } catch (Exception unused) {
            if (Log.D) {
                Log.d("FloorTemplateEntity", "json = " + jDJSONObject.toString());
                return null;
            }
            return null;
        }
    }

    private void parseTemplates(JDJSONArray jDJSONArray, JDJSONObject jDJSONObject) {
        int size;
        FloorTemplate parseTemplate;
        JDJSONObject optJSONObject;
        if (jDJSONArray == null || (size = jDJSONArray.size()) <= 0) {
            return;
        }
        this.templates = new ArrayList<>(size);
        for (int i2 = 0; i2 < size; i2++) {
            JDJSONObject optJSONObject2 = jDJSONArray.optJSONObject(i2);
            if (optJSONObject2 != null && (parseTemplate = parseTemplate(optJSONObject2)) != null) {
                if (FloorConstant.isCommonFloor(parseTemplate.mId)) {
                    if (jDJSONObject != null && (optJSONObject = jDJSONObject.optJSONObject(parseTemplate.bId)) != null && parseTemplate.parseCommonData(optJSONObject)) {
                        this.templates.add(parseTemplate);
                    }
                } else {
                    this.templates.add(parseTemplate);
                }
            }
        }
    }

    public FloorTemplate getTemplateById(String str) {
        if (hasTemplate()) {
            Iterator<BaseTemplateEntity> it = this.templates.iterator();
            while (it.hasNext()) {
                BaseTemplateEntity next = it.next();
                if (next != null && TextUtils.equals(next.mId, str) && (next instanceof FloorTemplate)) {
                    return (FloorTemplate) next;
                }
            }
            return null;
        }
        return null;
    }

    public boolean hasTemplate() {
        ArrayList<BaseTemplateEntity> arrayList = this.templates;
        return (arrayList == null || arrayList.isEmpty()) ? false : true;
    }

    public boolean hasTemplateByType(String str) {
        if (hasTemplate()) {
            Iterator<BaseTemplateEntity> it = this.templates.iterator();
            while (it.hasNext()) {
                BaseTemplateEntity next = it.next();
                if (!TextUtils.isEmpty(str) && TextUtils.equals(str, next.mId)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }
}
