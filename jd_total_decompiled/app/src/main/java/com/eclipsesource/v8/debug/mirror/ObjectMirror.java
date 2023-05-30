package com.eclipsesource.v8.debug.mirror;

import com.eclipsesource.v8.V8Array;
import com.eclipsesource.v8.V8Object;

/* loaded from: classes.dex */
public class ObjectMirror extends ValueMirror {
    private static final String PROPERTIES = "properties";
    private static final String PROPERTY_NAMES = "propertyNames";

    /* loaded from: classes.dex */
    public enum PropertyKind {
        Named(1),
        Indexed(2);
        
        int index;

        PropertyKind(int i2) {
            this.index = i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ObjectMirror(V8Object v8Object) {
        super(v8Object);
    }

    public PropertiesArray getProperties(PropertyKind propertyKind, int i2) {
        V8Array v8Array = new V8Array(this.v8Object.getRuntime());
        v8Array.push(propertyKind.index);
        v8Array.push(i2);
        V8Array v8Array2 = null;
        try {
            v8Array2 = this.v8Object.executeArrayFunction(PROPERTIES, v8Array);
            return new PropertiesArray(v8Array2);
        } finally {
            v8Array.close();
            if (v8Array2 != null && !v8Array2.isReleased()) {
                v8Array2.close();
            }
        }
    }

    public String[] getPropertyNames(PropertyKind propertyKind, int i2) {
        V8Array v8Array = new V8Array(this.v8Object.getRuntime());
        v8Array.push(propertyKind.index);
        v8Array.push(i2);
        V8Array v8Array2 = null;
        try {
            v8Array2 = this.v8Object.executeArrayFunction(PROPERTY_NAMES, v8Array);
            int length = v8Array2.length();
            String[] strArr = new String[length];
            for (int i3 = 0; i3 < length; i3++) {
                strArr[i3] = v8Array2.getString(i3);
            }
            return strArr;
        } finally {
            v8Array.close();
            if (v8Array2 != null) {
                v8Array2.close();
            }
        }
    }

    @Override // com.eclipsesource.v8.debug.mirror.Mirror
    public boolean isObject() {
        return true;
    }

    @Override // com.eclipsesource.v8.debug.mirror.Mirror
    public String toString() {
        return this.v8Object.toString();
    }
}
