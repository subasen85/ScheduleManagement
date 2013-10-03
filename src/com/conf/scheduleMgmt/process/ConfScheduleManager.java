package com.conf.scheduleMgmt.process;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.bytecode.opencsv.CSVReader;

import com.conf.scheduleMgmt.Schedule;
import com.conf.scheduleMgmt.Talk;
import com.conf.scheduleMgmt.Outputs.CSVOutput;
import com.conf.scheduleMgmt.Outputs.ExcelOutput;
import com.conf.scheduleMgmt.Outputs.IScheduleOutput;
import com.conf.scheduleMgmt.Outputs.OutputTypes;
import com.conf.scheduleMgmt.Outputs.PDFOutput;
import com.conf.scheduleMgmt.constants.ConfSchMgmtConstants;

public class ConfScheduleManager {

	List<Talk> listTalk = new ArrayList<Talk>();
	Schedule schedule = new Schedule();

	private static Logger log = LoggerFactory
			.getLogger(ConfScheduleManager.class.getName());

	/**
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 * 
	 * This Method will read the list of Talks from the input File
	 */
	public List<Talk> readTalkDetailsfromFile(String filePath)
			 {
		List<Talk> listTalkDetails = new ArrayList<Talk>();
		CSVReader csvReader;
		try {
			csvReader = new CSVReader(new FileReader(filePath));
			String[] row = null;
			while ((row = csvReader.readNext()) != null) {
				log.debug(row[0] + " # " + row[1]);
				listTalkDetails.add(this.buildTalkDetails(row[0], row[1]));
			}
			csvReader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			log.error("****Error in readTalkDetailsfromFile******",e);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("****Error in readTalkDetailsfromFile******",e);
		}
		
		log.info("****Total number of talks for the day is ****"
				+ listTalkDetails.size());
		Collections.sort(listTalkDetails);

		return listTalkDetails;

	}

	/**
	 * 
	 * @param talkName
	 * @param duration
	 * @return
	 * Method to build the Talk object from the file input
	 */
	private Talk buildTalkDetails(String talkName, String duration) {
		Talk talkDetails = new Talk();
		talkDetails.setName(talkName);
		if (!duration.equalsIgnoreCase(ConfSchMgmtConstants.LIGHTNING)) {
			try {
				talkDetails.setDuration(Integer.parseInt(duration));
			} catch (NumberFormatException e) {
				log.debug(duration + " is not a valid number ");

				talkDetails.setDuration(0);
			}
		} else {
			talkDetails.setDuration(ConfSchMgmtConstants.FIVEMINS);
		}

		return talkDetails;
	}

	/**
	 * 
	 * @param listTalk
	 * Mehtod to process the Talk
	 */
	public void processTalk(List<Talk> listTalk) {
		while (!listTalk.isEmpty()) {
			Iterator<Talk> talkIterator = listTalk.iterator();
			while (talkIterator.hasNext()) {
				if (schedule.scheduleTalk(talkIterator.next())) {
					talkIterator.remove();
				}

			}
			schedule.setTrackFull();
		}
		schedule.includeNetworkingTak();

	}
	



	public void renderOutput(OutputTypes type, String outputFilePath) {

		IScheduleOutput scheduleOutput = null;
		switch (type) {
		case CSV:
			scheduleOutput = new CSVOutput(outputFilePath);
			break;
		case Excell:
			scheduleOutput = new ExcelOutput();
			break;
		case PDF:
			scheduleOutput = new PDFOutput();
			break;
		}
		try {
			schedule.export(scheduleOutput);
			
		} catch (IOException e) {
			log.error(
					"Program encountered an error while writig the schedule details ",
					e);
		}
	}

}
