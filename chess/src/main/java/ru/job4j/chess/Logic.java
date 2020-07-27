package ru.job4j.chess;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import java.util.Arrays;

/**
 * //TODO add comments.
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Logic {
    private final Figure[] figures = new Figure[32];
    private int index = 0;

    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    public void move(Cell source, Cell dest)
            throws FigureNotFoundException, ImpossibleMoveException, OccupiedCellException {
        int index = this.findBy(source);
        Cell[] steps = this.figures[index].way(source, dest);
        for (Cell cell : steps) {
            for (Figure figure : this.figures) {
                if (cell == figure.position()) {
                    throw new ImpossibleMoveException();
                }
            }
        }
        if (!isFree(steps)) {
            throw new OccupiedCellException();
        }
        this.figures[index] = this.figures[index].copy(dest);
    }

    private boolean isFree(Cell[] steps) throws OccupiedCellException {
        for (int index = 0; index < figures.length; index++) {
            for (int jindex = 0; jindex < steps.length; jindex++) {
                if (figures[index].equals(steps[jindex])) {
                    //throw new OccupiedCellException();
                    return false;
                }
            }
        }
        return true;
    }

    public void clean() {
        Arrays.fill(this.figures, null);
        this.index = 0;
    }

    private int findBy(Cell cell) throws FigureNotFoundException {
        for (int index = 0; index != this.figures.length; index++) {
            if (this.figures[index] != null && this.figures[index].position().equals(cell)) {
                return index;
            }
        }
        throw new FigureNotFoundException();
    }
}
