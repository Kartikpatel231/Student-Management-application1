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
                  .put("Subject", "Welcome to SVVV Campus  Place Hub")
                  .put("TextPart", "You are receiving this email because you have successfully registered with Campus Place Hub.")
                  .put("HTMLPart", "<h3>Welcome to <a href='https://www.campusplacehub.com/'>Campus Place Hub</a>!</h3><br />" +
                          "We are excited to have you on board. You can now complete your profile, provide your university details, and start applying to various companies on our platform." +
                          "Your application will be reviewed by our admin team once you have filled in all the necessary details." +
                          "<p>Thank you for joining Campus Place Hub!</p>")
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
