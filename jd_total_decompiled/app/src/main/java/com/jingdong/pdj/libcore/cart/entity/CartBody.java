package com.jingdong.pdj.libcore.cart.entity;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001:\u0003\u001a\u001b\u001cB\u0007\u00a2\u0006\u0004\b\u0018\u0010\u0019J-\u0010\b\u001a\u00020\u00072\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\b\u0010\tR\u0018\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0018\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0006\n\u0004\b\r\u0010\u000eR\u001e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0018\u0010\u0013\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u000eR\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u000eR\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u000eR\u0018\u0010\u0016\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u000eR\u0018\u0010\u0017\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u000e\u00a8\u0006\u001d"}, d2 = {"Lcom/jingdong/pdj/libcore/cart/entity/CartBody;", "", "", "skuId", "", "skuNum", "skuUuid", "", "setSkuInfo", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;)V", "", "detailTag", "Ljava/lang/Boolean;", "storeId", "Ljava/lang/String;", "", "Lcom/jingdong/pdj/libcore/cart/entity/SkuOperDTO;", "skuOperList", "Ljava/util/List;", "bizScene", "venderId", "sceneType", "source", "bizModelCode", "<init>", "()V", "BizModelCode", "BizScene", "SceneType", "libCore_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes7.dex */
public final class CartBody {
    @JvmField
    @Nullable
    public List<SkuOperDTO> skuOperList;
    @JvmField
    @Nullable
    public String bizModelCode = "7";
    @JvmField
    @Nullable
    public String bizScene = "1";
    @JvmField
    @Nullable
    public String sceneType = "1";
    @JvmField
    @Nullable
    public String venderId = "";
    @JvmField
    @Nullable
    public String source = "";
    @JvmField
    @Nullable
    public String storeId = "";
    @JvmField
    @Nullable
    public Boolean detailTag = Boolean.FALSE;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006R\u0016\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u0004\u00a8\u0006\u0007"}, d2 = {"Lcom/jingdong/pdj/libcore/cart/entity/CartBody$BizModelCode;", "", "", "NEARBY_FEEDS_JD", "Ljava/lang/String;", "<init>", "()V", "libCore_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes7.dex */
    public static final class BizModelCode {
        public static final BizModelCode INSTANCE = new BizModelCode();
        @NotNull
        public static final String NEARBY_FEEDS_JD = "7";

        private BizModelCode() {
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006R\u0016\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u0004\u00a8\u0006\u0007"}, d2 = {"Lcom/jingdong/pdj/libcore/cart/entity/CartBody$BizScene;", "", "", "APP_FORM_HOME", "Ljava/lang/String;", "<init>", "()V", "libCore_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes7.dex */
    public static final class BizScene {
        @NotNull
        public static final String APP_FORM_HOME = "1";
        public static final BizScene INSTANCE = new BizScene();

        private BizScene() {
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006R\u0016\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u0004\u00a8\u0006\u0007"}, d2 = {"Lcom/jingdong/pdj/libcore/cart/entity/CartBody$SceneType;", "", "", "HOME", "Ljava/lang/String;", "<init>", "()V", "libCore_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes7.dex */
    public static final class SceneType {
        @NotNull
        public static final String HOME = "1";
        public static final SceneType INSTANCE = new SceneType();

        private SceneType() {
        }
    }

    public static /* synthetic */ void setSkuInfo$default(CartBody cartBody, String str, Integer num, String str2, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            str2 = "";
        }
        cartBody.setSkuInfo(str, num, str2);
    }

    public final void setSkuInfo(@Nullable String skuId, @Nullable Integer skuNum, @Nullable String skuUuid) {
        if (this.skuOperList == null) {
            this.skuOperList = new ArrayList();
        }
        List<SkuOperDTO> list = this.skuOperList;
        if (list != null) {
            SkuOperDTO skuOperDTO = new SkuOperDTO(null, null, null, 7, null);
            skuOperDTO.skuId = skuId;
            skuOperDTO.skuNum = skuNum;
            skuOperDTO.skuUuid = skuUuid;
            list.add(skuOperDTO);
        }
    }
}
