package models.wall;

import models.maze.MazeObject;

public interface Wall extends MazeObject {

    /**
     * make the cell either breakable or not .
     * @param breakable if true then make the cell breakable , else make it unbreakable .
     */
    void setBreakable(boolean breakable);

    /**
     * Get wither the cell is breakable or not .
     * @return true if the cell is breakable or false if it is unbreakable .
     */
    boolean isBreakable();

    /**
     * Get the health of the cell if breakable.
     * @return the health bound by 0 - 100
     */
    int getHealth();

    /**
     * Affect the cell health by some effect.
     * if the effect causes the health to reach or surpass zero the cell will break,
     * when a cell break it is replaced by either a bomb or space randomly.
     * @param effect the magnitude of the effect.
     * @return true if the health is affected by any amount in that range ]0,effect], false otherwise.
     */
    boolean affectHealthBy(int effect);


}
