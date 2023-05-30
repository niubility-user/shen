package cn.com.union.fido.bean.uafclient.policy;

import cn.com.union.fido.bean.Extension;
import java.util.List;

/* loaded from: classes.dex */
public class MatchCriteria {
    private List<String> aaid;
    private List<String> assertionSchemes;
    private long attachmentHint;
    private List<Short> attestationTypes;
    private List<Integer> authenticationAlgorithms;
    private int authenticatorVersion;
    private List<Extension> exts;
    private List<String> keyIDs;
    private int keyProtection;
    private int matcherProtection;
    private int tcDisplay;
    private long userVerification;
    private List<String> vendorID;

    public List<String> getAaid() {
        return this.aaid;
    }

    public List<String> getAssertionSchemes() {
        return this.assertionSchemes;
    }

    public long getAttachmentHint() {
        return this.attachmentHint;
    }

    public List<Short> getAttestationTypes() {
        return this.attestationTypes;
    }

    public List<Integer> getAuthenticationAlgorithms() {
        return this.authenticationAlgorithms;
    }

    public int getAuthenticatorVersion() {
        return this.authenticatorVersion;
    }

    public List<Extension> getExts() {
        return this.exts;
    }

    public List<String> getKeyIDs() {
        return this.keyIDs;
    }

    public int getKeyProtection() {
        return this.keyProtection;
    }

    public int getMatcherProtection() {
        return this.matcherProtection;
    }

    public int getTcDisplay() {
        return this.tcDisplay;
    }

    public long getUserVerification() {
        return this.userVerification;
    }

    public List<String> getVendorID() {
        return this.vendorID;
    }

    public void setAaid(List<String> list) {
        this.aaid = list;
    }

    public void setAssertionSchemes(List<String> list) {
        this.assertionSchemes = list;
    }

    public void setAttachmentHint(long j2) {
        this.attachmentHint = j2;
    }

    public void setAttestationTypes(List<Short> list) {
        this.attestationTypes = list;
    }

    public void setAuthenticationAlgorithms(List<Integer> list) {
        this.authenticationAlgorithms = list;
    }

    public void setAuthenticatorVersion(int i2) {
        this.authenticatorVersion = i2;
    }

    public void setExts(List<Extension> list) {
        this.exts = list;
    }

    public void setKeyIDs(List<String> list) {
        this.keyIDs = list;
    }

    public void setKeyProtection(int i2) {
        this.keyProtection = i2;
    }

    public void setMatcherProtection(int i2) {
        this.matcherProtection = i2;
    }

    public void setTcDisplay(int i2) {
        this.tcDisplay = i2;
    }

    public void setUserVerification(long j2) {
        this.userVerification = j2;
    }

    public void setVendorID(List<String> list) {
        this.vendorID = list;
    }
}
