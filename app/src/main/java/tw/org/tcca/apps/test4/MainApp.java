package tw.org.tcca.apps.test4;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MainApp extends Application {
    public RequestQueue queue;
    public SharedPreferences sp;
    public SharedPreferences.Editor editor;

    @Override
    public void onCreate() {
        super.onCreate();
        queue = Volley.newRequestQueue(this);

        sp = getSharedPreferences("config", MODE_PRIVATE);
        editor = sp.edit();
    }
}
