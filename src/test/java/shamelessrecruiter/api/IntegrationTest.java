package shamelessrecruiter.api;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import shamelessrecruiter.ShamelessrecruiterApplication;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ShamelessrecruiterApplication.class)
@WebIntegrationTest("server.port:9000")

public class IntegrationTest {
    @Autowired
    org.springframework.data.mongodb.core.MongoTemplate mongoTemplate;

    @Before
    public void executedBeforeEach() {
        // Working option to change browser.
        // System.setProperty("browser", "chrome");
        // Ensure DB is clean
        mongoTemplate.getDb().dropDatabase();
    }

    @Test
    public void reportAnnoyingRecruiter(){
        try {
            HttpResponse<JsonNode> jsonResponse = Unirest.post("http://localhost:9000/api/reportRecruiter")
                    .header("accept", "application/json")
                    .header("content-type", "application/json")
                    .body("{\"name\": \"Ajeje Brazu\", \"email\": \"ajeje@bra.zu\", \"message\": \"Dear candidate\"}")
                    .asJson();
            System.out.println(jsonResponse.getBody());
            JSONObject myObj = jsonResponse.getBody().getObject();
            assertEquals("good service", myObj.getString("response"));
        }
        catch(UnirestException e){
            e.printStackTrace();
        }
    }
}
