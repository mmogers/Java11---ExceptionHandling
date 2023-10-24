/**
 * Section : 2 Exception Handling and Assertions
 * Topic: try-with-resources: Suppressed exceptions
 */
public class SeasonTest {
    public static void main(String[] args) throws Exception {
        //try-with-resources
        try(Season s = new Season(1)){

            for(int i = 0 ; i< 2 ; i++){
                //nested try-with-resources extended- including catch
                try(s; //passing the var for season
                    Season.Episode e = s.new Episode(i +1)){
                    e.run();
                } catch (Exception ex){
                    ex.printStackTrace(System.out); //will print ex with supressed ex info
                }
            }
        }
    }
}
