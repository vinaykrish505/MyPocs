package com.pocs.incresol.mypocs.RFCCalling;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.pocs.incresol.mypocs.R;

import org.json.JSONObject;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.protocol.HTTP;
import cz.msebera.android.httpclient.util.EntityUtils;

public class RFCCallTest extends AppCompatActivity {

    String MY_APP_TAG = "RFCCallTest";
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rfccall_test);
        textView = (TextView) findViewById(R.id.textView);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        String username = "bemolPVM";
        String password = "pvm2012";
        String url = "http://180.200.1.231:4567/sap/ZRFC_CODCLI";


        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(url);
            String source = username + ":" + password;
            String base64EncodedCredentials = "Basic " + android.util.Base64.encodeToString(source.getBytes(), android.util.Base64.URL_SAFE | android.util.Base64.NO_WRAP);
            String formData = "{\"ZKUNNR\":\"0001613380\"}";
            httppost.setHeader("Authorization", base64EncodedCredentials);

            httppost.setHeader(HTTP.CONTENT_TYPE, "application/json");

            JSONObject obj = new JSONObject();

//            obj.put("day", String.valueOf(2));
//            obj.put("emailId", "userTest@gmail.com");
//            obj.put("month", String.valueOf(5));
//            obj.put("year", String.valueOf(2013));
            httppost.setEntity(new StringEntity(formData, "UTF-8"));
            HttpResponse response = httpclient.execute(httppost);

            if (response.getStatusLine().getStatusCode() == 200) {
                Log.d("response ok", "ok response :/");
            } else {
                Log.d("response not ok", "Something went wrong :/ + " + response.getStatusLine().getStatusCode());
            }

            String responseBody = EntityUtils.toString(response.getEntity());
            textView.setText(responseBody);
        } catch (Exception e) {
            textView.setText("ERROR");
            e.printStackTrace();
            Log.d("tag", "Error: " + e.getMessage());
        }

		/* END Test/Play with beanstalk API */
    }
}
