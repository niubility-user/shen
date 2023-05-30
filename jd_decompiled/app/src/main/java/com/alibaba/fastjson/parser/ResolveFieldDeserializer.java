package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.util.TypeUtils;
import com.jd.framework.json.JDJSONArray;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class ResolveFieldDeserializer extends FieldDeserializer {
    private final Collection collection;
    private final int index;
    private final Object key;
    private final List list;
    private final Map map;
    private final DefaultJSONParser parser;

    public ResolveFieldDeserializer(DefaultJSONParser defaultJSONParser, List list, int i2) {
        super(null, null, 0);
        this.parser = defaultJSONParser;
        this.index = i2;
        this.list = list;
        this.key = null;
        this.map = null;
        this.collection = null;
    }

    @Override // com.alibaba.fastjson.parser.deserializer.FieldDeserializer
    public void parseField(DefaultJSONParser defaultJSONParser, Object obj, Type type, Map<String, Object> map) {
    }

    @Override // com.alibaba.fastjson.parser.deserializer.FieldDeserializer
    public void setValue(Object obj, Object obj2) {
        JDJSONArray jDJSONArray;
        Object relatedArray;
        Map map = this.map;
        if (map != null) {
            map.put(this.key, obj2);
            return;
        }
        Collection collection = this.collection;
        if (collection != null) {
            collection.add(obj2);
            return;
        }
        this.list.set(this.index, obj2);
        List list = this.list;
        if (!(list instanceof JDJSONArray) || (relatedArray = (jDJSONArray = (JDJSONArray) list).getRelatedArray()) == null || Array.getLength(relatedArray) <= this.index) {
            return;
        }
        if (jDJSONArray.getComponentType() != null) {
            obj2 = TypeUtils.cast(obj2, jDJSONArray.getComponentType(), this.parser.config);
        }
        Array.set(relatedArray, this.index, obj2);
    }

    public ResolveFieldDeserializer(Map map, Object obj) {
        super(null, null, 0);
        this.parser = null;
        this.index = -1;
        this.list = null;
        this.key = obj;
        this.map = map;
        this.collection = null;
    }

    public ResolveFieldDeserializer(Collection collection) {
        super(null, null, 0);
        this.parser = null;
        this.index = -1;
        this.list = null;
        this.key = null;
        this.map = null;
        this.collection = collection;
    }
}
