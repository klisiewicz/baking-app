package pl.karollisiewicz.baking.app;

import android.app.Application;

import com.evernote.android.state.StateSaver;

import org.androidannotations.annotations.EApplication;

@EApplication
public class BakingApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        StateSaver.setEnabledForAllActivitiesAndSupportFragments(this, true);
    }
}
