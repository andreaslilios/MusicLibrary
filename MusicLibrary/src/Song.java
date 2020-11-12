
import java.io.Serializable;

public class Song implements Serializable {

    private String title;
    private String singer;
    private String duration;
    

    public Song(String title, String singer, String duration) {
        this.title = title;
        this.singer = singer;
        this.duration = duration;
        
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getSinger() {
        return singer;
    }

    public void setDuration(String id) {
        this.duration = duration;
    }

    public String getDuration() {
        return duration;
    }

    

    public String toString() {
        return "Title:" + title + "Singer:" + singer + "Duration:" + duration ;
    }

}
