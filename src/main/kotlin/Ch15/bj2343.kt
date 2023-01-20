package Ch15

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    var stk = StringTokenizer(br.readLine())
    val n = stk.nextToken().toInt()
    val m = stk.nextToken().toInt()
    var start = 0
    var end = 0
    val guitarArr = IntArray(n)
    stk = StringTokenizer(br.readLine())
    repeat(n){ i ->
        guitarArr[i] = stk.nextToken().toInt()
        end += guitarArr[i]
        start = maxOf(start, guitarArr[i])
    }
    // m개의 테잎을 만든 다음. 해당 테잎의 맥스 값을 합 중 최댓 값을 기준으로 바이너리 서치를 하는 거임.
    // 하나의 테잎은 총 앨범의 합 / m <= v <= 총 앨범의 합
    // 순서는 안 꼬이는게 핵심,
    while(start <= end){
        val mid = (start + end) / 2 // 최댓값임. 애보다 무조건 작아야 함.
        var count = 0 // 몇 개로 분리 되는지
        var temp = 0 // 가중 합
        for(i in guitarArr.indices){
            if(mid < guitarArr[i] + temp){ // mid 보다 temp + i 가 더 클 경우
                temp = 0 // temp 초기화
                count += 1
            }
            temp += guitarArr[i]
        }
        if(temp != 0){
            count += 1
        }
        // count 갯수가 적은 경우 mid값이 크다, count 갯수가 큰 경우 mid값이 작다.
        if(count <= m){
            end = mid - 1
        }
        else{
            start = mid + 1
        }
    }
    print(start)
}