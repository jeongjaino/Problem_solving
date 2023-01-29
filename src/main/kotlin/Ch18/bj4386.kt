package Ch18

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.math.*

private lateinit var parent : IntArray

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    var stk : StringTokenizer
    val n = br.readLine().toInt()
    parent = IntArray(n){ i -> i }
    val starArray = Array(n){ Pair(0.0, 0.0) } // a별 x좌표, a별 y좌표
    val edgeList : MutableList<Triple<Int, Int, Double>> = mutableListOf() // a별, b별, a->b cost
    repeat(n){ i ->
        stk = StringTokenizer(br.readLine())
        starArray[i] = Pair(stk.nextToken().toDouble(), stk.nextToken().toDouble())
        repeat(i){ j ->
            val dist = getDistance(starArray[i], starArray[j])
            edgeList.add(Triple(j, i, dist))
        }
    }
    edgeList.sortWith( compareBy{ it.third } )  // 비용을 기준으로 정렬

    var totalCost = 0.0
    edgeList.forEach{
        val a = it.first
        val b = it.second
        val cost = it.third
        if(findParent(a) != findParent(b)){ // 크루스칼 부모가 다르면 유니온
            union(a, b)
            totalCost += cost
        }
    }
    print(totalCost)
}

fun getDistance(i : Pair<Double, Double>, j : Pair<Double, Double>): Double{
    return floor(hypot((i.first - j.first), (i.second - j.second)) * 100 ) / 100
}

fun findParent(x : Int) : Int{
    if(parent[x] == x) return x
    parent[x] = findParent(parent[x])
    return parent[x]
}

fun union(a : Int, b: Int) {
    val A = findParent(a)
    val B = findParent(b)
    if (A == B) return
    parent[B] = A
}
