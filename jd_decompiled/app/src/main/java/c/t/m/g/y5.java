package c.t.m.g;

import android.annotation.SuppressLint;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.LruCache;
import android.util.Pair;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;

@SuppressLint({"NewApi"})
/* loaded from: classes.dex */
public class y5 extends p5 {
    public LruCache<String, Pair<Double, Double>> d;

    public y5(String str) {
        super(str, "check cell");
        this.d = new LruCache<>(100);
    }

    @Override // c.t.m.g.p5
    public void a() {
        super.a();
        this.d.evictAll();
    }

    @Override // c.t.m.g.p5
    public boolean c(Bundle bundle) {
        String string = bundle.getString("cellkey");
        Location location = (Location) bundle.getParcelable(ThemeTitleConstant.TITLE_LOCATION_DRAWABLE_ID);
        if (location == null) {
            return true;
        }
        if (TextUtils.isEmpty(string)) {
            return false;
        }
        Pair<Double, Double> pair = this.d.get(string);
        if (pair != null) {
            return u0.b(location.getLatitude(), location.getLongitude(), ((Double) pair.first).doubleValue(), ((Double) pair.second).doubleValue()) < 6000.0d;
        }
        this.d.put(string, Pair.create(Double.valueOf(location.getLatitude()), Double.valueOf(location.getLongitude())));
        return true;
    }
}
