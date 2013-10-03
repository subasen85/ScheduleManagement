package com.conf.scheduleMgmt.process;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.bytecode.opencsv.CSVWriter;

import com.conf.scheduleMgmt.Session;
import com.conf.scheduleMgmt.Track;
import com.conf.scheduleMgmt.Utils.TimeUtils;
import com.conf.scheduleMgmt.constants.ConfSchMgmtConstants;

/**
 * Task which will take a track info and write it to a file
 * 
 */
public class CSVOutputTask implements Runnable {
	public CSVOutputTask(Track trackDetails, String outputFilePath) {
		super();
		this.trackDetails = trackDetails;
		this.outputFilePath = outputFilePath;
	}

	private Track trackDetails;
	private String outputFilePath;
	private static Logger log = LoggerFactory.getLogger(CSVOutputTask.class
			.getName());

	public String getOutputFilePath() {
		return outputFilePath;
	}

	public void setOutputFilePath(String outputFilePath) {
		this.outputFilePath = outputFilePath;
	}

	public Track getTrackDetails() {
		return trackDetails;
	}

	public void setTrackDetails(Track trackDetails) {
		this.trackDetails = trackDetails;
	}

	@Override
	public void run() {
		CSVWriter writer;
		try {
			writer = new CSVWriter(new FileWriter(outputFilePath));

			List<String[]> listTalkSchDetails = new ArrayList<String[]>();
			for (Session preSession : trackDetails.getPreLunch()
					.getPreLunchSessions()) {
				String[] scheduleDetails = new String[3];
				scheduleDetails[0] = TimeUtils.convertTo12HrFormat(preSession
						.getStartTime());
				scheduleDetails[1] = preSession.getSessionTitle();
				scheduleDetails[2] = preSession.getDuration()
						+ ConfSchMgmtConstants.MINUTES;
				listTalkSchDetails.add(scheduleDetails);

			}
			for (Session postSession : trackDetails.getPostLunch()
					.getPostLunchSessions()) {
				String[] scheduleDetails = new String[3];
				scheduleDetails[0] = TimeUtils.convertTo12HrFormat(postSession
						.getStartTime());
				scheduleDetails[1] = postSession.getSessionTitle();
				scheduleDetails[2] = postSession.getDuration()
						+ ConfSchMgmtConstants.MINUTES;
				listTalkSchDetails.add(scheduleDetails);

			}

			writer.writeAll(listTalkSchDetails);
			writer.close();
		} catch (IOException e) {
			log.error("Problem occured while writing data to the CSV file", e);
		}

	}

}
