package cn.com.union.fido.util.uaf;

import cn.com.union.fido.bean.Version;
import cn.com.union.fido.bean.uafclient.Operation;
import cn.com.union.fido.bean.uafclient.OperationHeader;
import cn.com.union.fido.util.StringTools;
import cn.com.union.fido.util.UAFTools;

/* loaded from: classes.dex */
public class HeaderValid {
    /* JADX WARN: Removed duplicated region for block: B:32:0x0061  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static short validOPHeader(OperationHeader operationHeader, String str) {
        short s;
        int length;
        int length2;
        if (operationHeader != null) {
            Version upv = operationHeader.getUpv();
            if (upv != null) {
                Integer num = upv.minor;
                s = (num == null || num != UAFTools.getClientVersion().minor) ? (short) 6 : (short) 0;
                Integer num2 = upv.major;
                if (s == 0) {
                    if (num2 != null && num2 == UAFTools.getClientVersion().major) {
                        s = 0;
                    }
                }
                String op = operationHeader.getOp();
                if (s == 0 && (!StringTools.isValidateString(op) || (!op.equals(Operation.Reg.name()) && !op.equals(Operation.Auth.name()) && !op.equals(Operation.Dereg.name())))) {
                    s = 6;
                }
                String appID = operationHeader.getAppID();
                if (s == 0) {
                    if (appID != null && (length2 = appID.getBytes().length) <= 512) {
                        if (length2 > 0) {
                            if (appID.equalsIgnoreCase(str) || appID.indexOf("http") == 0) {
                                s = 0;
                            }
                        }
                    }
                    s = 6;
                }
                String serverData = operationHeader.getServerData();
                if (s == 0 || op.equalsIgnoreCase(Operation.Dereg.name())) {
                    return s;
                }
                if (StringTools.isValidateString(serverData) && (length = serverData.getBytes().length) > 0 && length <= 1536) {
                    return (short) 0;
                }
            }
            s = 6;
            String op2 = operationHeader.getOp();
            if (s == 0) {
                s = 6;
            }
            String appID2 = operationHeader.getAppID();
            if (s == 0) {
            }
            String serverData2 = operationHeader.getServerData();
            if (s == 0) {
            }
            return s;
        }
        return (short) 6;
    }
}
