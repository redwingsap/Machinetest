package com.arjun.machinetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    public static String URL_LOGIN ="http://services.trainees.baabtra.com/LoginService/login.php";
    public static final String KEY_USERNAME="email";
    public static final String KEY_PASSWORD="password";
    Button login;
    EditText uname,pwd;
    String s1,s2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        uname = (EditText)findViewById(R.id.editText);
        pwd = (EditText)findViewById(R.id.editText2);
        login = (Button) findViewById(R.id.button);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1 = uname.getText().toString();
                s2= pwd.getText().toString();
                StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if(response.trim().equals("success")){
                                    openProfile();
                                }else{
                                    Toast.makeText(LoginActivity.this,response, Toast.LENGTH_LONG).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(LoginActivity.this,error.toString(),Toast.LENGTH_LONG ).show();
                            }
                        }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> map = new HashMap<String,String>();
                        map.put(KEY_USERNAME,email);
                        map.put(KEY_PASSWORD,password);
                        return map;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(stringRequest);

            }
        });
    }
}
