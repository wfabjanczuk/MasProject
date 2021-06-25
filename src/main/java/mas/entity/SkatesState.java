package mas.entity;

public enum SkatesState {
    FUNCTIONAL,
    WITHDRAWN,
    IN_SERVICE,
    REMOVED;

    @Override
    public String toString() {
        switch (this) {
            case REMOVED:
                return "usunięte";
            case WITHDRAWN:
                return "wycofane";
            case IN_SERVICE:
                return "serwisowane";
            case FUNCTIONAL:
                return "sprawne";
            default:
                throw new IllegalArgumentException();
        }
    }
}
