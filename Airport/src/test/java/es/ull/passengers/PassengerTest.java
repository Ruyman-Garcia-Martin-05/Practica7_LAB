package es.ull.passengers;

import es.ull.flights.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PassengerTest {

    private Passenger passenger;
    private Flight flight;

    @BeforeEach
    void setUp() {
        passenger = new Passenger("P123", "John Doe", "US");
        flight = new Flight("AA1234", 2);
    }

    @Test
    void testPassengerCreation() {
        assertEquals("P123", passenger.getIdentifier());
        assertEquals("John Doe", passenger.getName());
        assertEquals("US", passenger.getCountryCode());
        assertNull(passenger.getFlight());
    }

    @Test
    void testInvalidCountryCode() {
        assertThrows(RuntimeException.class, () -> new Passenger("P124", "Jane Doe", "INVALID"));
    }

    @Test
    void testJoinFlight() {
        passenger.joinFlight(flight);
        assertEquals(flight, passenger.getFlight());
        assertEquals(1, flight.getNumberOfPassengers());
    }

    @Test
    void testLeaveFlight() {
        passenger.joinFlight(flight);
        passenger.joinFlight(null); // Leaving the flight
        assertNull(passenger.getFlight());
        assertEquals(0, flight.getNumberOfPassengers());
    }

    @Test
    void testSwitchFlight() {
        Flight newFlight = new Flight("BB5678", 3);
        passenger.joinFlight(flight);
        passenger.joinFlight(newFlight);
        assertEquals(newFlight, passenger.getFlight());
        assertEquals(0, flight.getNumberOfPassengers());
        assertEquals(1, newFlight.getNumberOfPassengers());
    }
}
