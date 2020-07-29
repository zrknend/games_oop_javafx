package ru.job4j.chess.firuges.black;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

public class BishopBlack implements Figure {
    private final Cell position;

    public BishopBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) {
        if (!isDiagonal(position, dest)) {
            throw new IllegalStateException(
                    String.format("Could not move by diagonal from %s to %s", source, dest)
            );
        }
        int size = Math.abs(source.getX() - dest.getX());
        Cell[] steps = new Cell[size];
        int deltaX = source.getX() - dest.getX() < 0 ? +1 : -1;
        int deltaY = source.getY() - dest.getY() < 0 ? +1 : -1;
        int x = source.getX();
        int y = source.getY();
        for (int index = 0; index < size; index++) {
            x += deltaX;
            y += deltaY;
            steps[index] = Cell.findBy(x, y);
        }

        return steps;
    }

    public boolean isDiagonal(Cell source, Cell dest) {
        return  Math.abs(source.getX() - dest.getX()) == Math.abs(source.getY() - dest.getY());

    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}
