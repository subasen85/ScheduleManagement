package com.conf.scheduleMgmt;

import java.util.ArrayList;
import java.util.List;

import com.conf.scheduleMgmt.Utils.TimeUtils;
import com.conf.scheduleMgmt.constants.ConfSchMgmtConstants;
//Added comments from Aruna System.
public class PostLunchTrack {

	private List<Session> postLunchSessions;
	private int remaningDuration;
	public boolean isNetworkScheduled;

	public PostLunchTrack() {
		super();
		this.postLunchSessions = new ArrayList<Session>();
		this.remaningDuration = 240;
	}

	/**
	 * @return the isNetworkScheduled
	 */
	public boolean isNetworkScheduled() {
		return isNetworkScheduled;
	}

	/**
	 * @param isNetworkScheduled
	 *            the isNetworkScheduled to set
	 */
	public void setNetworkScheduled(boolean isNetworkScheduled) {
		this.isNetworkScheduled = isNetworkScheduled;
	}

	/**
	 * @return the postLunchSessions
	 */
	public List<Session> getPostLunchSessions() {
		return postLunchSessions;
	}

	/**
	 * @param postLunchSessions
	 *            the postLunchSessions to set
	 */
	public void setPostLunchSessions(List<Session> postLunchSessions) {
		this.postLunchSessions = postLunchSessions;
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
	 * 
	 * @param talk
	 * @return
	 */
	public boolean scheduletalk(Talk talk) {
		Session currentSession = new Session();
		if (talk.getDuration() <= remaningDuration) {

			currentSession.setSessionTitle(talk.getName());
			currentSession.setDuration(String.valueOf(talk.getDuration()));
			if (getPostLunchSessions().size() == 0) {
				currentSession.setStartTime(ConfSchMgmtConstants.LUNCH_START_TIME);
				currentSession.setEndTime(TimeUtils
						.convertTimeToString(TimeUtils.addMins(ConfSchMgmtConstants.LUNCH_END_TIME,
								talk.getDuration())));
			} else {
				currentSession.setEndTime(TimeUtils
						.convertTimeToString(TimeUtils.addMins(
								getPostLunchSessions().get(
										getPostLunchSessions().size() - 1)
										.getEndTime(), talk.getDuration())));
				currentSession.setStartTime(getPostLunchSessions().get(
						getPostLunchSessions().size() - 1).getEndTime());
			}
			getPostLunchSessions().add(currentSession);
			remaningDuration -= talk.getDuration();
			return true;
		} else if (!isNetworkScheduled()) {
			scheduleNetwork();
		}
		return false;
	}

	/**
	 * Method to inlcude the Network at the End
	 */
	public void scheduleNetwork() {
		// TODO Auto-generated method stub
		Session currentSession = new Session();

	currentSession.setSessionTitle(ConfSchMgmtConstants.NETWORK_TIME);
		currentSession.setDuration("");
		if (TimeUtils.isGreaterThan4PM(getPostLunchSessions().get(
				getPostLunchSessions().size() - 1).getEndTime())) {
			currentSession.setStartTime(ConfSchMgmtConstants.NETWORK_START_TIME);
		} else {
			currentSession.setStartTime(getPostLunchSessions().get(
					getPostLunchSessions().size() - 1).getEndTime());
		}
		currentSession.setEndTime(ConfSchMgmtConstants.NETWORK_END_TIME);
		getPostLunchSessions().add(currentSession);
		setNetworkScheduled(true);

	}

}
