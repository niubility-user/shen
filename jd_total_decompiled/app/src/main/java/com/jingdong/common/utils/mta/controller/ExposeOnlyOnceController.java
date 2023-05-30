package com.jingdong.common.utils.mta.controller;

import java.util.HashSet;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u00c0\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0014\u0010\u000bJ\u0017\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\u0007\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\u0007\u0010\u0006J\u0017\u0010\b\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\b\u0010\u0006J\r\u0010\n\u001a\u00020\t\u00a2\u0006\u0004\b\n\u0010\u000bJ\r\u0010\f\u001a\u00020\t\u00a2\u0006\u0004\b\f\u0010\u000bJ\r\u0010\r\u001a\u00020\t\u00a2\u0006\u0004\b\r\u0010\u000bR*\u0010\u0010\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u000ej\n\u0012\u0006\u0012\u0004\u0018\u00010\u0002`\u000f8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u0011R*\u0010\u0012\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u000ej\n\u0012\u0006\u0012\u0004\u0018\u00010\u0002`\u000f8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u0011R*\u0010\u0013\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u000ej\n\u0012\u0006\u0012\u0004\u0018\u00010\u0002`\u000f8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0011\u00a8\u0006\u0015"}, d2 = {"Lcom/jingdong/common/utils/mta/controller/ExposeOnlyOnceController;", "", "", "exposeId", "", "addExposeId", "(Ljava/lang/String;)Z", "addNoRefNoBackExposedId", "addNoRefExposedId", "", "reset", "()V", "resetNoRefNoBack", "resetNoRef", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "noRefNoBackExposedIdSet", "Ljava/util/HashSet;", "noRefExposedIdSet", "exposedIdSet", "<init>", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public final class ExposeOnlyOnceController {
    public static final ExposeOnlyOnceController INSTANCE = new ExposeOnlyOnceController();
    private static final HashSet<String> exposedIdSet = new HashSet<>();
    private static final HashSet<String> noRefNoBackExposedIdSet = new HashSet<>();
    private static final HashSet<String> noRefExposedIdSet = new HashSet<>();

    private ExposeOnlyOnceController() {
    }

    public final boolean addExposeId(@Nullable String exposeId) {
        return exposedIdSet.add(exposeId);
    }

    public final boolean addNoRefExposedId(@Nullable String exposeId) {
        return noRefExposedIdSet.add(exposeId);
    }

    public final boolean addNoRefNoBackExposedId(@Nullable String exposeId) {
        return noRefNoBackExposedIdSet.add(exposeId);
    }

    public final void reset() {
        exposedIdSet.clear();
    }

    public final void resetNoRef() {
        noRefExposedIdSet.clear();
    }

    public final void resetNoRefNoBack() {
        noRefNoBackExposedIdSet.clear();
    }
}
