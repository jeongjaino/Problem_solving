package Ch13

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private var n = 0
private var s = 0
private var k = 0
private lateinit var map : Array<IntArray>
private lateinit var viruses : Array<MutableList<Pair<Int, Int>>>

private fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    var stk = StringTokenizer(br.readLine())
    n = stk.nextToken().toInt()
    k = stk.nextToken().toInt()
    map = Array(n){ IntArray(n) }
    viruses = Array(k){ mutableListOf() }
    repeat(n){ i ->
        stk = StringTokenizer(br.readLine())
        repeat(n){ j ->
            map[i][j] = stk.nextToken().toInt()
            if(map[i][j] != 0) {
                viruses[map[i][j] - 1].add(Pair(i, j)) // 바이러스 위치 : 바이러스 번호 = index, 위치 = value
            }
        }
    }
    stk = StringTokenizer(br.readLine())
    // 시간, 좌표
    s = stk.nextToken().toInt()
    val x = stk.nextToken().toInt()
    val y = stk.nextToken().toInt()
    bfs()
    print(map[x - 1][y - 1])
}

// 1초가 지났을 때 왜 이모양이지?
// 순서대로 차례로인데, 모든 1번이 같이 증폭인가?

private fun bfs(){
    // 상하좌우
    val dx = intArrayOf(-1, 1, 0, 0)
    val dy = intArrayOf(0, 0, -1, 1)
    val visited = Array(n){ BooleanArray(n) }
    val q : Queue<Triple<Int, Int, Int>> = LinkedList()
    for(virusList in viruses){ // 바이러스 순서대로
        virusList.forEach{ virus ->
            q.add(Triple(virus.first, virus.second, s))
            visited[virus.first][virus.second] = true
        }
    } // 순서대로 큐에 삽입
    while(q.isNotEmpty()) {
        val cur = q.poll()
        if(cur.third == 0){
            continue // 남은 시간이 0 인 친구는 사용하지 않음.
        }
        for (i in 0..3) {
            val nx = cur.first + dx[i]
            val ny = cur.second + dy[i]
            if(nx < 0 || ny < 0 || nx >= n || ny >= n || visited[nx][ny]){
                continue
            }
            if(map[nx][ny] == 0){
                map[nx][ny] = map[cur.first][cur.second] // 전염
                visited[nx][ny] = true
                q.add(Triple(nx, ny, cur.third - 1))
                continue
            }
        }
    }
}