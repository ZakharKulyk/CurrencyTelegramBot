package parce;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.MonoDto;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;

import org.apache.http.util.EntityUtils;


import java.util.ArrayList;
import java.util.List;

import static org.apache.http.impl.client.HttpClients.*;
import static сonstants.ConstantForDevProcess.MONO_URL_API;

public class MonoParcer {
    private final static ObjectMapper objectMapper = new ObjectMapper();

    public List<MonoDto> getRequest() {
        CloseableHttpClient client = createDefault();
        try {
            HttpGet request = new HttpGet(MONO_URL_API);
            CloseableHttpResponse response = client.execute(request);
            try {
                if (response.getStatusLine().getStatusCode() == 200) {
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        String result = EntityUtils.toString(entity);
                        List<MonoDto> mono = objectMapper.readValue(result, new TypeReference<List<MonoDto>>() {
                        });
                        return mono;
                    }
                } else {
                    System.out.println("Error: " + response.getStatusLine().getStatusCode());
                }
            } finally {
                response.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                client.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<MonoDto>();
    }
}
