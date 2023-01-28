package Ch17

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val graph = Array(n + 1){ IntArray(n + 1){ 0 } }

    repeat(m){
        val (a, b) = br.readLine().split(" ").map { it.toInt() }
        graph[a][b] = 1 // 알 수 있음
    }

    for(k in 1 .. n){
        for(a in 1 .. n){
            for(b in 1.. n){
                if(graph[a][k] == 1 && graph[k][b] == 1){
                    graph[a][b] = 1
                }
            }
        }
    }

    var result = 0
    for(a in 1 .. n){
        var flag = true
        for(b in 1 .. n){
            if(a == b){
                continue
            }
            if(graph[a][b] == 0 && graph[b][a] == 0){
                flag = false
                break
            }
        }
        if(flag){
            result += 1
        }
    }
    print(result)
}
