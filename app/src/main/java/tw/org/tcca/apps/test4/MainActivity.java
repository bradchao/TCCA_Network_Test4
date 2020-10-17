package tw.org.tcca.apps.test4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private MainApp mainApp;
    private TextView lotto;
    private EditText account, passwd, realname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainApp = (MainApp)getApplication();
        lotto = findViewById(R.id.lottoView);
        account = findViewById(R.id.account);
        passwd = findViewById(R.id.passwd);
        realname = findViewById(R.id.realname);

    }

    public void createLotto(View view) {
        StringRequest request = new StringRequest(
                Request.Method.POST,
                "http://10.0.100.124/brad03.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("bradlog", response);
                        if (response.equals("OK")) {
                            lotto.setText("註冊成功");
                            success();
                        }else{
                            lotto.setText("註冊失敗");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.v("bradlog", error.toString());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map = new HashMap<>();
                map.put("account", account.getText().toString());
                map.put("passwd", passwd.getText().toString());
                map.put("realname", realname.getText().toString());
                return map;
            }
        };
        mainApp.queue.add(request);
    }

    private void success(){
        finish();
    }
}