package GameObjects;

import Interfaces.IGameManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;


public class GameManager implements IGameManager {
    private GAME_STATE _state;
    private int _gameReadyCount;
    Location LobbySpawn;
    public static List<String> _currentQueue = new ArrayList<>();
    public GameManager(int playersNeeded, Location lobby) {
        SetCurrentState(GAME_STATE.WAITING_FOR_PLAYERS);
        _gameReadyCount = playersNeeded;
        LobbySpawn = lobby;
    }

    public GAME_STATE GetCurrentState() {
        return _state;
    }

    public void SetCurrentState(GAME_STATE state) {
        _state = state;
    }

    public void UpdateGameState() {
        switch (_state) {
            case WAITING_FOR_PLAYERS: {
                if (_currentQueue.size() == _gameReadyCount) {
                    SetCurrentState(GAME_STATE.GAME_STARTING);


                }
                break;
            }
            case GAME_STARTING:
                break;
            case GAME_RUNNING:
                break;
            case GAME_FINISHED:
                break;
            default:
                break;
        }

        //Always check to see if someone left and put the game into a waiting state if someone leaves.
        if (_currentQueue.size() < _gameReadyCount)
            SetCurrentState(GAME_STATE.WAITING_FOR_PLAYERS);
    }

    public void IncrementPlayer(String name) {
        _currentQueue.add(name);
    }

    public void DecrementPlayer(String name) {
        _currentQueue.remove(name);
    }

    public int GetCurrentPlayerCount() {
        return _currentQueue.size();
    }

    public void EndGame() {
        if(_state == GAME_STATE.GAME_RUNNING){
            SetCurrentState(GAME_STATE.GAME_FINISHED);
            ResetGame();
        }
    }

    public void ResetGame() {
        SetCurrentState(GAME_STATE.WAITING_FOR_PLAYERS);
        _currentQueue.clear();
        for (Player player: Bukkit.getServer().getOnlinePlayers()
             ) {
            IncrementPlayer(player.getName());
            UpdateGameState();
            Bukkit.getServer().getPlayer(player.getName()).teleport(LobbySpawn);
        }

    }

    public void StartGame() {
        if(_state == GAME_STATE.GAME_STARTING){
            Location Arena = new Location(Bukkit.getWorlds().get(0), 80, 80, 80);
            Location Arena2 = new Location(Bukkit.getWorlds().get(0), 90, 90, 90);
            Location Arena3 = new Location(Bukkit.getWorlds().get(0), 100, 100, 100);
            Location Arena4 = new Location(Bukkit.getWorlds().get(0), 110, 110, 110);
            Location Arena5 = new Location(Bukkit.getWorlds().get(0), 120, 120, 120);
            Location Arena6 = new Location(Bukkit.getWorlds().get(0), 130, 130, 130);
            Location Arena7 = new Location(Bukkit.getWorlds().get(0), 140, 140, 140);
            Location Arena8 = new Location(Bukkit.getWorlds().get(0), 150, 150, 150);
            int unit;
            for (unit = 0; unit < _gameReadyCount/2; unit++) {
                switch (unit) {
                    case 0: {
                        Bukkit.getServer().getPlayer(_currentQueue.get(0)).teleport(Arena);
                        Bukkit.getServer().getPlayer(_currentQueue.get(1)).teleport(Arena);
                        break;
                    }
                    case 1: {
                        Bukkit.getServer().getPlayer(_currentQueue.get(2)).teleport(Arena2);
                        Bukkit.getServer().getPlayer(_currentQueue.get(3)).teleport(Arena2);
                        break;
                    }
                    case 2: {
                        Bukkit.getServer().getPlayer(_currentQueue.get(4)).teleport(Arena3);
                        Bukkit.getServer().getPlayer(_currentQueue.get(5)).teleport(Arena3);
                        break;
                    }
                    case 3: {
                        Bukkit.getServer().getPlayer(_currentQueue.get(6)).teleport(Arena4);
                        Bukkit.getServer().getPlayer(_currentQueue.get(7)).teleport(Arena4);
                        break;
                    }
                    case 4: {
                        Bukkit.getServer().getPlayer(_currentQueue.get(8)).teleport(Arena5);
                        Bukkit.getServer().getPlayer(_currentQueue.get(9)).teleport(Arena5);
                        break;
                    }
                    case 5: {
                        Bukkit.getServer().getPlayer(_currentQueue.get(10)).teleport(Arena6);
                        Bukkit.getServer().getPlayer(_currentQueue.get(11)).teleport(Arena6);
                        break;
                    }
                    case 6: {
                        Bukkit.getServer().getPlayer(_currentQueue.get(12)).teleport(Arena7);
                        Bukkit.getServer().getPlayer(_currentQueue.get(13)).teleport(Arena7);
                        break;
                    }
                    case 7: {
                        Bukkit.getServer().getPlayer(_currentQueue.get(14)).teleport(Arena8);
                        Bukkit.getServer().getPlayer(_currentQueue.get(15)).teleport(Arena8);
                        break;
                    }
                    default: {
                        Bukkit.getServer().broadcastMessage("An odd number of players are on.");
                        break;
                    }
                }
            }


        }
        // TODO: 1/9/2016 check some new stuff in 
        SetCurrentState(GAME_STATE.GAME_RUNNING);
    }
}
