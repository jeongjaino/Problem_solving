package Ch12

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    var stk = StringTokenizer(br.readLine())
    val n = stk.nextToken().toInt() // 세로
    val m = stk.nextToken().toInt() // 가로

    val cheeseMap = Array(n){ IntArray(m) } // 치즈 맵
    val dx = arrayOf(-1, 1, 0 ,0)
    val dy = arrayOf(0, 0, -1, 1)
    var cheeseTime = 0
    var cheeseCount = 0

    repeat(n){ row ->
        stk = StringTokenizer(br.readLine())
        repeat(m){ col ->
            cheeseMap[row][col] = stk.nextToken().toInt()
            if(cheeseMap[row][col] == 1){
                cheeseCount += 1
            }
        }
    }

    var temp = 0
    while(cheeseCount != 0){
        temp = cheeseCount // 이전 치즈 개수 저장
        val visited = Array(n){ BooleanArray(m) } // 방문 처리 (0 인곳 재방문 방지)
        val q : Queue<IntArray> = LinkedList<IntArray>()
        q.add(intArrayOf(0, 0))
        visited[0][0] = true
        while(q.isNotEmpty()){
            val cur = q.poll()
            for(i in 0 .. 3){
                val nx = cur[0] + dx[i]
                val ny = cur[1] + dy[i]
                if(nx < 0 || ny < 0 || nx >= n || ny >= m || visited[nx][ny]){
                    continue
                }
                if(cheeseMap[nx][ny] == 1){ // 1인 친구를 다시 넣으면 안됨
                    cheeseMap[nx][ny] = 0
                    cheeseCount -= 1
                }else if(cheeseMap[nx][ny] == 0){ // 방문 안하고, 연결된 노드만 돌아야함.
                    q.add(intArrayOf(nx, ny))
                }
                visited[nx][ny] = true
            }
        }
        cheeseTime += 1 // 한번 돌 때마다 시간 경과
    }
    println(cheeseTime)
    print(temp)
}
