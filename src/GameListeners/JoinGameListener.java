package GameListeners;

import GameObjects.GameManager;
import Interfaces.IGameManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;



public class JoinGameListener implements Listener {

    private IGameManager _gameState;
    public JoinGameListener(IGameManager GameState) {
        _gameState = GameState;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerJoin ( PlayerJoinEvent event ) {
        Player player = event.getPlayer();
        player.sendMessage("Welcome to the tournaments!");
        _gameState.IncrementPlayer(player.getName());
        _gameState.UpdateGameState();
    }

    @EventHandler
    public void onPlayerQuit ( PlayerQuitEvent event ) {
        //// TODO: 1/9/2016 Need to implement a way to handle a player quitting during the game started.

        Player player = event.getPlayer();
        Bukkit.getLogger().info("Player Left");
        _gameState.DecrementPlayer(player.getName());
        _gameState.UpdateGameState();
    }


}
