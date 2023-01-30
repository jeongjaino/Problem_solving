package Ch18

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val tc = br.readLine().toInt()
    repeat(tc){
        val n = br.readLine().toInt() // 총 팀의 갯수
        val graph = Array(n + 1){ mutableListOf<Int>() } // 순위 그래표
        val degree = IntArray(n + 1) // 진입 차수
        val teamArray = IntArray(n + 1) // 이전 순위 배열, index = 순위, value = 팀
        var stk = StringTokenizer(br.readLine())
        for(i in 1 .. n){
            val a = stk.nextToken().toInt()
            teamArray[i] = a
            for(j in 2 .. i){
                graph[a].add(teamArray[j - 1]) // 높은 순위에 낮은 순위를 연결
                degree[teamArray[j - 1]] += 1
            }
        }
        val m = br.readLine().toInt() // 상대 순위 변경 팀의 갯수
        repeat(m){
            stk = StringTokenizer(br.readLine())
            val a = stk.nextToken().toInt()
            val b = stk.nextToken().toInt()
            val indexA = teamArray.indexOf(a) // 이전 순위
            val indexB = teamArray.indexOf(b) // 이전 순위
            if(indexA > indexB) {
                graph[a].remove(b)
                degree[b] -= 1
                graph[b].add(a)
                degree[a] += 1
            }
            else {
                graph[b].remove(a)
                degree[a] -= 1
                graph[a].add(b)
                degree[b] += 1
            }
        }
        val q : Queue<Int> = LinkedList()
        for(i in 1 .. n){
            if(degree[i] == 0){
                q.add(i)
            }
        }
        // impossible의 경우 -> cycle이 발생한 경우
        // ?의 경우 -> 정점이면서 진입 차수가 0인 수가 2개인 경우 -> 여러개의 위상정렬이 가능한 경우
        val result = mutableListOf<Int>()
        while(q.isNotEmpty()) {
            if(q.size > 1){
                println("?")
                return
            }
            val cur = q.poll()
            result.add(cur)
            for (i in graph[cur]) {
                degree[i] -= 1
                if (degree[i] == 0) {
                    q.add(i)
                }
            }
        }
        // 모든 노드를 연결했다면
        if(result.size == n){
            for(i in (n - 1) downTo 0){
                print("${result[i]} ")
            }
        }else{
            print("IMPOSSIBLE")
        }
        println()
    }
}