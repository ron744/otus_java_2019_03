package annotations;

public class AnnotationsTest {

    @BeforeEach
    public static void before(){ System.out.println("before test"); }

    @Test
    public static void test1() { System.out.println("test1"); }

    @Test
    public static void test2(){
        System.out.println("test2");
    }

    @Test
    public static void test3(){
        System.out.println("test3");
    }

    @AfterEach
    public static void after(){
        System.out.println("after test");
    }
}
