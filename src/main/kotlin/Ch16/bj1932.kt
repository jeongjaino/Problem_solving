package Ch16

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    var stk : StringTokenizer
    val d = Array(n){ IntArray(n) }
    val numList = Array(n){ IntArray(n) }
    repeat(n){ i ->
        stk = StringTokenizer(br.readLine())
        for(j in 0 .. i){
            numList[i][j] = stk.nextToken().toInt()
            if(i == 0){ // 맨 위
                d[i][j] = numList[i][j]
                continue
            }
            when (j) {
                0 -> {
                    d[i][j] = d[i - 1][j] + numList[i][j]
                }
                in 1 until i -> {
                    d[i][j] = maxOf(d[i - 1][j - 1], d[i - 1][j]) + numList[i][j]
                }
                i -> {
                    d[i][j] = d[i - 1][j - 1] + numList[i][j]
                }
            }
        }
    }
    print((d[n -1].maxOrNull()))
}