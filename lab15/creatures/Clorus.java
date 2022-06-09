package creatures;

import huglife.Action;
import huglife.Creature;
import huglife.Direction;
import huglife.Occupant;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import static huglife.HugLifeUtils.randomEntry;

public class Clorus extends Creature {

    /** red color. */
    private int r;
    /** green color. */
    private int g;
    /** blue color. */
    private int b;

    private static final Double moveEnergyLose = 0.03;
    private static final Double stayEnergyLose = 0.01;
    private static final Double moveProbability = 0.5;


    public Clorus(double e) {
        super("clorus");
        r = 34;
        g = 0;
        b = 231;
        energy = e;
    }

    @Override
    public void move() {
        energy -= moveEnergyLose;
    }

    @Override
    public void attack(Creature c) {
        energy += c.energy();

    }

    @Override
    public Creature replicate() {
        energy *= 0.5;
        Clorus cbaby = new Clorus(energy);
        return cbaby;
    }

    @Override
    public void stay() {
        energy -= stayEnergyLose;
    }

    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        List<Direction> emptyNeighbors = new ArrayList<>();
        List<Direction> plipNeighbors = new ArrayList<>();
        boolean anyPlip = false;

        for (Direction dir : neighbors.keySet()) {
            if (neighbors.get(dir).name().equals("empty")) {
                emptyNeighbors.add(dir);
            } else if (neighbors.get(dir).name().equals("plip")) {
                plipNeighbors.add(dir);
                anyPlip = true;
            }
        }

        if (emptyNeighbors.isEmpty()) {
            return new Action(Action.ActionType.STAY);
        } else if (anyPlip) {
            return new Action(Action.ActionType.ATTACK, randomEntry(plipNeighbors));
        } else if (energy >= 1.0) {
            return new Action(Action.ActionType.REPLICATE, randomEntry(emptyNeighbors));
        } else {
            return new Action(Action.ActionType.MOVE, randomEntry(emptyNeighbors));
        }
    }


    @Override
    public Color color() {
        return color(r, g, b);
    }
}
