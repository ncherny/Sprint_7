import api.methods.CourierMethods;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import util.Consts;

import java.util.UUID;

public class BaseCourierTestClass {
    protected String login;
    protected String password;
    protected String firstName;

    @Before
    public void setup() {
        RestAssured.baseURI = Consts.BASE_URL;

        login = UUID.randomUUID().toString();
        password = UUID.randomUUID().toString();
        firstName = UUID.randomUUID().toString();
    }

    @After
    public void cleanup() {
        CourierMethods.cleanUpCreatedCourier(login, password);
    }
}
