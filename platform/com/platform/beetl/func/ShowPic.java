package com.platform.beetl.func;

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
				for (String s : imageArray) {
					if (s != null && s.length() > 0) {
						sBuilder.append("<a class=\"fancybox1\" rel=\"group\" href=\"http://45.33.62.141").append(s).append("\">");
						sBuilder.append("<img src=\"http://45.33.62.141").append(s).append("\"height=\"100\" width=\"100\" alt=\"\"/>");
						sBuilder.append("</a>");
					}
				}
			}
		}
		
		return sBuilder.toString();
	}

}
