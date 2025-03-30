package com.example.rawfile;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.rawfile.credits;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    TextView textTv;
    EditText textEt;
    private final String FILENAME = "rawtest.txt";
    String fileName = FILENAME.substring(0, FILENAME.length() - 4);
    int resourceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textTv = findViewById(R.id.textTv);
        textEt = findViewById(R.id.textEt);

        resourceId = this.getResources().getIdentifier(fileName, "raw", this.getPackageName());
    }

    public void rawFileBtn(View view) {
        try {
            InputStream iS = this.getResources().openRawResource(resourceId);
            InputStreamReader iSR = new InputStreamReader(iS);
            BufferedReader bR = new BufferedReader(iSR);
            StringBuilder sB = new StringBuilder();
            String line = bR.readLine();

            while (line != null) {
                sB.append(line + '\n');
                line = bR.readLine();
            }
            bR.close();
            iSR.close();
            iS.close();
            textTv.setText(sB.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void textBtn(View view) {
        String text = textEt.getText().toString();
        textTv.setText(text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@Nullable MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.credits) {
            Intent si = new Intent(this, credits.class);
            startActivity(si);
        }
        return true;
    }
}
