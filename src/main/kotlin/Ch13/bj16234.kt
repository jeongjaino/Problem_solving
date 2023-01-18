package Ch13

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.math.abs

private var n = 0
private var r = 0
private var l = 0
private lateinit var map : Array<IntArray>
private lateinit var visited : Array<BooleanArray>
private var result = 0

private fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    var stk = StringTokenizer(br.readLine())
    n = stk.nextToken().toInt()
    r = stk.nextToken().toInt()
    l = stk.nextToken().toInt() // l명 이상 r명 이하
    map = Array(n){ IntArray(n) }
    repeat(n){ i ->
        stk = StringTokenizer(br.readLine())
        repeat(n){ j ->
            map[i][j] = stk.nextToken().toInt()
        }
    }
    // 모든 칸에 대해 연합을 만들고
    // 위에서 접근하는 거랑, 밑에서 접근하는게 다르기에, 모든 칸을 가서 비교해야 해
    while(true) { // 무한 반복, 0에서 n까지 모든 경우가 가능하고, 다시 연합을 해도 됨.
        visited = Array(n) { BooleanArray(n) }
        var flag = false
        repeat(n) { i ->
            repeat(n) { j ->
                if (!visited[i][j]) { // 방문을 하지 않은 곳
                    if(bfs(i, j)){
                        flag = true
                    }
                }
            }
        }
        if(!flag){
            break // bfs() 에서 true 반환이 없을 때 -> 연합 x
        }
        result += 1
    }
    print(result)
}

// 국경선을 공유하는 두 나라의 인구 차이가 L명 이상, R명 이하라면, 두 나라가 공유하는 국경선을 오늘 하루 동안 연다.
// (0, 0)으로 도달할 수 없는 곳도 생각해야 해.
private fun bfs(x : Int, y: Int): Boolean {
    val dx = intArrayOf(1, 0, -1, 0)
    val dy = intArrayOf(0, 1, 0, -1)
    val guild : MutableList<Pair<Int, Int>> = mutableListOf(Pair(x, y)) // 연합
    var sum = map[x][y]
    visited[x][y] = true
    val q : Queue<Pair<Int, Int>> = LinkedList()
    q.add(Pair(x, y))
    while(q.isNotEmpty()){
        val cur = q.poll()
        for(i in 0 until 4){
            val nx = cur.first + dx[i]
            val ny = cur.second + dy[i]
            if(nx < 0 || ny < 0 || nx >= n || ny >= n || visited[nx][ny]){
                continue
            }
            val dif = abs(map[cur.first][cur.second] - map[nx][ny]) // 값의 차이
            if(dif in r..l){ 
                guild.add(Pair(nx, ny)) // 연합 추가 
                sum += map[nx][ny]
                q.add(Pair(nx, ny))
                visited[nx][ny] = true
            }
        }
    }
    val avg = sum / guild.size
    guild.forEach{
        map[it.first][it.second] = avg // 값 평균
    }
    return guild.size > 1 // 자신이외에 길드 나라가 없는 경우 false
}