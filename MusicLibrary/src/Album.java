
import java.util.ArrayList;


public class Album {

    private String description;
    private String genre;
    private String year;
    private ArrayList<Song> songs= new ArrayList<Song>();

    public Album(String description, String genre, String year, ArrayList<Song> songs) {
        this.description = description;
        this.genre = genre;
        this.year = year;
        this.songs= songs;

    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }
        public void setYear(String year) {
        this.year = year;
    }

    public String getYear() {
        return year;
    }


    public String toString() {
        return "Description:" + description +"Year:"+ year + "Genre:" + genre + "Songs:" + songs;
    }

}
