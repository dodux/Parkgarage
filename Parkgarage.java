import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Parkgarage {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Testdaten einlesen
		String input = "";
		if (args.length > 0) {
			String inputData = "";
			inputData = loadInputData(args[0]);
			if (!inputData.isEmpty()) {
				input = inputData;
			}
		}
		if (input.length() < 1) {
			System.out.println("no input data found.");
			System.exit(0);
		}

		int autos = 0;
		int maxAutos = 0;
		int waiting = 0;
		int maxWaiting = 0;

		String[] zeile = input.split("\r\n");
		String[] config = zeile[0].split(" ");
		int parking = Integer.parseInt(config[0]); // # Parking
		int cars = Integer.parseInt(config[1]); // # Cars
		
		String[] garage = zeile[1].split(" ");


		int[] autosArr = new int[2*cars];


		for (int i = 0; i < garage.length; i++) {
			autosArr[i] = Integer.parseInt(garage[i]);
		}

		for (int i = 0; i < autosArr.length; i++) {
			if (autosArr[i] > 0) {
				autos++;
			} else {
				autos--;
			}

			if(autos > parking)
			{
				waiting ++;
				autos--;
			}
			
			if(autos < parking && waiting >= 1)
			{
				waiting --;
				autos++;
			}
			
			if(waiting > maxWaiting)
			{
				maxWaiting = waiting;
			}
			if (autos > maxAutos) {
				maxAutos = autos;
			}
			
			
		}

		JOptionPane.showMessageDialog(null, maxAutos + " " + maxWaiting);
	}

	
	//____________________________________________________________________________________________
	//____________________________________________________________________________________________
	public static String loadInputData(String datName) {
		String fileData = "";
		File file = new File(datName);

		if (!file.canRead() || !file.isFile()) {
			return "";
		}
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(datName));
			String zeile = null;
			while ((zeile = in.readLine()) != null) {
				fileData += zeile + "\r\n";
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return fileData;
	}

}
