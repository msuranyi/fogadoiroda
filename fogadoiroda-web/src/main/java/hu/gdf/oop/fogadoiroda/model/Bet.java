package hu.gdf.oop.fogadoiroda.model;

public class Bet extends BaseEntity {

	private int userId;

	private int eventId;

	private int outcomeId;

	private int betAmount;

	private User user;

	private Event event;

	private Outcome outcome;

	public Bet() {
	}

	public Bet(int userId, int eventId, int outcomeId, int betAmount) {
		this.userId = userId;
		this.eventId = eventId;
		this.outcomeId = outcomeId;
		this.betAmount = betAmount;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public int getOutcomeId() {
		return outcomeId;
	}

	public void setOutcomeId(int outcomeId) {
		this.outcomeId = outcomeId;
	}

	public int getBetAmount() {
		return betAmount;
	}

	public void setBetAmount(int betAmount) {
		this.betAmount = betAmount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Outcome getOutcome() {
		return outcome;
	}

	public void setOutcome(Outcome outcome) {
		this.outcome = outcome;
	}

	@Override
	public String getIdentifier() {
		return null;
	}

}