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
    var result = Integer.MIN_VALUE
    for(i in 1 .. n){
        stk = StringTokenizer(br.readLine())
        schedule[i] = Pair(stk.nextToken().toInt(), stk.nextToken().toInt()) // (소요일, 수익)
    }
    for(i in 1 .. n){
        for(j in 1 .. i){
            // 해당 일에 끝난다면
            if(j + schedule[j].first == i + 1){ // 2일에 시작해서 3일이 소요되면 4일
                d[i] = maxOf(d[j - 1] + schedule[j].second, d[i])
            }else{
                d[i] = maxOf(d[i], d[j - 1])
            }
        }
        result = maxOf(d[i], result)
    }
    print(result)
}
