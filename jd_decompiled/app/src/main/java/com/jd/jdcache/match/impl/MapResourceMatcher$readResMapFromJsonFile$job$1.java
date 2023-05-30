package com.jd.jdcache.match.impl;

import com.jd.jdcache.entity.JDCacheDataSource;
import com.jd.jdcache.entity.JDCacheLocalResp;
import com.jd.jdcache.entity.JDCacheLocalRespKt;
import com.jd.jdcache.util.FileHelperKt;
import com.jd.jdcache.util.IUsefulCheckKt;
import com.jd.jdcache.util.UrlHelper;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u008a@\u00a2\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"", "invoke", "(Ljava/lang/Object;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "com.jd.jdcache.match.impl.MapResourceMatcher$readResMapFromJsonFile$job$1", f = "MapResourceMatcher.kt", i = {}, l = {62}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes13.dex */
public final class MapResourceMatcher$readResMapFromJsonFile$job$1 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
    final /* synthetic */ String $filePath;
    int label;
    final /* synthetic */ MapResourceMatcher this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MapResourceMatcher$readResMapFromJsonFile$job$1(MapResourceMatcher mapResourceMatcher, String str, Continuation continuation) {
        super(1, continuation);
        this.this$0 = mapResourceMatcher;
        this.$filePath = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@NotNull Continuation<?> continuation) {
        return new MapResourceMatcher$readResMapFromJsonFile$job$1(this.this$0, this.$filePath, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Continuation<? super Unit> continuation) {
        return ((MapResourceMatcher$readResMapFromJsonFile$job$1) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        List list;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        boolean z = true;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            File file = new File(this.$filePath);
            this.label = 1;
            obj = FileHelperKt.getString(file, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i2 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            ResultKt.throwOnFailure(obj);
        }
        String str = (String) obj;
        JDCacheDataSource dataSource = this.this$0.getDataSource();
        if (dataSource != null) {
            List<JDCacheLocalResp> jsonArrayParse = JDCacheLocalRespKt.jsonArrayParse(str);
            HashMap<String, JDCacheLocalResp> hashMap = null;
            Map map = null;
            hashMap = null;
            if (jsonArrayParse != null && (list = (List) IUsefulCheckKt.useful$default(jsonArrayParse, false, 1, null)) != null) {
                if (list != null && !list.isEmpty()) {
                    z = false;
                }
                if (!z) {
                    map = (Map) HashMap.class.newInstance();
                    for (Object obj2 : list) {
                        String urlToKey = UrlHelper.INSTANCE.urlToKey(((JDCacheLocalResp) obj2).getUrl());
                        if (urlToKey != null) {
                            Intrinsics.checkExpressionValueIsNotNull(map, "map");
                            map.put(urlToKey, obj2);
                        }
                    }
                }
                hashMap = (HashMap) map;
            }
            dataSource.setLocalFileMap(hashMap);
        }
        return Unit.INSTANCE;
    }
}
