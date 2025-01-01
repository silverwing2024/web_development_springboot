import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JUnitCycleQuiz {

    /*
    문제03 ㄱ
    각각의 테스트를 시작하기 전에 "Hello!"를 출력하는 메서드와 모든 테스트를 끝마치고 "Bye"를 출력하는 메서드를 추가해주세요.
    다음의 테스트가 있다고 가정합니다.
    * */

    @Test
    public void JUnitQuiz3(){
        System.out.println("this is the first test.");
    }

    @Test
    public void JUnitQuiz4(){
        System.out.println("this is the second test.");
    }
    /*
    여기에서 JUnitCycleQuiz 클래스를 전체 실행하면 콘솔에 다음과 같이 출력되려면 어떻게 할지 작성해 보시기 바랍니다.

    실행 예

    Hello!
    this is the first test.
    Hello!
    this is the second test.
    Bye!

    */
    @BeforeEach // 테스트 케이스를 시작하기 전마다 실행
    public void hello() {
        System.out.println("Hello!");
    }

    @AfterAll   // 전체 테스트를 마치고 종료하기 전에 1회 실행
    public static void bye() {
        System.out.println("Bye!");
    }

    /*
    *   src/main/java 에 있는 TestController.java 파일을 엽니다.
    */


}
