package processapi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import assets.UserRequest;
//This method reads the file from the input source given from the UserRequest and returns the int after the string is parsed from the file
public class ReadData {
//	public static int readData(UserRequest request) throws FileNotFoundException {
//		String input = request.getInputSource();
//		File file= new File(input);
//		int readData = 0;
//		BufferedReader reader = new BufferedReader(new FileReader(input));
//		String read;
//		try {
//			while((read=reader.readLine())!= null) {
//				try {
//					String data = reader.readLine();
//					 readData = Integer.parseInt(data);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return readData;
//	}
//	
	
	//This method reads the file from the input source given from the UserRequest and returns the int after the string is parsed from the file
	public static int readData(String inputSource) throws FileNotFoundException {
		
		File file= new File(inputSource);
		int readData = 0;
		BufferedReader reader = new BufferedReader(new FileReader(inputSource));
		String read;
		try {
			while((read=reader.readLine())!= null) {
				try {
					String data = reader.readLine();
					 readData = Integer.parseInt(data);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return readData;
	}
}
