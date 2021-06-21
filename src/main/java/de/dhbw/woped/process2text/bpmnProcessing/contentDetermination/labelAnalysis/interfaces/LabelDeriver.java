package de.dhbw.woped.process2text.bpmnProcessing.contentDetermination.labelAnalysis.interfaces;

import de.dhbw.woped.process2text.bpmnProcessing.contentDetermination.labelAnalysis.structure.Activity;

import java.util.ArrayList;

public interface LabelDeriver {
	
	/**
	 * Investigates label and determines action and business object.
	 */
	public void processLabel(Activity label, String labelStyle); 

	/**
	 * Returns the computed action of the processed label.
	 */
	public ArrayList<String> returnActions();
	
	/**
	 * Returns the computed business object of the processed label.
	 */
	public ArrayList<String> returnBusinessObjects();
	
	/**
	 * Returns the computed addition.
	 */
	public String returnAddition();
	
}