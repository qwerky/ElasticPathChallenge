package neil.epdc;

import java.io.IOException;
import java.io.InputStream;

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
      try {
        return mapper.readValue(is, Response.class).getCurrentCell();
      } finally {
        is.close();
      }
    } else {
      throw new IOException("No response entity");
    }
  }

}
