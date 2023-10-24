/**
 * Section : 2 Exception Handling and Assertions
 * Topic: Custom Exceptions - Custom Unchecked Examples
 */

class CustomError extends UnknownError{ //extends a subclass of Error
    CustomError(String message){
        super(message);
    }
}
class CustomArithmeticException extends ArithmeticException{ //extends a subclass of RuntimeException
    CustomArithmeticException(String message){
        super(message);
    }
}
public class UncheckedExamples {
    public static void main(String[] args) {
        UncheckedExamples uex = new UncheckedExamples();
        try{
            System.out.println("------Executing method that throes" + " CustomError");
            uex.testCustomError(0);
        } catch (CustomError e){
            System.out.println(e);
        }
        System.out.println("--------Executing method that throws Custom RuntimeException");
        uex.testCustomRuntimeException(0);
    }
    private void testCustomError(int i){
        try{
            throw new UnknownError();
        } catch (Throwable e){
            if (i == 0){
                throw new CustomError("Head scratching unknown error");
            }
        }

    }

    private void testCustomRuntimeException(int i){
        try{
            int temp = 10 / i;
        } catch (RuntimeException e){
            if (i == 0){
                throw new CustomArithmeticException("Custom Arithmetic: Divide by zero error");
            }
        }
    }
}
