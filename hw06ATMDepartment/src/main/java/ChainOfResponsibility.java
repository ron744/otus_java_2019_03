public interface ChainOfResponsibility {
    void nextBalance(ChainOfResponsibility nextChain);
    void requestBalance();
}
