/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movietime.storage;


public class OpenningFileException extends Exception {
    static final long serialVersionUID = 1L;
    public OpenningFileException(String msg){
        super(msg);
    }
}