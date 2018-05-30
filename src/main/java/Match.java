public class Match {
    private String dateMatch;
    private String hourMatch;
    private String team1;
    private String score;
    private String team2;
    private String placeMatch;

    public Match(){

    }

    public String tweetMatch(){
        return "Today: " + dateMatch + " at "+ hourMatch+ " in " + placeMatch + " we watched an amazing match: "+ team1+ " vs "+ team2 +
                "  with the score "+ score;
    }

    public String getDateMatch() {
        return dateMatch;
    }

    public void setDateMatch(String dateMatch) {
        this.dateMatch = dateMatch;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getPlaceMatch() {
        return placeMatch;
    }

    public void setPlaceMatch(String placeMatch) {
        this.placeMatch = placeMatch;
    }


    public String getHourMatch() {
        return hourMatch;
    }
    public void setHourMatch(String hourMatch) {
        this.hourMatch = hourMatch;
    }
}
