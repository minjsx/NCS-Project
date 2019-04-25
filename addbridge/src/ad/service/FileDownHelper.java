package ad.service;

import java.io.FileInputStream;
import java.io.IOException;

import java.io.OutputStream;

public class FileDownHelper {
	
	public static void download( String filepath, OutputStream os ) throws IOException{
		FileInputStream is = null;
		try{
				is = new FileInputStream(filepath);
				byte[] data = new byte[8096];
				int len = -1;
				while( (len = is.read(data)) != -1 ){
					os.write( data, 0, len);
				}
			}finally{
				if( is!= null ) try{ is.close(); } catch(IOException es){}
			}
	}
}