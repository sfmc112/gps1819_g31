package movietime.database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class DatabaseManager {
    private static final String API_KEY = "2a3f76ac51ab2751b4d89e6da5052462";
    
    //TODO: get movies by genres
    public ArrayList<Movie> getMoviesByGenre(HashSet<Integer> genres){
        StringBuilder builder = new StringBuilder();
        
        for(int genre : genres){
            builder.append(genre).append(",");
        }
        
        String result = builder.toString();
        result = result.substring(0, result.length()-1);
        
        return null;
    }
//    
//    //TODO: get prefered movies
//    public ArrayList<Movie> getMoviesByPreference(UserPreferences userPref){
//        
//    }
//    
//    //TODO: get movies for search
//    public ArrayList<Movie> getMoviesByKeyword(String keyword, Integer genre){
//        
//    }
    
    //TODO: get movie By ID
    public Movie getMovieByID(int id){
        String result = getData("https://api.themoviedb.org/3/movie/" + id + "?api_key=" + API_KEY + "&language=pt-PT");
        return extractDataFromJSONSingleID(new JSONObject(result));
    }
    
    private Movie extractDataFromJSON(JSONObject m){
        JSONArray genres = m.getJSONArray("genre_ids");
        ArrayList<Integer> gen = new ArrayList<>();
        
        for(Object num : genres.toList()){
            gen.add((Integer) num);
        }
        
        Movie result = new Movie(m.getInt("id"),
                                 m.getString("title"),
                                 m.getString("release_date"),
                                 gen,
                                 m.getString("overview"),
                                 m.getString("poster_path"),
                                 m.getString("backdrop_path"));
        
        return result;
    }
    
    private Movie extractDataFromJSONSingleID(JSONObject m){
        JSONArray genres = m.getJSONArray("genres");
        ArrayList<Integer> gen = new ArrayList<>();
        
        for(int i = 0; i < genres.length(); i++){
            JSONObject temp = genres.getJSONObject(i);
            gen.add(temp.getInt("id"));
        }
        
        Movie result = new Movie(m.getInt("id"),
                                 m.getString("title"),
                                 m.getString("release_date"),
                                 gen,
                                 m.getString("overview"),
                                 m.getString("poster_path"),
                                 m.getString("backdrop_path"));
        
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
}
