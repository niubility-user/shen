package com.jd.lib.babel.servicekit;

import com.jd.lib.babel.servicekit.imagekit.BabelImageKitServer;
import com.jd.lib.babel.servicekit.impl.BabelImageKitServerImpl;
import com.jd.lib.babel.servicekit.impl.BabelLanguageImpl;
import com.jd.lib.babel.servicekit.impl.BabelNetKitServerImpl;
import com.jd.lib.babel.servicekit.impl.CartUtil;
import com.jd.lib.babel.servicekit.impl.ClickUtil;
import com.jd.lib.babel.servicekit.impl.DpiImpl;
import com.jd.lib.babel.servicekit.impl.EventUtil;
import com.jd.lib.babel.servicekit.impl.FontUtil;
import com.jd.lib.babel.servicekit.impl.FrameUtil;
import com.jd.lib.babel.servicekit.impl.InfoBaseInfo;
import com.jd.lib.babel.servicekit.impl.LoginUtil;
import com.jd.lib.babel.servicekit.impl.ParserImpl;
import com.jd.lib.babel.servicekit.iservice.IBaseInfo;
import com.jd.lib.babel.servicekit.iservice.ICart;
import com.jd.lib.babel.servicekit.iservice.IClick;
import com.jd.lib.babel.servicekit.iservice.IDpi;
import com.jd.lib.babel.servicekit.iservice.IEvent;
import com.jd.lib.babel.servicekit.iservice.IFonts;
import com.jd.lib.babel.servicekit.iservice.IFrame;
import com.jd.lib.babel.servicekit.iservice.ILanguage;
import com.jd.lib.babel.servicekit.iservice.ILogin;
import com.jd.lib.babel.servicekit.iservice.IParser;
import com.jd.lib.babel.servicekit.networkkit.BabelNetWorkKitServer;

/* loaded from: classes13.dex */
public class Babel_Helper {
    public static void init() {
        BabelServer.putClass(BabelImageKitServer.class, BabelImageKitServerImpl.class);
        BabelServer.putClass(IParser.class, ParserImpl.class);
        BabelServer.putClass(IFrame.class, FrameUtil.class);
        BabelServer.putClass(BabelNetWorkKitServer.class, BabelNetKitServerImpl.class);
        BabelServer.putClass(IClick.class, ClickUtil.class);
        BabelServer.putClass(IDpi.class, DpiImpl.class);
        BabelServer.putClass(IBaseInfo.class, InfoBaseInfo.class);
        BabelServer.putClass(ICart.class, CartUtil.class);
        BabelServer.putClass(IEvent.class, EventUtil.class);
        BabelServer.putClass(IFonts.class, FontUtil.class);
        BabelServer.putClass(ILanguage.class, BabelLanguageImpl.class);
        BabelServer.putClass(ILogin.class, LoginUtil.class);
    }
}
