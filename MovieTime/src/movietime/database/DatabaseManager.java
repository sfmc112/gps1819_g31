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

public class DatabaseManager {

    private static final String API_KEY = "2a3f76ac51ab2751b4d89e6da5052462";

    public DatabaseManager() {
    }

    //TODO: Organize hashset by release date in the object that calls this method
    /**
     * This function returns the information from the movie DB in JSON format
     * about the followed movies(is for the followed menu)
     *
     * @param followedIds HashSet of movie ID's
     * @return ArrayList of Movies with the correspondent ids given in the
     * parameters
     */
    public static ArrayList<Movie> getFollowedMovies(HashSet<Integer> followedIds) {
        if (followedIds.isEmpty()) {
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
            String date = getLocalDateNow();

            String query = getData("https://api.themoviedb.org/3/discover/movie?api_key="
                    + API_KEY + "&language=pt-PT&region=PT&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&primary_release_date.gte="
                    + date + "&release_date.gte=" + date);

            JSONObject global = new JSONObject(query);
            int pages = global.getInt("total_pages");
            int quantity = global.getInt("total_results");
            if (pages > 10) {
                pages = 10;
            }

            ArrayList<Movie> moviesInsideArray = new ArrayList<>();

            for (int i = 1; i < pages + 1; i++) {
                query = getData("https://api.themoviedb.org/3/discover/movie?api_key="
                        + API_KEY + "&language=pt-PT&region=PT&sort_by=popularity.desc&include_adult=false&include_video=false&page=" + i + "&primary_release_date.gte="
                        + date + "&release_date.gte=" + date);

                global = new JSONObject(query);
                JSONArray list = global.getJSONArray("results");

                for (int j = 0; j < list.length(); j++) {
                    JSONObject temp = list.getJSONObject(j);
                    moviesInsideArray.add(extractDataFromJSON(temp));
                }
            }

            movies.add(moviesInsideArray);

            return movies;

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
    public static ArrayList<ArrayList<Movie>> getUpcomingMoviesByGenre(HashSet<Integer> genres) {
        if (genres.isEmpty()) {
            return null;
        }
        try {
            ArrayList<ArrayList<Movie>> movies = new ArrayList<>();
            Object[] array_genres;
            String date = getLocalDateNow();

            genres.stream().map((genre) -> {
                String query = getData("https://api.themoviedb.org/3/discover/movie?api_key="
                        + API_KEY + "&language=pt-PT&region=PT&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&primary_release_date.gte="
                        + date + "&release_date.gte=" + date + "&with_genres="
                        + genre + "&append_to_response=credits");
                JSONObject global = new JSONObject(query);
                int pages = global.getInt("total_pages");
                int quantity = global.getInt("total_results");
                if (pages > 10) {
                    pages = 10;
                }
                ArrayList<Movie> moviesByGenre = new ArrayList<>();
                for (int i = 1; i < pages + 1; i++) {
                    query = getData("https://api.themoviedb.org/3/discover/movie?api_key="
                            + API_KEY + "&language=pt-PT&region=PT&sort_by=popularity.desc&include_adult=false&include_video=false&page=" + i + "&primary_release_date.gte="
                            + date + "&release_date.gte=" + date + "&with_genres="
                            + genre + "&append_to_response=credits");

                    global = new JSONObject(query);
                    JSONArray list = global.getJSONArray("results");

                    for (int j = 0; j < list.length(); j++) {
                        JSONObject temp = list.getJSONObject(j);
                        moviesByGenre.add(extractDataFromJSON(temp));
                    }
                }
                return moviesByGenre;
            }).forEachOrdered((moviesByGenre) -> {
                movies.add(moviesByGenre);
            });
            return movies;

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
        for (Iterator<ArrayList<Movie>> it = movies.iterator(); it.hasNext();) {
            ArrayList<Movie> movieList = it.next();
            for (Iterator<Movie> it2 = movieList.iterator(); it2.hasNext();) {
                Movie movie = it2.next();
                if (!movie.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                    it2.remove();
                }
            }

            if (movieList.isEmpty()) {
                it.remove();
            }
        }
        return movies;
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
    public static ArrayList<ArrayList<Movie>> getUpcomingMoviesByKeywordAndGenre(
            String keyword, Integer genre) {
        HashSet<Integer> genres = new HashSet<>();
        genres.add(genre);
        ArrayList<ArrayList<Movie>> movies = getUpcomingMoviesByGenre(genres);

        return searchByKeyword(movies, keyword);
    }

    /**
     * This function returns a list of movies from search with only the keyword
     * selected
     *
     * @param keyword keyword provided on the search menu
     * @return list of movies from search with the specified keyword
     */
    public static ArrayList<ArrayList<Movie>> getUpcomingMoviesByKeyword(String keyword) {
        ArrayList<ArrayList<Movie>> movies = getUpcomingMovies();

        return searchByKeyword(movies, keyword);
    }

    ///TODO check this description... it returns a Movie and not a JSON object
    /**
     * This function returns the JSON object of a movie queried for a given id
     *
     * @param id id of the requested Movie
     * @return Movie object retreived from the id query
     */
    public static Movie getMovieByID(int id) {
        try {
            String result = getData("https://api.themoviedb.org/3/movie/"
                    + id + "?api_key=" + API_KEY
                    + "&language=pt-PT&append_to_response=credits");

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
        JSONArray genres = m.getJSONArray("genre_ids");
        HashSet<Integer> gen = new HashSet<>();

        for (Object num : genres.toList()) {
            gen.add((Integer) num);
        }

        String poster;
        String backdrop;

        try {
            poster = m.getString("poster_path");
        } catch (JSONException e) {
            poster = "";//TODO: put Default URL
        }

        try {
            backdrop = m.getString("backdrop_path");
        } catch (JSONException e) {
            backdrop = "";//TODO: put Default URL
        }

        Movie result = new Movie(m.getInt("id"),
                m.getString("title"),
                m.getString("release_date"),
                gen,
                m.getString("overview"),
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
        ArrayList<String> cast = new ArrayList<>();
        JSONArray castAr = credits.getJSONArray("cast");
        int size = 3;

        if (castAr.length() < 3) {
            size = castAr.length();
        }

        for (int i = 0; i < size; i++) {
            JSONObject actor = castAr.getJSONObject(i);
            cast.add(actor.getString("name"));
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
        JSONArray crew = credits.getJSONArray("crew");

        for (int i = 0; i < crew.length(); i++) {
            JSONObject member = crew.getJSONObject(i);
            if (member.getString("job").compareTo("Director") == 0) {
                return member.getString("name");
            }
        }

        return "not specified";
    }


    /**
     * Only to be used while getting a single movie from an ID. This function
     * gets data from the JSON data from movies with a selected ID
     *
     * @param m JSONObject data
     * @return Movie object
     */
    private static Movie extractDataFromJSONSingleID(JSONObject m) {
        JSONArray genres = m.getJSONArray("genres");
        HashSet<Integer> gen = new HashSet<>();

        for (int i = 0; i < genres.length(); i++) {
            JSONObject temp = genres.getJSONObject(i);
            gen.add(temp.getInt("id"));
        }

        String poster;
        String backdrop;

        try {
            poster = m.getString("poster_path");
        } catch (JSONException e) {
            poster = "";//TODO: put Default URL
        }

        try {
            backdrop = m.getString("backdrop_path");
        } catch (JSONException e) {
            backdrop = "";//TODO: put Default URL
        }

        String director = getDirectorFromJSONData(m.getJSONObject("credits"));
        ArrayList<String> cast = getCastFromJSONData(m.getJSONObject("credits"));

        Movie result = new Movie(m.getInt("id"),
                m.getString("title"),
                m.getString("release_date"),
                gen,
                m.getString("overview"),
                poster,
                backdrop,
                cast,
                director);

        return result;
    }

    /**
     * Function that establises connection and makes the query to the Database API
     * @param address URL address
     * @return the data from the Database API
     */
    private static String getData(String address) {
        StringBuilder resp = new StringBuilder();

        try {
            URL url = new URL(address);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milisegundos */);
            conn.setConnectTimeout(10000 /* milisegundos */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();

            int codigo = conn.getResponseCode();
            if (codigo == HttpURLConnection.HTTP_OK /*200*/) {
                InputStream is = conn.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line;

                while ((line = br.readLine()) != null) {
                    resp.append(line).append("\n");
                }
            } else {
                resp.append("Error while trying to access page: ")
                        .append(address).append("\tcode:").append(codigo);
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
     * get localdate in YYYY-MM-DD string
     * @return localdate in YYYY-MM-DD string
     */
    private static String getLocalDateNow() {
        String date = LocalDate.now().getYear() + "-"
                + LocalDate.now().getMonthValue() + "-"
                + LocalDate.now().getDayOfMonth();

        return date;
    }
}
