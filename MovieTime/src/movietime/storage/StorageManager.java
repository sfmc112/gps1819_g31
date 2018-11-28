package movietime.storage;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import movietime.accounts.User;

public class StorageManager {

    private final static String ACCOUNTS_FILE = ".\\data\\MovieTimeUsers.bin";
    
    /**
     * It's purpose is to open the file that contains the accounts information,
     * in case the file is not created (meaning that nobody has registered yet),
     * the method also creates the accounts file.
     * 
     * @return ObjectInputStream  -> object that is ready to be readen from
     * the file
     * @throws OpenningFileException
     * @throws EOFException 
     */
    private synchronized static ObjectInputStream openReadRegister()
            throws OpeningFileException, EOFException
    {
        File file = new File(ACCOUNTS_FILE);
        try {
            return new ObjectInputStream(new FileInputStream(file));
            
        }catch(FileNotFoundException e){
            
            try{
                file.createNewFile();
                return new ObjectInputStream(new FileInputStream(file));
            }catch(EOFException e1) {
                throw e1;
            }catch(IOException e1) {
                throw new OpeningFileException("Error while opening"
                        + " the file for reading "
                    + ACCOUNTS_FILE + e1);
            }
            
        }catch(EOFException e) {
            throw e;
        }catch(IOException e) {
            throw new OpeningFileException("Error while opening "
                    + "the file for reading " 
                    + ACCOUNTS_FILE + " Error: " + e);
        }
    }
    
    /**
     * It's purpose is to open the file that contains the accounts information 
     * to write on it,in case the file is not created, the method also creates 
     * the accounts file.
     * 
     * @return ObjectOutputStream -> object that is ready to be wirtten in 
     * the file
     * @throws OpenningFileException 
     */
    private synchronized static ObjectOutputStream openWriteRegister()
            throws OpeningFileException
    {
        File file = new File(ACCOUNTS_FILE);
        try {
            
            ObjectOutputStream out = new ObjectOutputStream(
                                     new FileOutputStream(ACCOUNTS_FILE));
            return out;
            
        }catch(FileNotFoundException e) {
            try{
                file.createNewFile();
                return new ObjectOutputStream(new FileOutputStream(file));
            }catch(IOException e1) {
                throw new OpeningFileException("Error while openning the file to read"
                    + ACCOUNTS_FILE + e1);
            }
        }catch(IOException e) {
            throw new OpeningFileException("Erro ao abrir o ficheiro para leitura " 
                    + ACCOUNTS_FILE + " Error: " + e);
        }
    }
    
    /**
     * This method reads the accounts' file and retrieves the users' array that 
     * it contains
     * 
     * @return ArrayList<User>
     * @throws movietime.storage.OpeningFileException
     * @throws OpenningFileException
     * @throws ReadWriteObjectException 
     */
    public synchronized static ArrayList<User> getUsersFromFile()
            throws OpeningFileException,ReadWriteObjectException
    {
        ObjectInputStream in= null;
        
        try{
            in = openReadRegister();
            ArrayList<User> data = (ArrayList<User>) in.readObject();
            return data;
            
        }catch(EOFException e) {
            return new ArrayList<>();
            
        }catch( OpeningFileException e) {
            throw new OpeningFileException(e.getMessage());
            
        }catch( ClassNotFoundException e) {
            throw new ReadWriteObjectException(e.getMessage());
        } catch( IOException e) {
            throw new ReadWriteObjectException(e.getMessage());
        } finally {
            try{
                if(in != null)
                    in.close();
                
            }catch(IOException e){
                System.out.println("Error while trying to close file");
            }
        }
    }
    
    /**
     * This method retrieves the users' array, add the new user in the array and
     * saves the updated array in the file
     * 
     * @param user
     * @throws OpenningFileException
     * @throws ReadWriteObjectException 
     */
    public synchronized static void addNewUser(User user)
            throws OpeningFileException, ReadWriteObjectException
    {
        ObjectOutputStream out = null;
        ArrayList<User> users;
        
        try{
            
            users = getUsersFromFile();
            users.add(user);
            
            new FileOutputStream(ACCOUNTS_FILE).close();

            out = openWriteRegister();
            out.writeObject(users);
            
        }catch(ReadWriteObjectException | OpeningFileException e) {
            throw e;
        }catch(IOException e) {
            throw new ReadWriteObjectException(e.getMessage());
        }finally {
            try{
                if(out != null)
                    out.close();
            }catch(IOException e){
                System.out.println("Error Closing file");
            }
        }
    }
    
    /**
     * Retrieves the ArrayLists of registered users, checks if the user it 
     * receives by parameter is in the array and if so deletes it from the array.
     * It then adds the updated user (received via parameter) in the array and
     * saves the array in the file
     * 
     * @param user
     * @throws ReadWriteObjectException 
     */
    public synchronized static void updateUserInfo(User user) 
            throws ReadWriteObjectException,OpeningFileException
    {
        ObjectOutputStream out = null;
        ArrayList<User> users;
        
        try{
            users = getUsersFromFile();
            
            for(int i = 0; i < users.size(); i++){
                if(user.getUsername().equals(users.get(i).getUsername())){
                    users.remove(i);
                    break;
                }
            }
            
            new FileOutputStream(ACCOUNTS_FILE).close();
            users.add(user);
            //addNewUser(user);
            out = openWriteRegister();
            out.writeObject(users);
            
        }catch(IOException e) {
            System.err.println("Test error :x");
        }catch(ReadWriteObjectException | OpeningFileException e) {
            throw e;
        }finally {
            try{
                if(out != null)
                    out.close();
            }catch(IOException e){
                System.out.println("Error Closing file");
            }
        }       
    }
}
