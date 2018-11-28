package movietime.storage;

import java.util.ArrayList;
import movietime.accounts.User;
import movietime.authentication.*;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

public class StorageManagerTest {

    public StorageManagerTest() {
    }
    
    @After
    public void tearDown(){
        
    }

    /**
     * Test of updateUserInfo method, of class StorageManager. If the argument
     * if the method is an User object already present in the file, the file
     * should remain unchanghed
     */
    @Test
    public void testUpdateUserInfo() throws Exception {
        System.out.println("updateUserInfo");

        try {
            AuthenticationManager.createUser("joana1234", "joana", "monica");
        } catch (OpeningFileException | ReadWriteObjectException | UserAlreadyExistsException e) {
            System.out.println(e);
        }
        ArrayList<User> expResult = StorageManager.getUsersFromFile();
        User user = new User("joana1234", "joana", "monica");
        StorageManager.updateUserInfo(user);
        ArrayList<User> result = StorageManager.getUsersFromFile();
        System.out.println(expResult);
        System.out.println(result);
        if(!arrayListEquals(result, expResult))
            fail("if the method is an User object already present in the file,"
                    + " the file should remain unchanghed");
    }
    
    /**
     * Test of updateUserInfo method, of class StorageManager. If the argument
     * if the method is an User object already present in the file, the file
     * should remain unchanghed
     */
    @Test
    public void testUpdateUserInfoWithEmptyStrings() throws Exception {
        System.out.println("updateUserInfo with empty strings");

        try {
            AuthenticationManager.createUser("marco", "marco", "marco");
        } catch (OpeningFileException | ReadWriteObjectException | UserAlreadyExistsException e) {
            System.out.println(e);
        }
        ArrayList<User> expResult = StorageManager.getUsersFromFile();
        User user = new User("marco", "", "");
        StorageManager.updateUserInfo(user);
        ArrayList<User> result = StorageManager.getUsersFromFile();
        if(!arrayListEquals(result, expResult))
            fail("if the method is an User object already present in the file,"
                    + " the file should remain unchanghed");
    }
    
    /**
     * Test of updateUserInfo method, of class StorageManager. If the argument
     * if the method is an empty User object the file should remain unchanghed
     */
//    @Test
//    public void testUpdateUserInfoWithEmptyUser() throws Exception {
//        System.out.println("updateUserInfo with empty user");
//        
//        ArrayList<User> expResult = StorageManager.getUsersFromFile();
//        User user = new User("", "", "");
//        StorageManager.updateUserInfo(user);
//        ArrayList<User> result = StorageManager.getUsersFromFile();
//        System.out.println(expResult);
//        System.out.println(result);
//        if(!arrayListEquals(result, expResult))
//            fail("if the method is an User object already present in the file,"
//                    + " the file should remain unchanghed");
//    }

    /*
    * This method compares two ArrayList<User> objects.
    * Returns true if they are equal, false otherwise.
     */
    private boolean arrayListEquals(ArrayList<User> aL1, ArrayList<User> aL2) {
        if (aL1.size() == aL2.size()) {
            int flag = 0;
            for (int i = 0; i < aL1.size(); i++) {
                for (int j = 0; j < aL2.size(); j++) {
                    if (aL1.get(i).getUsername().equals(aL2.get(j).getUsername()) &&
                            aL1.get(i).getFirstName().equals(aL2.get(j).getFirstName()) &&
                            aL1.get(i).getLastName().equals(aL2.get(j).getLastName())) {
                        flag = 1;
                    }
                }
                if (flag == 0) {
                    return false;
                }
                flag = 0;
            }
            return true;
        }
        return false;
    }

}
