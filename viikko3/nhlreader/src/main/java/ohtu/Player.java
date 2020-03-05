
package ohtu;

public class Player {
    
    private String name;
    private String team;
    private int goals;
    private int assists;
    private String nationality;

    public void setName(String name) {
        this.name = name;
    }
    
    public void setTeam(String team) {
        this.team = team;
    }
    
    public void setGoals(int goals) {
        this.goals = goals;
    }
    
    public void setAssists(int assists) {
        this.assists = assists;
    }
    
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getName() {
        return this.name;
    }
    
    public String getTeam() {
        return this.team;
    }
    
    public int getGoals() {
        return this.goals;
    }
    
    public int getAssists() {
        return this.assists;
    }
    
    public String getNationality() {
        return this.nationality;
    }
    
    public void printPlayer() {
        System.out.printf("%20s %5s %10s %2s%n", name, team, goals + " + " + assists, " = " + (goals + assists));
    }
}
