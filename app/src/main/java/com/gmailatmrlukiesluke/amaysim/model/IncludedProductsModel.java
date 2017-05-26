package com.gmailatmrlukiesluke.amaysim.model;

import java.util.List;

/**
 * Created by user on 5/26/2017.
 */

public class IncludedProductsModel {
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
        private String name;
        private int includedData;
        private int includedCredit;
        private int includedInternationalTalk;
        private double price;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getIncludedData() {
            return includedData;
        }

        public void setIncludedData(int includedData) {
            this.includedData = includedData;
        }

        public int getIncludedCredit() {
            return includedCredit;
        }

        public void setIncludedCredit(int includedCredit) {
            this.includedCredit = includedCredit;
        }

        public int getIncludedInternationalTalk() {
            return includedInternationalTalk;
        }

        public void setIncludedInternationalTalk(int includedInternationalTalk) {
            this.includedInternationalTalk = includedInternationalTalk;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }
}
