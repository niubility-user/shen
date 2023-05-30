package cn.com.union.fido.util.policy;

import cn.com.union.fido.bean.Extension;
import cn.com.union.fido.bean.asm.AuthenticatorInfo;
import cn.com.union.fido.bean.uafclient.policy.MatchCriteria;
import cn.com.union.fido.bean.uafclient.policy.Policy;
import cn.com.union.fido.util.CommonTools;
import cn.com.union.fido.util.UAFTools;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class PolicyHandler {
    private static boolean acceptedCompare(MatchCriteria matchCriteria, AuthenticatorInfo authenticatorInfo, List<String> list, boolean z) {
        boolean z2;
        String str = authenticatorInfo.aaid;
        String vendorIDFromAAID = UAFTools.getVendorIDFromAAID(str);
        if (CommonTools.isValidateList(matchCriteria.getAaid())) {
            z2 = matchCriteria.getAaid().contains(str.toLowerCase()) || matchCriteria.getAaid().contains(str.toUpperCase());
            if (z2 && z && (authenticatorInfo.isSecondFactorOnly || !authenticatorInfo.isRoamingAuthenticator)) {
                if (!CommonTools.isValidateList(list)) {
                    z2 = false;
                } else if (CommonTools.isValidateList(matchCriteria.getKeyIDs())) {
                    z2 = CommonTools.ifElementContains(matchCriteria.getKeyIDs(), list) & true;
                }
            }
        } else {
            z2 = true;
        }
        return z2 && (CommonTools.isValidateList(matchCriteria.getVendorID()) ? matchCriteria.getVendorID().contains(vendorIDFromAAID) : true) && (CommonTools.longEqualAccepted(matchCriteria.getUserVerification(), authenticatorInfo.userVerification) || (((authenticatorInfo.userVerification & 1024) > 0L ? 1 : ((authenticatorInfo.userVerification & 1024) == 0L ? 0 : -1)) == 0 && ((matchCriteria.getUserVerification() & 1024) > 0L ? 1 : ((matchCriteria.getUserVerification() & 1024) == 0L ? 0 : -1)) == 0 && ((authenticatorInfo.userVerification & matchCriteria.getUserVerification()) > 0L ? 1 : ((authenticatorInfo.userVerification & matchCriteria.getUserVerification()) == 0L ? 0 : -1)) != 0)) && CommonTools.intEqualAccepted(matchCriteria.getKeyProtection(), authenticatorInfo.keyProtection) && CommonTools.intEqualAccepted(matchCriteria.getMatcherProtection(), authenticatorInfo.matcherProtection) && CommonTools.longEqualAccepted(matchCriteria.getAttachmentHint(), authenticatorInfo.attachmentHint) && CommonTools.intEqualAccepted(matchCriteria.getTcDisplay(), authenticatorInfo.tcDisplay) && (CommonTools.isValidateList(matchCriteria.getAuthenticationAlgorithms()) ? matchCriteria.getAuthenticationAlgorithms().contains(Integer.valueOf(authenticatorInfo.authenticationAlgorithm)) : true) && (CommonTools.isValidateList(matchCriteria.getAssertionSchemes()) ? matchCriteria.getAssertionSchemes().contains(authenticatorInfo.assertionScheme) : true) && (CommonTools.isValidateList(matchCriteria.getAttestationTypes()) ? CommonTools.ifElementContains(matchCriteria.getAttestationTypes(), authenticatorInfo.attestationTypes) : true);
    }

    private static boolean acceptedCompare(MatchCriteria matchCriteria, AuthenticatorInfo authenticatorInfo, List<String> list, boolean z, List<Extension> list2) {
        boolean z2;
        ArrayList arrayList = new ArrayList();
        String str = authenticatorInfo.aaid;
        String vendorIDFromAAID = UAFTools.getVendorIDFromAAID(str);
        boolean z3 = false;
        if (CommonTools.isValidateList(matchCriteria.getAaid())) {
            z2 = matchCriteria.getAaid().contains(str.toLowerCase()) || matchCriteria.getAaid().contains(str.toUpperCase());
            if (z2 && z && (authenticatorInfo.isSecondFactorOnly || !authenticatorInfo.isRoamingAuthenticator)) {
                if (!CommonTools.isValidateList(list)) {
                    z2 = false;
                } else if (CommonTools.isValidateList(matchCriteria.getKeyIDs())) {
                    z2 = CommonTools.ifElementContains(matchCriteria.getKeyIDs(), list, matchCriteria.getExts(), arrayList) & true;
                }
            }
        } else {
            z2 = true;
        }
        boolean contains = CommonTools.isValidateList(matchCriteria.getVendorID()) ? matchCriteria.getVendorID().contains(vendorIDFromAAID) : true;
        boolean z4 = CommonTools.longEqualAccepted(matchCriteria.getUserVerification(), authenticatorInfo.userVerification) || ((authenticatorInfo.userVerification & 1024) == 0 && (matchCriteria.getUserVerification() & 1024) == 0 && (authenticatorInfo.userVerification & matchCriteria.getUserVerification()) != 0);
        boolean intEqualAccepted = CommonTools.intEqualAccepted(matchCriteria.getKeyProtection(), authenticatorInfo.keyProtection);
        boolean intEqualAccepted2 = CommonTools.intEqualAccepted(matchCriteria.getMatcherProtection(), authenticatorInfo.matcherProtection);
        boolean longEqualAccepted = CommonTools.longEqualAccepted(matchCriteria.getAttachmentHint(), authenticatorInfo.attachmentHint);
        boolean intEqualAccepted3 = CommonTools.intEqualAccepted(matchCriteria.getTcDisplay(), authenticatorInfo.tcDisplay);
        boolean contains2 = CommonTools.isValidateList(matchCriteria.getAuthenticationAlgorithms()) ? matchCriteria.getAuthenticationAlgorithms().contains(Integer.valueOf(authenticatorInfo.authenticationAlgorithm)) : true;
        boolean contains3 = CommonTools.isValidateList(matchCriteria.getAssertionSchemes()) ? matchCriteria.getAssertionSchemes().contains(authenticatorInfo.assertionScheme) : true;
        boolean ifElementContains = CommonTools.isValidateList(matchCriteria.getAttestationTypes()) ? CommonTools.ifElementContains(matchCriteria.getAttestationTypes(), authenticatorInfo.attestationTypes) : true;
        if (z2 && contains && z4 && intEqualAccepted && intEqualAccepted2 && longEqualAccepted && intEqualAccepted3 && contains2 && contains3 && ifElementContains) {
            z3 = true;
        }
        if (z3) {
            list2.addAll(arrayList);
        }
        return z3;
    }

    private static boolean compareRegister(Policy policy, AuthenticatorInfo authenticatorInfo, List<String> list, boolean z) {
        List<List<MatchCriteria>> accepted = policy.getAccepted();
        List<MatchCriteria> disallowed = policy.getDisallowed();
        if (CommonTools.isValidateList(disallowed)) {
            Iterator<MatchCriteria> it = disallowed.iterator();
            while (it.hasNext()) {
                if (disallowedCompare(authenticatorInfo, it.next(), list, z)) {
                    return false;
                }
            }
        }
        if (CommonTools.isValidateList(accepted)) {
            Iterator<List<MatchCriteria>> it2 = accepted.iterator();
            while (it2.hasNext()) {
                Iterator<MatchCriteria> it3 = it2.next().iterator();
                while (it3.hasNext()) {
                    if (acceptedCompare(it3.next(), authenticatorInfo, list, z)) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    private static boolean compareRegister(Policy policy, AuthenticatorInfo authenticatorInfo, List<String> list, boolean z, List<Extension> list2) {
        List<List<MatchCriteria>> accepted = policy.getAccepted();
        List<MatchCriteria> disallowed = policy.getDisallowed();
        if (CommonTools.isValidateList(disallowed)) {
            Iterator<MatchCriteria> it = disallowed.iterator();
            while (it.hasNext()) {
                if (disallowedCompare(authenticatorInfo, it.next(), list, z)) {
                    return false;
                }
            }
        }
        if (CommonTools.isValidateList(accepted)) {
            Iterator<List<MatchCriteria>> it2 = accepted.iterator();
            while (it2.hasNext()) {
                Iterator<MatchCriteria> it3 = it2.next().iterator();
                while (it3.hasNext()) {
                    if (acceptedCompare(it3.next(), authenticatorInfo, list, z, list2)) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    private static boolean disallowedCompare(AuthenticatorInfo authenticatorInfo, MatchCriteria matchCriteria, List<String> list, boolean z) {
        boolean z2;
        String str = authenticatorInfo.aaid;
        String vendorIDFromAAID = UAFTools.getVendorIDFromAAID(str);
        if (CommonTools.isValidateList(matchCriteria.getAaid())) {
            z2 = matchCriteria.getAaid().contains(str.toLowerCase()) || matchCriteria.getAaid().contains(str.toUpperCase());
            if (z2 && !z && CommonTools.isValidateList(matchCriteria.getKeyIDs())) {
                z2 = CommonTools.ifElementContains(matchCriteria.getKeyIDs(), list) & true;
            }
        } else {
            z2 = false;
        }
        return z2 || (CommonTools.isValidateList(matchCriteria.getVendorID()) ? matchCriteria.getVendorID().contains(vendorIDFromAAID) : false) || ((authenticatorInfo.userVerification > matchCriteria.getUserVerification() ? 1 : (authenticatorInfo.userVerification == matchCriteria.getUserVerification() ? 0 : -1)) == 0 || (((authenticatorInfo.userVerification & 1024) > 0L ? 1 : ((authenticatorInfo.userVerification & 1024) == 0L ? 0 : -1)) == 0 && ((matchCriteria.getUserVerification() & 1024) > 0L ? 1 : ((matchCriteria.getUserVerification() & 1024) == 0L ? 0 : -1)) == 0 && ((authenticatorInfo.userVerification & matchCriteria.getUserVerification()) > 0L ? 1 : ((authenticatorInfo.userVerification & matchCriteria.getUserVerification()) == 0L ? 0 : -1)) != 0)) || CommonTools.intEqualDisallowed(matchCriteria.getKeyProtection(), authenticatorInfo.keyProtection) || CommonTools.intEqualDisallowed(matchCriteria.getMatcherProtection(), authenticatorInfo.matcherProtection) || CommonTools.longEqualDisallowed(matchCriteria.getAttachmentHint(), authenticatorInfo.attachmentHint) || CommonTools.intEqualDisallowed(matchCriteria.getTcDisplay(), authenticatorInfo.tcDisplay) || (CommonTools.isValidateList(matchCriteria.getAuthenticationAlgorithms()) ? matchCriteria.getAuthenticationAlgorithms().contains(Integer.valueOf(authenticatorInfo.authenticationAlgorithm)) : false) || (CommonTools.isValidateList(matchCriteria.getAssertionSchemes()) ? matchCriteria.getAssertionSchemes().contains(authenticatorInfo.assertionScheme) : false) || (CommonTools.isValidateList(matchCriteria.getAttestationTypes()) ? CommonTools.ifElementContains(matchCriteria.getAttestationTypes(), authenticatorInfo.attestationTypes) : false);
    }

    public static int getAcceptedAttestationTypes(Policy policy, AuthenticatorInfo authenticatorInfo) {
        List<Short> list;
        ArrayList arrayList = new ArrayList();
        Iterator<List<MatchCriteria>> it = policy.getAccepted().iterator();
        while (it.hasNext()) {
            for (MatchCriteria matchCriteria : it.next()) {
                List<String> aaid = matchCriteria.getAaid();
                if (!CommonTools.isValidateList(aaid)) {
                    list = authenticatorInfo.attestationTypes;
                } else if (aaid.contains(authenticatorInfo.aaid.toLowerCase()) || aaid.contains(authenticatorInfo.aaid.toUpperCase())) {
                    if (matchCriteria.getAttestationTypes() != null) {
                        list = matchCriteria.getAttestationTypes();
                    }
                }
                arrayList.addAll(list);
            }
        }
        return arrayList.size() > 0 ? ((Short) arrayList.get(0)).shortValue() : R2.id.recommend_year_festival_shot_pd_title;
    }

    public static List<String> getAcceptedKeyIDs(Policy policy, String str) {
        ArrayList arrayList = new ArrayList();
        Iterator<List<MatchCriteria>> it = policy.getAccepted().iterator();
        while (it.hasNext()) {
            for (MatchCriteria matchCriteria : it.next()) {
                List<String> aaid = matchCriteria.getAaid();
                if (CommonTools.isValidateList(aaid) && (aaid.contains(str.toLowerCase()) || aaid.contains(str.toUpperCase()))) {
                    if (matchCriteria.getKeyIDs() != null) {
                        arrayList.addAll(matchCriteria.getKeyIDs());
                    }
                }
            }
        }
        return arrayList;
    }

    public static AuthenticatorInfo getAllowedAuthenticator(Policy policy, AuthenticatorInfo authenticatorInfo, List<String> list, boolean z) {
        if (compareRegister(policy, authenticatorInfo, list, z)) {
            return authenticatorInfo;
        }
        return null;
    }

    public static AuthenticatorInfo getAllowedAuthenticator(Policy policy, AuthenticatorInfo authenticatorInfo, List<String> list, boolean z, List<Extension> list2) {
        if (compareRegister(policy, authenticatorInfo, list, z, list2)) {
            return authenticatorInfo;
        }
        return null;
    }
}
