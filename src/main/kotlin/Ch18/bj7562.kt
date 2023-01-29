package Ch18

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private val dxy = arrayOf(Pair(-1, -2), Pair(1, -2), Pair(-2, -1), Pair(2, -1),
    Pair(2, 1), Pair(-2, 1), Pair(-1, 2), Pair(1, 2))

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val tc = br.readLine().toInt()
    var stk : StringTokenizer
    repeat(tc){
        val length = br.readLine().toInt()
        stk = StringTokenizer(br.readLine())
        val cur = Pair(stk.nextToken().toInt(), stk.nextToken().toInt()) // 현재위치 입력
        stk = StringTokenizer(br.readLine())
        val next = Pair(stk.nextToken().toInt(), stk.nextToken().toInt()) // 목적지 입력
        if(cur == next){ // 현재와 목적지가 같을 때
            println(0)
        }else {
            println(knightMove(length, cur, next))
        }
    }
}
fun knightMove(length: Int, cur: Pair<Int, Int>, next: Pair<Int, Int>): Int{
    val visited = Array(length){ BooleanArray(length) } // 방문 처리
    visited[cur.first][cur.second] = true
    val q : Queue<Triple<Int, Int, Int>> = LinkedList()
    q.add(Triple(cur.first, cur.second, 0)) // x좌표, y좌표, cost
    while(q.isNotEmpty()){
        val pos = q.poll()
        for(i in dxy){
            val dx = pos.first + i.first
            val dy = pos.second + i.second

            if(dx < 0 || dy < 0 || dx >= length || dy >= length || visited[dx][dy])
                continue

            if(dx == next.first && dy == next.second) {
                return pos.third + 1
            }
            q.add(Triple(dx, dy, pos.third + 1))
            visited[dx][dy] = true
        }
    }
    return 0
}
