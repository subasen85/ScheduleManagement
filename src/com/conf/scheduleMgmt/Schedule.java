package com.conf.scheduleMgmt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.conf.scheduleMgmt.Outputs.IScheduleOutput;

public class Schedule {

	
	List<Track> listTrackDetails;

	public Schedule() {
		super();
		listTrackDetails = new ArrayList<Track>();
	}

	
	/**
	 * @return the listTrackDetails
	 */
	public List<Track> getListTrackDetails() {
		return listTrackDetails;
	}


	/**
	 * @param listTrackDetails the listTrackDetails to set
	 */
	public void setListTrackDetails(List<Track> listTrackDetails) {
		this.listTrackDetails = listTrackDetails;
	}


	/**
	 * @param talk
	 *            This method will identify in which track the talk needs to be
	 *            scheduled.
	 */
	public boolean scheduleTalk(Talk talk) {

		Track latestTrack = this.getLatestTrack();
		
		return latestTrack.scheduleTalk(talk);
		

	}

	/**
	 * 
	 * @return
	 * To Return the latest Track
	 */
	private Track getLatestTrack() {
		if (listTrackDetails.isEmpty()
				|| listTrackDetails.get(listTrackDetails.size() - 1)
						.isTrackFull()) {
			listTrackDetails.add(new Track());
		} 
			return listTrackDetails.get(listTrackDetails.size() - 1);
		

	}
	/**
	 * Set the Track Full
	 */
	
	public void setTrackFull(){
		listTrackDetails.get(listTrackDetails.size()-1).setTrackFull(true);
	}

/**
 * Method to include the network timing to the end
 */
	public void includeNetworkingTak() {
		listTrackDetails.get(listTrackDetails.size()-1).getPostLunch().scheduleNetwork();
		// TODO Auto-generated method stub
		
	}

	/**
	 * 
	 * @param scheduleOutput
	 * @throws IOException
	 * Export the Schedule to the ouput path
	 */

	public void export(IScheduleOutput scheduleOutput) throws IOException {
		// TODO Auto-generated method stub
		scheduleOutput.printOutput(this);
		
	}

}
