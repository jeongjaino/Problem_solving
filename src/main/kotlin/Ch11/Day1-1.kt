import java.io.BufferedReader
import java.io.InputStreamReader

fun main(){

    val br = BufferedReader(InputStreamReader(System.`in`))

    val num = br.readLine().toInt() // 총 인원
    val scaredList = mutableListOf<Int>()
    br.readLine().split(" ").forEach{
        scaredList.add(it.toInt())
    } // 공포도 받기
    scaredList.sort() // 오름차순 정렬

    var result = 0
    while(scaredList.isNotEmpty()){ // 인원이 존재할 때
        if(scaredList.size >= scaredList.first()){ // 현재 인원이 최대공포도보다 클 때
            result += 1 // 그룹 하나 추가
            repeat(scaredList.first()){
                scaredList.removeAt(0) // 마지막 원소(최댓값) 삭제
            }
        }else{ // 총 인원보다 최댓값이 더 크다면
            print(result)
            return
        }
    }
    print(result)
}