/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movietime.database;

import java.util.ArrayList;
import java.util.HashSet;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author salex
 */
public class MovieTest {
    
    public MovieTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getDirector method, of class Movie.
     */
    @Test
    public void testGetDirector() {
        System.out.println("getDirector");
        Movie instance = null;
        String expResult = "";
        String result = instance.getDirector();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getId method, of class Movie.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Movie instance = null;
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTitle method, of class Movie.
     */
    @Test
    public void testGetTitle() {
        System.out.println("getTitle");
        Movie instance = null;
        String expResult = "";
        String result = instance.getTitle();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRelease_date method, of class Movie.
     */
    @Test
    public void testGetRelease_date() {
        System.out.println("getRelease_date");
        Movie instance = null;
        String expResult = "";
        String result = instance.getRelease_date();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGenres method, of class Movie.
     */
    @Test
    public void testGetGenres() {
        System.out.println("getGenres");
        Movie instance = null;
        HashSet<Integer> expResult = null;
        HashSet<Integer> result = instance.getGenres();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOverview method, of class Movie.
     */
    @Test
    public void testGetOverview() {
        System.out.println("getOverview");
        Movie instance = null;
        String expResult = "";
        String result = instance.getOverview();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPoster method, of class Movie.
     */
    @Test
    public void testGetPoster_String() {
        System.out.println("getPoster");
        String size = "";
        Movie instance = null;
        String expResult = "";
        String result = instance.getPoster(size);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBackdrop method, of class Movie.
     */
    @Test
    public void testGetBackdrop_String() {
        System.out.println("getBackdrop");
        String size = "";
        Movie instance = null;
        String expResult = "";
        String result = instance.getBackdrop(size);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPoster method, of class Movie.
     */
    @Test
    public void testGetPoster_0args() {
        System.out.println("getPoster");
        Movie instance = null;
        String expResult = "";
        String result = instance.getPoster();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBackdrop method, of class Movie.
     */
    @Test
    public void testGetBackdrop_0args() {
        System.out.println("getBackdrop");
        Movie instance = null;
        String expResult = "";
        String result = instance.getBackdrop();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getActors method, of class Movie.
     */
    @Test
    public void testGetActors() {
        System.out.println("getActors");
        Movie instance = null;
        ArrayList<String> expResult = null;
        ArrayList<String> result = instance.getCast();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Movie.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Movie instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
