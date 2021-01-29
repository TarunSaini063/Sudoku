package Sudoku.buildlogic;

import Sudoku.computationlogic.GameLogic;
import Sudoku.persistence.LocalStorageImpl;
import Sudoku.problemdomain.IStorage;
import Sudoku.problemdomain.SudokuGame;
import Sudoku.userinterface.IUserInterfaceContract;
import Sudoku.userinterface.logic.ControlLogic;

import java.io.IOException;

public class SudokuBuildLogic {
    public static void build(IUserInterfaceContract.View userInterface) throws IOException {
        SudokuGame initialState;
        IStorage storage = new LocalStorageImpl();

        try {
            initialState = storage.getGameData();
        } catch (IOException e) {
            initialState = GameLogic.getNewGame();
            storage.updateGameData(initialState);
        }

        IUserInterfaceContract.EventListener uiLogic = new ControlLogic(storage, userInterface);
        userInterface.setListener(uiLogic);
        userInterface.updateBoard(initialState);
    }
}
