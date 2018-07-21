package com.egandunning.vba;

public class ConvertText {

	public static int maxVbaLineContinue = 25;
	public static int maxLineLength = 200;
	public static int lineBreakPosition = maxLineLength - 7;
	
	public static String toVbaString(String text) {
		
		StringBuilder outputText = new StringBuilder();
		
		String[] lines = text.split("\n");
		
		for(int i = 0; i < lines.length; i++) {
			
			String temp = lines[i].replace("\"", "\"\"");
			
			while(true) {
				
				outputText.append("\"");
				
				//shorten lines when necessary
				if(temp.length() > maxLineLength) {
					outputText.append(temp.substring(0, lineBreakPosition)).append("\"").append(" & _\n");
					temp = temp.substring(lineBreakPosition);
				} else { //line length is within acceptable limit
					outputText.append(temp).append("\"");
					break;
				}
			}
			
			//add newline and line continuation character
			if(i != lines.length - 1) {
				outputText.append(" & vbCrLf & _\n");
			}
		}
		return outputText.toString();
	}
}
