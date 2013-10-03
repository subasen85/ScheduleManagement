/**
 * 
 */
package com.conf.scheduleMgmt.Outputs;

/**
 * enum for different types of O/P
 * 
 */
public enum OutputTypes {
	CSV(1), Excell(2),  PDF(3);
	private int type;

	private OutputTypes(int type) {
		this.type = type;
	}

	public int getType() {
		return this.type;
	}
}
