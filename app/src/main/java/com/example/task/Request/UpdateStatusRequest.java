package com.example.task.Request;

import com.google.gson.annotations.SerializedName;

public class UpdateStatusRequest {


    @SerializedName("Value")
    StatusValues statusValues;


    public StatusValues getStatusValues() {
        return statusValues;
    }

    public void setStatusValues(StatusValues statusValues) {
        this.statusValues = statusValues;
    }

    public static class StatusValues{
        String P_LANG_NO;
        String P_BILL_SRL;
        String P_DLVRY_STATUS_FLG;
        String P_DLVRY_RTRN_RSN;

        public StatusValues(String p_LANG_NO, String p_BILL_SRL, String p_DLVRY_STATUS_FLG, String p_DLVRY_RTRN_RSN) {
            P_LANG_NO = p_LANG_NO;
            P_BILL_SRL = p_BILL_SRL;
            P_DLVRY_STATUS_FLG = p_DLVRY_STATUS_FLG;
            P_DLVRY_RTRN_RSN = p_DLVRY_RTRN_RSN;
        }


        public String getP_LANG_NO() {
            return P_LANG_NO;
        }

        public void setP_LANG_NO(String p_LANG_NO) {
            P_LANG_NO = p_LANG_NO;
        }

        public String getP_BILL_SRL() {
            return P_BILL_SRL;
        }

        public void setP_BILL_SRL(String p_BILL_SRL) {
            P_BILL_SRL = p_BILL_SRL;
        }

        public String getP_DLVRY_STATUS_FLG() {
            return P_DLVRY_STATUS_FLG;
        }

        public void setP_DLVRY_STATUS_FLG(String p_DLVRY_STATUS_FLG) {
            P_DLVRY_STATUS_FLG = p_DLVRY_STATUS_FLG;
        }

        public String getP_DLVRY_RTRN_RSN() {
            return P_DLVRY_RTRN_RSN;
        }

        public void setP_DLVRY_RTRN_RSN(String p_DLVRY_RTRN_RSN) {
            P_DLVRY_RTRN_RSN = p_DLVRY_RTRN_RSN;
        }
    }
}
