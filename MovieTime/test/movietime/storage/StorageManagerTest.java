package movietime.storage;

import java.io.File;
import java.util.ArrayList;
import movietime.accounts.User;
import movietime.authentication.*;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class StorageManagerTest {

    public StorageManagerTest() {
    }

    /**
     * Test of updateUserInfo method, of class StorageManager. If the argument
     * if the method is an User object already present in the file, the file
     * should remain unchanghed
     */
    
    @Before
    public void cleanFile(){
        File a = new File(".\\data\\MovieTimeUsers.bin");
        a.delete();
    }
    
   @Test
    public void testUpdateUserInfo_updatePreferences() throws Exception {
        System.out.println("updateUserInfo");

        try {
            AuthenticationManager.createUser("userino", "user", "mac");
        } catch (OpeningFileException | ReadWriteObjectException |
                UserAlreadyExistsException e) 
        {
            System.out.println(e);
        }
        
        User expResult = StorageManager.getUsersFromFile().get(0);
        
        expResult.addFavoriteMovie(1234);
        expResult.addFavoriteMovie(12345);
        expResult.addFavoriteMovie(123456);
        
        expResult.getPreferences().setDaysToAlert(20);
        expResult.getPreferedGenres().add(28);
        
        StorageManager.updateUserInfo(expResult);
        User result = StorageManager.getUsersFromFile().get(0);
        System.out.println(result.getPreferedGenres());
        System.out.println(result.getPreferences().getDaysToAlert());
        
        assertEquals(expResult, result);
    }
    
    
    @Test
    public void testAddNewUser(){
        System.out.println("addUser");
        
        User user = new User("Djanga","d","a");
        
        try{
            ArrayList<User> users = StorageManager.getUsersFromFile();
            int expSize = users.size()+1;
            
            StorageManager.addNewUser(user);
        
            ArrayList<User> usersAfter = StorageManager.getUsersFromFile();
            int size = usersAfter.size();
            User result = usersAfter.get(size-1);

            assertEquals(users.size()+1,usersAfter.size());

        }catch(OpeningFileException | ReadWriteObjectException e){
            e.printStackTrace();
        }
    }
    
    
    @Test
    public void testGetUsersFromFile(){
        System.out.println("getUsersFromFile");
        
        try{
            System.out.println(StorageManager.getUsersFromFile());
            
            assertEquals(2,StorageManager.getUsersFromFile().size());
        }catch(Exception e){   
        }
    }
    
    private boolean isOutputEqual(ArrayList<User> a,ArrayList<User> b){
        if(a.size() != b.size())
            return false;
        
        for(int i = 0; i < a.size(); i++){
            if(!a.get(i).equals(b.get(i)))
                return false;
        }
        
        return true;
    }
}