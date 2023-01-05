import java.io.BufferedReader
import java.io.InputStreamReader

fun main(){

    val br = BufferedReader(InputStreamReader(System.`in`))

    val num = br.readLine().toInt()

    val scaredList = mutableListOf<Int>()
    br.readLine().split(" ").forEach{
        scaredList.add(it.toInt())
    }
    scaredList.sort() // 오름차순 정렬

    var result = 0
    while(scaredList.isNotEmpty()){ // 인원이 존재할 때
        if(num - scaredList.last() >= 0){ // 총 인원보다 최댓값이 더 작을 때
            result += 1 // 그룹 하나 추가
            repeat(scaredList.last()){
                scaredList.removeAt(scaredList.lastIndex) // 마지막 원소(최댓값) 삭제
            }
        }else{ // 총 인원보다 최댓값이 더 크다면
            print(result)
            return
        }
    }
    print(result)
}