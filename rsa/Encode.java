/**********************************************
 * Encode.java
 * @author SarahGreen
 * UNI: stg2117
 * COMS 3134 Columbia University, Fall 2013
 *********************************************/

package rsa;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The {@link Encode} class takes in a message, and either encrypts or decrypts
 * it, based on the command line argument specifying the public or private key
 * file.
 */
public class Encode {

	/**
	 * Main method to read in a message from System.in, locate the appropriate
	 * exponent and mod values from the file named in the command line (public
	 * or private key), and then iterate through the message to encrypt/decrypt
	 * it line by line. The resulting values are printed to System.out.
	 * 
	 * @param args
	 *            command line arguments
	 * @throws FileNotFoundException
	 *             in case an incorrect file is specified for the public or
	 *             private keys
	 */
	public static void main(String[] args) throws FileNotFoundException {
		int[] msg = readMessage();
		File file = new File(args[0]);
		Scanner fileReader = new Scanner(file);
		String temp = "";
		while (fileReader.hasNextLine())
			temp += fileReader.nextLine() + "\n";
		temp = temp.substring(0, temp.lastIndexOf("\n"));
		String[] tempArr = temp.split("\n");
		int exp = Integer.parseInt(tempArr[0]);
		int N = Integer.parseInt(tempArr[1]);
		for (int i = 0; i < msg.length; i++)
			System.out.println(powerMod(msg[i], exp, N));
	}

	/**
	 * Reads in a message from System.in. Creates a String of the input, splits
	 * it into a String array, then parses each entry to make an int array.
	 * 
	 * @return int[] representing the inputed message
	 */
	public static int[] readMessage() {
		Scanner reader = new Scanner(System.in);
		String str = "";
		while (reader.hasNextLine())
			str += reader.nextLine() + "\n";
		str = str.substring(0, str.lastIndexOf("\n"));
		String[] arr = str.split("\n");
		int[] message = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			int temp = Integer.parseInt(arr[i]);
			message[i] = temp;
		}
		return message;
	}

	/**
	 * Recursively computes an exponent, mod a particular value. Used to
	 * encrypt/decrypt a message. Runs in logarithmic time.
	 * 
	 * @param b
	 *            the base
	 * @param e
	 *            the exponent
	 * @param mod
	 *            the number to mod
	 * @return the computed result
	 */
	public static int powerMod(int b, int e, int mod) {
		if (e == 0)
			return 1;
		else if (e % 2 == 0) {
			int sqrt = powerMod(b, (e / 2), mod);
			return (sqrt * sqrt) % mod;
		} else
			return (powerMod(b, (e - 1), mod) * (b % mod)) % mod;
	}

}