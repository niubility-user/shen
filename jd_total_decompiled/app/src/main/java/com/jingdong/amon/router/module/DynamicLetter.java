package com.jingdong.amon.router.module;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.amon.router.c;
import com.jingdong.amon.router.callback.NavigationCallback;
import java.util.Set;

/* loaded from: classes18.dex */
public class DynamicLetter {
    private String action;
    private Bundle combineBundle;
    private Class destination;
    private int enterAnim;
    private int exitAnim;
    private Context from;
    private Bundle mExtras;
    private int mFlags;
    private int mRequestCode;
    private NavigationCallback.OnCompleteCallback<Letter> onCompleteCallback;
    private NavigationCallback.OnLostCallBack<Letter> onLostCallBack;
    private Bundle optionsCompat;
    private Letter originLetter;
    private Uri uri;
    private String uriStr;
    private int useIntercept;

    /* loaded from: classes18.dex */
    public static class Builder {
        private String action;
        private Bundle combineBundle;
        private Class destination;
        private Bundle mExtras;
        private NavigationCallback.OnCompleteCallback<Letter> onCompleteCallback;
        private NavigationCallback.OnLostCallBack<Letter> onLostCallBack;
        private Bundle optionsCompat;
        private Uri uri;
        private String uriStr;
        private int mRequestCode = -1;
        private int mFlags = -1;
        private int enterAnim = -1;
        private int exitAnim = -1;
        private int useIntercept = -1;
        private int useOriginalLetter = -1;

        public Builder(String str, Class cls) {
            this.destination = cls;
            this.uriStr = str;
            Uri c2 = c.c(str);
            this.uri = c2;
            Set<String> queryParameterNames = c2.getQueryParameterNames();
            if (queryParameterNames == null || queryParameterNames.size() <= 0) {
                return;
            }
            for (String str2 : queryParameterNames) {
                if (!TextUtils.isEmpty(str2)) {
                    String queryParameter = this.uri.getQueryParameter(str2);
                    if (!TextUtils.isEmpty(queryParameter)) {
                        if (this.mExtras == null) {
                            this.mExtras = new Bundle();
                        }
                        this.mExtras.putString(str2, queryParameter);
                    }
                }
            }
        }

        public DynamicLetter build() {
            return new DynamicLetter(this);
        }

        public Builder putExtras(Bundle bundle) {
            if (this.mExtras == null) {
                this.mExtras = new Bundle();
            }
            this.mExtras.putAll(bundle);
            return this;
        }

        public Builder setAction(String str) {
            this.action = str;
            return this;
        }

        public Builder setCombineBundle(Bundle bundle) {
            this.combineBundle = bundle;
            return this;
        }

        public Builder setEnterAnim(int i2) {
            this.enterAnim = i2;
            return this;
        }

        public Builder setExitAnim(int i2) {
            this.exitAnim = i2;
            return this;
        }

        public Builder setExtras(Bundle bundle) {
            this.mExtras = bundle;
            return this;
        }

        public Builder setFlags(int i2) {
            this.mFlags = i2;
            return this;
        }

        public Builder setOnCompleteCallback(NavigationCallback.OnCompleteCallback<Letter> onCompleteCallback) {
            this.onCompleteCallback = onCompleteCallback;
            return this;
        }

        public Builder setOnLostCallBack(NavigationCallback.OnLostCallBack<Letter> onLostCallBack) {
            this.onLostCallBack = onLostCallBack;
            return this;
        }

        public Builder setOptionsCompat(Bundle bundle) {
            this.optionsCompat = bundle;
            return this;
        }

        public Builder setRequestCode(int i2) {
            this.mRequestCode = i2;
            return this;
        }

        public Builder setUseIntercept(int i2) {
            this.useIntercept = i2;
            return this;
        }
    }

    private DynamicLetter(Builder builder) {
        this.action = builder.action;
        this.optionsCompat = builder.optionsCompat;
        this.exitAnim = builder.exitAnim;
        this.enterAnim = builder.enterAnim;
        this.mExtras = builder.mExtras;
        this.combineBundle = builder.combineBundle;
        this.mFlags = builder.mFlags;
        this.mRequestCode = builder.mRequestCode;
        this.destination = builder.destination;
        this.useIntercept = builder.useIntercept;
        this.onLostCallBack = builder.onLostCallBack;
        this.onCompleteCallback = builder.onCompleteCallback;
        this.uriStr = builder.uriStr;
        this.uri = builder.uri;
    }

    public Letter convertToLetter() {
        Letter letter = new Letter(this.from, this.uriStr);
        letter.setDestination(this.destination);
        letter.withFlags(this.mFlags);
        letter.withAction(this.action);
        letter.withExtras(this.mExtras);
        letter.withEnterAnim(this.enterAnim);
        letter.withExitAnim(this.exitAnim);
        letter.withOptionsCompat(this.optionsCompat);
        letter.withRequestCode(this.mRequestCode);
        letter.withOnLostCallBack(this.onLostCallBack);
        letter.withOnCompleteCallback(this.onCompleteCallback);
        return letter;
    }

    public String getAction() {
        return this.action;
    }

    public Bundle getCombineBundle() {
        return this.combineBundle;
    }

    public Class getDestination() {
        return this.destination;
    }

    public int getEnterAnim() {
        return this.enterAnim;
    }

    public int getExitAnim() {
        return this.exitAnim;
    }

    public Bundle getExtras() {
        return this.mExtras;
    }

    public int getFlags() {
        return this.mFlags;
    }

    public Context getFrom() {
        return this.from;
    }

    public NavigationCallback.OnCompleteCallback<Letter> getOnCompleteCallback() {
        return this.onCompleteCallback;
    }

    public NavigationCallback.OnLostCallBack<Letter> getOnLostCallBack() {
        return this.onLostCallBack;
    }

    public Bundle getOptionsCompat() {
        return this.optionsCompat;
    }

    public Letter getOriginLetter() {
        return this.originLetter;
    }

    public int getRequestCode() {
        return this.mRequestCode;
    }

    public Uri getUri() {
        return this.uri;
    }

    public String getUriStr() {
        return this.uriStr;
    }

    public int getUseIntercept() {
        return this.useIntercept;
    }

    public void setAction(String str) {
        this.action = str;
    }

    public void setCombineBundle(Bundle bundle) {
        this.combineBundle = bundle;
    }

    public void setDestination(Class cls) {
        this.destination = cls;
    }

    public void setEnterAnim(int i2) {
        this.enterAnim = i2;
    }

    public void setExitAnim(int i2) {
        this.exitAnim = i2;
    }

    public void setExtras(Bundle bundle) {
        this.mExtras = bundle;
    }

    public void setFlags(int i2) {
        this.mFlags = i2;
    }

    public void setFrom(Context context) {
        this.from = context;
    }

    public void setOnCompleteCallback(NavigationCallback.OnCompleteCallback<Letter> onCompleteCallback) {
        this.onCompleteCallback = onCompleteCallback;
    }

    public void setOnLostCallBack(NavigationCallback.OnLostCallBack<Letter> onLostCallBack) {
        this.onLostCallBack = onLostCallBack;
    }

    public void setOptionsCompat(Bundle bundle) {
        this.optionsCompat = bundle;
    }

    public void setOriginLetter(Letter letter) {
        this.originLetter = letter;
    }

    public void setRequestCode(int i2) {
        this.mRequestCode = i2;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public void setUriStr(String str) {
        this.uriStr = str;
    }

    public void setUseIntercept(int i2) {
        this.useIntercept = i2;
    }

    public String toString() {
        return "DynamicLetter{uri=" + this.uri + ", uriStr='" + this.uriStr + "', from=" + this.from + ", originLetter=" + this.originLetter + ", destination=" + this.destination + ", mExtras=" + this.mExtras + ", combineBundle=" + this.combineBundle + ", mRequestCode=" + this.mRequestCode + ", mFlags=" + this.mFlags + ", action='" + this.action + "', optionsCompat=" + this.optionsCompat + ", enterAnim=" + this.enterAnim + ", exitAnim=" + this.exitAnim + ", useIntercept=" + this.useIntercept + ", onLostCallBack=" + this.onLostCallBack + ", onCompleteCallback=" + this.onCompleteCallback + '}';
    }
}
