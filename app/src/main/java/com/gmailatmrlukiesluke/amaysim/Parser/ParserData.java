package com.gmailatmrlukiesluke.amaysim.Parser;

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
                String msn = "NA";
                int credit = 0;
                String creditExpiry = "NA";

                JSONArray parentArray = response.getJSONArray("included");
                for (int i = 0; i < parentArray.length(); i++) {
                    String id = "";
                    String type = "";

                    JSONObject finalObject = parentArray.getJSONObject(i);

                    if (contains(finalObject, "id")) {
                        id = finalObject.getString("id");
                    }
                    if (contains(finalObject, "type")) {
                        type = finalObject.getString("type");
                    }

                    for (int j = 0; j < finalObject.getJSONArray("attributes").length(); j++) {

                        JSONObject object = finalObject.getJSONArray("attributes").getJSONObject(j);
                        msn = object.getString("msn");
                        credit = (int) object.getDouble("credit");
                        creditExpiry = object.getString("credit-expiry");

                    }

                    IncludedModel includedModel = new IncludedModel();
                    includedModel.setId(id);
                    includedModel.setType(type);
                    includedModel.setMsn(msn);
                    includedModel.setCredit(credit);
                    includedModel.setCreditExpiry(creditExpiry);
                    includedModelArrayList.add(includedModel);
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
