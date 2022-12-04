package model;

import java.util.HashMap;

public class Achievement {
    private static Achievement instance = null;
    private HashMap<String, Boolean> achievements;

    private Achievement(){
        HashMap<String, Boolean> hm = new HashMap<>();
        hm.put("wait strip", false);
        hm.put("The Bomber", false);
        hm.put("Freak of destruction", false);
        hm.put("hundred point", false);
        hm.put("thousand point", false);
        achievements = hm;
    }

    public static Achievement getInstance(){
        if (instance == null){
            instance = new Achievement();
        }
        return instance;
    }
    public HashMap<String, Boolean> getachievents(){return achievements;}

    public void unlock(String ach){
        if (achievements.containsKey(ach)){
            System.out.println("yes key");
            achievements.replace(ach, true);
        }
        return;
    }
}
