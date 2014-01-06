
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import static org.apache.log4j.Logger.getLogger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.core.NestedRuntimeException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
/**
 *
 * @author xiaojinghai
 */
public class TestD {

    private static final Logger LOGGER = getLogger(TestAccountRestController.class);

    public static RestTemplate restTemplate = new RestTemplate();

    public static final String baseUrl = "http://localhost:8080/server/api/v1/user";

    public static void main(String[] ares) throws IOException {
        //发送实体
        MultiValueMap<String, String> request = new LinkedMultiValueMap<String, String>();
        request.add("username", "测试用户");
        request.add("password", "test");
        try {
            //String s = restTemplate.getForObject("http://localhost:8080/server/api/v1/account/create.json", String.class);
            String s = restTemplate.postForObject("http://localhost:8080/server/api/v1/account/create", request, String.class);
            System.out.println(s);
        } catch (HttpClientErrorException e) {
            LOGGER.error("HttpClientErrorException:  " + e.getResponseBodyAsString());

            ObjectMapper mapper = new ObjectMapper();
            ErrorHolder eh = mapper.readValue(e.getResponseBodyAsString(), ErrorHolder.class);

            LOGGER.error("HttpClientErrorException:  " + eh.getErrorMessage());
        } catch (HttpStatusCodeException e) {
            LOGGER.error("HttpStatusCodeException:  " + e.getResponseBodyAsString());
        } catch (RestClientException e) {
            LOGGER.error("RestClientException:  " + e.getMessage());
        } catch (NestedRuntimeException e) {
            LOGGER.error("NestedRuntimeException:  " + e.getMessage());
        } catch (RuntimeException e) {
            LOGGER.error("RuntimeException:  " + e.getMessage());
        }


        /*
         // Set the Accept header
         HttpHeaders requestHeaders = new HttpHeaders();
         requestHeaders.setAccept(Collections.singletonList(new MediaType("application", "json")));
         HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
         */
    }

    public static HttpHeaders createHeaders(final String username, final String password) {
        return new HttpHeaders() {
            {
                String auth = username + ":" + password;
                byte[] encodedAuth = Base64.encodeBase64(
                        auth.getBytes(Charset.forName("US-ASCII")));
                String authHeader = "Basic " + new String(encodedAuth);
                set("Authorization", authHeader);
            }
        };
    }

    public static String UploadFile(String path) {
        //http://weitd.iteye.com/blog/1585118
        String upload = "/user/upload?format=json";
        MultiValueMap<String, Object> formData = new LinkedMultiValueMap<String, Object>();
        Resource resource;
        try {
            resource = new UrlResource("file://" + path);
            formData.add("json", resource);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(formData, requestHeaders);
        //ResponseEntity<String> response = restTemplate.exchange(upload, HttpMethod.POST, requestEntity, String.class);
        //return response.getBody();
        return null;
    }
}
