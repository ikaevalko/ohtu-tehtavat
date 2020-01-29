package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatisticsTest {
    
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        
        stats = new Statistics(readerStub);
    }  
    
    @Test
    public void searchFindsPlayer() {
        
        Player player = stats.search("Kurri");
        assertEquals("Kurri", player.getName());
    }
    
    @Test
    public void searchRetursNullIfPlayerNotFound() {
        
        assertEquals(null, stats.search("Selänne"));
    }
    
    @Test
    public void teamReturnsCorrectPlayers() {
        
        List<Player> team = stats.team("EDM");
        
        assertTrue(containsPlayer(team, "Semenko"));
        assertTrue(containsPlayer(team, "Kurri"));
        assertTrue(containsPlayer(team, "Gretzky"));
    }
    
    @Test
    public void topScorersReturnsCorrectPlayers() {
        
        List<Player> tops = stats.topScorers(4);
        
        assertEquals("Gretzky", tops.get(0).getName());
        assertEquals("Lemieux", tops.get(1).getName());
        assertEquals("Yzerman", tops.get(2).getName());
        assertEquals("Kurri", tops.get(3).getName());
        assertEquals("Semenko", tops.get(4).getName()); 
   }
    
    private boolean containsPlayer(List<Player> team, String name) {
        
        for(Player p : team) {
            
            if (p.getName().equals(name)) {
                return true;
            }
        }
        
        return false;
    }
}
