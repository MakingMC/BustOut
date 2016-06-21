import GameListeners.JoinGameListener;
import GameObjects.GAME_STATE;
import GameObjects.GameManager;
import Interfaces.IGameManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


/**
 * Created by RogerB on 1/9/2016.
 */
public final class Main extends JavaPlugin{
    private IGameManager _gameState;
    private Location Lobby;

    @Override
    public void onEnable() {
        getLogger().info("Enabling...");

        World world = getServer().getWorlds().get(0);
        Lobby = new Location(world, 160, 76, 90);

        _gameState = new GameManager(4, Lobby);
        Bukkit.getServer().getPluginManager().registerEvents(new JoinGameListener(_gameState), this);
        getLogger().info("Enabled");
        for (Player p: Bukkit.getServer().getOnlinePlayers()
                ) {
            _gameState.IncrementPlayer(p.getName());
            _gameState.UpdateGameState();
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player)sender;

        switch (label.toLowerCase()) {

            case "currentqueue":
            {
                player.sendMessage(GameManager._currentQueue.toString());
                break;
            }
            case "getstate":
            {
                player.sendMessage(String.format("The current game state is: %s", _gameState.GetCurrentState().toString()));
                break;
            }

            case "getplayercount":
            {
                player.sendMessage(String.format("The current game player count is: %s", _gameState.GetCurrentPlayerCount()));
                break;
            }
            case "start":
            {
                if(_gameState.GetCurrentState() == GAME_STATE.GAME_STARTING){
                    _gameState.StartGame();
                }
                else {
                    player.sendMessage(String.format("Game is not ready to start."));
                }
                break;
            }
            case "end":
            {
                if(_gameState.GetCurrentState() == GAME_STATE.GAME_RUNNING){
                    _gameState.EndGame();
                }
                else {
                    player.sendMessage(String.format("Game is not started."));
                }
                break;
            }

            default:
                break;
        }

        return true;
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabling...");
        getLogger().info("Disabled.");
        for (Player p: Bukkit.getServer().getOnlinePlayers()
                ) {
            _gameState.DecrementPlayer(p.getName());
            _gameState.UpdateGameState();
        }
    }

    @Override
    public void onLoad() {
        getLogger().info("Loading...");
        getLogger().info("Loaded.");
    }
}
