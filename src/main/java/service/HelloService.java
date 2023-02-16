package service;

import decorator.ioc.Collect;
import enity.Drug;
import params.Job;
import utils.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

@Collect
public class HelloService {
    public void sayHello(Job j1){
        try {
            Connection connect = DBUtil.getConnect();
            ResultSet resultSet = connect.prepareStatement("select * from drug").executeQuery();
//            List<Drug> list = DBUtil.getConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("cout this jobName >> "+j1.JobName);
    }
}


