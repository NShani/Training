package com.wso2.test.api.tierapi.delegate;

import com.wso2.test.api.tierapi.bean.Subscription;
import com.wso2.test.api.tierapi.dao.ContainerSpecsDao;
import com.wso2.test.api.tierapi.dao.PlanDao;
import com.wso2.test.api.tierapi.dao.SubscriptionDao;
import com.wso2.test.api.tierapi.dao.impl.ContainerSpecDaoImpl;
import com.wso2.test.api.tierapi.dao.impl.PlanDaoImpl;
import com.wso2.test.api.tierapi.dao.impl.SubscriptionDaoImpl;

public class DAOdelegate {

    private static PlanDao planInstance = new PlanDaoImpl();
    private static ContainerSpecsDao containerSpecInstance = new ContainerSpecDaoImpl();
    private static SubscriptionDao subscriptionInstance = new SubscriptionDaoImpl();
        
    /**
     * Get PlanDAO object
     */
    public static PlanDao getPlanInstance() {
        return planInstance;
    }
    
    /**
     * Get PlanContainerSpec object
     */
    public static ContainerSpecsDao getContainerSpecInstance() {
        return containerSpecInstance;
    }

    /**
     * Get SubscriptionDao object
     * 
     */
    public static SubscriptionDao getSubscriptionInstance() {
        return subscriptionInstance;
    }

}
