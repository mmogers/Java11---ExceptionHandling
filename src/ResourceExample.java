import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Section : 2 Exception Handling and Assertions
 * Topic: Use try-with-resources construct
 */
public class ResourceExample {
    private String getPropertyFromFile(String filename, String propertyName)  //method demonstrate problems with try-catch-finally clauses
            throws IOException{
        String property = null;
        BufferedReader br = null;
        try{ //if file not found this line will throw the exception and the var will remain null
            br = new BufferedReader(new FileReader(filename));
            String line;
            String name;
            while((line = br.readLine()) != null){
                name = line.split("\\s")[0];
                if(name.equals(propertyName)){
                    property = line.split("\\s")[1];
                }
            }
        } catch (Exception e){
            System.out.println("--------Actual Code Throws this error: ");
            e.printStackTrace();
            throw e;
        } finally {
            br.close(); //br is null if file doesn't exist so following statement generates new error
        }
        return property;
    }
    //this method shows try-with-resource feature
    private String getPropertyValueFromFile(String filename, String propertyName) throws IOException{
        String property = null;
        //in a try-with-resource construct the resource automatically closed
        try(BufferedReader br = new BufferedReader(new FileReader(filename))){
            String line;
            String name;
            while((line = br.readLine()) != null){
                name = line.split("\\s")[0];
                if(name.equals(propertyName)){
                    property = line.split("\\s")[1];
                }
            }
        }
        return property;
    } //no catch or finally here


    public static void main(String[] args) {
        ResourceExample rex = new ResourceExample();
        String propertyName = "FIRST";
        /*try{
            String property = rex.getPropertyFromFile("properties.txt", propertyName);
            System.out.println("The value of the property " + propertyName + " = " + property);
        } catch (Exception e){
            System.out.println("--------Surrounding Code Sees this Error: ");
            e.printStackTrace(); //printing exception confirms that exception thrown NOT FileNotFoundException but NullPointerException
        }*/
        try{
            String property = rex.getPropertyValueFromFile("properties.txt", propertyName);//no nullpointerException here
            System.out.println("The value of the property " + propertyName + " = " + property);
        } catch (Exception e){
            System.out.println("--------Surrounding Code Sees this Error: ");
            e.printStackTrace(); //printing exception confirms that exception thrown NOT FileNotFoundException but NullPointerException
        }
    }
}
