package com.pocs.incresol.mypocs;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class ShareDataToWhatsappContactActivity extends AppCompatActivity {

    TextView sendData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_data_to_whatsapp_contact);
        sendData = (TextView) findViewById(R.id.sendDataToWhatsappContact);
        sendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("smsto:" + "9490900600");
                Intent i = new Intent(Intent.ACTION_SENDTO, uri);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT, "Hello, HERO!");
                i.setPackage("com.whatsapp");
                startActivity(i);
            }
        });
    }
}
