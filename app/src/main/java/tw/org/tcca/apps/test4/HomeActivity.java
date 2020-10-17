package tw.org.tcca.apps.test4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    private MainApp mainApp;
    private TextView who;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mainApp = (MainApp)getApplication();
        ((TextView)findViewById(R.id.who)).setText(mainApp.sp.getString("realname", "xxx"));
    }

    public void logout(View view) {
        mainApp.editor.clear();
        mainApp.editor.commit();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}