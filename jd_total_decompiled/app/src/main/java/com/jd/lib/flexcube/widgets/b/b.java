package com.jd.lib.flexcube.widgets.b;

import com.jd.lib.babel.ifloor.utils.CommonServiceUtil;
import com.jd.lib.babel.servicekit.iservice.IParser;
import com.jd.lib.babel.servicekit.util.BabelServiceUtils;
import com.jd.lib.flexcube.iwidget.entity.material.ClickEvent;
import com.jd.lib.flexcube.iwidget.entity.material.ExposureInfo;
import com.jd.lib.flexcube.iwidget.entity.material.MaterialModel;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class b {
    static {
        Pattern.compile("^#(\\w+)\\{\\{(.+)\\}\\}$");
        Pattern.compile("^\\$(\\w+)(==|>=|<=|<|>|!=)(.+)");
        Pattern.compile(".*['|\"].+['|\"].*");
        Pattern.compile("\\S+\\[[0-9]+\\]");
        Pattern.compile("(.*)\\[(.*)\\]");
        Pattern.compile("^[\\d]*$");
        Pattern.compile("^[-\\+]?[.\\d]*$");
    }

    public static ClickEvent a(Map<String, String> map, String str) {
        if (com.jd.lib.flexcube.iwidget.b.c.c(str)) {
            return null;
        }
        if (!str.substring(0, 1).equals("$")) {
            try {
                return (ClickEvent) CommonServiceUtil.parseObject(str, ClickEvent.class);
            } catch (Exception unused) {
                return null;
            }
        } else if (map == null) {
            return null;
        } else {
            try {
                String[] split = str.substring(1).split("\\.");
                if (split.length <= 1) {
                    return (ClickEvent) CommonServiceUtil.parseObject(map.get(str.substring(1)), ClickEvent.class);
                }
                JSONObject jSONObject = new JSONObject(map.get(split[0]));
                int length = split.length;
                String str2 = null;
                for (int i2 = 1; i2 < length; i2++) {
                    if (i2 == length - 1 && jSONObject != null) {
                        str2 = jSONObject.optString(split[i2]);
                    } else if (jSONObject != null) {
                        jSONObject = jSONObject.optJSONObject(split[i2]);
                    }
                }
                return (ClickEvent) CommonServiceUtil.parseObject(str2, ClickEvent.class);
            } catch (Exception unused2) {
                return null;
            }
        }
    }

    public static ExposureInfo b(Map<String, String> map, String str) {
        if (com.jd.lib.flexcube.iwidget.b.c.c(str)) {
            return null;
        }
        if (!str.substring(0, 1).equals("$")) {
            try {
                return (ExposureInfo) CommonServiceUtil.parseObject(str, ExposureInfo.class);
            } catch (Exception unused) {
                return null;
            }
        } else if (map == null) {
            return null;
        } else {
            try {
                String[] split = str.substring(1).split("\\.");
                if (split.length <= 1) {
                    return (ExposureInfo) CommonServiceUtil.parseObject(map.get(str.substring(1)), ExposureInfo.class);
                }
                JSONObject jSONObject = new JSONObject(map.get(split[0]));
                int length = split.length;
                String str2 = null;
                for (int i2 = 1; i2 < length; i2++) {
                    if (i2 == length - 1 && jSONObject != null) {
                        str2 = jSONObject.optString(split[i2]);
                    } else if (jSONObject != null) {
                        jSONObject = jSONObject.optJSONObject(split[i2]);
                    }
                }
                return (ExposureInfo) CommonServiceUtil.parseObject(str2, ExposureInfo.class);
            } catch (Exception unused2) {
                return null;
            }
        }
    }

    public static Object c(String str, Map<String, String> map) {
        IParser iParser;
        try {
            if (com.jd.lib.flexcube.iwidget.b.c.c(str) || !com.jd.lib.flexcube.iwidget.b.c.b(str, "$")) {
                return str;
            }
            if (map != null && (map instanceof Map)) {
                String[] split = str.substring(1).split("\\.");
                if (split != null && split.length >= 1) {
                    String str2 = map.get(split[0]);
                    if (com.jd.lib.flexcube.iwidget.b.c.d(str2) && (iParser = (IParser) BabelServiceUtils.getService(IParser.class)) != null) {
                        Object string2Object = iParser.string2Object(str2);
                        for (int i2 = 1; i2 < split.length; i2++) {
                            string2Object = (string2Object == null || !(string2Object instanceof Map)) ? null : ((Map) string2Object).get(split[i2]);
                        }
                        return string2Object;
                    }
                }
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    public static String d(Map<String, String> map, String str) {
        if (com.jd.lib.flexcube.iwidget.b.c.c(str)) {
            return null;
        }
        if (str.substring(0, 1).equals("$")) {
            if (map == null) {
                return null;
            }
            if (!str.contains(OrderISVUtil.MONEY_DECIMAL)) {
                return map.get(str.substring(1, str.length()));
            }
            Object c2 = c(str, map);
            if (c2 != null) {
                return c2.toString();
            }
            return null;
        }
        return str;
    }

    public static List<MaterialModel> e(Map<String, String> map, String str) {
        if (com.jd.lib.flexcube.iwidget.b.c.c(str)) {
            return null;
        }
        if (!str.substring(0, 1).equals("$")) {
            try {
                return CommonServiceUtil.parseArray(str, MaterialModel.class);
            } catch (Exception unused) {
                return null;
            }
        } else if (map == null) {
            return null;
        } else {
            try {
                String[] split = str.substring(1).split("\\.");
                if (split.length <= 1) {
                    return CommonServiceUtil.parseArray(map.get(str.substring(1)), MaterialModel.class);
                }
                JSONObject jSONObject = new JSONObject(map.get(split[0]));
                int length = split.length;
                String str2 = null;
                for (int i2 = 1; i2 < length; i2++) {
                    if (i2 == length - 1 && jSONObject != null) {
                        str2 = jSONObject.optString(split[i2]);
                    } else if (jSONObject != null) {
                        jSONObject = jSONObject.optJSONObject(split[i2]);
                    }
                }
                return CommonServiceUtil.parseArray(str2, MaterialModel.class);
            } catch (Exception unused2) {
                return null;
            }
        }
    }

    public static <T> List<T> f(Map<String, String> map, String str, Class<T> cls) {
        if (com.jd.lib.flexcube.iwidget.b.c.c(str)) {
            return null;
        }
        if (!str.substring(0, 1).equals("$")) {
            try {
                return CommonServiceUtil.parseArray(str, cls);
            } catch (Exception unused) {
                return null;
            }
        } else if (map == null) {
            return null;
        } else {
            try {
                String[] split = str.substring(1).split("\\.");
                if (split.length <= 1) {
                    return CommonServiceUtil.parseArray(map.get(str.substring(1)), cls);
                }
                JSONObject jSONObject = new JSONObject(map.get(split[0]));
                int length = split.length;
                String str2 = null;
                for (int i2 = 1; i2 < length; i2++) {
                    if (i2 == length - 1 && jSONObject != null) {
                        str2 = jSONObject.optString(split[i2]);
                    } else if (jSONObject != null) {
                        jSONObject = jSONObject.optJSONObject(split[i2]);
                    }
                }
                return CommonServiceUtil.parseArray(str2, cls);
            } catch (Exception unused2) {
                return null;
            }
        }
    }
}
