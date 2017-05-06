package android.cool.com.daydayup2;

import org.litepal.crud.DataSupport;

/**
 * Created by cl on 2017/5/3.
 */

public class School extends DataSupport {
private String Tip;
    private String Mon;
    private String Tue;
    private String Wed;
    private String Thu;
    private String Fri;
    private String Sar;
    private String Sun;
    private String Time;

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getFri() {
        return Fri;
    }

    public void setFri(String fri) {
        Fri = fri;
    }

    public String getMon() {
        return Mon;
    }

    public void setMon(String mon) {
        Mon = mon;
    }

    public String getSar() {
        return Sar;
    }

    public void setSar(String sar) {
        Sar = sar;
    }

    public String getSun() {
        return Sun;
    }

    public void setSun(String sun) {
        Sun = sun;
    }

    public String getThu() {
        return Thu;
    }

    public void setThu(String thu) {
        Thu = thu;
    }

    public String getTip() {
        return Tip;
    }

    public void setTip(String tip) {
        Tip = tip;
    }



    public String getTue() {
        return Tue;
    }

    public void setTue(String tue) {
        Tue = tue;
    }

    public String getWed() {
        return Wed;
    }

    public void setWed(String wed) {
        Wed = wed;
    }
}
