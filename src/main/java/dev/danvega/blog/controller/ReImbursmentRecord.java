package dev.danvega.blog.controller;

public class ReImbursmentRecord {
    private String address;
    private String distanceTraveled;
    private Double reimBurserment;
    private  String HomeownerName;
    private String InstallDate;


    public ReImbursmentRecord(String address, String DistanceTraveled, Double ReimBurserment, String HomeownerName, String InstallDate  ) {
        this.address = address;
        this.distanceTraveled = DistanceTraveled;
        this.reimBurserment = ReimBurserment;
        this.HomeownerName = HomeownerName;
        this.InstallDate = InstallDate;

    }

    public String getRecordAddress() {
        return address;
    }

    public String getDistanceTraveled() {
        return distanceTraveled;
    }


    public Double getReimBurserment() {
        return reimBurserment;
    }

    public String getHomeownerName() {
        return HomeownerName;
    }
    public String getInstallDate() {
        String formattedDate = VoltaicDataformatter.convertDateFormat(InstallDate);
        return InstallDate;
    }



}
