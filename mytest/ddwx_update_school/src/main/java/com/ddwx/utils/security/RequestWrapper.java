package com.ddwx.utils.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.text.SimpleDateFormat;

public final class RequestWrapper extends HttpServletRequestWrapper {

    public RequestWrapper(HttpServletRequest httpservletrequest) {
        super(httpservletrequest);
    }

    public String[] getParameterValues(String s){
		String str[] = super.getParameterValues(s);
		if (str == null) {
			return null;
        }else{
        	for(int i=0;i<str.length;i++){
        		str[i]=this.escapeHtml(str[i]);
        	}
        }
		return str;
    }

    public String getParameter(String s) {
    	return this.escapeHtml(super.getParameter(s));
    }

    public String getHeader(String s) {
        String s1 = this.escapeHtml(super.getHeader(s));
        return s1;
    }
    
    public boolean isValidDate(String s)
    {
    	boolean date1 = false;
    	boolean date2 = false;
	 	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    try
	    {
	    	dateFormat.parse(s);
	    	date1 = true;
	    }catch (Exception e){
	    	date1 =false;
	    }
	    SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
	    try
	    {
	    	dateFormat1.parse(s);
	    	date2 = true;
	    }catch (Exception e){
	    	date2 =false;
	    }
	    if(date1 || date2){
	    	return true ;
	    }else{
	    	return false;
	    }
    }

    public static String escapeHtml(String str){
    	if(str == null){
    		return null;
    	}
    	return str.replaceAll("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }

    public static void main(String[] args){
    	String ss="enterExamListFtl201405~!@#$%^&*()_+{}[]<>,.;/'\\|`<script>alerthaving (1);<img></img>\"&";
    	//System.out.println(RequestWrapper.escapeHtml(ss));
    }
    
}
