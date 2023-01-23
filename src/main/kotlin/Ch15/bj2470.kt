package Ch15

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.math.abs

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val numList = IntArray(n)
    val stk = StringTokenizer(br.readLine())
    repeat(n){ i ->
        numList[i] = stk.nextToken().toInt()
    }
    numList.sort()
    var start = 0
    var end = numList.lastIndex
    var temp = Integer.MAX_VALUE
    var result = Pair(numList[start], numList[end])
    // 0에 가깝다는 것은 절댓값으로 제일 작은 값을 의미
    while(start < end){
        val sum = numList[start] + numList[end]
        if(abs(sum) < abs(temp)){ // 새로운 작은 값
            temp = sum
            result = Pair(numList[start], numList[end])
        }
        // 5 6 7 10
        if(sum > 0){ // 합한 값이 양수 일 때
            end -= 1
        }
        // -7 -6 -5 -1
        else if(sum < 0){ // 합한 값이 음수 일 때
          start += 1
        }
        else{
            break
        }
    }
    println(result.first)
    print(result.second)
}