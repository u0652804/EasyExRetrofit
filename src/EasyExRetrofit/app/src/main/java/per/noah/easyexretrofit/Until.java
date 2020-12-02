package per.noah.easyexretrofit;

import timber.log.Timber;

public class Until {
    public static final boolean enableTimber = true;
    public void initTimber(boolean enable) {
        if (!enable) return;

        // init Timber
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        Timber.d("Timber inited.");
    }
}
