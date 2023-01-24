package Ch16

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val schedule = Array(n + 1){ Pair(0, 0)}
    val d = IntArray(n + 1)
    var stk : StringTokenizer
    for(i in 1 .. n){
        stk = StringTokenizer(br.readLine())
        schedule[i] = Pair(stk.nextToken().toInt(), stk.nextToken().toInt()) // (소요일, 수익)
    }
    // 미리 d에 넣어 놓으면 되나
    for(i in 1 .. n){
        // 이전의 값을 받아와야지
        d[i] = maxOf(d[i], d[i - 1])
        val pri = i + schedule[i].first - 1
        if(pri > n){
            continue
        }
        d[pri] = maxOf(d[pri], d[i - 1] + schedule[i].second)
    }
    print(d[n])
}