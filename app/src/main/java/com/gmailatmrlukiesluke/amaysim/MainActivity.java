package com.gmailatmrlukiesluke.amaysim;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gmailatmrlukiesluke.amaysim.Parser.ParserData;
import com.gmailatmrlukiesluke.amaysim.model.IncludedProductsModel;
import com.gmailatmrlukiesluke.amaysim.model.IncludedServiceModel;
import com.gmailatmrlukiesluke.amaysim.model.IncludedSubscriptionsModel;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar mToolbar;
    private TextInputLayout mInputLayoutMSN;
    private EditText edtMSN;
    private Button btnLogin;
    private Resources mResources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= 21) {
            TransitionInflater inflater = TransitionInflater.from(this);
            Transition transition = inflater.inflateTransition(R.transition.transition_a);
            getWindow().setExitTransition(transition);

            Slide slide = new Slide();
            slide.setDuration(1000);
            getWindow().setReenterTransition(slide);
        }

        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);

        mInputLayoutMSN = (TextInputLayout) findViewById(R.id.input_layout_msn);
        edtMSN = (EditText) findViewById(R.id.edtMSN);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

        //edtMSN.setText("0468874507");

        mInputLayoutMSN.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence text, int i, int i1, int i2) {
                if (text.length() < 1) {
                    mInputLayoutMSN.setError(getString(R.string.msn_required));
                    mInputLayoutMSN.setErrorEnabled(true);
                } else {
                    mInputLayoutMSN.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    public JSONObject getCollectionData() {
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
        String attrMSN = "";
        ArrayList<IncludedProductsModel> includedProductsModelArrayList = ParserData.parseIncludedDataProducts(getCollectionData());
        ArrayList<IncludedServiceModel> includedServiceModelArrayList = ParserData.parseIncludedDataServices(getCollectionData());
        ArrayList<IncludedSubscriptionsModel> includedSubscriptionsModelArrayList = ParserData.parseIncludedDataSubscription(getCollectionData());

        for (int i = 0; i < includedServiceModelArrayList.size(); i++) {
            IncludedServiceModel current = includedServiceModelArrayList.get(i);

            for (int j = 0; j < current.getAttributesList().size(); j++) {
                IncludedServiceModel.Attributes c = current.getAttributesList().get(j);
                attrMSN = c.getMsn().trim();
                Log.d("LWG", "current.getMsn(): " + c.getMsn());
            }
        }

        if (isEmptyMSN()) {
            mInputLayoutMSN.setError(getString(R.string.msn_required));

        } else {

            if (attrMSN.equals(edtMSN.getText().toString().trim())) {
                mInputLayoutMSN.setError(null);

                Gson gson = new Gson();
                String json = gson.toJson(includedServiceModelArrayList.get(0));
                //Toast.makeText(this, "Collection Data: " + json, Toast.LENGTH_LONG).show();

                ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, null);
                Intent intent = new Intent(this, WelcomeActivity.class);
                intent.putExtra("msn", edtMSN.getText().toString().trim());
                startActivity(intent, compat.toBundle());

            } else {
                mInputLayoutMSN.setError(getString(R.string.msn_invalid));
            }
        }
    }

    private boolean isEmptyMSN() {
        return edtMSN.getText() == null || edtMSN.getText().toString() == null || edtMSN.getText().toString().isEmpty(); //|| edtUsername.getText().toString().trim().isEmpty();
    }
}
