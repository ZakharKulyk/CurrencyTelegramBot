package parce;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.NbuDto;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static сonstants.ConstantForDevProcess.NBU_URL_API;

public class NBUParcer {
    private static final ObjectMapper mapper = new ObjectMapper();

    public List<NbuDto> getRequest() {
        CloseableHttpClient client = HttpClients.createDefault();
        try {
            HttpGet request = new HttpGet(NBU_URL_API);
            CloseableHttpResponse response = client.execute(request);
            try {
                if (response.getStatusLine().getStatusCode() == 200) {
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        String result = EntityUtils.toString(entity, StandardCharsets.UTF_8);
                        List<NbuDto> nbu = mapper.readValue(result, new TypeReference<List<NbuDto>>() {
                        });
                        return nbu;
                    }
                } else {
                    System.out.println("Erorr: " + response.getStatusLine().getStatusCode());
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
        return new ArrayList<NbuDto>();
    }
}
