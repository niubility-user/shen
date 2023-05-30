package cn.com.union.fido.bean.uafclient.tls;

import java.util.List;

/* loaded from: classes.dex */
public class ServerResponse {
    public List<Token> additionalTokens;
    public String description;
    public String location;
    public String newUAFRequest;
    public String postData;
    public int statusCode;

    public List<Token> getAdditionalTokens() {
        return this.additionalTokens;
    }

    public String getDescription() {
        return this.description;
    }

    public String getLocation() {
        return this.location;
    }

    public String getNewUAFRequest() {
        return this.newUAFRequest;
    }

    public String getPostData() {
        return this.postData;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public void setAdditionalTokens(List<Token> list) {
        this.additionalTokens = list;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setLocation(String str) {
        this.location = str;
    }

    public void setNewUAFRequest(String str) {
        this.newUAFRequest = str;
    }

    public void setPostData(String str) {
        this.postData = str;
    }

    public void setStatusCode(int i2) {
        this.statusCode = i2;
    }
}
