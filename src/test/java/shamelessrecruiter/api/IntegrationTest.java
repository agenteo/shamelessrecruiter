package shamelessrecruiter.api;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
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
    public void reportAndListAnnoyingRecruiter(){
        try {
            HttpResponse<JsonNode> jsonResponse = Unirest.post("http://localhost:9000/api/reportRecruiter")
                    .header("accept", "application/json")
                    .header("content-type", "application/json")
                    .body("{\"name\": \"Ajeje Brazu\", \"email\": \"ajeje@bra.zu\", \"message\": \"Dear candidate\"}")
                    .asJson();
            JSONObject myObj = jsonResponse.getBody().getObject();
            assertEquals("successfully submitted recruiter",
                         "good service", myObj.getString("response"));

            jsonResponse = Unirest.get("http://localhost:9000/api/listRecruiters")
                    .header("accept", "application/json")
                    .header("content-type", "application/json")
                    .asJson();
            JSONArray parsedResponse = jsonResponse.getBody().getArray();
            assertEquals("first element matches the reported recruiter",
                         "{\"name\":\"Ajeje Brazu\",\"message\":\"Dear candidate\",\"email\":\"ajeje@bra.zu\"}",
                         parsedResponse.get(0).toString());
        }
        catch(UnirestException e){
            e.printStackTrace();
        }
    }
}
