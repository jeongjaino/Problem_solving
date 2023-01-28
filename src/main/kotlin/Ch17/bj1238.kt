package Ch17

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private lateinit var result : IntArray

// 다른 마을에서 x 마을까지 최단 거리 + x 마을에서 다른 마을까지 최단 거리
fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, m, x) = br.readLine().split(" ").map{ it.toInt() }
    val graph = Array(n+1){ mutableListOf<Pair<Int, Int>>() }
    result = IntArray(n + 1)

    // 그래프 입력
    repeat(m){
        val (a, b, c) = br.readLine().split(" ").map{ it.toInt() }
        graph[a].add(Pair(b, c))
    }

    val distance = (0..n).map{ Int.MAX_VALUE }.toMutableList()

    // 간선 값으로 정렬된 우선순위 큐
    val q = PriorityQueue<Pair<Int, Int>>{ p1, p2 ->
        when {
            p1.second > p2.second -> 1
            p1.second < p2.second -> -1
            else -> 0
        }
    }

    q.add(Pair(x, 0)) // 시작 마을, 간선 값 0
    distance[x] = 0

    while(q.isNotEmpty()){
        val (now, dist) = q.poll() // 최단거리가 가장 짧은 값 꺼내기

        if (distance[now] < dist) // 처리된 값이면 무시
            continue

        for(i in graph[now]){
            val cost = dist + i.second
            if(cost < distance[i.first]){ // 거치는 값이 더 작은 경우
                distance[i.first] = cost
                q.add(Pair(i.first, cost))
            }
        }
    }

    distance.forEachIndexed { index, value ->
        if(value != Int.MAX_VALUE){
            result[index] += value
        }
    }

    var max = - 1
    for(i in 1 .. n){
        val temp = dijkstra(i, graph, n, x)
        if(temp != Int.MAX_VALUE){
            result[i] += temp
            max = maxOf(max, result[i])
        }
    }
    print(max)
}

// 모든 마을에서 x 마을까지 거리
fun dijkstra(start: Int, graph: Array<MutableList<Pair<Int, Int>>>, n : Int, x: Int): Int{
    val distance = (0..n).map{ Int.MAX_VALUE }.toMutableList()

    val q = PriorityQueue<Pair<Int, Int>>{ p1, p2 ->
        when {
            p1.second > p2.second -> 1
            p1.second < p2.second -> -1
            else -> 0
        }
    }

    q.add(Pair(start, 0))
    distance[start] = 0

    while(q.isNotEmpty()){
        val (now, dist) = q.poll()
        if(now == x){
            return dist
        }
        if(distance[now] < dist)
            continue
        for(i in graph[now]){
            val cost = dist + i.second
            if(cost < distance[i.first]){
                distance[i.first] = cost
                q.add(Pair(i.first, cost))
            }
        }
    }
    return Int.MAX_VALUE // 갈수 없는 경우
}