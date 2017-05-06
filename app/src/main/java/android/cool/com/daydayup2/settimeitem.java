package android.cool.com.daydayup2;

/**
 * Created by cl on 2017/5/3.
 */

public class settimeitem {
    String tip;
    String time;

    public settimeitem(String time, String tip) {
        this.time = time;
        this.tip = tip;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}
