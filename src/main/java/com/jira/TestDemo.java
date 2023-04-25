package com.jira;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TestDemo {
    public void createUser(String dashboardLink,String defectId) throws IOException{
        Playwright playwright=Playwright.create();
        APIRequest apiRequest=playwright.request();
        APIRequestContext apiRequestContext=apiRequest.newContext();
//      APIResponse apiResponse=apiRequestContext.get("https://joshsamplejira.atlassian.net/rest/api/3/issue/DFD-1", RequestOptions.create().setHeader("Authorization","Basic YXNod2luaS50b2Rld2FyQGpvc2hzb2Z0d2FyZS5jb206QVRBVFQzeEZmR0YwMTAzYUpDWGFrYWhfT2VQRGc4djEwc3F5VzN3enF4a1ktUjY5d3puUmJxY1BtbnFWN2hUVURwZUpNR1NMRTc2YmJ3cUJYTmxHRklrUjY2WjRhV1NodDNpak9HRmFMOG41M3ZFdW85SGp4RlBNb3BsbXNYaEJlUGxVcWk3LVFsdGdwNlhoems2bE9EdGl2ak1hX21lWVdRdlZTcXBUYnp3WTlkbW8ya2U3aXVRPTAxNUVDQzI2"));
        APIResponse apiResponse=apiRequestContext.get("https://"+dashboardLink+".atlassian.net/rest/api/3/issue/"+defectId, RequestOptions.create().setHeader("Authorization","Basic YXNod2luaS50b2Rld2FyQGpvc2hzb2Z0d2FyZS5jb206QVRBVFQzeEZmR0YwMTAzYUpDWGFrYWhfT2VQRGc4djEwc3F5VzN3enF4a1ktUjY5d3puUmJxY1BtbnFWN2hUVURwZUpNR1NMRTc2YmJ3cUJYTmxHRklrUjY2WjRhV1NodDNpak9HRmFMOG41M3ZFdW85SGp4RlBNb3BsbXNYaEJlUGxVcWk3LVFsdGdwNlhoems2bE9EdGl2ak1hX21lWVdRdlZTcXBUYnp3WTlkbW8ya2U3aXVRPTAxNUVDQzI2"));
        ObjectMapper objectMapper=new ObjectMapper();
        JsonNode jsonNode=objectMapper.readTree(apiResponse.body());
        String jResponse=jsonNode.toPrettyString();
        try {
            // Convert JSON string to JsonNode object
            JsonNode jsonNode1=objectMapper.readTree(apiResponse.body());
            // Create CSV file
            File file = new File("Jiraticket.csv");
            FileWriter writer = new FileWriter(file);
            // Write header row to CSV file
                writer.write("key,bugStatus,projectName,priority,assigneeName,assigneeEmail\n");
            // Write Jira ticket details to CSV file
                String key = jsonNode1.get("key").asText();
                String projectName=jsonNode1.get("fields").get("project").get("name").asText();
                String bugStatus = jsonNode1.get("fields").get("status").get("name").asText();
                String priority= jsonNode1.get("fields").get("priority").get("name").asText();
                String assigneeName= jsonNode1.get("fields").get("assignee").get("displayName").asText();
                String assigneeEmail= jsonNode1.get("fields").get("assignee").get("emailAddress").asText();
//                String type=jsonNode1.get("fields").get("description").get("content").get("type").asText();
//                 System.out.println("fPrinting type"+type);

//            String firstName;
//            for (JsonNode atrrrr : arrayNode) {
//                firstName = atrrrr.get("text").asText();
//                System.out.println("fPrinting fn"+firstName);
//            }
            writer.write(key+ "," +bugStatus+"," +projectName+"," +priority+"," +assigneeName+"," +assigneeEmail+"\n");
                writer.close();
            System.out.println("CSV file created and updated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(jResponse);
    }
    public static void main(String[] args) throws IOException {
        TestDemo testDemo=new TestDemo();
        Scanner sc= new Scanner(System.in);
        System.out.print("Enter a dashboard link: ");
        String dashboardLink= sc.nextLine();
        System.out.print("Enter a defect ID: ");
        String defectId= sc.nextLine();
        testDemo.createUser(dashboardLink,defectId);

    }
}
