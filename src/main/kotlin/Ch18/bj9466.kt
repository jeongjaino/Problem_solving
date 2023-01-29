package Ch18

import java.io.BufferedReader
import java.io.InputStreamReader

private lateinit var visited : BooleanArray
private lateinit var finished : BooleanArray
private lateinit var students : IntArray
private var n = 0
private var result = 0

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val tc = br.readLine().toInt()
    repeat(tc){
        n = br.readLine().toInt()
        students = IntArray(n + 1)
        visited = BooleanArray(n + 1)
        finished = BooleanArray(n + 1)
        result = 0
        br.readLine().split(" ").map{ it.toInt() }.forEachIndexed { index, value ->
            students[index + 1] = value  // 인덱스 + 1 = 학생 번호
        }
        // 팀 결성
        for(i in 1 .. n){
            if(!visited[i]){
                makeTeam(i)
            }
        }
        println(n - result)
    }
}

fun makeTeam(first : Int) {
    visited[first] = true // 현재 방문한 노드 처리
    val next = students[first] // 다음에 방문할 노드

    if(!visited[next]){ // 다음 노드를 방문하지 않았으면 방문
        makeTeam(next)
    }

    // next를 방문한 적이 있는데, 해당 next가 끝까지 탐색하지 않은 상황 -> cycle
    if(visited[next] && !finished[next]){
        var temp = next
        while (temp != first) {
            result += 1 // 연결되어 있는 부분 + 1
            temp = students[temp]
        }
        result += 1 // first도 팀에 포함
    }
    finished[first] = true
}