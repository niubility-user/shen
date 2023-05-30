package com.jingdong.common.lbs.event;

import com.jingdong.cleanmvp.common.BaseEvent;

/* loaded from: classes5.dex */
public class LocationAddressEvent extends BaseEvent {
    public static final String LOCATION_ADDRESS_RESULT = "location_address_result";

    public LocationAddressEvent(String str) {
        super(str);
    }
}
