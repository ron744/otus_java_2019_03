package annotations;

public class AnnotationsTest {

    @BeforeEach
    public void before(){
        System.out.println("before test"); }

    @Test
    public void test1() {
        System.out.println("test1"); }

    @Test
    public void test2(){
        System.out.println("test2");
    }

    @Test
    public void test3(){
        System.out.println("test3");
    }

    @AfterEach
    public void after(){
        System.out.println("after test");
    }
}
