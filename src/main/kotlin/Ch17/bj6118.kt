package Ch17

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    var stk = StringTokenizer(br.readLine())
    val n = stk.nextToken().toInt()
    val m = stk.nextToken().toInt()
    val graph = Array(n + 1){ mutableListOf<Int>() }
    val distance = Array(n + 1){ Int.MAX_VALUE }
    repeat(m){
        stk = StringTokenizer(br.readLine())
        val a = stk.nextToken().toInt()
        val b = stk.nextToken().toInt()
        graph[a].add(b)
        graph[b].add(a)
    }

    val q = PriorityQueue<Pair<Int, Int>>{ p1, p2 -> // 위치와 cost
        when {
            p1.second > p2.second -> 1
            p1.second < p2.second -> -1
            else -> 0
        }
    }

    q.add(Pair(1, 0)) // 시작 헛간
    distance[1] = 0
    while(q.isNotEmpty()){
        val (cur, dist) = q.poll()

        if(dist > distance[cur]) // 방문한 헛간이라면
            continue

        for(next in graph[cur]){
            val cost = dist + 1
            if(distance[next] > cost){
                distance[next] = cost
                q.add(Pair(next, cost))
            }
        }
    }

    var resultIndex = 0
    var resultCount = 0
    var resultDistance = 0
    distance.forEachIndexed{ index, value ->
        if(value != Int.MAX_VALUE){ // 도달할 수 있는 거리 중
            if(resultDistance < value){
                resultDistance = value
                resultIndex = index
                resultCount = 1
            }
            else if(resultDistance == value){
                resultCount += 1
            }
        }
    }
    print("$resultIndex $resultDistance $resultCount")
}
