package Parce;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import BankParceDto.PrivatDto;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

public class PrivatParcer {
    public List<PrivatDto> getRequest(){
        CloseableHttpClient client = HttpClients.createDefault();
        try
        {
            HttpGet request = new HttpGet("https://api.privatbank.ua/p24api/pubinfo?exchange&coursid=5");
            CloseableHttpResponse response = client.execute(request);
            try
            {
                if(response.getStatusLine().getStatusCode() == 200) {
                    HttpEntity entity = response.getEntity();
                    if (entity != null)
                    {
                        String result = EntityUtils.toString(entity);
                        ObjectMapper mapper = new ObjectMapper();
                        List<PrivatDto> privatDtos = mapper.readValue(result, new TypeReference<List<PrivatDto>>(){});
                        return privatDtos;
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
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }
        return new ArrayList<PrivatDto>();
    }
}
