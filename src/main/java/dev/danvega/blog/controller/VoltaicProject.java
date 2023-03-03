package dev.danvega.blog.controller;

import java.util.HashMap;
import java.util.Map;

public class VoltaicProject {


    //////      STAGE: { STATUS: STATUS_START_TIME}      ////////
    private Map<String, Map<String, String>> stagesWithStatuses;


    private String address;
    private String HomeownerName;

    private String ProjectRecordID;

    private String AHJ;

    private String checkMPU;

    private  String checkSolarPermit;




    public VoltaicProject(String address, String HomeownerName, String ProjectRecordId) {


        this.address = address;
        this.HomeownerName = HomeownerName;
        this.ProjectRecordID = ProjectRecordId;
        //  Project stage and status are set to here


        stagesWithStatuses = new HashMap<>();


        Map<String, String> Welcome = new HashMap<>();
        Welcome.put("date", "0");
        stagesWithStatuses.put("Welcome", Welcome);


        Map<String, String> Voltaic_Check = new HashMap<>();
        Voltaic_Check.put("date", "0");
        stagesWithStatuses.put("Voltaic Check", Voltaic_Check);


        Map<String, String> Site_survey = new HashMap<>();
        Site_survey.put("date", "0");
        stagesWithStatuses.put("Site Survey", Site_survey);


        Map<String, String> QC_Check = new HashMap<>();
        QC_Check.put("date", "0");
        stagesWithStatuses.put("QC Check", QC_Check);


        Map<String, String> NTP = new HashMap<>();
        NTP.put("date", "0");
        stagesWithStatuses.put("NTP", NTP);


        Map<String, String> Design = new HashMap<>();
        Design.put("date", "0");
        stagesWithStatuses.put("Design", Design);


        Map<String, String> FLA = new HashMap<>();
        FLA.put("date", "0");
        stagesWithStatuses.put("FLA", FLA);


        Map<String, String> SolarPermit = new HashMap<>();
        SolarPermit.put("submitted", "0");
        SolarPermit.put("date", "0");
        stagesWithStatuses.put("Solar Permit", SolarPermit);


        Map<String, String> SolarInstall = new HashMap<>();


        SolarInstall.put("date", "0");
        stagesWithStatuses.put("Solar Install", SolarInstall);


        Map<String, String> Final_Inspection = new HashMap<>();
        Final_Inspection.put("date", "0");
        stagesWithStatuses.put("Final Inspection", Final_Inspection);


        Map<String, String> PTO = new HashMap<>();
        PTO.put("date", "0");
        stagesWithStatuses.put("PTO", PTO);


        // ==== OPTIONAL PROJECT STAGES
        //  ROOF STAGES
        //  MPU STAGES
        //  HOA STAGES
        //


        Map<String, String> MPU = new HashMap<>();
        MPU.put("date", "0");
        stagesWithStatuses.put("MPU", MPU);


        Map<String, String> QUIET_COOL = new HashMap<>();
        QUIET_COOL.put("date", "0");
        stagesWithStatuses.put("Quiet Cool", QUIET_COOL);


        Map<String, String> ROOF = new HashMap<>();
        ROOF.put("date", "0");
        stagesWithStatuses.put("Roof", ROOF);


        Map<String, String> HOA_APPROVAL = new HashMap<>();
        HOA_APPROVAL.put("date", "0");
        stagesWithStatuses.put("HOA", HOA_APPROVAL);


    }


    public String getAddress() {
        return address;
    }

    public String getHomeownerName() {
        return HomeownerName;
    }


    public String getProjectRecordID() {
        return ProjectRecordID;
    }

    public Map<String, String> getStatusesForStage(String stage) {
        return stagesWithStatuses.get(stage);
    }


    public void updateStatusTime(String stage, String status, String time) {
        if (!stagesWithStatuses.containsKey(stage)) {
            stagesWithStatuses.put(stage, new HashMap<>());
        }
        stagesWithStatuses.get(stage).put(status, time);
    }


    public String getStatusTime(String stage, String status) {
        if (!stagesWithStatuses.containsKey(stage)) {
            return null;
        }
        return stagesWithStatuses.get(stage).get(status);
    }


    public String getStatusRetentionTimePerStage(String stage) {
        return stagesWithStatuses.get(stage).get("Final");
    }





    public void setHomeownerName(String HomeownerName) {
        this.HomeownerName = HomeownerName;
    }

    public String getAHJ() {
        return AHJ;
    }
    public void setAHJ(String AHJ) {
        this.AHJ = AHJ;
    }


    public void setAddress(String Address) {
        this.address = Address;
    }
    public String getCheckMPU() {
        return checkMPU;
    }
    public void setCheckMPU(String checkMPU) {
        this.checkMPU = checkMPU;
    }

    public void setProjectRecordID(String projectRecordID) {
        this.ProjectRecordID = projectRecordID;
    }

    public void SetSolarPermit(String checkSolarPermit) {
        this.checkSolarPermit = checkSolarPermit;
    }

    public String  getCheckSolarPermit() {
        return checkSolarPermit;
    }










}
