package com.gs.graph.cheap.flights;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CheapestFlightSourceToDestinationTest {

  private CheapestFlightSourceToDestination cheapestFlightSourceToDestination = new CheapestFlightSourceToDestination();
  
  @Test
  public void testScnerio1() {
    String[][] flights = {
                          {"New York", "Philly", "100"},
                          {"New York", "Miami", "600"},
                          {"New York", "Atlanta", "500"},
                          {"Philly", "Atlanta", "200"},
                          {"Atlanta", "Miami", "100"}
                         };
    assertEquals(400, cheapestFlightSourceToDestination.cheapFlight(flights, "New York", "Miami"));
  }
  
}
