package com.tarsus.example.TEST;

import com.tarsus.example.base.Tarsus;

import java.util.List;

public class BaseServerTest {
    public static void main(String[] args) {

        String a = "#a#1#b##a#tom#b#jump#c#12#d#[1,2,3,4,5]#z##c#2#z#";
        String b = "#a##a#tom#b#jump#c#12#d#[1,2,3,4,5]#z##b#End#z#";
        String c = "#a##a#tom#b#jump#c#12#d#[1,2,3,4,5]#z#" +
                "#b#tom#c#jump#d##a#tom#b#jump#c#12#d#[1,2,3," +
                "4,5]#z##e##a#tom#b#jump#c#12#d#[1,2,3,4,5]#e##a#t" +
                "om#b#jump#c#12#d#[1,2,3,4,5]#z##z##f#12#g#[1,2,3,4," +
                "5]#h##a#tom#b#jump#c#12#d#[1,2,3,4,5]#e##a#tom#b#jump" +
                "#c#12#d#[1,2,3,4,5]#e##a#tom#b#jump#c#12#d#[1,2,3,4,5]#e##a" +
                "#tom#b#jump#c#12#d#[1,2,3,4,5]#z##z##z##z##z#";

        final Tarsus arcBaseServer = new Tarsus();
//        final List list = arcBaseServer.unpkgBody(c);
//        final List list1 = arcBaseServer.unpkgBody(a);
//        final List list2 = arcBaseServer.unpkgBody(b);
//        System.out.println(list);
//        System.out.println(list1);
//        System.out.println(list2);

    }
}
