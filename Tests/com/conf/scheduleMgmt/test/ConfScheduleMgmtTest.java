package com.conf.scheduleMgmt.test;

import org.junit.Test;

import com.conf.scheduleMgmt.Outputs.OutputTypes;
import com.conf.scheduleMgmt.process.ConfScheduleManager;

public class ConfScheduleMgmtTest {
	
	@Test
	public void testPrepareSchedule(){
		// I have hardcoded the path.We can get the path details as an cmd line
		// argument.Here the input is a file based input.
		ConfScheduleManager confScheduleMgmt = new ConfScheduleManager();
		confScheduleMgmt.processTalk(confScheduleMgmt
				.readTalkDetailsfromFile("D://confTrackMgmt//input.csv"));
		confScheduleMgmt.renderOutput(OutputTypes.CSV, "D://confTrackMgmt//");
	}

}
