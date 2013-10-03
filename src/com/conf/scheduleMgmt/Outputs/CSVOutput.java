package com.conf.scheduleMgmt.Outputs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.conf.scheduleMgmt.Schedule;
import com.conf.scheduleMgmt.Track;
import com.conf.scheduleMgmt.constants.ConfSchMgmtConstants;
import com.conf.scheduleMgmt.process.CSVOutputTask;

public class CSVOutput implements IScheduleOutput {

	private String outputPath;

	public CSVOutput(String outputPath) {
		super();
		this.outputPath = outputPath;
	}
	
	

	public String getOutputPath() {
		return outputPath;
	}



	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}



	@Override
	public void printOutput(Schedule schedule) {
		// TODO Auto-generated method stub
		ExecutorService executor = Executors
				.newFixedThreadPool(ConfSchMgmtConstants.DEFAULT_POOL_SIZE);
		int cnt = 0;
		
		for (Track track : schedule.getListTrackDetails()
				) {
			cnt++;

			Runnable worker = new CSVOutputTask(track,
					this.getOutputPath()
							+ ConfSchMgmtConstants.OUTPUT_FILE_NAME + cnt
							+ ConfSchMgmtConstants.CSV_EXTN);
			executor.execute(worker);

		}

		executor.shutdown();

		while (!executor.isTerminated()) {

		}

	}

}
