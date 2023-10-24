/**
 * Section : 2 Exception Handling and Assertions
 * Topic: Custom Exceptions
 */

class CustomException extends Exception{ //custom ex extend ex (checked)
    private static final int ERROR_CODE = 9876;//add ERROR CODE for own purposes, maybe logging
    CustomException(String message, Throwable cause, boolean writableStackTrace){
        super(message + " (" + ERROR_CODE + ")", cause, false, writableStackTrace); //call super making a 3 arg enableSuppression = false
    }
}
class CustomThrowable extends Throwable{ //custom exception (checked)
    private static final int ERROR_CODE = 1234; //can have different members as any other class

    CustomThrowable(){ //default constructor, null message

    }
    CustomThrowable(String message){
        super(message + " (" + ERROR_CODE + ")"); //customize the constructor
    }

    CustomThrowable(String message, Throwable e){
        super(message + "( " + ERROR_CODE + ")", e);
        doSomethingSpecial(); //want custom exception of this type do something special
    }

    void doSomethingSpecial(){
        System.out.println("[First] Send alert, log or persist information");
    }
}
public class CheckedExamples {
    /*public static void main(String[] args) throws Throwable { //to exercise both methods
        CheckedExamples cex = new CheckedExamples();

        System.out.println("---------------Custom Throwable Handled-------------");
        //try/catch not required since method handles exception
        cex.handleThrowable();

        try{
            System.out.println("---------------Custom Throwable Thrown/Caught-----------");
            cex.throwThrowable(0);
        }catch (CustomThrowable c){
            c.printStackTrace(System.out);
        }
        System.out.println("----------------Custom Throwable Thrown/Not Caught------------");
        cex.throwThrowable(1);
    }*/
    public static void main(String[] args) throws CustomException { //to exercise both methods
        CheckedExamples cex = new CheckedExamples();

        System.out.println("---------------Custom Throwable Handled-------------");
        //try/catch not required since method handles exception
        cex.handleThrowable();

        try{
            System.out.println("---------------Custom Exception Thrown/Caught-----------");
            cex.testExceptionNotHandled(0);
        }catch (CustomException c){
            c.printStackTrace(System.out);
        }
        System.out.println("----------------Custom Exception Thrown/Not Caught------------");
        cex.testExceptionNotHandled(1);
    }

    private void throwThrowable(int i) throws CustomThrowable{ //this method doesn't handle the custom exc thrown
        try{
            throw new IllegalStateException("Woops, This is bad"); //customizing existing exc with message
        }catch(Exception e){
            if (i == 0){
                throw new CustomThrowable("Caught and re-Thrown ", e); //calls constructor on CustomThrowable that takes msg and ex
            }
            if (i == 1){
                throw new CustomThrowable("Created and Thrown"); // calls constructor on CustomThrowable that takes a message
            }
        }

    }
    private void handleThrowable(){ //this method handles the checked ex it throws
        try{
            throw new CustomThrowable(); //creating with no args constructor and throwing
        } catch (CustomThrowable c){
            c.printStackTrace(System.out); //handles all CustomThrowable thrown within it
        }
    }

    private void testExceptionNotHandled(int i) throws CustomException{ //method does not handle custom exception thrown
        try{
            throw new IllegalStateException("Ouch - Bug");
        } catch (Exception e){
            if (i == 0){
                throw new CustomException("Custom Exception" , e, false); //calls constructor on CustomException that takes message , originating Exception object and boolean for stacktrace
            }else{
                throw new CustomException("Custom Exception" , e, true);
            }
        }
    }
}
