package mas.service;

import mas.entity.SkatesState;

import java.util.Arrays;
import java.util.List;

public class SkatesStateService {
    public static List<SkatesState> getPossibleStatesAfterService() {
        return Arrays.asList(SkatesState.FUNCTIONAL, SkatesState.WITHDRAWN, SkatesState.REMOVED);
    }
}
