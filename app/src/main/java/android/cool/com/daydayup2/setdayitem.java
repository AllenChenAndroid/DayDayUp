package android.cool.com.daydayup2;

/**
 * Created by cl on 2017/5/3.
 */

public class setdayitem {

    String  week;
    String name;
    String place;
    String tip;

    public setdayitem(String place, String name, String tip, String week) {
        this.place = place;
        this.name = name;
        this.tip = tip;
        this.week = week;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }


}
