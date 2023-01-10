package Ch12

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.math.abs

// 크기가 N×N인 도시가 있다.
// 0은 빈 칸, 1은 집, 2는 치킨집을 의미한다.
// 집의 개수는 2N개를 넘지 않으며, 적어도 1개는 존재한다.
// 치킨집의 개수는 M보다 크거나 같고, 13보다 작거나 같다.

private var n = 0; private var m = 0
private var result = Integer.MAX_VALUE
private lateinit var chickenMap : Array<IntArray>
private lateinit var selected : BooleanArray
private val shopMap : MutableList<Pair<Int, Int>> = mutableListOf() // 치킨집 좌표 리스트
private val houseMap : MutableList<Pair<Int, Int>> = mutableListOf() // 집 좌표 리스트

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    var stk = StringTokenizer(br.readLine())
    n = stk.nextToken().toInt()
    m = stk.nextToken().toInt()
    chickenMap = Array(n){ IntArray(n) }
    repeat(n) { i ->
        stk = StringTokenizer(br.readLine())
        repeat(n) { j ->
            chickenMap[i][j] = stk.nextToken().toInt()
            if (chickenMap[i][j] == 1) {
                houseMap.add(Pair(i, j)) // 집 좌표 등록
            }
            else if (chickenMap[i][j] == 2) {
                shopMap.add(Pair(i, j)) // 치킨집 좌표 등록
            }
        }
    }
    selected = BooleanArray(shopMap.size)
    backtrackingCombination(m, 0)
    print(result)
}

// n의 치킨집 개수, m은 최대 치킨집 개수
// 백트래킹 조합 알고리즘
fun backtrackingCombination(depth: Int, start: Int) {
    if(depth == 0){
        var cityDistance = 0 // 도시의 거리
        repeat(houseMap.size){ i -> // 모든 하우스
            var chickenDistance = 100 // 치킨 거리 최대 100
            repeat(shopMap.size){ j ->
                if(selected[j]){ // 선택된 치킨집
                    chickenDistance = minOf(chickenDistance, getDistance(houseMap[i], shopMap[j]))
                }
            }
            cityDistance += chickenDistance
        }
        result = minOf(result, cityDistance)
        return
    }
    for(i in start until shopMap.size){
        selected[i] = true
        backtrackingCombination(depth - 1,i + 1)
        selected[i] = false;
    }
}

fun getDistance(house: Pair<Int, Int>, shop: Pair<Int, Int>) : Int{
    return abs(house.first - shop.first) + abs(house.second - shop.second)
}
