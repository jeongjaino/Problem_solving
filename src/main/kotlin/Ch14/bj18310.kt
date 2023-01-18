package Ch14

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.abs

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val numList = br.readLine().split(" ").map{ it.toInt() }.sorted()
    // 인덱스 설정
    if(n % 2 == 0){ // 짝수 일 때 0123
        val leftValue = getDistance(n / 2 - 1, numList)
        val rightValue = getDistance(n / 2, numList)
        if(rightValue < leftValue){
            print(numList[n / 2])
        }else{
            print(numList[n / 2 - 1])
        }
    }else{ // 홀수 일 때
        print(numList[n / 2])
    }
}
fun getDistance(index : Int, numList: List<Int>): Int{
    var result = 0
    repeat(numList.size){ j ->
        result += abs(numList[index] - numList[j]) // 거리를 모음
    }
    return result
}