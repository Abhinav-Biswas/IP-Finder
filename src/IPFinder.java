import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class IPFinder {

	public static String getIp(String str) throws Exception {
		URL whatismyip = new URL(str);
//				new URL("),
//				new URL() };
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
			String ip = in.readLine();
			return ip;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/*public static void main(String[] args) throws Exception {
		String str[] = {"http://checkip.amazonaws.com", "http://icanhazip.com/", "http://ifconfig.me/ip" };
		for (int i = 0; i < str.length; i++) {
			System.out.println(IPFinder.getIp(str[i]));
		}
	}*/

}