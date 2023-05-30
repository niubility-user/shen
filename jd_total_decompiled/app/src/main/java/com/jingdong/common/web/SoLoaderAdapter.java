package com.jingdong.common.web;

import com.facebook.soloader.SoLoader;
import com.tencent.xweb.util.LibraryEngine;

/* loaded from: classes6.dex */
public class SoLoaderAdapter implements LibraryEngine.LibraryLoader {
    @Override // com.tencent.xweb.util.LibraryEngine.LibraryLoader
    public void loadLibrary(String str) {
        SoLoader.loadLibrary(str);
    }
}
