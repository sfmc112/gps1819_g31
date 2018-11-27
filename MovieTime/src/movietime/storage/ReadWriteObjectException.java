package movietime.storage;

public class ReadWriteObjectException extends Exception {

    static final long serialVersionUID = 1L;

    public ReadWriteObjectException(String msg) {
        super(msg);
    }
}
