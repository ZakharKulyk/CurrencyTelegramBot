package Parce;

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

public class MonoParcer {
    public List<MonoDto> getRequest() {
        CloseableHttpClient client = createDefault();
        try
        {
            HttpGet request = new HttpGet("https://api.monobank.ua/bank/currency");
            CloseableHttpResponse response  = client.execute(request);
            try
            {
                if(response.getStatusLine().getStatusCode() == 200)
                {
                    HttpEntity entity = response.getEntity();
                    if(entity != null)
                    {
                        String result  = EntityUtils.toString(entity);
                        ObjectMapper objectMapper = new ObjectMapper();
                        List<MonoDto> mono = objectMapper.readValue(result, new TypeReference<List<MonoDto>>(){});
                        return mono;
                    }
                }
                else
                {
                    System.out.println("Error: " + response.getStatusLine().getStatusCode());
                }
            }
            finally {
                response.close();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            try
            {
                client.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        return new ArrayList<MonoDto>();
    }
}
