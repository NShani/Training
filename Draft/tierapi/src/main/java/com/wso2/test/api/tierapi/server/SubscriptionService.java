package com.wso2.test.api.tierapi.server;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.wso2.test.api.tierapi.bean.Subscription;
import com.wso2.test.api.tierapi.dao.impl.SubscriptionDaoImpl;
import com.wso2.test.api.tierapi.delegate.DAOdelegate;

@Path("/subscriptions")
public class SubscriptionService {
private SubscriptionDaoImpl subscriptionInstance= (SubscriptionDaoImpl) DAOdelegate.getSubscriptionInstance();
    
    /*
     * Get all Plans
     * @return                  Return all plans
     */
    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Boolean newSubscription(Subscription subscription) throws SQLException {
    
    return subscriptionInstance.planSubscription(subscription);
    }
    
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Subscription> getSubscriptions() throws SQLException {
        
        return subscriptionInstance.getAllSubscription();
    }
    
    @POST
    @Path("/upgrade/{applicationId}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public boolean updateSubscription(@PathParam("applicationId") int applicationId,Subscription subscription) throws SQLException {
        
        return subscriptionInstance.upgradePlan(applicationId, subscription);
    }

}
