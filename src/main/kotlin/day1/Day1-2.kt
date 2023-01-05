import java.io.BufferedReader
import java.io.InputStreamReader

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))

    val numList = br.readLine()
    var result = numList[0].digitToInt()

    for(i in 1 until numList.length){
        val num = numList[i].digitToInt()
        if((result > 1) and (num > 1)){ // 곱해야하 하는 경우
            if((result * num <= 2000000000)) { // 곱한 값이 20억 보다 작을 때
                result *= num
            }
            else if (result + num <= 2000000000) { // 더한 값이 20억 보다 작을 때
                result += num
            }
        }else { // 더해야 하는 경우
            if (result + num <= 2000000000) { // 더한 값이 20억 보다 작을 때
                result += num
            }
        }
    }
    print(result)
}