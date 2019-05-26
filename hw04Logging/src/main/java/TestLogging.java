public class TestLogging implements TestLoggingInterface {
    @Override
    @Log
    public void calculation(int p1, int p2) { }

    @Override
    public void print(String string) { }
}
