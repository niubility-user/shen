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
    */
    public static boolean validAAID(String str) {
        String[] split;
        boolean z = true;
        if (str != null) {
            try {
                if (str.trim().length() == 9 && (split = str.split("#")) != null && split.length == 2 && StringTools.isHexNumberRex(split[0])) {
                }
            } catch (Exception unused) {
                return false;
            }
        }
        z = false;
        return z;
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
