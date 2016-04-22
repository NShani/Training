package com.wso2.test.api.tierapi.bean;

import javax.xml.bind.annotation.XmlRootElement;

/*
 * class to implement the container spec details
 */
@XmlRootElement
public class ContainerSpec {
    
    private int id;
    private String conSpecName;
    private int cpu;
    private int memory;
    private int storage;
    private int costPerHour;
    
    public ContainerSpec(){
        
    }
   
    public ContainerSpec(String conSpecName, int cpu, int memory,int storage, int costPerHour) {
        this.conSpecName= conSpecName;
        this.cpu=cpu;
        this.storage=storage;
        this.memory=memory;
        this.costPerHour=costPerHour;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setConSpecName(String conSpecName) {
        this.conSpecName=conSpecName;
    }
    
    public String getConSpecName() {
        return conSpecName;
    }
    
    public void setCpu(int cpu) {
        this.cpu=cpu;
    }
    
    public int getCpu() {
        return cpu;
    }
    
    public void setMemory(int memory) {
        this.memory=memory;
    }
    
    public int getMemory() {
        return memory;
    }
    
    public void setStorage(int storage) {
        this.storage=storage;
    }
    
    public int getStorage() {
        return storage;
    }
    
    public void setCostPerHour(int costPerHour) {
        this.costPerHour=costPerHour;
    }
    
    public int getCostPerHour() {
        return costPerHour;
    }
}
