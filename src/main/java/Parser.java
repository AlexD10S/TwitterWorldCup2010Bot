import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private static final int WhiteSpacesToBeMatch = 3;


    public static ArrayList<Match> getListOfMatches(String file){
        ArrayList<Match> listOfMatches = new ArrayList<Match>();
        String line;
        try {
            try (
                    InputStream fis = new FileInputStream(file);
                    //InputStream fis = new FileInputStream("/tweets.txt");
                    InputStreamReader isr = new InputStreamReader(fis, Charset.forName("Cp1252"));
                    BufferedReader br = new BufferedReader(isr);
            ) {
                while ((line = br.readLine()) != null) {
                    // Deal with the line
                    if(isMatch(line)){
                        Match match = parseMatch(line);
                        listOfMatches.add(match);
                    }


                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfMatches;
    }

    private static boolean isMatch(String lineToAnalise){
        boolean isAMatch = false;
        String [] separateLineInWhiteSpaces = lineToAnalise.split(" ");

        if(separateLineInWhiteSpaces.length > WhiteSpacesToBeMatch){
            isAMatch = true;
        }
        return isAMatch;
    }

    private static Match parseMatch(String lineToAnalise){
        Match match = new Match();
        String [] separateLineInWhiteSpaces = lineToAnalise.split(" ");

        match.setDateMatch(separateLineInWhiteSpaces[0]);
        match.setHourMatch(separateLineInWhiteSpaces[1]);

        //here is more complex, could be composed names Ej. South Korean

        if(teamWithTwoNames(separateLineInWhiteSpaces[2], separateLineInWhiteSpaces[3])){
            match.setTeam1(separateLineInWhiteSpaces[2] + " " + separateLineInWhiteSpaces[3]);
            match.setScore(separateLineInWhiteSpaces[4]);
            if(teamWithTwoNames(separateLineInWhiteSpaces[5], separateLineInWhiteSpaces[6])){
                match.setTeam2(separateLineInWhiteSpaces[5] + separateLineInWhiteSpaces[6]);
            }
            else{
                match.setTeam2(separateLineInWhiteSpaces[5]);

            }
        }
        else{
            match.setTeam1(separateLineInWhiteSpaces[2]);
            match.setScore(separateLineInWhiteSpaces[3]);
            if(teamWithTwoNames(separateLineInWhiteSpaces[4], separateLineInWhiteSpaces[5])){
                match.setTeam2(separateLineInWhiteSpaces[4] +" " +separateLineInWhiteSpaces[5]);
            }
            else{
                match.setTeam2(separateLineInWhiteSpaces[4]);
            }

        }
        match.setPlaceMatch(lineToAnalise.split("@")[1]);

        return match;
    }

    private static boolean teamWithTwoNames (String part1, String part2){
        boolean twoNames = true;
        Pattern p = Pattern.compile("(([0-9]))");
        Matcher m = p.matcher(part2);
        //For team home
        if(m.find()){
            //Check if contains a number
           twoNames = false;
        }
        //For team away
        if(part2.contains("@")){
            twoNames = false;
        }
        return twoNames;
    }
}
