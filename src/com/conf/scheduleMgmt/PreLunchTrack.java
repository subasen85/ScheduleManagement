package com.conf.scheduleMgmt;

import java.util.ArrayList;
import java.util.List;

import com.conf.scheduleMgmt.Utils.TimeUtils;

public class PreLunchTrack {
	private List<Session> preLunchSessions;
	private int remaningDuration;
	private boolean isLunchScheduled;

	public PreLunchTrack() {
		super();
		this.preLunchSessions = new ArrayList<Session>();
		this.remaningDuration = 180;
	}

	/**
	 * @return the preLunchSessions
	 */
	public List<Session> getPreLunchSessions() {
		return preLunchSessions;
	}

	/**
	 * @param preLunchSessions
	 *            the preLunchSessions to set
	 */
	public void setPreLunchSessions(List<Session> preLunchSessions) {
		this.preLunchSessions = preLunchSessions;
	}

	/**
	 * @return the remaningDuration
	 */
	public int getRemaningDuration() {
		return remaningDuration;
	}

	/**
	 * @param remaningDuration
	 *            the remaningDuration to set
	 */
	public void setRemaningDuration(int remaningDuration) {
		this.remaningDuration = remaningDuration;
	}

	/**
	 * @return the isLunchScheduled
	 */
	public boolean isLunchScheduled() {
		return isLunchScheduled;
	}

	/**
	 * @param isLunchScheduled
	 *            the isLunchScheduled to set
	 */
	public void setLunchScheduled(boolean isLunchScheduled) {
		this.isLunchScheduled = isLunchScheduled;
	}
/**
 * 
 * @param talk
 * @return
 * Schedule the Task before the Lunch
 */
	public boolean scheduletalk(Talk talk) {
		Session currentSession = new Session();
		if (talk.getDuration() <= remaningDuration) {

			currentSession.setSessionTitle(talk.getName());
			currentSession.setDuration(talk.getDuration() + "min");
			if (getPreLunchSessions().size() == 0) {
				currentSession.setStartTime("09:00");
				currentSession.setEndTime(TimeUtils
						.convertTimeToString(TimeUtils.addMins("09:00", talk
								.getDuration())));
			} else {
				currentSession.setEndTime(TimeUtils
						.convertTimeToString(TimeUtils.addMins(
								getPreLunchSessions().get(
										getPreLunchSessions().size() - 1)
										.getEndTime(), talk.getDuration())));
				currentSession.setStartTime(getPreLunchSessions().get(
										getPreLunchSessions().size() - 1)
										.getEndTime());
			}
			getPreLunchSessions().add(currentSession);
			remaningDuration -= talk.getDuration();

			return true;
		} else if (!isLunchScheduled())
			scheduleLunch(currentSession);

		return false;
	}
	
	/**
	 * 
	 * @param currentSession
	 * Method to include the Lunch Timing
	 */

	private void scheduleLunch(Session currentSession) {
		// TODO Auto-generated method stub

		currentSession.setSessionTitle("Lunch Time");
		currentSession.setDuration("60 min");
		currentSession.setStartTime("12:00");
		currentSession.setEndTime("13:00");
		getPreLunchSessions().add(currentSession);
		setLunchScheduled(true);

	}

}
