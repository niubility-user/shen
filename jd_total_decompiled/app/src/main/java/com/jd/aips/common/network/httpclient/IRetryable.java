package com.jd.aips.common.network.httpclient;

import java.io.IOException;

/* loaded from: classes12.dex */
public interface IRetryable {
    public static final int RETRY = -1;
    public static final int SUCCESS = 1;

    int execute() throws IOException;
}
