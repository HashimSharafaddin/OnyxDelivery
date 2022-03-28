package com.example.task.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BillData {

    @SerializedName("DeliveryBills")
    private ArrayList<BillData.BillsModel> BillsList;




    public ArrayList<BillsModel> getBillsList() {
        return BillsList;
    }

    public void setBillsList(ArrayList<BillsModel> billsList) {
        BillsList = billsList;
    }

    public class BillsModel {


        @SerializedName("BILL_AMT")
        private String bill_amount;

        @SerializedName("BILL_DATE")
        private String bill_date;

        @SerializedName("BILL_NO")
        private String bill_number;

        @SerializedName("BILL_SRL")
        private String bill_serial;

        @SerializedName("BILL_TIME")
        private String bill_time;

        @SerializedName("BILL_TYPE")
        private String bill_type;

        @SerializedName("CSTMR_ADDRSS")
        private String customer_address;

        @SerializedName("CSTMR_APRTMNT_NO")
        private String customer_APRTMNT_NO;

        @SerializedName("CSTMR_BUILD_NO")
        private String customer_building_number;

        @SerializedName("CSTMR_FLOOR_NO")
        private String customer_floor_number;

        @SerializedName("CSTMR_NM")
        private String customer_name;


        @SerializedName("DLVRY_AMT")
        private String delivery_amount;


        @SerializedName("DLVRY_STATUS_FLG")
        private String delivery_status_flag;

        @SerializedName("LATITUDE")
        private String latitude;

        @SerializedName("LONGITUDE")
        private String longitude;

        @SerializedName("MOBILE_NO")
        private String customer_mobile_number;

        @SerializedName("RGN_NM")
        private String region_name;

        @SerializedName("TAX_AMT")
        private String tax_amount;

        public String getBill_amount() {
            return bill_amount;
        }

        public void setBill_amount(String bill_amount) {
            this.bill_amount = bill_amount;
        }

        public String getBill_date() {
            return bill_date;
        }

        public void setBill_date(String bill_date) {
            this.bill_date = bill_date;
        }

        public String getBill_number() {
            return bill_number;
        }

        public void setBill_number(String bill_number) {
            this.bill_number = bill_number;
        }

        public String getBill_serial() {
            return bill_serial;
        }

        public void setBill_serial(String bill_serial) {
            this.bill_serial = bill_serial;
        }

        public String getBill_time() {
            return bill_time;
        }

        public void setBill_time(String bill_time) {
            this.bill_time = bill_time;
        }

        public String getBill_type() {
            return bill_type;
        }

        public void setBill_type(String bill_type) {
            this.bill_type = bill_type;
        }

        public String getCustomer_address() {
            return customer_address;
        }

        public void setCustomer_address(String customer_address) {
            this.customer_address = customer_address;
        }

        public String getCustomer_APRTMNT_NO() {
            return customer_APRTMNT_NO;
        }

        public void setCustomer_APRTMNT_NO(String customer_APRTMNT_NO) {
            this.customer_APRTMNT_NO = customer_APRTMNT_NO;
        }

        public String getCustomer_building_number() {
            return customer_building_number;
        }

        public void setCustomer_building_number(String customer_building_number) {
            this.customer_building_number = customer_building_number;
        }

        public String getCustomer_floor_number() {
            return customer_floor_number;
        }

        public void setCustomer_floor_number(String customer_floor_number) {
            this.customer_floor_number = customer_floor_number;
        }

        public String getCustomer_name() {
            return customer_name;
        }

        public void setCustomer_name(String customer_name) {
            this.customer_name = customer_name;
        }

        public String getDelivery_amount() {
            return delivery_amount;
        }

        public void setDelivery_amount(String delivery_amount) {
            this.delivery_amount = delivery_amount;
        }

        public String getDelivery_status_flag() {
            return delivery_status_flag;
        }

        public void setDelivery_status_flag(String delivery_status_flag) {
            this.delivery_status_flag = delivery_status_flag;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getCustomer_mobile_number() {
            return customer_mobile_number;
        }

        public void setCustomer_mobile_number(String customer_mobile_number) {
            this.customer_mobile_number = customer_mobile_number;
        }

        public String getRegion_name() {
            return region_name;
        }

        public void setRegion_name(String region_name) {
            this.region_name = region_name;
        }

        public String getTax_amount() {
            return tax_amount;
        }

        public void setTax_amount(String tax_amount) {
            this.tax_amount = tax_amount;
        }
    }
}
