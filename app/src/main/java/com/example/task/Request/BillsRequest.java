package com.example.task.Request;

import com.google.gson.annotations.SerializedName;

public class BillsRequest {

//    String value;
    @SerializedName("Value")
    Bill bill;

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public static class Bill{

        public Bill(String p_DLVRY_NO, String p_LANG_NO, String p_BILL_SRL, String p_PRCSSD_FLG) {
            P_DLVRY_NO = p_DLVRY_NO;
            P_LANG_NO = p_LANG_NO;
            P_BILL_SRL = p_BILL_SRL;
            P_PRCSSD_FLG = p_PRCSSD_FLG;
        }

        String P_DLVRY_NO;
        String P_LANG_NO;
        String P_BILL_SRL;
        String P_PRCSSD_FLG;

        public String getP_DLVRY_NO() {
            return P_DLVRY_NO;
        }

        public void setP_DLVRY_NO(String p_DLVRY_NO) {
            P_DLVRY_NO = p_DLVRY_NO;
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

        public String getP_PRCSSD_FLG() {
            return P_PRCSSD_FLG;
        }

        public void setP_PRCSSD_FLG(String p_PRCSSD_FLG) {
            P_PRCSSD_FLG = p_PRCSSD_FLG;
        }
    }
}
