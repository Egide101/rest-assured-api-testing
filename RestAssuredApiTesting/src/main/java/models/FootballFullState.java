package models;

import java.util.List;

public class FootballFullState {
	private String homeTeam;	
	private String awayTeam;	
	private Boolean finished;	
	private int	gameTimeInSeconds;
	private List<Goal> goals;
	private String period;
	private List<String> possibles;
	private List<String> corners;
	private List<String> redCards;
	private List<String> yellowCards;
    private String startDateTime;
    private Boolean started;
    private List<Team> teams;
    
	public String getHomeTeam() {
		return homeTeam;
	}
	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}
	public String getAwayTeam() {
		return awayTeam;
	}
	public void setAwayTeam(String awayTeam) {
		this.awayTeam = awayTeam;
	}
	public Boolean getFinished() {
		return finished;
	}
	public void setFinished(Boolean finished) {
		this.finished = finished;
	}
	public int getGameTimeInSeconds() {
		return gameTimeInSeconds;
	}
	public void setGameTimeInSeconds(int gameTimeInSeconds) {
		this.gameTimeInSeconds = gameTimeInSeconds;
	}
	public List<Goal> getGoals() {
		return goals;
	}
	public void setGoals(List<Goal> goals) {
		this.goals = goals;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public List<String> getPossibles() {
		return possibles;
	}
	public void setPossibles(List<String> possibles) {
		this.possibles = possibles;
	}
	public List<String> getCorners() {
		return corners;
	}
	public void setCorners(List<String> corners) {
		this.corners = corners;
	}
	public List<String> getRedCards() {
		return redCards;
	}
	public void setRedCards(List<String> redCards) {
		this.redCards = redCards;
	}
	public List<String> getYellowCards() {
		return yellowCards;
	}
	public void setYellowCards(List<String> yellowCards) {
		this.yellowCards = yellowCards;
	}
	public String getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(String startDateTime) {
		this.startDateTime = startDateTime;
	}
	public Boolean getStarted() {
		return started;
	}
	public void setStarted(Boolean started) {
		this.started = started;
	}
	public List<Team> getTeams() {
		return teams;
	}
	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}
}
