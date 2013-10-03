package com.conf.scheduleMgmt.main;

import java.io.IOException;

import com.conf.scheduleMgmt.Outputs.OutputTypes;
import com.conf.scheduleMgmt.process.ConfScheduleManager;

public class ConfScheduleMain {
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		ConfScheduleManager mgnr = new ConfScheduleManager();
		mgnr.processTalk(mgnr
				.readTalkDetailsfromFile("D://confTrackMgmt//input.csv"));
		mgnr.renderOutput(OutputTypes.CSV, "D://confTrackMgmt//");

	}


}
