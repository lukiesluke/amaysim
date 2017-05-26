package com.gmailatmrlukiesluke.amaysim.Parser;

import android.util.Log;

import com.gmailatmrlukiesluke.amaysim.model.IncludedModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by lwgarces on 5/26/2017.
 */

public class ParserData {

    public static ArrayList<IncludedModel> parseIncludedData(JSONObject response) {
        ArrayList<IncludedModel> includedModelArrayList = new ArrayList<>();
        if (response != null && response.length() > 0) {
            try {


                JSONArray parentArray = response.getJSONArray("included");
                for (int i = 0; i < parentArray.length(); i++) {
                    JSONObject finalObject = parentArray.getJSONObject(i);
                    String id = "";
                    String type = "";
                    String attrMSN = "";
                    int attrCredit = 0;
                    String attrCreditExpiry = "";

                    if (contains(finalObject, "id")) {
                        id = finalObject.getString("id");
                    }
                    if (contains(finalObject, "type")) {
                        type = finalObject.getString("type");
                    }

                    if (contains(finalObject, "attributes")) {
                        JSONObject attrObject = finalObject.getJSONObject("attributes");
                        attrMSN = attrObject.getString("msn");
                        attrCredit = Integer.parseInt(attrObject.getString("credit"));
                        attrCreditExpiry = attrObject.getString("credit-expiry");
                    }
                    Log.d("LWG", "attr: " + attrMSN + " " + attrCredit + " " + attrCreditExpiry);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return includedModelArrayList;
    }

    public static boolean contains(JSONObject jsonObject, String key) {
        return jsonObject != null && jsonObject.has(key) && !jsonObject.isNull(key) ? true : false;
    }
}
