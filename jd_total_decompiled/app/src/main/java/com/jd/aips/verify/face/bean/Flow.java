package com.jd.aips.verify.face.bean;

import java.io.Serializable;

/* loaded from: classes12.dex */
public class Flow implements Serializable {
    static final long serialVersionUID = 6799397190118914299L;
    public String algChannel;
    public double blurScore;
    public String createTime;
    public String decisionCode;
    public double distance;
    public String encryptionType;
    public String ext;
    public double gammaScore;
    public double hackScore;
    public String imgBase64;
    public double liveScore;
    public String modelVersion;
    public String nirImgBase64;
    public String orderId;
    public double phiScore;
    public double thetaScore;
    public String userId;
    public String verifyId;
    public String verifyResult;
    public double verifyScore;

    public String getAlgChannel() {
        return this.algChannel;
    }

    public double getBlurScore() {
        return this.blurScore;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public String getDecisionCode() {
        return this.decisionCode;
    }

    public double getDistance() {
        return this.distance;
    }

    public String getEncryptionType() {
        return this.encryptionType;
    }

    public String getExt() {
        return this.ext;
    }

    public double getGammaScore() {
        return this.gammaScore;
    }

    public double getHackScore() {
        return this.hackScore;
    }

    public String getImgBase64() {
        return this.imgBase64;
    }

    public double getLiveScore() {
        return this.liveScore;
    }

    public String getModelVersion() {
        return this.modelVersion;
    }

    public String getNirImgBase64() {
        return this.nirImgBase64;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public double getPhiScore() {
        return this.phiScore;
    }

    public double getThetaScore() {
        return this.thetaScore;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getVerifyId() {
        return this.verifyId;
    }

    public String getVerifyResult() {
        return this.verifyResult;
    }

    public double getVerifyScore() {
        return this.verifyScore;
    }

    public void setAlgChannel(String str) {
        this.algChannel = str;
    }

    public void setBlurScore(double d) {
        this.blurScore = d;
    }

    public void setCreateTime(String str) {
        this.createTime = str;
    }

    public void setDecisionCode(String str) {
        this.decisionCode = str;
    }

    public void setDistance(double d) {
        this.distance = d;
    }

    public void setEncryptionType(String str) {
        this.encryptionType = str;
    }

    public void setExt(String str) {
        this.ext = str;
    }

    public void setGammaScore(double d) {
        this.gammaScore = d;
    }

    public void setHackScore(double d) {
        this.hackScore = d;
    }

    public void setImgBase64(String str) {
        this.imgBase64 = str;
    }

    public void setLiveScore(double d) {
        this.liveScore = d;
    }

    public void setModelVersion(String str) {
        this.modelVersion = str;
    }

    public void setNirImgBase64(String str) {
        this.nirImgBase64 = str;
    }

    public void setOrderId(String str) {
        this.orderId = str;
    }

    public void setPhiScore(double d) {
        this.phiScore = d;
    }

    public void setThetaScore(double d) {
        this.thetaScore = d;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public void setVerifyId(String str) {
        this.verifyId = str;
    }

    public void setVerifyResult(String str) {
        this.verifyResult = str;
    }

    public void setVerifyScore(double d) {
        this.verifyScore = d;
    }
}
