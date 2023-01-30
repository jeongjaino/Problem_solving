package Ch17

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private lateinit var graph : Array<MutableList<Pair<Int, Int>>>
private lateinit var reverseGraph : Array<MutableList<Pair<Int, Int>>>
private var n = 0
private var x = 0

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val stk = StringTokenizer(br.readLine())
    n = stk.nextToken().toInt()
    val m = stk.nextToken().toInt()
    x = stk.nextToken().toInt()
    graph = Array(n + 1){ mutableListOf() }
    reverseGraph = Array(n + 1){ mutableListOf() }

    repeat(m){
        val (a, b, c) = br.readLine().split(" ").map{ it.toInt() }
        graph[a].add(Pair(b, c)) // 정방향 그래프
        reverseGraph[b].add(Pair(a, c)) // 역방향 그래프
    }
    val distance1 = dijkstra(graph) // x 마을에서 다른 노드까지의 거리
    val distance2 = dijkstra(reverseGraph) // 다른 노드에서 x 마을까지 거리

    var result = 0
    for(i in 1 .. n){
        result = maxOf(result, distance1[i] + distance2[i])
    }
    print(result)
}

fun dijkstra(graph: Array<MutableList<Pair<Int, Int>>>): IntArray{
    val distance = IntArray(n + 1){ Int.MAX_VALUE }
    val visited = BooleanArray(n + 1)
    val q = PriorityQueue<Pair<Int, Int>>{ p1, p2 ->
        when {
            p1.second > p2.second -> 1
            p1.second < p2.second -> -1
            else -> 0
        }
    }
    q.add(Pair(x, 0))
    distance[x] = 0

    while(q.isNotEmpty()){
        val (now, dist) = q.poll()

        if(visited[now])
            continue
        visited[now] = true

        for(i in graph[now]){
            val cost = dist + i.second
            if(cost < distance[i.first]){
                distance[i.first] = cost
                q.add(Pair(i.first, cost))
            }
        }
    }
    return distance
}