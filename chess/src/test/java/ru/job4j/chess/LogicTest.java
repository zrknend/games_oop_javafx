package ru.job4j.chess;

import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.black.BishopBlack;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LogicTest {

    @Ignore
    @Test
    public void move()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.C1));
        logic.move(Cell.C1, Cell.H6);
    }

    @Test
    public void BishopBlackPositionTrue() {
        BishopBlack figure = new BishopBlack(Cell.A8);
        assertThat(Cell.A8, is(figure.position()));
    }

    @Test
    public void BishopBlackCopyTrue() {
        BishopBlack figure1 = new BishopBlack(Cell.A3);
        Figure figure2 = figure1.copy(Cell.A3);
        assertThat(Cell.A3, is(figure2.position()));
    }

    @Test
    public void BishopBlackWayTrue() {
        BishopBlack figure = new BishopBlack(Cell.C1);
        Cell[] expectedWay = figure.way(Cell.C1, Cell.G5);
        Cell[] trueWay = {Cell.D2, Cell.E3, Cell.F4, Cell.G5};
        assertThat(expectedWay, is(trueWay));
    }

    @Test(expected = IllegalStateException.class)
    public void BishopBlackWayFalse() {
        BishopBlack figure = new BishopBlack(Cell.C1);
        Cell[] expectedWay = figure.way(Cell.C1, Cell.C2);
    }

    @Test(expected = FigureNotFoundException.class)
        public void FigureNotFound()
            throws FigureNotFoundException, ImpossibleMoveException, OccupiedCellException {
        Logic logic = new Logic();
        logic.move(Cell.C1, Cell.C2);
    }

    @Test(expected = ImpossibleMoveException.class)
        public void WhenAFigureStandsInTheWay()
            throws FigureNotFoundException, ImpossibleMoveException, OccupiedCellException {
        Logic logic = new Logic();
        BishopBlack figure1 = new BishopBlack(Cell.A1);
        logic.add(figure1);
        BishopBlack figure2 = new BishopBlack(Cell.B2);
        logic.add(figure2);
        logic.move(Cell.A1, Cell.C3);
    }
}