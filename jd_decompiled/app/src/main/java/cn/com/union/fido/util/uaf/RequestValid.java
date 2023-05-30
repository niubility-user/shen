package cn.com.union.fido.util.uaf;

import cn.com.union.fido.bean.uafclient.DeregisterAuthenticator;
import cn.com.union.fido.bean.uafclient.DeregisterRequest;
import cn.com.union.fido.bean.uafclient.OperationRequest;
import cn.com.union.fido.bean.uafclient.RegistrationRequest;
import cn.com.union.fido.bean.uafclient.policy.MatchCriteria;
import cn.com.union.fido.bean.uafclient.policy.Policy;
import cn.com.union.fido.util.Base64Tools;
import cn.com.union.fido.util.CommonTools;
import cn.com.union.fido.util.StringTools;
import com.jdjr.risk.jdcn.common.utils.FsGsonUtil;
import java.util.List;

/* loaded from: classes.dex */
public class RequestValid {
    public static OperationRequest parseOPRequestMessage(String str) {
        try {
            return (OperationRequest) FsGsonUtil.gsonToBean(str, OperationRequest.class);
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x002a, code lost:
        if (cn.com.union.fido.util.StringTools.isHexNumberRex(r4[1]) != false) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean validAAID(java.lang.String r4) {
        /*
            r0 = 1
            r1 = 0
            if (r4 == 0) goto L2d
            java.lang.String r2 = r4.trim()     // Catch: java.lang.Exception -> L2f
            int r2 = r2.length()     // Catch: java.lang.Exception -> L2f
            r3 = 9
            if (r2 != r3) goto L2d
            java.lang.String r2 = "#"
            java.lang.String[] r4 = r4.split(r2)     // Catch: java.lang.Exception -> L2f
            if (r4 == 0) goto L2d
            int r2 = r4.length     // Catch: java.lang.Exception -> L2f
            r3 = 2
            if (r2 != r3) goto L2d
            r2 = r4[r1]     // Catch: java.lang.Exception -> L2f
            boolean r2 = cn.com.union.fido.util.StringTools.isHexNumberRex(r2)     // Catch: java.lang.Exception -> L2f
            if (r2 == 0) goto L2d
            r4 = r4[r0]     // Catch: java.lang.Exception -> L2f
            boolean r4 = cn.com.union.fido.util.StringTools.isHexNumberRex(r4)     // Catch: java.lang.Exception -> L2f
            if (r4 == 0) goto L2d
            goto L2e
        L2d:
            r0 = 0
        L2e:
            r1 = r0
        L2f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.union.fido.util.uaf.RequestValid.validAAID(java.lang.String):boolean");
    }

    public static boolean validKeyID(String str) {
        if (str != null) {
            try {
                if (Base64Tools.isBase64(str.getBytes())) {
                    int length = StringTools.urlSafeBase64Dec(str).length;
                    return length >= 32 && length <= 2048;
                }
                return false;
            } catch (Exception unused) {
                return false;
            }
        }
        return false;
    }

    public static short validOPRequest(OperationRequest operationRequest) {
        short s;
        List<List<MatchCriteria>> accepted;
        List<MatchCriteria> list;
        int length;
        int length2;
        if (operationRequest != null) {
            if (operationRequest instanceof DeregisterRequest) {
                List<DeregisterAuthenticator> authenticators = ((DeregisterRequest) operationRequest).getAuthenticators();
                if (!CommonTools.isValidateList(authenticators)) {
                    return (short) 6;
                }
                s = 6;
                for (DeregisterAuthenticator deregisterAuthenticator : authenticators) {
                    if (deregisterAuthenticator == null) {
                        return (short) 6;
                    }
                    String aaid = deregisterAuthenticator.getAaid();
                    String keyID = deregisterAuthenticator.getKeyID();
                    if (!StringTools.isValidateString(aaid) || !StringTools.isValidateString(keyID) || !validAAID(aaid) || !validKeyID(keyID)) {
                        return (short) 6;
                    }
                    s = 0;
                }
            } else {
                String challenge = operationRequest.getChallenge();
                s = (!StringTools.isValidateString(challenge) || !Base64Tools.isBase64(challenge.getBytes()) || (length2 = challenge.getBytes().length) < 8 || length2 > 64) ? (short) 6 : (short) 0;
                if (operationRequest instanceof RegistrationRequest) {
                    String username = ((RegistrationRequest) operationRequest).getUsername();
                    if (s == 0) {
                        s = (!StringTools.isValidateString(username) || (length = username.getBytes().length) <= 0 || length > 128) ? (short) 6 : (short) 0;
                    }
                }
                Policy policy = operationRequest.getPolicy();
                if (s == 0) {
                    if (policy == null || (accepted = policy.getAccepted()) == null || accepted.isEmpty() || (list = accepted.get(0)) == null || list.isEmpty()) {
                        return (short) 6;
                    }
                    for (MatchCriteria matchCriteria : list) {
                        if (CommonTools.isValidateList(matchCriteria.getAaid())) {
                            s = (!CommonTools.isValidateList(matchCriteria.getVendorID()) && matchCriteria.getUserVerification() == 0 && matchCriteria.getKeyProtection() == 0 && matchCriteria.getMatcherProtection() == 0 && matchCriteria.getTcDisplay() == 0 && !CommonTools.isValidateList(matchCriteria.getAuthenticationAlgorithms()) && !CommonTools.isValidateList(matchCriteria.getAssertionSchemes()) && !CommonTools.isValidateList(matchCriteria.getAttestationTypes())) ? (short) 0 : (short) 6;
                        } else if (CommonTools.isValidateList(matchCriteria.getAuthenticationAlgorithms()) && CommonTools.isValidateList(matchCriteria.getAssertionSchemes())) {
                        }
                    }
                }
            }
            return s;
        }
        return (short) 6;
    }
}
