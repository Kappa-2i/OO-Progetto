package EXCEPTIONS;

//Eccezione personalizzata
public class MyExc extends Exception{

    public MyExc() {
        super();
    }

    public MyExc(String message) {
        super(message);
    }
}
