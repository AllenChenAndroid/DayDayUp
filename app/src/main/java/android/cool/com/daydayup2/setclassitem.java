package android.cool.com.daydayup2;

/**
 * Created by cl on 2017/5/4.
 */

public class setclassitem {
    String title;
    String content;

    public setclassitem(String content, String title) {
        this.content = content;
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
