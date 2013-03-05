package neil.epdc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class Client {

  private String host = "http://www.epdeveloperchallenge.com/api/";
  private HttpClient client = new DefaultHttpClient();
  private JsonHandler handler = new JsonHandler();
  
  /**
   * Creates a new maze.
   * @return
   * @throws IOException
   */
  public CurrentCell newMaze() throws IOException {
    HttpPost post = new HttpPost(host + "init");
    return client.execute(post, handler);
  }
  
  /**
   * Moves one step.
   * @param guid
   * @param direction
   * @return
   * @throws IOException
   */
  public CurrentCell move(String guid, Direction direction) throws IOException {
    List<NameValuePair> params = new ArrayList<NameValuePair>();
    params.add(new BasicNameValuePair("mazeGuid", guid));
    params.add(new BasicNameValuePair("direction", direction.name()));
    UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
    
    HttpPost post = new HttpPost(host + "move");
    post.setEntity(entity);
    
    return client.execute(post, handler);
  }
  
  /**
   * Backtracks to a previously visited location.
   * @param guid
   * @param x
   * @param y
   * @return
   * @throws IOException
   */
  public CurrentCell backtrack(String guid, int x, int y) throws IOException {
    
    List<NameValuePair> params = new ArrayList<NameValuePair>();
    params.add(new BasicNameValuePair("mazeGuid", guid));
    params.add(new BasicNameValuePair("x", String.valueOf(x)));
    params.add(new BasicNameValuePair("y", String.valueOf(y)));
    UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
    
    HttpPost post = new HttpPost(host + "jump");
    post.setEntity(entity);
    
    return client.execute(post, handler);
  }

}
