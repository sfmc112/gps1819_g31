package movietime.database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DatabaseManager implements DatabaseConstants {

    private static final String API_KEY = "2a3f76ac51ab2751b4d89e6da5052462";

    public DatabaseManager() {
    }

    /**
     * This function returns the information from the movie DB in JSON format
     * about the followed movies(is for the followed menu)
     *
     * @param followedIds HashSet of movie ID's
     * @return ArrayList of Movies with the correspondent ids given in the
     * parameters
     */
    public static ArrayList<Movie> getFollowedMovies(
            HashSet<Integer> followedIds) {
        if (followedIds == null || followedIds.isEmpty()) {
            return null;
        }

        ArrayList<Movie> followedMovies = new ArrayList<>();
        for (int movieId : followedIds) {
            Movie movie = getMovieByID(movieId);
            if (movie != null) {
                followedMovies.add(movie);
            }
        }
        return followedMovies;
    }

    /**
     * This function retrieves from the The MoviesDB the list of upcoming
     * movies.
     *
     * @return List of all upcoming movies from the MovieDB
     */
    public static ArrayList<ArrayList<Movie>> getUpcomingMovies() {
        try {
            ArrayList<ArrayList<Movie>> movies = new ArrayList<>();
            
            String query = getDataFromAllUpcomingMovies();
            JSONObject global = new JSONObject(query);
            
            int pages = global.getInt(TOTAL_PAGES);
            if (pages > MAX_PAGES) {
                pages = MAX_PAGES;
            }

            ArrayList<Movie> moviesInsideArray = new ArrayList<>();
            for (int i = 1; i <= pages; i++) {
                query = getDataFromAllUpcomingMovies(pages);
                global = new JSONObject(query);
                
                JSONArray list = global.getJSONArray(RESULTS);

                for (int j = 0; j < list.length(); j++) {
                    JSONObject temp = list.getJSONObject(j);
                    moviesInsideArray.add(extractDataFromJSON(temp));
                }
            }

            movies.add(moviesInsideArray);
            if (movies.size() == 1 && movies.get(0).isEmpty()) {
                return null;
            } else {
                return movies;
            }

        } catch (JSONException e) {
            return null;
        }
    }

    /**
     * This function receives a list of genres and returns a list of movie lists
     * for each genre.
     *
     * @param genres List of genres (user's preferred genres)
     * @return List of movies of the provided genres
     */
    public static ArrayList<ArrayList<Movie>> getUpcomingMoviesByGenre(
            HashSet<Integer> genres) {
        if (genres == null || genres.isEmpty()) {
            return null;
        }
        try {
            ArrayList<ArrayList<Movie>> movies = new ArrayList<>();
            String date = getLocalDateNow();

            for (Integer genre : genres) {
                String query = getMoviesByGenre(genre);
                JSONObject global = new JSONObject(query);
                
                int pages = global.getInt(TOTAL_PAGES);
                if (pages > MAX_PAGES) {
                    pages = MAX_PAGES;
                }
                ArrayList<Movie> moviesByGenre = new ArrayList<>();
                for (int i = 1; i < pages + 1; i++) {
                    query = getMoviesByGenre(genre, i);
                    global = new JSONObject(query);
                    
                    JSONArray list = global.getJSONArray(RESULTS);
                    for (int j = 0; j < list.length(); j++) {
                        JSONObject temp = list.getJSONObject(j);
                        moviesByGenre.add(extractDataFromJSON(temp));
                    }
                }
                movies.add(moviesByGenre);

            }
            if (movies.size() == 1 && movies.get(0).isEmpty()) {
                return null;
            } else {
                return movies;
            }
        } catch (JSONException e) {
            return null;
        }
    }

    /**
     * From a given ArrayList of ArrayLists of movies, returns ArrayList of
     * ArrayLists of moviesSS of the elements that contain the keyword
     *
     * @param movies ArrayList of ArrayLists of movies
     * @param keyword keyword provided on the search menu
     * @return ArrayList of ArrayLists of movies of the elements that contain
     * the keyword
     */
    private static ArrayList<ArrayList<Movie>> searchByKeyword(
            ArrayList<ArrayList<Movie>> movies, String keyword) {
        if (movies == null || keyword == null) {
            return null;
        }
        for (Iterator<ArrayList<Movie>> it = movies.iterator(); it.hasNext();) {
            ArrayList<Movie> movieList = it.next();
            for (Iterator<Movie> it2 = movieList.iterator(); it2.hasNext();) {
                Movie movie = it2.next();
                if (!movie.getTitle().toLowerCase()
                        .contains(keyword.toLowerCase())) {
                    it2.remove();
                }
            }

            if (movieList.isEmpty()) {
                it.remove();
            }
        }
        if (movies.size() == 1 && movies.get(0).isEmpty()) {
            return null;
        } else {
            return movies;
        }
    }

    /**
     * This function returns a list of movies from search with both genre and
     * the keyword selected
     *
     * @param keyword keyword provided on the search menu
     * @param genre genre provided on the search menu
     * @return list of movies from search with both genre and the keyword
     * selected
     */
    public static ArrayList<ArrayList<Movie>>getUpcomingMoviesByKeywordAndGenre(
            String keyword, Integer genre)
    {
        if (keyword == null && genre == null) {
            return getUpcomingMovies();
            
        } else if (keyword == null && genre != null) {
            HashSet<Integer> lonelyHashSet = new HashSet<>();
            lonelyHashSet.add(genre);
            return getUpcomingMoviesByGenre(lonelyHashSet);
            
        } else if (keyword != null && genre == null) {
            return getUpcomingMoviesByKeyword(keyword);
        
        } else {
            HashSet<Integer> genres = new HashSet<>();
            genres.add(genre);
            ArrayList<ArrayList<Movie>> movies = getUpcomingMoviesByGenre
                                                                       (genres);

            return searchByKeyword(movies, keyword);
        }
    }

    /**
     * This function returns a list of movies from search with only the keyword
     * selected
     *
     * @param keyword keyword provided on the search menu
     * @return list of movies from search with the specified keyword
     */
    public static ArrayList<ArrayList<Movie>> getUpcomingMoviesByKeyword(
            String keyword) {
        if (keyword == null) {
            return null;
        }
        ArrayList<ArrayList<Movie>> movies = getUpcomingMovies();

        return searchByKeyword(movies, keyword);
    }

    /**
     * This function returns a movie queried for a given id
     *
     * @param id id of the requested Movie
     * @return Movie object retreived from the id query
     */
    public static Movie getMovieByID(int id) {

        try {
            String result = getMovieDataFromId(id);

            return extractDataFromJSONSingleID(new JSONObject(result));
        } catch (JSONException e) {
            return null;
        }
    }

    /**
     * Must be used with the JSON data that contains a large ammounts of data
     * This extracts data from the JSON data received from the API
     *
     * @param m JSONObject with movie data
     * @return Movie object created based on the JSON data
     */
    private static Movie extractDataFromJSON(JSONObject m) {
        if (m == null) {
            return null;
        }
        
        JSONArray genres = m.getJSONArray(MOVIE_GENRE_IDS);
        HashSet<Integer> gen = new HashSet<>();

        for (Object num : genres.toList()) {
            gen.add((Integer) num);
        }

        String poster;
        String backdrop;

        try {
            poster = m.getString(MOVIE_POSTER_PATH);
        } catch (JSONException e) {
            poster = "";
        }

        try {
            backdrop = m.getString(MOVIE_BACKDROP_PATH);
        } catch (JSONException e) {
            backdrop = "";
        }

        Movie result = new Movie(m.getInt(MOVIE_ID),
                m.getString(MOVIE_TITLE),
                m.getString(MOVIE_RELEASE_DATE),
                gen,
                m.getString(MOVIE_OVERVIEW),
                poster,
                backdrop);

        return result;
    }

    /**
     * This method searches for the main actors names from a JSONObject
     *
     * @param credits JSONObject that contains the information about the movie
     * cast
     * @return List of names of the movie's cast
     */
    private static ArrayList<String> getCastFromJSONData(JSONObject credits) {
        if (credits == null) {
            return null;
        }
        ArrayList<String> cast = new ArrayList<>();
        JSONArray castAr = credits.getJSONArray(MOVIE_CAST);
        int size = MAX_CAST;

        if (castAr.length() < MAX_CAST) {
            size = castAr.length();
        }

        for (int i = 0; i < size; i++) {
            JSONObject actor = castAr.getJSONObject(i);
            cast.add(actor.getString(MOVIE_CAST_NAME));
        }

        return cast;
    }

    /**
     * This method searches for the movie director's name from a JSONObject
     *
     * @param credits JSONObject that contains the information about the movie
     * Director (in case it's available)
     * @return Director's name or "not specified"
     */
    private static String getDirectorFromJSONData(JSONObject credits) {
        JSONArray crew = credits.getJSONArray(MOVIE_CREW);

        for (int i = 0; i < crew.length(); i++) {
            JSONObject member = crew.getJSONObject(i);
            if (member.getString(MOVIE_CREW_JOB)
                    .compareTo(MOVIE_CREW_DIRECTOR) == 0) {
                return member.getString(MOVIE_CREW_NAME);
            }
        }

        return NOT_SPECIFIED;
    }

    /**
     * Only to be used while getting a single movie from an ID. This function
     * gets data from the JSON data from movies with a selected ID
     *
     * @param m JSONObject data
     * @return Movie object
     */
    private static Movie extractDataFromJSONSingleID(JSONObject m) {
        JSONArray genres = m.getJSONArray(MOVIE_GENRES);
        HashSet<Integer> gen = new HashSet<>();

        for (int i = 0; i < genres.length(); i++) {
            JSONObject temp = genres.getJSONObject(i);
            gen.add(temp.getInt(GENRE_ID));
        }

        String poster;
        String backdrop;

        try {
            poster = m.getString(MOVIE_POSTER_PATH);
        } catch (JSONException e) {
            poster = "";
        }

        try {
            backdrop = m.getString(MOVIE_BACKDROP_PATH);
        } catch (JSONException e) {
            backdrop = "";
        }

        String director = getDirectorFromJSONData(m.getJSONObject
                                                            (MOVIE_CREDITS));
        ArrayList<String>cast = getCastFromJSONData(m.getJSONObject
                                                            (MOVIE_CREDITS));

        Movie result = new Movie(m.getInt(MOVIE_ID),
                m.getString(MOVIE_TITLE),
                m.getString(MOVIE_RELEASE_DATE),
                gen,
                m.getString(MOVIE_OVERVIEW),
                poster,
                backdrop,
                cast,
                director);

        return result;
    }

    /**
     * Function that establises connection and makes the query to the Database
     * API
     *
     * @param address URL address
     * @return the data from the Database API
     */
    private static String getMovieDataFromId(int id){
        String result = getData("https://api.themoviedb.org/3/movie/"
                    + id + "?api_key=" + API_KEY
                    + "&language=pt-PT&append_to_response=credits");
        
        return result;
    }
    
    private static String getMoviesByGenre(int genre){
        String date = getLocalDateNow();
        
        String query = getData("https://api.themoviedb.org/3/discover/movie?api_key="
                        + API_KEY + "&language=pt-PT&region=PT&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&primary_release_date.gte="
                        + date + "&release_date.gte=" + date + "&with_genres="
                        + genre + "&append_to_response=credits");
        
        return query;
    }
    
    private static String getMoviesByGenre(int genre, int page){
        String date = getLocalDateNow();
        
        String query = getData("https://api.themoviedb.org/3/discover/movie?api_key="
                            + API_KEY + "&language=pt-PT&region=PT&sort_by=popularity.desc&include_adult=false&include_video=false&page="
                            + page + "&primary_release_date.gte="
                            + date + "&release_date.gte=" + date
                            + "&with_genres="
                            + genre + "&append_to_response=credits");
        
        return query;
    }
    
    private static String getDataFromAllUpcomingMovies(int page){
        String date = getLocalDateNow();
        
        String query = getData("https://api.themoviedb.org/3/discover/movie?api_key="
                        + API_KEY + "&language=pt-PT&region=PT&sort_by=popularity.desc&include_adult=false&include_video=false&page="
                        + page + "&primary_release_date.gte="
                        + date + "&release_date.gte=" + date);
        
        return query;
    }
    
    private static String getDataFromAllUpcomingMovies(){
        String date = getLocalDateNow();
        
        String query = getData("https://api.themoviedb.org/3/discover/movie?api_key="
                + API_KEY + "&language=pt-PT&region=PT&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&primary_release_date.gte="
                + date + "&release_date.gte=" + date);
            
            return query;
    }
    
    private static String getData(String address) {
        StringBuilder resp = new StringBuilder();

        try {
            URL url = new URL(address);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(10000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();

            int code = conn.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK /*200*/) {
                InputStream is = conn.getInputStream();
                BufferedReader br = new BufferedReader
                            (new InputStreamReader(is));
                String line;

                while ((line = br.readLine()) != null) {
                    resp.append(line).append("\n");
                }
            } else {
                resp.append("Error while trying to access page: ")
                        .append(address).append("\tcode:").append(code);
            }
        } catch (SocketTimeoutException e) {
            resp.append("Timeout error");
        } catch (MalformedURLException e) {
            resp.append("Error in URL with query");
        } catch (IOException | IllegalStateException e) {
            resp.append("Error during conection with The Movie DB");
        }

        return resp.toString();
    }

    /**
     * Get localdate in YYYY-MM-DD string
     *
     * @return localdate in YYYY-MM-DD string
     */
    private static String getLocalDateNow() {
        String date = LocalDate.now().getYear() + "-"
                + LocalDate.now().getMonthValue() + "-"
                + LocalDate.now().getDayOfMonth();

        return date;
    }
}