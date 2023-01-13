package br.com.geradordedevs.pagarme.exceptions;

public class PagarmeException extends RuntimeException{
    public PagarmeException (String message){
        super(message);
    }
}
