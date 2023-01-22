package Ch15

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.HashMap

// 마이너스와 0 인값이 입력이 가능
// 중복인 입력이 들어올 수 있음.
fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    var stk = StringTokenizer(br.readLine())
    val n = stk.nextToken().toInt()
    val numList = IntArray(n)
    val numMap = HashMap<Int, Int>()
    stk = StringTokenizer(br.readLine())
    repeat(n){ i ->
        numList[i] = stk.nextToken().toInt()
        if(numMap.containsKey(numList[i])){ // 만약 중복된 키라면
            numMap[numList[i]] = numMap[numList[i]]!!.plus(1)
        }else{
            numMap[numList[i]] = 1
        }
    }
    var result = 0
    // 두 수를 뽑아 값을 만들 수 있음.
    for(i in 0 until numList.size - 1){
        for(j in i + 1 until numList.size){ // 순서는 상관 없음.
            val sum = numList[i] + numList[j]
            if(numMap.containsKey(sum)){ // 만든 값이 키 중에 있다면
                // 1번 예외 0이 2개인데, 0 + 0인 상황
                if(numList[i] == 0 && numList[j] == 0){
                    if(numMap[sum]!! >= 3){ // 0이 3개 이상인 경우는 가능
                        result += numMap[sum]!!
                        numMap.remove(sum)
                    }
                }
                // 2번 예외 1이 1개, 0 + 1 = 1인 상황 -> 자기 자신을 포함하는 상황
                else if(numList[i] == 0 || numList[j] == 0){
                    if(numMap[sum]!! >= 2){ // 중복된 수면 가능
                        result += numMap[sum]!!
                        numMap.remove(sum)
                    }
                }
                else{
                    result += numMap[sum]!! // 중복된 횟수만큼 더함
                    numMap.remove(sum) // 만든 값이 다른 두 수에 의해 만들어 지지 않도록 제거
                }
            }
        }
    }
    print(result)
}