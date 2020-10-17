package tw.org.tcca.apps.test4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private MainApp mainApp;
    private EditText account, passwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mainApp = (MainApp)getApplication();
        account = findViewById(R.id.login_account);
        passwd = findViewById(R.id.login_passwd);
    }

    public void register(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void login(View view) {
        StringRequest request = new StringRequest(
                Request.Method.POST,
                "http://10.0.100.124/login.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("bradlog", response);
                        parseLogin(response);
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
                return map;
            }
        };
        mainApp.queue.add(request);
    }

    private void parseLogin(String json){
        try {
            JSONObject root = new JSONObject(json);
            String login = root.getString("login");
            if (login.equals("OK")){
                gotoHome();
            }else{
                showErrorDialog();
            }
        } catch (JSONException e) {
            Log.v("bradlog", e.toString());
        }

    }

    private void showErrorDialog(){
        new AlertDialog.Builder(this)
                .setMessage("Login ERROR")
                .setPositiveButton("OK", null)
                .create()
                .show();
    }

    private void gotoHome(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);

    }
}