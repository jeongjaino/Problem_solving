import java.io.BufferedReader
import java.io.InputStreamReader

fun main(){

    val br = BufferedReader(InputStreamReader(System.`in`))

    val num = br.readLine().toInt()
    val coinList = br.readLine().split(" ").map{ it.toInt() }.sorted()
    var target = 1

    // 현재 얻은 화폐의 합까지 모두 만들 수 있다고 가정,
    // 만약 모든 합 target 보다 다음 숫자가 큰 경우
    for(coin in  coinList) {
        if(target < coin) {
            break
        }else {
            target += coin
        }
    }
    print(target)
}