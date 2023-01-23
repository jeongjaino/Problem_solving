import java.io.BufferedReader
import java.io.InputStreamReader

fun main(){

    val br = BufferedReader(InputStreamReader(System.`in`))

    val numList = br.readLine().map{ it.digitToInt() }
    var previous = numList[0]
    var zeroCount = 0 // 0의 개수
    var oneCount = 0 // 1의 개수

    // 구간이 몇개 있는지를 확인
    if(previous == 0){
        zeroCount += 1
    }else{
        oneCount += 1
    }
    for(i in 1 until numList.size){
        if(numList[i] != previous){ // 이전 숫자와 현재 숫자가 다른 경우
            if(previous == 0){
                oneCount += 1
                previous = 1
            }else{
                zeroCount += 1
                previous = 0
            }
        }
    }
    if(zeroCount < oneCount) print(zeroCount) else print(oneCount)
}