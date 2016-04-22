package com.wso2.test.api.tierapi.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.xml.bind.annotation.XmlRootElement;

import com.wso2.test.api.tierapi.bean.ContainerSpec;
import com.wso2.test.api.tierapi.bean.Plan;
import com.wso2.test.api.tierapi.dao.PlanDao;
import com.wso2.test.api.tierapi.util.DBConfiguration;

@XmlRootElement
public class PlanDaoImpl implements PlanDao{
    
    public static void main( String[] args ) throws SQLException
    {
        PlanDaoImpl plan=new PlanDaoImpl();
        
        //Plan plan2=new Plan("18",2,3,4);
        ContainerSpec plan3=plan.getAllowedConSpecs(1).get(0);
       // Plan plan3=plan.getPlanByPlanId(1);
        System.out.println("ID"+plan3.getId()+"\n Name :"+plan3.getConSpecName());//()+"\nMAX ins:"+plan3.getMaxInstances()+"\nMax LC"+plan3.getMaxLC()+"\nTeam :"+plan3.getTeam());
        
    }
    
    @Override
    public List<Plan> getAllPlans() throws SQLException{
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        
        List<Plan> plans = new ArrayList<Plan>();
        String sql="select * from Plan";
        
        try {
        DBConfiguration dbCon=new DBConfiguration();
        dbConnection= dbCon.getConnection();
        
        
        preparedStatement= dbConnection.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            Plan plan = new Plan();
            plan.setId(rs.getInt("PLAN_ID"));
            plan.setPlanName(rs.getString("PLAN_NAME"));
            plan.setTeam(rs.getInt("TEAM"));
            plan.setMaxInstances(rs.getInt("MAX_INSTANCES"));
            plan.setMaxLC(rs.getInt("MAX_LC"));
            
            plans.add(plan);
        }
        } catch (SQLException e) {
            throw e;
        } finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        return plans;
    }

    @Override
    public Plan getPlanByPlanId(int planId) throws SQLException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        Logger log = Logger.getLogger(PlanDaoImpl.class.getName()); 
        Plan plan = new Plan();
        String sql="select * from Plan WHERE PLAN_ID ="+planId;
   
        try {
           
        DBConfiguration dbCon=new DBConfiguration();
        
        dbConnection= dbCon.getConnection();
        System.out.println("OOOOOOOOOOOOOOOOOOOOO");
        preparedStatement= dbConnection.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();
        
        while (rs.next()) {
            
            plan.setId(rs.getInt("PLAN_ID"));
            plan.setPlanName(rs.getString("PLAN_NAME"));
            plan.setTeam(rs.getInt("TEAM"));
            plan.setMaxInstances(rs.getInt("MAX_INSTANCES"));
            plan.setMaxLC(rs.getInt("MAX_LC"));
            
        }
        rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
            
        }
        return plan;
    }

    @Override
    public Plan definePlan(Plan plan) throws SQLException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        
        String sql="INSERT INTO Plan (PLAN_NAME, TEAM, MAX_INSTANCES, MAX_LC) VALUES (?, ?, ?, ?)";

        try {
            DBConfiguration dbCon=new DBConfiguration();
            dbConnection= dbCon.getConnection();
            preparedStatement = dbConnection.prepareStatement(sql);
            
            
            preparedStatement.setString(1, plan.getPlanName());
            preparedStatement.setInt(2, plan.getTeam());
            preparedStatement.setInt(3, plan.getMaxInstances());
            preparedStatement.setInt(4, plan.getMaxLC());
            
            preparedStatement.executeUpdate();
            preparedStatement.close();
            
            String sql2="select * from Plan WHERE PLAN_NAME= ?";
            preparedStatement= dbConnection.prepareStatement(sql2);
            preparedStatement.setString(1, plan.getPlanName());
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {          
                plan.setId(rs.getInt("PLAN_ID"));
                plan.setPlanName(rs.getString("PLAN_NAME"));
                plan.setTeam(rs.getInt("TEAM"));
                plan.setMaxInstances(rs.getInt("MAX_INSTANCES"));
                plan.setMaxLC(rs.getInt("MAX_LC"));
            } 
            
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        } finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        return plan;
    }

    @Override
    public boolean deletePlanById(int planId) throws SQLException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        
        boolean isDeleted;
        
        String sql="DELETE FROM Plan WHERE PLAN_ID="+planId;
    
        try {
        DBConfiguration dbCon=new DBConfiguration();
        dbConnection= dbCon.getConnection();
        
        preparedStatement= dbConnection.prepareStatement(sql);
        
        isDeleted = preparedStatement.executeUpdate() == 1 ? true : false;
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        } finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        return isDeleted;
    }


    @Override
    public Plan updatePlanById(int planId, Plan plan) throws SQLException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String sql="Update Plan SET PLAN_NAME=?, TEAM= ?, MAX_INSTANCES=?, MAX_LC=? WHERE PLAN_ID = ?";
        try {
            DBConfiguration dbCon=new DBConfiguration();
            dbConnection= dbCon.getConnection();
            preparedStatement = dbConnection.prepareStatement(sql);

            preparedStatement.setString(1, plan.getPlanName());
            preparedStatement.setInt(2, plan.getTeam());
            preparedStatement.setInt(3, plan.getMaxInstances());
            preparedStatement.setInt(4, plan.getMaxLC());
            preparedStatement.setInt(5, planId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            
            String sql2="select * from Plan WHERE PLAN_ID= ?";
            preparedStatement= dbConnection.prepareStatement(sql2);
            preparedStatement.setInt(1, planId);
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {          
                plan.setId(rs.getInt("PLAN_ID"));
                plan.setPlanName(rs.getString("PLAN_NAME"));
                plan.setTeam(rs.getInt("TEAM"));
                plan.setMaxInstances(rs.getInt("MAX_INSTANCES"));
                plan.setMaxLC(rs.getInt("MAX_LC"));
            } 

        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        } finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        return plan;
        
    }

    @Override
    public List<ContainerSpec> getAllowedConSpecs(int planId) throws SQLException {
        Connection dbConnection = null;
//        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement = null;
        
        List<ContainerSpec> allowedContainerSpecs = new ArrayList<ContainerSpec>();
//        List<Integer> containerIDs =new ArrayList<>();
//        
//        String sqlRestictedContainerSpecs="SELECT * FROM "
//                + "Plan JOIN RestrictedPlanContainerSpecs ON Plan.PLAN_ID=RestrictedPlanContainerSpecs.PLAN_ID"
//                + " WHERE RestrictedPlanContainerSpecs.PLAN_ID =1";
        
        
        String sqlAllContainerSpecs="select * from ContainerSpecs WHERE CON_SPEC_ID NOT IN "
                + "(SELECT CON_SPEC_ID FROM Plan JOIN RestrictedPlanContainerSpecs ON"
                + " Plan.PLAN_ID = RestrictedPlanContainerSpecs.PLAN_ID WHERE"
                + " RestrictedPlanContainerSpecs.PLAN_ID ="+planId+")";
        
//        String sql="select * from Plan WHERE PLAN_ID ="+planId;
        //String sql
//        ContainerSpecDaoImpl conSpecDAOImpl=new ContainerSpecDaoImpl();
        try {
        DBConfiguration dbCon=new DBConfiguration();
        dbConnection= dbCon.getConnection();
        
        preparedStatement= dbConnection.prepareStatement(sqlAllContainerSpecs);
        
        ResultSet rs = preparedStatement.executeQuery();
        

        while (rs.next()) {
            ContainerSpec containerSpecification = new ContainerSpec();
            containerSpecification.setId(rs.getInt("CON_SPEC_ID"));
            containerSpecification.setConSpecName(rs.getString("CON_SPEC_NAME"));
            containerSpecification.setCpu(rs.getInt("CPU"));
            containerSpecification.setMemory(rs.getInt("MEMORY"));
            containerSpecification.setStorage(rs.getInt("STORAGE"));
            containerSpecification.setCostPerHour(rs.getInt("COST_PER_HOUR"));
            System.out.println(containerSpecification.getConSpecName()+">>>>>>>>>>>>>");
           allowedContainerSpecs.add(containerSpecification);
        }
        } catch (SQLException ex) {
            throw ex;
        } finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        return allowedContainerSpecs;

    }
}
