import java.io.*;

class FileOutput {
	public static void writeFile(String str) {
		FileOutputStream out; // declare a file output object
		PrintStream p; // declare a print stream object

		try {
			// Create a new file output stream
			// connected to "myfile.txt"
			out = new FileOutputStream("<Full Path here>\\ip.txt");

			// Connect print stream to the output stream
			p = new PrintStream(out);

			p.println(str);

			p.close();
		} catch (Exception e) {
			System.err.println("Error writing to file");
		}
	}

	
}
