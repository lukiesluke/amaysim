package com.gmailatmrlukiesluke.amaysim.model;

import java.util.List;

/**
 * Created by user on 5/26/2017.
 */

public class IncludedSubscriptionsModel {
    private String id;
    private String type;
    private List<Attributes> attributesList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Attributes> getAttributesList() {
        return attributesList;
    }

    public void setAttributesList(List<Attributes> attributesList) {
        this.attributesList = attributesList;
    }

    public static class Attributes {
        private int includedDataBalance;
        private int includedCreditBalance;
        private int includedRolloverCreditBalance;
        private int includedRolloverDataBalance;
        private int includedInternationalTalkBalance;
        private String expiryDate;

        public int getIncludedDataBalance() {
            return includedDataBalance;
        }

        public void setIncludedDataBalance(int includedDataBalance) {
            this.includedDataBalance = includedDataBalance;
        }

        public int getIncludedCreditBalance() {
            return includedCreditBalance;
        }

        public void setIncludedCreditBalance(int includedCreditBalance) {
            this.includedCreditBalance = includedCreditBalance;
        }

        public int getIncludedRolloverCreditBalance() {
            return includedRolloverCreditBalance;
        }

        public void setIncludedRolloverCreditBalance(int includedRolloverCreditBalance) {
            this.includedRolloverCreditBalance = includedRolloverCreditBalance;
        }

        public int getIncludedRolloverDataBalance() {
            return includedRolloverDataBalance;
        }

        public void setIncludedRolloverDataBalance(int includedRolloverDataBalance) {
            this.includedRolloverDataBalance = includedRolloverDataBalance;
        }

        public int getIncludedInternationalTalkBalance() {
            return includedInternationalTalkBalance;
        }

        public void setIncludedInternationalTalkBalance(int includedInternationalTalkBalance) {
            this.includedInternationalTalkBalance = includedInternationalTalkBalance;
        }

        public String getExpiryDate() {
            return expiryDate;
        }

        public void setExpiryDate(String expiryDate) {
            this.expiryDate = expiryDate;
        }
    }

}
