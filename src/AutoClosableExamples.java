/**
 * Section : 2 Exception Handling and Assertions
 * Topic: Implementing AutoClosable
 */
class Door implements AutoCloseable{ //must implement close() which is invoked implicitly in finally block of try-with-resources

    @Override
    public void close()  {
        System.out.println("Executing close(): The door is closing");
    }

    public void doSomething() throws NullPointerException{//forcing an Exception to see what happens
        throw new NullPointerException(this.getClass().getName() + " is null");
    }
}

class Alarm implements AutoCloseable{
    @Override
    public void close()  {
        System.out.println("Executing close(): The alarm is armed");
    }
}
public class AutoClosableExamples {
    public static void main(String[] args) { //resources are closed in reverse order of their declaration
        try(Alarm a = new Alarm();
            Door d = new Door()){
            d.doSomething();
        }catch (NullPointerException ne){
            System.out.println(ne);
        }finally {
            System.out.println("Resources are closed ");
        }
    }
}
