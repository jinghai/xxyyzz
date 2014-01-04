
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
/**
 *
 * @author xiaojinghai
 */
public class Test {

    public static void main(String[] ares) {
        RestTemplate restTemplate = new RestTemplate();

        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>(2);
        converters.add(new FormHttpMessageConverter());
        converters.add(new MappingJackson2HttpMessageConverter());
        //restTemplate.setMessageConverters(converters);
        String s = restTemplate.getForObject("http://www.baidu.com", String.class);
        System.out.println(s);
    }
}
