package Interfaces;

import GameObjects.GAME_STATE;

/**
 * Created by RogerB on 1/9/2016.
 */
public interface IGameManager {
    GAME_STATE GetCurrentState();
    void SetCurrentState(GAME_STATE state);
    void UpdateGameState();
    void IncrementPlayer(String name);
    void DecrementPlayer(String name);
    int GetCurrentPlayerCount();
    void ResetGame();
    void StartGame();
    void EndGame();
}
