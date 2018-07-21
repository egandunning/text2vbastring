package com.egandunning.vba;

public class ConvertText {

	public static int maxVbaLineContinue = 10;
	public static int maxLineLength = 200;
	public static int lineBreakPosition = maxLineLength - 7;
	
	public static String toVbaString(String text, String name) {
		
		StringBuilder outputText = new StringBuilder(name + " = ");
		
		String[] lines = text.split("\n");
		
		int lineContinueCount = 0;
		
		for(int i = 0; i < lines.length; i++) {
			
			String temp = lines[i].replace("\"", "\"\"");
			
			while(true) {
				
				outputText.append("\"");
				
				//shorten lines when necessary
				if(temp.length() > maxLineLength) {
					outputText.append(temp.substring(0, lineBreakPosition)).append("\"").append(" & _\n");
					temp = temp.substring(lineBreakPosition);
					lineContinueCount++;
				} else { //line length is within acceptable limit
					outputText.append(temp).append("\"");
					break;
				}
			}
			
			//add newline and line continuation character
			if(i != lines.length - 1) {
				outputText.append(" & vbCrLf & _\n");
				lineContinueCount++;
			}
			
			if(lineContinueCount == maxVbaLineContinue - 1) {
				outputText.delete(outputText.lastIndexOf(" & _"), outputText.length());
				outputText.append("\n" + name + " = " + name + " & ");
				lineContinueCount = 0;
			}
			
		}
		return outputText.toString();
	}
}
