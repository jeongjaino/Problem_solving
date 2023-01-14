package Ch13

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private var n = 0
private var studentCount = 0
private var result = false
private lateinit var map : Array<IntArray>
private val teachers : MutableList<Pair<Int, Int>> = mutableListOf()

private fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    var stk = StringTokenizer(br.readLine())
    n = stk.nextToken().toInt()
    map = Array(n){ IntArray(n) }
    repeat(n){ i ->
        stk = StringTokenizer(br.readLine())
        repeat(n){ j ->
            val token = stk.nextToken().toString()
            if(token == "S"){ // 학생은 3
                map[i][j] = 3
                studentCount += 1
            }
            else if(token == "T"){ // 선생은 2
                map[i][j] = 2
                teachers.add(Pair(i, j))
            }
            else{ // 공간은 0
                map[i][j] = 0
            }
        }
    }
    setWall(3, 0)
    if(result){
       print("YES")
    }else{
        print("NO")
    }
}

private fun bfs(){
    val dx = intArrayOf(-1, 1, 0, 0)
    val dy = intArrayOf(0, 0, -1, 1)
    val q : Queue<Pair<Int, Int>> = LinkedList()
    val curMap = map.map{ it.copyOf() } // 2차원 배열 깊은 복사
    for(teacher in teachers){
        q.add(teacher)
        while(q.isNotEmpty()){
            val cur = q.poll()
            for(i in 0 .. 3){
                for(j in 0 until n){
                    val nx = cur.first + dx[i] * j // next 찾기
                    val ny = cur.second + dy[i] * j // next
                    if(nx < 0 || ny < 0 || nx >= n || ny >= n){
                        continue
                    }
                    if(curMap[nx][ny] == 1){ // 벽을 만나면 break
                        break
                    }
                    if(curMap[nx][ny] == 0 || curMap[nx][ny] == 3){ // 선생님 감시 가능 구역(학생, 빈공간)
                        curMap[nx][ny] = 2 // 감시가능 구역으로 변경
                    }
                }
            }
        }
    }
    var safeCount = 0
    curMap.forEach { arr ->
        arr.forEach {
            if(it == 3){
                safeCount += 1
            }
        }
    }
    val curResult = studentCount == safeCount
    result = result or curResult
}

// 백트래킹 DFS : 2차원 배열
private fun setWall(depth: Int, start: Int){
    if(depth == 0){ // 벽 3개 다 설치했을 경우
        bfs()
        return
    }
    for(i in start until n * n){
        val x = i / n
        val y = i % n
        if(map[x][y] == 0){
            map[x][y] = 1
            setWall(depth - 1, i + 1)
            map[x][y] = 0
        }
    }
}
