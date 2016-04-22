package com.wso2.test.api.tierapi.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.wso2.test.api.tierapi.bean.ContainerSpec;
import com.wso2.test.api.tierapi.bean.Subscription;
import com.wso2.test.api.tierapi.dao.SubscriptionDao;
import com.wso2.test.api.tierapi.util.DBConfiguration;

@XmlRootElement
public class SubscriptionDaoImpl implements SubscriptionDao {

    public static void main( String[] args ) throws SQLException
    {
        SubscriptionDaoImpl subscription=new SubscriptionDaoImpl();
        Subscription sub=new Subscription();
        sub.setPlanId(2);
        sub.setStatus(false);
        //Plan plan2=new Plan("18",2,3,4);
//        subscription.upgradePlan(0, sub);
       // Plan plan3=plan.getPlanByPlanId(1);
//        System.out.println("ID"+plan3.getId()+"\n Name :"+plan3.getConSpecName());//()+"\nMAX ins:"+plan3.getMaxInstances()+"\nMax LC"+plan3.getMaxLC()+"\nTeam :"+plan3.getTeam());
        
    }
    
    @Override
    public boolean planSubscription(Subscription subscription) throws SQLException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        
        String sql="INSERT INTO Subscription (PLAN_ID, APP_OWNER_ID, START_DATE, STATUS) VALUES (?, ?, ?, ?)";

        try {
            DBConfiguration dbCon=new DBConfiguration();
            dbConnection= dbCon.getConnection();
            preparedStatement = dbConnection.prepareStatement(sql);
            
            
            preparedStatement.setInt(1, subscription.getPlanId());
            preparedStatement.setInt(2, subscription.getAppOwnerId());
            preparedStatement.setDate(3, subscription.getStartDate());
            preparedStatement.setBoolean(4, subscription.getStatus());
            
            preparedStatement.executeUpdate();
            preparedStatement.close();
            
            
            return true;
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
        
    }

    @Override
    public boolean upgradePlan(int applicationId,Subscription subscription) throws SQLException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String sql="Update Subscription SET PLAN_ID=?, START_DATE=?, STATUS=? WHERE APP_OWNER_ID = ?";
        try {
            DBConfiguration dbCon=new DBConfiguration();
            dbConnection= dbCon.getConnection();
            preparedStatement = dbConnection.prepareStatement(sql);

            preparedStatement.setInt(1, subscription.getPlanId());
            preparedStatement.setDate(2, subscription.getStartDate());
            preparedStatement.setBoolean(3, subscription.getStatus());
            preparedStatement.setInt(4, applicationId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            
            return true;

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
    }

    @Override
    public List<Subscription> getAllSubscription() throws SQLException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        
        List<Subscription> subscriptions = new ArrayList<Subscription>();
        String sql="select * from Subscription";
        
        try {
        DBConfiguration dbCon=new DBConfiguration();
        dbConnection= dbCon.getConnection();
        
        
        preparedStatement= dbConnection.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            Subscription subscription = new Subscription();
            subscription.setPlanId(rs.getInt("PLAN_ID"));
            subscription.setAppOwnerId(rs.getInt("APP_OWNER_ID"));
            subscription.setStartDate(rs.getDate("START_DATE"));
            subscription.setStatus(rs.getBoolean("STATUS"));
            
            subscriptions.add(subscription);
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
        return subscriptions;
    }

   

}
