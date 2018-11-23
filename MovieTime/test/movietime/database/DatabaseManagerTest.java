/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movietime.database;

import java.util.ArrayList;
import java.util.HashSet;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author salex
 */
public class DatabaseManagerTest {
    
    public DatabaseManagerTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    /**
     * Test of getFollowedMovies method, of class DatabaseManager.
     * The method should return null when the HashSet passed as argument is null
     */
    @Test
    public void testGetFollowedMoviesWithNullHashSet() {
        System.out.println("getFollowedMoviesWithNullHashSet");
        HashSet<Integer> followedIds = null;
        ArrayList<Movie> expResult = null;
        ArrayList<Movie> result = DatabaseManager.getFollowedMovies(followedIds);
        assertEquals("The method should return null when the HashSet passed as argument is null", expResult, result);
    }

    /**
     * Test of getUpcomingMoviesByGenre method, of class DatabaseManager.
     * With a null HashSet as past parameter.
     */
    @Test
    public void testGetUpcomingMoviesByGenre() {
        System.out.println("getUpcomingMoviesByGenre");
        HashSet<Integer> genres = null;
        ArrayList<ArrayList<Movie>> expResult = null;
        ArrayList<ArrayList<Movie>> result = DatabaseManager.getUpcomingMoviesByGenre(genres);
        assertEquals("A null HashSet does not return the expected result", expResult, result);
    }
    
    /**
     * Test of getUpcomingMoviesByGenre method, of class DatabaseManager.
     * With an empty HashSet as past parameter.
     */
    @Test
    public void testGetUpcomingMoviesByGenreWithEmptyHashSet() {
        System.out.println("getUpcomingMoviesByGenre");
        HashSet<Integer> genres = new HashSet<>();
        ArrayList<ArrayList<Movie>> expResult = null;
        ArrayList<ArrayList<Movie>> result = DatabaseManager.getUpcomingMoviesByGenre(genres);
        assertEquals("An empty HashSet does not return the expected result", expResult, result);
    }
    /**
     * Test of getUpcomingMoviesByKeywordAndGenre method, of class DatabaseManager.
     * Calling the method with an HashSet containing Integers that do not represent
     * any genre should return null do distinct it from the case in which there are
     * no actual movies for a specified genre
     */
    
    @Test
    public void testGetUpcomingMoviesByGenreWithIncorrectGenres(){
        System.out.println("getUpcomingMoviesByGenreWithIncorrectGenres");
        HashSet<Integer> genres = new HashSet<>();
        genres.add(2);
        ArrayList<ArrayList<Movie>> result = DatabaseManager.getUpcomingMoviesByGenre(genres);
        assertNull("When the argument is an HashSet containing Integers that do not "
                + "represent any genre the method should return null", result);
    }
    
    /**
     * Test of getUpcomingMoviesByKeywordAndGenre method, of class DatabaseManager.
     * Searching upcoming movies with an "" keyword and a null genre the result should be
     * the same as the getUpComingMovies result
     */
    @Test
    public void testGetUpcomingMoviesByEmptyKeywordAndNotSelectedGenre() {
        System.out.println("getUpcomingMoviesByKeywordAndNotSelectedGenre");
        String keyword = "";
        Integer genre = null;
        ArrayList<ArrayList<Movie>> expResult = DatabaseManager.getUpcomingMovies();
        ArrayList<ArrayList<Movie>> result = DatabaseManager.getUpcomingMoviesByKeywordAndGenre(keyword, genre);
        if(!arrayListOfArrayListEquals(expResult, result))
            fail("When the arguments are an empty keyword and a null genre (when it is not selected) the method"
                    + "should return the same as the getUpcomingMovies method");
    }
    
    /**
     * Test of getUpcomingMoviesByKeywordAndGenre method, of class DatabaseManager.
     * Searching upcoming movies with an empty keyword the method should return all the movies
     * of that genre
     */
    @Test
    public void testGetUpcomingMoviesByEmptyKeywordAndGenre() {
        System.out.println("getUpcomingMoviesByKeywordAndGenre");
        String keyword = "";
        Integer genre = 18;
        HashSet<Integer> genres = new HashSet<>();
        genres.add(genre);
        
        ArrayList<ArrayList<Movie>> expResult = DatabaseManager.getUpcomingMoviesByGenre(genres);
        ArrayList<ArrayList<Movie>> result = DatabaseManager.getUpcomingMoviesByKeywordAndGenre(keyword, genre);
        if(!arrayListOfArrayListEquals(expResult, result))
            fail("When the arguments are an empty keyword and a selected genre the method should"
                    + "return all the upcoming movies of that genre");
    }

    /**
     * Test of getUpcomingMoviesByKeyword method, of class DatabaseManager.
     * Searching upcoming movies with an empty keyword the result should be
     * the same as the getUpcomingMovies method.
     */
    @Test
    public void testGetUpcomingMoviesByEmptyKeyword() {
        System.out.println("getUpcomingMoviesByKeyword");
        String keyword = "";
        ArrayList<ArrayList<Movie>> expResult = DatabaseManager.getUpcomingMovies();
        ArrayList<ArrayList<Movie>> result = DatabaseManager.getUpcomingMoviesByKeyword(keyword);
        if(!arrayListOfArrayListEquals(expResult, result))
            fail("When the arguments are an empty keyword and a selected genre the method should"
                    + "return the same as the getUpcomingMovies method");
    }

    /**
     * Test of getMovieByID method, of class DatabaseManager.
     */
    @Test
    public void testGetMovieByID() {
        System.out.println("getMovieByID");
        int id = 0;
        Movie expResult = null;
        Movie result = DatabaseManager.getMovieByID(id);
        assertNull("When the argument of the method is an unexistent id, the returned movie"
                + " should be null", result);
    }
    
    
    /*
    * This method compares the ArrayList<ArrayList<Movie>> objects.
    * Returns true if they are equal, false if they are not equal.
    */
    private boolean arrayListOfArrayListEquals(ArrayList<ArrayList<Movie>> aListAl1, ArrayList<ArrayList<Movie>> aListAl2){
        if(aListAl1.size() != aListAl2.size())
            return false;
        ArrayList<Movie> aL1 = aListAl1.get(0);
        ArrayList<Movie> aL2 = aListAl2.get(0);
        if(aL1.size() != aL2.size())
            return false;
        for(int i = 0; i < aL1.size(); i++){
            if(!(aL1.get(i).getId() == aL2.get(i).getId()))
                return false;
        }
        return true;
    }
    
}
