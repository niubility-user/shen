package com.jd.cashier.app.jdlibcutter.impl.activity;

import com.jd.cashier.app.jdlibcutter.protocol.activity.IActivityConnector;
import com.jd.cashier.app.jdlibcutter.protocol.activity.IActivityConnectorCreator;

/* loaded from: classes13.dex */
public class ActivityConnectorCreatorImpl implements IActivityConnectorCreator {
    @Override // com.jd.cashier.app.jdlibcutter.protocol.activity.IActivityConnectorCreator
    public IActivityConnector createActivityConnector() {
        return new ActivityConnectorImpl();
    }
}
