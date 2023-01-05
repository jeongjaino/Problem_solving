import java.io.BufferedReader
import java.io.InputStreamReader

fun main(){

    val br = BufferedReader(InputStreamReader(System.`in`))
    val numList = br.readLine().trim()
    val num = mutableListOf<Int>()
    numList.forEach {
        num.add(it.digitToInt())
    }

    var previous = num[1]
    var zeroCount = 0
    var oneCount = 0

    // 구간이 몇개 있는지를 확인
    if(previous == 0){
        zeroCount += 1
    }else{
        oneCount += 1
    }
    for(i in 2 until num.size - 1){
        if(num[i] != previous){
            if(previous == 0){
                oneCount += 1
                previous = 1
            }else{
                zeroCount += 1
                previous = 0
            }
        }
    }

    // 0인 경우의 수
    if(num.count{ it == 0} < num.count{ it == 1}){
        print(zeroCount)
    }else{
        print(oneCount)
    }
}