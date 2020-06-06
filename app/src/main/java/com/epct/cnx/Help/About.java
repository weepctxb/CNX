package com.epct.cnx.Help;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.epct.cnx.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_about);

        Button about_show = (Button) findViewById(R.id.about_show);
        about_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse("https://drive.google.com/uc?export=download&id=17a0VUgWyP76jmT_Lf3dihsL2v-SdFx11"),"application/pdf");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                try
                {
                    getApplicationContext().startActivity(intent);
                }
                catch (ActivityNotFoundException e)
                {
                    Toast.makeText(About.this, "Error (ActivityNotFoundException)\nPlease check that you have a PDF reader installed on your device.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}