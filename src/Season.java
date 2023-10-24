/**
 * Section : 2 Exception Handling and Assertions
 * Topic: Suppressed exceptions
 */
public class Season implements AutoCloseable{
    private int seasonNumber; //each season has seasonNumber
    public Season(int seasonNumber){ //constructor
        this.seasonNumber = seasonNumber;
    }

    public class Episode implements AutoCloseable{ //inner class implements Autoclosable
        private int episodeNumber; //each episode has a number
        public Episode(int episodeNumber){ //constructor
            this.episodeNumber = episodeNumber;
        }

        @Override
        public void close() throws Exception {
            System.out.println("Trying to close the episode");
            //exception for episode = 1 only
            if (this.episodeNumber == 1) throw new RuntimeException("Closing failed on episode");
            System.out.println("Closed the episode");
        }
        public void run(){
            System.out.println("Running Episode " + this.episodeNumber);
            if (this.episodeNumber == 1) throw new RuntimeException("Episode failed");
        }
    }
    @Override
    public void close() throws RuntimeException {
        System.out.println("Trying to close the season");
        if (this.seasonNumber == 1) throw new RuntimeException("Closing failed on season");
        System.out.println("Closed the season");
    }
    public void run(){
        System.out.println("Running series " + this.seasonNumber);
        if (this.seasonNumber == 1) throw new RuntimeException("Season Failed");
    }

    /*public static void main(String[] args) throws Exception {
        //main method kicks off the first season & episode using try-with-resources statement with 2 autoclosable resources
        try (
                Season s = new Season(1);
                Season.Episode e = s.new Episode(1)) {
            s.run();
            e.run();
        } catch (Exception e){
            System.out.println("ERROR CAUGHT " + e); //shows only one RuntimeEx - suppressed

            for(Throwable t: e.getSuppressed()){
                System.out.println("SUPPRESSED " + t); //will show all 3
            }
        }
    }*/
    public static void main(String[] args) throws Exception {
        Throwable addedSuppressed = null; //local var of type throwable
        //main method kicks off the first season & episode using try-with-resources statement with 2 autoclosable resources
        try (
                Season s = new Season(1);
                Season.Episode e = s.new Episode(1)) {
            try {
                s.run();
            }catch (Exception ex){
                addedSuppressed = ex; //set the caught exception to a local var
            }
            e.run();
        } catch (Exception e){
            System.out.println("ERROR CAUGHT " + e); //shows only one RuntimeEx - suppressed
            //adding suppressed exceptions
            if(addedSuppressed != null) e.addSuppressed(addedSuppressed);
            for(Throwable t: e.getSuppressed()){
                System.out.println("SUPPRESSED " + t); //will show all 3
            }
        }
    }

}

