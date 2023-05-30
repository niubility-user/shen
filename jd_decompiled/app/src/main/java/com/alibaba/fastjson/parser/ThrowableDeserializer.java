package com.alibaba.fastjson.parser;

/* loaded from: classes.dex */
public class ThrowableDeserializer extends JavaBeanDeserializer {
    public ThrowableDeserializer(ParserConfig parserConfig, Class<?> cls) {
        super(parserConfig, cls, cls);
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x01a1, code lost:
        if (r4 == null) goto L121;
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x01a3, code lost:
        r4.setValue(r3, r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x01a7, code lost:
        return (T) r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x01a8, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x01b0, code lost:
        throw new com.alibaba.fastjson.JSONException("create instance error", r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0033, code lost:
        if (java.lang.Throwable.class.isAssignableFrom(r2) != false) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00d5, code lost:
        if (r2 != null) goto L110;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00d7, code lost:
        r3 = (T) new java.lang.Exception(r12, r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00de, code lost:
        r4 = r2.getConstructors();
        r6 = r4.length;
        r10 = r7;
        r11 = r10;
        r14 = r11;
        r9 = 0;
        r10 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00e8, code lost:
        if (r9 >= r6) goto L123;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00ea, code lost:
        r17 = r4[r9];
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00f1, code lost:
        if (r17.getParameterTypes().length != 0) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00f3, code lost:
        r14 = r17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00fb, code lost:
        if (r17.getParameterTypes().length != 1) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0105, code lost:
        if (r17.getParameterTypes()[0] != java.lang.String.class) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0107, code lost:
        r11 = r17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0110, code lost:
        if (r17.getParameterTypes().length != 2) goto L127;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0119, code lost:
        if (r17.getParameterTypes()[0] != java.lang.String.class) goto L128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0124, code lost:
        if (r17.getParameterTypes()[1] != java.lang.Throwable.class) goto L129;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0126, code lost:
        r10 = r17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x0128, code lost:
        r9 = r9 + 1;
        r10 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x012c, code lost:
        if (r10 == 0) goto L78;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x012e, code lost:
        r3 = (T) ((java.lang.Throwable) r10.newInstance(r12, r8));
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x013e, code lost:
        if (r11 == null) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0140, code lost:
        r3 = (java.lang.Throwable) r11.newInstance(r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x014d, code lost:
        if (r14 == null) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x014f, code lost:
        r3 = (java.lang.Throwable) r14.newInstance(new java.lang.Object[0]);
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0159, code lost:
        r3 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x015a, code lost:
        if (r3 != null) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x015c, code lost:
        r3 = (T) new java.lang.Exception(r12, r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x0161, code lost:
        if (r13 == null) goto L87;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0163, code lost:
        ((java.lang.Throwable) r3).setStackTrace(r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0166, code lost:
        if (r5 == null) goto L102;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x0168, code lost:
        if (r2 == null) goto L95;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x016c, code lost:
        if (r2 != r18.clazz) goto L92;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x016e, code lost:
        r7 = r18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x0170, code lost:
        r0 = r19.config.getDeserializer(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x0178, code lost:
        if ((r0 instanceof com.alibaba.fastjson.parser.JavaBeanDeserializer) == false) goto L95;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x017a, code lost:
        r7 = (com.alibaba.fastjson.parser.JavaBeanDeserializer) r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x017e, code lost:
        r7 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x017f, code lost:
        r0 = r5.entrySet().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x018b, code lost:
        if (r0.hasNext() == false) goto L119;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x018d, code lost:
        r2 = (java.util.Map.Entry) r0.next();
        r2 = r2.getValue();
        r4 = r7.getFieldDeserializer((java.lang.String) r2.getKey());
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v6 */
    @Override // com.alibaba.fastjson.parser.JavaBeanDeserializer, com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public <T> T deserialze(com.alibaba.fastjson.parser.DefaultJSONParser r19, java.lang.reflect.Type r20, java.lang.Object r21) {
        /*
            Method dump skipped, instructions count: 444
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.ThrowableDeserializer.deserialze(com.alibaba.fastjson.parser.DefaultJSONParser, java.lang.reflect.Type, java.lang.Object):java.lang.Object");
    }
}
