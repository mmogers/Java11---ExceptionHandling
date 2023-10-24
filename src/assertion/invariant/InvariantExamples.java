/**
 * Section : 2 Exception Handling and Assertions
 * Topic: Using Assertions
 */
package assertion.invariant;

import java.util.ArrayList;
import java.util.Arrays;

public class InvariantExamples {
    public enum PetType{
        DOG, CAT, HAMSTER, GERBIL,PARROT
    }

    public static void main(String[] args) {
        InvariantExamples ex = new InvariantExamples();
        /*for (PetType pt : PetType.values()){
            System.out.println(ex.getPetTypeDietaryNeeds(pt));
        }*/
        for (PetType pt : PetType.values()){
            System.out.println("A juvenile "+ pt + " is called a " + ex.getPetTypeAgeDescription(pt, 0));
        }

    }

    private  String getPetTypeDietaryNeeds(PetType petType){ //assertions for a control flow invariant
        switch (petType){
            case DOG:
                return "Some formulary for dog";
            case CAT:
                return "Some formulary for cat";
            case GERBIL:
                return "Some formulary for gerbil";
            case HAMSTER:
                return "Some formulary for hamster";
            default: //control flow invariant assertion , assumption : code not reachable, all types referenced above
                assert false : "Formulary does not exist for " + petType;

        }
        return null;
    }

    //examples for pre and post condition invariantsand internal invariants
    public String getPetTypeAgeDescription(PetType petType, int petAge){
        if (petAge < 0 ) throw new IllegalArgumentException("Age cannot be negative");
        assert petAge >= 0; //bad practice : assertion to check validity of public method params
        String description = "ADULT";
        /*if (petType == PetType.CAT || petType == PetType.DOG) {
            if (petAge > 7)
                description = (petType == PetType.CAT) ? "KITTEN" : "PUPPY";
        } else {
            assert (petType == PetType.GERBIL || petType == PetType.HAMSTER); //internal invariant assertion
            if (petAge > 3) description = "SENIOR";
            else if (petAge < 1)  {
                description = "PUP";
            }
        }*/
        if (petType == PetType.CAT || petType == PetType.DOG) {
            if (petAge > 7) description = "SENIOR";
        } else if (petType == PetType.PARROT) {
            if(petAge > 45 )  description = "SENIOR";
            else if (petAge < 1) description = "CHICK";
        } else {
            assert (petType == PetType.GERBIL || petType == PetType.HAMSTER); //internal invariant assertion
            if (petAge > 3) description = "SENIOR";
            else if (petAge < 1) description = "PUP";
        }

        //good practice :post condition assertion
        assert (new ArrayList<>(Arrays.asList(
                new String[]{"ADULT", "SENIOR", "KITTEN", "PUPPY", "PUP", "CHICK"})).
                indexOf(description) > -1) : "No description exists for " +
                petType + " age " + petAge;
        /*//not so good
        ArrayList assertArrayList = new ArrayList(Arrays.asList(new String[] //creating data just for assertion, not including assertion
                {"ADULT", "SENIOR", "KITTEN", "PUPPY", "PUP", "CHICK"}));

        assert (assertArrayList.indexOf(description) > -1) : "No description exists for " + //food practice : post condition assertion
                petType + " age " + petAge;*/
        /*assert (new ArrayList<>(Arrays.asList( //changes the code  :(
                new String[]{"ADULT", "SENIOR", "KITTEN", "PUPPY", "PUP", "CHICK"})).
                indexOf(description) > -1 && petAge++ == 0) : "No description exists for " +
                petType + " age " + petAge;*/
        return description;
    }
}
