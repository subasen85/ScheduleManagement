package com.conf.scheduleMgmt.Outputs;

import java.io.IOException;

import com.conf.scheduleMgmt.Schedule;

public interface IScheduleOutput {
	public void printOutput(Schedule schedule) throws IOException;

}
