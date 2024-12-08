package es.ull.flights;

import es.ull.passengers.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlightTest {

    private Flight flight;
    private Passenger passenger;

    @BeforeEach
    void setUp() {
        flight = new Flight("AA1234", 2);
        passenger = new Passenger("P123", "John Doe", "US");
    }

    @Test
    void testFlightCreation() {
        assertEquals("AA1234", flight.getFlightNumber());
        assertEquals(0, flight.getNumberOfPassengers());
    }

    @Test
    void testInvalidFlightNumber() {
        assertThrows(RuntimeException.class, () -> new Flight("INVALID", 2));
    }

    @Test
    void testAddPassenger() {
        assertTrue(flight.addPassenger(passenger));
        assertEquals(1, flight.getNumberOfPassengers());
    }

    @Test
    void testAddPassengerExceedingSeats() {
        flight.addPassenger(new Passenger("P124", "Jane Doe", "US"));
        flight.addPassenger(new Passenger("P125", "Alice Doe", "US"));
        assertThrows(RuntimeException.class, () -> flight.addPassenger(new Passenger("P126", "Bob Doe", "US")));
    }

    @Test
    void testRemovePassenger() {
        flight.addPassenger(passenger);
        assertTrue(flight.removePassenger(passenger));
        assertEquals(0, flight.getNumberOfPassengers());
    }
}
