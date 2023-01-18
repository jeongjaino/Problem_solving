package Ch14

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private data class Student(
    var name : String,
    var korean: Int,
    var english: Int,
    var math: Int
)

fun main(){

    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val students = Array(n){ Student("", 0, 0, 0) }
    var stk : StringTokenizer
    repeat(n){ i ->
        stk = StringTokenizer(br.readLine())
        students[i].name = stk.nextToken()
        students[i].korean = stk.nextToken().toInt()
        students[i].english = stk.nextToken().toInt()
        students[i].math = stk.nextToken().toInt()
    }

    // 국어 점수가 내림 차순
    //국어 점수가 같으면 영어 점수가 오름 차순
    //국어 점수와 영어 점수가 같으면 수학 점수가 내림 차순
    //모든 점수가 같으면 이름이 사전 순으로 오름 차순

    students.sortWith(
        compareBy<Student>{ -it.korean }
            .thenBy { it.english }
            .thenBy { -it.math }
            .thenBy { it.name }
    )
    repeat(n){ i ->
        println(students[i].name)
    }
}