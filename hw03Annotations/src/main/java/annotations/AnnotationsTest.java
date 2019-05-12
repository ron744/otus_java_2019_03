package annotations;

public class AnnotationsTest {

    AnnotationsTest(){
        System.out.println("Call of the constructor");
    }

    @BeforeEach
    public static void before(){
        System.out.println("before test");
    }

    @Test
    public static void test() { System.out.println("test"); }

    @Test
    public static void test1(){
        System.out.println("test1");
    }

    @Test
    public static void test2(){
        System.out.println("test2");
    }

    @AfterEach
    public static void after(){
        System.out.println("after test");
    }
}
