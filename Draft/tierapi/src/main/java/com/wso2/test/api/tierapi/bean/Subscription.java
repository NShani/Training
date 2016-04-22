package com.wso2.test.api.tierapi.bean;

import java.sql.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Subscription {
    
    private int planId;
    private int appOwnerId;
    private Date startDate;
    private boolean status;
    public int getPlanId() {
        return planId;
    }
    public void setPlanId(int planId) {
        this.planId = planId;
    }
    public int getAppOwnerId() {
        return appOwnerId;
    }
    public void setAppOwnerId(int appOwnerId) {
        this.appOwnerId = appOwnerId;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public boolean getStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
