package Ch16

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    var stk = StringTokenizer(br.readLine())
    val n = stk.nextToken().toInt()
    val k = stk.nextToken().toInt()
    val coinArr = IntArray(n)
    val d = IntArray(k + 1) // index 를 만들 수 있는 갯수가 value
    repeat(n){ i ->
        stk = StringTokenizer(br.readLine())
        coinArr[i] = stk.nextToken().toInt()
    }
    coinArr.forEach { coin ->
        for(i in 1 .. k){
            if(i == coin){
                d[i] += 1
            }
            else if(i > coin){
                d[i] += d[i - coin]
            }
        }
    }
    print(d[k])
}