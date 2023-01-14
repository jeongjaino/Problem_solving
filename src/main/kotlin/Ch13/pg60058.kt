package Ch13

fun main(){
    print(solution("(()())()"))
}

fun solution(p: String): String {
    var answer = ""
    if(p.isEmpty()) {
        return answer
    }
    val index = balancedString(p)
    var u = p.substring(0, index + 1)
    val v = p.substring(index + 1, p.length)
    if (correctedString(u)) { // 올바른 문자열
        answer = u + solution(v)
    } else { // 올바르지 않은 문자열
        answer = "(" + solution(v) + ")"
        u = u.substring(1, u.length - 1)
        var reversedString = ""
        for(i in u.indices){
            reversedString += if(u[i] == '(') { ')' } else { '(' }
        }
        answer += reversedString
    }
    return answer
}
// 균형 잡힌 스트링인지
fun balancedString(p: String) : Int {
    var count = 0
    for(i in p.indices){
        if(p[i] == '('){
            count += 1
        }else{
            count -= 1
        }
        if(count == 0){
            return i
        }
    }
    return 0
}

fun correctedString(p: String): Boolean{
    var count = 0
    for(i in p.indices){
        if(p[i] == '('){
            count += 1
        }else{
            count -= 1
        }
        if(count < 0){
            return false
        }
    }
    return true
}