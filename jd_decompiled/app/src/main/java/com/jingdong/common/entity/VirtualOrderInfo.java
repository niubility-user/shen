package com.jingdong.common.entity;

import android.text.TextUtils;
import com.alibaba.fastjson.parser.Feature;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.TypeToken;
import java.io.Serializable;
import java.util.List;

/* loaded from: classes5.dex */
public class VirtualOrderInfo implements Serializable {
    public static final String REDIRECT_M = "M";
    public static final String REDIRECT_NATIVE = "Native";
    public static final int VIRTUAL_ORDER_TYPE_DATA_CHARGE = 87;
    public static final int VIRTUAL_ORDER_TYPE_FLIGHT = 35;
    public static final int VIRTUAL_ORDER_TYPE_GROUP = 28;
    public static final int VIRTUAL_ORDER_TYPE_HOTEL = 39;
    public static final int VIRTUAL_ORDER_TYPE_LOC = 75;
    public static final int VIRTUAL_ORDER_TYPE_LOTTERY = 36;
    public static final int VIRTUAL_ORDER_TYPE_MOVIE = 43;
    public static final int VIRTUAL_ORDER_TYPE_PHONE_CHARGE = 37;
    public static final int VIRTUAL_ORDER_TYPE_QQ_GAME_CHARGE = 34;
    public static final int VIRTUAL_ORDER_TYPE_YICHE = 90;
    public List<VirtualOrderButton> nextOperate;
    public long orderId;
    public RedirectProtocol redirectProtocol;
    public boolean showDelButton;
    public String sumMoney;
    public VirtualOrderStatus virtualOrderStatus;
    public VirtualOrderType virtualOrderType;
    public List<VirtualWare> wareInfos;

    /* loaded from: classes5.dex */
    public static class RedirectProtocol {
        public String param;
        public String type;
        public String url;
    }

    /* loaded from: classes5.dex */
    public static class VirtualOrderButton {
    }

    /* loaded from: classes5.dex */
    public static class VirtualOrderMessage {
        public String msg;
        public String name;
    }

    /* loaded from: classes5.dex */
    public static class VirtualOrderStatus {
    }

    /* loaded from: classes5.dex */
    public static class VirtualOrderType {
    }

    /* loaded from: classes5.dex */
    public static class VirtualWare {
        public List<VirtualOrderMessage> messages;
    }

    public static VirtualOrderInfo parse(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return (VirtualOrderInfo) JDJSON.parseObject(str, new TypeToken<VirtualOrderInfo>() { // from class: com.jingdong.common.entity.VirtualOrderInfo.1
        }.getType(), new Feature[0]);
    }
}
