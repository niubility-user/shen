package com.jingdong.common.utils.friend;

import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u000f\u0010\bJ\u0015\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\r\u0010\u0007\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0007\u0010\bR\u001d\u0010\u000e\u001a\u00020\t8B@\u0002X\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\u00a8\u0006\u0010"}, d2 = {"Lcom/jingdong/common/utils/friend/JDFriendManager;", "", "Lcom/jingdong/common/utils/friend/GetShareFriendListCallback;", "callback", "", "getShareFriendList", "(Lcom/jingdong/common/utils/friend/GetShareFriendListCallback;)V", "requestShareFriendList", "()V", "Lcom/jingdong/common/utils/friend/ShareFriendListHelper;", "shareFriendListHelper$delegate", "Lkotlin/Lazy;", "getShareFriendListHelper", "()Lcom/jingdong/common/utils/friend/ShareFriendListHelper;", "shareFriendListHelper", "<init>", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public final class JDFriendManager {
    public static final JDFriendManager INSTANCE = new JDFriendManager();

    /* renamed from: shareFriendListHelper$delegate  reason: from kotlin metadata */
    private static final Lazy shareFriendListHelper;

    static {
        Lazy lazy;
        lazy = LazyKt__LazyJVMKt.lazy(new Function0<ShareFriendListHelper>() { // from class: com.jingdong.common.utils.friend.JDFriendManager$shareFriendListHelper$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final ShareFriendListHelper invoke() {
                return new ShareFriendListHelper();
            }
        });
        shareFriendListHelper = lazy;
    }

    private JDFriendManager() {
    }

    private final ShareFriendListHelper getShareFriendListHelper() {
        return (ShareFriendListHelper) shareFriendListHelper.getValue();
    }

    public final void getShareFriendList(@NotNull GetShareFriendListCallback callback) {
        getShareFriendListHelper().getShareFriendListAndRequest(callback);
    }

    public final void requestShareFriendList() {
        ShareFriendListHelper.requestShareFriendList$default(getShareFriendListHelper(), null, 1, null);
    }
}
