package models;

public class Goal {
    private int clockTime;
    private Boolean confirmed;
    private int id;
    private Boolean ownGoal;
    private Boolean  penalty;
    private String period;
    private int playerId;
    private String teamId;
    
	public int getClockTime() {
		return clockTime;
	}
	public void setClockTime(int clockTime) {
		this.clockTime = clockTime;
	}
	public Boolean getConfirmed() {
		return confirmed;
	}
	public void setConfirmed(Boolean confirmed) {
		this.confirmed = confirmed;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Boolean getOwnGoal() {
		return ownGoal;
	}
	public void setOwnGoal(Boolean ownGoal) {
		this.ownGoal = ownGoal;
	}
	public Boolean getPenalty() {
		return penalty;
	}
	public void setPenalty(Boolean penalty) {
		this.penalty = penalty;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
}
