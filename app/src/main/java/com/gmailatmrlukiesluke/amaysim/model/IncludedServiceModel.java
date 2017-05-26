package com.gmailatmrlukiesluke.amaysim.model;

import java.util.List;

/**
 * Created by lwgarces on 5/26/2017.
 */

public class IncludedServiceModel {
    private String id;
    private String type;
    private List<Attributes> attributesList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public List<Attributes> getAttributesList() {
        return attributesList;
    }

    public void setAttributesList(List<Attributes> attributesList) {
        this.attributesList = attributesList;
    }

    public static class Attributes {
        private String msn;
        private int credit;
        private String credit_expiry;

        public String getMsn() {
            return msn;
        }

        public void setMsn(String msn) {
            this.msn = msn;
        }

        public int getCredit() {
            return credit;
        }

        public void setCredit(int credit) {
            this.credit = credit;
        }

        public String getCredit_expiry() {
            return credit_expiry;
        }

        public void setCredit_expiry(String credit_expiry) {
            this.credit_expiry = credit_expiry;
        }
    }
}
