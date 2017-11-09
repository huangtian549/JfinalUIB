package com.platform.beetl.func;

import java.util.Date;

import org.beetl.core.Context;
import org.beetl.core.Function;

import com.platform.tools.ToolDateTime;


public class ShowPic implements Function {

	@Override
	public Object call(Object[] paras, Context arg1) {
		
		Object o = paras[0];
		StringBuilder sBuilder = new StringBuilder();
		if (o != null) {
			String image = (String) o;
			if (image != null && image.length() > 0) {
				
				String[] imageArray = image.split(",");
				for (String s : imageArray) {
					if (s != null && s.length() > 0) {
						String[] arr = s.split("\\.");
						String smallPicName = arr[0] + "_100*100." + arr[1];
						String largePicName = arr[0] + "_800*600." + arr[1]; 
							sBuilder.append("<a class=\"fancybox1\" rel=\"group\"  href=\"http://45.33.62.141").append(largePicName).append("\">");
							sBuilder.append("<img src=\"http://45.33.62.141").append(smallPicName).append("\"height=\"100\" width=\"100\" alt=\"\"/>");
							sBuilder.append("</a>");
					}
				}
			}
		}
		
		return sBuilder.toString();
	}

}
