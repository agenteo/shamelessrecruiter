package shamelessrecruiter.web;

import net.codestory.simplelenium.DomElement;
import net.codestory.simplelenium.PageObject;
import org.junit.Test;

import net.codestory.simplelenium.SeleniumTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ShamelessrecruiterApplication.class)
@WebIntegrationTest("server.port:9000")

public class IntegrationTest extends SeleniumTest {
    ReportRecruiterPage reportRecruiterPage;

    @Override
    protected String getDefaultBaseUrl() {
        return "http://localhost:9000";
    }

    @Test
    public void reportAnnoyingRecruiter() {
        goTo(reportRecruiterPage);
        reportRecruiterPage.shouldDisplayWelcomeMessage();
        reportRecruiterPage.shouldDisplayReportForm();
        reportRecruiterPage.fillInReport("Ajeje Brazu", "ajeje@bra.zu",
                                         "Dear candidate, I have the perfect opportunity for you...");
        reportRecruiterPage.submitReport();
        reportRecruiterPage.shouldDisplaySuccessMessage();
    }

    static class ReportRecruiterPage implements PageObject {
        DomElement body = find("body");
        DomElement nameField = find("input#name");
        DomElement emailField = find("input#email");
        DomElement messageField = find("textarea#message");
        DomElement submitButton = find("input#submitReport");

        @Override
        public String url(){
            return "/";
        }

        void shouldDisplayWelcomeMessage(){
            body.should().contain("Welcome to ShamelessRecruiter");
        };

        void shouldDisplayReportForm(){
            nameField.should().haveSize(1);
            emailField.should().haveSize(1);
            messageField.should().haveSize(1);
        }

        void fillInReport(String name, String email, String message){
            nameField.fill(name);
            emailField.fill(email);
            messageField.fill(message);
        }

        void submitReport(){
            submitButton.submit();
        }

        void shouldDisplaySuccessMessage(){
            body.should().contain("Thanks for reporting a shameless recruiter");
        }
    }


}