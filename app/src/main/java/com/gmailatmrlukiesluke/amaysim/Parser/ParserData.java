package com.gmailatmrlukiesluke.amaysim.Parser;

import android.util.Log;

import com.gmailatmrlukiesluke.amaysim.model.IncludedProductsModel;
import com.gmailatmrlukiesluke.amaysim.model.IncludedServiceModel;
import com.gmailatmrlukiesluke.amaysim.model.IncludedSubscriptionsModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lwgarces on 5/26/2017.
 */

public class ParserData {

    public static ArrayList<IncludedServiceModel> parseIncludedDataServices(JSONObject response) {
        ArrayList<IncludedServiceModel> includedServiceModelArrayList = new ArrayList<>();
        if (response != null && response.length() > 0) {
            try {

                JSONArray parentArray = response.getJSONArray("included");
                for (int i = 0; i < parentArray.length(); i++) {
                    JSONObject finalObject = parentArray.getJSONObject(i);
                    String id = "";
                    String type = "";

                    if (contains(finalObject, "type")) {
                        type = finalObject.getString("type");
                    }
                    if (type.equals("services")) {

                        if (contains(finalObject, "id")) {
                            id = finalObject.getString("id");
                        }
                        if (contains(finalObject, "type")) {
                            type = finalObject.getString("type");
                        }

                        List<IncludedServiceModel.Attributes> attributesList = new ArrayList<>();
                        if (contains(finalObject, "attributes")) {
                            String attrMSN = "";
                            int attrCredit = 0;
                            String attrCreditExpiry = "";

                            JSONObject attrObject = finalObject.getJSONObject("attributes");
                            if (contains(attrObject, "msn")) {
                                attrMSN = attrObject.getString("msn");
                            }
                            if (contains(attrObject, "credit")) {
                                attrCredit = Integer.parseInt(attrObject.getString("credit"));
                            }
                            if (contains(attrObject, "credit-expiry")) {
                                attrCreditExpiry = attrObject.getString("credit-expiry");
                            }

                            IncludedServiceModel.Attributes attributes = new IncludedServiceModel.Attributes();
                            attributes.setMsn(attrMSN);
                            attributes.setCredit(attrCredit);
                            attributes.setCredit_expiry(attrCreditExpiry);
                            attributesList.add(attributes);

                            Log.d("LWG", "attr: " + attrMSN + " " + attrCredit + " " + attrCreditExpiry);
                        }

                        IncludedServiceModel includedServiceModel = new IncludedServiceModel();
                        includedServiceModel.setId(id);
                        includedServiceModel.setType(type);
                        includedServiceModel.setAttributesList(attributesList);
                        includedServiceModelArrayList.add(includedServiceModel);
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return includedServiceModelArrayList;
    }

    public static ArrayList<IncludedSubscriptionsModel> parseIncludedDataSubscription(JSONObject response) {
        ArrayList<IncludedSubscriptionsModel> includedSubscriptionsModelArrayList = new ArrayList<>();
        if (response != null && response.length() > 0) {

            try {
                JSONArray parentArray = response.getJSONArray("included");
                for (int i = 0; i < parentArray.length(); i++) {
                    JSONObject finalObject = parentArray.getJSONObject(i);
                    String id = "";
                    String type = "";

                    if (contains(finalObject, "type")) {
                        type = finalObject.getString("type");
                    }

                    if (type.equals("subscriptions")) {

                        if (contains(finalObject, "id")) {
                            id = finalObject.getString("id");
                        }
                        if (contains(finalObject, "type")) {
                            type = finalObject.getString("type");
                        }

                        List<IncludedSubscriptionsModel.Attributes> attributesList = new ArrayList<>();
                        if (contains(finalObject, "attributes")) {
                            int includedDataBalance = 0;
                            int includedCreditBalance = 0;
                            int includedRolloverCreditBalance = 0;
                            int includedRolloverDataBalance = 0;
                            int includedInternationalTalkBalance = 0;
                            String expiryDate = "";

                            JSONObject attrObject = finalObject.getJSONObject("attributes");
                            if (contains(attrObject, "included-data-balance")) {
                                includedDataBalance = Integer.parseInt(attrObject.getString("included-data-balance"));
                            }
                            if (contains(attrObject, "included-credit-balance")) {
                                includedCreditBalance = Integer.parseInt(attrObject.getString("included-credit-balance"));
                            }
                            if (contains(attrObject, "included-rollover-credit-balance")) {
                                includedRolloverCreditBalance = Integer.parseInt(attrObject.getString("included-rollover-credit-balance"));
                            }
                            if (contains(attrObject, "included-rollover-data-balance")) {
                                includedRolloverDataBalance = Integer.parseInt(attrObject.getString("included-rollover-data-balance"));
                            }
                            if (contains(attrObject, "included-international-talk-balance")) {
                                includedInternationalTalkBalance = Integer.parseInt(attrObject.getString("included-international-talk-balance"));
                            }
                            if (contains(attrObject, "expiry-date")) {
                                expiryDate = attrObject.getString("expiry-date");
                            }

                            IncludedSubscriptionsModel.Attributes attributes = new IncludedSubscriptionsModel.Attributes();
                            attributes.setIncludedDataBalance(includedDataBalance);
                            attributes.setIncludedCreditBalance(includedCreditBalance);
                            attributes.setIncludedRolloverCreditBalance(includedRolloverCreditBalance);
                            attributes.setIncludedRolloverDataBalance(includedRolloverDataBalance);
                            attributes.setIncludedInternationalTalkBalance(includedInternationalTalkBalance);
                            attributes.setExpiryDate(expiryDate);
                            attributesList.add(attributes);
                        }

                        IncludedSubscriptionsModel includedSubscriptionsModel = new IncludedSubscriptionsModel();
                        includedSubscriptionsModel.setId(id);
                        includedSubscriptionsModel.setType(type);
                        includedSubscriptionsModel.setAttributesList(attributesList);
                        includedSubscriptionsModelArrayList.add(includedSubscriptionsModel);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        return includedSubscriptionsModelArrayList;
    }

    public static ArrayList<IncludedProductsModel> parseIncludedDataProducts(JSONObject response) {
        ArrayList<IncludedProductsModel> includedProductsModelArrayList = new ArrayList<>();
        if (response != null && response.length() > 0) {
            try {
                JSONArray parentArray = response.getJSONArray("included");
                for (int i = 0; i < parentArray.length(); i++) {
                    JSONObject finalObject = parentArray.getJSONObject(i);
                    String id = "";
                    String type = "";

                    if (contains(finalObject, "type")) {
                        type = finalObject.getString("type");
                    }

                    if (type.equals("products")) {

                        if (contains(finalObject, "id")) {
                            id = finalObject.getString("id");
                        }
                        if (contains(finalObject, "type")) {
                            type = finalObject.getString("type");
                        }

                        List<IncludedProductsModel.Attributes> attributesList = new ArrayList<>();
                        if (contains(finalObject, "attributes")) {
                            String name = "";
                            int includedData = 0;
                            int includedCredit = 0;
                            int includedInternationalTalk = 0;
                            double price = 0;

                            JSONObject attrObject = finalObject.getJSONObject("attributes");
                            if (contains(attrObject, "name")) {
                                name = attrObject.getString("name");
                            }
                            if (contains(attrObject, "included-data")) {
                                includedData = Integer.parseInt(attrObject.getString("included-data"));
                            }
                            if (contains(attrObject, "included-credit")) {
                                includedCredit = Integer.parseInt(attrObject.getString("included-credit"));
                            }
                            if (contains(attrObject, "included-international-talk")) {
                                includedInternationalTalk = Integer.parseInt(attrObject.getString("included-international-talk"));
                            }
                            if (contains(attrObject, "price")) {
                                price = Integer.parseInt(attrObject.getString("price"));
                            }

                            IncludedProductsModel.Attributes attributes = new IncludedProductsModel.Attributes();
                            attributes.setName(name);
                            attributes.setIncludedData(includedData);
                            attributes.setIncludedCredit(includedCredit);
                            attributes.setIncludedInternationalTalk(includedInternationalTalk);
                            attributes.setPrice(price);
                            attributesList.add(attributes);

                        }

                        IncludedProductsModel includedProductsModel = new IncludedProductsModel();
                        includedProductsModel.setId(id);
                        includedProductsModel.setType(type);
                        includedProductsModel.setAttributesList(attributesList);
                        includedProductsModelArrayList.add(includedProductsModel);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return includedProductsModelArrayList;
    }

    public static boolean contains(JSONObject jsonObject, String key) {
        return jsonObject != null && jsonObject.has(key) && !jsonObject.isNull(key) ? true : false;
    }
}
