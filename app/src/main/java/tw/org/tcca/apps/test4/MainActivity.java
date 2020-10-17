package tw.org.tcca.apps.test4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {
    private RequestQueue queue;
    private TextView lotto;
    private EditText account, passwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lotto = findViewById(R.id.lottoView);
        account = findViewById(R.id.account);
        passwd = findViewById(R.id.passwd);
        queue = Volley.newRequestQueue(this);
    }

    public void createLotto(View view) {
        StringRequest request = new StringRequest(
                Request.Method.GET,
                "http://10.0.100.124/brad02.php?account=" + account.getText().toString() +
                    "&passwd=" + passwd.getText().toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("bradlog", response);
                        lotto.setText(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.v("bradlog", error.toString());
                    }
                }
        );
        queue.add(request);
    }
}