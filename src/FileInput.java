import java.io.*;

class FileInput {
	private static String str;
	private static BufferedReader br;

	public static String readFile() {
	
		try {
			br = new BufferedReader(new FileReader("<Full Path here>\\ip.txt"));

			str = br.readLine();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		return str;
	}

}
