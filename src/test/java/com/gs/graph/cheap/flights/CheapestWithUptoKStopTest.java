package com.gs.graph.cheap.flights;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CheapestWithUptoKStopTest {

  private CheapestWithUptoKStop cheapestWithUptoKStop = new CheapestWithUptoKStop();
  
  @Test
  public void testScnerio1() {
    
    String[][] flights = {
                              {"NYC", "ATL", "1600"},
                              {"NYC", "EWR", "200"},
                              {"NYC", "PTS", "500"},
                              {"PTS", "ATL", "500"},
                              {"PTS", "CHR", "100"},
                              {"CHR", "ATL", "100"},
                              {"EWR", "PTS", "200"}
                         };
    assertEquals(700, cheapestWithUptoKStop.cheapestflight(flights, "NYC", "ATL", 2));
  }
  
  
  @Test
  public void testScnerio2() {
    
    String[][] flights = {
                              {"NYC", "ATL", "1600"},
                              {"NYC", "EWR", "200"},
                              {"NYC", "PTS", "500"},
                              {"PTS", "ATL", "500"},
                              {"PTS", "CHR", "100"},
                              {"CHR", "ATL", "100"},
                              {"EWR", "PTS", "200"}
                         };
    assertEquals(600, cheapestWithUptoKStop.cheapestflight(flights, "NYC", "ATL", 3));
  }
  
  
  @Test
  public void testScnerio3() {    
    String[][] flights = {
                              {"NYC", "ATL", "600"},
                              {"NYC", "EWR", "200"},
                              {"NYC", "PTS", "500"},
                              {"PTS", "ATL", "500"},
                              {"PTS", "CHR", "100"},
                              {"CHR", "ATL", "100"},
                              {"EWR", "PTS", "200"}
                         };
    assertEquals(600, cheapestWithUptoKStop.cheapestflight(flights, "NYC", "ATL", 2));
  }
  
}
