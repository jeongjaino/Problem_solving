import java.io.BufferedReader
import java.io.InputStreamReader

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val num = br.readLine().toInt()
    val coinList = mutableListOf<Int>()
    br.readLine().split(" ").forEach{
        coinList.add(it.toInt())
    }
    coinList.sortDescending()
    repeat(coinList.first() * num){  // 양의 정수를 찾는 반복
        for(i in 1 until coinList.size) {
            var resultNum = it
            for (j in i until coinList.size) {
                if (resultNum >= coinList[j]) { // 현재 수가 양의 정수 보다 이하일 때
                    resultNum -= coinList[j]
                }
            }
            if(resultNum == 0){
                break
            }
            if(i == coinList.size - 1){
                print(it)
                return
            }
        }
    }
}