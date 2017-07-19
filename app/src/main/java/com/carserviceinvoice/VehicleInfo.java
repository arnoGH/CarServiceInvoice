package com.carserviceinvoice;

import java.io.Serializable;

public class VehicleInfo implements Serializable {

    private String custName, carMake, carModel, year, mileage;
    private static final long serialVersionUID = 1L;

    public VehicleInfo(){

    }

    public VehicleInfo(String custName, String carMake, String carModel, String year, String mileage) {
        this.custName = custName;
        this.carMake = carMake;
        this.carModel = carModel;
        this.year = year;
        this.mileage = mileage;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCarMake() {
        return carMake;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }
}
