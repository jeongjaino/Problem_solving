package Ch16

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val stk = StringTokenizer(br.readLine())
    val numList = IntArray(n)
    val d = IntArray(n){ 1 } // 인원
    repeat(n) { i ->
        numList[i] = stk.nextToken().toInt()
    }
    for(i in 0 until n){
        for(j in 0 until i){
            if(numList[i] < numList[j]){
                d[i] = maxOf(d[i - 1], d[j] + 1)
            }
        }
    }
    print(n - (d.maxOrNull() ?: 0))
}