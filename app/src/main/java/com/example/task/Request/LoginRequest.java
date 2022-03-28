package com.example.task.Request;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {

    @SerializedName("Value")
    LoginCredentials credentials;

    public LoginCredentials getCredentials() {
        return credentials;
    }

    public void setCredentials(LoginCredentials credentials) {
        this.credentials = credentials;
    }

    public static class LoginCredentials{
        String P_LANG_NO;
        String P_DLVRY_NO;
        String P_PSSWRD;

        public String getP_LANG_NO() {
            return P_LANG_NO;
        }

        public void setP_LANG_NO(String p_LANG_NO) {
            P_LANG_NO = p_LANG_NO;
        }

        public String getP_DLVRY_NO() {
            return P_DLVRY_NO;
        }

        public void setP_DLVRY_NO(String p_DLVRY_NO) {
            P_DLVRY_NO = p_DLVRY_NO;
        }

        public String getP_PSSWRD() {
            return P_PSSWRD;
        }

        public void setP_PSSWRD(String p_PSSWRD) {
            P_PSSWRD = p_PSSWRD;
        }

        public LoginCredentials(String p_LANG_NO, String p_DLVRY_NO, String p_PSSWRD) {
            P_LANG_NO = p_LANG_NO;
            P_DLVRY_NO = p_DLVRY_NO;
            P_PSSWRD = p_PSSWRD;



        }
    }
}
