package com.gmailatmrlukiesluke.amaysim;

import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.transition.TransitionInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.gmailatmrlukiesluke.amaysim.Parser.ParserData;
import com.gmailatmrlukiesluke.amaysim.model.IncludedProductsModel;
import com.gmailatmrlukiesluke.amaysim.model.IncludedServiceModel;
import com.gmailatmrlukiesluke.amaysim.model.IncludedSubscriptionsModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class WelcomeActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private String mMSN;
    private Resources mResources;
    private TextView tvMSN;
    private TextView tvCredit;
    private TextView tvCreditExpiry;
    private TextView tvIncludedDataBalance;
    private TextView tvIncludedCreditBalance;
    private TextView tvIncludedRolloverCreditBalance;
    private TextView tvIncludedRolloverDataBalance;
    private TextView tvIncludedInternationalTalkBalance;
    private TextView tvExpiryDate;

    private TextView tvName;
    private TextView tvIncludedData;
    private TextView tvIncludedCredit;
    private TextView tvIncludedInternationalTalk;
    private TextView tvPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        if (Build.VERSION.SDK_INT >= 21) {
            Slide slide = new Slide();
            slide.setDuration(1000);
            getWindow().setEnterTransition(slide);
            getWindow().setReturnTransition(TransitionInflater.from(this).inflateTransition(R.transition.transition_a));
        }
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mMSN = extras.getString("msn");
            //Toast.makeText(this, "Bundle extras mMSN: " + mMSN, Toast.LENGTH_LONG).show();
        }

        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvMSN = (TextView) findViewById(R.id.tvMSN);
        tvCredit = (TextView) findViewById(R.id.tvCredit);
        tvCreditExpiry = (TextView) findViewById(R.id.tvCreditExpiry);

        tvIncludedDataBalance = (TextView) findViewById(R.id.tv_included_data_balance);
        tvIncludedCreditBalance = (TextView) findViewById(R.id.tv_included_credit_balance);
        tvIncludedRolloverCreditBalance = (TextView) findViewById(R.id.tv_included_rollover_credit_balance);
        tvIncludedRolloverDataBalance = (TextView) findViewById(R.id.tv_included_rollover_data_balance);
        tvIncludedInternationalTalkBalance = (TextView) findViewById(R.id.tv_included_international_talk_balance);
        tvExpiryDate = (TextView) findViewById(R.id.tv_expiry_date);

        tvName = (TextView) findViewById(R.id.tv_name);
        tvIncludedData = (TextView) findViewById(R.id.tv_included_data);
        tvIncludedCredit = (TextView) findViewById(R.id.tv_included_credit);
        tvIncludedInternationalTalk = (TextView) findViewById(R.id.tv_included_international_talk);
        tvPrice = (TextView) findViewById(R.id.tv_price);

        CollectionDataInformation();
    }

    private void CollectionDataInformation() {
        ArrayList<IncludedServiceModel> includedServiceModelArrayList = ParserData.parseIncludedDataServices(getCollectionData());
        for (int i = 0; i < includedServiceModelArrayList.size(); i++) {
            IncludedServiceModel current = includedServiceModelArrayList.get(i);

            for (int j = 0; j < current.getAttributesList().size(); j++) {
                IncludedServiceModel.Attributes c = current.getAttributesList().get(j);
                tvMSN.setText(c.getMsn().trim());
                tvCredit.setText("$ " + c.getCredit());
                tvCreditExpiry.setText(c.getCredit_expiry().trim());
            }
        }

        ArrayList<IncludedSubscriptionsModel> includedSubscriptionsModelArrayList = ParserData.parseIncludedDataSubscription(getCollectionData());
        for (int i = 0; i < includedSubscriptionsModelArrayList.size(); i++) {
            IncludedSubscriptionsModel current = includedSubscriptionsModelArrayList.get(i);

            for (int j = 0; j < current.getAttributesList().size(); j++) {
                IncludedSubscriptionsModel.Attributes c = current.getAttributesList().get(j);
                tvIncludedDataBalance.setText("$ " + c.getIncludedDataBalance());
                tvIncludedCreditBalance.setText("$ " + c.getIncludedCreditBalance());
                tvIncludedRolloverCreditBalance.setText("$ " + c.getIncludedRolloverCreditBalance());
                tvIncludedRolloverDataBalance.setText(c.getIncludedRolloverDataBalance() + "");
                tvIncludedInternationalTalkBalance.setText(c.getIncludedInternationalTalkBalance() + "");
                tvExpiryDate.setText(c.getExpiryDate().trim());
            }
        }

        ArrayList<IncludedProductsModel> includedProductsModelArrayList = ParserData.parseIncludedDataProducts(getCollectionData());
        for (int i = 0; i < includedProductsModelArrayList.size(); i++) {
            IncludedProductsModel current = includedProductsModelArrayList.get(i);

            for (int j = 0; j < current.getAttributesList().size(); j++) {
                IncludedProductsModel.Attributes c = current.getAttributesList().get(j);
                tvName.setText(c.getName());
                tvIncludedData.setText(c.getIncludedData() + "");
                tvIncludedCredit.setText("$ " + c.getIncludedCredit());
                tvIncludedInternationalTalk.setText(c.getIncludedInternationalTalk() + "");
                tvPrice.setText("$ " + c.getPrice());
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
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
}
