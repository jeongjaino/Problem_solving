package Ch18

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private lateinit var parent : IntArray

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    while(true) {
        var stk = StringTokenizer(br.readLine())
        val n = stk.nextToken().toInt() // 집의 수
        val m = stk.nextToken().toInt() // 도로의 수
        if(n == 0 && m == 0){ return }

        parent = IntArray(n) { i -> i }
        val edgeList: MutableList<Triple<Int, Int, Int>> = mutableListOf()
        repeat(m) {
            stk = StringTokenizer(br.readLine())
            val x = stk.nextToken().toInt() // x번째 집
            val y = stk.nextToken().toInt() // y번째 집
            val z = stk.nextToken().toInt() // x와 y사이의 거리
            edgeList.add(Triple(x, y, z))
        }

        var result = 0
        edgeList.sortWith(compareBy { it.third })
        edgeList.forEach {
            val x = it.first
            val y = it.second
            val z = it.third
            if (findParent(x) != findParent(y)) { // 루트 노드가 다른 경우
                union(x, y)
            }else{
                result += z
            }
        }
        println(result) // 절약할 수 있는 최대 비용
    }
}

private fun findParent(x : Int) : Int{
    if(parent[x] == x) return x
    parent[x] = findParent(parent[x])
    return parent[x]
}

private fun union(a : Int, b: Int) {
    val A = findParent(a)
    val B = findParent(b)
    if (A == B) return
    parent[B] = A
}
