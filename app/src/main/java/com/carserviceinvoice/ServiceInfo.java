package com.carserviceinvoice;

import java.io.Serializable;

public class ServiceInfo implements Serializable {

    private String serv, serv1, serv2, serv3, serv4;
    private double price, price1, price2, price3, price4, tax;

    private static final long serialVersionUID = 1L;

    public ServiceInfo(){
    }

    public ServiceInfo(String serv, String serv1, String serv2, String serv3,
                       String serv4, double price, double price1, double price2,
                       double price3, double price4, double tax) {
        this.serv = serv;
        this.serv1 = serv1;
        this.serv2 = serv2;
        this.serv3 = serv3;
        this.serv4 = serv4;
        this.price = price;
        this.price1 = price1;
        this.price2 = price2;
        this.price3 = price3;
        this.price4 = price4;
        this.tax = tax;
    }

    public void setServ(String serv) {
        this.serv = serv;
    }

    public void setServ1(String serv1) {
        this.serv1 = serv1;
    }

    public void setServ2(String serv2) {
        this.serv2 = serv2;
    }

    public void setServ3(String serv3) {
        this.serv3 = serv3;
    }

    public void setServ4(String serv4) {
        this.serv4 = serv4;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPrice1(double price1) {
        this.price1 = price1;
    }

    public void setPrice2(double price2) {
        this.price2 = price2;
    }

    public void setPrice3(double price3) {
        this.price3 = price3;
    }

    public void setPrice4(double price4) {
        this.price4 = price4;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public String getServ() {
        return serv;
    }

    public String getServ1() {
        return serv1;
    }

    public String getServ2() {
        return serv2;
    }

    public String getServ3() {
        return serv3;
    }

    public String getServ4() {
        return serv4;
    }

    public double getPrice() {
        return price;
    }

    public double getPrice1() {
        return price1;
    }

    public double getPrice2() {
        return price2;
    }

    public double getPrice3() {
        return price3;
    }

    public double getPrice4() {
        return price4;
    }

    public double getTax() {
        return tax;
    }

    public double getSubTotal(){
        return (price + price1 + price2 + price3 + price4);
    }

    public double getTaxDollars(){
        if (getTax() == 0.0)
            return 0.0;
        return ((getTax()/100) * getSubTotal());
    }

    public double getTotal(){
        return (getTaxDollars() + getSubTotal());
    }
}
