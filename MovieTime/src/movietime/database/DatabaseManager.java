package movietime.database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DatabaseManager {
    private static final String API_KEY = "2a3f76ac51ab2751b4d89e6da5052462";
 
    //This function retrieves from the The MoviesDB the list of upcoming movies.
    public ArrayList<ArrayList<Movie>> getUpcomingMovies(){
        ArrayList<ArrayList<Movie>> movies = new ArrayList<>();
        String date = getLocalDateNow();
        
        String query = getData("https://api.themoviedb.org/3/discover/movie?api_key=" + 
                    API_KEY + "&language=pt-PT&region=PT&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&primary_release_date.gte=" +
                    date + "&release_date.gte=" + date);
        
        JSONObject global = new JSONObject(query);
        int pages = global.getInt("total_pages");
        int quantity = global.getInt("total_results");
        if(pages > 10)
            pages = 10;

        ArrayList<Movie> moviesInsideArray = new ArrayList<>();
        
        for(int i = 1; i < pages+1;i++){
            
            query = getData("https://api.themoviedb.org/3/discover/movie?api_key=" + 
                    API_KEY + "&language=pt-PT&region=PT&sort_by=popularity.desc&include_adult=false&include_video=false&page="+ i +"&primary_release_date.gte=" +
                    date + "&release_date.gte=" + date);

            global = new JSONObject(query);
            JSONArray list = global.getJSONArray("results");

            for(int j = 0; j < list.length(); j++){
                JSONObject temp = list.getJSONObject(j);
                moviesInsideArray.add(extractDataFromJSON(temp));
            }
        }
        
        movies.add(moviesInsideArray);
        return movies;
    }
    
    //This function receives a list of genres and returns a list of movie lists for each genre.
    public ArrayList<ArrayList<Movie>> getUpcomingMoviesByGenre(HashSet<Integer> genres){
        ArrayList<ArrayList<Movie>> movies = new ArrayList<>();
        Object [] array_genres;
        String date = getLocalDateNow();
        
        for(Integer genre: genres){
             
             String query = getData("https://api.themoviedb.org/3/discover/movie?api_key=" + 
                    API_KEY + "&language=pt-PT&region=PT&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&primary_release_date.gte=" +
                    date + "&release_date.gte=" + date + "&with_genres=" + genre);
        
            JSONObject global = new JSONObject(query);
            int pages = global.getInt("total_pages");
            int quantity = global.getInt("total_results");
            if(pages > 10)
                pages = 10;

            ArrayList<Movie> moviesByGenre = new ArrayList<>();

            for(int i = 1; i < pages+1;i++){
            
                query = getData("https://api.themoviedb.org/3/discover/movie?api_key=" + 
                        API_KEY + "&language=pt-PT&region=PT&sort_by=popularity.desc&include_adult=false&include_video=false&page="+ i +"&primary_release_date.gte=" +
                        date + "&release_date.gte=" + date + "&with_genres=" + genre);

                global = new JSONObject(query);
                JSONArray list = global.getJSONArray("results");

                for(int j = 0; j < list.length(); j++){
                    JSONObject temp = list.getJSONObject(j);
                    moviesByGenre.add(extractDataFromJSON(temp));
                }
            }
            movies.add(moviesByGenre);
        }
        return movies;
    }
    
    //from a given ArrayList of ArrayLists of movies, returns ArrayList of ArrayLists of moviesSS of the elements that contain the keyword
    private ArrayList<ArrayList<Movie>> searchByKeyword(ArrayList<ArrayList<Movie>> movies, String keyword){
        for (Iterator<ArrayList<Movie>> iterator = movies.iterator(); iterator.hasNext();) {
            ArrayList<Movie> movieList = iterator.next();
            for (Iterator<Movie> iterator1 = movieList.iterator(); iterator1.hasNext();) {
                Movie movie = iterator1.next();
                if(!movie.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                    iterator1.remove();
            }
            
            if(movieList.isEmpty())
                iterator.remove();
        }
        return movies;
    }
    
    //This function returns a list of movies from search with both genre and the keyword selected
    public ArrayList<ArrayList<Movie>> getUpcomingMoviesByKeywordAndGenre(String keyword, Integer genre){
        HashSet<Integer> genres = new HashSet<>();
        genres.add(genre);
        ArrayList<ArrayList<Movie>> movies = getUpcomingMoviesByGenre(genres);
        
        return searchByKeyword(movies,keyword);
    }
    
    //This function returns a list of movies from search with only the keyword selected
    public ArrayList<ArrayList<Movie>> getUpcomingMoviesByKeyword(String keyword){
        ArrayList<ArrayList<Movie>> movies = getUpcomingMovies();
        
        return searchByKeyword(movies,keyword);
    }
    
    //this function returns the JSON object of a movie queried for a given id
    public Movie getMovieByID(int id){
        String result = getData("https://api.themoviedb.org/3/movie/" + id + "?api_key=" + API_KEY + "&language=pt-PT");
        return extractDataFromJSONSingleID(new JSONObject(result));
    }
    
    //Must be used with the JSON data that contains a large ammounts of data
    //This extracts data from the JSON data received from the API
    private Movie extractDataFromJSON(JSONObject m){
        JSONArray genres = m.getJSONArray("genre_ids");
        HashSet<Integer> gen = new HashSet<>();
        
        for(Object num : genres.toList()){
            gen.add((Integer) num);
        }
        
        String poster;
        String backdrop;
        
        try{
            poster = m.getString("poster_path");
        }catch(JSONException e){
            poster = "";//TODO: put Default URL
        }

        try{
            backdrop = m.getString("backdrop_path");
        }catch(JSONException e){
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
    
    //Only to be used while getting a single movie from an ID.
    //This function get's data from the JSON data from movies with a selected ID
    private Movie extractDataFromJSONSingleID(JSONObject m){
        JSONArray genres = m.getJSONArray("genres");
        HashSet<Integer> gen = new HashSet<>();
        
        for(int i = 0; i < genres.length(); i++){
            JSONObject temp = genres.getJSONObject(i);
            gen.add(temp.getInt("id"));
        }
        
        String poster;
        String backdrop;
        
        try{
            poster = m.getString("poster_path");
        }catch(JSONException e){
            poster = "";//TODO: put Default URL
        }

        try{
            backdrop = m.getString("backdrop_path");
        }catch(JSONException e){
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
    
    //Function that establises connection and makes the query to the Database API
    private String getData(String address) 
    {
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
                resp.append("Error while trying to access page: ").append(address).append("\tcode: ").append(codigo);
            }
        } catch (IOException e) {
            //TODO: solve this e.printStackTrace();
        }
        return resp.toString();
    }
    
    @Deprecated
    //Converts genre array to string
    private String genreListToString(HashSet<Integer> genres){ 
        StringBuilder builder = new StringBuilder();
        
        for(int genre : genres){
            builder.append(genre).append("|");
        }
        
        String result = builder.toString();
        result = result.substring(0, result.length()-1);
        
        return result;
    }
    
    //get localdate in YYYY-MM-DD string
    private String getLocalDateNow(){
        String date = LocalDate.now().getYear() + "-" +
                    LocalDate.now().getMonthValue() + "-" + 
                    LocalDate.now().getDayOfMonth();
        
        return date;
    }
}
