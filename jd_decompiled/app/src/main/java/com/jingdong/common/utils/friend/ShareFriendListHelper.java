package com.jingdong.common.utils.friend;

import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.utils.PersonalDesCommonUtils;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.JDSharedPreferences;
import com.jingdong.jdsdk.utils.JsonEncryptUtil;
import com.jingdong.jdsdk.utils.Md5Encrypt;
import com.jingdong.sdk.eldermode.util.JDElderModeUtils;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 $2\u00020\u0001:\u0001$B\u0007\u00a2\u0006\u0004\b\"\u0010#J\u000f\u0010\u0003\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0019\u0010\u0007\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ\u001b\u0010\u000b\u001a\u0004\u0018\u00010\u00022\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0002\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0019\u0010\r\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0002\u00a2\u0006\u0004\b\r\u0010\bJ\u0011\u0010\u000e\u001a\u0004\u0018\u00010\u0002H\u0002\u00a2\u0006\u0004\b\u000e\u0010\u0004J\u0011\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0012\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0011J\u0017\u0010\u0015\u001a\u00020\u00062\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0019\u0010\u0017\u001a\u00020\u00062\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0013\u00a2\u0006\u0004\b\u0017\u0010\u0016R\u0018\u0010\u0018\u001a\u0004\u0018\u00010\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u001d\u0010\u001f\u001a\u00020\u001a8B@\u0002X\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u0018\u0010 \u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b \u0010!\u00a8\u0006%"}, d2 = {"Lcom/jingdong/common/utils/friend/ShareFriendListHelper;", "", "Lcom/jd/framework/json/JDJSONObject;", "getShareFriendListObjectOrEmpty", "()Lcom/jd/framework/json/JDJSONObject;", "friendListObject", "", "setShareFriendListAndSaveToSp", "(Lcom/jd/framework/json/JDJSONObject;)V", "Lcom/jingdong/jdsdk/network/toolbox/HttpResponse;", "response", "parseResponse", "(Lcom/jingdong/jdsdk/network/toolbox/HttpResponse;)Lcom/jd/framework/json/JDJSONObject;", "saveShareFriendListToSp", "readShareFriendListFromSp", "", "getShareFriendListKey", "()Ljava/lang/String;", "getMd5Pin", "Lcom/jingdong/common/utils/friend/GetShareFriendListCallback;", "callback", "getShareFriendListAndRequest", "(Lcom/jingdong/common/utils/friend/GetShareFriendListCallback;)V", "requestShareFriendList", "cachedUserPin", "Ljava/lang/String;", "Lcom/jingdong/jdsdk/utils/JDSharedPreferences;", "sharePreferences$delegate", "Lkotlin/Lazy;", "getSharePreferences", "()Lcom/jingdong/jdsdk/utils/JDSharedPreferences;", "sharePreferences", "shareFriendListObject", "Lcom/jd/framework/json/JDJSONObject;", "<init>", "()V", "Companion", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public final class ShareFriendListHelper {
    private static final String FUNCTION_ID = "jdf_queryBothwayFriendsInfo";
    private static final int SP_ENC_TYPE = 1;
    private static final String SP_KEY_SHARE_FRIEND_LIST = "share_friend_list_";
    private static final String SP_NAME = "jd_friend_manager_sp";
    private String cachedUserPin;
    private JDJSONObject shareFriendListObject;

    /* renamed from: sharePreferences$delegate  reason: from kotlin metadata */
    private final Lazy sharePreferences;

    public ShareFriendListHelper() {
        Lazy lazy;
        lazy = LazyKt__LazyJVMKt.lazy(new Function0<JDSharedPreferences>() { // from class: com.jingdong.common.utils.friend.ShareFriendListHelper$sharePreferences$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final JDSharedPreferences invoke() {
                JdSdk jdSdk = JdSdk.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(jdSdk, "JdSdk.getInstance()");
                return new JDSharedPreferences(jdSdk.getApplicationContext(), "jd_friend_manager_sp", 0);
            }
        });
        this.sharePreferences = lazy;
    }

    private final String getMd5Pin() {
        if (LoginUserBase.hasLogin()) {
            String userPin = LoginUserBase.getUserPin();
            if (userPin == null || userPin.length() == 0) {
                return "";
            }
            try {
                String md5 = Md5Encrypt.md5(userPin);
                Intrinsics.checkExpressionValueIsNotNull(md5, "Md5Encrypt.md5(userPin)");
                return md5;
            } catch (Exception unused) {
                return "";
            }
        }
        return "";
    }

    private final String getShareFriendListKey() {
        String md5Pin = getMd5Pin();
        if (md5Pin.length() == 0) {
            return null;
        }
        return SP_KEY_SHARE_FRIEND_LIST + md5Pin;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final JDJSONObject getShareFriendListObjectOrEmpty() {
        JDJSONObject jDJSONObject = this.shareFriendListObject;
        return jDJSONObject != null ? jDJSONObject : new JDJSONObject();
    }

    private final JDSharedPreferences getSharePreferences() {
        return (JDSharedPreferences) this.sharePreferences.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final JDJSONObject parseResponse(HttpResponse response) {
        JDJSONObject fastJsonObject;
        JDJSONObject parseObject;
        JDJSONObject jDJSONObject = null;
        if (response == null || (fastJsonObject = response.getFastJsonObject()) == null) {
            return null;
        }
        int optInt = fastJsonObject.optInt(JsonEncryptUtil.ENC_KEY);
        try {
            if (optInt == 0) {
                parseObject = fastJsonObject.optJSONObject("data");
            } else {
                parseObject = JDJSON.parseObject(PersonalDesCommonUtils.commonDecrypt(fastJsonObject.optString("data"), optInt));
            }
            jDJSONObject = parseObject;
            return jDJSONObject;
        } catch (Exception unused) {
            return jDJSONObject;
        }
    }

    private final JDJSONObject readShareFriendListFromSp() {
        String shareFriendListKey = getShareFriendListKey();
        if (shareFriendListKey != null) {
            String string = getSharePreferences().getString(shareFriendListKey, "");
            if (!(string == null || string.length() == 0)) {
                String commonDecrypt = PersonalDesCommonUtils.commonDecrypt(string, 1);
                if (!(commonDecrypt == null || commonDecrypt.length() == 0)) {
                    try {
                        return JDJSON.parseObject(commonDecrypt);
                    } catch (Exception unused) {
                    }
                }
            }
        }
        return null;
    }

    public static /* synthetic */ void requestShareFriendList$default(ShareFriendListHelper shareFriendListHelper, GetShareFriendListCallback getShareFriendListCallback, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            getShareFriendListCallback = null;
        }
        shareFriendListHelper.requestShareFriendList(getShareFriendListCallback);
    }

    private final void saveShareFriendListToSp(JDJSONObject friendListObject) {
        String shareFriendListKey;
        if (friendListObject == null || (shareFriendListKey = getShareFriendListKey()) == null) {
            return;
        }
        String jSONString = friendListObject.toJSONString();
        if (jSONString == null) {
            jSONString = "";
        }
        String commonEncrypt = PersonalDesCommonUtils.commonEncrypt(jSONString, 1);
        getSharePreferences().edit().putString(shareFriendListKey, commonEncrypt != null ? commonEncrypt : "").apply();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setShareFriendListAndSaveToSp(JDJSONObject friendListObject) {
        if (friendListObject != null) {
            this.shareFriendListObject = friendListObject;
            saveShareFriendListToSp(friendListObject);
        }
    }

    public final void getShareFriendListAndRequest(@Nullable GetShareFriendListCallback callback) {
        if (JDElderModeUtils.isElderMode()) {
            if (callback != null) {
                callback.onResult(new JDJSONObject());
            }
        } else if (!LoginUserBase.hasLogin()) {
            this.shareFriendListObject = null;
            if (callback != null) {
                callback.onResult(new JDJSONObject());
            }
        } else {
            String userPin = LoginUserBase.getUserPin();
            if (userPin == null || userPin.length() == 0) {
                this.shareFriendListObject = null;
                if (callback != null) {
                    callback.onResult(new JDJSONObject());
                    return;
                }
                return;
            }
            if ((!Intrinsics.areEqual(userPin, this.cachedUserPin)) != false) {
                this.shareFriendListObject = null;
                this.cachedUserPin = userPin;
            }
            JDJSONObject jDJSONObject = this.shareFriendListObject;
            if (jDJSONObject != null) {
                if (callback != null) {
                    callback.onResult(jDJSONObject);
                }
                requestShareFriendList$default(this, null, 1, null);
                return;
            }
            JDJSONObject readShareFriendListFromSp = readShareFriendListFromSp();
            this.shareFriendListObject = readShareFriendListFromSp;
            if (readShareFriendListFromSp != null) {
                if (callback != null) {
                    callback.onResult(readShareFriendListFromSp);
                }
                requestShareFriendList$default(this, null, 1, null);
                return;
            }
            requestShareFriendList(callback);
        }
    }

    public final void requestShareFriendList(@Nullable final GetShareFriendListCallback callback) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setFunctionId(FUNCTION_ID);
        httpSetting.setEffect(0);
        httpSetting.setCacheMode(2);
        httpSetting.setHost(Configuration.getPersonalHost());
        httpSetting.setListener(new HttpGroup.OnCommonListener() { // from class: com.jingdong.common.utils.friend.ShareFriendListHelper$requestShareFriendList$1
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(@Nullable HttpResponse response) {
                JDJSONObject parseResponse;
                JDJSONObject shareFriendListObjectOrEmpty;
                parseResponse = ShareFriendListHelper.this.parseResponse(response);
                ShareFriendListHelper.this.setShareFriendListAndSaveToSp(parseResponse);
                GetShareFriendListCallback getShareFriendListCallback = callback;
                if (getShareFriendListCallback != null) {
                    shareFriendListObjectOrEmpty = ShareFriendListHelper.this.getShareFriendListObjectOrEmpty();
                    getShareFriendListCallback.onResult(shareFriendListObjectOrEmpty);
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(@Nullable HttpError error) {
                JDJSONObject shareFriendListObjectOrEmpty;
                GetShareFriendListCallback getShareFriendListCallback = callback;
                if (getShareFriendListCallback != null) {
                    shareFriendListObjectOrEmpty = ShareFriendListHelper.this.getShareFriendListObjectOrEmpty();
                    getShareFriendListCallback.onResult(shareFriendListObjectOrEmpty);
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
            public void onReady(@Nullable HttpGroup.HttpSettingParams params) {
            }
        });
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }
}
