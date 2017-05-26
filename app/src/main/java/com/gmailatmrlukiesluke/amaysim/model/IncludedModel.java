package com.gmailatmrlukiesluke.amaysim.model;

/**
 * Created by lwgarces on 5/26/2017.
 */

public class IncludedModel {
    String id;
    String type;
    String msn;
    int credit;
    String creditExpiry;

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

    public String getCreditExpiry() {
        return creditExpiry;
    }

    public void setCreditExpiry(String creditExpiry) {
        this.creditExpiry = creditExpiry;
    }
}
