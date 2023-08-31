package dev_v2_0.t4;

import com.tarsus.lib.main_control.load_server.TarsusBodyABS;

public class PrivateJson extends TarsusBodyABS {
    public String name;
    public String value;

    public static void main(String[] args) {
        PrivateJson privateJson = new PrivateJson();
        privateJson.name = "1";
        privateJson.value = "2";
        System.out.println(privateJson.json());
    }
}
