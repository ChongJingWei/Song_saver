package sg.edu.rp.c346.id22024709.songsaver;

public class Song {

    private int _id;
    private String title;
    private String singers;
    private int year;
    private int stars;

    public Song (String title, String singers, int year, int stars){
        this.title = title;
        this.singers = singers;
        this.year = year;
        this.stars = stars;
    }
    public int get_id(){
        return _id;
    }
    public String getTitle(){
        return title;
    }
    public String getSingers(){
        return singers;
    }
    public int getYear(){
        return year;
    }
    public int getStar(){
        return stars;
    }
}
