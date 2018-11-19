/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movietime.database;

import java.util.HashSet;

public class Movie {
    
    public static final int ACTION = 28;
    public static final int WESTERN = 37;
    public static final int WAR = 10752;
    public static final int THRILLER = 53;
    public static final int TV_MOVIE = 10770;
    public static final int SCIENCE_FICTION = 878;
    public static final int ROMANCE = 10749;
    public static final int MYSTERY = 9648;
    public static final int HISTORY = 36;
    public static final int MUSIC = 10402;
    public static final int HORROR = 27;
    public static final int FANTASY = 14;
    public static final int FAMILY = 10751;
    public static final int DRAMA = 18;
    public static final int DOCUMENTARY = 99;
    public static final int CRIME = 80;
    public static final int COMEDY = 35;
    public static final int ANIMATION = 16;
    public static final int ADVENTURE = 12;
    
    //Poster Sizes
    public static final String SIZE_VERY_SMALL = "w92";
    public static final String SIZE_SMALL = "w154";
    public static final String SIZE_RECOMMENDED = "w185";
    public static final String SIZE_MEDIUM = "w342";
    public static final String SIZE_LARGE = "w500";
    public static final String SIZE_VERY_LARGE = "w780";
    public static final String SIZE_ORIGINAL = "original"; //Tends to be very big
    
    //Backdrop sizes
    public static final String BK_SIZE_SMALL = "w300";
    public static final String BK_SIZE_MEDIUM = "w780";
    public static final String BK_SIZE_BIG = "w1280";
    public static final String BK_SIZE_ORIGINAL = "original"; //Tends to be very big

    
    final private int id;
    final private String title;
    final private String release_date;
    final private HashSet<Integer> genres;
    final private String overview;
    final private String poster;
    final private String backdrop;
    
    //TODO: add Actors and Director
    
    public Movie(int id, String title, String release_date, HashSet<Integer> genres, String overview, String poster, String backdrop) {
        this.id = id;
        this.title = title;
        this.release_date = release_date;
        this.genres = genres;
        this.overview = overview;
        this.poster = poster;
        this.backdrop = backdrop;
    }

    public int getId() {
        return id;
    }

    public String getTile() {
        return title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public HashSet<Integer> getGenres() {
        return genres;
    }

    public String getOverview() {
        return overview;
    }

    public String getPoster(String size) {
        return "https://image.tmdb.org/t/p/" + size + "/" + poster;
    }

    public String getBackdrop(String size) {
        return "https://image.tmdb.org/t/p/" + size + "/" + backdrop;
    }

    @Override
    public String toString() {
        return "Movie{" + "id=" + id + ", tile=" + title + ", release_date=" + release_date + ", genres=" + genres + ", overview=" + overview + ", poster=" + poster + ", backdrop=" + backdrop + '}';
    }
}
