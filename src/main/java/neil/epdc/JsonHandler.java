package neil.epdc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonHandler implements ResponseHandler<CurrentCell> {

  private ObjectMapper mapper = new ObjectMapper();
  
  public CurrentCell handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
    HttpEntity entity = response.getEntity();
    if (entity != null) {
      InputStream is = entity.getContent();
      BufferedReader reader = new BufferedReader(new InputStreamReader(is));
      String line = null;
      StringBuilder buffer = new StringBuilder();
      while ((line = reader.readLine()) != null) {
        buffer.append(line).append("\n");
      }
      try {
        return mapper.readValue(buffer.toString(), Response.class).getCurrentCell();
      } catch (Exception ex) {
        throw new IOException("Failed to parse response as a cell:\n" + buffer);
      } finally {
        is.close();
      }
    } else {
      throw new IOException("No response entity");
    }
  }

}
