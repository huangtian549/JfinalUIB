package com.platform.beetl.func;

import java.util.Date;

import org.beetl.core.Context;
import org.beetl.core.Function;


public class ShowPic implements Function {

	@Override
	public Object call(Object[] paras, Context arg1) {
		
		Object o = paras[0];
		StringBuilder sBuilder = new StringBuilder();
		if (o != null) {
			String image = (String) o;
			if (image != null && image.length() > 0) {
				
				String[] imageArray = image.split(",");
				Date now = new Date();
				Long time = now.getTime();
				for (String s : imageArray) {
					if (s != null && s.length() > 0) {
						String[] arr = s.split("\\.");
						String smallPicName = arr[0] + "_100*100." + arr[1];
						String largePicName = arr[0] + "_800*600." + arr[1]; 
						if(time > 1500960369030L) {
							sBuilder.append("<a class=\"fancybox1\" rel=\"group\"  href=\"http://45.33.62.141").append(largePicName).append("\">");
							sBuilder.append("<img src=\"http://45.33.62.141").append(smallPicName).append("\"height=\"100\" width=\"100\" alt=\"\"/>");
							sBuilder.append("</a>");
						}else {
							sBuilder.append("<a class=\"fancybox1\" rel=\"group\"  href=\"http://45.33.62.141").append(s).append("\">");
							sBuilder.append("<img src=\"http://45.33.62.141").append(s).append("\"height=\"100\" width=\"100\" alt=\"\"/>");
							sBuilder.append("</a>");
						}
					}
				}
			}
		}
		
		return sBuilder.toString();
	}

}
