package com.conf.scheduleMgmt;

public class Talk implements Comparable<Talk> {
	private String name;
	private int duration;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * Sorting the talk in ascending order based on the duration
	 */
	@Override
	public int compareTo(Talk talkDetails) {

		return this.getDuration() - talkDetails.getDuration();
	}
}
