package test;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.APIRequester;
import utils.Response;

import static org.testng.Assert.assertTrue;

public class GitHubBranchesTest {
    private String url = "https://api.github.com/repos/vlbartkov/TestQA/branches";
    private Response response;
    private APIRequester apiRequester = new APIRequester();
    private JSONObject jsonObject = new JSONObject();
    private JSONParser jsonParser = new JSONParser();
    private JSONArray jsonArray = new JSONArray();

    public void getResponse(String url) {
        this.response = this.apiRequester.getRequest(url);
    }

    @Test(priority = 0)
    public void responseCodeTest() {
        this.getResponse(this.url);
        assertTrue(this.response.getCode() == 200);
    }

    @Test
    public void responseNotEmpty() {
        try {

            jsonArray = (JSONArray) jsonParser.parse(this.response.getContent());
            System.out.println(jsonArray.toJSONString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        assertTrue(jsonArray.size() > 0);
    }

    @Test
    public void responseContentTest() {
        String[] keys = {"name", "commit", "protected", "protection", "protection_url"};
        try {
            jsonArray = (JSONArray) jsonParser.parse(this.response.getContent());
            for (Object jo : jsonArray) {
                jsonObject = (JSONObject) jo;
                for (String key : keys) {
                    assertTrue(((JSONObject) jo).containsKey(key));
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sendRequestForMissingRepoTest() {
        String wrongUrl = "https://api.github.com/repos/vlbartkov/Test/branches";
        this.getResponse(wrongUrl);
        assertTrue(this.response.getCode() == 404);
    }

}
