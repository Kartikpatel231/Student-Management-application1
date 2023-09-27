package com.mycompany.studentmanagementapp.service;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.ClientOptions;
import com.mailjet.client.resource.Emailv31;
import lombok.SneakyThrows;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class MailJetService {

    //public static void main(String[] args) throws MailjetException, MailjetSocketTimeoutException {
    @SneakyThrows
          public  String sendEmail(String name,String email) throws  MailjetException,MailjetSocketTimeoutException {
          MailjetClient client;
          MailjetRequest request;
          MailjetResponse response;
          String apiKey = "231124812de7fb74678ea18a9db03609";
          String apiSecret = "2595dd56ecd8ff070de265186f50798e";


          client = new MailjetClient(apiKey, apiSecret, new ClientOptions("v3.1"));

          // Create the email message JSON
          JSONObject emailMessage = new JSONObject()
                  .put("From", new JSONObject()
                          .put("Email", "brainspace@brainspace.tech")
                          .put("Name", "kartik"))
                  .put("To", new JSONArray()
                          .put(new JSONObject()
                                  .put("Email", email)
                                  .put("Name", name)))
                  .put("Subject", "Greetings from SVVV Placment-Managment-App")
                  .put("TextPart", "You got this email because You Registered With SVVV-PlacementProApp")
                  .put("HTMLPart", "<h3> welcome to <a href='https://www.brainspace.tech/'>SVVV-Blog Page</a>!</h3><br />You Can Visit Our Website For more Information!")
                  .put("CustomID", "AppGettingStartedTest");

          // Create the Mailjet request
          request = new MailjetRequest(Emailv31.resource)
                  .property(Emailv31.MESSAGES, new JSONArray().put(emailMessage));

          response = client.post(request);
          System.out.println(response.getStatus());
          System.out.println(response.getData());
          if (response.getStatus() == 200) {
              return "eamail is sent";
          }
          return "email not sent";
      }

}
