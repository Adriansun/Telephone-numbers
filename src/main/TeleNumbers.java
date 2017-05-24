package main;

import java.io.*;
import java.util.*;

public class TeleNumbers{
	public static void main(String[] args){
		String inputFile = "D:/ProgramProjekt/Eclipse/TelephoneNumbers/src/main/input.txt";
		String outputFile = "D:/ProgramProjekt/Eclipse/TelephoneNumbers/src/main/output.txt";
		
		List <String> listOfNumbers = readInput(inputFile);
		Collections.sort(listOfNumbers);
		listOfNumbers = checkDup(listOfNumbers);
		writeOutput(listOfNumbers, outputFile);
	}

	/**
	 * Read input file and use formatNumber method.
	 * 
	 * @param Path to inputFile
	 * @return List with numbers in proper MSISDN-form
	 */
	@SuppressWarnings("resource")
	public static List <String> readInput(String path){
		List<String> listOfNumbers = new ArrayList<String>();

		try{
			String line = new String();
			BufferedReader reader = new BufferedReader(new FileReader(path));			

			while ((line = reader.readLine()) != null){
				if(line.length()>0){
					line = formatNumber(line);
					listOfNumbers.add(line);
				}
			}
		}catch(IOException e){
			e.printStackTrace();
			}

		return listOfNumbers;
	}
	
	/**
	 * Converts a number to MSISDN-form.
	 *
	 * @param String Number
	 * @return Number MSISDN-form
	 */
	public static String formatNumber(String number){
		number = number.replaceAll("-", "");
		StringBuilder sb = new StringBuilder(number);
		
		if(sb.charAt(0)=='0'){
			sb.deleteCharAt(0);
			sb.insert(0, "+46");
		}else if(sb.charAt(0)=='+'){
			
		}else{
			sb.insert(0, "+46");
		}
		
		number = sb.toString();	       
		
		return number;
	}

	/**
	 * Checks for duplicated numbers.
	 * 
	 * @param List listOfNumbers is the list with all numbers
	 * @return List with no duplicated numbers
	 */
	public static List <String> checkDup(List <String> listOfNumbers){
		List <String> formattedListOfNumbers = new ArrayList<String>();
		String temp = null;
		int count = 0;
		
		for (String number : listOfNumbers){
			if(temp == null){
				temp = number;
				count++;
			}else{
				if(temp.equals(number)){
					count++;
				}else{
					formattedListOfNumbers.add(temp + ";" + count);
					count = 1;
					temp = number;
				}
			}
		}
		
		formattedListOfNumbers.add(temp + ";" + count);
		
		return formattedListOfNumbers;
	}

	/**
	 * Output to file.
	 * 
	 * @param List listOfNumbers
	 * @param Path to outputFile
	 */
	public static void writeOutput(List <String> listOfNumbers, String path){
		try{
			FileWriter fileWriter = new FileWriter(path);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			for(String number : listOfNumbers){ 
				bufferedWriter.write(number + "\n");
			}

			bufferedWriter.close();
			fileWriter.close();
			
		}catch(IOException e){ 
			e.printStackTrace();
			}
	}
}