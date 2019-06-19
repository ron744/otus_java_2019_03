public interface ChainLink {
    void setNextChainLink(ChainLink nextChain);
    void requestBalance();
}
