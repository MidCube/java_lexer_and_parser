package main.java.cig23.cam.ac.uk.SummerCompiler;

public class getFloat {
    public static String getFloat(String s, int i) throws Main.LexingException {
        int j = i;

        if (Character.isLetter(s.charAt(j)) || (!Character.isDigit(s.charAt(j)) && (s.charAt(j) !='-' && s.charAt(j) != '.' ))) {
            //error not a number
            throw new Main.LexingException("Characters that weren't cos appeared in the input");
        }

        //It will accept -0 as a token which maybe I should remove but
        //isn't too bad for now
        //If it is a negative number
        if (s.charAt(j) == '-') {

            j++;

        }
        //If the first digit is 0 then it must be 0 or 0. or 0.x
        if (s.charAt(j) == '0'){

            if (j == s.length()-1) {
                j++;
                //last thing was a leading 0
                return s.substring(i, j);
            } else if (s.charAt(j+1) != '.') {
                //it is just 0
                j++;
                return s.substring(i, j);
            } else if (s.charAt(j+1) == '.') {
                //It is 0. or 0.x
                j++;
                j++;
            }
        } else {
            //It at least starts with normal number
            for( ; j < s.length(); ) {
                if(Character.isDigit(s.charAt(j))) {
                    j++;
                } else if (s.charAt(j) == '.') {
                    j++;
                    break;
                } else {
                    if (i==j-1 && s.charAt(j-1)=='-') {
                        throw new Main.LexingException("A - on its own is not a valid float.");
                    } else {
                        return s.substring(i, j);
                    }
                }
            }
        }

        //Find out what comes after decimal point
        for( ; j < s.length(); ) {
            if(Character.isDigit(s.charAt(j))) {
                j++;
            } else if (s.charAt(j) == '.') {
                //Throw an error, multiple .
                throw new Main.LexingException("There are multiple . in one of your numbers");
            } else {
                return s.substring(i, j);
            }
        }
        return s.substring(i, j);


    }
}
