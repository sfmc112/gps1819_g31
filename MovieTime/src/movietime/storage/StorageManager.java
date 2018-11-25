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

class OpenFileException extends Exception {
    static final long serialVersionUID = 1L;
    public OpenFileException(String msg){
        super(msg);
    }
}

class ReadWriteObjectError extends Exception{
    static final long serialVersionUID = 1L;
    public ReadWriteObjectError(String msg){
        super(msg);
    }
}



public class StorageManager {
    private final static String ACCOUNTS_FILE = System.getProperty("user.home") + "\\Desktop\\xyz.dat";
    
    private static ObjectInputStream openReadRegister()
            throws OpenFileException, EOFException
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
                throw new OpenFileException("Erro ao abrir o ficheiro para leitura "
                    + ACCOUNTS_FILE + e1);
            }
            
        }catch(EOFException e) {
            throw e;
        }catch(IOException e) {
            throw new OpenFileException("Erro ao abrir o ficheiro para leitura " 
                    + ACCOUNTS_FILE + " Error: " + e);
        }
    }
    
    private static ObjectOutputStream openWriteRegister()
            throws OpenFileException
    {
        File file = new File(ACCOUNTS_FILE);
        try {
            
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ACCOUNTS_FILE));
            return out;
            
        }catch(FileNotFoundException e) {
            try{
                file.createNewFile();
                return new ObjectOutputStream(new FileOutputStream(file));
            }catch(IOException e1) {
                throw new OpenFileException("Error while openning the file to read"
                    + ACCOUNTS_FILE + e1);
            }
        }catch(IOException e) {
            throw new OpenFileException("Erro ao abrir o ficheiro para leitura " 
                    + ACCOUNTS_FILE + " Error: " + e);
        }
    }
    
    public static ArrayList<User> getUsersFromFile()
            throws OpenFileException,ReadWriteObjectError
    {
        ObjectInputStream in= null;
        
        try{
            in = openReadRegister();
            ArrayList<User> data = (ArrayList<User>) in.readObject();
            return data;
            
        }catch(EOFException e) {
            return new ArrayList<>();
            
        }catch( OpenFileException e) {
            throw new OpenFileException(e.getMessage());
            
        }catch( ClassNotFoundException e) {
            throw new ReadWriteObjectError(e.getMessage());
        } catch( IOException e) {
            throw new ReadWriteObjectError(e.getMessage());
        } finally {
            try{
                if(in != null)
                    in.close();
                
            }catch(IOException e){
                System.out.println("Error while trying to close file");
            }
        }
    }
    
    public static void addNewUser(User user)
            throws OpenFileException, ReadWriteObjectError
    {
        ObjectOutputStream out = null;
        ArrayList<User> users;
        
        try{
            
            users = getUsersFromFile();
            users.add(user);
            
            out = openWriteRegister();
            out.writeObject(users);
            
        }catch(ReadWriteObjectError e) {
            throw e;
        }catch(OpenFileException e) {
            throw e;
        }catch(IOException e) {
            throw new ReadWriteObjectError(e.getMessage());
        }finally {
            try{
                if(out != null)
                    out.close();
            }catch(IOException e){
                System.out.println("Error Closing file");
            }
        }
    }
    
    public synchronized static void changeInfoUser(User user) throws Exception{
        ArrayList<User> users;
        
        try{
            users = getUsersFromFile();
            
            for(int i = 0; i < users.size(); i++){
                if(user.getUsername().equals(users.get(i).getUsername())){
                    users.remove(i);
                    break;
                }
            }
            
            addNewUser(user);
            
        }catch(ReadWriteObjectError | OpenFileException e) {
            throw e;
        }
            
    }
}
