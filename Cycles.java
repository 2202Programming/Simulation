public enum Cycles {
    SLOW(Constants.SLOW_CYCLE), MEDIUM(Constants.MEDIUM_CYCLE), FAST(Constants.FAST_CYCLE);

    private int numberOfCycles;

    private Cycles(int numberOfCycles) {
        this.numberOfCycles = numberOfCycles;
    }

    public int getNumberOfCycles() {
        return numberOfCycles;
    }
}
