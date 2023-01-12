package Ch13

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(){

    val br = BufferedReader(InputStreamReader(System.`in`))
    var stk = StringTokenizer(br.readLine())
    val n = stk.nextToken().toInt() // 도시의 개수
    val m = stk.nextToken().toInt() // 도로 개수
    val k = stk.nextToken().toInt() // 거리 정보
    val x = stk.nextToken().toInt() - 1 // 출발 도시
    val map = Array(n){ mutableListOf<Int>() }
    repeat(m){
        stk = StringTokenizer(br.readLine())
        val a = stk.nextToken().toInt() - 1
        val b = stk.nextToken().toInt() - 1
        map[a].add(b)
    }

    val result = MutableList(n + 1) { Integer.MAX_VALUE } // index = city, value = depth
    val q : Queue<Int> = LinkedList()
    val visited = BooleanArray(n)
    q.add(x)
    visited[x] = true
    result[x] = 0
    while(q.isNotEmpty()){
        val cur = q.poll()
        for(city in map[cur]){
            if(!visited[city]) {
                result[city] = result[cur] + 1
                q.add(city)
                visited[city] = true
            }
        }
    }
    if(result.count{ it == k } != 0){
        result.forEachIndexed{ index, value ->
            if(value == k){
                println(index + 1)
            }
        }
    }else{
        print(-1)
    }
}