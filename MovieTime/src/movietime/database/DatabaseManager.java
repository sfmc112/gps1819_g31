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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DatabaseManager {
    private static final String API_KEY = "2a3f76ac51ab2751b4d89e6da5052462";
    
    //TODO: get movies by genre
    //TODO: create a copy of this function and remove the genre 
    public ArrayList<Movie> getUpcomingMoviesByGenre(HashSet<Integer> genres){
        ArrayList<Movie> movies = new ArrayList<>();
        
        String genre = genreListToString(genres);
        String date = getLocalDateNow();
        
        String query = getData("https://api.themoviedb.org/3/discover/movie?api_key=" + 
                    API_KEY + "&language=pt-PT&region=PT&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&primary_release_date.gte=" +
                    date + "&release_date.gte=" + date + "&with_genres=" + genre);
        
        JSONObject global = new JSONObject(query);
        int pages = global.getInt("total_pages");
        int quantity = global.getInt("total_results");
        if(pages > 10)
            pages = 10;
        
        for(int i = 1; i < pages+1;i++){
            
            query = getData("https://api.themoviedb.org/3/discover/movie?api_key=" + 
                    API_KEY + "&language=pt-PT&region=PT&sort_by=popularity.desc&include_adult=false&include_video=false&page="+ i +"&primary_release_date.gte=" +
                    date + "&release_date.gte=" + date + "&with_genres=" + genre);

            
            System.out.println(query);
            
            global = new JSONObject(query);
            JSONArray list = global.getJSONArray("results");
            System.out.println(list);
            
            for(int j = 0; j < list.length(); j++){
                JSONObject temp = list.getJSONObject(j);
                movies.add(extractDataFromJSON(temp));
            }
        }
        
        return movies;
    }
    
//    //TODO: get movies for search
//    public ArrayList<Movie> getMoviesByKeyword(String keyword, Integer genre){
//        
//    }
    
    public Movie getMovieByID(int id){
        String result = getData("https://api.themoviedb.org/3/movie/" + id + "?api_key=" + API_KEY + "&language=pt-PT");
        return extractDataFromJSONSingleID(new JSONObject(result));
    }
    
    
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
    
    
    private String genreListToString(HashSet<Integer> genres){
        StringBuilder builder = new StringBuilder();
        
        for(int genre : genres){
            builder.append(genre).append("|");
        }
        
        String result = builder.toString();
        result = result.substring(0, result.length()-1);
        
        return result;
    }
    
    private String getLocalDateNow(){
        String date = LocalDate.now().getYear() + "-" +
                    LocalDate.now().getMonthValue() + "-" + 
                    LocalDate.now().getDayOfMonth();
        
        return date;
    }
}
