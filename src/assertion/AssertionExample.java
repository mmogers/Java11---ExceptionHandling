package assertion;

/**
 * Section c : Exception Handling and Assertions
 * Topic: Using Assertions
 */
public class AssertionExample {

    static {
        boolean startupAllowed = false;
        assert startupAllowed = true; //startupAllowed will be true only if assertions are enabled, otherwise its ignored
        if (!startupAllowed){
            throw new RuntimeException("Assertions must be enabled !!!"); //the statement prevents the class from loading
        }
    }

    enum assertFlags{ //maintains detailed messages for Assertion Errors
        BYTE_OUT_OF_RANGE("Value is out of range for a byte");
        private String message;

        assertFlags(String message){
            this.message = message;
        }
    }
    public static String getAssertMessage(assertFlags aflag){
        return "Method returns " + aflag.message;
    }
    public static void printAssertMessage(assertFlags aflag){
        System.out.println("Method returns " + aflag.message);
    }
    public static void main(String[] args) {
        byte s2;
        short maxValue = Byte.MAX_VALUE + 1; //value exceeds max value
/*
        assert maxValue >= Byte.MIN_VALUE && maxValue <= Byte.MAX_VALUE : "Value out of range for byte"; //assert the value within the proper range
*/
        assert maxValue >= Byte.MIN_VALUE && maxValue <= Byte.MAX_VALUE : getAssertMessage(assertFlags.BYTE_OUT_OF_RANGE); //more beautiful
        //assert maxValue >= Byte.MIN_VALUE && maxValue <= Byte.MAX_VALUE : printAssertMessage(assertFlags.BYTE_OUT_OF_RANGE); //void method is not allowed
        //assert maxValue >= Byte.MIN_VALUE && maxValue <= Byte.MAX_VALUE : assertFlags.BYTE_OUT_OF_RANGE; //BYTE_OUT_OF_RANGE
        s2 = (byte) maxValue; //casting short to byte can lead to undetected overflow...
        System.out.println("Short value = " + maxValue); //actual expected value
        System.out.println("Byte value = " + s2); //max value overflowed

    }
}
