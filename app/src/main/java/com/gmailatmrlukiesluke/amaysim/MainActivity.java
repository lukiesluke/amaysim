package com.gmailatmrlukiesluke.amaysim;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gmailatmrlukiesluke.amaysim.Parser.ParserData;
import com.gmailatmrlukiesluke.amaysim.model.IncludedModel;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtMSN;
    private Button btnLogin;
    private Resources mResources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtMSN = (EditText) findViewById(R.id.edtMSN);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

    }

    public JSONObject getDataCollection() {
        mResources = getResources();
        InputStream inputStream = mResources.openRawResource(R.raw.collection);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        JSONObject jsonObjectCollection = null;

        try {
            String line = "";
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            jsonObjectCollection = new JSONObject(response.toString());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return jsonObjectCollection;
    }

    @Override
    public void onClick(View v) {
        ArrayList<IncludedModel> includedModelArrayList = ParserData.parseIncludedData(getDataCollection());
        Gson gson = new Gson();
        String json = gson.toJson(includedModelArrayList);
        Log.d("LWG", json);

        Toast.makeText(this, "Collection Data: " + json, Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }
}
