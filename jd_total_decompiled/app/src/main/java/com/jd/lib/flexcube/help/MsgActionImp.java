package com.jd.lib.flexcube.help;

import java.util.HashMap;
import java.util.List;

/* loaded from: classes14.dex */
public abstract class MsgActionImp implements MsgActionInterface {
    public HashMap hashMap;

    @Override // com.jd.lib.flexcube.help.MsgActionInterface
    public void handleAction(List list) {
    }

    public MsgActionImp setHashMap(HashMap hashMap) {
        this.hashMap = hashMap;
        return this;
    }
}
