package com.example.task.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BillItemsData {

    @SerializedName("DeliveryBillItems")
    private ArrayList<BillItemsData.BillsItemsModel> BillsList;


    public ArrayList<BillsItemsModel> getBillsList() {
        return BillsList;
    }

    public void setBillsList(ArrayList<BillsItemsModel> billsList) {
        BillsList = billsList;
    }

    public class BillsItemsModel {

        @SerializedName("BILL_NO")
        private String bill_number;

        @SerializedName("BILL_SRL")
        private String bill_serial;

        @SerializedName("ITM_UNT")
        private String item_unit;

        @SerializedName("I_CODE")
        private String item_code;

        @SerializedName("I_NM")
        private String item_name;

        @SerializedName("I_PRICE")
        private String item_price;

        @SerializedName("I_PRICE_VAT")
        private String item_price_vat;

        @SerializedName("I_QTY")
        private String item_quantity;

        @SerializedName("MOBILE_NO")
        private String customer_mobile_number;

        @SerializedName("P_SIZE")
        private String p_size;

        @SerializedName("VAT_AMT")
        private String vat_amount;

        @SerializedName("VAT_PRCNT")
        private String vat_percentage;


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

        public String getItem_unit() {
            return item_unit;
        }

        public void setItem_unit(String item_unit) {
            this.item_unit = item_unit;
        }

        public String getItem_code() {
            return item_code;
        }

        public void setItem_code(String item_code) {
            this.item_code = item_code;
        }

        public String getItem_name() {
            return item_name;
        }

        public void setItem_name(String item_name) {
            this.item_name = item_name;
        }

        public String getItem_price() {
            return item_price;
        }

        public void setItem_price(String item_price) {
            this.item_price = item_price;
        }

        public String getItem_price_vat() {
            return item_price_vat;
        }

        public void setItem_price_vat(String item_price_vat) {
            this.item_price_vat = item_price_vat;
        }

        public String getItem_quantity() {
            return item_quantity;
        }

        public void setItem_quantity(String item_quantity) {
            this.item_quantity = item_quantity;
        }

        public String getCustomer_mobile_number() {
            return customer_mobile_number;
        }

        public void setCustomer_mobile_number(String customer_mobile_number) {
            this.customer_mobile_number = customer_mobile_number;
        }

        public String getP_size() {
            return p_size;
        }

        public void setP_size(String p_size) {
            this.p_size = p_size;
        }

        public String getVat_amount() {
            return vat_amount;
        }

        public void setVat_amount(String vat_amount) {
            this.vat_amount = vat_amount;
        }

        public String getVat_percentage() {
            return vat_percentage;
        }

        public void setVat_percentage(String vat_percentage) {
            this.vat_percentage = vat_percentage;
        }
    }

}
