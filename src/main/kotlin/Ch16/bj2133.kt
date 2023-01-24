package Ch16

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val d = IntArray(n + 1)
    d[0] = 1 // d[2] = 3, d[4] = 미리 지정하고 해도 됨
    for(i in 2 .. n){
        d[i] = d[i - 2] * 3
        for (j in 4..n step 2) { // 4이상의 짝수
            if(i < j){
                break
            }
            d[i] += 2 * d[i - j]
        }
    }
    print(d[n])
}