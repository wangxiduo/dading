package autotools;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class AutoPublicTools
{
	public static boolean Base64ToImg(String imgStr, String path)
	{
		if (imgStr == null)
		{
			return false;
		}
		
		BASE64Decoder decoder = new BASE64Decoder();
		try
		{
			byte[] b = decoder.decodeBuffer(imgStr);

			for (int i = 0; i < b.length; ++i)
			{
				if (b[i] < 0)
				{
					b[i] += 256;
				}
			}
			OutputStream out = new FileOutputStream(path);
			out.write(b);
			out.flush();
			out.close();

			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public static String ImgToBase64(String imgFile)
	{
		InputStream inputStream = null;
		byte[] data = null;
		
		try
		{
			inputStream = new FileInputStream(imgFile);
			data = new byte[inputStream.available()];
			inputStream.read(data);
			inputStream.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		BASE64Encoder encoder = new BASE64Encoder();
		
		return encoder.encode(data);
	}
}
