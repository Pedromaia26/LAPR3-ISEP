package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SearchShipMapperTest {

    @Test
    void toDto() {
        SearchShipMapper mapper = new SearchShipMapper();
        Ship ship = new Ship("123456789", "ship", "1000000000", "callSign", "A", "100", "500", "3");
        SearchShipDto expected = new SearchShipDto(ship);
        SearchShipDto valor = SearchShipMapper.toDto(ship);
        assertEquals(expected, valor);
    }
}