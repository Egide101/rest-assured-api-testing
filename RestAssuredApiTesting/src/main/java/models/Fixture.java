package models;

public class Fixture {
	
	private String fixtureId;
	private Status fixtureStatus;
	private FootballFullState footballFullState;
	
	public String getFixtureId() {
		return fixtureId;
	}
	public void setFixtureId(String fixtureId) {
		this.fixtureId = fixtureId;
	}
	public Status getFixtureStatus() {
		return fixtureStatus;
	}
	public void setFixtureStatus(Status fixtureStatus) {
		this.fixtureStatus = fixtureStatus;
	}
	public FootballFullState getFootballFullState() {
		return footballFullState;
	}
	public void setFootballFullState(FootballFullState footballFullState) {
		this.footballFullState = footballFullState;
	}
}
