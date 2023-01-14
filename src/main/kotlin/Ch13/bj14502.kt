package Ch13

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private var n = 0
private var m = 0
private var result = 0
private lateinit var map : Array<IntArray>
private val viruses : MutableList<Pair<Int, Int>> = mutableListOf()

private fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    var stk = StringTokenizer(br.readLine())
    n = stk.nextToken().toInt()
    m = stk.nextToken().toInt()
    map = Array(n){ IntArray(m) }
    repeat(n){ i ->
        stk = StringTokenizer(br.readLine())
        repeat(m){ j ->
            val token = stk.nextToken().toInt()
            map[i][j] = token
            if(token == 2){
                viruses.add(Pair(i, j))
            }
        }
    }
    // 임의로 벽 3개 세우고, 모든 경우의 수 계산 -> 백트래킹?
    // 바이러스 다 대입하고, 0인경우 2로 전환
    // 1인 곳 세기
    setWall(3, 0)
    print(result)
}

private fun bfs(){
    val dx = intArrayOf(1, 0, -1, 0)
    val dy = intArrayOf(0, 1, 0, -1)
    val q : Queue<Pair<Int, Int>> = LinkedList()
    val visited = Array(n){ BooleanArray(m) }
    val curMap = map.map{ it.copyOf() } // 2차원 배열 깊은 복사
    for(virus in viruses){
        q.add(virus)
        visited[virus.first][virus.second] = true
        while(q.isNotEmpty()){
            val cur = q.poll()
            for(i in 0 .. 3){
                val nx = cur.first + dx[i]
                val ny = cur.second + dy[i]
                if(nx < 0 || ny < 0 || nx >= n || ny >= m || visited[nx][ny]){
                    continue
                }
                if(curMap[nx][ny] == 0){ // 바이러스 감염
                    curMap[nx][ny] = 2 // 감염
                    q.add(Pair(nx, ny)) // 감염된 곳 추가
                    visited[nx][ny]
                }
            }
        }
    }
    var safeCount = 0
    curMap.forEach { arr ->
        arr.forEach {
            if(it == 0){
                safeCount += 1
            }
        }
    }
    result = maxOf(safeCount, result) // 0의 개수
}

// 백트래킹 DFS : 2차원 배열
private fun setWall(depth: Int, start: Int){
    if(depth == 0){ // 벽 3개 다 설치했을 경우
        bfs()
        return
    }
    for(i in start until n * m){
        val x = i / m
        val y = i % m
        if(map[x][y] == 0){
            map[x][y] = 1
            setWall(depth - 1, i + 1)
            map[x][y] = 0
        }
    }
}
