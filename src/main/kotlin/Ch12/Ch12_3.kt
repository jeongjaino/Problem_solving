package Ch12

fun main(){
    print(solution("ababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdabababcdcdab"))
}

fun solution(s: String): Int {
    val answer = IntArray(s.length / 2 + 1){ s.length }
    for(unit in 1 .. s.length / 2){ // 총 문자열 단위 숫자
        var temp = s.substring(0, unit) // 비교하는 문자열
        var count = 1
        for(i in unit .. s.length - unit step unit){
            if(temp == s.substring(i, i + unit)){ // 앞의 문자와 같은 경우
                count += 1
            }else{ // 앞의 문자와 다른 경우
                if(count >= 2){
                    answer[unit] -= unit * (count - 1) + count.toString().length
                }
                temp = s.substring(i, i + unit) // 비교하는 문자열 다시 설정
                count = 1
            }
        }
        if(count >= 2){
            answer[unit] -= unit * (count - 1) + count.toString().length
        }
    }
    return answer.minOrNull() ?: s.length
}