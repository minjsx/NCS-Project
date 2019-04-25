package ad.service;

import java.io.*;

public class FileSaveHelper {

	//private static Random random = new Random();
	
	public static String save( String directory, InputStream is, String fileName ) throws IOException
	{
		
		//long currentTime = System.currentTimeMillis();
		//int randomValue = random.nextInt(50);
		//String fileName = Long.toString(currentTime) + "_" + Integer.toString(randomValue);
		
		File file = new File( directory, fileName );
		FileOutputStream os = null;
		try{
			os = new FileOutputStream( file );
			byte[] data = new byte[8096];
			int len = -1;
			while( (len = is.read(data)) != -1 ){
				os.write(data, 0, len);
			}
		} finally{
			if( os!= null ) try{ os.close(); } catch( IOException es ){}
		}
		
		return file.getAbsolutePath();
	}
}

