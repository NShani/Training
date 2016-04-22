package com.wso2.test.api.tierapi.dao;

import java.sql.SQLException;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.wso2.test.api.tierapi.bean.Plan;
import com.wso2.test.api.tierapi.bean.Subscription;

@XmlRootElement
public interface SubscriptionDao {
    
    /*
     * Subscribe to a Plan
     */
    public boolean planSubscription(Subscription subscription) throws SQLException;
    
    /*
     * Subscribe to a Plan
     */
     
    public boolean upgradePlan(int applicationId,Subscription subscription) throws SQLException;

    /*
     * Subscribe to a Plan
     */
     
    public List<Subscription> getAllSubscription() throws SQLException;

    
    
}
