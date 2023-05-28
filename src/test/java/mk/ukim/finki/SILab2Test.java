package mk.ukim.finki;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SILab2Test {
    @Test
    void everyBranch() {
        RuntimeException ex;
        ex = assertThrows(RuntimeException.class,() -> SILab2.function(null, Collections.emptyList()));
        assertTrue(ex.getMessage().contains("Mandatory information missing!"));

        assertTrue(SILab2.function(new User(null, "Ana.Banana", "ana.taseva56@gmail.com"), Collections.emptyList()));

        assertFalse(SILab2.function(new User("Ana567", "Softversko", "ana.taseva56@gmail.com"), new ArrayList<>(List.of(
                new User("Ana567", "Softversko", "ana.taseva56@gmail.com"),
                new User("Miki23", "Inzenerstvo", "miki.milevski@gmail.com")))));

        assertFalse(SILab2.function(new User("Ana567", "12345", "ana123"),Collections.emptyList()));

        assertFalse(SILab2.function(new User("Ana567", "Leto Petok", "ana123"),Collections.emptyList()));

    }

    @Test
    void multipleConditions(){
        RuntimeException ex;
        ex = assertThrows(RuntimeException.class,() -> SILab2.function(null, Collections.emptyList()));
        assertTrue(ex.getMessage().contains("Mandatory information missing!"));

        ex = assertThrows(RuntimeException.class,() -> SILab2.function(new User("eli5",null,"elipetrova@gmail.com"), Collections.emptyList()));
        assertTrue(ex.getMessage().contains("Mandatory information missing!"));

        ex = assertThrows(RuntimeException.class,() -> SILab2.function(new User("eli5","12345678",null), Collections.emptyList()));
        assertTrue(ex.getMessage().contains("Mandatory information missing!"));

        assertTrue(SILab2.function(new User(null, "Ana.Banana", "ana.taseva56@gmail.com"), Collections.emptyList()));
    }

}