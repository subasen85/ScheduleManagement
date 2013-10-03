package com.conf.scheduleMgmt;


public class Track {
	//List<Session> listSessionDetails;
	PostLunchTrack postLunch;
	PreLunchTrack preLunch;
	
	
	public Track() {
		super();
		postLunch=new PostLunchTrack();
		preLunch=new PreLunchTrack();
		
	}
	/**
	 * @return the postLunch
	 */
	public PostLunchTrack getPostLunch() {
		return postLunch;
	}
	/**
	 * @param postLunch the postLunch to set
	 */
	public void setPostLunch(PostLunchTrack postLunch) {
		this.postLunch = postLunch;
	}
	/**
	 * @return the preLunch
	 */
	public PreLunchTrack getPreLunch() {
		return preLunch;
	}
	/**
	 * @param preLunch the preLunch to set
	 */
	public void setPreLunch(PreLunchTrack preLunch) {
		this.preLunch = preLunch;
	}
	

	boolean isTrackFull;
	
	public boolean isTrackFull() {
		return isTrackFull;
	}
	public void setTrackFull(boolean isTrackFull) {
		this.isTrackFull = isTrackFull;
	}
	
	/**
	 * @param talk
	 * This method will identify in which slot this talk needs to be scheduled
	 */
	public boolean scheduleTalk(Talk talk){
		if(preLunch.scheduletalk(talk)){
			return true;
		}else if(postLunch.scheduletalk(talk)){
			return true;
		}
		return false;
		
		
		
		
	}
}
