package com.facebook.imagepipeline.cache;

import com.facebook.binaryresource.BinaryResource;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.WriterCallback;
import com.facebook.cache.disk.FileCache;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.memory.PooledByteStreams;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.instrumentation.FrescoInstrumenter;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import f.f;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class BufferedDiskCache {
    private static final Class<?> TAG = BufferedDiskCache.class;
    private final FileCache mFileCache;
    private final ImageCacheStatsTracker mImageCacheStatsTracker;
    private final PooledByteBufferFactory mPooledByteBufferFactory;
    private final PooledByteStreams mPooledByteStreams;
    private final Executor mReadExecutor;
    private final StagingArea mStagingArea = StagingArea.getInstance();
    private final Executor mWriteExecutor;

    public BufferedDiskCache(FileCache fileCache, PooledByteBufferFactory pooledByteBufferFactory, PooledByteStreams pooledByteStreams, Executor executor, Executor executor2, ImageCacheStatsTracker imageCacheStatsTracker) {
        this.mFileCache = fileCache;
        this.mPooledByteBufferFactory = pooledByteBufferFactory;
        this.mPooledByteStreams = pooledByteStreams;
        this.mReadExecutor = executor;
        this.mWriteExecutor = executor2;
        this.mImageCacheStatsTracker = imageCacheStatsTracker;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean checkInStagingAreaAndFileCache(CacheKey cacheKey) {
        EncodedImage encodedImage = this.mStagingArea.get(cacheKey);
        if (encodedImage != null) {
            encodedImage.close();
            FLog.v(TAG, "Found image for %s in staging area", cacheKey.getUriString());
            this.mImageCacheStatsTracker.onStagingAreaHit(cacheKey);
            return true;
        }
        FLog.v(TAG, "Did not find image for %s in staging area", cacheKey.getUriString());
        this.mImageCacheStatsTracker.onStagingAreaMiss(cacheKey);
        try {
            return this.mFileCache.hasKey(cacheKey);
        } catch (Exception unused) {
            return false;
        }
    }

    private f<Boolean> containsAsync(final CacheKey cacheKey) {
        try {
            FrescoInstrumenter.onBeforeSubmitWork("BufferedDiskCache_containsAsync");
            return f.c(new Callable<Boolean>
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0011: RETURN 
                  (wrap: f.f<java.lang.Boolean> : 0x000d: INVOKE 
                  (wrap: java.util.concurrent.Callable<java.lang.Boolean> : 0x0008: CONSTRUCTOR 
                  (r4v0 'this' com.facebook.imagepipeline.cache.BufferedDiskCache A[IMMUTABLE_TYPE, THIS])
                  (r0 I:java.lang.Object A[DONT_GENERATE, DONT_INLINE, REMOVE])
                  (r5v0 'cacheKey' com.facebook.cache.common.CacheKey A[DONT_INLINE])
                 A[Catch: Exception -> 0x0012, MD:(com.facebook.imagepipeline.cache.BufferedDiskCache, java.lang.Object, com.facebook.cache.common.CacheKey):void (m), WRAPPED] call: com.facebook.imagepipeline.cache.BufferedDiskCache.1.<init>(com.facebook.imagepipeline.cache.BufferedDiskCache, java.lang.Object, com.facebook.cache.common.CacheKey):void type: CONSTRUCTOR)
                  (wrap: java.util.concurrent.Executor : 0x000b: IGET (r4v0 'this' com.facebook.imagepipeline.cache.BufferedDiskCache A[IMMUTABLE_TYPE, THIS]) A[Catch: Exception -> 0x0012, WRAPPED] com.facebook.imagepipeline.cache.BufferedDiskCache.mReadExecutor java.util.concurrent.Executor)
                 type: STATIC call: f.f.c(java.util.concurrent.Callable, java.util.concurrent.Executor):f.f A[Catch: Exception -> 0x0012, MD:<TResult>:(java.util.concurrent.Callable<TResult>, java.util.concurrent.Executor):f.f<TResult> (m), TRY_LEAVE, WRAPPED])
                 in method: com.facebook.imagepipeline.cache.BufferedDiskCache.containsAsync(com.facebook.cache.common.CacheKey):f.f<java.lang.Boolean>, file: classes.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:302)
                	at jadx.core.dex.regions.TryCatchRegion.generate(TryCatchRegion.java:85)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                Caused by: java.lang.NullPointerException
                	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:798)
                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1105)
                	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:872)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:421)
                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:367)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
                	... 21 more
                */
            /*
                this = this;
                java.lang.String r0 = "BufferedDiskCache_containsAsync"
                java.lang.Object r0 = com.facebook.imagepipeline.instrumentation.FrescoInstrumenter.onBeforeSubmitWork(r0)     // Catch: java.lang.Exception -> L12
                com.facebook.imagepipeline.cache.BufferedDiskCache$1 r1 = new com.facebook.imagepipeline.cache.BufferedDiskCache$1     // Catch: java.lang.Exception -> L12
                r1.<init>()     // Catch: java.lang.Exception -> L12
                java.util.concurrent.Executor r0 = r4.mReadExecutor     // Catch: java.lang.Exception -> L12
                f.f r5 = f.f.c(r1, r0)     // Catch: java.lang.Exception -> L12
                return r5
            L12:
                r0 = move-exception
                java.lang.Class<?> r1 = com.facebook.imagepipeline.cache.BufferedDiskCache.TAG
                r2 = 1
                java.lang.Object[] r2 = new java.lang.Object[r2]
                java.lang.String r5 = r5.getUriString()
                r3 = 0
                r2[r3] = r5
                java.lang.String r5 = "Failed to schedule disk-cache read for %s"
                com.facebook.common.logging.FLog.w(r1, r0, r5, r2)
                f.f r5 = f.f.m(r0)
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.cache.BufferedDiskCache.containsAsync(com.facebook.cache.common.CacheKey):f.f");
        }

        private f<EncodedImage> foundPinnedImage(CacheKey cacheKey, EncodedImage encodedImage) {
            FLog.v(TAG, "Found image for %s in staging area", cacheKey.getUriString());
            this.mImageCacheStatsTracker.onStagingAreaHit(cacheKey);
            return f.n(encodedImage);
        }

        private f<EncodedImage> getAsync(final CacheKey cacheKey, final AtomicBoolean atomicBoolean) {
            try {
                FrescoInstrumenter.onBeforeSubmitWork("BufferedDiskCache_getAsync");
                return f.c(new Callable<EncodedImage>
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0011: RETURN 
                      (wrap: f.f<com.facebook.imagepipeline.image.EncodedImage> : 0x000d: INVOKE 
                      (wrap: java.util.concurrent.Callable<com.facebook.imagepipeline.image.EncodedImage> : 0x0008: CONSTRUCTOR 
                      (r3v0 'this' com.facebook.imagepipeline.cache.BufferedDiskCache A[IMMUTABLE_TYPE, THIS])
                      (r0 I:java.lang.Object A[DONT_GENERATE, DONT_INLINE, REMOVE])
                      (r5v0 'atomicBoolean' java.util.concurrent.atomic.AtomicBoolean A[DONT_INLINE])
                      (r4v0 'cacheKey' com.facebook.cache.common.CacheKey A[DONT_INLINE])
                     A[Catch: Exception -> 0x0012, MD:(com.facebook.imagepipeline.cache.BufferedDiskCache, java.lang.Object, java.util.concurrent.atomic.AtomicBoolean, com.facebook.cache.common.CacheKey):void (m), WRAPPED] call: com.facebook.imagepipeline.cache.BufferedDiskCache.2.<init>(com.facebook.imagepipeline.cache.BufferedDiskCache, java.lang.Object, java.util.concurrent.atomic.AtomicBoolean, com.facebook.cache.common.CacheKey):void type: CONSTRUCTOR)
                      (wrap: java.util.concurrent.Executor : 0x000b: IGET (r3v0 'this' com.facebook.imagepipeline.cache.BufferedDiskCache A[IMMUTABLE_TYPE, THIS]) A[Catch: Exception -> 0x0012, WRAPPED] com.facebook.imagepipeline.cache.BufferedDiskCache.mReadExecutor java.util.concurrent.Executor)
                     type: STATIC call: f.f.c(java.util.concurrent.Callable, java.util.concurrent.Executor):f.f A[Catch: Exception -> 0x0012, MD:<TResult>:(java.util.concurrent.Callable<TResult>, java.util.concurrent.Executor):f.f<TResult> (m), TRY_LEAVE, WRAPPED])
                     in method: com.facebook.imagepipeline.cache.BufferedDiskCache.getAsync(com.facebook.cache.common.CacheKey, java.util.concurrent.atomic.AtomicBoolean):f.f<com.facebook.imagepipeline.image.EncodedImage>, file: classes.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                    	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                    	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:302)
                    	at jadx.core.dex.regions.TryCatchRegion.generate(TryCatchRegion.java:85)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                    Caused by: java.lang.NullPointerException
                    	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:798)
                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1105)
                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:872)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:421)
                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:367)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
                    	... 21 more
                    */
                /*
                    this = this;
                    java.lang.String r0 = "BufferedDiskCache_getAsync"
                    java.lang.Object r0 = com.facebook.imagepipeline.instrumentation.FrescoInstrumenter.onBeforeSubmitWork(r0)     // Catch: java.lang.Exception -> L12
                    com.facebook.imagepipeline.cache.BufferedDiskCache$2 r1 = new com.facebook.imagepipeline.cache.BufferedDiskCache$2     // Catch: java.lang.Exception -> L12
                    r1.<init>()     // Catch: java.lang.Exception -> L12
                    java.util.concurrent.Executor r5 = r3.mReadExecutor     // Catch: java.lang.Exception -> L12
                    f.f r4 = f.f.c(r1, r5)     // Catch: java.lang.Exception -> L12
                    return r4
                L12:
                    r5 = move-exception
                    java.lang.Class<?> r0 = com.facebook.imagepipeline.cache.BufferedDiskCache.TAG
                    r1 = 1
                    java.lang.Object[] r1 = new java.lang.Object[r1]
                    java.lang.String r4 = r4.getUriString()
                    r2 = 0
                    r1[r2] = r4
                    java.lang.String r4 = "Failed to schedule disk-cache read for %s"
                    com.facebook.common.logging.FLog.w(r0, r5, r4, r1)
                    f.f r4 = f.f.m(r5)
                    return r4
                */
                throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.cache.BufferedDiskCache.getAsync(com.facebook.cache.common.CacheKey, java.util.concurrent.atomic.AtomicBoolean):f.f");
            }

            /* JADX INFO: Access modifiers changed from: private */
            @Nullable
            public PooledByteBuffer readFromDiskCache(CacheKey cacheKey) {
                try {
                    Class<?> cls = TAG;
                    FLog.v(cls, "Disk cache read for %s", cacheKey.getUriString());
                    BinaryResource resource = this.mFileCache.getResource(cacheKey);
                    if (resource == null) {
                        FLog.v(cls, "Disk cache miss for %s", cacheKey.getUriString());
                        this.mImageCacheStatsTracker.onDiskCacheMiss(cacheKey);
                        return null;
                    }
                    FLog.v(cls, "Found entry in disk cache for %s", cacheKey.getUriString());
                    this.mImageCacheStatsTracker.onDiskCacheHit(cacheKey);
                    InputStream openStream = resource.openStream();
                    PooledByteBuffer newByteBuffer = this.mPooledByteBufferFactory.newByteBuffer(openStream, (int) resource.size());
                    openStream.close();
                    FLog.v(cls, "Successful read from disk cache for %s", cacheKey.getUriString());
                    return newByteBuffer;
                } catch (IOException e2) {
                    FLog.w(TAG, e2, "Exception reading from cache for %s", cacheKey.getUriString());
                    this.mImageCacheStatsTracker.onDiskCacheGetFail(cacheKey);
                    throw e2;
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void writeToDiskCache(CacheKey cacheKey, final EncodedImage encodedImage) {
                Class<?> cls = TAG;
                FLog.v(cls, "About to write to disk-cache for key %s", cacheKey.getUriString());
                try {
                    this.mFileCache.insert(cacheKey, new WriterCallback() { // from class: com.facebook.imagepipeline.cache.BufferedDiskCache.6
                        @Override // com.facebook.cache.common.WriterCallback
                        public void write(OutputStream outputStream) {
                            BufferedDiskCache.this.mPooledByteStreams.copy(encodedImage.getInputStream(), outputStream);
                        }
                    });
                    this.mImageCacheStatsTracker.onDiskCachePut(cacheKey);
                    FLog.v(cls, "Successful disk-cache write for key %s", cacheKey.getUriString());
                } catch (IOException e2) {
                    FLog.w(TAG, e2, "Failed to write to disk-cache for key %s", cacheKey.getUriString());
                }
            }

            public f<Void> clearAll() {
                this.mStagingArea.clearAll();
                FrescoInstrumenter.onBeforeSubmitWork("BufferedDiskCache_clearAll");
                try {
                    return f.c(new Callable<Void>
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0016: RETURN 
                          (wrap: f.f<java.lang.Void> : 0x0012: INVOKE 
                          (wrap: java.util.concurrent.Callable<java.lang.Void> : 0x000d: CONSTRUCTOR 
                          (r4v0 'this' com.facebook.imagepipeline.cache.BufferedDiskCache A[IMMUTABLE_TYPE, THIS])
                          (r0 I:java.lang.Object A[DONT_GENERATE, DONT_INLINE, REMOVE])
                         A[Catch: Exception -> 0x0017, MD:(com.facebook.imagepipeline.cache.BufferedDiskCache, java.lang.Object):void (m), WRAPPED] call: com.facebook.imagepipeline.cache.BufferedDiskCache.5.<init>(com.facebook.imagepipeline.cache.BufferedDiskCache, java.lang.Object):void type: CONSTRUCTOR)
                          (wrap: java.util.concurrent.Executor : 0x0010: IGET (r4v0 'this' com.facebook.imagepipeline.cache.BufferedDiskCache A[IMMUTABLE_TYPE, THIS]) A[Catch: Exception -> 0x0017, WRAPPED] com.facebook.imagepipeline.cache.BufferedDiskCache.mWriteExecutor java.util.concurrent.Executor)
                         type: STATIC call: f.f.c(java.util.concurrent.Callable, java.util.concurrent.Executor):f.f A[Catch: Exception -> 0x0017, MD:<TResult>:(java.util.concurrent.Callable<TResult>, java.util.concurrent.Executor):f.f<TResult> (m), TRY_LEAVE, WRAPPED])
                         in method: com.facebook.imagepipeline.cache.BufferedDiskCache.clearAll():f.f<java.lang.Void>, file: classes.dex
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                        	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                        	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:302)
                        	at jadx.core.dex.regions.TryCatchRegion.generate(TryCatchRegion.java:85)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                        	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
                        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
                        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
                        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
                        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                        Caused by: java.lang.NullPointerException
                        	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:798)
                        	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
                        	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                        	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1105)
                        	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:872)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:421)
                        	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:367)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
                        	... 21 more
                        */
                    /*
                        this = this;
                        com.facebook.imagepipeline.cache.StagingArea r0 = r4.mStagingArea
                        r0.clearAll()
                        java.lang.String r0 = "BufferedDiskCache_clearAll"
                        java.lang.Object r0 = com.facebook.imagepipeline.instrumentation.FrescoInstrumenter.onBeforeSubmitWork(r0)
                        com.facebook.imagepipeline.cache.BufferedDiskCache$5 r1 = new com.facebook.imagepipeline.cache.BufferedDiskCache$5     // Catch: java.lang.Exception -> L17
                        r1.<init>()     // Catch: java.lang.Exception -> L17
                        java.util.concurrent.Executor r0 = r4.mWriteExecutor     // Catch: java.lang.Exception -> L17
                        f.f r0 = f.f.c(r1, r0)     // Catch: java.lang.Exception -> L17
                        return r0
                    L17:
                        r0 = move-exception
                        java.lang.Class<?> r1 = com.facebook.imagepipeline.cache.BufferedDiskCache.TAG
                        r2 = 0
                        java.lang.Object[] r2 = new java.lang.Object[r2]
                        java.lang.String r3 = "Failed to schedule disk-cache clear"
                        com.facebook.common.logging.FLog.w(r1, r0, r3, r2)
                        f.f r0 = f.f.m(r0)
                        return r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.cache.BufferedDiskCache.clearAll():f.f");
                }

                public f<Boolean> contains(CacheKey cacheKey) {
                    return containsSync(cacheKey) ? f.n(Boolean.TRUE) : containsAsync(cacheKey);
                }

                public boolean containsSync(CacheKey cacheKey) {
                    return this.mStagingArea.containsKey(cacheKey) || this.mFileCache.hasKeySync(cacheKey);
                }

                public boolean diskCheckSync(CacheKey cacheKey) {
                    if (containsSync(cacheKey)) {
                        return true;
                    }
                    return checkInStagingAreaAndFileCache(cacheKey);
                }

                public f<EncodedImage> get(CacheKey cacheKey, AtomicBoolean atomicBoolean) {
                    try {
                        if (FrescoSystrace.isTracing()) {
                            FrescoSystrace.beginSection("BufferedDiskCache#get");
                        }
                        EncodedImage encodedImage = this.mStagingArea.get(cacheKey);
                        if (encodedImage != null) {
                            return foundPinnedImage(cacheKey, encodedImage);
                        }
                        f<EncodedImage> async = getAsync(cacheKey, atomicBoolean);
                        if (FrescoSystrace.isTracing()) {
                            FrescoSystrace.endSection();
                        }
                        return async;
                    } finally {
                        if (FrescoSystrace.isTracing()) {
                            FrescoSystrace.endSection();
                        }
                    }
                }

                public long getSize() {
                    return this.mFileCache.getSize();
                }

                public void put(final CacheKey cacheKey, EncodedImage encodedImage) {
                    try {
                        if (FrescoSystrace.isTracing()) {
                            FrescoSystrace.beginSection("BufferedDiskCache#put");
                        }
                        Preconditions.checkNotNull(cacheKey);
                        Preconditions.checkArgument(EncodedImage.isValid(encodedImage));
                        this.mStagingArea.put(cacheKey, encodedImage);
                        final EncodedImage cloneOrNull = EncodedImage.cloneOrNull(encodedImage);
                        try {
                            FrescoInstrumenter.onBeforeSubmitWork("BufferedDiskCache_putAsync");
                            this.mWriteExecutor.execute(new Runnable
                            /*  JADX ERROR: Method code generation error
                                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x002b: INVOKE 
                                  (wrap: java.util.concurrent.Executor : 0x0024: IGET (r7v0 'this' com.facebook.imagepipeline.cache.BufferedDiskCache A[IMMUTABLE_TYPE, THIS]) A[Catch: Exception -> 0x002f, all -> 0x0053, WRAPPED] com.facebook.imagepipeline.cache.BufferedDiskCache.mWriteExecutor java.util.concurrent.Executor)
                                  (wrap: java.lang.Runnable : 0x0028: CONSTRUCTOR 
                                  (r7v0 'this' com.facebook.imagepipeline.cache.BufferedDiskCache A[IMMUTABLE_TYPE, THIS])
                                  (r1 I:java.lang.Object A[DONT_GENERATE, DONT_INLINE, REMOVE])
                                  (r8v0 'cacheKey' com.facebook.cache.common.CacheKey A[DONT_INLINE])
                                  (r0v3 'cloneOrNull' com.facebook.imagepipeline.image.EncodedImage A[DONT_INLINE])
                                 A[Catch: Exception -> 0x002f, all -> 0x0053, MD:(com.facebook.imagepipeline.cache.BufferedDiskCache, java.lang.Object, com.facebook.cache.common.CacheKey, com.facebook.imagepipeline.image.EncodedImage):void (m), WRAPPED] call: com.facebook.imagepipeline.cache.BufferedDiskCache.3.<init>(com.facebook.imagepipeline.cache.BufferedDiskCache, java.lang.Object, com.facebook.cache.common.CacheKey, com.facebook.imagepipeline.image.EncodedImage):void type: CONSTRUCTOR)
                                 type: INTERFACE call: java.util.concurrent.Executor.execute(java.lang.Runnable):void A[Catch: Exception -> 0x002f, all -> 0x0053, MD:(java.lang.Runnable):void (c), TRY_LEAVE] in method: com.facebook.imagepipeline.cache.BufferedDiskCache.put(com.facebook.cache.common.CacheKey, com.facebook.imagepipeline.image.EncodedImage):void, file: classes.dex
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                                	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:302)
                                	at jadx.core.dex.regions.TryCatchRegion.generate(TryCatchRegion.java:85)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                                	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:302)
                                	at jadx.core.dex.regions.TryCatchRegion.generate(TryCatchRegion.java:85)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
                                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
                                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
                                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                                Caused by: java.lang.NullPointerException
                                	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:798)
                                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
                                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
                                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
                                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
                                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                                	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1105)
                                	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:872)
                                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:421)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
                                	... 27 more
                                */
                            /*
                                this = this;
                                boolean r0 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()     // Catch: java.lang.Throwable -> L53
                                if (r0 == 0) goto Lb
                                java.lang.String r0 = "BufferedDiskCache#put"
                                com.facebook.imagepipeline.systrace.FrescoSystrace.beginSection(r0)     // Catch: java.lang.Throwable -> L53
                            Lb:
                                com.facebook.common.internal.Preconditions.checkNotNull(r8)     // Catch: java.lang.Throwable -> L53
                                boolean r0 = com.facebook.imagepipeline.image.EncodedImage.isValid(r9)     // Catch: java.lang.Throwable -> L53
                                com.facebook.common.internal.Preconditions.checkArgument(r0)     // Catch: java.lang.Throwable -> L53
                                com.facebook.imagepipeline.cache.StagingArea r0 = r7.mStagingArea     // Catch: java.lang.Throwable -> L53
                                r0.put(r8, r9)     // Catch: java.lang.Throwable -> L53
                                com.facebook.imagepipeline.image.EncodedImage r0 = com.facebook.imagepipeline.image.EncodedImage.cloneOrNull(r9)     // Catch: java.lang.Throwable -> L53
                                java.lang.String r1 = "BufferedDiskCache_putAsync"
                                java.lang.Object r1 = com.facebook.imagepipeline.instrumentation.FrescoInstrumenter.onBeforeSubmitWork(r1)     // Catch: java.lang.Exception -> L2f java.lang.Throwable -> L53
                                java.util.concurrent.Executor r2 = r7.mWriteExecutor     // Catch: java.lang.Exception -> L2f java.lang.Throwable -> L53
                                com.facebook.imagepipeline.cache.BufferedDiskCache$3 r3 = new com.facebook.imagepipeline.cache.BufferedDiskCache$3     // Catch: java.lang.Exception -> L2f java.lang.Throwable -> L53
                                r3.<init>()     // Catch: java.lang.Exception -> L2f java.lang.Throwable -> L53
                                r2.execute(r3)     // Catch: java.lang.Exception -> L2f java.lang.Throwable -> L53
                                goto L49
                            L2f:
                                r1 = move-exception
                                java.lang.Class<?> r2 = com.facebook.imagepipeline.cache.BufferedDiskCache.TAG     // Catch: java.lang.Throwable -> L53
                                java.lang.String r3 = "Failed to schedule disk-cache write for %s"
                                r4 = 1
                                java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> L53
                                r5 = 0
                                java.lang.String r6 = r8.getUriString()     // Catch: java.lang.Throwable -> L53
                                r4[r5] = r6     // Catch: java.lang.Throwable -> L53
                                com.facebook.common.logging.FLog.w(r2, r1, r3, r4)     // Catch: java.lang.Throwable -> L53
                                com.facebook.imagepipeline.cache.StagingArea r1 = r7.mStagingArea     // Catch: java.lang.Throwable -> L53
                                r1.remove(r8, r9)     // Catch: java.lang.Throwable -> L53
                                com.facebook.imagepipeline.image.EncodedImage.closeSafely(r0)     // Catch: java.lang.Throwable -> L53
                            L49:
                                boolean r8 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
                                if (r8 == 0) goto L52
                                com.facebook.imagepipeline.systrace.FrescoSystrace.endSection()
                            L52:
                                return
                            L53:
                                r8 = move-exception
                                boolean r9 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
                                if (r9 == 0) goto L5d
                                com.facebook.imagepipeline.systrace.FrescoSystrace.endSection()
                            L5d:
                                throw r8
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.cache.BufferedDiskCache.put(com.facebook.cache.common.CacheKey, com.facebook.imagepipeline.image.EncodedImage):void");
                        }

                        public f<Void> remove(final CacheKey cacheKey) {
                            Preconditions.checkNotNull(cacheKey);
                            this.mStagingArea.remove(cacheKey);
                            try {
                                FrescoInstrumenter.onBeforeSubmitWork("BufferedDiskCache_remove");
                                return f.c(new Callable<Void>
                                /*  JADX ERROR: Method code generation error
                                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0019: RETURN 
                                      (wrap: f.f<java.lang.Void> : 0x0015: INVOKE 
                                      (wrap: java.util.concurrent.Callable<java.lang.Void> : 0x0010: CONSTRUCTOR 
                                      (r4v0 'this' com.facebook.imagepipeline.cache.BufferedDiskCache A[IMMUTABLE_TYPE, THIS])
                                      (r0 I:java.lang.Object A[DONT_GENERATE, DONT_INLINE, REMOVE])
                                      (r5v0 'cacheKey' com.facebook.cache.common.CacheKey A[DONT_INLINE])
                                     A[Catch: Exception -> 0x001a, MD:(com.facebook.imagepipeline.cache.BufferedDiskCache, java.lang.Object, com.facebook.cache.common.CacheKey):void (m), WRAPPED] call: com.facebook.imagepipeline.cache.BufferedDiskCache.4.<init>(com.facebook.imagepipeline.cache.BufferedDiskCache, java.lang.Object, com.facebook.cache.common.CacheKey):void type: CONSTRUCTOR)
                                      (wrap: java.util.concurrent.Executor : 0x0013: IGET (r4v0 'this' com.facebook.imagepipeline.cache.BufferedDiskCache A[IMMUTABLE_TYPE, THIS]) A[Catch: Exception -> 0x001a, WRAPPED] com.facebook.imagepipeline.cache.BufferedDiskCache.mWriteExecutor java.util.concurrent.Executor)
                                     type: STATIC call: f.f.c(java.util.concurrent.Callable, java.util.concurrent.Executor):f.f A[Catch: Exception -> 0x001a, MD:<TResult>:(java.util.concurrent.Callable<TResult>, java.util.concurrent.Executor):f.f<TResult> (m), TRY_LEAVE, WRAPPED])
                                     in method: com.facebook.imagepipeline.cache.BufferedDiskCache.remove(com.facebook.cache.common.CacheKey):f.f<java.lang.Void>, file: classes.dex
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                                    	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                                    	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:302)
                                    	at jadx.core.dex.regions.TryCatchRegion.generate(TryCatchRegion.java:85)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
                                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
                                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
                                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
                                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                                    Caused by: java.lang.NullPointerException
                                    	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:798)
                                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
                                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
                                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
                                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1105)
                                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:872)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:421)
                                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
                                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
                                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:367)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
                                    	... 21 more
                                    */
                                /*
                                    this = this;
                                    com.facebook.common.internal.Preconditions.checkNotNull(r5)
                                    com.facebook.imagepipeline.cache.StagingArea r0 = r4.mStagingArea
                                    r0.remove(r5)
                                    java.lang.String r0 = "BufferedDiskCache_remove"
                                    java.lang.Object r0 = com.facebook.imagepipeline.instrumentation.FrescoInstrumenter.onBeforeSubmitWork(r0)     // Catch: java.lang.Exception -> L1a
                                    com.facebook.imagepipeline.cache.BufferedDiskCache$4 r1 = new com.facebook.imagepipeline.cache.BufferedDiskCache$4     // Catch: java.lang.Exception -> L1a
                                    r1.<init>()     // Catch: java.lang.Exception -> L1a
                                    java.util.concurrent.Executor r0 = r4.mWriteExecutor     // Catch: java.lang.Exception -> L1a
                                    f.f r5 = f.f.c(r1, r0)     // Catch: java.lang.Exception -> L1a
                                    return r5
                                L1a:
                                    r0 = move-exception
                                    java.lang.Class<?> r1 = com.facebook.imagepipeline.cache.BufferedDiskCache.TAG
                                    r2 = 1
                                    java.lang.Object[] r2 = new java.lang.Object[r2]
                                    java.lang.String r5 = r5.getUriString()
                                    r3 = 0
                                    r2[r3] = r5
                                    java.lang.String r5 = "Failed to schedule disk-cache remove for %s"
                                    com.facebook.common.logging.FLog.w(r1, r0, r5, r2)
                                    f.f r5 = f.f.m(r0)
                                    return r5
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.cache.BufferedDiskCache.remove(com.facebook.cache.common.CacheKey):f.f");
                            }
                        }
