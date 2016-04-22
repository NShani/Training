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

import com.wso2.test.api.tierapi.bean.ContainerSpec;
import com.wso2.test.api.tierapi.bean.Plan;
import com.wso2.test.api.tierapi.dao.impl.PlanDaoImpl;
import com.wso2.test.api.tierapi.delegate.DAOdelegate;

@Path("/plans")
public class PlanService {
    
    private PlanDaoImpl planInstance= (PlanDaoImpl) DAOdelegate.getPlanInstance();
    
    /*
     * Get all Plans
     * @return                  Return all plans
     */
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Plan> getPlans() throws SQLException {
        
        return planInstance.getAllPlans();
    }

    /*
     * Get Plan using Plan ID
     * @param planId            Plan ID of the plan
     * @return                  Return the plan refer to the Plan ID
     */
    @GET
    @Path("/{planId}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Plan getPlan(@PathParam("planId") int planId) throws SQLException {
        
        return planInstance.getPlanByPlanId(planId);
    }
    
    @GET
    @Path("/allowedSpecs/{planId}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<ContainerSpec> getAllowedConSpecs(@PathParam("planId") int planId) throws SQLException {
        
        return planInstance.getAllowedConSpecs(planId);
    }
}
