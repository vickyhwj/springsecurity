package db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Db {
    public static Map<String, ArrayList<String>> userPowerMap;
    public static Map<String, ArrayList<String>> indexPowerMap;
    static{
        userPowerMap=new HashMap<>();
        final ArrayList<String> list1=new ArrayList<>();
        list1.add("ROLE_NO");
        list1.add("ROLE_USER");
        userPowerMap.put("vicky", list1);

        final ArrayList<String> list2=new ArrayList<>();
        list2.add("ROLE_ADMIN");
        list2.add("ROLE_USER");
        userPowerMap.put("json", list2);

    }
    public static ArrayList<String> findPowerByUsername(final String username){
        return userPowerMap.get(username);
    }
    public static ArrayList<String> findIndexzPowerByUsername(final String index){
        return indexPowerMap.get(index);
    }
}
