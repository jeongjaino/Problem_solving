package Ch12

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(){

    val br = BufferedReader(InputStreamReader(System.`in`))

    val numList = br.readLine().toList().map{ it.digitToInt() }
    val listSize = numList.size
    val leftSum = numList.subList(0, listSize/2).sum() // 왼쪽 리스트 분할
    val rightSum = numList.subList(listSize/2, listSize).sum() // 오른쪽 리스트 분할

    if(leftSum == rightSum) print("LUCKY") else print("READY")
}
