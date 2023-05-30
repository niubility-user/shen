package com.alibaba.fastjson.parser;

import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.lang.reflect.Type;

/* loaded from: classes.dex */
public class ParseContext {
    public final Object fieldName;
    public Object object;
    public final ParseContext parent;
    private transient String path;
    public Type type;

    public ParseContext(ParseContext parseContext, Object obj, Object obj2) {
        this.parent = parseContext;
        this.object = obj;
        this.fieldName = obj2;
    }

    public String toString() {
        if (this.path == null) {
            if (this.parent == null) {
                this.path = "$";
            } else if (this.fieldName instanceof Integer) {
                this.path = this.parent.toString() + "[" + this.fieldName + "]";
            } else {
                this.path = this.parent.toString() + OrderISVUtil.MONEY_DECIMAL + this.fieldName;
            }
        }
        return this.path;
    }
}
