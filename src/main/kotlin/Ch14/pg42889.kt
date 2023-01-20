package Ch14

fun main(){
    print(solution(5, intArrayOf(2, 1, 2, 6, 2, 4, 3, 3)))
}
// 1, 2, 2, 2, 3, 3, 4, 6
fun solution(N: Int, stages: IntArray) {

    val result = Array(N){ Pair(0, 0.toFloat())} // 인덱스, 값
    var leftCount = stages.size
    var count = 0
    for(i in 1 .. N){
        count = stages.count{ it == i }
        val failed = count / leftCount.toFloat()
        result[i - 1] = Pair(i, failed)
        leftCount -= count
    }
    result.sortWith( compareBy<Pair<Int, Float>> { -it.second }.thenBy { it.first })
    val answer = IntArray(N)
    for(i in 0 until N){
        answer[i] = result[i + 1].first
    }
}