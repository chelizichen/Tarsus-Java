package utils;

import java.util.List;

/**
 * 用于拼接 **不会发生 SQL注入事故的 SQL语句**
 */
public class SqlUtil {
    public static String getPagination(String page,String size){
        final int _page = Integer.parseInt(page);
        final int _size = Integer.parseInt(size);
        final int next_page = _page * _size;
        final int next_size = (_page + 1) * _size;
        return " limit " + next_page + "," + next_size;
    }

    public static String getMatchKeyword(String keyword){
        return " %"+keyword+"% ";
    }

    public static String getBody(String TableName){
        return "select * from " + TableName + " ";
    }

    public static String getBody(String TableName,String[] columns){
        StringBuffer stb = new StringBuffer();
        for (int i = 0; i < columns.length; i++) {
            if(i == columns.length - 1){
                stb.append(columns[i]);
            }else {
                stb.append(columns[i]).append(",");
            }
        }
        return "select " + stb.toString() + " from " + TableName + " ";
    }
}
