package cn.com.union.fido.util.uaf;

/* loaded from: classes.dex */
public class HeaderValid {
    /* JADX WARN: Removed duplicated region for block: B:32:0x0061  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static short validOPHeader(cn.com.union.fido.bean.uafclient.OperationHeader r7, java.lang.String r8) {
        /*
            r0 = 0
            r1 = 6
            if (r7 == 0) goto La5
            cn.com.union.fido.bean.Version r2 = r7.getUpv()
            if (r2 == 0) goto L29
            java.lang.Integer r3 = r2.minor
            if (r3 == 0) goto L18
            cn.com.union.fido.bean.Version r4 = cn.com.union.fido.util.UAFTools.getClientVersion()
            java.lang.Integer r4 = r4.minor
            if (r3 != r4) goto L18
            r3 = 0
            goto L19
        L18:
            r3 = 6
        L19:
            java.lang.Integer r2 = r2.major
            if (r3 != 0) goto L2a
            if (r2 == 0) goto L29
            cn.com.union.fido.bean.Version r3 = cn.com.union.fido.util.UAFTools.getClientVersion()
            java.lang.Integer r3 = r3.major
            if (r2 != r3) goto L29
            r3 = 0
            goto L2a
        L29:
            r3 = 6
        L2a:
            java.lang.String r2 = r7.getOp()
            if (r3 != 0) goto L5b
            boolean r4 = cn.com.union.fido.util.StringTools.isValidateString(r2)
            if (r4 == 0) goto L5a
            cn.com.union.fido.bean.uafclient.Operation r4 = cn.com.union.fido.bean.uafclient.Operation.Reg
            java.lang.String r4 = r4.name()
            boolean r4 = r2.equals(r4)
            if (r4 != 0) goto L5b
            cn.com.union.fido.bean.uafclient.Operation r4 = cn.com.union.fido.bean.uafclient.Operation.Auth
            java.lang.String r4 = r4.name()
            boolean r4 = r2.equals(r4)
            if (r4 != 0) goto L5b
            cn.com.union.fido.bean.uafclient.Operation r4 = cn.com.union.fido.bean.uafclient.Operation.Dereg
            java.lang.String r4 = r4.name()
            boolean r4 = r2.equals(r4)
            if (r4 != 0) goto L5b
        L5a:
            r3 = 6
        L5b:
            java.lang.String r4 = r7.getAppID()
            if (r3 != 0) goto L7f
            if (r4 == 0) goto L7e
            byte[] r5 = r4.getBytes()
            int r5 = r5.length
            r6 = 512(0x200, float:7.175E-43)
            if (r5 > r6) goto L7e
            if (r5 <= 0) goto L7f
            boolean r8 = r4.equalsIgnoreCase(r8)
            if (r8 != 0) goto L7c
            java.lang.String r8 = "http"
            int r8 = r4.indexOf(r8)
            if (r8 != 0) goto L7e
        L7c:
            r3 = 0
            goto L7f
        L7e:
            r3 = 6
        L7f:
            java.lang.String r7 = r7.getServerData()
            if (r3 != 0) goto La3
            cn.com.union.fido.bean.uafclient.Operation r8 = cn.com.union.fido.bean.uafclient.Operation.Dereg
            java.lang.String r8 = r8.name()
            boolean r8 = r2.equalsIgnoreCase(r8)
            if (r8 != 0) goto La3
            boolean r8 = cn.com.union.fido.util.StringTools.isValidateString(r7)
            if (r8 == 0) goto La5
            byte[] r7 = r7.getBytes()
            int r7 = r7.length
            if (r7 <= 0) goto La5
            r8 = 1536(0x600, float:2.152E-42)
            if (r7 > r8) goto La5
            goto La6
        La3:
            r0 = r3
            goto La6
        La5:
            r0 = 6
        La6:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.union.fido.util.uaf.HeaderValid.validOPHeader(cn.com.union.fido.bean.uafclient.OperationHeader, java.lang.String):short");
    }
}
