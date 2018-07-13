package cn.jxl.cloud.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
//import java.util.Base64;
//import java.util.Base64.Encoder;  

//import sun.misc.BASE64Decoder;  
//import sun.misc.BASE64Encoder;  

import org.apache.commons.codec.binary.Base64;

public class ImageBase64Util {
	// public static void main(String[] args) {
	// String[] strImg =
	// "iVBORw0KGgoAAAANSUhEUgAAAfQAAAASCAYAAACtvCyhAAADBklEQVR4Xu3dT6gVVRwH8M/TFqIrIaIoRIVCIigS3bQJRAhCUCuyDFwoLkRMcSmktBApLAhCQS2EFpELI2gRWoSiqKVR+A8l20m5EbJNmRY/OBOXR8jlvft8PO53Nnczd2bOZy58Z87vnHNHZItABCIQgQhEYMoLjEz5FqQBEYhABCIQgQgYHeizcRt/xCYCEYhABCIQgakj0AX6w/gaT+IituEwnsKFqdOcXGkEIhCBCERgOAW6QN+B7XgXpzANh/AMfhwjzVwcx4v4aYzHyNciEIEIRCACEehDoAv0D/Aq5uDPPr7Xzy4V6L+M86Ggn/NknwhEIAIRiMDQC1Sgv4/NTeIbvNdq6FvxJq5jF37FLXyIdfikfb6Fh/AdPsI+PIJP8Ry+wlHsxj9DLx6ACEQgAhGIwAQIVKDvx9p27ArsNZjeutwX4RLOtPp6dwm1z6PY2far+vsqPI+N+LLV5OfjHL7A2wn0CbiDOWQEIhCBCESA/0a5d13uj+N3vNwT6OdxFg/iBfyAB/BtE6wQ/xszcKDVzfciXe75iUUgAhGIQATuk0BXQ69u9ArxBbj5P4F+uo1+f73nLbt7sz+Cg+0t/mrPddfDwRXUW/7396k9OU0EIhCBCERgKAVGD4q7V6Bfxmu426Tmtbr4ih65G1iJE0igD+VPKo2OQAQiEIHJEBhPoHfXW4vRPI1XsAHX2vz1x9ob+sJWR5+M9uWcEYhABCIQgaEQGGugz8JJfN7mr3dY1Q2/rHXdV829utyXoEbPZ4tABCIQgQhEYIIE+q2h16C4n1Hd63fawjM1sr0GxG3BMSzGnjZdbTWeaCPka9raOwn1CbqDOWwEIhCBCESgZ5R7Be5LbRGYmrq2vGfp1wryGhRX88zX99TQq0u95p0v7ZH8DJvwWxsJ/zHeaCvFPdseBgIfgQhEIAIRiMCABQbxb2vV/V7T2GrRmJryNnqbib/a1LYBX34OF4EIRCACEYhACQwi0CMZgQhEIAIRiMAkCyTQJ/kG5PQRiEAEIhCBQQj8C2tAiBPh6KbCAAAAAElFTkSuQmCC".split("base64,");
	// String pathImg = "/Users/zhaoxn/Downloads/bbb.jpg";
	// System.out.println(strImg[strImg.length -1]);
	// GenerateImage(pathImg,strImg[strImg.length -1]);
	// }

	// 图片转化成base64字符串
	public static String GetImageStr(String picPathName) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		// String imgFile = "/Users/zhaoxn/Downloads/aaaa.jpg";// 待处理的图片
		String imgFile = picPathName;// 待处理的图片
		InputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
			in = new FileInputStream(imgFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// //对字节数组Base64编码
		// BASE64Encoder encoder = new BASE64Encoder();
		// return encoder.encode(data);//返回Base64编码过的字节数组字符串

		// 对字节数组Base64编码
		return Base64.encodeBase64String(data);// 返回Base64编码过的字节数组字符串
	}

	// base64字符串转化成图片
	public static boolean GenerateImage(String picPathName, String picStrBase64) { // 对字节数组字符串进行Base64解码并生成图片
		if (picStrBase64 == null) // 图像数据为空
			return false;
		// BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			// byte[] b = decoder.decodeBuffer(imgStr);
			byte[] b = Base64.decodeBase64(picStrBase64);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			// 生成jpeg图片
			// String imgFilePath = "/Users/zhaoxn/Downloads/aaaa22222.jpg";// 新生成的图片
			String imgFilePath = picPathName;
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}