package com.jd.dynamic.base;

import com.jd.dynamic.lib.j.e;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes13.dex */
public class DynamicPreParse {
    private IPreParseFinishListener a;
    private ArrayList<PreParseConfig> b;

    /* renamed from: c  reason: collision with root package name */
    private ConcurrentHashMap<String, AtomicInteger> f1851c = new ConcurrentHashMap<>(16);
    private int d;

    /* renamed from: e  reason: collision with root package name */
    private long f1852e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public static class CacheEngineTask extends com.jd.dynamic.lib.utils.q<e.a, Void, e.a> {

        /* renamed from: m  reason: collision with root package name */
        private final DynamicPreParse f1853m;

        public CacheEngineTask(DynamicPreParse dynamicPreParse) {
            this.f1853m = dynamicPreParse;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.jd.dynamic.lib.utils.q
        /* renamed from: v  reason: merged with bridge method [inline-methods] */
        public e.a b(e.a... aVarArr) {
            if (aVarArr == null || aVarArr.length <= 0) {
                return null;
            }
            e.a aVar = aVarArr[0];
            if (aVar != null) {
                aVar.d = com.jd.dynamic.lib.j.e.b(aVar);
                return aVar;
            }
            return aVar;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.jd.dynamic.lib.utils.q
        /* renamed from: w  reason: merged with bridge method [inline-methods] */
        public void g(e.a aVar) {
            com.jd.dynamic.lib.j.f fVar;
            if (aVar == null || (fVar = aVar.a) == null) {
                return;
            }
            PreParseConfig preParseConfig = aVar.f2257c;
            int i2 = preParseConfig.preCreateCount;
            String str = preParseConfig.mConfig.getModule() + CartConstant.KEY_YB_INFO_LINK + aVar.f2257c.mConfig.getTemplateId();
            if (aVar.d) {
                ArrayList<DynamicTemplateEngine> arrayList = DynamicSdk.getEngine().templateEngineCache.get(str);
                if ((com.jd.dynamic.lib.utils.m.I(arrayList) ? arrayList.size() : 0) >= i2) {
                    this.f1853m.b(aVar);
                    com.jd.dynamic.lib.utils.t.e("CacheEngineTask", "removeCacheCountByModule = " + this.f1853m.f1851c.size());
                }
            } else {
                fVar.g();
                com.jd.dynamic.lib.utils.t.e("CacheEngineTask", "release = " + aVar.f2257c.a);
            }
            com.jd.dynamic.lib.utils.t.e("PreParseElTask", "holder parse and cached== " + aVar.d + " index = " + aVar.f2257c.a + " finish take time = " + DynamicMtaUtil.getCurMicroseconds(System.nanoTime() - aVar.b));
        }
    }

    /* loaded from: classes13.dex */
    public interface IPreParseFinishListener {
        void onPreParseFinish();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public static class PreParseElTask extends com.jd.dynamic.lib.utils.q<e.a, Void, e.a> {

        /* renamed from: m  reason: collision with root package name */
        private final DynamicPreParse f1854m;

        public PreParseElTask(DynamicPreParse dynamicPreParse) {
            this.f1854m = dynamicPreParse;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.jd.dynamic.lib.utils.q
        /* renamed from: v  reason: merged with bridge method [inline-methods] */
        public e.a b(e.a... aVarArr) {
            com.jd.dynamic.lib.j.f fVar;
            if (aVarArr == null || aVarArr.length <= 0) {
                return null;
            }
            e.a aVar = aVarArr[0];
            if (aVar == null || (fVar = aVar.a) == null) {
                return aVar;
            }
            fVar.f();
            return aVar;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.jd.dynamic.lib.utils.q
        /* renamed from: w  reason: merged with bridge method [inline-methods] */
        public void g(e.a aVar) {
            if (aVar != null) {
                if (this.f1854m.f(aVar)) {
                    new CacheEngineTask(this.f1854m).executeOnExecutor(com.jd.dynamic.lib.utils.q.THREAD_POOL_EXECUTOR, aVar);
                } else {
                    com.jd.dynamic.lib.j.f fVar = aVar.a;
                    if (fVar != null) {
                        fVar.g();
                    }
                }
                com.jd.dynamic.lib.utils.t.e("PreParseElTask", "holder parse finish take time = " + DynamicMtaUtil.getCurMicroseconds(System.nanoTime() - aVar.b));
                if (this.f1854m.d - 1 == aVar.f2257c.a) {
                    if (this.f1854m.a != null) {
                        this.f1854m.a.onPreParseFinish();
                    }
                    com.jd.dynamic.lib.utils.t.e("PreParseElTask", "all finish take time = " + DynamicMtaUtil.getCurMicroseconds(System.nanoTime() - this.f1854m.f1852e));
                }
            }
        }
    }

    /* loaded from: classes13.dex */
    static class PreParseEngineTask extends com.jd.dynamic.lib.utils.q<PreParseConfig, Void, e.a> {

        /* renamed from: m  reason: collision with root package name */
        private final DynamicPreParse f1855m;

        public PreParseEngineTask(DynamicPreParse dynamicPreParse) {
            this.f1855m = dynamicPreParse;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.jd.dynamic.lib.utils.q
        /* renamed from: v  reason: merged with bridge method [inline-methods] */
        public e.a b(PreParseConfig... preParseConfigArr) {
            if (preParseConfigArr == null || preParseConfigArr.length <= 0) {
                return null;
            }
            return new com.jd.dynamic.lib.j.e(preParseConfigArr[0]).a();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.jd.dynamic.lib.utils.q
        /* renamed from: w  reason: merged with bridge method [inline-methods] */
        public void g(e.a aVar) {
            com.jd.dynamic.lib.j.f fVar;
            if (aVar == null || (fVar = aVar.a) == null || fVar.a() == null) {
                return;
            }
            fVar.a().h();
            if (fVar.a().G(fVar.e())) {
                new PreParseElTask(this.f1855m).executeOnExecutor(com.jd.dynamic.lib.utils.q.THREAD_POOL_EXECUTOR, aVar);
            }
        }
    }

    public DynamicPreParse(PreParseConfig preParseConfig, IPreParseFinishListener iPreParseFinishListener) {
        this.a = iPreParseFinishListener;
        ArrayList<PreParseConfig> arrayList = new ArrayList<>();
        this.b = arrayList;
        arrayList.add(preParseConfig);
    }

    public DynamicPreParse(ArrayList<PreParseConfig> arrayList, IPreParseFinishListener iPreParseFinishListener) {
        this.a = iPreParseFinishListener;
        this.b = arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(e.a aVar) {
        String module = aVar.f2257c.mConfig.getModule();
        Iterator it = new ArrayList(this.f1851c.keySet()).iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (str.contains(module)) {
                this.f1851c.remove(str);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean f(e.a aVar) {
        int i2 = aVar.f2257c.preCreateCount;
        String a = aVar.a();
        AtomicInteger atomicInteger = this.f1851c.get(a);
        if (atomicInteger == null) {
            atomicInteger = new AtomicInteger(1);
            this.f1851c.put(a, atomicInteger);
        } else {
            atomicInteger.incrementAndGet();
        }
        return atomicInteger.get() <= i2;
    }

    public void execParse() {
        if (!com.jd.dynamic.b.a.b.o().B() || !com.jd.dynamic.lib.utils.m.I(this.b)) {
            IPreParseFinishListener iPreParseFinishListener = this.a;
            if (iPreParseFinishListener != null) {
                iPreParseFinishListener.onPreParseFinish();
                return;
            }
            return;
        }
        this.d = this.b.size();
        for (int i2 = 0; i2 < this.b.size(); i2++) {
            PreParseConfig preParseConfig = this.b.get(i2);
            if (preParseConfig != null) {
                preParseConfig.a = i2;
                if (preParseConfig.preCreateCount > 0) {
                    String str = preParseConfig.mConfig.getModule() + CartConstant.KEY_YB_INFO_LINK + preParseConfig.mConfig.getTemplateId();
                    if (DynamicSdk.getEngine().templateEngineCache.get(str) == null) {
                        DynamicSdk.getEngine().templateEngineCache.put(str, new ArrayList<>());
                    }
                }
                new PreParseEngineTask(this).executeOnExecutor(com.jd.dynamic.lib.utils.q.THREAD_POOL_EXECUTOR, preParseConfig);
            }
        }
    }
}
