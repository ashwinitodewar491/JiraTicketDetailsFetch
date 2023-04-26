package com.jira;
import org.testng.annotations.Test;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
public class JiraDetails {
    JiraDetails jiraDetails=null;

 @Test

    public void testJiraApi() throws IOException {
        URL url=new URL("https://joshsamplejira.atlassian.net/rest/api/3/issue/DFD-1");
        HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
        String password="ATATT3xFfGF0103aJCXakah_OePDg8v10sqyW3wzqxkY-R69wznRbqcPmnqV7hTUDpeJMGSLE76bbwqBXNlGFIkR66Z4aWSht3ijOGFaL8n53vEuo9HjxFPMoplmsXhBePlUqi7-Qltgp6Xhzk6lODtivjMa_meYWQvVSqpTbzwY9dmo2ke7iuQ=015ECC26";
        String auth="ashwini.todewar@joshsoftware.com"+":"+password;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes());
        String authHeaderValue = "Basic "+new String(encodedAuth);
        System.out.println(authHeaderValue);
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("Authorization",authHeaderValue);
        httpURLConnection.connect();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(httpURLConnection.getInputStream()));
        System.out.println(in);
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
            System.out.println(content.toString());
        }
     in.close();    }
}
