package day1

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val stk = StringTokenizer(br.readLine())
    var n = stk.nextToken().toInt() // 볼링공의 개수
    val m = stk.nextToken().toInt() // 볼링공의 최대 무게
    val weightArray = IntArray(11)
    br.readLine().split(" ").forEach{
        weightArray[it.toInt()] += 1
    } // 볼링공 무게의 리스트

    var result = 0
    repeat(m){
        n -= weightArray[it] // 전체 개수 중 중복 제거
        result += weightArray[it] * n // 선택할 수 있는 경우의 수 * 동일한 무게의 볼링공의 수
    }
    print(result)
}