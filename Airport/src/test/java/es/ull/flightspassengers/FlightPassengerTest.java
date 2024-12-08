package es.ull.flightspassengers;

import es.ull.flights.Flight;
import es.ull.passengers.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlightPassengerTest {

    private Flight flight1;
    private Flight flight2;
    private Passenger passenger;

    @BeforeEach
    void setUp() {
        flight1 = new Flight("AA1234", 2);
        flight2 = new Flight("BB5678", 3);
        passenger = new Passenger("P123", "John Doe", "US");
    }

    @Test
    void testPassengerJoinsFlight() {
        passenger.joinFlight(flight1);
        assertEquals(flight1, passenger.getFlight());
        assertEquals(1, flight1.getNumberOfPassengers());
    }

    @Test
    void testPassengerSwitchesFlights() {
        passenger.joinFlight(flight1);
        passenger.joinFlight(flight2);
        assertEquals(flight2, passenger.getFlight());
        assertEquals(0, flight1.getNumberOfPassengers());
        assertEquals(1, flight2.getNumberOfPassengers());
    }

    @Test
    void testPassengerLeavesFlight() {
        passenger.joinFlight(flight1);
        passenger.joinFlight(null); // Leaving the flight
        assertNull(passenger.getFlight());
        assertEquals(0, flight1.getNumberOfPassengers());
    }

    @Test
    void testAddMultiplePassengersToFlight() {
        Passenger passenger2 = new Passenger("P124", "Jane Doe", "US");
        Passenger passenger3 = new Passenger("P125", "Alice Doe", "US");

        flight1.addPassenger(passenger);
        flight1.addPassenger(passenger2);

        assertEquals(2, flight1.getNumberOfPassengers());
        assertThrows(RuntimeException.class, () -> flight1.addPassenger(passenger3)); // Exceed seat limit
    }

    @Test
    void testRemovePassengerFromFlight() {
        flight1.addPassenger(passenger);
        flight1.removePassenger(passenger);

        assertEquals(0, flight1.getNumberOfPassengers());
        assertNull(passenger.getFlight());
    }
}
